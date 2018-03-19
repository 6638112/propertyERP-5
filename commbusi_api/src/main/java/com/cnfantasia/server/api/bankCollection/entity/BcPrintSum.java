package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 银行出盘明细打印（按楼栋、小区编号）
 * 
 * @author liyulin
 * @version 1.0 2017年5月22日 上午11:40:31
 */
public class BcPrintSum {

	/** 小区id */
	private BigInteger gbId;
	/** 楼栋id */
	private BigInteger bId;
	/** 楼栋号、小区名称 */
	private String name;
	/** 所含条数 */
	private Integer count = 1;
	/** 出盘金额（单位：元） */
	private BigDecimal totalOutAmount = BigDecimal.ZERO;
	/** 划款金额（单位：元） */
	private BigDecimal totalDealAmount = BigDecimal.ZERO;
	/** 划款滞纳金（单位：元） */
	private BigDecimal totalDealLateAmount = BigDecimal.ZERO;

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public BigInteger getbId() {
		return bId;
	}

	public void setbId(BigInteger bId) {
		this.bId = bId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getTotalOutAmount() {
		return totalOutAmount;
	}

	public void setTotalOutAmount(BigDecimal totalOutAmount) {
		this.totalOutAmount = totalOutAmount;
	}

	public BigDecimal getTotalDealAmount() {
		return totalDealAmount;
	}

	public void setTotalDealAmount(BigDecimal totalDealAmount) {
		this.totalDealAmount = totalDealAmount;
	}

	public BigDecimal getTotalDealLateAmount() {
		return totalDealLateAmount;
	}

	public void setTotalDealLateAmount(BigDecimal totalDealLateAmount) {
		this.totalDealLateAmount = totalDealLateAmount;
	}

}
