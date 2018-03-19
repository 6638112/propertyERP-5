package com.cnfantasia.server.ms.ebuy.entity;

import com.cnfantasia.server.ms.ebuy.constant.EbuyDict;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class OrderExportBean extends OrderBean {
	
	private static final long serialVersionUID = 7849551020332310817L;

	private String delivPeopleName;
	
	private String delivPhone;
	
	private String delivAddressArea; //f_deliv_address_area
	
	private String delivAddressDetail;
	
	private String productId; //t_ebuy_product_f_id
	
	private String productName;
	
	private String productName2; //流量充值时用这个商品名称
	
	private String productType;
	
	private Integer qty;
	
	private Long price;
	
	private Long purchasePrice; //商品采购价
	
	private String priceUnit; // f_price_unit;
	
	private Long totalPrice;

	private Long deliveryOrderAmount;//运单支付的现金

	private Long deliveryOrderCoupon;//运单支付的优惠
	
	private String size; //尺寸
	
	private String colour; //颜色
	
	private String opName; //活动名称
	
	private Integer refundOrderStatus;//退款状态={"1":"部分退款","2":"全额退款"}
	
	private Integer refundPayStatus;//退款账户={"1":"微信支付","2":"支付宝","3":"银联支付","4":"粮票","9":"双乾支付"}　退款方式
	
	private Double refundFee; //退款金额
	
	private Integer tkStatus; //结算状态
	
	private Integer supplyType; //供应商属性
	
	private String flowNo; //流水号
	
	private String identifyNo;

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
		if(qty == null) {
			return 0;
		}
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

	public Long getPrice() {
		if(price == null) {
			return 0L;
		}
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getTotalPrice() {
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

	public Long getPurchasePrice() {
		if(purchasePrice == null) {
			return 0L;
		}
		return purchasePrice;
	}

	public void setPurchasePrice(Long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getRefundPayStatus() {
		return refundPayStatus;
	}
	
	public String getRefundPayStatusStr() {
		if(refundPayStatus == null) {
			return "";
		}
		return EbuyDict.PAYMETHOD_MAP.get(refundPayStatus.toString());
	}

	public void setRefundPayStatus(Integer refundPayStatus) {
		this.refundPayStatus = refundPayStatus;
	}

	public Double getRefundFee() {
//		if(refundFee == null) {
//			return 0.0D;
//		}
		return refundFee;
	}

	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
	}

	public Integer getTkStatus() {
		return tkStatus;
	}
	
	public String getTkStatusStr() {
		if(tkStatus == null) {
			return "";
		}
		switch (tkStatus.intValue()) { //EbuyDict
		case 1:
			return "未提款";
		case 2:
			return "提款中";
		case 3:
			return "提款中";
		default:
			return "";
		}
	}

	public void setTkStatus(Integer tkStatus) {
		this.tkStatus = tkStatus;
	}

	public Integer getSupplyType() {
		return supplyType;
	}
	
	public String getSupplyTypeStr() { //1:自营（全国）;2:附近商家;3:物业专供; 4自营（区域）
		if(supplyType == null) {
			return "自营";
		} else if(supplyType == 1 || supplyType == 4) {
			return "自营";
		} else if(supplyType == 2) {
			return "楼下店";
		} else if(supplyType == 3) {
			return "物业专供";
		}
		return "自营";
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public Integer getRefundOrderStatus() {
		return refundOrderStatus;
	}
	
	public String getRefundOrderStatusStr() {
		if(refundOrderStatus == null) {
			return "";
		} else if(refundOrderStatus == 1) {
			return "部分退款";
		} else {
			return "全额退款";
		}
	}

	public void setRefundOrderStatus(Integer refundOrderStatus) {
		this.refundOrderStatus = refundOrderStatus;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}

	@Override
	public Long getDeliveryOrderAmount() {
		return deliveryOrderAmount;
	}

	@Override
	public void setDeliveryOrderAmount(Long deliveryOrderAmount) {
		this.deliveryOrderAmount = deliveryOrderAmount;
	}

	@Override
	public Long getDeliveryOrderCoupon() {
		return deliveryOrderCoupon;
	}

	@Override
	public void setDeliveryOrderCoupon(Long deliveryOrderCoupon) {
		this.deliveryOrderCoupon = deliveryOrderCoupon;
	}
}
