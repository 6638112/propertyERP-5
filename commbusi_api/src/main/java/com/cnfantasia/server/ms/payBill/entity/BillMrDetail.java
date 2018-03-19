package com.cnfantasia.server.ms.payBill.entity;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

/**
 * 抄水表详情
 * 
 * @author liyulin
 * @version 1.0 2017年2月10日 下午6:10:04
 */
public class BillMrDetail {
	private BigInteger propertyFeeDetailId;
	private BigDecimal totalPrice;
	private String name;
	private BigDecimal signalPrice;
	private String signalPriceStr;
	private BigDecimal priceUnitValue;
	private String startValue;
	private String endValue;
	private Integer type;
	private String mrName;

	public BigDecimal getTotalPrice() {
		return totalPrice.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSignalPrice() {
		if(signalPrice != null) {
			return delDotZero(BigDecimalUtil.div100(signalPrice, 4).toString());
		}
		return null;
	}

	public void setSignalPrice(BigDecimal signalPrice) {
		this.signalPrice = signalPrice;
	}

	public String getPriceUnitValue() {
		if(priceUnitValue != null) {
			return delDotZero(priceUnitValue.toString());
		}
		return null;
	}

	public void setPriceUnitValue(BigDecimal priceUnitValue) {
		this.priceUnitValue = priceUnitValue;
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

	public BigInteger getPropertyFeeDetailId() {
		return propertyFeeDetailId;
	}

	public void setPropertyFeeDetailId(BigInteger propertyFeeDetailId) {
		this.propertyFeeDetailId = propertyFeeDetailId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public String getMrName() {
		return mrName;
	}

	public void setMrName(String mrName) {
		this.mrName = mrName;
	}

	public String getSignalPriceStr() {
		return signalPriceStr;
	}

	public void setSignalPriceStr(String signalPriceStr) {
		this.signalPriceStr = signalPriceStr;
	}

	/**
	 * 去掉数字后面多余的0
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

}
