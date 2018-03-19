package com.cnfantasia.server.notification.mq;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.smsMq.dao.SmsMqBaseDao;
import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;
import com.cnfantasia.server.notification.dao.NotificationDao;
import com.cnfantasia.server.notification.mq.constant.SMSMQConstant;

public class SMSMQ implements IMessageQueue<SmsMq> {
	
	@Resource
	SmsMqBaseDao smsMqBaseDao;
	
	@Resource
	NotificationDao notificationDao;
	
	List<SmsMq> smsMqList = new ArrayList<SmsMq>();
	
	@Override
	public void addMessage(SmsMq entity) {
		notificationDao.insertSmsMq(entity);
	}

	@Override
	public SmsMq pollMessage() {
		if(smsMqList.isEmpty())
			fetchAllMessage();
		
		//如果fetchAllMessage()后，还是空队列，直接返回null
		if(smsMqList.isEmpty())
			return null;
		
		SmsMq smsMq = smsMqList.get(smsMqList.size()-1);
		smsMqList.remove(smsMqList.size()-1);
		return smsMq;
	}

	@Override
	public List<SmsMq> fetchAllMessage() {
		if (smsMqList.size() > 0)
			return smsMqList;

		SmsMq smsMq = new SmsMq();
		smsMq.setSendStatus(SMSMQConstant.SMSMQ_Status_NotSend);
		smsMq.setSys0DelState(0);
		smsMqList =  smsMqBaseDao.selectSmsMqByCondition(MapConverter.convertBean(smsMq), false);
		return smsMqList;
	}


}
