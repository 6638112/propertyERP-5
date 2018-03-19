package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * 账单信息
* Filename:    PayBillInfo.java
* @version:    1.0.0
* Create at:   2015年12月14日 下午5:20:07
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月14日       shiyl             1.0             1.0 Version
 */
public class PayBillInfo extends PayBill{
	private static final long serialVersionUID = 1L;
	
	/**账单费用详情*/
	private List<PropertyFeeDetail> propertyFeeDetailList;
	public List<PropertyFeeDetail> getPropertyFeeDetailList() {
		return propertyFeeDetailList;
	}
	public void setPropertyFeeDetailList(List<PropertyFeeDetail> propertyFeeDetailList) {
		this.propertyFeeDetailList = propertyFeeDetailList;
	}
	
}
