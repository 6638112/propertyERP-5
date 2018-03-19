package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.common.utils.DateUtils;


public class EbuyForRevenue extends AbstractRevSrcEntity<Long>{
	
	private EbuyOrderRevenue ebuyOrderRevenue;

	@Override
	public BigInteger getUniqueId() {
		return ebuyOrderRevenue.getDelivOrderId();
	}

	@Override
	public Long getPayAmount() {
		long totalPrice = 0;
		for(EbuyProdRevenue ebuyProdRevenue : ebuyOrderRevenue.getEbuyProdRevenueList()) {
			totalPrice += ebuyProdRevenue.getPrice();
		}
		totalPrice += ebuyOrderRevenue.getDelivFee();
		return totalPrice;
	}

	@Override
	public String getRevActiveTime() {
		return DateUtils.formatTime(ebuyOrderRevenue.getRecTm());
	}

	@Override
	public BigDecimal getSrcMoney() {
		return BigDecimal.ZERO;
	}

	public EbuyOrderRevenue getEbuyOrderRevenue() {
		return ebuyOrderRevenue;
	}

	public void setEbuyOrderRevenue(EbuyOrderRevenue ebuyOrderRevenue) {
		this.ebuyOrderRevenue = ebuyOrderRevenue;
	}

	@Override
	public BigDecimal getAmountPtbt() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getAmountUsrReal() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}
	
}
