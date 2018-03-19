package com.cnfantasia.server.domainbase.dredgeBankCard.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(用户绑定的银行卡信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class DredgeBankCard implements Serializable{
/* */

/* 
public class DredgeBankCard extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 银行名称 */	private String bankName;	/** 银行支行名称 */	private String bankBranchName;	/** 持卡人 */	private String ownerName;	/** 银行账号 */	private String bankNo;	/**  */	private BigInteger tUserFId;	/** 是否默认收款银行账号 */	private Integer isDefault;	/** 支行所在省 */	private String bankProvince;	/** 支行所在市 */	private String bankCity;
	public DredgeBankCard(){
	}
	public DredgeBankCard(DredgeBankCard arg){
		this.id = arg.getId();		this.bankName = arg.getBankName();		this.bankBranchName = arg.getBankBranchName();		this.ownerName = arg.getOwnerName();		this.bankNo = arg.getBankNo();		this.tUserFId = arg.gettUserFId();		this.isDefault = arg.getIsDefault();		this.bankProvince = arg.getBankProvince();		this.bankCity = arg.getBankCity();
	}
	/**	 * 	 * @param id 	 * @param bankName 银行名称	 * @param bankBranchName 银行支行名称	 * @param ownerName 持卡人	 * @param bankNo 银行账号	 * @param tUserFId 	 * @param isDefault 是否默认收款银行账号	 * @param bankProvince 支行所在省	 * @param bankCity 支行所在市	 */
	public DredgeBankCard(BigInteger id,String bankName,String bankBranchName,String ownerName,String bankNo,BigInteger tUserFId,Integer isDefault,String bankProvince,String bankCity){
		this.id = id;		this.bankName = bankName;		this.bankBranchName = bankBranchName;		this.ownerName = ownerName;		this.bankNo = bankNo;		this.tUserFId = tUserFId;		this.isDefault = isDefault;		this.bankProvince = bankProvince;		this.bankCity = bankCity;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("bankName=").append(bankName).append(";");		sbf.append("bankBranchName=").append(bankBranchName).append(";");		sbf.append("ownerName=").append(ownerName).append(";");		sbf.append("bankNo=").append(bankNo).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("isDefault=").append(isDefault).append(";");		sbf.append("bankProvince=").append(bankProvince).append(";");		sbf.append("bankCity=").append(bankCity).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getBankName() {		return bankName;	}	public void setBankName(String bankName) {		this.bankName = bankName;	}	public String getBankBranchName() {		return bankBranchName;	}	public void setBankBranchName(String bankBranchName) {		this.bankBranchName = bankBranchName;	}	public String getOwnerName() {		return ownerName;	}	public void setOwnerName(String ownerName) {		this.ownerName = ownerName;	}	public String getBankNo() {		return bankNo;	}	public void setBankNo(String bankNo) {		this.bankNo = bankNo;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public Integer getIsDefault() {		return isDefault;	}	public void setIsDefault(Integer isDefault) {		this.isDefault = isDefault;	}	public String getBankProvince() {		return bankProvince;	}	public void setBankProvince(String bankProvince) {		this.bankProvince = bankProvince;	}	public String getBankCity() {		return bankCity;	}	public void setBankCity(String bankCity) {		this.bankCity = bankCity;	}
	
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
		DredgeBankCard other = (DredgeBankCard) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
