package com.cnfantasia.server.ms.copyOrder.entity;

/**
 * 订单信息
 * 
 * @author liyulin
 * @version 1.0 2017年6月9日 下午1:39:05
 */
public class OrderInfoEntity {
	
	/** 商品名称 */
	private String productName;
	/** 商品数量 */
	private Integer num;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
