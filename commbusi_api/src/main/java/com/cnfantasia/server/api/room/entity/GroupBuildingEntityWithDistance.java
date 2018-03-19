/**   
* Filename:    GroupBuildingEntityWithDistance.java   
* @version:    1.0  
* Create at:   2015年2月4日 上午8:41:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

/**
 * Filename:    GroupBuildingEntityWithDistance.java
 * @version:    1.0.0
 * Create at:   2015年2月4日 上午8:41:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月4日       shiyl             1.0             1.0 Version
 */
public class GroupBuildingEntityWithDistance extends GroupBuildingEntity{
	private static final long serialVersionUID = 1L;
	
	/**距离*/
	private Double distance;
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
