package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class FinanceReqEntity implements Serializable {
	
	private static final long serialVersionUID = 296346640226719319L;
	
	private Long id; //主键ID

	private String liberateNum; //解放区花号
	
	private String realName; //真实姓名
	
	private String mobile; //手机号码
	
	private String province; //省
	
	private String city; //城市
	
	private String prefecture; //小区所在县乡
	
	private String residential; //小区名称
	
	private String building; //楼栋
	
	private String roomNum; //房间号
	
	private Long roomId;
	
	private BigDecimal propertyFees; //物业费  -parkingFee 停车费
	
	private String licensePlate; //车牌号
	
	private BigDecimal parkingFee;// 停车费
	
	private String sourceClick; //f_source_click;

	public BigDecimal getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(BigDecimal parkingFee) {
		this.parkingFee = parkingFee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getResidential() {
		return residential;
	}

	public void setResidential(String residential) {
		this.residential = residential;
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

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public BigDecimal getPropertyFees() {
		return propertyFees;
	}

	public void setPropertyFees(BigDecimal propertyFees) {
		this.propertyFees = propertyFees;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getSourceClick() {
		return sourceClick;
	}

	public void setSourceClick(String sourceClick) {
		this.sourceClick = sourceClick;
	}
	
}
