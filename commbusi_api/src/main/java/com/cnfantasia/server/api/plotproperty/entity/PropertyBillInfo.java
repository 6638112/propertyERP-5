package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

public class PropertyBillInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**账单ID*/
	private BigInteger payBillId;
	/**订单id*/
	private BigInteger orderId;
	/**账单标题*/
	private String billTitle;//账单已出的就为账单月份
	/**账单实付金额（*/
	private Double billAmt;
	private long billAmtL;
	/**账单支付时间*/
	private String payTime;
	/**支付状态*/
	private int payStatus;
	/**优惠金额*/
	private Double preferentialAmt;
	private long preferentialAmtL;
	/**客户端支付状态*/
	private int clientPayStatus;
	/**订单list*/
	private List<EbuyOrder> orderList;
	
	public BigInteger getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(BigInteger payBillId) {
		this.payBillId = payBillId;
	}
	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}
	public Double getBillAmt() {
		billAmt = Double.valueOf(PriceUtil.div100s(billAmtL));
		return billAmt;
	}
	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public BigInteger getOrderId() {
		return orderId;
	}
	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public Double getPreferentialAmt() {
		preferentialAmt = Double.valueOf(PriceUtil.div100s(preferentialAmtL));
		return preferentialAmt;
	}
	public void setPreferentialAmt(Double preferentialAmt) {
		this.preferentialAmt = preferentialAmt;
	}
	public String getBillTitle() {
		return billTitle;
	}
	public int getClientPayStatus() {
		return clientPayStatus;
	}
	public void setClientPayStatus(int clientPayStatus) {
		this.clientPayStatus = clientPayStatus;
	}
	public List<EbuyOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<EbuyOrder> orderList) {
		this.orderList = orderList;
	}
	
}
