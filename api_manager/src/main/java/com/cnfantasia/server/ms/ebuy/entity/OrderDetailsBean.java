package com.cnfantasia.server.ms.ebuy.entity;

import java.util.List;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class OrderDetailsBean extends OrderBean {
	
	private static final long serialVersionUID = -1783659027964720696L;

	private String delivPeopleName;
	
	private String delivPhone;
	
	private String delivAddressArea; //f_deliv_address_area
	
	private String delivAddressDetail;

	private String identifyNo;
	
	private List<OrderProductBean> productList;

	public String getDelivPeopleName() {
		return delivPeopleName;
	}

	public void setDelivPeopleName(String delivPeopleName) {
		this.delivPeopleName = delivPeopleName;
	}

	public String getDelivPhone() {
		return delivPhone;
	}

	public void setDelivPhone(String delivPhone) {
		this.delivPhone = delivPhone;
	}

	public String getDelivAddressArea() {
		return delivAddressArea;
	}

	public void setDelivAddressArea(String delivAddressArea) {
		this.delivAddressArea = delivAddressArea;
	}

	public String getDelivAddressDetail() {
		return delivAddressDetail;
	}

	public void setDelivAddressDetail(String delivAddressDetail) {
		this.delivAddressDetail = delivAddressDetail;
	}

	public List<OrderProductBean> getProductList() {
		return productList;
	}

	public void setProductList(List<OrderProductBean> productList) {
		this.productList = productList;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}

}
