package com.cnfantasia.server.ms.revenue.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.operateMsg.service.OperateMsgService;
import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

public class OperateMsgTask implements ISynTask{
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	OperateMsgService operateMsgService;

	@Override
	public Integer execTask() {
		List<MessageToBeSend> messageToBeSendList = operateMsgService.qryMessageToBeSendList4Task();
		logger.info("will to be send msg list: "  + messageToBeSendList);
		int sendCount = 0; 
		for(int i = 0; i < messageToBeSendList.size(); i++){
			logger.info("sending msg is: "  + messageToBeSendList.get(i));
			sendCount += operateMsgService.sendMsg(messageToBeSendList.get(i));
		}
		
		return sendCount;
	}
}
