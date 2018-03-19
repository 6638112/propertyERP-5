package com.cnfantasia.server.domainbase.propertyCardDeductionDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业代扣卡划扣详情) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyCardDeductionDetail implements Serializable{
*/


public class PropertyCardDeductionDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public PropertyCardDeductionDetail(){
	}
	public PropertyCardDeductionDetail(PropertyCardDeductionDetail arg){
		this.id = arg.getId();
	}
	/**
	public PropertyCardDeductionDetail(BigInteger id,BigInteger tUserHasPropertyCardFId,BigInteger tPayBillFId,BigInteger tEbuyOrderFId,Long payBillAmt,Long ptbtAmt,Long realPayAmt,String orderNo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
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
		PropertyCardDeductionDetail other = (PropertyCardDeductionDetail) obj;
		if (id == null) {
		return true;
	}
	
}