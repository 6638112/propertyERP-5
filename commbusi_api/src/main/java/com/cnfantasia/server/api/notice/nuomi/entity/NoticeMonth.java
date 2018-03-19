package com.cnfantasia.server.api.notice.nuomi.entity;

import java.util.List;

public class NoticeMonth {
	
	private String title;
	private List<NoticeItem> noticeItems;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<NoticeItem> getNoticeItems() {
		return noticeItems;
	}

	public void setNoticeItems(List<NoticeItem> noticeItems) {
		this.noticeItems = noticeItems;
	}

}
