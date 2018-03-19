package com.cnfantasia.server.api.property.dto;

/**
 * 车禁已缴账单详情
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午1:43:18
 */
public class CarPaidBillDetailDto extends CarUnPaidBillDetailDto {
	private BillPayDto billPay;

	public BillPayDto getBillPay() {
		return billPay;
	}

	public void setBillPay(BillPayDto billPay) {
		this.billPay = billPay;
	}

}
