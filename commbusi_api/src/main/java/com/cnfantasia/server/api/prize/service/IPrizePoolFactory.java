/**   
* Filename:    IPrizePoolFactory.java   
* @version:    1.0  
* Create at:   2014年7月1日 上午1:03:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntityExtend;

/**
 * Filename:    IPrizePoolFactory.java
 * @version:    1.0.0
 * Create at:   2014年7月1日 上午1:03:23
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月1日       shiyl             1.0             1.0 Version
 */
public interface IPrizePoolFactory{
	
	/**
	 * 直接抽奖
	 * @param userId
	 * @param userType
	 * @param requestClientInfoEntity
	 * @return
	 */
	public PrizeResultDiscountEntity doPrize(BigInteger userId,Integer userType,RequestClientInfoEntity requestClientInfoEntity);
	
	/**
	 * 抽奖结果存入临时表
	 * @param userId
	 * @param userType
	 * @param requestClientInfoEntity
	 * @param prizeCheckParamEntity
	 * @return
	 */
	public PrizeResultDiscountEntityExtend doPrize(BigInteger userId,Integer userType,RequestClientInfoEntity requestClientInfoEntity, PrizeCheckParamEntity prizeCheckParamEntity);
	
}
