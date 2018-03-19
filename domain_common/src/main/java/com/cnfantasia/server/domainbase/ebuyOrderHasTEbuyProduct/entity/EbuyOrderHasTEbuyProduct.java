package com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(订单商品关系) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyOrderHasTEbuyProduct implements Serializable{
*/


public class EbuyOrderHasTEbuyProduct extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuyOrderFId;	/**  */	private BigInteger tEbuyProductFId;	/** 使用的商品规格Id */	private BigInteger tEbuyProductSpecFId;	/** 商品数量 */	private Long productQty;	/** 商品实际单价 */	private Long productPrice;	/** 商品实际积分价格 */	private Long productPricePoint;	/** 商品实际配送价格 */	private Long deliveryRealFee;	/** 配送方式Id */	private BigInteger deliveryId;	/** 供应商Id */	private BigInteger supplyMerchantId;	/** 1 */	private Integer opType;	/**  */	private Long purchasePrice;
	public EbuyOrderHasTEbuyProduct(){
	}
	public EbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.tEbuyProductSpecFId = arg.gettEbuyProductSpecFId();		this.productQty = arg.getProductQty();		this.productPrice = arg.getProductPrice();		this.productPricePoint = arg.getProductPricePoint();		this.deliveryRealFee = arg.getDeliveryRealFee();		this.deliveryId = arg.getDeliveryId();		this.supplyMerchantId = arg.getSupplyMerchantId();		this.opType = arg.getOpType();		this.purchasePrice = arg.getPurchasePrice();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 	 * @param tEbuyProductFId 	 * @param tEbuyProductSpecFId 使用的商品规格Id	 * @param productQty 商品数量	 * @param productPrice 商品实际单价	 * @param productPricePoint 商品实际积分价格	 * @param deliveryRealFee 商品实际配送价格	 * @param deliveryId 配送方式Id	 * @param supplyMerchantId 供应商Id	 * @param opType 1	 * @param purchasePrice 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyOrderHasTEbuyProduct(BigInteger id,BigInteger tEbuyOrderFId,BigInteger tEbuyProductFId,BigInteger tEbuyProductSpecFId,Long productQty,Long productPrice,Long productPricePoint,Long deliveryRealFee,BigInteger deliveryId,BigInteger supplyMerchantId,Integer opType,Long purchasePrice,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.tEbuyProductFId = tEbuyProductFId;		this.tEbuyProductSpecFId = tEbuyProductSpecFId;		this.productQty = productQty;		this.productPrice = productPrice;		this.productPricePoint = productPricePoint;		this.deliveryRealFee = deliveryRealFee;		this.deliveryId = deliveryId;		this.supplyMerchantId = supplyMerchantId;		this.opType = opType;		this.purchasePrice = purchasePrice;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("tEbuyProductSpecFId=").append(tEbuyProductSpecFId).append(";");		sbf.append("productQty=").append(productQty).append(";");		sbf.append("productPrice=").append(productPrice).append(";");		sbf.append("productPricePoint=").append(productPricePoint).append(";");		sbf.append("deliveryRealFee=").append(deliveryRealFee).append(";");		sbf.append("deliveryId=").append(deliveryId).append(";");		sbf.append("supplyMerchantId=").append(supplyMerchantId).append(";");		sbf.append("opType=").append(opType).append(";");		sbf.append("purchasePrice=").append(purchasePrice).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}	public BigInteger gettEbuyProductSpecFId() {		return tEbuyProductSpecFId;	}	public void settEbuyProductSpecFId(BigInteger tEbuyProductSpecFId) {		this.tEbuyProductSpecFId = tEbuyProductSpecFId;	}	public Long getProductQty() {		return productQty;	}	public void setProductQty(Long productQty) {		this.productQty = productQty;	}	public Long getProductPrice() {		return productPrice;	}	public void setProductPrice(Long productPrice) {		this.productPrice = productPrice;	}	public Long getProductPricePoint() {		return productPricePoint;	}	public void setProductPricePoint(Long productPricePoint) {		this.productPricePoint = productPricePoint;	}	public Long getDeliveryRealFee() {		return deliveryRealFee;	}	public void setDeliveryRealFee(Long deliveryRealFee) {		this.deliveryRealFee = deliveryRealFee;	}	public BigInteger getDeliveryId() {		return deliveryId;	}	public void setDeliveryId(BigInteger deliveryId) {		this.deliveryId = deliveryId;	}	public BigInteger getSupplyMerchantId() {		return supplyMerchantId;	}	public void setSupplyMerchantId(BigInteger supplyMerchantId) {		this.supplyMerchantId = supplyMerchantId;	}	public Integer getOpType() {		return opType;	}	public void setOpType(Integer opType) {		this.opType = opType;	}	public Long getPurchasePrice() {		return purchasePrice;	}	public void setPurchasePrice(Long purchasePrice) {		this.purchasePrice = purchasePrice;	}
	
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
		EbuyOrderHasTEbuyProduct other = (EbuyOrderHasTEbuyProduct) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
