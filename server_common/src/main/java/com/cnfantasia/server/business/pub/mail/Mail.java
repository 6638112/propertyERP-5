package com.cnfantasia.server.business.pub.mail;

import java.util.List;

public class Mail {
	/**
	 * 发送人
	 */
	private String sender;
	/**
	 * 收件人,多人用逗号分隔
	 */
	private String recipientsTo;
	/**
	 * 抄送人
	 */
	private String recipientsCc;
	/**
	 * 密送人
	 */
	private String recipientsBcc;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 正文
	 */
	private String body;
	/**
	 * 附件列表
	 */
	private List<String> attachments;
	
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipientsTo() {
		return recipientsTo;
	}
	public void setRecipientsTo(String recipientsTo) {
		this.recipientsTo = recipientsTo;
	}
	public String getRecipientsCc() {
		return recipientsCc;
	}
	public void setRecipientsCc(String recipientsCc) {
		this.recipientsCc = recipientsCc;
	}
	public String getRecipientsBcc() {
		return recipientsBcc;
	}
	public void setRecipientsBcc(String recipientsBcc) {
		this.recipientsBcc = recipientsBcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	
}

