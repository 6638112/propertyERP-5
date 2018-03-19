package com.cnfantasia.server.business.pub.entity;


/**
 * 商品类
 * 
 * @author wenfq
 */
public class Goods {
	/**
	 * 商品的id，海吉星的ID
	 */
	String tag;
	/**
	 * 商品标题
	 */
	String title;
	/**
	 * 商品描述
	 */
	String description;
	/**
	 * 商品价格
	 */
	double price;
	/**
	 * 商品数量
	 */
	int quantity;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}