/**   
* Filename:    IPrizePool.java   
* @version:    1.0  
* Create at:   2014年7月1日 上午1:01:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.util.List;

import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.MultiPrizeArea;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;



/**
 * Filename:    IPrizePool.java
 * @version:    1.0.0
 * Create at:   2014年7月1日 上午1:01:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月1日       shiyl             1.0             1.0 Version
 */
public interface IPrizePool extends IPrizePoolBase{
	
	/**
	 * 查询奖池总数量
	 * @return
	 */
	public Long getPrizeCountTotal();
	
	/**
	 * 进行抽奖
	 * @param firstPrize
	 * @param onLineDays
	 * @param totalPrizeTimes
	 * @param currLeastPrizeRecord 当前用户最低折扣
	 * @return
	 */
	public PrizeResultDiscountEntity doPrize(Boolean firstPrize,int onLineDays,int totalPrizeTimes,PrizeRecord currLeastPrizeRecord);
	
	/**
	 * 查询奖池奖品信息
	 * @return
	 */
	public MultiPrizeArea getMultiPrizeArea();
	
	/**
	 * 获取当前用户数量的折扣配置规则
	 * @return
	 */
	public PrizeRuleGenerateConfigEntity getCurrPrizeRuleGenerateConfig();
	
	/**
	 * 回填折扣信息
	 * 返回退回的折扣数量
	 * @param allDiscountValueList
	 */
	public int insertPrizeData(List<DiscountValueEntity> allDiscountValueList);
	
//	/**
//	 * 更改奖池状态为停止，返回当前奖池的状态
//	 * @return
//	 */
//	public Integer change2Stop();
//	/**
//	 * 更改奖池状态为启动，返回当前奖池的状态
//	 * @return
//	 */
//	public Integer change2Doing();

}
