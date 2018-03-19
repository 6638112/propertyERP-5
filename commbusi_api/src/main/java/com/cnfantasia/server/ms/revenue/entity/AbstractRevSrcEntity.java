package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * 收益来源信息抽象类
* Filename:    AbstractRevSrcEntity.java
* @version:    1.0.0
* Create at:   2015年11月19日 上午11:45:33
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractRevSrcEntity<G> {
	public abstract BigInteger getUniqueId();
	public abstract G getPayAmount();
	public abstract BigDecimal getSrcMoney();
	public abstract String getRevActiveTime();
	
	/**平台补贴额*/
	public abstract BigDecimal getAmountPtbt();
	/**用户实际缴费额*/
	public abstract BigDecimal getAmountUsrReal();
	
	/**
	 * 获取支付成功的流水号
	 * @return
	 */
	public String getPayFlowNo(){
		return "";
	}
	/**
	 * 支付方式=={"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":"微信轻应用支付","7":"纯优惠券支付"}
	 * @return
	 */
	public Integer getPayMethod(){
		return null;
	}
	
	private BigInteger gbId;
	
	private BigInteger propManagerId;
	
	public BigInteger getGbId() {
		return gbId;
	}
	
	public BigInteger getPropManagerId() {
		return propManagerId;
	}
	
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	
	public void setPropManagerId(BigInteger propManagerId) {
		this.propManagerId = propManagerId;
	}
	
	/**实付（用户代扣卡续费处理）*/
	private BigDecimal wyAmountUsrReal;
	/**补贴（用户代扣卡续费处理）*/
	private BigDecimal wyAmountPtbt;
	/**缴费方式=={"1":"用户在线支付","2":"物业公司手工标记","3":"物业代扣卡划扣","4":"物业宝抵扣"}*/
	private BigInteger payWay;
	public BigDecimal getWyAmountUsrReal() {
		return PriceUtil.div100(wyAmountUsrReal);
	}

	public void setWyAmountUsrReal(BigDecimal wyAmountUsrReal) {
		this.wyAmountUsrReal = wyAmountUsrReal;
	}

	public BigDecimal getWyAmountPtbt() {
		return PriceUtil.div100(wyAmountPtbt);
	}

	public void setWyAmountPtbt(BigDecimal wyAmountPtbt) {
		this.wyAmountPtbt = wyAmountPtbt;
	}
	
	public BigInteger getPayWay() {
		return payWay;
	}
	
	public void setPayWay(BigInteger payWay) {
		this.payWay = payWay;
	}
	
}
