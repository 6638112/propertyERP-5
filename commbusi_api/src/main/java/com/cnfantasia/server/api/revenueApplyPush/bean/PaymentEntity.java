package com.cnfantasia.server.api.revenueApplyPush.bean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

/**
 * 付款单
 * */
public class PaymentEntity {
	private String srcBillNumber;			// 原系统编码
	
	private String billStatus;				// 单据状态(保存、提交)
	private String mainOrgCompany;			// 公司
	private String bizDate;					// 业务日期
	private String payBillType;				// 付款类型
	private String currency;				// 付款币别
	private String payerAccountBank;		// 付款账户
	private String payerBank;				// 付款银行
	private String payerAccount;			// 付款科目
	private String settlementType;			// 结算方式(PayerPay、SharePay、PayeePay)付款方承担费用、均摊、收款方承担费用
	private String adminOrgUnit;			// 部门
	private String costCenter;				// 成本中心
	private String payeeType;				// 收款人类型
	private String payee;					// 收款人名称
	private String payeeAccountBank;		// 收款账号
	private String payeeBank;				// 收银银行
	private String paymentType;				//付款方式
	private String sourceType;				//付款单来源
	private String desc;					// 摘要
	private List<PaymentEntryEntity> casPaymentEntry;			// 分录
	private List<AttachmentEntity> attachment;					// 附件
	String creator; //制单人
	
	// 以下是解放区增加的字段
	private BigInteger miniRoleId;//提款对象的id，如物业公司的id，代理的id，维修师傅的id，等等
	private String easUserAccount;  //EAS推送账号
	private String easUserPassword;	//EAS推送账号密码
	
	public BigInteger getMiniRoleId() {
		return miniRoleId;
	}
	public void setMiniRoleId(BigInteger miniRoleId) {
		this.miniRoleId = miniRoleId;
	}
	public String getEasUserAccount() {
		return easUserAccount;
	}
	public void setEasUserAccount(String easUserAccount) {
		this.easUserAccount = easUserAccount;
	}
	public String getEasUserPassword() {
		try {
			return new String(Base64.decodeBase64(easUserPassword));
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		return easUserPassword;
	}
	public void setEasUserPassword(String easUserPassword) {
		this.easUserPassword = easUserPassword;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getSrcBillNumber() {
		return srcBillNumber;
	}
	public void setSrcBillNumber(String srcBillNumber) {
		this.srcBillNumber = srcBillNumber;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getMainOrgCompany() {
		return mainOrgCompany;
	}
	public void setMainOrgCompany(String mainOrgCompany) {
		this.mainOrgCompany = mainOrgCompany;
	}
	public String getBizDate() {
		return bizDate;
	}
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	public String getPayBillType() {
		return payBillType;
	}
	public void setPayBillType(String payBillType) {
		this.payBillType = payBillType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPayerAccountBank() {
		return payerAccountBank;
	}
	public void setPayerAccountBank(String payerAccountBank) {
		this.payerAccountBank = payerAccountBank;
	}
	public String getPayerBank() {
		return payerBank;
	}
	public void setPayerBank(String payerBank) {
		this.payerBank = payerBank;
	}
	public String getPayerAccount() {
		return payerAccount;
	}
	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public String getAdminOrgUnit() {
		return adminOrgUnit;
	}
	public void setAdminOrgUnit(String adminOrgUnit) {
		this.adminOrgUnit = adminOrgUnit;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getPayeeType() {
		return payeeType;
	}
	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getPayeeAccountBank() {
		return payeeAccountBank;
	}
	public void setPayeeAccountBank(String payeeAccountBank) {
		this.payeeAccountBank = payeeAccountBank;
	}
	public String getPayeeBank() {
		return payeeBank;
	}
	public void setPayeeBank(String payeeBank) {
		this.payeeBank = payeeBank;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public List<PaymentEntryEntity> getCasPaymentEntry() {
		if(casPaymentEntry == null){
			casPaymentEntry = new ArrayList<PaymentEntryEntity>();
		}
		return casPaymentEntry;
	}
	public void setCasPaymentEntry(List<PaymentEntryEntity> casPaymentEntry) {
		this.casPaymentEntry = casPaymentEntry;
	}
	public List<AttachmentEntity> getAttachment() {
		if(attachment == null){
			attachment = new ArrayList<AttachmentEntity>();
		}
		return attachment;
	}
	public void setAttachment(List<AttachmentEntity> attachment) {
		this.attachment = attachment;
	}
	
}
