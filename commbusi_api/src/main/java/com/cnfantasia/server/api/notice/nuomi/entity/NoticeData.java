package com.cnfantasia.server.api.notice.nuomi.entity;

import java.util.List;

public class NoticeData {

	private List<NoticeMonth> noticeMonths;
	private boolean hasNext;

	public List<NoticeMonth> getNoticeMonths() {
		return noticeMonths;
	}

	public void setNoticeMonths(List<NoticeMonth> noticeMonths) {
		this.noticeMonths = noticeMonths;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}


}
