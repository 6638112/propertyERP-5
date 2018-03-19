package com.cnfantasia.server.api.userQuestion.entity;

import java.math.BigInteger;

/**
 * 用户提问，供OOS管理员查看使用
 * @author wenfq 2017-02-27
 *
 */
public class UserQuestion4Admin {
	private BigInteger uqId; //提问ID
	private String gbId;
	private String gbName;
	private String buildingName;//楼栋名称
	private String location;//省-市-区
	private String roomNum;//房号
	private BigInteger huaId;//解放号
	private String mobile;
	private String content;
	private String answerContent;
	private String updateUser;
	private String updateUserName;
	private String updateTime;
	private int status;
	private String addTime;
	private BigInteger serviceId;
	
	public String getGbId() {
		return gbId;
	}
	public void setGbId(String gbId) {
		this.gbId = gbId;
	}
	public BigInteger getHuaId() {
		return huaId;
	}
	public void setHuaId(BigInteger huaId) {
		this.huaId = huaId;
	}
	public BigInteger getUqId() {
		return uqId;
	}
	public void setUqId(BigInteger uqId) {
		this.uqId = uqId;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public BigInteger getServiceId() {
		return serviceId;
	}

	public void setServiceId(BigInteger serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}
	
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public String getUpdateUser() {
		return updateUser;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
}
