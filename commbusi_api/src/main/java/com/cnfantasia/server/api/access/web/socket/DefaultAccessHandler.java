package com.cnfantasia.server.api.access.web.socket;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.cnfantasia.server.api.access.constant.AccessConstant;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.entity.CarNumPayLogDetail;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.access.web.socket.codec.general.JfqRpc;
import com.cnfantasia.server.api.access.web.socket.codec.general.JfqRpc.JfqMsg;
import com.cnfantasia.server.api.access.web.socket.codec.general.JfqRpc.ReqUpdateOwner;
import com.cnfantasia.server.api.access.web.socket.codec.general.JfqRpc.Resp.Builder;
import com.cnfantasia.server.api.access.web.socket.codec.general.JfqRpc.RespSyncInfo;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.carYhsMsg.entity.CarYhsMsgSendParam;
import com.cnfantasia.server.carYhsMsg.service.ICarYhsMsgService;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.blackList.entity.BlackList;
import com.cnfantasia.server.domainbase.blackList.service.IBlackListBaseService;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carGroupBuilding.service.ICarGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumList.service.ICarNumListBaseService;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.service.ICarNumPayLogBaseService;
import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description 车禁数据源服务器
 *
 */
public class DefaultAccessHandler implements IoHandler {
	 protected final Logger mLogger = Logger.getLogger(getClass());
    /**
     * @author wangzhe
     * @date 2016年4月14日
     * @description 查询停车费监听器
     *
     */
    public static interface qryFeeListener {

        void onReceived(String plate, float fee, Date inDate);

        void onFail(String desc);
    }

    public static final String KEY_CAR_GROUP_BUILDING = "key_car_group_building"; // 停车场编码的key
                                                                                  // value:CarGroupBuilding
    public static final String KEY_GB_ID = "key_group_building_id"; // 小区ID的key
                                                                    // value:BigInteger
    public static final String KEY_LOGIN = "key_login"; // 已登录 value:boolean

    public static final String KEY_PARK_CODE = "key_park_code"; // 停车场编码的key
                                                                // value:String
    /** server端ip */
    public static final String KEY_LOCAL_IP = "key_local_ip";

    /** client段ip */
    public static final String KEY_REMOTE_IP = "key_remote_ip";

    public static final String KEY_GB_NAME = "key_gb_name";// 小区名称

    private static final String KEY_PENDING_MOONCARD_PUSH = "key_pending_mooncard_push"; // 正在推送的月卡缴费信息
    
    public static final String KEY_REMOTE_VERSION = "key_remote_version";//客户端版本号
    // value:List<CarNumPayLogDetail>

    @Resource
    private IAccessService accessService;

    @Resource
    private ICarGroupBuildingBaseService carGroupBuildingBaseService;

    @Resource
    private ICarNumListBaseService carNumListBaseService;

    @Resource
    private ICarNumPayLogBaseService carNumPayLogBaseService;

    private TcpServer mTcpServer = new TcpServer();

    @Resource
    private ISysParamManager sysParamManager;

    @Resource
    private IUuidManager uuidManager;

    @Resource
    private IGroupBuildingBaseService groupBuildingBaseService;

    @Resource
    private IBlackListBaseService blackListBaseService;
    
    @Resource
    private ICarYhsMsgService carYhsMsgService;

    private static SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TcpServer getmTcpServer() {
        return mTcpServer;
    }

    public void setmTcpServer(TcpServer mTcpServer) {
        this.mTcpServer = mTcpServer;
    }

    public void destroy() {
        mTcpServer.destroy();
    }

    @Override
    public void exceptionCaught(final IoSession session, final Throwable cause) throws Exception {
        //mLogger.debug(session, cause);
    }

    public void init() {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("type", 1);
    	List<BlackList> blackLists = blackListBaseService.getBlackListByConditionDim(paramMap);
    	InetAddress[] ips = new InetAddress[blackLists.size()];
    	try {
	    	for (int i = 0; i < blackLists.size(); i++) {
				ips[i] = InetAddress.getByName(blackLists.get(i).getContent());
			}
    	} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	mTcpServer.initBlacklist(ips);
    	
        mTcpServer.init(new DefaultAccessEncoder(), new DefaultAccessDecoder(), this);
        int port = 8443;
        try {
            port = Integer.valueOf(sysParamManager.getSysParaValue("accessTcpDefaultPort"));
        } catch (NumberFormatException e) {
        }
        String filename = DefaultAccessHandler.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        mLogger.info("AccessHandler文件名:" + filename);
        if (!filename.matches(".*API\\d{1,}?.*")) {
            mLogger.info("AccessHandler文件启动:" + filename);
            mTcpServer.bind(port);
        } else {
            mLogger.info("AccessHandler文件不启动:" + filename);
        }
        /*if(filename.contains("API507")){
       	    mLogger.info("AccessHandler文件启动:" + filename);
            mTcpServer.bind(port);
        } else {
            mLogger.info("AccessHandler文件不启动:" + filename);
        }*/
    }
    
    @Override
    public void inputClosed(final IoSession session) throws Exception {
        // mLogger.info("inputClosed..." + session);
        session.closeOnFlush();
    }

    /**
     * @author wangzhe
     * @date 2016年4月13日
     * @description 处理登陆信息
     *
     * @param session
     * @param respBuilder
     * @param login
     */
    private void login(final IoSession session, JfqRpc.Resp.Builder respBuilder, JfqRpc.ReqLogin login) {
        final JfqRpc.RespLogin.Builder loginbuilder = JfqRpc.RespLogin.newBuilder();
        respBuilder.setResult(false);
        loginbuilder.setResult(false);
        
        if (null != login.getSid() && !TextUtils.isEmpty(login.getSid())) {
            try {
                Date sid = mSdf.parse(login.getSid());
                Date now = new Date();
                if (Math.abs(sid.getTime() - now.getTime()) <= 1000L * 60 * 60 * 24) {
                    CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(login.getParkLot());
                    if (null != carGroupBuilding && carGroupBuilding.getAccount().equals(login.getAccount())
                            && carGroupBuilding.getPwd().equals(login.getPwd())) {
                        respBuilder.setResult(true);
                        loginbuilder.setResult(true);
                        loginbuilder.setDate(DateUtils.getCurrentDate());
                        session.setAttribute(KEY_PARK_CODE, login.getParkLot());
                        session.setAttribute(KEY_LOGIN, true);
                        session.setAttribute(KEY_CAR_GROUP_BUILDING, carGroupBuilding);
                        session.setAttribute(KEY_GB_ID, carGroupBuilding.gettGroupBuildingId());

                        GroupBuilding groupBuilding = groupBuildingBaseService
                                .getGroupBuildingBySeqId(carGroupBuilding.gettGroupBuildingId());
                        session.setAttribute(KEY_GB_NAME, groupBuilding.getName());
                        {
                            String remoteIp = session.getRemoteAddress().toString();
                            remoteIp = remoteIp.substring(1, remoteIp.lastIndexOf(":"));
                            String localIp = session.getLocalAddress().toString();
                            localIp = localIp.substring(1, localIp.lastIndexOf(":"));

                            session.setAttribute(KEY_LOCAL_IP, localIp);
                            session.setAttribute(KEY_REMOTE_IP, remoteIp);
                        }
                        
                        session.setAttribute(KEY_REMOTE_VERSION, login.getVersion());

                        RedisCacheHandler.srem(RedisCachePrefix.car_offline_list, carGroupBuilding.gettGroupBuildingId().toString());
                        
                    } else {
                    }
                } else {
                }
            } catch (ParseException e) {
                mLogger.error(e);
            }
        }

        respBuilder.setPayload(loginbuilder.build().toByteString());
    }

    @Override
    public void messageReceived(final IoSession session, final Object obj) throws Exception {
        // mLogger.info("messageReceived..." + session);
        if (obj instanceof JfqMsg) {
            final JfqMsg msg = (JfqMsg) obj;
            try {
                if (JfqRpc.ToM.Request.equals(msg.getMt())) {
                    processRequest(session, msg);
                } else {
                    processResponse(session, msg);
                }
            } catch (final InvalidProtocolBufferException e) {
            }
        } else {
        }

        Object bLogin = session.getAttribute(KEY_LOGIN, false);
        if (!(bLogin instanceof Boolean) || !(Boolean) bLogin) {
            session.closeOnFlush();
        } else {
        }
    }

    @Override
    public void messageSent(final IoSession session, final Object message) throws Exception {
        // mLogger.info("messageSent..." + session);
    }

    /**
     * @author wangzhe
     * @date 2016年4月13日
     * @description 月卡信息
     *
     * @param respBuilder
     * @param moncard
     */
    private void monCard(Object gbId, Builder respBuilder, JfqRpc.ReqMonCard rmc) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        List<JfqRpc.MonCardItem> mcis = rmc.getMcList();
        for (JfqRpc.MonCardItem mci : mcis) {
        	paramMap.clear();
        	paramMap.put("carNum", mci.getPlate());
        	paramMap.put("gbId", gbId);
            CarNumList carnum = accessService.getCardetail(paramMap);

            if (null != carnum) {
                carnum.setExpireDate(mci.getValidDate());
                carNumListBaseService.updateCarNumList(carnum);

                CarNumPayLog carNumpayLog = new CarNumPayLog();
                carNumpayLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log));
                carNumpayLog.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
                carNumpayLog.setPayTime(mci.getPayDate());
                carNumpayLog.setFee((long) (mci.getFee() * 100f));
                int payNum = mci.getInterval();
                carNumpayLog.setPayNum((long) payNum);
                carNumpayLog.settCarNumId(carnum.getId());
                carNumpayLog.setStatus(1);// 非解放区缴费
                carNumpayLog.setPushStatus(1);// 已推送
                carNumpayLog.settEbuyOrderId(null);// 非解放区无订单号
                {
                    if (payNum > 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date now = DateUtils.strToDateTime(mci.getValidDate());
                        carNumpayLog.setPayStartDate(sdf.format(DateUtils.decreateMonth(now, payNum)));
                        carNumpayLog.setPayEndDate(sdf.format(now));
                    }
                }
                carNumPayLogBaseService.insertCarNumPayLog(carNumpayLog);
                respBuilder.setResult(true);
            } else {
                // TODO 没有该车牌
                respBuilder.setResult(true);
            }
        }
    }

    /**
     * @author wangzhe
     * @date 2016年5月25日
     * @description 发送缴费信息
     *
     * @param gbid
     * @param mcis
     * @return
     */
    public boolean reqMoonCard(BigInteger gbid, List<JfqRpc.MonCardItem> mcis) {
        boolean ret = false;
        if (null != gbid && null != mcis && 0 < mcis.size()) {
            Map<Long, IoSession> sMap = mTcpServer.getManagedSessions();
            for (Map.Entry<Long, IoSession> sEntry : sMap.entrySet()) {
                IoSession session = sEntry.getValue();
                if (gbid.equals(session.getAttribute(KEY_GB_ID))) {
                    JfqRpc.JfqMsg.Builder builder = JfqRpc.JfqMsg.newBuilder();
                    builder.setId(new Date().getTime());
                    builder.setMt(JfqRpc.ToM.Request);
                    builder.setSt(JfqRpc.ToS.MonCard);

                    JfqRpc.Req.Builder reqBuilder = JfqRpc.Req.newBuilder();
                    reqBuilder.setDate(DateUtils.getCurrentDate());

                    JfqRpc.ReqMonCard.Builder rmcBuilder = JfqRpc.ReqMonCard.newBuilder();
                    rmcBuilder.addAllMc(mcis);

                    reqBuilder.setPayload(rmcBuilder.build().toByteString());

                    builder.setRequest(reqBuilder);

                    session.write(builder.build());
                    ret = true;
                } else {
                }
            }
        } else {
        }
        return ret;
    }

    /**
     * @author wangzhe
     * @date 2016年4月14日
     * @description 处理请求
     *
     * @param session
     * @param msg
     * @throws InvalidProtocolBufferException
     */
    private void processRequest(final IoSession session, final JfqMsg msg) throws InvalidProtocolBufferException {
        final JfqRpc.JfqMsg.Builder msgBuilder = JfqRpc.JfqMsg.newBuilder();
        msgBuilder.setId(msg.getId());
        msgBuilder.setMt(JfqRpc.ToM.Response);
        msgBuilder.setSt(msg.getSt());

        final JfqRpc.Resp.Builder respBuilder = JfqRpc.Resp.newBuilder();

        Object req = null;
        switch (msg.getSt().getNumber()) {
        case JfqRpc.ToS.Login_VALUE:
            login(session, respBuilder, JfqRpc.ReqLogin.parseFrom(msg.getRequest().getPayload()));
            break;
        case JfqRpc.ToS.HeartBeat_VALUE:
            // 回应心跳，没有payload
            break;
        case JfqRpc.ToS.VehIn_VALUE:
            vehIn((BigInteger) session.getAttribute(KEY_GB_ID), respBuilder, JfqRpc.ReqVehIn.parseFrom(msg.getRequest().getPayload()));
            break;
        case JfqRpc.ToS.VehOut_VALUE:
            vehOut((BigInteger) session.getAttribute(KEY_GB_ID), respBuilder, JfqRpc.ReqVehOut.parseFrom(msg.getRequest().getPayload()));
            break;
        case JfqRpc.ToS.UpdateOwner_VALUE:
            reqUpdateOwner((BigInteger) session.getAttribute(KEY_GB_ID), respBuilder,
                    JfqRpc.ReqUpdateOwner.parseFrom(msg.getRequest().getPayload()));
            break;
        case JfqRpc.ToS.MonCard_VALUE:
        	Object gbId = session.getAttribute(KEY_GB_ID);
            monCard(gbId, respBuilder, JfqRpc.ReqMonCard.parseFrom(msg.getRequest().getPayload()));
            break;
        case JfqRpc.ToS.BaseInfo_VALUE:
            // server端不处理
            respBuilder.setResult(false);
            respBuilder.setDesc("server端不处理");
            break;
        case JfqRpc.ToS.SyncInfo_VALUE:
            // server端不处理
            req = JfqRpc.ReqSyncInfo.parseFrom(msg.getRequest().getPayload());
            respBuilder.setResult(false);
            respBuilder.setDesc("server端不处理");
            break;
        case JfqRpc.ToS.QryFee_VALUE:
            // server端不处理
            req = JfqRpc.ReqQryFee.parseFrom(msg.getRequest().getPayload());
            respBuilder.setResult(false);
            respBuilder.setDesc("server端不处理");
            break;
        case JfqRpc.ToS.PayFee_VALUE:
            // server端不处理
            req = JfqRpc.ReqPayFee.parseFrom(msg.getRequest().getPayload());
            respBuilder.setResult(false);
            respBuilder.setDesc("server端不处理");
            break;
        case JfqRpc.ToS.Lock_VALUE:
            // server端不处理
            req = JfqRpc.ReqLock.parseFrom(msg.getRequest().getPayload());
            respBuilder.setResult(false);
            respBuilder.setDesc("server端不处理");
            break;
        default:
            break;
        }

        if (JfqRpc.ToS.HeartBeat.equals(msg.getSt())) {
            // 心跳包的话检测是否有月卡缴费
            checkIfPendingCache(session);
            pushYhsPayMsg();
        } else {
            msgBuilder.setResponse(respBuilder);
            session.write(msgBuilder.build());
        }

    }

    /**
     * @author wangzhe
     * @date 2016年5月27日
     * @description 检查是否有待发送的包
     *
     * @param session
     */
    private void checkIfPendingCache(IoSession session) {
        List<CarNumPayLogDetail> paylogs = accessService.getPendingCarNumPayLog((BigInteger) session.getAttribute(KEY_GB_ID));
        if (null != paylogs && 0 < paylogs.size()) {
            JfqRpc.JfqMsg.Builder msgBuilder = JfqRpc.JfqMsg.newBuilder();
            msgBuilder.setId(new Date().getTime());
            msgBuilder.setMt(JfqRpc.ToM.Request);
            msgBuilder.setSt(JfqRpc.ToS.MonCard);

            JfqRpc.Req.Builder reqBuilder = JfqRpc.Req.newBuilder();
            reqBuilder.setDate(DateUtils.getCurrentDate());

            JfqRpc.ReqMonCard.Builder monBuilder = JfqRpc.ReqMonCard.newBuilder();

            for (CarNumPayLogDetail cnpd : paylogs) {
                JfqRpc.MonCardItem.Builder mci = JfqRpc.MonCardItem.newBuilder();

                mci.setPlate(cnpd.getPlate());
                mci.setFee(cnpd.getFee() / 100f);
                mci.setValidDate(cnpd.getValidDate());
                mci.setPayDate(cnpd.getPayTime());

                monBuilder.addMc(mci);
            }

            reqBuilder.setPayload(monBuilder.build().toByteString());

            msgBuilder.setRequest(reqBuilder);

            session.write(msgBuilder.build());

            session.setAttribute(KEY_PENDING_MOONCARD_PUSH, paylogs);

        } else {
        }
    }

    private void processResponse(final IoSession session, final JfqMsg msg) throws InvalidProtocolBufferException {
        Object result = null;
        final JfqRpc.Resp resp = msg.getResponse();
        if (null != resp) {
            switch (msg.getSt().getNumber()) {
            case JfqRpc.ToS.Login_VALUE:
                // server端不处理
                result = resp;
                break;
            case JfqRpc.ToS.HeartBeat_VALUE:
                // server端不处理
                result = resp;
                break;
            case JfqRpc.ToS.VehIn_VALUE:
                // server端不处理
                result = resp;
                break;
            case JfqRpc.ToS.VehOut_VALUE:
                // server端不处理
                result = resp;
                break;
            case JfqRpc.ToS.UpdateOwner_VALUE:
                if (resp.getResult()) {
                    // 车主信息同步成功
                    // done
                } else {
                }
                result = resp;
                break;
            case JfqRpc.ToS.MonCard_VALUE:
                if (resp.getResult()) {
                    // 月卡同步成功
                    Object obj = session.getAttribute(KEY_PENDING_MOONCARD_PUSH);
                    if (null != obj) {
                        if (obj instanceof List<?>) {
                            List<CarNumPayLogDetail> paylist = (List<CarNumPayLogDetail>) obj;
                            for (CarNumPayLogDetail cnpd : paylist) {
                                cnpd.setPushStatus(AccessConstant.push_status_suc);
                            }
                            carNumPayLogBaseService.updateCarNumPayLogBatch(paylist);
                        } else {
                        }
                    } else {
                    }
                    // done
                } else {
                }
                result = resp;
                break;
            case JfqRpc.ToS.BaseInfo_VALUE:
                if (resp.getResult()) {
                    respBaseInfo((CarGroupBuilding) session.getAttribute(KEY_CAR_GROUP_BUILDING),
                            JfqRpc.RespBaseInfo.parseFrom(resp.getPayload()));
                } else {
                }
                break;
            case JfqRpc.ToS.SyncInfo_VALUE:
                if (resp.getResult()) {
                    respSyncInfo((BigInteger) session.getAttribute(KEY_GB_ID), JfqRpc.RespSyncInfo.parseFrom(resp.getPayload()));
                } else {
                }
                break;
            case JfqRpc.ToS.QryFee_VALUE:
                if (resp.getResult()) {
                    respQryFee((qryFeeListener) session.getAttribute(msg.getId()), JfqRpc.RespQryFee.parseFrom(resp.getPayload()));
                } else {
                    ((qryFeeListener) session.getAttribute(msg.getId())).onFail(resp.getDesc());
                }
                break;
            case JfqRpc.ToS.PayFee_VALUE:
                if (resp.getResult()) {
                    // 发送缴费成功
                } else {
                }
                result = resp;
                break;
            case JfqRpc.ToS.Lock_VALUE:
                if (resp.getResult()) {
                    // 发送锁车/解锁成功
                } else {
                }
                result = resp;
                break;
            default:
                break;
            }
        } else {
            // TODO 返回为空的处理
        }
    }

    /**
     * @author wangzhe
     * @date 2016年4月14日
     * @description 发送已缴费通知
     *
     * @param gbid 小区ID
     * @param plate 车牌
     * @param fee 已缴费用
     * @param payDate 缴费时间
     * @return 是否发送成功
     */
    public boolean reqPayFee(BigInteger gbid, String plate, float fee, String payDate) {
        Map<Long, IoSession> sMap = mTcpServer.getManagedSessions();
        boolean sendStatus = false;
        for (Map.Entry<Long, IoSession> sEntry : sMap.entrySet()) {
            IoSession session = sEntry.getValue();
            if (gbid.equals(session.getAttribute(KEY_GB_ID))) {
                JfqRpc.JfqMsg.Builder builder = JfqRpc.JfqMsg.newBuilder();
                builder.setId(new Date().getTime());
                builder.setMt(JfqRpc.ToM.Request);
                builder.setSt(JfqRpc.ToS.PayFee);

                JfqRpc.Req.Builder reqBuilder = JfqRpc.Req.newBuilder();
                reqBuilder.setDate(DateUtils.getCurrentDate());

                JfqRpc.ReqPayFee.Builder payfeebuilder = JfqRpc.ReqPayFee.newBuilder();
                payfeebuilder.setPlate(plate);
                payfeebuilder.setFee(fee);
                payfeebuilder.setPayDate(payDate);

                reqBuilder.setPayload(payfeebuilder.build().toByteString());
                builder.setRequest(reqBuilder);
                WriteFuture writeFuture = session.write(builder.build());
                
                {
                	final CarYhsMsgSendParam carYhsMsgSendParam = new CarYhsMsgSendParam();
                	carYhsMsgSendParam.setGbId(gbid);
                	carYhsMsgSendParam.setCarNum(plate);
                	carYhsMsgSendParam.setPayTime(payDate);
                	// 监听发送状态
                	writeFuture.addListener(new IoFutureListener<IoFuture>() {

    					@Override
    					public void operationComplete(IoFuture future) {
    						if(future.isDone()){
    							carYhsMsgService.updateSendStatus(carYhsMsgSendParam);
    						}
    					}
    				});
                }
                sendStatus = true;
            }
        }
        return sendStatus;
    }
    
    /**
     * 推送临停车缴费消息未发送成功（30秒内状态未变为1的，则当作未成功）的记录
     */
    private final void pushYhsPayMsg(){
    	// 查询当前节点TCP的所有连接小区id
    	List<Object> gbIds = new ArrayList<Object>();
    	Map<Long, IoSession> sMap = mTcpServer.getManagedSessions();
    	for (Map.Entry<Long, IoSession> sEntry : sMap.entrySet()) {
    		Object gbId = sEntry.getValue().getAttribute(KEY_GB_ID);
    		gbIds.add(gbId);
    	}
    	if(gbIds.size()>0){
    		List<CarYhsMsg> carYhsMsgs = carYhsMsgService.selectCarYhsMsgForSending(gbIds);
        	for(CarYhsMsg carYhsMsg:carYhsMsgs){
        		reqPayFee(carYhsMsg.gettGroupBuildingFId(), carYhsMsg.gettCarNum(), carYhsMsg.getFee(), carYhsMsg.getPayTime());
        	}
    	}
    }
    
    /**
     * @author wangzhe
     * @date 2016年4月13日
     * @description 查询临时停车费（异步）。此报文实时性强，不做缓存
     *
     * @param lnr 监听器
     * @param gbid 小区ID
     * @param plate 车牌ID
     * 
     * @return 是否发送成功
     */
    public boolean reqQryFee(qryFeeListener listener, BigInteger gbid, String plate) {
        Map<Long, IoSession> sMap = mTcpServer.getManagedSessions();
        boolean ret = false;
        for (Map.Entry<Long, IoSession> sEntry : sMap.entrySet()) {
            IoSession session = sEntry.getValue();
            if (gbid.equals(session.getAttribute(KEY_GB_ID))) {
                JfqRpc.JfqMsg.Builder builder = JfqRpc.JfqMsg.newBuilder();
                builder.setId(new Date().getTime());
                builder.setMt(JfqRpc.ToM.Request);
                builder.setSt(JfqRpc.ToS.QryFee);

                JfqRpc.Req.Builder reqBuilder = JfqRpc.Req.newBuilder();
                reqBuilder.setDate(DateUtils.getCurrentDate());

                JfqRpc.ReqQryFee.Builder qryfeebuilder = JfqRpc.ReqQryFee.newBuilder();
                qryfeebuilder.setPlate(plate);

                reqBuilder.setPayload(qryfeebuilder.build().toByteString());

                builder.setRequest(reqBuilder);

                session.setAttribute(builder.getId(), listener);

                session.write(builder.build());
                ret = true;
            } else {
            }
        }
        return ret;
    }

    /**
     * @author wangzhe
     * @param carGroupBuilding
     * @date 2016年4月13日
     * @description 收到车牌变更、新增
     *
     * @param respBuilder
     * @param updateowner
     */
    private void reqUpdateOwner(BigInteger gbId, Builder respBuilder, ReqUpdateOwner updateowner) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        for (JfqRpc.Owner owner : updateowner.getOwnerList()) {
            for (JfqRpc.Vehicle veh : owner.getVehList()) {
            	paramMap.clear();
            	paramMap.put("carNum", veh.getPlate());
            	paramMap.put("gbId", gbId);
                CarNumList carnum = accessService.getCardetail(paramMap);
                if (null == carnum) {
                    carnum = new CarNumList();
                    carnum.settCarNum(veh.getPlate());
                    carnum.setLockStatus(0);
                } else {
                }

                carnum.setExpireDate(veh.getValidDate());
                carnum.setStatus(1);
                carnum.setFee((long) (veh.getFee() * 100));
                carnum.settGroupBuildingFId(gbId);

                if (null == carnum.getId()) {
                    carnum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
                    carnum.setSys0AddTime(DateUtils.getCurrentDate());
                    carNumListBaseService.insertCarNumList(carnum);
                } else {
                    carnum.setSys0UpdTime(DateUtils.getCurrentDate());
                    carNumListBaseService.updateCarNumList(carnum);
                }
            }
        }
        respBuilder.setResult(true);
    }

    /**
     * @author wangzhe
     * @param carGroupBuilding
     * @date 2016年4月13日
     * @description 当前车场信息
     *
     * @param baseinfo
     */
    private void respBaseInfo(CarGroupBuilding carGroupBuilding, JfqRpc.RespBaseInfo baseinfo) {
        carGroupBuilding.setParkingTotal((long) baseinfo.getSpotSum());
        carGroupBuilding.setParkingCrnt((long) baseinfo.getEptySum());
        // TODO 在场车处理
        carGroupBuildingBaseService.updateCarGroupBuilding(carGroupBuilding);
    }

    /**
     * @author wangzhe
     * @param listener
     * @date 2016年4月13日
     * @description 查询临时停车费
     *
     * @param qryfee
     */
    private void respQryFee(qryFeeListener listener, JfqRpc.RespQryFee qryfee) {
        if (null != listener) {
            listener.onReceived(qryfee.getPlate(), qryfee.getFee(), DateUtils.strToDateTime(qryfee.getInDate()));
        } else {
        }
    }

    /**
     * @author wangzhe
     * @param gbid
     * @date 2016年4月13日
     * @description 同步信息
     *
     * @param syncinfo
     */
    private void respSyncInfo(BigInteger gbid, RespSyncInfo syncinfo) {
        JfqRpc.Resp.Builder dummy = JfqRpc.Resp.newBuilder();
        reqUpdateOwner(gbid, dummy, syncinfo.getRuo());
        vehIn(gbid, dummy, syncinfo.getIn());
        vehOut(gbid, dummy, syncinfo.getOut());
    }

    @Override
    public void sessionClosed(final IoSession session) throws Exception {
        // mLogger.info("sessionClosed..." + session);
        Object gb_id = session.getAttribute(KEY_GB_ID);
        if (null != gb_id && !TextUtils.isEmpty(gb_id.toString())) {
            RedisCacheHandler.sadd(RedisCachePrefix.car_offline_list,gb_id.toString());
        } else {
        }
    }

    @Override
    public void sessionCreated(final IoSession session) throws Exception {
        // mLogger.info("sessionCreated..." + session);
    }

    @Override
    public void sessionIdle(final IoSession session, final IdleStatus status) throws Exception {
        // mLogger.info("sessionIdle..." + session + ",status:" + status);
        if (IdleStatus.READER_IDLE.equals(status)) {
            session.closeOnFlush();
        } else {
        }
    }

    @Override
    public void sessionOpened(final IoSession session) throws Exception {
        // mLogger.info("sessionOpened..." + session);
    }

    /**
     * @author wangzhe
     * @param gb TODO
     * @param respBuilder
     * @param rvi
     * @date 2016年4月12日
     * @description 处理入场信息
     */
    private void vehIn(BigInteger gbId, JfqRpc.Resp.Builder respBuilder, JfqRpc.ReqVehIn rvi) {
        List<JfqRpc.VehInItem> viis = rvi.getViList();
        for (JfqRpc.VehInItem vii : viis) {
        	boolean isSuccess = accessService.vehIn(vii.getPlate(), gbId, vii.getInDate(), AccessDict.JFQCarType.UN_MONTH_CAR, null);
            if (isSuccess) {
                // 成功
                respBuilder.setResult(true);
            } else {
                // 数据库保存失败
                respBuilder.setResult(false);
                respBuilder.setDesc("保存失败");
            }
        }
    }

    /**
     * @author wangzhe
     * @param carGroupBuilding
     * @date 2016年4月13日
     * @description 处理出场信息
     *
     * @param respBuilder
     * @param rvi
     */
    private void vehOut(BigInteger gbId, JfqRpc.Resp.Builder respBuilder, JfqRpc.ReqVehOut rvo) {
        List<JfqRpc.VehOutItem> vois = rvo.getVoList();
        for (JfqRpc.VehOutItem voi : vois) {
        	long fee = (long) ((voi.getCoupon() + voi.getCash()) * 100);
        	boolean result = accessService.vehOut(voi.getPlate(), gbId, voi.getInDate(), voi.getOutDate(), fee, AccessDict.JFQCarType.UN_MONTH_CAR);
            respBuilder.setResult(result);
            respBuilder.setDesc(result ? "成功" : "失败");
        }

    }
}
