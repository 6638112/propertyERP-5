package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;

/**
 * 收益总金额预览信息
 */
public class RevenueAmountInfo {
	/**项目类别*/
	private Integer projectType;
	/**总金额*/
	private BigDecimal totalAmount;
	/**已结算金额*/
	private BigDecimal setedAmount;
	/**待结算金额*/
	private BigDecimal toSetAmount;
	/**在途金额*/
	private BigDecimal settingAmount;
	public Integer getProjectType() {
		return projectType;
	}
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getSetedAmount() {
		return setedAmount;
	}
	public void setSetedAmount(BigDecimal setedAmount) {
		this.setedAmount = setedAmount;
	}
	public BigDecimal getToSetAmount() {
		return toSetAmount;
	}
	public void setToSetAmount(BigDecimal toSetAmount) {
		this.toSetAmount = toSetAmount;
	}
	public BigDecimal getSettingAmount() {
		return settingAmount;
	}
	public void setSettingAmount(BigDecimal settingAmount) {
		this.settingAmount = settingAmount;
	}
	
	
	
}
