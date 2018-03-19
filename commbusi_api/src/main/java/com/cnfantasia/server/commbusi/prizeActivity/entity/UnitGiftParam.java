/**   
* Filename:    UnitGiftParam.java   
* @version:    1.0  
* Create at:   2015年9月15日 下午8:21:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    UnitGiftParam.java
 * @version:    1.0.0
 * Create at:   2015年9月15日 下午8:21:22
 * Description:单个小奖品
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月15日       shiyl             1.0             1.0 Version
 */
public class UnitGiftParam {
	private String uqMark;
	private String giftName;
	private String valueCode;
	
	public boolean isNull(){
		return StringUtils.isEmpty(uqMark)||StringUtils.isEmpty(giftName)||StringUtils.isEmpty(valueCode);
	}
	
	public UnitGiftParam(String uqMark,String giftName,String valueCode){
		this.uqMark = uqMark;
		this.giftName = giftName;
		this.valueCode = valueCode;
	}
	
	public String getUqMark() {
		return uqMark;
	}
	public void setUqMark(String uqMark) {
		this.uqMark = uqMark;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getValueCode() {
		return valueCode;
	}
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}
	
}
