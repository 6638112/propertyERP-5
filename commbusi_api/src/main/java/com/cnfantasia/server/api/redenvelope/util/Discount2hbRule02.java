/**   
* Filename:    Discount2hbRule02.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午7:38:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.util;

import com.cnfantasia.server.api.prizeRule.service.IPrizeRuleManager;

/**
 * Filename:    Discount2hbRule02.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午7:38:37
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public class Discount2hbRule02 extends AbstractDiscount2hbRule{
	private IPrizeRuleManager prizeRuleManager;
	public void setPrizeRuleManager(IPrizeRuleManager prizeRuleManager) {
		this.prizeRuleManager = prizeRuleManager;
	}
	
	@Override
	public Long getMoneyByDiscount(Double discount) {
		int n = (int) (discount*10)+1;
		Long ruleA1 = prizeRuleManager.getRuleA1();
		Long ruleD = prizeRuleManager.getRuleD();
		Long result = ruleA1-(n-1)*ruleD;
		return result;
	}
	
}
