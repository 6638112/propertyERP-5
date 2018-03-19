/**   
* Filename:    IPrizeRuleService.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午3:22:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.service;

import java.util.List;

import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleDrawOnlinedaysEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.domainbase.prizeRuleConfig.entity.PrizeRuleConfig;

/**
 * Filename:    IPrizeRuleService.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午3:22:46
 * Description: 折扣规则服务类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public interface IPrizeRuleService {
	/**
	 * 获取抽奖规则配置列表
	 * @return
	 */
	public List<PrizeRuleConfig> getPrizeRuleConfigList();
	
	/**
	 * 获取抽奖区间配置列表
	 * @return
	 */
	public List<PrizeRuleGenerateAreaEntity> getPrizeRuleGenerateAreaList();
	
	/**
	 * 获取用在线时间对应抽奖概率配置信息
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedaysEntity> getprizeRuleDrawOnlinedaysList();
	
	/**
	 * 查询用户数对应的抽奖规则
	 * @return
	 */
	public List<PrizeRuleGenerateConfigEntity> getPrizeRuleGenerateUsercountList();
}
