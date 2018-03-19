/**   
* Filename:    PrizeSurpriseGiftEntity.java   
* @version:    1.0  
* Create at:   2015年4月16日 上午9:04:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.entity;

import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;

/**
 * Filename:    PrizeSurpriseGiftEntity.java
 * @version:    1.0.0
 * Create at:   2015年4月16日 上午9:04:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月16日       shiyl             1.0             1.0 Version
 */
public class PrizeSurpriseGiftEntity extends PrizeSurpriseGift{
	private static final long serialVersionUID = 1L;
	
	/**是否已过期 0未过期 1已过期 */
	private Integer expiredStatus;
	public Integer getExpiredStatus() {
		return expiredStatus;
	}
	public void setExpiredStatus(Integer expiredStatus) {
		this.expiredStatus = expiredStatus;
	}


	@Override
	public Integer getUseStatus() {
		Integer superUseStatus = super.getUseStatus();
		if(superUseStatus!=null&&superUseStatus.compareTo(SurpriseGiftDict.PrizeSurpriseGift_UseStatus.NotUse)==0
				&&expiredStatus!=null&&expiredStatus==1){///**是否已过期 0未过期 1已过期 */
			return SurpriseGiftDict.PrizeSurpriseGift_UseStatus.Expired;
		}
		//已使用的但是已过期的 则返回已使用的状态
		return super.getUseStatus();
	}
	
	/**获取有效期的Long表示*/
	public Long getExpiredTimeLong(){
		Long res = null;
		String time = getExpiryTime();
		if(!StringUtils.isEmpty(time)){
			res = DateUtil.timeToLong(time);
		}
		return res;
	}
	
}
