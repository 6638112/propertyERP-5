/**   
* Filename:    AbstractPrizePoolGroup.java   
* @version:    1.0  
* Create at:   2015年1月16日 上午6:57:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.util.List;

import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.api.prize.constant.PrizeDict.PrizePool_State;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * Filename:    AbstractPrizePoolGroup.java
 * @version:    1.0.0
 * Create at:   2015年1月16日 上午6:57:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月16日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractPrizePoolGroup implements IPrizePoolGroup{
//	private Log logger = LogFactory.getLog(getClass());
	
	/**奖池状态*/
	private Integer poolState = PrizePool_State.NOT_START;;
	/**奖池列表 缓存*/
	protected List<IPrizePool> prizePoolList;
	
	@Override
	public synchronized List<IPrizePool> fetchPrizePoolList() {
		return prizePoolList;
	}

	@Override
	public synchronized void init(){
		poolState = PrizePool_State.NOT_START;
		//初始化各个奖池的折扣定义规则
		initPrizeRuleGenerateConfig();
		for(IPrizePool tmpPool:prizePoolList){
			tmpPool.init();
		}
		poolState = PrizePool_State.DOING;
	}
	
	@Override
	public synchronized void initPrizeRuleGenerateConfig(){
		for(IPrizePool tmpPool:prizePoolList){
			tmpPool.initPrizeRuleGenerateConfig();
		}
	}
	
	@Override
	public synchronized void reloadConfig() {
		if(PrizeDict.PrizePool_State.DOING.compareTo(poolState)==0){
			throw new BusinessRuntimeException("prizePool.reloadConfig.isDoing.error");
		}else{
			for(IPrizePool tmpPool:prizePoolList){
				tmpPool.reloadConfig();
			}
		}
	}
	
	@Override
	public Integer getPoolState() {
		return poolState;
	}

	@Override
	public void setPoolState(Integer poolState) {
		this.poolState = poolState;
	}
	
/**根据时间*/	
//	private PrizeRuleGenerateConfigEntity getPrizeRuleGenerateConfigByTime(String nowTime){
//		PrizeRuleGenerateConfigEntity tmpConfig = null;
//		String nowTime = dualDao.getNowTime();
//		tmpConfig = prizeRuleManager.getPrizeRuleGenerateTimeConfig(nowTime);
//		return tmpConfig;
//	}
	
/**根据用户数*/
//	private PrizeRuleGenerateConfigEntity getPrizeRuleGenerateConfigByUserTotalCount(){
//		PrizeRuleGenerateConfigEntity tmpConfig = null;
//		Long userTotalCount = commonUserService.getUserTotalCount();//查询总的用户数
//		tmpConfig = prizeRuleManager.getPrizeRuleGenerateUsercountConfig(userTotalCount);
//		return tmpConfig;
//	}
	
	
}
