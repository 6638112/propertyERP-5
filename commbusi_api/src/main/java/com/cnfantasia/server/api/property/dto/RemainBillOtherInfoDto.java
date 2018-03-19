package com.cnfantasia.server.api.property.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 待缴费账单界面其它信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月13日 上午9:59:14
 */
public class RemainBillOtherInfoDto {
	/** 物业代扣卡账户金额 */
	private BigDecimal propertyCardAmt;
	/** 是否开通代扣卡服务 */
	private boolean isOpenService;
	/** 缴费月份 */
	private List<String> periodMonths;
	/** 停车费可缴费月数 */
	private List<String> carMonths;

	public BigDecimal getPropertyCardAmt() {
		return propertyCardAmt;
	}

	public void setPropertyCardAmt(BigDecimal propertyCardAmt) {
		this.propertyCardAmt = propertyCardAmt;
	}

	public boolean isOpenService() {
		return isOpenService;
	}

	public void setOpenService(boolean isOpenService) {
		this.isOpenService = isOpenService;
	}

	public List<String> getPeriodMonths() {
		return periodMonths;
	}

	public void setPeriodMonths(List<String> periodMonths) {
		this.periodMonths = periodMonths;
	}

	public List<String> getCarMonths() {
		return carMonths;
	}

	public void setCarMonths(List<String> carMonths) {
		this.carMonths = carMonths;
	}

}
