/**   
* Filename:    UserHasTRoomParamEntity.java   
* @version:    1.0  
* Create at:   2015年5月14日 上午8:47:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.math.BigInteger;

/**
 * Filename:    UserHasTRoomParamEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月14日 上午8:47:26
 * Description:用户门牌新增的参数存储类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月14日       shiyl             1.0             1.0 Version
 */
public class UserHasTRoomParamEntity {
	
	private BigInteger roomId;
	private BigInteger userId;
	private Integer adminType;
	private BigInteger inviterUserId;
	private BigInteger bindUserId;
	
	public UserHasTRoomParamEntity(BigInteger roomId, BigInteger userId,Integer adminType, BigInteger inviterUserId,BigInteger bindUserId){
		this.roomId = roomId;
		this.userId = userId;
		this.adminType = adminType;
		this.inviterUserId = inviterUserId;
		this.bindUserId = bindUserId;
	}
	
	public BigInteger getRoomId() {
		return roomId;
	}
	public void setRoomId(BigInteger roomId) {
		this.roomId = roomId;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public Integer getAdminType() {
		return adminType;
	}
	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}
	public BigInteger getInviterUserId() {
		return inviterUserId;
	}
	public void setInviterUserId(BigInteger inviterUserId) {
		this.inviterUserId = inviterUserId;
	}
	public BigInteger getBindUserId() {
		return bindUserId;
	}
	public void setBindUserId(BigInteger bindUserId) {
		this.bindUserId = bindUserId;
	}
	
	
}
