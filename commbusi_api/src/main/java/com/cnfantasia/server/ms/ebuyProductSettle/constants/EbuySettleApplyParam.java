package com.cnfantasia.server.ms.ebuyProductSettle.constants;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: EbuySettleApplyParam
 * @Date: 2016-08-10 9:26
 * @Auther: kangduo
 * @Description:(结算申请查询参数)
 */
public class EbuySettleApplyParam implements Serializable {

	private static final long serialVersionUID = 2878765705832829303L;

	private BigInteger supplyMerchantId;
	private String orderNo;
	private String payTimeStart;
	private String payTimeEnd;
	private String pageType;
	private BigInteger revenueApplyId;
	private String revApplyFinanceId;
	private String visibleType;

	public BigInteger getSupplyMerchantId() {
		return supplyMerchantId;
	}

	public void setSupplyMerchantId(BigInteger supplyMerchantId) {
		this.supplyMerchantId = supplyMerchantId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayTimeStart() {
		return payTimeStart;
	}

	public void setPayTimeStart(String payTimeStart) {
		this.payTimeStart = payTimeStart;
	}

	public String getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public BigInteger getRevenueApplyId() {
		return revenueApplyId;
	}

	public void setRevenueApplyId(BigInteger revenueApplyId) {
		this.revenueApplyId = revenueApplyId;
	}

	public String getRevApplyFinanceId() {
		return revApplyFinanceId;
	}

	public void setRevApplyFinanceId(String revApplyFinanceId) {
		this.revApplyFinanceId = revApplyFinanceId;
	}

	public String getVisibleType() {
		return visibleType;
	}

	public void setVisibleType(String visibleType) {
		this.visibleType = visibleType;
	}

}
