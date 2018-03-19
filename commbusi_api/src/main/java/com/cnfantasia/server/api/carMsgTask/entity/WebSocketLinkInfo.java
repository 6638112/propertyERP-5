package com.cnfantasia.server.api.carMsgTask.entity;

import java.math.BigInteger;

public class WebSocketLinkInfo {

	/** 小区id */
	private BigInteger gbId;
	/** 停车场编号 */
	private int parkId;

	public WebSocketLinkInfo() {
		super();
	}

	public WebSocketLinkInfo(BigInteger gbId, int parkId) {
		super();
		this.gbId = gbId;
		this.parkId = parkId;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public int getParkId() {
		return parkId;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

}
