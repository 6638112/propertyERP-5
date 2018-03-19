package com.cnfantasia.server.ms.refundOrder.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

public class RefudOrderEntity extends EbuyRefundOrder{
	private static final long serialVersionUID = 1L;
	//退款单包含的商品信息
	private List<RefudOrderProductEntity> productList;
	//供应商
	private EbuySupplyMerchant ebuySupplyMerchant;
	//运单信息
	private EbuyDeliveryOrder deliveryOrder;
	
	private String orderNo;
	
	private String applyReason;
	
	private String payFlowNo;
	
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

	public String getPayFlowNo() {
		return payFlowNo;
	}
	public void setPayFlowNo(String payFlowNo) {
		this.payFlowNo = payFlowNo;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<RefudOrderProductEntity> getProductList() {
		return productList;
	}
	public void setProductList(List<RefudOrderProductEntity> productList) {
		this.productList = productList;
	}
	public EbuySupplyMerchant getEbuySupplyMerchant() {
		return ebuySupplyMerchant;
	}
	public void setEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant) {
		this.ebuySupplyMerchant = ebuySupplyMerchant;
	}
	public EbuyDeliveryOrder getDeliveryOrder() {
		return deliveryOrder;
	}
	public void setDeliveryOrder(EbuyDeliveryOrder deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}
	public String getAddMan() {
		return addMan;
	}
	public void setAddMan(String addMan) {
		this.addMan = addMan;
	}
	public String getUpdateMan() {
		return updateMan;
	}
	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
	
}
