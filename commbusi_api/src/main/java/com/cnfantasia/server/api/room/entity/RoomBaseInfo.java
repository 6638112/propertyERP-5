/**   
* Filename:    RoomBaseInfo.java   
* @version:    1.0  
* Create at:   2014年5月20日 上午9:02:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月20日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import java.io.Serializable;


/**
 * Filename:    RoomBaseInfo.java
 * @version:    1.0.0
 * Create at:   2014年5月20日 上午9:02:38
 * Description: 房间类的基本信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月20日       shiyl             1.0             1.0 Version
 */
public class RoomBaseInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**虚拟房间Id*/
	private String id;
	private String roomNum;
	private String building;
	private String groupBuilding;
	private String block;
	private String city;
	private String province;
	private String totalAddress;
	
	/**虚拟房间花号*/
	private String roomHuaId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getGroupBuilding() {
		return groupBuilding;
	}
	public void setGroupBuilding(String groupBuilding) {
		this.groupBuilding = groupBuilding;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getTotalAddress() {
		return totalAddress;
	}
	public void setTotalAddress(String totalAddress) {
		this.totalAddress = totalAddress;
	}
	
	public String getRoomHuaId() {
		return roomHuaId;
	}
	public void setRoomHuaId(String roomHuaId) {
		this.roomHuaId = roomHuaId;
	}
	
}
