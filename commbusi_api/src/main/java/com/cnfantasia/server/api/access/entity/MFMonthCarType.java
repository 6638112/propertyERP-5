package com.cnfantasia.server.api.access.entity;

import java.math.BigDecimal;

/**
 * 小蜜蜂月卡车类型
 * 
 * @author liyulin
 * @version 1.0 2017年8月14日 上午11:27:44
 */
public class MFMonthCarType {

	/** 月卡类型ID */
	private String id;
	/** 名称 */
	private String typeName;
	/** 一次充值最大月数 */
	private int maxMonthNum;
	/** 数量 */
	private int unitNum;
	/** 单位 */
	private String unit;
	/** 单价（单位：元） */
	private BigDecimal unitPrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getMaxMonthNum() {
		return maxMonthNum;
	}

	public void setMaxMonthNum(int maxMonthNum) {
		this.maxMonthNum = maxMonthNum;
	}

	public int getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(int unitNum) {
		this.unitNum = unitNum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
