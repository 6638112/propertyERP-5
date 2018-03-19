/**   
* Filename:    SuppluContectInfoEntity.java   
* @version:    1.0  
* Create at:   2014年11月28日 上午8:47:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity02;

import java.math.BigInteger;

/**
 * Filename:    SuppluContectInfoEntity.java
 * @version:    1.0.0
 * Create at:   2014年11月28日 上午8:47:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月28日       shiyl             1.0             1.0 Version
 */
public class SuppluContectInfoEntity {
	
	private BigInteger supplyId;
	private String phone;
	public BigInteger getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(BigInteger supplyId) {
		this.supplyId = supplyId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
