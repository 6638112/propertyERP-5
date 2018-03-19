package com.cnfantasia.server.commbusi.microblogQueue.entity;

import java.math.BigInteger;

/**
 * 小区最低折扣
 * @author shiyl
 *
 */
public class GBLeastDiscount {
	/**小区Id*/
	private BigInteger gbId;
	/**小区名称*/
	private String gbName;
	/**最低折扣Id*/
	private BigInteger discountId;
	/**最低折扣取值*/
	private Double discount;
	/**最低折扣抽取人*/
	private BigInteger userId;
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public BigInteger getDiscountId() {
		return discountId;
	}
	public void setDiscountId(BigInteger discountId) {
		this.discountId = discountId;
	}
	
}
