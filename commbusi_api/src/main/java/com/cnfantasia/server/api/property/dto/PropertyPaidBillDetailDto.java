package com.cnfantasia.server.api.property.dto;

/**
 * 物业已缴费账单详情
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午1:44:05
 */
public class PropertyPaidBillDetailDto extends PropertyUnPaidBillDetailDto {
	private BillPayDto billPay;

	public BillPayDto getBillPay() {
		return billPay;
	}

	public void setBillPay(BillPayDto billPay) {
		this.billPay = billPay;
	}

}
