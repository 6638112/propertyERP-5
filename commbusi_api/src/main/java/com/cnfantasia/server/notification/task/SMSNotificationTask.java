package com.cnfantasia.server.notification.task;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.domainbase.smsMq.dao.SmsMqBaseDao;
import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;
import com.cnfantasia.server.notification.mq.SMSMQ;

/**
 * 短信通知服务
 * 
 * @author wenfq 2016-02-27
 *
 */
public class SMSNotificationTask extends NotificationTask {
	@Resource
	SMSMQ smsMQ;
	
	@Resource
	SmsMqBaseDao smsMqBaseDao;

	@Resource
	ICommMobileService commMobileService;

	@Override
	public boolean sendNotification() throws ClientProtocolException,
			IOException {
		int updCount = 0;
		
		while (smsMQ.fetchAllMessage().size() > 0) {//只要队列中有数据，就推送，当队列中无数据是就终止本次任务调度
			SmsMq smsMq = smsMQ.pollMessage();
//			commMobileService.sendMsg(smsMq.getMobile(), smsMq.getContent());
			ShortMsgUtil.sendMessages(smsMq.getMobile(), smsMq.getContent());
			smsMq.setSendStatus(1);
			updCount += smsMqBaseDao.updateSmsMq(smsMq);
		}

		return updCount > 0;
	}

}
