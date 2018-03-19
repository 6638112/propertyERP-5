package com.cnfantasia.server.api.property.dto;

import java.math.BigInteger;

/**
 * 账单详情
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午11:07:33
 */
public class BillDetailDto {

	/** 账单名称 */
	private String name;
	/** 描述（缴费区间、车牌等） */
	private String desc;
	/** 账单金额（单位：元） */
	private String amount;
	/** 优惠金额（单位：元） */
	private String discount;
	/** 物业账单名称 */
	private String typeName;
	/** 图标 */
	private String icon;
	/** 账单id */
	private BigInteger orderId;
	/** 账单详情类型（1：物业缴费；2：车禁） */
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public BigInteger getOrderId() {
		return orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
