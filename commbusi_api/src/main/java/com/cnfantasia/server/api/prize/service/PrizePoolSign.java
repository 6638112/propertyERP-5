/**   
* Filename:    PrizePoolSign.java   
* @version:    1.0  
* Create at:   2015年1月16日 上午10:18:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.util.List;

import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleDict.ConfigType;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * Filename:    PrizePoolSign.java
 * @version:    1.0.0
 * Create at:   2015年1月16日 上午10:18:13
 * Description: 已签约小区的奖池
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月16日       shiyl             1.0             1.0 Version
 */
public class PrizePoolSign extends AbstractPrizePool{
	
	@Override
	public void initPrizeRuleGenerateConfig() {
//		{//根据用户数获取对应规则
//			Long userTotalCount = commonUserService.getUserTotalCount();//查询总的用户数
//			prizeRuleGenerateConfig = prizeRuleManager.getPrizeRuleGenerateUsercountConfig(userTotalCount,ConfigType.Time_Sign);
//		}
		{//根据时间获取对应规则
			String nowTime = dualDao.getNowTime();
			prizeRuleGenerateConfig = prizeRuleManager.getPrizeRuleGenerateTimeConfig(nowTime,ConfigType.Time_Sign);
		}
	}
	
	@Override
	public Long getPrizeCountTotal() {
		Long prizeTotalCount = commonPrizeService.getPrizeCountSigned().longValue();//查询总奖池数量
		return prizeTotalCount;
	}
	
	@Override
	protected List<PrizeRecord> getPrizeRecordCurrMonth() {
		//查询已签约小区的抽奖记录
		List<PrizeRecord> donePrizeRecList = prizeDao.selectPrizeRecordCurrMonthSign();
		return donePrizeRecList;
	}

}
