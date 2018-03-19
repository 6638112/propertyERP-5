package com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(供应商配送包含的商品信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyDeliveryOrderProduct implements Serializable{
*/


public class EbuyDeliveryOrderProduct extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 送货单Id */	private BigInteger tEbuyDeliveryOrderFId;	/** 订单商品关系Id */	private BigInteger tEbuyOrderHasTEbuyProductFId;	/** 商品Id */	private BigInteger tEbuyProductFId;	/** 订单Id */	private BigInteger tEbuyOrderFId;	/** 供应商Id */	private BigInteger supplyMerchantId;	/** 1 */	private Integer opType;
	public EbuyDeliveryOrderProduct(){
	}
	public EbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct arg){
		this.id = arg.getId();		this.tEbuyDeliveryOrderFId = arg.gettEbuyDeliveryOrderFId();		this.tEbuyOrderHasTEbuyProductFId = arg.gettEbuyOrderHasTEbuyProductFId();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.supplyMerchantId = arg.getSupplyMerchantId();		this.opType = arg.getOpType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyDeliveryOrderFId 送货单Id	 * @param tEbuyOrderHasTEbuyProductFId 订单商品关系Id	 * @param tEbuyProductFId 商品Id	 * @param tEbuyOrderFId 订单Id	 * @param supplyMerchantId 供应商Id	 * @param opType 1	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyDeliveryOrderProduct(BigInteger id,BigInteger tEbuyDeliveryOrderFId,BigInteger tEbuyOrderHasTEbuyProductFId,BigInteger tEbuyProductFId,BigInteger tEbuyOrderFId,BigInteger supplyMerchantId,Integer opType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyDeliveryOrderFId = tEbuyDeliveryOrderFId;		this.tEbuyOrderHasTEbuyProductFId = tEbuyOrderHasTEbuyProductFId;		this.tEbuyProductFId = tEbuyProductFId;		this.tEbuyOrderFId = tEbuyOrderFId;		this.supplyMerchantId = supplyMerchantId;		this.opType = opType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyDeliveryOrderFId=").append(tEbuyDeliveryOrderFId).append(";");		sbf.append("tEbuyOrderHasTEbuyProductFId=").append(tEbuyOrderHasTEbuyProductFId).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("supplyMerchantId=").append(supplyMerchantId).append(";");		sbf.append("opType=").append(opType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyDeliveryOrderFId() {		return tEbuyDeliveryOrderFId;	}	public void settEbuyDeliveryOrderFId(BigInteger tEbuyDeliveryOrderFId) {		this.tEbuyDeliveryOrderFId = tEbuyDeliveryOrderFId;	}	public BigInteger gettEbuyOrderHasTEbuyProductFId() {		return tEbuyOrderHasTEbuyProductFId;	}	public void settEbuyOrderHasTEbuyProductFId(BigInteger tEbuyOrderHasTEbuyProductFId) {		this.tEbuyOrderHasTEbuyProductFId = tEbuyOrderHasTEbuyProductFId;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger getSupplyMerchantId() {		return supplyMerchantId;	}	public void setSupplyMerchantId(BigInteger supplyMerchantId) {		this.supplyMerchantId = supplyMerchantId;	}	public Integer getOpType() {		return opType;	}	public void setOpType(Integer opType) {		this.opType = opType;	}
	
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
		EbuyDeliveryOrderProduct other = (EbuyDeliveryOrderProduct) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
