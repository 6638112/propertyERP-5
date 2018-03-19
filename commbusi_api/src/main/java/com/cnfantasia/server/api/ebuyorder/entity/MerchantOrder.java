package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.DateUtils;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class MerchantOrder implements Serializable {
	
	private static final long serialVersionUID = -2848754000819699998L;

	private Long id;
	
	private Long orderId;
	
	private String orderNo;
	
	//订单状态=={"1":"待付款","2":"已取消","3":"待发货","4":"待收货","5":"待评价","6":"已完成","99":"已删除"}
	private Integer status; //增加已放弃状态，此状态为7
	
	private Date payTime;
	
	private String deliverAddr;
	
	private String receivePeople;
	
	private String phone;
	
	private BigDecimal deliverMoney;
	
	private List<OrderProduct> productList;
	
	private BigDecimal tatalPrice;
	
	private double prodsPrice;
	
	private Integer totalQty;
	
	private long huaId; //购买者解放号

	private Integer userDeliveryType;//用户选择的提货方式，1 店铺默认，2 用户自提

	public long getHuaId() {
		return huaId;
	}

	public void setHuaId(long huaId) {
		this.huaId = huaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPayTime() {
		return payTime;
	}
	
	public String getPayTimeStr() {
		long time = System.currentTimeMillis() - payTime.getTime();
		time /= 60 * 1000;
		if(time >= 60) {
			return DateUtils.formatMinuteTime(payTime);
		} else if(time < 1){
			return "1分钟内";
		} else {
			return time + "分钟前";
		}
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getDeliverAddr() {
		return deliverAddr;
	}

	public void setDeliverAddr(String deliverAddr) {
		this.deliverAddr = deliverAddr;
	}

	public String getReceivePeople() {
		return receivePeople;
	}

	public void setReceivePeople(String receivePeople) {
		this.receivePeople = receivePeople;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<OrderProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<OrderProduct> productList) {
		this.productList = productList;
	}

	public BigDecimal getTatalPrice() {
		if(tatalPrice == null &&productList != null) {
			double temp = 0.0;
			for(OrderProduct prod : productList) {
				temp = BigDecimalUtil.add(prod.getProdPrice(), temp);
			}
			tatalPrice = BigDecimal.valueOf(temp);
		}
		return tatalPrice;
	}

	public void setTatalPrice(BigDecimal tatalPrice) {
		this.tatalPrice = tatalPrice;
	}

	public BigDecimal getDeliverMoney() {
		if(deliverMoney != null) {
			return BigDecimalUtil.div100(deliverMoney);
		}
		return deliverMoney;
	}

	public void setDeliverMoney(BigDecimal deliverMoney) {
		this.deliverMoney = deliverMoney;
	}

	public double getProdsPrice() {
		prodsPrice = 0;
		if(productList != null) {
			for(OrderProduct prod : productList) {
				prodsPrice = BigDecimalUtil.add(prod.getProdPrice(), prodsPrice);
			}
		}
		return prodsPrice;
	}

	public void setProdsPrice(double prodsPrice) {
		this.prodsPrice = prodsPrice;
	}

	public Integer getTotalQty() {
		totalQty = 0;
		if(productList != null) {
			for(OrderProduct prod : productList) {
				totalQty += prod.getQty();
			}
		}
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	public Integer getUserDeliveryType() {
		return userDeliveryType;
	}

	public void setUserDeliveryType(Integer userDeliveryType) {
		this.userDeliveryType = userDeliveryType;
	}
}
