/**   
* Filename:    ConfirmPayBillRusultEntity.java   
* @version:    1.0  
* Create at:   2015年8月12日 上午11:25:58   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.entity;

import java.math.BigInteger;

/**
 * Filename:    ConfirmPayBillRusultEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月12日 上午11:25:58
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月12日       shiyl             1.0             1.0 Version
 */
public class ConfirmPayBillRusultEntity {

	public ConfirmPayBillRusultEntity(){}
	
	public ConfirmPayBillRusultEntity(BigInteger orderId,Boolean isAutoPaySucc){
		this.orderId = orderId;
		this.isAutoPaySucc = isAutoPaySucc;
	}
	
	/**订单Id*/
	private BigInteger orderId;
	public BigInteger getOrderId() {
		return orderId;
	}
	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}
	
	/**是否自动支付成功*/
	private Boolean isAutoPaySucc;
	public Boolean getIsAutoPaySucc() {
		return isAutoPaySucc;
	}
	public void setIsAutoPaySucc(Boolean isAutoPaySucc) {
		this.isAutoPaySucc = isAutoPaySucc;
	}
	
	
}
