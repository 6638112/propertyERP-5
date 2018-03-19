/**   
* Filename:    PrizeCountWithUserEntity.java   
* @version:    1.0  
* Create at:   2015年2月2日 下午1:11:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;

/**
 * Filename:    PrizeCountWithUserEntity.java
 * @version:    1.0.0
 * Create at:   2015年2月2日 下午1:11:48
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月2日       shiyl             1.0             1.0 Version
 */
public class PrizeCountWithUserEntity extends UserSimpleEntity{

	private static final long serialVersionUID = 1L;
	/**抽奖次数*/
	private Long prizeCount;
	public Long getPrizeCount() {
		return prizeCount;
	}
	public void setPrizeCount(Long prizeCount) {
		this.prizeCount = prizeCount;
	}
	
}
