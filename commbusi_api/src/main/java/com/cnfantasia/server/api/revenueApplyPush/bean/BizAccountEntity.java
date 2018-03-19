package com.cnfantasia.server.api.revenueApplyPush.bean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

/**
 * 报销单
 * @author wenfq
 *
 */
public class BizAccountEntity implements Cloneable{
	private String srcBillNumber;			// 原系统编码
	
	private String billStatus;				// 单据状态(保存、提交)
	private String theme;					// 主题
	private String appDate;					// 申请日期
	private String applierCompany;			// 报销人公司
	private String applierDept;				// 报销人部门
	private String applier;					// 报销人
	private String position;				// 职位
	private String payCompany;				// 费用支付公司
	private String currency;				// 币别
	private String payMode;					// 支付方式
	private String prior;					// 紧急程度(HIGH、MIDDLE、LOW)
	private String revDept;					// 收款单位
	private String revAccount;				// 收款方账号
	private String revOpenBank;				// 收款方开户行
	private String isonlyperson;			//是否是个人收款

	private String cause;					// 事由
	private String biller;					//制单人
	private String desc;					// 备注
	
	private List<BizAccountEntryEntity> bizAccountEntry;	// 分录数据
	private List<AttachmentEntity> attachment;				// 附件
	
	// 以下是解放区增加的字段
	private BigInteger miniRoleId;//提款对象的id，如物业公司的id，代理的id，维修师傅的id，等等
	private String easUserAccount;  //EAS推送账号
	private String easUserPassword;	//EAS推送账号密码
	
	public String getEasUserAccount() {
		return easUserAccount;
	}
	public void setEasUserAccount(String easUserAccount) {
		this.easUserAccount = easUserAccount;
	}
	
	/**
	 * 生成密文方法，采用Base64加解密
	 * @param args
	 */
	public static void main(String[] args) {
//		String password = new String(Base64.encodeBase64String("chenxiaoyhyn".getBytes()));
		String password = new String(Base64.encodeBase64String("pjx588".getBytes()));
		System.out.println(password);
		System.out.println(new String(Base64.decodeBase64(password)));
	}
	
	
	public String getEasUserPassword() {
		/*
		  	String password = new String(Base64.encodeBase64String("123456".getBytes()));
			System.out.println(password);
			System.out.println(new String(Base64.decodeBase64(password)));   //MTIzNDU2
		 */
		
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
	public BigInteger getMiniRoleId() {
		return miniRoleId;
	}
	public void setMiniRoleId(BigInteger miniRoleId) {
		this.miniRoleId = miniRoleId;
	}
	public List<BizAccountEntryEntity> getBizAccountEntry() {
		if(bizAccountEntry == null){
			bizAccountEntry = new ArrayList<BizAccountEntryEntity>();
		}
		return bizAccountEntry;
	}
	public void setBizAccountEntry(List<BizAccountEntryEntity> bizAccountEntry) {
		this.bizAccountEntry = bizAccountEntry;
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
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getApplierCompany() {
		return applierCompany;
	}
	public void setApplierCompany(String applierCompany) {
		this.applierCompany = applierCompany;
	}
	public String getApplierDept() {
		return applierDept;
	}
	public void setApplierDept(String applierDept) {
		this.applierDept = applierDept;
	}
	public String getApplier() {
		return applier;
	}
	public void setApplier(String applier) {
		this.applier = applier;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPayCompany() {
		return payCompany;
	}
	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getPrior() {
		return prior;
	}
	public void setPrior(String prior) {
		this.prior = prior;
	}
	public String getRevDept() {
		return revDept;
	}
	public void setRevDept(String revDept) {
		this.revDept = revDept;
	}
	public String getRevAccount() {
		return revAccount;
	}
	public void setRevAccount(String revAccount) {
		this.revAccount = revAccount;
	}
	public String getRevOpenBank() {
		return revOpenBank;
	}
	public void setRevOpenBank(String revOpenBank) {
		this.revOpenBank = revOpenBank;
	}

	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBiller() {
		return biller;
	}
	public void setBiller(String biller) {
		this.biller = biller;
	}
	public String getIsonlyperson() {
		return isonlyperson;
	}
	public void setIsonlyperson(String isonlyperson) {
		this.isonlyperson = isonlyperson;
	}
	
	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
