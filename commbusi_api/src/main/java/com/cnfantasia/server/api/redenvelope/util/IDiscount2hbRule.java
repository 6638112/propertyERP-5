/**   
* Filename:    IDiscount2hbRule.java   
* @version:    1.0  
* Create at:   2014年6月25日 上午7:25:07   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.util;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    IDiscount2hbRule.java
 * @version:    1.0.0
 * Create at:   2014年6月25日 上午7:25:07
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月25日       shiyl             1.0             1.0 Version
 */
public interface IDiscount2hbRule {
	/**
	 * 通过折扣兑换粮票
	 * @param discount 折扣
	 * @return 返回金额（分）
	 */
	Long getMoneyByDiscount(Double discount);
	/**
	 * 通过折扣兑换粮票
	 * @param registUserId 注册用户Id
	 * @param discount
	 * @return
	 */
	Long getMoneyByDiscount(BigInteger registUserId, Double discount);
	
	/**
	 * 获取用户当前默认门牌管理信息
	 * @param userId
	 * @return
	 */
	UserHasTRoom getDefaultUserHasTRoom(BigInteger userId);
	
	/**
	 * 通过折扣兑换粮票
	 * @param userId
	 * @param discount
	 * @return
	 */
	Long getMoneyByDiscount(UserHasTRoom userHasTRoom, Double discount);
}
