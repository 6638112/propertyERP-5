/**   
* Filename:    IPrizePoolBase.java   
* @version:    1.0  
* Create at:   2015年1月19日 上午9:04:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;


/**
 * Filename:    IPrizePoolBase.java
 * @version:    1.0.0
 * Create at:   2015年1月19日 上午9:04:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月19日       shiyl             1.0             1.0 Version
 */
public interface IPrizePoolBase {

	/**
	 * 初始化奖池
	 */
	public void init();
	
	/**
	 * 初始化折扣奖池配置规则
	 */
	public void initPrizeRuleGenerateConfig();
	
	
	/**
	 * 刷新奖池配置
	 */
	public void reloadConfig();

}
