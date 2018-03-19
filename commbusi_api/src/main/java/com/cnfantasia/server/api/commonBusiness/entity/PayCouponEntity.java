/**   
* Filename:    PayCouponEntity.java   
* @version:    1.0  
* Create at:   2014年7月2日 上午3:21:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;

/**
 * Filename:    PayCouponEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月2日 上午3:21:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月2日       shiyl             1.0             1.0 Version
 */
public class PayCouponEntity extends PayCoupon{
	private static final long serialVersionUID = 1L;
	/**优惠详情列表*/
	private List<PayCouponDetailCommonEntity> payCouponDetailCommonEntityList;
	
	public List<PayCouponDetailCommonEntity> getPayCouponDetailCommonEntityList() {
		return payCouponDetailCommonEntityList;
	}
	public void setPayCouponDetailCommonEntityList(List<PayCouponDetailCommonEntity> payCouponDetailCommonEntityList) {
		this.payCouponDetailCommonEntityList = payCouponDetailCommonEntityList;
	}
	
	
	
}
