package com.cnfantasia.server.api.dredge.entity;

import com.cnfantasia.server.api.dredge.util.DredgeCombineStatusUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DredgeDetails implements Serializable {
	
	private static final long serialVersionUID = -2921518637003083572L;

	private Long id;
	
	private String provice;
	
	private String city;
	
	private String district;
	
	private String address;
	
	private String linkName;
	
	private String userLinkTel;
	
	private Long userId;
	
	private String userPhone;
	
	private Date addServiceTm;
	
	private String dredgeType1;
	
	private String dredgeType2;
	
	private String dredgeType3;
	
	private Date expectTm;
	
	private String serviceDesc;
	
	 private String serviceSupplier; //服务供应商
	
	private String picUrl;
	
	private Integer payType; //服务前付款1，服务后付款2
	
	//orderAmount = realPay + couponAmount;
	private BigDecimal orderAmount; //订单总费用：人工费+其它费+耗材费
	
	private BigDecimal realPay; //订单总实付
	
	private BigDecimal couponAmount; //订单总补贴
	
	private BigDecimal dredgeBillAmount;//维修单费用：人工费+其它费;耗材费=orderAmount - dredgeBillAmount
	
	private Integer payMethod;
	
	private String payMethodStr;
	
	private Date payTm;
	
	private String payFlow;
	
	private ProcessMaster process;

	private String billNo;
	
	private Integer status;
	
	private List<Product> productList;
	
	private List<AmountDetail> amountDetailList;
	
	private List<ProcessRecording> processRecordingList;
	
	private List<Comment> commentList;

	private Integer billType;
	// 1 待付款 2 待分配 3 待服务 4 已服务 5 已完成 6 已取消 7 退款中 8 已退款
	private Integer combineStatus;
	private String dredgeProductName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceSupplier() {
		return serviceSupplier;
	}

	public void setServiceSupplier(String serviceSupplier) {
		this.serviceSupplier = serviceSupplier;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		if(address != null && address.indexOf("区") < 4 && address.length() >= 6) {
			return null;
		}
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getUserLinkTel() {
		return userLinkTel;
	}

	public void setUserLinkTel(String userLinkTel) {
		this.userLinkTel = userLinkTel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Date getAddServiceTm() {
		return addServiceTm;
	}

	public void setAddServiceTm(Date addServiceTm) {
		this.addServiceTm = addServiceTm;
	}

	public String getDredgeType1() {
		return dredgeType1;
	}

	public void setDredgeType1(String dredgeType1) {
		this.dredgeType1 = dredgeType1;
	}

	public String getDredgeType2() {
		return dredgeType2;
	}

	public void setDredgeType2(String dredgeType2) {
		this.dredgeType2 = dredgeType2;
	}

	public String getDredgeType3() {
		return dredgeType3;
	}

	public void setDredgeType3(String dredgeType3) {
		this.dredgeType3 = dredgeType3;
	}

	public Date getExpectTm() {
		return expectTm;
	}

	public void setExpectTm(Date expectTm) {
		this.expectTm = expectTm;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getPicUrl() {
		return picUrl;
	}
	
	public List<String> getPicUrlList() {
		if(picUrl != null && picUrl.length() > 0) {
			String[] picUrlStrs = picUrl.split(";");
			List<String> picList = new ArrayList<String>();
			for(String picUrlStr: picUrlStrs) {
				if(picUrlStr != null && picUrlStr.length() > 0) {
					picList.add(picUrlStr);
				}
			}
			return picList;
		}
		return null;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public ProcessMaster getProcess() {
		return process;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getDredgeBillAmount() {
		return dredgeBillAmount;
	}

	public void setDredgeBillAmount(BigDecimal dredgeBillAmount) {
		this.dredgeBillAmount = dredgeBillAmount;
	}

	public Integer getPayMethod() {
		return payMethod;
	}
	
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	
	public String getPayMethodStr() {
		return payMethodStr;
	}
	
	public void setPayMethodStr(String payMethodStr) {
		this.payMethodStr = payMethodStr;
	}

	public Date getPayTm() {
		return payTm;
	}

	public void setPayTm(Date payTm) {
		this.payTm = payTm;
	}

	public String getPayFlow() {
		return payFlow;
	}

	public void setPayFlow(String payFlow) {
		this.payFlow = payFlow;
	}

	public void setProcess(ProcessMaster process) {
		this.process = process;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public List<AmountDetail> getAmountDetailList() {
		return amountDetailList;
	}

	public void setAmountDetailList(List<AmountDetail> amountDetailList) {
		this.amountDetailList = amountDetailList;
	}

	public List<ProcessRecording> getProcessRecordingList() {
		return processRecordingList;
	}

	public void setProcessRecordingList(List<ProcessRecording> processRecordingList) {
		this.processRecordingList = processRecordingList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBillType() {
		return billType;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	public Integer getCombineStatus() {
		return DredgeCombineStatusUtil.getCombineStatus(billType, status);
	}

	public void setCombineStatus(Integer combineStatus) {
		this.combineStatus = combineStatus;
	}

	public String getDredgeProductName() {
		return dredgeProductName;
	}

	public void setDredgeProductName(String dredgeProductName) {
		this.dredgeProductName = dredgeProductName;
	}

	public static class ProcessMaster {
		
		private Long masterUserId;
		
		private String masterName;
		
		private String masterTel;
		
		private Date acceptTm; //接单时间
		
		private Date expectWorkTm; //师傅期待上门时间

		public String getMasterName() {
			return masterName;
		}

		public void setMasterName(String masterName) {
			this.masterName = masterName;
		}

		public String getMasterTel() {
			return masterTel;
		}

		public void setMasterTel(String masterTel) {
			this.masterTel = masterTel;
		}

		public Long getMasterUserId() {
			return masterUserId;
		}

		public void setMasterUserId(Long masterUserId) {
			this.masterUserId = masterUserId;
		}

		public Date getAcceptTm() {
			return acceptTm;
		}

		public void setAcceptTm(Date acceptTm) {
			this.acceptTm = acceptTm;
		}

		public Date getExpectWorkTm() {
			return expectWorkTm;
		}

		public void setExpectWorkTm(Date expectWorkTm) {
			this.expectWorkTm = expectWorkTm;
		}

	}
	
	public static class AmountDetail {
		
		private Long adId;
		
		private String adName;
		
		private String adType;
		
		private BigDecimal payAmount;

		public Long getAdId() {
			return adId;
		}

		public void setAdId(Long adId) {
			this.adId = adId;
		}

		public String getAdName() {
			return adName;
		}

		public void setAdName(String adName) {
			this.adName = adName;
		}

		public String getAdType() {
			return adType;
		}

		public void setAdType(String adType) {
			this.adType = adType;
		}

		public BigDecimal getPayAmount() {
			return payAmount;
		}

		public void setPayAmount(BigDecimal payAmount) {
			this.payAmount = payAmount;
		}
		
	}
	
	public static class ProcessRecording {
		
		private Long prId;
		
		private Date recordingTm;
		
		private String processDesc;

		public Long getPrId() {
			return prId;
		}

		public void setPrId(Long prId) {
			this.prId = prId;
		}

		public Date getRecordingTm() {
			return recordingTm;
		}

		public void setRecordingTm(Date recordingTm) {
			this.recordingTm = recordingTm;
		}

		public String getProcessDesc() {
			return processDesc;
		}

		public void setProcessDesc(String processDesc) {
			this.processDesc = processDesc;
		}
		
	}
	
	public static class Comment {
		
		private Long commentId;
		
		private String name;
		
		private Integer value;

		public Long getCommentId() {
			return commentId;
		}

		public void setCommentId(Long commentId) {
			this.commentId = commentId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
		
	}
	
	public static class Product {
		
		private Long productId;
		
		private Integer buyCount;
		
		private BigDecimal buyPrice;
		
		private String priceUnit;
		
		private String productName;
		
		private String specName;

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public Integer getBuyCount() {
			return buyCount;
		}

		public void setBuyCount(Integer buyCount) {
			this.buyCount = buyCount;
		}

		public BigDecimal getBuyPrice() {
			return buyPrice;
		}

		public void setBuyPrice(BigDecimal buyPrice) {
			this.buyPrice = buyPrice;
		}

		public String getPriceUnit() {
			return priceUnit;
		}

		public void setPriceUnit(String priceUnit) {
			this.priceUnit = priceUnit;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getSpecName() {
			return specName;
		}

		public void setSpecName(String specName) {
			this.specName = specName;
		}
	}

}
