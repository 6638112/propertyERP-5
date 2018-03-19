/**   
* Filename:    MerchantResultEntity.java   
* @version:    1.0  
* Create at:   2014年11月26日 上午7:27:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity02;

import java.math.BigInteger;


/**
 * Filename:    MerchantResultEntity.java
 * @version:    1.0.0
 * Create at:   2014年11月26日 上午7:27:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月26日       shiyl             1.0             1.0 Version
 */
public class MerchantResultEntity extends MerchantResult{
	private BigInteger supplyTypeId;//商家所属类别

	public BigInteger getSupplyTypeId() {
		return supplyTypeId;
	}

	public void setSupplyTypeId(BigInteger supplyTypeId) {
		this.supplyTypeId = supplyTypeId;
	}
	
}
