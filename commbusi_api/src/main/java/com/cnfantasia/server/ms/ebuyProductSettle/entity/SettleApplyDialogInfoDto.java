package com.cnfantasia.server.ms.ebuyProductSettle.entity;

/**
 * 弹出框信息Dto
 * 
 * @author liyulin
 * @version 1.0 2016年6月15日 下午5:34:23
 */
public class SettleApplyDialogInfoDto {

	/** 收款账户 */
	private String merchantName;
	/** 收款账号 */
	private String accountBank;
	/** 开户银行 */
	private String accountName;
	/** 联系方式 */
	private String telphone;
	/** 联系人 */
	private String linkName;
	/** 开户支行 */
	private String bankName;
	/** 开卡支行 */
	private String bankBranch;
	/** 支行所在省 */
	private String bankProvince;
	/** 支行所在市 */
	private String bankCity;

	public SettleApplyDialogInfoDto() {
		super();
	}

	public SettleApplyDialogInfoDto(String merchantName, String accountBank, String accountName, String telphone, String linkName, String bankName,
			String bankBranch, String bankProvince, String bankCity) {
		super();
		this.merchantName = merchantName;
		this.accountBank = accountBank;
		this.accountName = accountName;
		this.telphone = telphone;
		this.linkName = linkName;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.bankProvince = bankProvince;
		this.bankCity = bankCity;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

}
