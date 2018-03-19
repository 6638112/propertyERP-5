/**   
* Filename:    PrizeRuleService.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午6:21:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.service;

import java.util.List;

import com.cnfantasia.server.api.prizeRule.dao.IPrizeRuleDao;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleDrawOnlinedaysEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.domainbase.prizeRuleConfig.dao.IPrizeRuleConfigBaseDao;
import com.cnfantasia.server.domainbase.prizeRuleConfig.entity.PrizeRuleConfig;

/**
 * Filename:    PrizeRuleService.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午6:21:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleService implements IPrizeRuleService{
	private IPrizeRuleDao prizeRuleDao;
	public void setPrizeRuleDao(IPrizeRuleDao prizeRuleDao) {
		this.prizeRuleDao = prizeRuleDao;
	}
	
	private IPrizeRuleConfigBaseDao prizeRuleConfigBaseDao;
	public void setPrizeRuleConfigBaseDao(IPrizeRuleConfigBaseDao prizeRuleConfigBaseDao) {
		this.prizeRuleConfigBaseDao = prizeRuleConfigBaseDao;
	}
	
//	private IPrizeRuleGenerateAreaBaseDao prizeRuleGenerateAreaBaseDao;
//	public void setPrizeRuleGenerateAreaBaseDao(IPrizeRuleGenerateAreaBaseDao prizeRuleGenerateAreaBaseDao) {
//		this.prizeRuleGenerateAreaBaseDao = prizeRuleGenerateAreaBaseDao;
//	}
//	
//	private IPrizeRuleDrawOnlinedaysBaseDao prizeRuleDrawOnlinedaysBaseDao;
//	public void setPrizeRuleDrawOnlinedaysBaseDao(IPrizeRuleDrawOnlinedaysBaseDao prizeRuleDrawOnlinedaysBaseDao) {
//		this.prizeRuleDrawOnlinedaysBaseDao = prizeRuleDrawOnlinedaysBaseDao;
//	}

	@Override
	public List<PrizeRuleConfig> getPrizeRuleConfigList() {
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigByCondition(null, false);
	}

	@Override
	public List<PrizeRuleGenerateAreaEntity> getPrizeRuleGenerateAreaList() {
		return prizeRuleDao.selectPrizeRuleGenerateAreaList();
	}

	@Override
	public List<PrizeRuleDrawOnlinedaysEntity> getprizeRuleDrawOnlinedaysList() {
		return prizeRuleDao.selectPrizeRuleDrawOnlinedaysList();
	}

	@Override
	public List<PrizeRuleGenerateConfigEntity> getPrizeRuleGenerateUsercountList() {
		return prizeRuleDao.selectPrizeRuleGenerateUsercountList();
	}
	
}
