/**   
* Filename:    RoomStatusCheckUtil.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午1:29:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.util;

import java.math.BigInteger;

import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * Filename:    RoomStatusCheckUtil.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午1:29:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public class RoomStatusCheckUtil {
	
	/**
	 * 判断真实门牌是否是默认的空门牌
	 * @return
	 */
	public static boolean checkIsRealRoomEmpty(BigInteger realRoomId){
		if(realRoomId==null||RoomConstants.ROOM_NULL_ID_REAL.compareTo(realRoomId)==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断真实门牌是否是默认的空门牌
	 * @return
	 */
	public static boolean checkIsRoomEmpty(Room currRoom){
		if(currRoom==null){
			return true;
		}
		return checkIsRealRoomEmpty(currRoom.gettRealRoomFId());
	}
	
	/**
	 * 核对真实门牌是否已经校验通过
	 * @param realRoom
	 * @return
	 */
	public static boolean checkIsRealRoomValidated(RealRoom realRoom){
		if(realRoom!=null&&realRoom.getValidateStatus()!=null&&realRoom.getValidateStatus().compareTo(RoomDict.RealRoom_ValidateStatus.IS_VALIDATED)==0){
			return true;
		}else{
			return false;
		}
	}
	
}
