package com.cnfantasia.server.api.plotproperty.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * 账单详情，用元做单位
 * @author wenfq 2016-01-18
 *
 */
public class PropertyFeeDetailWithYuan {
	private PropertyFeeDetail propertyFeeDetail;
	
	public PropertyFeeDetailWithYuan(PropertyFeeDetail propertyFeeDetail) {
		this.propertyFeeDetail = propertyFeeDetail;
	}
	
	public String getName() {
		return propertyFeeDetail.getName();
	}
	
	/**
	 * 按元返回费用
	 * @return
	 */
	public BigDecimal getTotalPriceYuan() {
		BigDecimal totalPriceFen = new BigDecimal(propertyFeeDetail.getTotalPrice());
		
		return totalPriceFen.divide(new BigDecimal(100));
	}

	public PropertyFeeDetail getPropertyFeeDetail() {
		return propertyFeeDetail;
	}

	public void setPropertyFeeDetail(PropertyFeeDetail propertyFeeDetail) {
		this.propertyFeeDetail = propertyFeeDetail;
	}
}
