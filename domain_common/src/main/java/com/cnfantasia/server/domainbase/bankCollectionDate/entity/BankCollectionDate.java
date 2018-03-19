package com.cnfantasia.server.domainbase.bankCollectionDate.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业公司银行托收日期配置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class BankCollectionDate implements Serializable{
*/


public class BankCollectionDate extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 银行托收日（同一物业公司可设置多个） */	private Integer bankCollectionDate;	/** 物业公司ID */	private BigInteger tPropertyCompanyFId;
	public BankCollectionDate(){
	}
	public BankCollectionDate(BankCollectionDate arg){
		this.id = arg.getId();		this.bankCollectionDate = arg.getBankCollectionDate();		this.tPropertyCompanyFId = arg.gettPropertyCompanyFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param bankCollectionDate 银行托收日（同一物业公司可设置多个）	 * @param tPropertyCompanyFId 物业公司ID	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public BankCollectionDate(BigInteger id,Integer bankCollectionDate,BigInteger tPropertyCompanyFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.bankCollectionDate = bankCollectionDate;		this.tPropertyCompanyFId = tPropertyCompanyFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("bankCollectionDate=").append(bankCollectionDate).append(";");		sbf.append("tPropertyCompanyFId=").append(tPropertyCompanyFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getBankCollectionDate() {		return bankCollectionDate;	}	public void setBankCollectionDate(Integer bankCollectionDate) {		this.bankCollectionDate = bankCollectionDate;	}	public BigInteger gettPropertyCompanyFId() {		return tPropertyCompanyFId;	}	public void settPropertyCompanyFId(BigInteger tPropertyCompanyFId) {		this.tPropertyCompanyFId = tPropertyCompanyFId;	}
	
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
		BankCollectionDate other = (BankCollectionDate) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
