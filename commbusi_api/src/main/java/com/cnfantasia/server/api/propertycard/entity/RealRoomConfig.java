package com.cnfantasia.server.api.propertycard.entity;

import java.math.BigInteger;

/**
 * 房间划扣配置 
 * @author wenfq 2016-04-27
 *
 */
public class RealRoomConfig {

	private BigInteger configId;
	/**
	 * 房间号id
	 */
	private BigInteger realRoomId;
	/**
	 * 房间描述，eg. xx小区xx栋xx单元xxx号
	 */
	private String roomDesciption;
	/**
	 * 是否开启自动划扣
	 */
	private int isOpenService;


	public BigInteger getRealRoomId() {
		return realRoomId;
	}

	public void setRealRoomId(BigInteger realRoomId) {
		this.realRoomId = realRoomId;
	}

	public String getRoomDesciption() {
		return roomDesciption;
	}
	public void setRoomDesciption(String roomDesciption) {
		this.roomDesciption = roomDesciption;
	}
	public int getIsOpenService() {
		return isOpenService;
	}
	public void setIsOpenService(int isOpenService) {
		this.isOpenService = isOpenService;
	}

	public BigInteger getConfigId() {
		return configId;
	}

	public void setConfigId(BigInteger configId) {
		this.configId = configId;
	}
}
