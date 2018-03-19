package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import java.math.BigDecimal;

/**
 * 结算管理导出表Dto
 * 
 * @author liyulin
 * @version 1.0 2016年6月17日 上午9:12:43
 */
public class EbuyProductSettleAdminExportDto {

	/** 供应商 */
	private String merchant;
	/** 合作模式 */
	private Integer revenueType;
	/** 提交时间 */
	private String applyTime;
	/** 审核时间 */
	private String handlerTime;
	/** 提款单号 */
	private String applyNo;
	/** 单据编号 */
	private String billNo;
	/** 结算时间 */
	private String settleTime;
	/** 结算金额 */
	private BigDecimal totalAmount;
	/** 审核状态 */
	private Integer auditStatus;
	/** 提款状态 */
	private Integer tkStatus;

	public EbuyProductSettleAdminExportDto() {
		super();
	}

	public EbuyProductSettleAdminExportDto(String merchant, String applyTime, String handlerTime, String applyNo, String billNo, String settleTime,
			BigDecimal totalAmount, Integer auditStatus, Integer tkStatus) {
		super();
		this.merchant = merchant;
		this.applyTime = applyTime;
		this.handlerTime = handlerTime;
		this.applyNo = applyNo;
		this.billNo = billNo;
		this.settleTime = settleTime;
		this.totalAmount = totalAmount;
		this.auditStatus = auditStatus;
		this.tkStatus = tkStatus;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getHandlerTime() {
		return handlerTime;
	}

	public void setHandlerTime(String handlerTime) {
		this.handlerTime = handlerTime;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(String settleTime) {
		this.settleTime = settleTime;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getTkStatus() {
		return tkStatus;
	}

	public void setTkStatus(Integer tkStatus) {
		this.tkStatus = tkStatus;
	}

	public Integer getRevenueType() {
		return revenueType;
	}

	public void setRevenueType(Integer revenueType) {
		this.revenueType = revenueType;
	}
}
