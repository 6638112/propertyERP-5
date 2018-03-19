package com.cnfantasia.server.notification.mq;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.wechatDredgebillMq.dao.WechatDredgebillMqBaseDao;
import com.cnfantasia.server.domainbase.wechatDredgebillMq.entity.WechatDredgebillMq;

public class WeChatMQ implements IMessageQueue<WechatDredgebillMq> {
	
	@Resource
	WechatDredgebillMqBaseDao wechatDredgebillMqBaseDao;

	List<WechatDredgebillMq> WechatDredgebillMqList = new ArrayList<WechatDredgebillMq>();

	@Override
	public void addMessage(WechatDredgebillMq entity) {
		wechatDredgebillMqBaseDao.insertWechatDredgebillMq(entity);
	}

	@Override
	public WechatDredgebillMq pollMessage() {
		if(WechatDredgebillMqList.isEmpty())
			fetchAllMessage();
		
		//如果fetchAllMessage()后，还是空队列，直接返回null
		if(WechatDredgebillMqList.isEmpty())
			return null;
		
		WechatDredgebillMq wechatDredgebillMq = WechatDredgebillMqList.get(WechatDredgebillMqList.size()-1);
		WechatDredgebillMqList.remove(WechatDredgebillMqList.size()-1);
		return wechatDredgebillMq;
	}

	@Override
	public List<WechatDredgebillMq> fetchAllMessage() {
		if (WechatDredgebillMqList.size() > 0)
			return WechatDredgebillMqList;
		
		WechatDredgebillMq wechatDredgebillMq = new WechatDredgebillMq();
		wechatDredgebillMq.setSendStatus(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Status_NotSend);
		wechatDredgebillMq.setSys0DelState(0);
		//wechatDredgebillMq.setType(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Type_AutoCanceled);
		WechatDredgebillMqList = wechatDredgebillMqBaseDao.selectWechatDredgebillMqByCondition(MapConverter.convertBean(wechatDredgebillMq), false);
		
		return WechatDredgebillMqList;
	}

}
