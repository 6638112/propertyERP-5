package com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.Integer;
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
	/**  */	private BigInteger id;	/** 订单id */	private BigInteger tEbuyOrderFId;	/** 供应商id */	private BigInteger tEbuySupplyMerchant;	/** 配送方式 */	private Integer deliveryType;
	public EbuyOrderDeliveryType(){
	}
	public EbuyOrderDeliveryType(EbuyOrderDeliveryType arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.tEbuySupplyMerchant = arg.gettEbuySupplyMerchant();		this.deliveryType = arg.getDeliveryType();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 订单id	 * @param tEbuySupplyMerchant 供应商id	 * @param deliveryType 配送方式	 */
	public EbuyOrderDeliveryType(BigInteger id,BigInteger tEbuyOrderFId,BigInteger tEbuySupplyMerchant,Integer deliveryType){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.tEbuySupplyMerchant = tEbuySupplyMerchant;		this.deliveryType = deliveryType;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("tEbuySupplyMerchant=").append(tEbuySupplyMerchant).append(";");		sbf.append("deliveryType=").append(deliveryType).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger gettEbuySupplyMerchant() {		return tEbuySupplyMerchant;	}	public void settEbuySupplyMerchant(BigInteger tEbuySupplyMerchant) {		this.tEbuySupplyMerchant = tEbuySupplyMerchant;	}	public Integer getDeliveryType() {		return deliveryType;	}	public void setDeliveryType(Integer deliveryType) {		this.deliveryType = deliveryType;	}
	
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
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
