package com.cnfantasia.server.api.loan.entity;

/**
 * 借贷用户信息封装对象
 * 
 * @author liyulin
 * @version 1.0 2017年7月4日 下午4:10:19
 */
public class LoanUserInfoEntity {

	/** 姓名 */
	private String name;
	/** 手机号码 */
	private String mobile;
	/** 身份证 */
	private String cardId;
	/** 城市 */
	private String cityName;
	/** 小区居住时长（单位：月数） */
	private Integer residenceTime;
	/** 房屋建筑面积（单位：平方米） */
	private double roomSize;
	/** 近6个月月均用电缴费金额 */
	private double energyFee;
	/** 近6个月月均用水缴费金额 */
	private double waterFee;
	/** 近6个月物业缴费记录 */
	private ChargeInfoEntity propertyCharge;
	/** 是否有车（1：“有”；2：“没有”；3：“未知”） */
	private int hasCar;
	/** 是否有车位（1：“有”；2：“没有”；3：“未知”） */
	private int hasParkingLot;
	/** 是否业主（1：“是”；2：“不是”；3：“未知”） */
	private int isPropertyProprietor;
	/** 近6个月非缴费的交易次数 */
	private int unPropertyChargeCount;
	/** 近6个月理财记录条数 */
	private int financeLogCount;

	public Integer getResidenceTime() {
		return residenceTime;
	}

	public void setResidenceTime(Integer residenceTime) {
		this.residenceTime = residenceTime;
	}

	public double getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(double roomSize) {
		this.roomSize = roomSize;
	}

	public int getHasCar() {
		return hasCar;
	}

	public void setHasCar(int hasCar) {
		this.hasCar = hasCar;
	}

	public int getHasParkingLot() {
		return hasParkingLot;
	}

	public void setHasParkingLot(int hasParkingLot) {
		this.hasParkingLot = hasParkingLot;
	}

	public int getIsPropertyProprietor() {
		return isPropertyProprietor;
	}

	public void setIsPropertyProprietor(int isPropertyProprietor) {
		this.isPropertyProprietor = isPropertyProprietor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getEnergyFee() {
		return energyFee;
	}

	public void setEnergyFee(double energyFee) {
		this.energyFee = energyFee;
	}

	public double getWaterFee() {
		return waterFee;
	}

	public void setWaterFee(double waterFee) {
		this.waterFee = waterFee;
	}

	public ChargeInfoEntity getPropertyCharge() {
		return propertyCharge;
	}

	public void setPropertyCharge(ChargeInfoEntity propertyCharge) {
		this.propertyCharge = propertyCharge;
	}

	public int getUnPropertyChargeCount() {
		return unPropertyChargeCount;
	}

	public void setUnPropertyChargeCount(int unPropertyChargeCount) {
		this.unPropertyChargeCount = unPropertyChargeCount;
	}

	public int getFinanceLogCount() {
		return financeLogCount;
	}

	public void setFinanceLogCount(int financeLogCount) {
		this.financeLogCount = financeLogCount;
	}

}
