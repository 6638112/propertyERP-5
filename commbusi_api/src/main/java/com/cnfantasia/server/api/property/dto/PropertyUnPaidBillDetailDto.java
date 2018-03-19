package com.cnfantasia.server.api.property.dto;

/**
 * 物业未缴费账单详情
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:06:47
 */
public class PropertyUnPaidBillDetailDto extends BillBaseDto {

	private PropertyBillTailDto propertyBillTail;

	public PropertyBillTailDto getPropertyBillTail() {
		return propertyBillTail;
	}

	public void setPropertyBillTail(PropertyBillTailDto propertyBillTail) {
		this.propertyBillTail = propertyBillTail;
	}

}
