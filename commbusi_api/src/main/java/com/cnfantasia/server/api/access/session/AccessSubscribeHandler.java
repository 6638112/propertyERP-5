package com.cnfantasia.server.api.access.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.entity.AccessMsgBean;
import com.cnfantasia.server.api.access.web.socket.DefaultAccessHandler;
import com.cnfantasia.server.api.access.web.socket.DefaultAccessHandler.qryFeeListener;
import com.cnfantasia.server.api.access.web.socket.TcpServer;
import com.cnfantasia.server.api.accesslink.entity.AccessLinkDto;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DateUtils;

/**
 * 消息订阅
 * 
 * @author liyulin
 * @version 1.0 2016年6月27日 下午3:52:17
 */
public class AccessSubscribeHandler implements IAccessSubscribeHandler {
	private final Logger logger = Logger.getLogger(getClass());

	private DefaultAccessHandler defaultAccessHandler;
	private DefaultAccessHandler getDefaultAccessHandler() {
		if(defaultAccessHandler == null) {
			defaultAccessHandler = (DefaultAccessHandler)CnfantasiaCommbusiUtil.getBeanManager("defaultAccessHandler");
		}
		return defaultAccessHandler;
	}
	/**
	 * 消息处理
	 * 
	 * @param message
	 */
	@SuppressWarnings("static-access")
	@Override
	public void handleMessage(Serializable message) {
		if (message instanceof AccessMsgBean) {
			AccessMsgBean msg = (AccessMsgBean) message;
			logger.info("AccessSubscribeHandler.handleMessage==>"+msg);
			
			switch (msg.getMsgType()) {
			/*<=============================万人插start=========================*/
			case AccessDict.MsgType.W_QUERY_FEE:
				// 查询费用
				Map<String, Object> feeMap = qryFee(msg);
				if (feeMap.get("fee") != null) {
					// key-value：【sessionId+'fee'】-【Map<String, String>的序列化】
					RedisCacheHandler.set(msg.getUuid() + "fee", JSON.toJSONString(feeMap), AccessDict.CacheExpire.EXPIRE_120s);
				}
				break;
			case AccessDict.MsgType.W_SEND_PAY_SUCCESS:
				reqPayFee(msg);
				break;
			case AccessDict.MsgType.W_QUERY_ALL_LINKS:
				// 查询所有连接
				List<AccessLinkDto> accessLinks = queryTcpLinks();
				if (accessLinks != null && accessLinks.size() > 0) {
					String[] allAccessLink = new String[accessLinks.size()];
					for (int i = 0, size = accessLinks.size(); i < size; i++) {
						AccessLinkDto accessLink = accessLinks.get(i);
						allAccessLink[i] = JSON.toJSONString(accessLink);
					}
					// key-value：【sessionId+'links'】-【AccessLinkDto的序列化数组】
					RedisCacheHandler.lpush(msg.getUuid() + "links", allAccessLink);
				}
				RedisCacheHandler.count(msg.getUuid() + "count");
				break;
			case AccessDict.MsgType.W_BREAK_LINK:
				// 断开连接
				closeTcpLinkByIoSessionId(msg.getIoSessionId());
				break;
			case AccessDict.MsgType.W_ADD_BLACK_LIST:
				// 加入黑名单
				TcpServer.addBlackList(msg.getRemoteIp());
				closeTcpLinkByIp(msg.getRemoteIp());
				break;
			case AccessDict.MsgType.W_REMOVE_BLACK_LIST:
				// 移除黑名单
				TcpServer.removeBlackList(msg.getRemoteIp());
				break;
			/*=============================万人插end=========================>*/
			default:
				break;
			}
		}
    }
	
    /**
     * 发送缴费成功
     * 
     * @param msg
     * @return
     */
    public boolean reqPayFee(AccessMsgBean msg){
    	return getDefaultAccessHandler().reqPayFee(msg.getGbId(), msg.getCarNum(), msg.getFee().floatValue(), msg.getPayDate());
    }
    
    /**
     * 查询费用
     * 
     * @param msg
     * @return
     */
    public JSONObject qryFee(final AccessMsgBean msg){
    	final JSONObject result = new JSONObject();

		if (StringUtils.isNotBlank(msg.getCarNum()) && msg.getGbId() != null) {
        	final CountDownLatch countDownLatch = new CountDownLatch(1);
            boolean isSendSuccessed = getDefaultAccessHandler().reqQryFee(new qryFeeListener() {

                @Override
                public void onReceived(String plate, float fee, Date inDate) {
                    synchronized (result) {
                        result.put("plate", plate);
                        result.put("fee", new BigDecimal(fee));
                        result.put("enterTime", inDate.getTime());

                        countDownLatch.countDown();
                    }
                }

                @Override
                public void onFail(String desc) {
                    // 查询失败处理
                	countDownLatch.countDown();
                }
			}, msg.getGbId(), msg.getCarNum());
            
            if (isSendSuccessed) {
                Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.QUERY_PARKING_FEE_WAITING_TIME, 5 * 1000);
                
                try {
					countDownLatch.await(waitTime, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					logger.error("qryFee.InterruptedException", e);
				}
            }
        } 
        
        return result;
    }
     
    /**
     * 查询所有Tcp客户端连接
     * 
     * @return
     */
    public List<AccessLinkDto> queryTcpLinks(){
    	Map<Long, IoSession> sMap = getDefaultAccessHandler().getmTcpServer().getManagedSessions();
    	
    	List<AccessLinkDto> accessLinks = new ArrayList<AccessLinkDto>();
    	for (Map.Entry<Long, IoSession> sEntry : sMap.entrySet()) {
            IoSession session = sEntry.getValue();
            long ioSessionId = session.getId();
            Object localIp = session.getAttribute(DefaultAccessHandler.KEY_LOCAL_IP);
            Object remoteIp = session.getAttribute(DefaultAccessHandler.KEY_REMOTE_IP);
            Object gbName = session.getAttribute(DefaultAccessHandler.KEY_GB_NAME);
            Object version = session.getAttribute(DefaultAccessHandler.KEY_REMOTE_VERSION);
            Long createTime = session.getCreationTime();
            String linkTime = DateUtils.formatTime(new Date(createTime));
            
            AccessLinkDto accessLink = new AccessLinkDto();
            accessLink.setIoSessionId(ioSessionId);
            accessLink.setLocalIp(String.valueOf(localIp));
            accessLink.setRemoteIp(String.valueOf(remoteIp));
            accessLink.setGbName(String.valueOf(gbName));
            accessLink.setLinkTime(linkTime);
            accessLink.setVersion(version);
            
            accessLinks.add(accessLink);
    	}
    	return accessLinks;
    }
    
    /**
     * 根据ioSessionId断开连接
     * 
     * @param ioSessionId
     * @return
     */
    public void closeTcpLinkByIoSessionId(long ioSessionId){
    	Map<Long, IoSession> sMap = getDefaultAccessHandler().getmTcpServer().getManagedSessions();
    	for (Map.Entry<Long, IoSession> sEntry : sMap.entrySet()) {
            IoSession session = sEntry.getValue();
            if(ioSessionId == session.getId()){
            	session.closeNow();
            	break;
            }
        }
    }
    
    /**
     * 根据ip断开连接
     * 
     * @param ip
     */
    public void closeTcpLinkByIp(String ip){
    	Map<Long, IoSession> sMap = getDefaultAccessHandler().getmTcpServer().getManagedSessions();
    	for (Map.Entry<Long, IoSession> sEntry : sMap.entrySet()) {
            IoSession session = sEntry.getValue();
            String remoteIp = session.getRemoteAddress().toString();
            remoteIp = remoteIp.substring(1, remoteIp.lastIndexOf(":"));
            if(remoteIp.equals(ip)){
            	session.closeNow();
            }
        }
    }
    
    
    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public final static String getDistanceTime(Date one, Date two) {
        long day = 0;
        long hour = 0;
        long min = 0;
        // long sec = 0;
        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        // sec = (diff/1000-day*24*60*60-hour*60*60-min*60);

        StringBuilder result = new StringBuilder();
        if (day != 0) {
            result.append(day + "天");
        }
        if (day != 0 || hour != 0) {
            result.append(hour + "小时");
        }
        result.append(min + "分");
        return result.toString();
    }
}