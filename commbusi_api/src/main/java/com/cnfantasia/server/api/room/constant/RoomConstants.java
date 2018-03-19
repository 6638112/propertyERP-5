/**   
* Filename:    RoomConstants.java   
* @version:    1.0  
* Create at:   2014年7月23日 上午1:44:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.constant;

import java.math.BigInteger;

/**
 * Filename:    RoomConstants.java
 * @version:    1.0.0
 * Create at:   2014年7月23日 上午1:44:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月23日       shiyl             1.0             1.0 Version
 */
public class RoomConstants {
	/**体验省Id*/
	public static final BigInteger DEFAULT_ADDRESS_PROVINCE_ID=new BigInteger("-1");
	/**体验市Id*/
	public static final BigInteger DEFAULT_ADDRESS_CITY_ID=new BigInteger("-1");
	/**体验行政区Id*/
	public static final BigInteger DEFAULT_ADDRESS_BLOCK_ID=new BigInteger("-1");
	/**体验小区Id*/
	public static final BigInteger DEFAULT_GROUP_BUILDING_ID=new BigInteger("-1");
	
	/**t_real_room空房间默认Id*/
	public static final BigInteger ROOM_NULL_ID_REAL = new BigInteger("-1");
	
	/**默认小区图标地址*/
	public static final String DEFAULT_GROUPBUILDING_ICON="defaultIcon.png";
	/**默认小区大图名称*/
	public static final String DEFAULT_GROUPBUILDING_PICDESC="defaultPicDesc.png";
	
	/**默认门牌为空，且需要设置门牌的错误异常信息*/
	public static final String DEFALT_ROOM_ISEMPTY_AND_NEEDSET_EXCEPTION_CODE="plotproperty.qryPayBill.defaultRoom.isEmpty";
	
	/**查询附近的小区 最大距离,单位 米*/
	public static final Double DEFAULT_MAX_LOCAT_DISTANCE_NEAR = 1000.0;
	
	/**消息推送标题-申请加入门牌*/
	public static final String Msgpush_Title_Room_Apply_Join = "家庭成员提醒";
	/**消息推送标题-同意拒绝门牌*/
	public static final String Msgpush_Title_Room_Apply_Decide = "加入门牌提醒";
	
	//用户申请加入审核状态 0:未处理;1: 已同意;2:已拒绝 ;(目前只返回状态为0和1的数据)
	public static class UserRoomApplyStatus{
		public static final Integer UNDO = 0;
		public static final Integer PASS = 1;
		public static final Integer REJECT = 2;
	}
}
