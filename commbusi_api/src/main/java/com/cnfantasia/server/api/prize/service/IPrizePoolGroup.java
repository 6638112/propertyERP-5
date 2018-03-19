/**   
* Filename:    IPrizePoolGroup.java   
* @version:    1.0  
* Create at:   2015年1月16日 上午6:56:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.util.List;

import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.MultiPrizeArea;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * Filename:    IPrizePoolGroup.java
 * @version:    1.0.0
 * Create at:   2015年1月16日 上午6:56:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月16日       shiyl             1.0             1.0 Version
 */
public interface IPrizePoolGroup extends IPrizePoolBase{
	/**
	 * 获取奖池状态
	 * @return
	 */
	public Integer getPoolState();
	
	/**
	 * @param poolState
	 */
	public void setPoolState(Integer poolState);
	
	/**
	 * 查询奖池总数量
	 * @return
	 */
	public Long getPrizeCountTotal(boolean signStatus);
	
	/**
	 * 进行抽奖
	 * @param onLineDays
	 * @param totalPrizeTimes
	 * @return
	 */
	public PrizeResultDiscountEntity doPrize(Boolean firstPrize,int onLineDays,int totalPrizeTimes,boolean signStatus,PrizeRecord currLeastPrizeRecord);
	
	/**
	 * 查询奖池奖品信息
	 * @return
	 */
	public MultiPrizeArea getMultiPrizeArea(boolean signStatus);
	
	/**
	 * 获取当前用户数量的折扣配置规则
	 * @return
	 */
	public PrizeRuleGenerateConfigEntity getCurrPrizeRuleGenerateConfig(boolean signStatus);
	
	/**
	 * 回填折扣信息
	 * 返回退回的折扣数量
	 * @param allDiscountValueList
	 */
	public int insertPrizeData(List<DiscountValueEntity> allDiscountValueList,boolean signStatus);

	/**
	 * 获取奖池列表
	 * @return
	 */
	public List<IPrizePool> fetchPrizePoolList();
	
	
}
