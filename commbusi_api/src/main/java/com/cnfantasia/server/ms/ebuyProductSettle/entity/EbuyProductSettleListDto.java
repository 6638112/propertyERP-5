package com.cnfantasia.server.ms.ebuyProductSettle.entity;

/**
 * 结算记录Dto
 * 
 * @author liyulin
 * @version 1.0 2016年6月7日 下午5:56:11
 */
public class EbuyProductSettleListDto {

	/**
	 * t_revenue_apply表f_id
	 */
	private String id;
	
	/**
	 * 提交时间
	 */
	private String applyTime;
	
	/**
	 * 审核时间
	 */
	private String auditTime;
	
	/**
	 * 支付时间
	 */
	private String payTime;
	
	/**
	 * 结算金额
	 */
	private String totalAmount;
	
	/**
	 * 结算账户
	 */
	private String accountName;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 计算状态
	 */
	private String settleStatus;
	
	public EbuyProductSettleListDto() {
		super();
	}

	public EbuyProductSettleListDto(String id, String applyTime, String auditTime, String payTime, String totalAmount, String accountName, String note,
			String settleStatus) {
		super();
		this.id = id;
		this.applyTime = applyTime;
		this.auditTime = auditTime;
		this.payTime = payTime;
		this.totalAmount = totalAmount;
		this.accountName = accountName;
		this.note = note;
		this.settleStatus = settleStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
}
