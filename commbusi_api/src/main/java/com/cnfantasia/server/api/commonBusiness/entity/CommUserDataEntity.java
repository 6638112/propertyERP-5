/**   
* Filename:    CommUserDataEntity.java   
* @version:    1.0  
* Create at:   2014年12月11日 上午8:58:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.math.BigInteger;

/**
 * Filename:    CommUserDataEntity.java
 * @version:    1.0.0
 * Create at:   2014年12月11日 上午8:58:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月11日       shiyl             1.0             1.0 Version
 */
public class CommUserDataEntity {
	/**用户Id*/
	private BigInteger userId;
	/**用户类型*/
	private Integer userType;
	/**用户默认门牌Id*/
	private BigInteger defaultRoomId;
	
	public CommUserDataEntity() {
	}

	public CommUserDataEntity(BigInteger userId,Integer userType,BigInteger defaultRoomId){
		this.userId = userId;
		this.userType = userType;
		this.defaultRoomId = defaultRoomId;
	}
	
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public BigInteger getDefaultRoomId() {
		return defaultRoomId;
	}
	public void setDefaultRoomId(BigInteger defaultRoomId) {
		this.defaultRoomId = defaultRoomId;
	}
	
}
