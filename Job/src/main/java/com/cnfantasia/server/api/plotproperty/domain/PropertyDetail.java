package com.cnfantasia.server.api.plotproperty.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class PropertyDetail implements Serializable {
	
	private static final long serialVersionUID = -767855952204097505L;

	private Integer isPay; //支付状态
	
	private Boolean isBuyFinance; //是否购买物业宝进行抵扣
	
	private BigDecimal sucPay; //付款金额
	
//	private Double discount; //折扣
	
//	private BigDecimal saveMoney; //节省金额
	
//	private String propertyPrice; //物业费
	
//	private String otherPrice; //其它费用
	
	private String payWay; //支付方式
	
	private String payTm; //支付时间
	
	private String proprietorName; //业主姓名
	
	private String addrBuilding; //
	
	private String addrRoom; //
	
	private String payLimiteStart;
	
	private String payLimiteEnd;

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Boolean getIsBuyFinance() {
		return isBuyFinance;
	}

	public void setIsBuyFinance(Boolean isBuyFinance) {
		this.isBuyFinance = isBuyFinance;
	}

	public BigDecimal getSucPay() {
		return sucPay;
	}

	public void setSucPay(BigDecimal sucPay) {
		this.sucPay = sucPay;
	}

//	public Double getDiscount() {
//		return discount;
//	}
//
//	public void setDiscount(Double discount) {
//		this.discount = discount;
//	}
//
//	public BigDecimal getSaveMoney() {
//		return saveMoney;
//	}
//
//	public void setSaveMoney(BigDecimal saveMoney) {
//		this.saveMoney = saveMoney;
//	}
//
//	public String getPropertyPrice() {
//		return propertyPrice;
//	}
//
//	public void setPropertyPrice(String propertyPrice) {
//		this.propertyPrice = propertyPrice;
//	}
//
//	public String getOtherPrice() {
//		return otherPrice;
//	}
//
//	public void setOtherPrice(String otherPrice) {
//		this.otherPrice = otherPrice;
//	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPayTm() {
		return payTm;
	}

	public void setPayTm(String payTm) {
		this.payTm = payTm;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public String getAddrBuilding() {
		return addrBuilding;
	}

	public void setAddrBuilding(String addrBuilding) {
		this.addrBuilding = addrBuilding;
	}

	public String getAddrRoom() {
		return addrRoom;
	}

	public void setAddrRoom(String addrRoom) {
		this.addrRoom = addrRoom;
	}

	public String getPayLimiteStart() {
		return payLimiteStart;
	}

	public void setPayLimiteStart(String payLimiteStart) {
		this.payLimiteStart = payLimiteStart;
	}

	public String getPayLimiteEnd() {
		return payLimiteEnd;
	}

	public void setPayLimiteEnd(String payLimiteEnd) {
		this.payLimiteEnd = payLimiteEnd;
	}
	
	/**优惠描述*/
	private String couponDesc;
	public String getCouponDesc() {
		return couponDesc;
	}
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	
	/**应缴费用详细*/
	private List<Map<String,Object>> feeDetail;
	public List<Map<String, Object>> getFeeDetail() {
		return feeDetail;
	}
	public void setFeeDetail(List<Map<String, Object>> feeDetail) {
		this.feeDetail = feeDetail;
	}
	
	/**账单显示名称*/
	private String topName;
	public String getTopName() {
		return topName;
	}
	public void setTopName(String topName) {
		this.topName = topName;
	}
	
}
