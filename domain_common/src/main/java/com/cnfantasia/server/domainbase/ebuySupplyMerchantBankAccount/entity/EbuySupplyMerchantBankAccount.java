package com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(楼下店等电商供应商银行卡信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuySupplyMerchantBankAccount implements Serializable{
*/


public class EbuySupplyMerchantBankAccount extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 电商供应商Id */	private BigInteger tSupplyMerchantId;	/** 银行账号 */	private String accountBank;	/** 银行账号账户名 */	private String accountName;	/** 开户行 */	private String bankName;	/** 开卡支行 */	private String bankBranch;	/** 支行所在省 */	private String bankProvince;	/** 运行所在城市 */	private String bankCity;	/** 备注手机号 */	private String linkTel;
	public EbuySupplyMerchantBankAccount(){
	}
	public EbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount arg){
		this.id = arg.getId();		this.tSupplyMerchantId = arg.gettSupplyMerchantId();		this.accountBank = arg.getAccountBank();		this.accountName = arg.getAccountName();		this.bankName = arg.getBankName();		this.bankBranch = arg.getBankBranch();		this.bankProvince = arg.getBankProvince();		this.bankCity = arg.getBankCity();		this.linkTel = arg.getLinkTel();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tSupplyMerchantId 电商供应商Id	 * @param accountBank 银行账号	 * @param accountName 银行账号账户名	 * @param bankName 开户行	 * @param bankBranch 开卡支行	 * @param bankProvince 支行所在省	 * @param bankCity 运行所在城市	 * @param linkTel 备注手机号	 * @param sys0UpdTime 更新时间	 * @param sys0DelState 记录状态	 */
	public EbuySupplyMerchantBankAccount(BigInteger id,BigInteger tSupplyMerchantId,String accountBank,String accountName,String bankName,String bankBranch,String bankProvince,String bankCity,String linkTel,String sys0UpdTime,Integer sys0DelState){
		this.id = id;		this.tSupplyMerchantId = tSupplyMerchantId;		this.accountBank = accountBank;		this.accountName = accountName;		this.bankName = bankName;		this.bankBranch = bankBranch;		this.bankProvince = bankProvince;		this.bankCity = bankCity;		this.linkTel = linkTel;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tSupplyMerchantId=").append(tSupplyMerchantId).append(";");		sbf.append("accountBank=").append(accountBank).append(";");		sbf.append("accountName=").append(accountName).append(";");		sbf.append("bankName=").append(bankName).append(";");		sbf.append("bankBranch=").append(bankBranch).append(";");		sbf.append("bankProvince=").append(bankProvince).append(";");		sbf.append("bankCity=").append(bankCity).append(";");		sbf.append("linkTel=").append(linkTel).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettSupplyMerchantId() {		return tSupplyMerchantId;	}	public void settSupplyMerchantId(BigInteger tSupplyMerchantId) {		this.tSupplyMerchantId = tSupplyMerchantId;	}	public String getAccountBank() {		return accountBank;	}	public void setAccountBank(String accountBank) {		this.accountBank = accountBank;	}	public String getAccountName() {		return accountName;	}	public void setAccountName(String accountName) {		this.accountName = accountName;	}	public String getBankName() {		return bankName;	}	public void setBankName(String bankName) {		this.bankName = bankName;	}	public String getBankBranch() {		return bankBranch;	}	public void setBankBranch(String bankBranch) {		this.bankBranch = bankBranch;	}	public String getBankProvince() {		return bankProvince;	}	public void setBankProvince(String bankProvince) {		this.bankProvince = bankProvince;	}	public String getBankCity() {		return bankCity;	}	public void setBankCity(String bankCity) {		this.bankCity = bankCity;	}	public String getLinkTel() {		return linkTel;	}	public void setLinkTel(String linkTel) {		this.linkTel = linkTel;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EbuySupplyMerchantBankAccount other = (EbuySupplyMerchantBankAccount) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
