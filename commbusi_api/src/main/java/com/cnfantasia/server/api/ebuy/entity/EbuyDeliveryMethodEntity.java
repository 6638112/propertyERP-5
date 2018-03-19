/**   
* Filename:    EbuyDeliveryMethodEntity.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午4:49:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.api.ebuy.util.EbuyDeliveryMethodUtil;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;

/**
 * Filename:    EbuyDeliveryMethodEntity.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午4:49:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public class EbuyDeliveryMethodEntity extends EbuyDeliveryMethod{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 判断对应金额是否在当前区间内[start,end)
	 * @param priceAmount
	 * @return
	 */
	public boolean fetchIsContain(Long priceAmount){
		Long amountStart = this.getPriceAmountStart();
		Long amountEnd = this.getPriceAmountEnd();
		return EbuyDeliveryMethodUtil.fetchIsContain(priceAmount, amountStart, amountEnd);
	}
	
	
}
