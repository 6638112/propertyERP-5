package com.cnfantasia.server.api.property.dto;

import java.util.List;

/**
 * 已缴账单列表每项信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月15日 下午5:38:43
 */
public class PaidBillItemDto {

	/** 账单标题 */
	private String billTitle;
	/** 账单实缴金额 */
	private String realAmt;
	/** 账单总金额 */
	private String totalAmt;
	/** 账单优惠金额 */
	private String discountAmt;
	/** 是否物业充值 */
	private boolean rechargeBill;
	/** 账单明细 */
	private List<BillDetailDto> billDetails;

	public String getBillTitle() {
		return billTitle;
	}

	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}

	public String getRealAmt() {
		return realAmt;
	}

	public void setRealAmt(String realAmt) {
		this.realAmt = realAmt;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(String discountAmt) {
		this.discountAmt = discountAmt;
	}

	public boolean isRechargeBill() {
		return rechargeBill;
	}

	public void setRechargeBill(boolean rechargeBill) {
		this.rechargeBill = rechargeBill;
	}

	public List<BillDetailDto> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetailDto> billDetails) {
		this.billDetails = billDetails;
	}

}
