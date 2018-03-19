/**   
* Filename:    PrizeRuleDrawOnlinedaysEntity.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午8:51:51   
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
import com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.entity.PrizeRuleDrawOnlinedays;

/**
 * Filename:    PrizeRuleDrawOnlinedaysEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午8:51:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleDrawOnlinedaysEntity extends PrizeRuleDrawOnlinedays{
	private static final long serialVersionUID = 1L;
	/**
	 * 判断对应在线天数是否在当前区间内
	 * @param onlineDays
	 * @return
	 */
	public boolean fetchIsContain(Long onlineDays){
		Long startDays = this.getStartDays();
		Long endDays = this.getEndDays();
		if(startDays!=null&&endDays!=null&&onlineDays>=startDays&&onlineDays<endDays){
			return true;
		}else{
			return false;
		}
	}
	
	public BigDecimal fetchAPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaAPercent(), 10000L);
	}
	public BigDecimal fetchBPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaBPercent(), 10000L);
	}
	public BigDecimal fetchCPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaCPercent(), 10000L);
	}
	public BigDecimal fetchDPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaDPercent(), 10000L);
	}
	public BigDecimal fetchEPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaEPercent(), 10000L);
	}
	
}
