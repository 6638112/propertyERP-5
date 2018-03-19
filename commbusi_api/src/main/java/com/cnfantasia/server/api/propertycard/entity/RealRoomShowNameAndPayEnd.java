package com.cnfantasia.server.api.propertycard.entity;

import java.util.Date;

public class RealRoomShowNameAndPayEnd {
	String realRoomShowName;
	String payEnd;
	
	public String getRealRoomShowName() {
		return realRoomShowName;
	}
	public void setRealRoomShowName(String realRoomShowName) {
		this.realRoomShowName = realRoomShowName;
	}
	public String getPayEnd() {
		return this.payEnd;
	}
	
	public String getPayEnd_MMdd() {
		return (new Date().getMonth()+1) +"月" + payEnd + "日";
	}
	
	public void setPayEnd(String payEnd) {
		this.payEnd = payEnd;
	}
}
