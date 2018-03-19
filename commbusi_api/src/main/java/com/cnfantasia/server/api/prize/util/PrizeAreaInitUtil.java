/**   
* Filename:    PrizeAreaInitUtil.java   
* @version:    1.0  
* Create at:   2015年1月19日 上午3:44:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.PrizeArea;
import com.cnfantasia.server.api.prize.entity.SimplePrizeArea;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;

/**
 * Filename:    PrizeAreaInitUtil.java
 * @version:    1.0.0
 * Create at:   2015年1月19日 上午3:44:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月19日       shiyl             1.0             1.0 Version
 */
public class PrizeAreaInitUtil {
	private static Log logger = LogFactory.getLog(PrizeAreaInitUtil.class);
	
	public static PrizeArea generatePrizeArea(PrizeRuleGenerateAreaEntity areaConfigA,Long totalCount,Double percentA
			,List<Double> doneCountTotalList){
		PrizeArea prizeAreaA = null;
		
		DiscountValueEntity startA = new DiscountValueEntity(areaConfigA.getStartDiscount());
		DiscountValueEntity endA = new DiscountValueEntity(areaConfigA.getEndDiscount());
		
		Integer prizeAreaADoneCount = 0;
		for(Double discount:doneCountTotalList){
			if(discount.compareTo(DiscountValueEntity.unPrizedEntity.doubleValue())==0){
				continue;//如果是未中奖的折扣，跳过
			}
			if(discount>=startA.doubleValue()&&discount<endA.doubleValue()){
				prizeAreaADoneCount++;
			}
		}
		{
			int countA = (int)(totalCount.intValue()*percentA)-prizeAreaADoneCount;
			if(countA<0){countA=0;}
			prizeAreaA = new SimplePrizeArea(countA, startA,endA);
		}
		
		logger.info("startA="+startA+",endA="+endA+",percentA = "+percentA+",prizeAreaADoneCount = "+prizeAreaADoneCount);
		return prizeAreaA;
	}
	
}
