package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 用来算一个订单有多个运单时的粮票分配，根据运单金额从大到小往下分配
 * @author yewj
 *
 */
public class EbuyDelivDiscount implements Serializable {
	
	private static final long serialVersionUID = 429075953706127197L;

	private BigInteger deliverId;
	
	private Long totalCouponAmount;  //订单总共用粮票优惠
	
	private Long prodPrice; //运单商品价格加运单运费
	
	private Long thisDeliveCoupon = 0L; //此运单使用粮票优惠

	public BigInteger getDeliverId() {
		return deliverId;
	}

	public void setDeliverId(BigInteger deliverId) {
		this.deliverId = deliverId;
	}

	public Long getTotalCouponAmount() {
		if(totalCouponAmount == null) {
			totalCouponAmount = 0L;
		}
		return totalCouponAmount;
	}

	public void setTotalCouponAmount(Long totalCouponAmount) {
		this.totalCouponAmount = totalCouponAmount;
	}

	public Long getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Long prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Long getThisDeliveCoupon() {
		if(thisDeliveCoupon == null) {
			thisDeliveCoupon = 0L;
		}
		return thisDeliveCoupon;
	}

	public void setThisDeliveCoupon(Long thisDeliveCoupon) {
		this.thisDeliveCoupon = thisDeliveCoupon;
	}

}
