package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class PrintFeeDetail {
	private BigInteger pfdId;
	/** 收费项目名称 */
	private String name;
	/** 月度数（开始） */
	private String startValue;
	/** 月读数（截至） */
	private String endValue;
	/** 金额 */
	private BigDecimal totalMoney;
	/** 单价 */
	private String signalPrice;
	/** 面积用量 */
	private String priceUnitValue;
	/** 用此字段==null来判断是否为水电费；水电费要显示本期、上期读书 */
	private BigInteger mpbrId;
	/** 欠费结转 */
	private BigDecimal unPaid;
	/** 是否为欠费 */
	private boolean owe;
	private List<String> mrPriceList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartValue() {
		return delDotZero(startValue);
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}

	public String getEndValue() {
		return delDotZero(endValue);
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getSignalPrice() {
		return delDotZero(signalPrice);
	}

	public void setSignalPrice(String signalPrice) {
		this.signalPrice = signalPrice;
	}

	public String getPriceUnitValue() {
		return delDotZero(priceUnitValue);
	}

	public void setPriceUnitValue(String priceUnitValue) {
		this.priceUnitValue = priceUnitValue;
	}

	public BigDecimal getUnPaid() {
		return unPaid;
	}

	public void setUnPaid(BigDecimal unPaid) {
		this.unPaid = unPaid;
	}

	/**
	 * 去掉数字后面多余的0
	 * 
	 * @param s
	 * @return
	 */
	private static final String delDotZero(String s) {
		if (StringUtils.isNotBlank(s)) {
			if (s.indexOf(".") > 0) {
				s = s.replaceAll("0+?$", "");//去掉多余的0
				s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
			}
		}
		return s;
	}

	public BigInteger getMpbrId() {
		return mpbrId;
	}

	public void setMpbrId(BigInteger mpbrId) {
		this.mpbrId = mpbrId;
	}

	public boolean isOwe() {
		return owe;
	}

	public void setOwe(boolean owe) {
		this.owe = owe;
	}

	public BigInteger getPfdId() {
		return pfdId;
	}

	public void setPfdId(BigInteger pfdId) {
		this.pfdId = pfdId;
	}

	public List<String> getMrPriceList() {
		return mrPriceList;
	}

	public void setMrPriceList(List<String> mrPriceList) {
		this.mrPriceList = mrPriceList;
	}

}
