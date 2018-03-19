package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EbuyProductShelf implements Serializable {

	private static final long serialVersionUID = -6785813390403696924L;

	private Long id;
	
	private BigDecimal priceDicount;
	
	private Integer opType; //运营类型，1为新用户专享
	
	private String opName; //运营活动名称,如新用户专享
	
	private Integer canBuyNum; //此类活动最多可以买多少个商品

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPriceDicount() {
		return priceDicount;
	}

	public void setPriceDicount(BigDecimal priceDicount) {
		this.priceDicount = priceDicount;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public Integer getCanBuyNum() {
		return canBuyNum;
	}

	public void setCanBuyNum(Integer canBuyNum) {
		this.canBuyNum = canBuyNum;
	}
	
}
