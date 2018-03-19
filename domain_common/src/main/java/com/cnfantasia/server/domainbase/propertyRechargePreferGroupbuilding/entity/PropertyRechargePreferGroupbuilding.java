package com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(物业预充值随机立减小区规则关联表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class PropertyRechargePreferGroupbuilding implements Serializable{
/* */

/* 
public class PropertyRechargePreferGroupbuilding extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tPropertyRechargePreferRuleFId;	/**  */	private BigInteger tGroupBuildingFId;
	public PropertyRechargePreferGroupbuilding(){
	}
	public PropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding arg){
		this.id = arg.getId();		this.tPropertyRechargePreferRuleFId = arg.gettPropertyRechargePreferRuleFId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
	}
	/**	 * 	 * @param id 	 * @param tPropertyRechargePreferRuleFId 	 * @param tGroupBuildingFId 	 */
	public PropertyRechargePreferGroupbuilding(BigInteger id,BigInteger tPropertyRechargePreferRuleFId,BigInteger tGroupBuildingFId){
		this.id = id;		this.tPropertyRechargePreferRuleFId = tPropertyRechargePreferRuleFId;		this.tGroupBuildingFId = tGroupBuildingFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tPropertyRechargePreferRuleFId=").append(tPropertyRechargePreferRuleFId).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettPropertyRechargePreferRuleFId() {		return tPropertyRechargePreferRuleFId;	}	public void settPropertyRechargePreferRuleFId(BigInteger tPropertyRechargePreferRuleFId) {		this.tPropertyRechargePreferRuleFId = tPropertyRechargePreferRuleFId;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}
	
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
		PropertyRechargePreferGroupbuilding other = (PropertyRechargePreferGroupbuilding) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
