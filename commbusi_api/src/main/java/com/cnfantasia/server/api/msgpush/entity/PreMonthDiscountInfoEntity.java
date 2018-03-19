/**   
* Filename:    PreMonthDiscountInfoEntity.java   
* @version:    1.0  
* Create at:   2014年9月30日 上午7:22:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.entity;

import java.io.Serializable;
import java.math.BigInteger;

import com.cnfantasia.server.api.room.constant.RoomDict;

/**
 * Filename:    PreMonthDiscountInfoEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月30日 上午7:22:40
 * Description:上个月份的折扣信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月30日       shiyl             1.0             1.0 Version
 */
public class PreMonthDiscountInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**用户Id*/
	private BigInteger userId;
	/**默认门牌Id*/
	private BigInteger defaultRoomId;
	/**年份*/
	private String year;
	/**月份*/
	private String month;
	/**折扣状态*/
	private Integer status;
	
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigInteger getDefaultRoomId() {
		return defaultRoomId;
	}
	public void setDefaultRoomId(BigInteger defaultRoomId) {
		this.defaultRoomId = defaultRoomId;
	}
	
	/**小区签约状态*/
	private Integer groupBuildingSignStatus;
	public Integer getGroupBuildingSignStatus() {
		return groupBuildingSignStatus;
	}
	public void setGroupBuildingSignStatus(Integer groupBuildingSignStatus) {
		this.groupBuildingSignStatus = groupBuildingSignStatus;
	}
	
	public boolean isSign(){
		boolean isSign = false;
		if(groupBuildingSignStatus!=null&&groupBuildingSignStatus.compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0){
			isSign = true;
		}
		return isSign;
	}
}
