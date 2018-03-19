package com.cnfantasia.server.ms.payBill.entity;

public class PayBillTotalData {
	/**账单总额*/
	private Long amountTotal;
	/**实付总额*/
	private Long succPayAmountTotal;
	
	private Long deduPriceTotal;
	
	public Long getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(Long amountTotal) {
		this.amountTotal = amountTotal;
	}
	public Long getSuccPayAmountTotal() {
		return succPayAmountTotal;
	}
	public void setSuccPayAmountTotal(Long succPayAmountTotal) {
		this.succPayAmountTotal = succPayAmountTotal;
	}
	public Long getDeduPriceTotal() {
		return deduPriceTotal;
	}
	public void setDeduPriceTotal(Long deduPriceTotal) {
		this.deduPriceTotal = deduPriceTotal;
	}
	
}
