/**   
* Filename:    PrizePoolInfoEntitySimple.java   
* @version:    1.0  
* Create at:   2015年1月19日 上午10:17:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.entity;

import com.cnfantasia.server.api.prize.entity.MultiPrizeArea;

/**
 * Filename:    PrizePoolInfoEntitySimple.java
 * @version:    1.0.0
 * Create at:   2015年1月19日 上午10:17:23
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月19日       shiyl             1.0             1.0 Version
 */
public class PrizePoolInfoEntitySimple {

	private MultiPrizeArea multiPrizeArea;
	private PrizeRuleGenerateConfigEntity prizeRuleGenerateConfigEntity;
	public MultiPrizeArea getMultiPrizeArea() {
		return multiPrizeArea;
	}
	public void setMultiPrizeArea(MultiPrizeArea multiPrizeArea) {
		this.multiPrizeArea = multiPrizeArea;
	}
	
	public PrizeRuleGenerateConfigEntity getPrizeRuleGenerateConfigEntity() {
		return prizeRuleGenerateConfigEntity;
	}


	public void setPrizeRuleGenerateConfigEntity(PrizeRuleGenerateConfigEntity prizeRuleGenerateConfigEntity) {
		this.prizeRuleGenerateConfigEntity = prizeRuleGenerateConfigEntity;
	}
	public PrizePoolInfoEntitySimple(MultiPrizeArea multiPrizeArea,PrizeRuleGenerateConfigEntity prizeRuleGenerateConfigEntity){
		this.multiPrizeArea = multiPrizeArea;
		this.prizeRuleGenerateConfigEntity = prizeRuleGenerateConfigEntity;
	}
	

}
