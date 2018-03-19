/**   
* Filename:    CommMobileService.java   
* @version:    1.0  
* Create at:   2014年12月19日 上午2:00:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.common.entity.CommMsg;
import com.cnfantasia.server.api.common.service.CommMsgService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.commonBusiness.util.SendSmsRunnable;

/**
 * Filename:    CommMobileService.java
 * @version:    1.0.0
 * Create at:   2014年12月19日 上午2:00:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月19日       shiyl             1.0             1.0 Version
 */
public class CommMobileService implements ICommMobileService{
	private Log logger = LogFactory.getLog(getClass());
	
	private CommMsgService commMsgService;
	
	@Override
	public boolean sendMsg(List<String> mobiles, String msg) {
		msg = appendSMSSignature(msg);
		ShortMsgUtil.sendMessages(mobiles, msg);
		
//		String isMessageSend = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_MESSAGE_SEND);
//		if(isMessageSend == null || !isMessageSend.equals("0")) {
//			try {
//				FutureTask<String> task = null;
//				if(isMessageSend == null || (!"2".equals(isMessageSend) && !"3".equals(isMessageSend))) {//加多一个微网通联通道
//					task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg, "1"));
//				} else if("2".equals(isMessageSend)) {
//					//云之讯自动加了签名
//					msg = msg.replace("【解放区】", "");
//					task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg, "2"));
//				} else if("3".equals(isMessageSend)) {//微网通联
//					task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg, "3"));
//				} 
//				new Thread(task).start();
////				logger.info("发送短信的响应 = " + task.get());//task.get()调用会阻塞等待返回处理结果，此异步处理就变成了同步处理。故注释此行。
//			} catch (Exception e) {
//				logger.error(e);
//				return false;
//			}
//		} else if(isMessageSend.equals("0")) {
//			List<CommMsg> commMsgList = new ArrayList<CommMsg>();
//			for(String mobile : mobiles) {
//				CommMsg commMsg = new CommMsg();
//				commMsg.setMobile(mobile);
//				if(msg.length() > 150) {
//					commMsg.setMsg(msg.substring(0, 150)); //控制长度，因为数据库字段的长度为150。
//				} else {
//					commMsg.setMsg(msg); //控制长度，因为数据库字段的长度为150。
//				}
//				commMsgList.add(commMsg);
//			}
//			commMsgService.insertCommMsgBatch(commMsgList);
//		}
		
		return true;
	}
	
	/**
	 * 增加短信签名
	 * 
	 * @param msg
	 * @return
	 */
	private String appendSMSSignature(String msg) {
		if (!msg.endsWith("【解放区】")) {
			msg += "【解放区】";
		}
		return msg;
	}

	@Override
	public boolean sendMsg(String mobile, String msg) {
		msg = appendSMSSignature(msg);
		List<String> mobiles = new ArrayList<String>();
		mobiles.add(mobile);
//		return sendMsg(mobiles, msg);
		ShortMsgUtil.sendMessages(mobiles, msg);
		return true;
	}

	public void setCommMsgService(CommMsgService commMsgService) {
		this.commMsgService = commMsgService;
	}

}
