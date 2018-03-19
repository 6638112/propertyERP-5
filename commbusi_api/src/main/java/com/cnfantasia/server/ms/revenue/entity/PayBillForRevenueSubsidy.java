package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

/**
 * 物业费补贴的收益
* Filename:    PayBillForRevenueSubsidy.java
* @version:    1.0.0
* Create at:   2015年11月25日 上午11:25:44
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月25日       shiyl             1.0             1.0 Version
 */
public class PayBillForRevenueSubsidy extends AbstractRevSrcEntity<BigDecimal>{
	private BigInteger uniqueId;
	public void setUniqueId(BigInteger uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	private PayBill payBill;
	public void setPayBill(PayBill payBill) {
		this.payBill = payBill;
	}
	
	@Override
	public BigInteger getUniqueId() {
		return uniqueId;
//		return payBill.getId();
	}

	@Override
	public BigDecimal getPayAmount() {
		return getAmountPtbt();//使用平台补贴额参与收益计算
	}

	@Override
	public String getRevActiveTime() {
		return payBill.getSys0UpdTime();
	}

	@Override
	public BigDecimal getSrcMoney() {
		return getAmountPtbt();//金额类收益才会有值，否则默认取0
	}

	@Override
	public BigDecimal getAmountPtbt() {
		if(payBill.getDeduPrice() != null && payBill.getDeduPrice() > 0) {
			return PriceUtil.div100(payBill.getAmount()-payBill.getSuccPayAmount() - payBill.getDeduPrice());//待缴总金额-用户实际支付的账单金额=平台补贴
		} else {
			return PriceUtil.div100(payBill.getAmount()-payBill.getSuccPayAmount());//待缴总金额-用户实际支付的账单金额=平台补贴
		}
		
	}

	@Override
	public BigDecimal getAmountUsrReal() {
		return BigDecimal.ZERO;//对于补贴额，用户实缴为0
	}
	
	
}
