package com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(账单欠费信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class PropertyFeeDetailUnpaid implements Serializable{
/* */

/* 
public class PropertyFeeDetailUnpaid extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */
	public PropertyFeeDetailUnpaid(){
	}
	public PropertyFeeDetailUnpaid(PropertyFeeDetailUnpaid arg){
		this.id = arg.getId();
	}
	/**
	public PropertyFeeDetailUnpaid(BigInteger id,BigInteger tGbId,BigInteger tRealRoomId,BigInteger tUnpaidPayBillId,BigInteger tPayBillId){
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
		PropertyFeeDetailUnpaid other = (PropertyFeeDetailUnpaid) obj;
		if (id == null) {
		return true;
	}
	
}