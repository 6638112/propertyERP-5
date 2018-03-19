package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;

/**
 * 结算管理Dto
 * 
 * @author liyulin
 * @version 1.0 2016年6月16日 下午6:58:41
 */
public class RevenueApplyDto extends RevenueApply {
	
	/** 供应商 */
	private String merchant;

	/** 结算的订单数量*/
	private Integer settleOrderCount;

	public RevenueApplyDto() {
		super();
	}

	public RevenueApplyDto(String merchant) {
		super();
		this.merchant = merchant;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public Integer getSettleOrderCount() {
		return settleOrderCount;
	}

	public void setSettleOrderCount(Integer settleOrderCount) {
		this.settleOrderCount = settleOrderCount;
	}
}
