/**   
* Filename:    UserHasTRoomWithRoomSimpleEntity.java   
* @version:    1.0  
* Create at:   2015年5月14日 上午8:54:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.room.util.SpecialUserChekUtil;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    UserHasTRoomWithRoomSimpleEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月14日 上午8:54:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月14日       shiyl             1.0             1.0 Version
 */
public class UserHasTRoomWithRoomSimpleEntity extends UserHasTRoom{
	private static final long serialVersionUID = 1L;
	/**基本room信息*/
	private Room ext_room;
	public void setExt_room(Room ext_room) {
		this.ext_room = ext_room;
	}
	public BigInteger getRealRoomId(){
		return ext_room.gettRealRoomFId();
	}
	
	
	/**room是否已经被验证过:0未验证1已验证*/
	private Boolean ext_roomIsValidate;
	public void setExt_roomIsValidate(Boolean ext_roomIsValidate) {
		this.ext_roomIsValidate = ext_roomIsValidate;
	}
	
	/**门牌管理员用户Id*/
	private BigInteger ext_adminUserId;
	public void setExt_adminUserId(BigInteger ext_adminUserId) {
		this.ext_adminUserId = ext_adminUserId;
	}
	/**
	 * 比较重要级别
	 * @param goal
	 * @return 
	 */
	public double compareToImportant(UserHasTRoomWithRoomSimpleEntity goal){
		return this.getImportantLevel()-goal.getImportantLevel();
	}
	
	public boolean getRoomIsValidate(){//门牌是否已被验证
		boolean res = false;
		if(ext_roomIsValidate!=null){
			res = ext_roomIsValidate;
		}
		return res;
	}
	
	private boolean getUserIsAdmin() {//用户是否为门牌管理员
		if(ext_adminUserId!=null&&ext_adminUserId.compareTo(gettUserFId())==0){
			return true;
		}
		return false;
	}
	
	private boolean getIsSpecialPlan(){//是否为特使计划的用户
		return SpecialUserChekUtil.checkIsSpecialUser(this);
	}
	private double getImportantLevel(){//获取重要级别
		double resLevel = 0;
		if(getIsSpecialPlan()){
			resLevel = 2;
		}else if(getRoomIsValidate()){
			resLevel = 1;
			if(getUserIsAdmin()){//用户是否为门牌管理员+0.5
				resLevel = resLevel+0.5;
			}
		}/*else{
			resLevel = 0;
		}*/
		return resLevel;
	}
	
	
}
