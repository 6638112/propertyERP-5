package com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(订单的配送设置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class EbuyOrderDeliveryType implements Serializable{
/* */

/* 
public class EbuyOrderDeliveryType extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */
	public EbuyOrderDeliveryType(){
	}
	public EbuyOrderDeliveryType(EbuyOrderDeliveryType arg){
		this.id = arg.getId();
	}
	/**
	public EbuyOrderDeliveryType(BigInteger id,BigInteger tEbuyOrderFId,BigInteger tEbuySupplyMerchant,Integer deliveryType){
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
		EbuyOrderDeliveryType other = (EbuyOrderDeliveryType) obj;
		if (id == null) {
		return true;
	}
	
}