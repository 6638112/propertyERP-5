package com.cnfantasia.server.api.property.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 物业缴费账单尾部信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午1:58:47
 */
public class PropertyBillTailDto {
	/** 缴费地址 */
	private String payAddress;
	/** 业主姓名 */
	private String proprietorName;
	/** 往月欠费（单位：分） */
	private BigDecimal lastUnpaid;
	/** 明细 */
	private List<PayBillDetailDto> payBillDetails;

	public String getPayAddress() {
		return payAddress;
	}

	public void setPayAddress(String payAddress) {
		this.payAddress = payAddress;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public BigDecimal getLastUnpaid() {
		return lastUnpaid;
	}

	public void setLastUnpaid(BigDecimal lastUnpaid) {
		this.lastUnpaid = lastUnpaid;
	}

	public List<PayBillDetailDto> getPayBillDetails() {
		return payBillDetails;
	}

	public void setPayBillDetails(List<PayBillDetailDto> payBillDetails) {
		this.payBillDetails = payBillDetails;
	}

}
