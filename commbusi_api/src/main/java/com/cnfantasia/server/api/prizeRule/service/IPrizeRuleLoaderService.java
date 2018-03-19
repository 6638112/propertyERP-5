/**   
* Filename:    IPrizeRuleLoaderService.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午9:22:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.service;

import com.cnfantasia.server.api.prizeRule.entity.PrizePoolGroupInfoEntity;

/**
 * Filename:    IPrizeRuleLoaderService.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午9:22:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public interface IPrizeRuleLoaderService {
	
	/**
	 * 刷新抽奖配置的缓存，重新加载奖池信息
	 * @return
	 */
	public boolean reloadPrizeRule();
	
	/**
	 * 查询奖池信息
	 * @return
	 */
	public PrizePoolGroupInfoEntity getPrizePoolInfo();
}
