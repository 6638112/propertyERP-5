package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.lang.String;import java.math.BigDecimal;


public class EbuyFlowRecharge implements Serializable {
	
	private static final long serialVersionUID = 4158489993972494437L;

	private Long orderId;
	
	private Long productId;
	
	private String name;
	
	private String flow;
	
	private BigDecimal price;	
	private String phone;
	
	private Integer status = 0;
	
	private String retOrderId;
	
	private String callBackResult;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRetOrderId() {
		return retOrderId;
	}

	public void setRetOrderId(String retOrderId) {
		this.retOrderId = retOrderId;
	}

	public String getCallBackResult() {
		return callBackResult;
	}

	public void setCallBackResult(String callBackResult) {
		this.callBackResult = callBackResult;
	}

	public String getOrderType() {
		if(flow.contains("HF")) {
			return "3";
		} else {
			return "1";
		}
	}
}
