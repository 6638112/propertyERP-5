package com.cnfantasia.server.notification.task;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.jfq.wechat.util.TemplateMsgSender;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.dao.DredgeDao;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForDetail;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.dredgeBill.dao.DredgeBillBaseDao;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.loginNo.dao.LoginNoBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.wechatDredgebillMq.dao.WechatDredgebillMqBaseDao;
import com.cnfantasia.server.domainbase.wechatDredgebillMq.entity.WechatDredgebillMq;
import com.cnfantasia.server.notification.mq.WeChatMQ;

public class WeChatMsgTask extends NotificationTask {
	
	@Resource
	WeChatMQ wechatMQ;
	
	@Resource 
	DredgeDao dredgeDao;
	
	@Resource 
	DredgeBillBaseDao dredgeBillBaseDao;
	
	@Resource
	WechatDredgebillMqBaseDao wechatDredgebillMqBaseDao;
	
	@Resource
	LoginNoBaseDao loginNoBaseDao;
	
	@Override
	public boolean sendNotification() throws ClientProtocolException, IOException {
		while (wechatMQ.fetchAllMessage().size() > 0 ) {//只要队列中有数据，就推送，当队列中无数据是就终止本次任务调度
			WechatDredgebillMq wechatDredgebillMq = wechatMQ.pollMessage();
			
			DredgeBillForDetail dredgeBillForDetail = dredgeDao.qryDredgeBillDetail(wechatDredgebillMq.gettDredgeBillFId()+"");
			DredgeBill dredgeBill = dredgeBillBaseDao.selectDredgeBillBySeqId(wechatDredgebillMq.gettDredgeBillFId());
			
			LoginNo loginNo = new LoginNo();
			loginNo.setType(LoginDict.AccountType.WEI_XIN_LIGHT_APP);//微信轻应用 
			loginNo.settUserFId(dredgeBill.gettUserFId());
			loginNo.setSys0DelState(0);
			List<LoginNo> loginNoList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNo), false);
			
			sendWeChatMsg(loginNoList, wechatDredgebillMq, dredgeBillForDetail);
			if(loginNoList.size()==0){
				wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_SendFail);
			}
			wechatDredgebillMqBaseDao.updateWechatDredgebillMq(wechatDredgebillMq);
		}
		
		return true;
	}
	
	/**
	 * 推送微信公众号消息
	 * @param loginNoList
	 * @param wechatDredgebillMq
	 * @param dredgeBillForDetail
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private boolean sendWeChatMsg(List<LoginNo> loginNoList, WechatDredgebillMq wechatDredgebillMq, 
			DredgeBillForDetail dredgeBillForDetail) throws ClientProtocolException, IOException{
		LoginNo loginNo = null;
		if (loginNoList.size() == 0) {
			return false;
		} else if (loginNoList.size() > 1) {
			return false;
		} else if (loginNoList.size() == 1) {
			loginNo = loginNoList.get(0);
		}
		
		String requestURL = new StringBuilder().append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&")
		.append("redirect_uri=REDIRECT_URI")
		.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect").toString();
		
		String redirect_uri_src = String.format("%sdredge/qryDredgeBillDetail.do?id=%s", CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL), wechatDredgebillMq.gettDredgeBillFId());
		
		String forwardURL = requestURL.replace("APPID", WeiXinPayConstantUtil.getAPP_ID(WeiXinPay_GoalAccType.JieFangQu_LightApp)).replace("REDIRECT_URI", redirect_uri_src);
		//String forwardURL = requestURL.replace("APPID", "wx83e844faad90be3e").replace("REDIRECT_URI", "%sdredge/qryDredgeBillDetail.do?id=%s"); //测试公众号
		
		String postDataTemplate = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":" + "\"" + forwardURL + "\"" +",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"%s\",\"color\":\"#173177\"},\"remark\":{\"value\":\"%s\",\"color\":\"#173177\"}}}";
		if(wechatDredgebillMq.getType()==DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Type_AutoCanceled){
			String postData = String.format(postDataTemplate,loginNo.getNo(),CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_DREDGE_BILL_STATUE_CHANGE_TEMP_ID),
					 "您的订单逾期没有师傅接单，被自动取消", 
					dredgeBillForDetail.getType(), dredgeBillForDetail.getBillNo(), dredgeBillForDetail.getStatusDesc(), dredgeBillForDetail.getExpectDate(), "欢迎再次预约！");
			postData = postData.replace(redirect_uri_src, URLEncoder.encode(redirect_uri_src, "UTF-8"));
			String sendResultResponse = TemplateMsgSender.sendMessage(postData);
			wechatDredgebillMq.setSendRespone(sendResultResponse);
			String errmsg = JSONObject.parseObject(sendResultResponse).getString("errmsg");
			if("ok".equals(errmsg)){
				wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_SendSuccess);
				return true;
			}else{
				wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_SendFail);
				return false;
			}
		}else if(wechatDredgebillMq.getType()==DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Type_MasterConfirm){
			String postData = String.format(postDataTemplate, loginNo.getNo(),CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_DREDGE_BILL_STATUE_CHANGE_TEMP_ID),
					 "您的订单已被师傅接单，请注意接听师傅来电", 
					dredgeBillForDetail.getType(), dredgeBillForDetail.getBillNo(), dredgeBillForDetail.getStatusDesc(), dredgeBillForDetail.getExpectDate(), "欢迎再次预约！");
			postData = postData.replace(redirect_uri_src, URLEncoder.encode(redirect_uri_src, "UTF-8"));
			String sendResultResponse = TemplateMsgSender.sendMessage(postData);
			wechatDredgebillMq.setSendRespone(sendResultResponse);
			String errmsg = JSONObject.parseObject(sendResultResponse).getString("errmsg");
			if("ok".equals(errmsg)){
				wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_SendSuccess);
				return true;
			}else{
				wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_SendFail);
				return false;
			}
		}else if(wechatDredgebillMq.getType()==DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Type_SetPayAmount){
			String postData = String.format(postDataTemplate, loginNo.getNo(), CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_DREDGE_BILL_STATUE_CHANGE_TEMP_ID),
					 "您的订单已被师傅设置收款金额，请支付订单", 
					dredgeBillForDetail.getType(), dredgeBillForDetail.getBillNo(), "师傅已设置订单金额", dredgeBillForDetail.getExpectDate(), "欢迎再次预约！");
			postData = postData.replace(redirect_uri_src, URLEncoder.encode(redirect_uri_src, "UTF-8"));
			String sendResultResponse = TemplateMsgSender.sendMessage(postData);
			wechatDredgebillMq.setSendRespone(sendResultResponse);
			String errmsg = JSONObject.parseObject(sendResultResponse).getString("errmsg");
			if("ok".equals(errmsg)){
				wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_SendSuccess);
				return true;
			}else{
				wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_SendFail);
				return false;
			}
		}
		
		return false;
	}
	

}
