package com.cnfantasia.server.domainbase.omsUserBankInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
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
	/** 用户编号 */
	public OmsUserBankInfo(){
	}
	public OmsUserBankInfo(OmsUserBankInfo arg){
		this.id = arg.getId();
	}
	/**
	public OmsUserBankInfo(BigInteger id,String bankName,String bankAccountName,String bankCardNo,String bankBranch,String bankProvince,String bankCity,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tOmsUserFId){
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
	}
	
	public BigInteger getId() {
	
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
		if (id == null) {
		return true;
	}
	
}