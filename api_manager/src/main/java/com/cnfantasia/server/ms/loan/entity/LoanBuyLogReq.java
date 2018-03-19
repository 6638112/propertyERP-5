package com.cnfantasia.server.ms.loan.entity;

/**
 * 借贷订单管理查询参数
 * 
 * @author liyulin
 * @version 1.0 2017年6月7日 下午1:15:55
 */
public class LoanBuyLogReq {

	/** 城市 */
	private String city;
	/** 小区 */
	private String gbName;
	/** 楼栋 */
	private String buildingName;
	/** 房间号 */
	private String roomNum;
	/** 购买人姓名 */
	private String name;
	/** 手机号 */
	private String mobile;
	/** 身份证 */
	private String idCard;
	/** 产品类型（冗余） */
	private String productType;
	/** 借贷时间开始 */
	private String loanDateStart;
	/** 借贷时间 截至 */
	private String loanDateEnd;
	/** 借贷平台 */
	private String platform;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getLoanDateStart() {
		return loanDateStart;
	}

	public void setLoanDateStart(String loanDateStart) {
		this.loanDateStart = loanDateStart;
	}

	public String getLoanDateEnd() {
		return loanDateEnd;
	}

	public void setLoanDateEnd(String loanDateEnd) {
		this.loanDateEnd = loanDateEnd;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
