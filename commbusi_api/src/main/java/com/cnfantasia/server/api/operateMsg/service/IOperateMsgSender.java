package com.cnfantasia.server.api.operateMsg.service;

import java.util.List;

import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

/**
 * 运营消息发送器接口
 * @author wenfq 2017-06-08
 *
 */
public interface IOperateMsgSender {
	/**
	 * 发送前的数据准备
	 * @return 待发送对象：比如手机号集、或用户id集、或公众号集
	 */
	public List<? extends Object> prepare(MessageToBeSend msg);
	
	/**
	 * 发送运营消息, 如发送短信、push消息、或公众号推送
	 * @param sendTarget 发送对象：手机号或解放号或微信号
	 * @param 运营消息
	 * @return 返回成功条数
	 */
	public int sendMessage(List<? extends Object> targetList, MessageToBeSend msg);
}
