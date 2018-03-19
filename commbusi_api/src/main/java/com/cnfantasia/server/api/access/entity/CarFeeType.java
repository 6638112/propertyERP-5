package com.cnfantasia.server.api.access.entity;

import java.math.BigDecimal;

/**
 * 月卡费用类型
 * 
 * @author liyulin
 * @version 1.0 2017年8月14日 下午2:43:16
 */
public class CarFeeType {

	/** 费用（单位：元） */
	private BigDecimal fee;
	/** 数量 */
	private int num;
	/** 单位 */
	private String unit;
	private String typeName;

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
