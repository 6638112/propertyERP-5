package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

/**
 * 其他代收费用
* Filename:    PayBillForRevenueOtherFee.java
* @version:    1.0.0
* Create at:   2015年11月25日 上午11:32:13
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月25日       shiyl             1.0             1.0 Version
 */
public class PayBillForRevenueOtherFee extends AbstractRevSrcEntity<BigDecimal>{
	private BigInteger uniqueId;
	public void setUniqueId(BigInteger uniqueId) {
		this.uniqueId = uniqueId;
	}

	private PayBill payBill;
	public void setPayBill(PayBill payBill) {
		this.payBill = payBill;
	}
	
	/**支付成功的流水号*/
	private String payFlowNo;
	public void setPayFlowNo(String payFlowNo) {
		this.payFlowNo = payFlowNo;
	}
	
	/**支付方式*/
	private Integer payMethod;
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	@Override
	public BigInteger getUniqueId() {
//		return payBill.getId();
		return uniqueId;
	}

	@Override
	public BigDecimal getPayAmount() {
		return getAmountTotal();//使用总额参与收益计算
	}

	@Override
	public String getRevActiveTime() {
		return payBill.getSys0UpdTime();
	}

	@Override
	public BigDecimal getSrcMoney() {
		return getAmountTotal();//使用总额
	}

	@Override
	public BigDecimal getAmountPtbt() {
		return PriceUtil.div100(payBill.getAmount()-payBill.getSuccPayAmount());//待缴总金额-用户实际支付的账单金额=平台补贴
	}

	@Override
	public BigDecimal getAmountUsrReal() {
		return PriceUtil.div100(payBill.getSuccPayAmount());
	}

	@Override
	public String getPayFlowNo() {
		return payFlowNo;
	}

	@Override
	public Integer getPayMethod() {
		return payMethod;
	}
	
	private BigDecimal getAmountTotal(){
		return PriceUtil.div100(payBill.getAmount());
	}
}
