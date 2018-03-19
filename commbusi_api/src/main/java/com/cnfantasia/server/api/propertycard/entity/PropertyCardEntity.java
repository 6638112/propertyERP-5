package com.cnfantasia.server.api.propertycard.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.propertyCard.entity.PropertyCard;

public class PropertyCardEntity {
	/**  */
	private BigInteger id;
	/** 卡内金额 */
	private BigDecimal cardAmount;
	/** 优惠金额 */
	private BigDecimal discountAmt;
	/** 用户实付金额 */
	private BigDecimal realPayAmt;

	public PropertyCardEntity(PropertyCard propertyCard) {
		this.id = propertyCard.getId();
		this.cardAmount = PriceUtil.removeTailZero(PriceUtil.div100(propertyCard.getCardAmount()));
		this.discountAmt = PriceUtil.removeTailZero(PriceUtil.div100(propertyCard.getDiscountAmt()));
		this.realPayAmt = PriceUtil.removeTailZero(PriceUtil.div100(propertyCard.getRealPayAmt()));
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigDecimal getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(BigDecimal cardAmount) {
		this.cardAmount = cardAmount;
	}
	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}
	public BigDecimal getRealPayAmt() {
		return realPayAmt;
	}
	public void setRealPayAmt(BigDecimal realPayAmt) {
		this.realPayAmt = realPayAmt;
	}
	
}
