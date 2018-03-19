package com.cnfantasia.server.commbusi.microblogQueue.entity;

import java.math.BigInteger;

public class SimpleAddressInfo {
	/**省名称*/
	private String provName;
	/**城市名称*/
	private String cityName;
	/**行政区名称*/
	private String blockName;
	/**小区名称*/
	private String gbName;
	/**楼栋名称*/
	private String buildName;
	/**门牌名称*/
	private String roomNum;
	
	/**小区Id*/
	private BigInteger gbId;
	/**城市Id*/
	private BigInteger addressCityId;
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public BigInteger getAddressCityId() {
		return addressCityId;
	}
	public void setAddressCityId(BigInteger addressCityId) {
		this.addressCityId = addressCityId;
	}
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	} 
	
	public String fetchRoomNumMask() {
		return maskLast2(roomNum);
	}
	
	public static String maskLast2(String roomNum){
		if(roomNum!=null&&roomNum.length()>=2){
			return roomNum.substring(0, roomNum.length()-2)+"**";
		}
		return "**";
	}
	
//	public static void main(String[] args) {
//		System.out.println(maskLast2("aa"));
//		System.out.println(maskLast2("订西2"));
//		System.out.println(maskLast2("A"));
//	}
	
}
