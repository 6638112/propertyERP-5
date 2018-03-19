/**   
* Filename:    PrizeRuleLoaderService.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午9:22:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.api.prize.entity.MultiPrizeArea;
import com.cnfantasia.server.api.prize.service.IPrizePool;
import com.cnfantasia.server.api.prize.service.IPrizePoolGroup;
import com.cnfantasia.server.api.prizeRule.entity.PrizePoolGroupInfoEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizePoolInfoEntitySimple;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * Filename:    PrizeRuleLoaderService.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午9:22:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleLoaderService implements IPrizeRuleLoaderService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPrizePoolGroup prizePoolGroup;
	public void setPrizePoolGroup(IPrizePoolGroup prizePoolGroup) {
		this.prizePoolGroup = prizePoolGroup;
	}

	private IPrizeRuleManager prizeRuleManager;
	public void setPrizeRuleManager(IPrizeRuleManager prizeRuleManager) {
		this.prizeRuleManager = prizeRuleManager;
	}
	
	@Override
	public boolean reloadPrizeRule() {
		logger.info("Start to reloadPrizeRule.");
		//获取当前奖池状态
		Integer poolState = prizePoolGroup.getPoolState();
		try {
			//暂停奖池
			prizePoolGroup.setPoolState(PrizeDict.PrizePool_State.STOPPED);
			//重新加载配置
			prizeRuleManager.updParaValue();
			//重新加载奖池信息
			prizePoolGroup.reloadConfig();
//			//奖池信息开启奖池
//			prizePool.setPoolState(PrizeDict.PrizePool_State.DOING);
			logger.info("ReloadPrizeRule success.");
			return true;
		} catch (Exception e) {
			logger.info("ReloadPrizeRule failed.");
			throw new BusinessRuntimeException("prizeRuleLoader.reloadPrizeRule.failed",e);
		}finally{
			//设置回奖池状态
			prizePoolGroup.setPoolState(poolState);
			logger.info("ReloadPrizeRule finished.");
		}
		
	}

	@Override
	public PrizePoolGroupInfoEntity getPrizePoolInfo() {
		Integer poolState = prizePoolGroup.getPoolState();
		List<PrizePoolInfoEntitySimple> simpleList = new ArrayList<PrizePoolInfoEntitySimple>();
		List<IPrizePool> prizePoolList = prizePoolGroup.fetchPrizePoolList();
		for(IPrizePool tmpPool:prizePoolList){
			PrizeRuleGenerateConfigEntity prizeRuleGenerateConfigEntity = tmpPool.getCurrPrizeRuleGenerateConfig();
			MultiPrizeArea multiPrizeArea = tmpPool.getMultiPrizeArea();
			PrizePoolInfoEntitySimple tmp = new PrizePoolInfoEntitySimple(multiPrizeArea, prizeRuleGenerateConfigEntity);
			simpleList.add(tmp);
		}
		return new PrizePoolGroupInfoEntity(poolState,simpleList);
	}
	
}
