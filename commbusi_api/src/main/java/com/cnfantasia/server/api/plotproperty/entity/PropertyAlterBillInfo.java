package com.cnfantasia.server.api.plotproperty.entity;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyAlterBillInfo implements Serializable {
	private BigInteger realRoomId;
	private BigDecimal latefeeAmount;
	private String latefeeStart;
	private String billMonthStart;
	private String periodMonths;
	private int propertyPeriodType;
	private int isPrefer;
	private String address;
	private String proprietorName;
	private List<AlterFeeItem> alterFeeItems;

	public BigInteger getRealRoomId() {
		return realRoomId;
	}

	public void setRealRoomId(BigInteger realRoomId) {
		this.realRoomId = realRoomId;
	}

	public BigDecimal getLatefeeAmount() {
		return latefeeAmount;
	}

	public void setLatefeeAmount(BigDecimal latefeeAmount) {
		this.latefeeAmount = latefeeAmount;
	}

	public String getLatefeeStart() {
		return latefeeStart;
	}

	public void setLatefeeStart(String latefeeStart) {
		this.latefeeStart = latefeeStart;
	}

	public String getBillMonthStart() {
		return billMonthStart;
	}

	public void setBillMonthStart(String billMonthStart) {
		this.billMonthStart = billMonthStart;
	}

	public String getPeriodMonths() {
		return periodMonths;
	}

	public void setPeriodMonths(String periodMonths) {
		this.periodMonths = periodMonths;
	}

	public int getPropertyPeriodType() {
		return propertyPeriodType;
	}

	public void setPropertyPeriodType(int propertyPeriodType) {
		this.propertyPeriodType = propertyPeriodType;
	}

	public int getIsPrefer() {
		return isPrefer;
	}

	public void setIsPrefer(int isPrefer) {
		this.isPrefer = isPrefer;
	}

	public List<AlterFeeItem> getAlterFeeItems() {
		return alterFeeItems;
	}

	public void setAlterFeeItems(List<AlterFeeItem> alterFeeItems) {
		this.alterFeeItems = alterFeeItems;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}
}
