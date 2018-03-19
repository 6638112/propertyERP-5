package com.cnfantasia.server.api.limitBuy.entity;

import java.math.BigInteger;

public class LimitBuyId_PrdtQty {
	BigInteger limitBuyId; //限时购活动id
	int prdtQty;//购买数量
	public BigInteger getLimitBuyId() {
		return limitBuyId;
	}
	public void setLimitBuyId(BigInteger limitBuyId) {
		this.limitBuyId = limitBuyId;
	}
	
	public int getPrdtQty() {
		return prdtQty;
	}
	public void setPrdtQty(int prdtQty) {
		this.prdtQty = prdtQty;
	}
}
