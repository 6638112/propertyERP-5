/**   
* Filename:    SimpleDelivAddress.java   
* @version:    1.0  
* Create at:   2014年6月11日 上午2:18:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

/**
 * Filename:    SimpleDelivAddress.java
 * @version:    1.0.0
 * Create at:   2014年6月11日 上午2:18:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月11日       shiyl             1.0             1.0 Version
 */
public class SimpleDelivAddress {
	private String addressArea;
	private String addressDetail;
	private BigInteger targetId;
	public SimpleDelivAddress(){}
	public SimpleDelivAddress(String addressArea,String addressDetail,BigInteger targetId){
		this.addressArea = addressArea;
		this.addressDetail = addressDetail;
		this.targetId = targetId;
	}
	
	public String getAddressTotal(){
		if (addressArea == null) {
			return addressDetail;
		}
		return addressArea+addressDetail;
	}
	public String getAddressArea() {
		return addressArea;
	}
	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public BigInteger getTargetId() {
		return targetId;
	}
	public void setTargetId(BigInteger targetId) {
		this.targetId = targetId;
	}
	
}
