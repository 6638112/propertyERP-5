/**   
* Filename:    PayBillEntity.java   
* @version:    1.0  
* Create at:   2014年8月11日 上午6:25:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.entity;

import java.util.List;

import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * Filename:    PayBillEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月11日 上午6:25:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月11日       shiyl             1.0             1.0 Version
 */
public class PayBillEntity extends PayBill{
	private static final long serialVersionUID = 1L;

	//数据来源，0为系统账单，1为对接软件查询的数据
	private Integer dataFromType = 0;
	public Integer getDataFromType() {
		return dataFromType;
	}
	public void setDataFromType(Integer dataFromType) {
		this.dataFromType = dataFromType;
	}

	/**账单费用详情*/
	private List<PropertyFeeDetail> propertyFeeDetailList;
	public List<PropertyFeeDetail> getPropertyFeeDetailList() {
		return propertyFeeDetailList;
	}
	public void setPropertyFeeDetailList(List<PropertyFeeDetail> propertyFeeDetailList) {
		this.propertyFeeDetailList = propertyFeeDetailList;
	}
	
	/**
	 * 真实门牌信息
	 */
	private RealRoomEntity realRoomEntity;
	public RealRoomEntity getRealRoomEntity() {
		return realRoomEntity;
	}
	public void setRealRoomEntity(RealRoomEntity realRoomEntity) {
		this.realRoomEntity = realRoomEntity;
	}
	
	
}
