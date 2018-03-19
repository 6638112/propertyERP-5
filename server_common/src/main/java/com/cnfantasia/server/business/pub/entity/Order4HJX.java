package com.cnfantasia.server.business.pub.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 订单推送到海吉星，POST时所需的实体类
 * 
 * @author wenfq
 * 
 */

public class Order4HJX {
	/**
	 * 解放区的订单Id
	 */
	String orderId;

	/**
	 * 解放区的配送单号
	 */
	String edoId;

	/**
	 * 来源的订单号，解放区提供
	 */
	String source_order_tag;

	/**
	 * 订单所包含的商品
	 */
	List<Goods> goods;
	/**
	 * 收件人
	 */
	String receiver;
	/**
	 * 收件人电话
	 */
	String phone;
	/**
	 * 收件地址
	 */
	String address;

	public String getEdoId() {
		return edoId;
	}

	public void setEdoId(String edoId) {
		this.edoId = edoId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSource_order_tag() {
		return source_order_tag;
	}

	public void setSource_order_tag(String source_order_tag) {
		this.source_order_tag = source_order_tag;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public String getReceiver() {
		if (StringUtils.isEmpty(receiver))
			return "解放区用户";

		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}