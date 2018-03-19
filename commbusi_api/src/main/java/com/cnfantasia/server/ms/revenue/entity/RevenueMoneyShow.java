package com.cnfantasia.server.ms.revenue.entity;

/**
 * 收益金额显示
* Filename:    RevenueMoneyShow.java
* @version:    1.0.0
* Create at:   2015年11月22日 下午3:33:04
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月22日       shiyl             1.0             1.0 Version
 */
public class RevenueMoneyShow {
	/**可提款总额*/
	private Double totalAmount;
	/**平台补贴额*/
	private Double amountPtbt;
	/**用户实际缴费额*/
	private Double amountUsrReal;
	
	private Double refundAmount;
	
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getAmountPtbt() {
		return amountPtbt;
	}
	public void setAmountPtbt(Double amountPtbt) {
		this.amountPtbt = amountPtbt;
	}
	public Double getAmountUsrReal() {
		return amountUsrReal;
	}
	public void setAmountUsrReal(Double amountUsrReal) {
		this.amountUsrReal = amountUsrReal;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	
}
