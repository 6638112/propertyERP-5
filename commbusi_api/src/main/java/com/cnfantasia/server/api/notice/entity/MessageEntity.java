package com.cnfantasia.server.api.notice.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messageView.entity.MessageView;
/**
 * 描述:(消息表) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class MessageEntity extends Message{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 消息使用的视图
	 */
	private MessageView messageView;
	public MessageView getMessageView() {
		return messageView;
	}
	public void setMessageView(MessageView messageView) {
		this.messageView = messageView;
	}
	
	/**
	 * 消息使用的参数
	 */
	private List<MessageParameter> messageParameterList;
	public List<MessageParameter> getMessageParameterList() {
		return messageParameterList;
	}
	public void setMessageParameterList(List<MessageParameter> messageParameterList) {
		this.messageParameterList = messageParameterList;
	}
	
}
