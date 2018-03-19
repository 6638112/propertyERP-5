package com.cnfantasia.wl.wechat.model;

/**
 * 付款前核对商品
 * 
 * @author wenfq
 * 
 */
public class Product4PrepareOrder {
	String productId;
	int productQty;
	String productSpecId;

	public String getProductSpecId() {
		return productSpecId;
	}

	public void setProductSpecId(String productSpecId) {
		this.productSpecId = productSpecId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
}
