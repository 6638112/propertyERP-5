/**   
* Filename:    RoomEntityWithValidate.java   
* @version:    1.0  
* Create at:   2014年8月18日 上午8:58:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

/**
 * Filename:    RoomEntityWithValidate.java
 * @version:    1.0.0
 * Create at:   2014年8月18日 上午8:58:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月18日       shiyl             1.0             1.0 Version
 */
public class RoomEntityWithValidate extends RoomEntity{
	private static final long serialVersionUID = 1L;
	
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
	
	public Integer fetchRoomValidteVerifyStatus(){
		if(roomValidate==null||roomValidate.getVerifyStatus()==null){
			return RoomDict.RoomValidte_VerifyStatus.UNDO;
		}else{
			return roomValidate.getVerifyStatus();
		}
	}
}
