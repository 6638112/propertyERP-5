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
	/**  */
	public PropertyRechargePreferGroupbuilding(){
	}
	public PropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding arg){
		this.id = arg.getId();
	}
	/**
	public PropertyRechargePreferGroupbuilding(BigInteger id,BigInteger tPropertyRechargePreferRuleFId,BigInteger tGroupBuildingFId){
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
		PropertyRechargePreferGroupbuilding other = (PropertyRechargePreferGroupbuilding) obj;
		if (id == null) {
		return true;
	}
	
}