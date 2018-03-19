package com.cnfantasia.server.domainbase.omsUserBankInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(OMS用户银行信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OmsUserBankInfo implements Serializable{
*/


public class OmsUserBankInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 用户编号 */	private BigInteger id;	/** 开户行 */	private String bankName;	/** 账户名称 */	private String bankAccountName;	/** 银行卡号 */	private String bankCardNo;	/** 开卡支行 */	private String bankBranch;	/** 支行所在省 */	private String bankProvince;	/** 支行所在市 */	private String bankCity;	/**  */	private BigInteger tOmsUserFId;
	public OmsUserBankInfo(){
	}
	public OmsUserBankInfo(OmsUserBankInfo arg){
		this.id = arg.getId();		this.bankName = arg.getBankName();		this.bankAccountName = arg.getBankAccountName();		this.bankCardNo = arg.getBankCardNo();		this.bankBranch = arg.getBankBranch();		this.bankProvince = arg.getBankProvince();		this.bankCity = arg.getBankCity();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tOmsUserFId = arg.gettOmsUserFId();
	}
	/**	 * 	 * @param id 用户编号	 * @param bankName 开户行	 * @param bankAccountName 账户名称	 * @param bankCardNo 银行卡号	 * @param bankBranch 开卡支行	 * @param bankProvince 支行所在省	 * @param bankCity 支行所在市	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tOmsUserFId 	 */
	public OmsUserBankInfo(BigInteger id,String bankName,String bankAccountName,String bankCardNo,String bankBranch,String bankProvince,String bankCity,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tOmsUserFId){
		this.id = id;		this.bankName = bankName;		this.bankAccountName = bankAccountName;		this.bankCardNo = bankCardNo;		this.bankBranch = bankBranch;		this.bankProvince = bankProvince;		this.bankCity = bankCity;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tOmsUserFId = tOmsUserFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("bankName=").append(bankName).append(";");		sbf.append("bankAccountName=").append(bankAccountName).append(";");		sbf.append("bankCardNo=").append(bankCardNo).append(";");		sbf.append("bankBranch=").append(bankBranch).append(";");		sbf.append("bankProvince=").append(bankProvince).append(";");		sbf.append("bankCity=").append(bankCity).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tOmsUserFId=").append(tOmsUserFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getBankName() {		return bankName;	}	public void setBankName(String bankName) {		this.bankName = bankName;	}	public String getBankAccountName() {		return bankAccountName;	}	public void setBankAccountName(String bankAccountName) {		this.bankAccountName = bankAccountName;	}	public String getBankCardNo() {		return bankCardNo;	}	public void setBankCardNo(String bankCardNo) {		this.bankCardNo = bankCardNo;	}	public String getBankBranch() {		return bankBranch;	}	public void setBankBranch(String bankBranch) {		this.bankBranch = bankBranch;	}	public String getBankProvince() {		return bankProvince;	}	public void setBankProvince(String bankProvince) {		this.bankProvince = bankProvince;	}	public String getBankCity() {		return bankCity;	}	public void setBankCity(String bankCity) {		this.bankCity = bankCity;	}	public BigInteger gettOmsUserFId() {		return tOmsUserFId;	}	public void settOmsUserFId(BigInteger tOmsUserFId) {		this.tOmsUserFId = tOmsUserFId;	}
	
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
		OmsUserBankInfo other = (OmsUserBankInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
