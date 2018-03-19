package com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业收费项费用明细临时表（生成账单使用）) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyFeeDetailTemp implements Serializable{
*/


public class PropertyFeeDetailTemp extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public PropertyFeeDetailTemp(){
	}
	public PropertyFeeDetailTemp(PropertyFeeDetailTemp arg){
		this.id = arg.getId();
	}
	/**
	public PropertyFeeDetailTemp(BigInteger id,Integer type,String name,String mrName,Double price,Double totalAmount,Double signalPrice,String signalPriceStr,Long priceUnitValue,Double nowValue,Double priorValue,BigInteger tGbId,BigInteger tRealRoomId,BigInteger tBillCycleId,BigInteger targetId,BigInteger tMrStandardRoomId,Double multiplierTimes,Long lastUnpaid,String billMonthStart,String createBillMonth,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
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
		PropertyFeeDetailTemp other = (PropertyFeeDetailTemp) obj;
		if (id == null) {
		return true;
	}
	
}