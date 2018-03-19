/**   
* Filename:    IPrizeRuleDao.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午6:23:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.dao;

import java.util.List;

import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleDrawOnlinedaysEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;


/**
 * Filename:    IPrizeRuleDao.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午6:23:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public interface IPrizeRuleDao {
	
	/**
	 * 获取抽奖区间配置列表
	 * @return
	 */
	public List<PrizeRuleGenerateAreaEntity> selectPrizeRuleGenerateAreaList();
	
	/**
	 * 获取用在线时间对应抽奖概率配置信息
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedaysEntity> selectPrizeRuleDrawOnlinedaysList();
	
	/**
	 * 查询用户数对应的抽奖规则
	 * @return
	 */
	public List<PrizeRuleGenerateConfigEntity> selectPrizeRuleGenerateUsercountList();
}
