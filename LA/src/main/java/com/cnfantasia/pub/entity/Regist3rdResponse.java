package com.cnfantasia.pub.entity;

public class Regist3rdResponse {
	boolean isFirstRegist;
	String realName;
	String userId;
	int loginType;
	String imgProfile;
	String defaultRoomId;
	DefaultRoomInfo defaultRoomInfo;
	double discount;
	boolean isAdmin;
	String huaId;
	String nickName;
	String mobile;
	String sessionKey;
	/** 手机绑定情况，0未绑定，1绑定成功，2绑定失败 */
	private Integer phoneBindState;	

	public Integer getPhoneBindState() {
		return phoneBindState;
	}

	public void setPhoneBindState(Integer phoneBindState) {
		this.phoneBindState = phoneBindState;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isFirstRegist() {
		return isFirstRegist;
	}

	public void setFirstRegist(boolean isFirstRegist) {
		this.isFirstRegist = isFirstRegist;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public String getImgProfile() {
		return imgProfile;
	}

	public void setImgProfile(String imgProfile) {
		this.imgProfile = imgProfile;
	}

	public String getDefaultRoomId() {
		return defaultRoomId;
	}

	public void setDefaultRoomId(String defaultRoomId) {
		this.defaultRoomId = defaultRoomId;
	}

	public DefaultRoomInfo getDefaultRoomInfo() {
		return defaultRoomInfo;
	}

	public void setDefaultRoomInfo(DefaultRoomInfo defaultRoomInfo) {
		this.defaultRoomInfo = defaultRoomInfo;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getHuaId() {
		return huaId;
	}

	public void setHuaId(String huaId) {
		this.huaId = huaId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
