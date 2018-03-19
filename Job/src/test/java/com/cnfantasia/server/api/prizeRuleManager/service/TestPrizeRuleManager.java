/**   
* Filename:    TestPrizeRuleManager.java   
* @version:    1.0  
* Create at:   2014年9月1日 上午3:03:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRuleManager.service;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleDict.ConfigType;
import com.cnfantasia.server.api.prizeRule.service.IPrizeRuleManager;

/**
 * Filename:    TestPrizeRuleManager.java
 * @version:    1.0.0
 * Create at:   2014年9月1日 上午3:03:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月1日       shiyl             1.0             1.0 Version
 */
public class TestPrizeRuleManager extends BaseTest{
	
	@Test
	public void test01(){
		String date = "2015-7-11 16:31:49";
		IPrizeRuleManager prizeRuleManager = ctx.getBean("prizeRuleManager", IPrizeRuleManager.class);
//		Object res = prizeRuleManager.getPrizeRuleGenerateUsercountConfig(10L);
		{
			Object res = prizeRuleManager.getPrizeRuleGenerateTimeConfig(date, ConfigType.Time_Sign);
			System.out.println(JSON.toJSONString(res));
		}
		{
			Object res = prizeRuleManager.getPrizeRuleGenerateTimeConfig(date, ConfigType.Time_UnSign);
			System.out.println(JSON.toJSONString(res));
		}
		
	}
	
//	@Test
//	public void Discount2hbRule(){
//		IDiscount2hbRule Discount2hbRule02 = ctx.getBean("discount2hbRule", IDiscount2hbRule.class);
//		Object res = Discount2hbRule02.getMoneyByDiscount(3.5);//4355
//		System.out.println(JSON.toJSONString(res));
//	}
	
}
