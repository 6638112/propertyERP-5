/**   
* Filename:    BuildingAndRealRoomListEntity.java   
* @version:    1.0  
* Create at:   2014年5月29日 上午11:22:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

/**
 * Filename:    BuildingAndRealRoomListEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月29日 上午11:22:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月29日       shiyl             1.0             1.0 Version
 */
public class BuildingAndRealRoomListEntity extends Building{
	private static final long serialVersionUID = 1L;
	/**楼栋下的门牌列表*/
	private List<RealRoom> realRroomList ;
	public List<RealRoom> getRealRroomList() {
		return realRroomList;
	}
	public void setRealRroomList(List<RealRoom> realRroomList) {
		this.realRroomList = realRroomList;
	}
	
}
