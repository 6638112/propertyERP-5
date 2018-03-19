package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 二级疏通类型 
 * @author wenfq
 *
 */
public class DredgeType2ndForUser {
	private BigInteger id;
	private BigInteger parentId;
	private String name;
	private BigDecimal estinatePrice; //预估费用
	private BigDecimal orignalPrice; //原价
	private BigDecimal discountPrice; //活动价
	private String priceDesc; // 价格描述
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getEstinatePrice() {
		return estinatePrice;
	}
	public void setEstinatePrice(BigDecimal estinatePrice) {
		this.estinatePrice = estinatePrice;
	}
	public BigInteger getParentId() {
		return parentId;
	}
	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}
	public BigDecimal getOrignalPrice() {
		return orignalPrice;
	}
	public void setOrignalPrice(BigDecimal orignalPrice) {
		this.orignalPrice = orignalPrice;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
}
