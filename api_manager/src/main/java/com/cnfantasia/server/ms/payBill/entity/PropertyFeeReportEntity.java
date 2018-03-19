package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigInteger;

/**
 * @ClassName: PropertyFeeReportEntity
 * @Date: 2017-06-08 16:26
 * @Auther: yanghua
 * @Description:(报表：费用项)
 */
public class PropertyFeeReportEntity {
	private BigInteger gbId;
	private BigInteger bId;
	private String thisName;
	/** 本期应收 */
	private Long totalAmt;
	/** 往期欠费 */
	private Long unpaidAmt;
	/** 用来区分是车禁、还是物业缴费，以此来跳转到不同的页面 */
	private String code;

	public BigInteger getbId() {
		return bId;
	}

	public void setbId(BigInteger bId) {
		this.bId = bId;
	}

	public String getThisName() {
		return thisName;
	}

	public void setThisName(String thisName) {
		this.thisName = thisName;
	}

	public Long getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Long totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Long getUnpaidAmt() {
		return unpaidAmt;
	}

	public void setUnpaidAmt(Long unpaidAmt) {
		this.unpaidAmt = unpaidAmt;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
