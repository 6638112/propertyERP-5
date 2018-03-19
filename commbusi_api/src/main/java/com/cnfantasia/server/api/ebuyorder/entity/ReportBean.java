/**
 * 
 */
package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;

/**
 * 类说明：报表输出对象
 * 
 * @author hunter
 * @since 2014年6月12日下午1:53:43
 */
public class ReportBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8909591198430977082L;

	private String id;
	private String supplyName;
	private String productName;
	private String typeName;
	private String price;
	private String total;
	private String priceUnit;
	private String orderNo;
	private String buyTime;
	private String orderStatus;
	private String logisticsName;
	private String logisticseCode;
	private String amount;
	private String factAmount;
	private String payStatus;
	private String payMethod;
	private String huaId;
	private String realName;
	private String mobile;
	private String deliveryFee;
	private String address;
	private String deliveryPhone;
	private String peopleName;
	private String payTime;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the supplyName
	 */
	public String getSupplyName() {
		return supplyName;
	}
	/**
	 * @param supplyName the supplyName to set
	 */
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the priceUnit
	 */
	public String getPriceUnit() {
		return priceUnit;
	}
	/**
	 * @param priceUnit the priceUnit to set
	 */
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the buyTime
	 */
	public String getBuyTime() {
		return buyTime;
	}
	/**
	 * @param buyTime the buyTime to set
	 */
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the logisticsName
	 */
	public String getLogisticsName() {
		return logisticsName;
	}
	/**
	 * @param logisticsName the logisticsName to set
	 */
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	/**
	 * @return the logisticseCode
	 */
	public String getLogisticseCode() {
		return logisticseCode;
	}
	/**
	 * @param logisticseCode the logisticseCode to set
	 */
	public void setLogisticseCode(String logisticseCode) {
		this.logisticseCode = logisticseCode;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the factAmount
	 */
	public String getFactAmount() {
		return factAmount;
	}
	/**
	 * @param factAmount the factAmount to set
	 */
	public void setFactAmount(String factAmount) {
		this.factAmount = factAmount;
	}
	/**
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}
	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * @return the payMethod
	 */
	public String getPayMethod() {
		return payMethod;
	}
	/**
	 * @param payMethod the payMethod to set
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	/**
	 * @return the huaId
	 */
	public String getHuaId() {
		return huaId;
	}
	/**
	 * @param huaId the huaId to set
	 */
	public void setHuaId(String huaId) {
		this.huaId = huaId;
	}
	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the deliveryFee
	 */
	public String getDeliveryFee() {
		return deliveryFee;
	}
	/**
	 * @param deliveryFee the deliveryFee to set
	 */
	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the deliveryPhone
	 */
	public String getDeliveryPhone() {
		return deliveryPhone;
	}
	/**
	 * @param deliveryPhone the deliveryPhone to set
	 */
	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}
	/**
	 * @return the peopleName
	 */
	public String getPeopleName() {
		return peopleName;
	}
	/**
	 * @param peopleName the peopleName to set
	 */
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	/**
	 * @return the payTime
	 */
	public String getPayTime() {
		return payTime;
	}
	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

}
