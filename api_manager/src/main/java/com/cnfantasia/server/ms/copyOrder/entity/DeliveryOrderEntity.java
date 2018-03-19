package com.cnfantasia.server.ms.copyOrder.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * 运单信息
 * 
 * @author liyulin
 * @version 1.0 2017年6月9日 下午1:40:25
 */
public class DeliveryOrderEntity {
	
	/** 运单id */
	public BigInteger deliveryId;
	/** 订单号 */
	private String orderNo;
	/** 下单时间 */
	private String downTime;
	/***/
	private String linkName;
	/** 联系人手机 */
	private String mobile;
	/** 联系人姓名 */
	private String address;
	/** 订单信息 */
	private List<OrderInfoEntity> orderInfoList;

	public BigInteger getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(BigInteger deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderInfoEntity> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<OrderInfoEntity> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

}
