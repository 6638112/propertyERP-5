package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;


public class DredgeBillForRevenue extends AbstractRevSrcEntity<BigDecimal>{
	private DredgeBill dredgeBill;
	
	public DredgeBill getDredgeBill() {
		return dredgeBill;
	}

	public void setDredgeBill(DredgeBill dredgeBill) {
		this.dredgeBill = dredgeBill;
	}
	/**
	 * 补贴金额
	 */
	BigDecimal amountPtbt;
	/**
	 * 用户实付金额
	 */
	BigDecimal amountUsrReal;

	public void setAmountPtbt(BigDecimal amountPtbt) {
		this.amountPtbt = amountPtbt;
	}

	public void setAmountUsrReal(BigDecimal amountUsrReal) {
		this.amountUsrReal = amountUsrReal;
	}

	@Override
	public BigInteger getUniqueId() {
		return dredgeBill.getId();
	}

	@Override
	public BigDecimal getPayAmount() {
		return getSrcMoney();//使用用户实际缴费额参与收益计算
	}

	@Override
	public String getRevActiveTime() {
		//TODO 用户支付的时间 
		return dredgeBill.getSys0UpdTime();
	}

	@Override
	public BigDecimal getSrcMoney() {
		return PriceUtil.div100(dredgeBill.getPayAmount());//使用用户实际缴费额参与收益计算
	}

	@Override
	public BigDecimal getAmountPtbt() {
		return amountPtbt;
	}

	@Override
	public BigDecimal getAmountUsrReal() {
		return amountUsrReal;
	}
}
