package com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(送货单) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyDeliveryOrder implements Serializable{
*/


public class EbuyDeliveryOrder extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 订单Id */	private BigInteger tEbuyOrderFId;	/** 供应商Id */	private BigInteger tSupplyMerchantFId;	/** 运单付的现金（分） */	private Long amount;	/** 运单付的红包（分） */	private Long totalCoupon;	/** 快递公司Id */	private BigInteger tEbuyExpressCompanyFId;	/** 配送费用 */	private Long deliveryRealFee;	/** 和供应商结算的邮费 */	private Long deliverySettlementFee;	/** 配送方式Id */	private BigInteger deliveryId;	/** 用户选择的配送方式 */	private Integer userDeliveryType;	/** 购买者 */	private BigInteger buyerId;	/** 创建时间 */	private String createTime;	/** 发货时间 */	private String deliveryTime;	/** 确认收货时间 */	private String receiveTime;	/** 物流编号 */	private String deliveryNo;	/** 快递单号 */	private String expressNo;	/** 配送状态 */	private Integer status;	/** 配送状态 */	private Integer refundStatus;	/** 是否已计算收益 */	private Integer revenueStatus;	/** 配送单推送状态 */	private Integer pushStatus;	/** 结算申请状态 */	private Integer settleStatus;	/** 现金流生成状态 */	private Integer cashStatus;
	public EbuyDeliveryOrder(){
	}
	public EbuyDeliveryOrder(EbuyDeliveryOrder arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.tSupplyMerchantFId = arg.gettSupplyMerchantFId();		this.amount = arg.getAmount();		this.totalCoupon = arg.getTotalCoupon();		this.tEbuyExpressCompanyFId = arg.gettEbuyExpressCompanyFId();		this.deliveryRealFee = arg.getDeliveryRealFee();		this.deliverySettlementFee = arg.getDeliverySettlementFee();		this.deliveryId = arg.getDeliveryId();		this.userDeliveryType = arg.getUserDeliveryType();		this.buyerId = arg.getBuyerId();		this.createTime = arg.getCreateTime();		this.deliveryTime = arg.getDeliveryTime();		this.receiveTime = arg.getReceiveTime();		this.deliveryNo = arg.getDeliveryNo();		this.expressNo = arg.getExpressNo();		this.status = arg.getStatus();		this.refundStatus = arg.getRefundStatus();		this.revenueStatus = arg.getRevenueStatus();		this.pushStatus = arg.getPushStatus();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.settleStatus = arg.getSettleStatus();		this.cashStatus = arg.getCashStatus();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 订单Id	 * @param tSupplyMerchantFId 供应商Id	 * @param amount 运单付的现金（分）	 * @param totalCoupon 运单付的红包（分）	 * @param tEbuyExpressCompanyFId 快递公司Id	 * @param deliveryRealFee 配送费用	 * @param deliverySettlementFee 和供应商结算的邮费	 * @param deliveryId 配送方式Id	 * @param userDeliveryType 用户选择的配送方式	 * @param buyerId 购买者	 * @param createTime 创建时间	 * @param deliveryTime 发货时间	 * @param receiveTime 确认收货时间	 * @param deliveryNo 物流编号	 * @param expressNo 快递单号	 * @param status 配送状态	 * @param refundStatus 配送状态	 * @param revenueStatus 是否已计算收益	 * @param pushStatus 配送单推送状态	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param settleStatus 结算申请状态	 * @param cashStatus 现金流生成状态	 */
	public EbuyDeliveryOrder(BigInteger id,BigInteger tEbuyOrderFId,BigInteger tSupplyMerchantFId,Long amount,Long totalCoupon,BigInteger tEbuyExpressCompanyFId,Long deliveryRealFee,Long deliverySettlementFee,BigInteger deliveryId,Integer userDeliveryType,BigInteger buyerId,String createTime,String deliveryTime,String receiveTime,String deliveryNo,String expressNo,Integer status,Integer refundStatus,Integer revenueStatus,Integer pushStatus,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer settleStatus,Integer cashStatus){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.tSupplyMerchantFId = tSupplyMerchantFId;		this.amount = amount;		this.totalCoupon = totalCoupon;		this.tEbuyExpressCompanyFId = tEbuyExpressCompanyFId;		this.deliveryRealFee = deliveryRealFee;		this.deliverySettlementFee = deliverySettlementFee;		this.deliveryId = deliveryId;		this.userDeliveryType = userDeliveryType;		this.buyerId = buyerId;		this.createTime = createTime;		this.deliveryTime = deliveryTime;		this.receiveTime = receiveTime;		this.deliveryNo = deliveryNo;		this.expressNo = expressNo;		this.status = status;		this.refundStatus = refundStatus;		this.revenueStatus = revenueStatus;		this.pushStatus = pushStatus;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.settleStatus = settleStatus;		this.cashStatus = cashStatus;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("tSupplyMerchantFId=").append(tSupplyMerchantFId).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("totalCoupon=").append(totalCoupon).append(";");		sbf.append("tEbuyExpressCompanyFId=").append(tEbuyExpressCompanyFId).append(";");		sbf.append("deliveryRealFee=").append(deliveryRealFee).append(";");		sbf.append("deliverySettlementFee=").append(deliverySettlementFee).append(";");		sbf.append("deliveryId=").append(deliveryId).append(";");		sbf.append("userDeliveryType=").append(userDeliveryType).append(";");		sbf.append("buyerId=").append(buyerId).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("deliveryTime=").append(deliveryTime).append(";");		sbf.append("receiveTime=").append(receiveTime).append(";");		sbf.append("deliveryNo=").append(deliveryNo).append(";");		sbf.append("expressNo=").append(expressNo).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("refundStatus=").append(refundStatus).append(";");		sbf.append("revenueStatus=").append(revenueStatus).append(";");		sbf.append("pushStatus=").append(pushStatus).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("settleStatus=").append(settleStatus).append(";");		sbf.append("cashStatus=").append(cashStatus).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger gettSupplyMerchantFId() {		return tSupplyMerchantFId;	}	public void settSupplyMerchantFId(BigInteger tSupplyMerchantFId) {		this.tSupplyMerchantFId = tSupplyMerchantFId;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}	public Long getTotalCoupon() {		return totalCoupon;	}	public void setTotalCoupon(Long totalCoupon) {		this.totalCoupon = totalCoupon;	}	public BigInteger gettEbuyExpressCompanyFId() {		return tEbuyExpressCompanyFId;	}	public void settEbuyExpressCompanyFId(BigInteger tEbuyExpressCompanyFId) {		this.tEbuyExpressCompanyFId = tEbuyExpressCompanyFId;	}	public Long getDeliveryRealFee() {		return deliveryRealFee;	}	public void setDeliveryRealFee(Long deliveryRealFee) {		this.deliveryRealFee = deliveryRealFee;	}	public Long getDeliverySettlementFee() {		return deliverySettlementFee;	}	public void setDeliverySettlementFee(Long deliverySettlementFee) {		this.deliverySettlementFee = deliverySettlementFee;	}	public BigInteger getDeliveryId() {		return deliveryId;	}	public void setDeliveryId(BigInteger deliveryId) {		this.deliveryId = deliveryId;	}	public Integer getUserDeliveryType() {		return userDeliveryType;	}	public void setUserDeliveryType(Integer userDeliveryType) {		this.userDeliveryType = userDeliveryType;	}	public BigInteger getBuyerId() {		return buyerId;	}	public void setBuyerId(BigInteger buyerId) {		this.buyerId = buyerId;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getDeliveryTime() {		return deliveryTime;	}	public void setDeliveryTime(String deliveryTime) {		this.deliveryTime = deliveryTime;	}	public String getReceiveTime() {		return receiveTime;	}	public void setReceiveTime(String receiveTime) {		this.receiveTime = receiveTime;	}	public String getDeliveryNo() {		return deliveryNo;	}	public void setDeliveryNo(String deliveryNo) {		this.deliveryNo = deliveryNo;	}	public String getExpressNo() {		return expressNo;	}	public void setExpressNo(String expressNo) {		this.expressNo = expressNo;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Integer getRefundStatus() {		return refundStatus;	}	public void setRefundStatus(Integer refundStatus) {		this.refundStatus = refundStatus;	}	public Integer getRevenueStatus() {		return revenueStatus;	}	public void setRevenueStatus(Integer revenueStatus) {		this.revenueStatus = revenueStatus;	}	public Integer getPushStatus() {		return pushStatus;	}	public void setPushStatus(Integer pushStatus) {		this.pushStatus = pushStatus;	}	public Integer getSettleStatus() {		return settleStatus;	}	public void setSettleStatus(Integer settleStatus) {		this.settleStatus = settleStatus;	}	public Integer getCashStatus() {		return cashStatus;	}	public void setCashStatus(Integer cashStatus) {		this.cashStatus = cashStatus;	}
	
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
		EbuyDeliveryOrder other = (EbuyDeliveryOrder) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
