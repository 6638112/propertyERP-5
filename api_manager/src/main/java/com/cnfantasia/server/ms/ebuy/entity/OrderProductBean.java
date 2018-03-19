package com.cnfantasia.server.ms.ebuy.entity;

import java.io.Serializable;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class OrderProductBean implements Serializable {
	
	private static final long serialVersionUID = 8180088629213974306L;

	private String id;
	
	private String productName;
	
	private String productType;
	
	private Integer qty;
	
	private Long price;
	
	private String priceUnit; // f_price_unit;
	
	private String forDisplay; //界面中显示有总计，此字段仅为变通的界面显示效果而已。
	
	private Long totalPrice;
	
	private String size; //尺寸
	
	private String colour; //颜色
	
	private String opName; //活动名称，如新用户专享
	
	private String productName2; //流量充值时商品名从别处取的
	
	public OrderProductBean() {
		super();
	}

	public OrderProductBean(String forDisplay) {
		super();
		this.forDisplay = forDisplay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public String getForDisplay() {
		return forDisplay;
	}

	public void setForDisplay(String forDisplay) {
		this.forDisplay = forDisplay;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getTotalPrice() {
		if(totalPrice == null) {
			totalPrice = price*qty;
		}
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getProductName2() {
		return productName2;
	}

	public void setProductName2(String productName2) {
		this.productName2 = productName2;
	}
	
}
