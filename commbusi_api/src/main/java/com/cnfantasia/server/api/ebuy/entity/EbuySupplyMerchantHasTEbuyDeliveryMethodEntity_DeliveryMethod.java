/**   
* Filename:    EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod.java   
* @version:    1.0  
* Create at:   2014年6月9日 下午2:14:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.entity.EbuySupplyMerchantHasTEbuyDeliveryMethod;

/**
 * Filename:    EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod.java
 * @version:    1.0.0
 * Create at:   2014年6月9日 下午2:14:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月9日       shiyl             1.0             1.0 Version
 */
public class EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod extends EbuySupplyMerchantHasTEbuyDeliveryMethod{
	private static final long serialVersionUID = 1L;
	/**配送方式*/
	private EbuyDeliveryMethod ebuyDeliveryMethod;
	public EbuyDeliveryMethod getEbuyDeliveryMethod() {
		return ebuyDeliveryMethod;
	}
	public void setEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod) {
		this.ebuyDeliveryMethod = ebuyDeliveryMethod;
	}
	@Override
	public BigInteger gettEbuyDeliveryMethodFId() {
		if(ebuyDeliveryMethod==null){
			return null;
		}
		return ebuyDeliveryMethod.getId();
	}
	@Override
	public void settEbuyDeliveryMethodFId(BigInteger tEbuyDeliveryMethodFId) {
		if(ebuyDeliveryMethod==null){
			ebuyDeliveryMethod = new EbuyDeliveryMethod();
		}
		ebuyDeliveryMethod.setId(tEbuyDeliveryMethodFId);
	}
	
}
