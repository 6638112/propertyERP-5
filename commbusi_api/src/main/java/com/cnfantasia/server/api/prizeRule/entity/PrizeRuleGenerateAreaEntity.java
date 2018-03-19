/**   
* Filename:    PrizeRuleGenerateAreaEntity.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午7:16:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.entity;

import java.math.BigDecimal;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.prizeRuleGenerateArea.entity.PrizeRuleGenerateArea;

/**
 * Filename:    PrizeRuleGenerateAreaEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午7:16:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleGenerateAreaEntity extends PrizeRuleGenerateArea{
	private static final long serialVersionUID = 1L;
	
	public BigDecimal fetchStartDiscountDiv1000(){
		return PriceUtil.divByDivNum(getStartDiscount(), 1000L);
	}
	
	public BigDecimal fetchEndDiscountDiv1000(){
		return PriceUtil.divByDivNum(getEndDiscount(), 1000L);
	}
	
//	public BigDecimal fetchPercentDiv10000(){
//		return PriceUtil.divByDivNum(getPercent(), 10000L);
//	}
	
	
}
