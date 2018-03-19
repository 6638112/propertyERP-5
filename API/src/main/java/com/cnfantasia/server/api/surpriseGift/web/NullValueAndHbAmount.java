/**   
* Filename:    NullValueAndHbAmount.java   
* @version:    1.0  
* Create at:   2015年7月3日 上午2:14:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月3日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.web;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;

/**
 * Filename:    NullValueAndHbAmount.java
 * @version:    1.0.0
 * Create at:   2015年7月3日 上午2:14:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月3日       shiyl             1.0             1.0 Version
 */
public class NullValueAndHbAmount {
	private Long hbAmount;
	private String nullValue;
	private PrizeSurpriseGift prizeSurpriseGift;
	public Long getHbAmount() {
		return hbAmount;
	}
	public void setHbAmount(Long hbAmount) {
		this.hbAmount = hbAmount;
	}
	public String getNullValue() {
		return nullValue;
	}
	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}
	
	public PrizeSurpriseGift getPrizeSurpriseGift() {
		return prizeSurpriseGift;
	}
	public void setPrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift) {
		this.prizeSurpriseGift = prizeSurpriseGift;
	}
	public NullValueAndHbAmount(){}
	public NullValueAndHbAmount(Long hbAmount,String nullValue,PrizeSurpriseGift prizeSurpriseGift){
		this.hbAmount = hbAmount;
		this.nullValue = nullValue;
		this.prizeSurpriseGift = prizeSurpriseGift;
	}
	
	public BigInteger getSupriseGiftId(){
		return prizeSurpriseGift==null?null:prizeSurpriseGift.getId();
	}
	
}
