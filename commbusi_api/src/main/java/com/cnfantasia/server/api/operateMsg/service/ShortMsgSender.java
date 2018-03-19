package com.cnfantasia.server.api.operateMsg.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

/**
 * 运营消息短信发送器 
 * @author wenfq  2017年6月8日
 *
 */
public class ShortMsgSender implements IOperateMsgSender {
	
	@Override
	public List<String> prepare(MessageToBeSend msg) {
		OperateMsgService operateMsgService = (OperateMsgService) CnfantasiaCommbusiUtil.getBeanManager("operateMsgService");
		List<String> userMobileList = operateMsgService.qryUserMobileByMsgToBeSendId(msg.getId());
		if(StringUtils.isNotBlank(msg.getWhiteList())){
			List<String> whiteList = Arrays.asList(msg.getWhiteList().split(";"));
			userMobileList.addAll(whiteList);
		}
		
		if(StringUtils.isNotBlank(msg.getBlackList())){
			List<String> blackList = Arrays.asList(msg.getBlackList().split(";"));
			userMobileList.removeAll(blackList);
		}
		
		return userMobileList;
	}

	@Override
	public int sendMessage(List<?> sendTarget, MessageToBeSend msg) {
		ShortMsgUtil.sendMessages((List<String>) sendTarget, msg.getContent());
		
		return sendTarget.size();
	}

}
