package com.cnfantasia.server.ms.refundOrder.entity;

import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

public class RefudOrderProductEntity extends EbuyRefundOrderProduct{
	private static final long serialVersionUID = 1L;
	private String refundproductName;
	/** 商品数量 */
	private Long productQty;
	/** 商品实际单价 */
	private Long productPrice;
	/** 商品分类 */
	private String productType;
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Long getProductQty() {
		return productQty;
	}
	public void setProductQty(Long productQty) {
		this.productQty = productQty;
	}
	public Long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}
	public String getRefundproductName() {
		return refundproductName;
	}
	public void setRefundproductName(String refundproductName) {
		this.refundproductName = refundproductName;
	}
}
