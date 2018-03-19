package com.cnfantasia.server.api.loan.entity;

/**
 * 借贷用户基本信息
 * 
 * @author liyulin
 * @version 1.0 2017年7月12日 下午1:16:02
 */
public class LoanUserBaseInfo {
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
	private Double roomSize;
	/** 是否业主 */
	private Boolean isPropertyProprietor;

	public LoanUserBaseInfo() {
		super();
	}

	public LoanUserBaseInfo(String name, String mobile, String cardId,
			String cityName, Integer residenceTime, Double roomSize,
			Boolean isPropertyProprietor) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.cardId = cardId;
		this.cityName = cityName;
		this.residenceTime = residenceTime;
		this.roomSize = roomSize;
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

	public Integer getResidenceTime() {
		return residenceTime;
	}

	public void setResidenceTime(Integer residenceTime) {
		this.residenceTime = residenceTime;
	}

	public Double getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Double roomSize) {
		this.roomSize = roomSize;
	}

	public Boolean getIsPropertyProprietor() {
		return isPropertyProprietor;
	}

	public void setIsPropertyProprietor(Boolean isPropertyProprietor) {
		this.isPropertyProprietor = isPropertyProprietor;
	}

}
