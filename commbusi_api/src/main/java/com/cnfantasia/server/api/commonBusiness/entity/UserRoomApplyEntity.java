/**   
* Filename:    UserRoomApplyEntity.java   
* @version:    1.0  
* Create at:   2014年12月19日 上午3:53:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    UserRoomApplyEntity.java
 * @version:    1.0.0
 * Create at:   2014年12月19日 上午3:53:11
 * Description: 用户门牌申请信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月19日       shiyl             1.0             1.0 Version
 */
public class UserRoomApplyEntity extends UserHasTRoom{
	private static final long serialVersionUID = 1L;
	
	/**申请者*/
	private UserSimpleEntity applyUser;
	public UserSimpleEntity getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(UserSimpleEntity applyUser) {
		this.applyUser = applyUser;
	}
	
	/**
	 * 门牌的校验信息
	 */
	private RoomValidate roomValidate;

	public RoomValidate getRoomValidate() {
		return roomValidate;
	}

	public void setRoomValidate(RoomValidate roomValidate) {
		this.roomValidate = roomValidate;
	}
	
	public boolean checkIsValidedSuccess(){
		if(roomValidate!=null&&roomValidate.getVerifyStatus()!=null
				&&roomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){
			return true;
		}else{
			return false;
		}
	}
	
}
