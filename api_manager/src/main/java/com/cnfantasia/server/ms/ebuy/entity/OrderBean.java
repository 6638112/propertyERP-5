package com.cnfantasia.server.ms.ebuy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.ms.ebuy.constant.EbuyDict;
import com.cnfantasia.server.ms.ebuy.constant.EbuyDict.EbuyOrder_Status;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class OrderBean implements Serializable {
	
	private static final long serialVersionUID = 2329391573822890419L;
	
	private String id;
	
	private String orderNo; //订单号
	
	private Long delieveOrderId;
	
	private Date buyTime; //
	
	private Date payTime;
	
	private String huaId; //解放号
	
	private String realName; //联系人，即买家
	
	private String mobile; //买家电话
	
	private String supplyName; //供应商名字
	
	private String supplyPhone; //供应商电话
	
	private Integer status; //订单状态
	
	private Integer deliveryStatus; //配送单状态
	
	private Integer payStatus; //支付状态
	
	private String payMethod;	//支付方式
	
	private Long amount; //运单商品销售金额
	
	private Long amountPurchage; //运单采购金额
	
	private Long amoutPurchageOrder; //订单采购价
	
	private Long couponAmount; //优惠总金额
	
	private Long deliveryFee; //解放区运费

	private Long deliveryOrderAmount;//运单支付的现金

	private Long deliveryOrderCoupon;//运单支付的优惠
	
	private Long deliverySettleFee; //供应商运费

	private Integer userDeliveryType;//用户选择的提货方式，1 店铺默认，2 用户自提
	
	private Long expressId;
	
	private String expressName;
	
	private String expressNo; //界面中的物流编号即是后台的快递单号
	
	private String kuaidiCom;//快递100对应编码
	
	private Long orderAmount; //订单应付金额
	
	private Long orderDeliveryFee; //订单总运费
	
	private Integer refundStatus; //退款状态 '退款状态={"0":"未启动","1":"退款中","2":"退款成功","3":"退款失败"}',

	private OrderPushRecorder orderPushRecorder; //订单推送记录
	
	private Long refundOrderId;
	
	private Integer deliveryMethodType; //
	
	private List<OrderOpBean> opList;
	
	private String groupBuildingName;
	
	public OrderPushRecorder getOrderPushRecorder() {
		return orderPushRecorder;
	}

	public void setOrderPushRecorder(OrderPushRecorder orderPushRecorder) {
		this.orderPushRecorder = orderPushRecorder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getDelieveOrderId() {
		return delieveOrderId;
	}

	public void setDelieveOrderId(Long delieveOrderId) {
		this.delieveOrderId = delieveOrderId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getHuaId() {
		return huaId;
	}

	public void setHuaId(String huaId) {
		this.huaId = huaId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSupplyPhone() {
		return supplyPhone;
	}

	public void setSupplyPhone(String supplyPhone) {
		this.supplyPhone = supplyPhone;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Integer getStatus() {
		return status;
	}
	
	public String getStatusStr() {
		if(status == EbuyOrder_Status.DaiFaHuo && deliveryStatus == 2) {
			return "待收货";
		}
		return EbuyDict.STATUS_MAP.get(status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPayStatus() {
		return payStatus;
	}
	
	public String getPayStatusStr() {
		return EbuyDict.PAYSTATUS_MAP.get(payStatus);
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayMethod() {
		return payMethod;
	}
	
	public String getPayMethodStr() {
		return EbuyDict.PAYMETHOD_MAP.get(payMethod);
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public Long getAmount() {
		if(amount == null) {
			return 0L;
		}
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getCouponAmount() {
		if(couponAmount == null) {
			return 0L;
		}
		return couponAmount;
	}

	public void setCouponAmount(Long couponAmount) {
		this.couponAmount = couponAmount;
	}

	public Long getDeliveryFee() {
		if(deliveryFee == null) {
			return 0L;
		}
		return deliveryFee;
	}

	public void setDeliveryFee(Long deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Long getExpressId() {
		return expressId;
	}

	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Long getOrderDeliveryFee() {
		return orderDeliveryFee;
	}

	public void setOrderDeliveryFee(Long orderDeliveryFee) {
		this.orderDeliveryFee = orderDeliveryFee;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public List<OrderOpBean> getOpList() {
		return opList;
	}

	public void setOpList(List<OrderOpBean> opList) {
		this.opList = opList;
	}
	
	public String getKuaidiCom() {
		return kuaidiCom;
	}

	public void setKuaidiCom(String kuaidiCom) {
		this.kuaidiCom = kuaidiCom;
	}
	
	public String getOpNames() {
		String opName = "";
		if(opList != null) {
			for(OrderOpBean op : opList) {
				if(op != null && op.getOpName() != null && !opName.contains(op.getOpName())) {
					opName += op.getOpName() + ",";
				}
			}
		}
		if(opName.endsWith(",")) {
			opName = opName.substring(0, opName.length() - 1);
		}
		return opName;
	}

	public Integer getRefundStatus() {
		if(refundStatus == null) {
			return 0;
		}
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Long getRefundOrderId() {
		return refundOrderId;
	}

	public void setRefundOrderId(Long refundOrderId) {
		this.refundOrderId = refundOrderId;
	}

	public Long getDeliverySettleFee() {
		if(deliverySettleFee == null) {
			return 0L;
		}
		return deliverySettleFee;
	}

	public void setDeliverySettleFee(Long deliverySettleFee) {
		this.deliverySettleFee = deliverySettleFee;
	}

	public Long getAmountPurchage() {
		if(amountPurchage == null) {
			return 0L;
		}
		return amountPurchage;
	}

	public void setAmountPurchage(Long amountPurchage) {
		this.amountPurchage = amountPurchage;
	}

	public Long getAmoutPurchageOrder() {
		if(amoutPurchageOrder == null) {
			return 0L;
		}
		return amoutPurchageOrder;
	}

	public void setAmoutPurchageOrder(Long amoutPurchageOrder) {
		this.amoutPurchageOrder = amoutPurchageOrder;
	}

	public Integer getDeliveryMethodType() {
		return deliveryMethodType;
	}

	public void setDeliveryMethodType(Integer deliveryMethodType) {
		this.deliveryMethodType = deliveryMethodType;
	}

	public Long getDeliveryOrderAmount() {
		return deliveryOrderAmount;
	}

	public void setDeliveryOrderAmount(Long deliveryOrderAmount) {
		this.deliveryOrderAmount = deliveryOrderAmount;
	}

	public Long getDeliveryOrderCoupon() {
		return deliveryOrderCoupon;
	}

	public void setDeliveryOrderCoupon(Long deliveryOrderCoupon) {
		this.deliveryOrderCoupon = deliveryOrderCoupon;
	}

	public String getGroupBuildingName() {
		return groupBuildingName;
	}

	public void setGroupBuildingName(String groupBuildingName) {
		this.groupBuildingName = groupBuildingName;
	}

	public Integer getUserDeliveryType() {
		return userDeliveryType;
	}

	public void setUserDeliveryType(Integer userDeliveryType) {
		this.userDeliveryType = userDeliveryType;
	}
}
