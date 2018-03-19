package com.cnfantasia.server.ms.notice.dto;

public class NoticeRequest {
	private String msgId;
	/** 页眉 */
	private String pageHeader;
	/** 标题 */
	private String title;
	/** 内容 */
	private String noticeNontent;
	/** 落款 */
	private String signature;
	/** 二维码 */
	private String rqCode;
	/** 推送方式 */
	private String pushWay;
	/** 推送时间 */
	private String pushTime;
	/** 公告有效开始时间 */
	private String expiryDateStart;
	/** 公告有效截止时间 */
	private String expirDateEnd;
	private String[] gbIds;
	/** t_message_print表id */
	private String mpId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNoticeNontent() {
		return noticeNontent;
	}

	public void setNoticeNontent(String noticeNontent) {
		this.noticeNontent = noticeNontent;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRqCode() {
		return rqCode;
	}

	public void setRqCode(String rqCode) {
		this.rqCode = rqCode;
	}

	public String getPushWay() {
		return pushWay;
	}

	public void setPushWay(String pushWay) {
		this.pushWay = pushWay;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public String getExpiryDateStart() {
		return expiryDateStart;
	}

	public void setExpiryDateStart(String expiryDateStart) {
		this.expiryDateStart = expiryDateStart;
	}

	public String getExpirDateEnd() {
		return expirDateEnd;
	}

	public void setExpirDateEnd(String expirDateEnd) {
		this.expirDateEnd = expirDateEnd;
	}

	public String[] getGbIds() {
		return gbIds;
	}

	public void setGbIds(String[] gbIds) {
		this.gbIds = gbIds;
	}

	public String getMpId() {
		return mpId;
	}

	public void setMpId(String mpId) {
		this.mpId = mpId;
	}

}
