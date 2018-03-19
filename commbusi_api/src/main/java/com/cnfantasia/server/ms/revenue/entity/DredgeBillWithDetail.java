package com.cnfantasia.server.ms.revenue.entity;

import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;

public class DredgeBillWithDetail extends DredgeBill {
	
	private static final long serialVersionUID = -6369689948326741641L;
	
	/** 费用类型 */
	private Integer feeType;	
	/** 金额 */
	private Long payAmountDetail;
	/** 是否包含平台费 */
	private Integer isIncludePlatformFee;
	
	private Integer revenueTmAdd;

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public Long getPayAmountDetail() {
		return payAmountDetail;
	}

	public void setPayAmountDetail(Long payAmountDetail) {
		this.payAmountDetail = payAmountDetail;
	}

	public Integer getIsIncludePlatformFee() {
		return isIncludePlatformFee;
	}

	public void setIsIncludePlatformFee(Integer isIncludePlatformFee) {
		this.isIncludePlatformFee = isIncludePlatformFee;
	}

	public Integer getRevenueTmAdd() {
		return revenueTmAdd;
	}

	public void setRevenueTmAdd(Integer revenueTmAdd) {
		this.revenueTmAdd = revenueTmAdd;
	}
	
}
