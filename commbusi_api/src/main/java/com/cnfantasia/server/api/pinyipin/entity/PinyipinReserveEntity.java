/**   
* Filename:    PinyipinReserveEntity.java   
* @version:    1.0  
* Create at:   2014年10月16日 上午11:28:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.entity;

import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;

/**
 * Filename:    PinyipinReserveEntity.java
 * @version:    1.0.0
 * Create at:   2014年10月16日 上午11:28:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月16日       shiyl             1.0             1.0 Version
 */
public class PinyipinReserveEntity extends CommunityPinyipinReserve{
	private static final long serialVersionUID = 1L;
	
	/**配送地址*/
	private EbuyDeliveryAddressEntity ebuyDeliveryAddressEntity;
	public EbuyDeliveryAddressEntity getEbuyDeliveryAddressEntity() {
		return ebuyDeliveryAddressEntity;
	}
	public void setEbuyDeliveryAddressEntity(EbuyDeliveryAddressEntity ebuyDeliveryAddressEntity) {
		this.ebuyDeliveryAddressEntity = ebuyDeliveryAddressEntity;
	}
	
	/**预定的用户信息*/
	private UserSimpleEntity user;
	public UserSimpleEntity getUser() {
		return user;
	}
	public void setUser(UserSimpleEntity user) {
		this.user = user;
	}
	
	
}
