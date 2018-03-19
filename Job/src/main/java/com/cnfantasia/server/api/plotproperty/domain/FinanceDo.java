package com.cnfantasia.server.api.plotproperty.domain;

import java.io.Serializable;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class FinanceDo implements Serializable {
	
	private static final long serialVersionUID = 296346640226719319L;

	private String liberateNum; //解放区花号
	
	private String realName; //真实姓名
	
	private String mobile; //手机号码
	
	private String province; //省
	
	private String city; //城市
	
	private String prefecture; //小区所在县乡
	
	private String building; //楼栋
	
	private String roomNum; //房间号
	
	private String roomId;
	
	private Float propertyFees; //物业费

	public String getLiberateNum() {
		return liberateNum;
	}

	public void setLiberateNum(String liberateNum) {
		this.liberateNum = liberateNum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Float getPropertyFees() {
		return propertyFees;
	}

	public void setPropertyFees(Float propertyFees) {
		this.propertyFees = propertyFees;
	}
}
