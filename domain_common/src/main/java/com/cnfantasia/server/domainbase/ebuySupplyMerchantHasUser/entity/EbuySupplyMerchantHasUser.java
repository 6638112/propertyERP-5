package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户供应商关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuySupplyMerchantHasUser implements Serializable{
*/


public class EbuySupplyMerchantHasUser extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 供应商ID */	private String tMerchantId;	/** 用户ID */	private String tUserId;	/** 是否首次登录 */	private Integer isFirstLogin;	/**  */	private String updateTm;
	public EbuySupplyMerchantHasUser(){
	}
	/**	 * 	 * @param id 	 * @param tMerchantId 供应商ID	 * @param tUserId 用户ID	 * @param isFirstLogin 是否首次登录	 * @param updateTm 	 * @param sys0DelState 记录状态	 */
	public EbuySupplyMerchantHasUser(BigInteger id,String tMerchantId,String tUserId,Integer isFirstLogin,String updateTm,Integer sys0DelState){
		this.id = id;		this.tMerchantId = tMerchantId;		this.tUserId = tUserId;		this.isFirstLogin = isFirstLogin;		this.updateTm = updateTm;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tMerchantId=").append(tMerchantId).append(";");		sbf.append("tUserId=").append(tUserId).append(";");		sbf.append("isFirstLogin=").append(isFirstLogin).append(";");		sbf.append("updateTm=").append(updateTm).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String gettMerchantId() {		return tMerchantId;	}	public void settMerchantId(String tMerchantId) {		this.tMerchantId = tMerchantId;	}	public String gettUserId() {		return tUserId;	}	public void settUserId(String tUserId) {		this.tUserId = tUserId;	}	public Integer getIsFirstLogin() {		return isFirstLogin;	}	public void setIsFirstLogin(Integer isFirstLogin) {		this.isFirstLogin = isFirstLogin;	}	public String getUpdateTm() {		return updateTm;	}	public void setUpdateTm(String updateTm) {		this.updateTm = updateTm;	}
	
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
		EbuySupplyMerchantHasUser other = (EbuySupplyMerchantHasUser) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
