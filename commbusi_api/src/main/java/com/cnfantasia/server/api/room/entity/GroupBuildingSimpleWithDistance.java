/**   
* Filename:    GroupBuildingSimpleWithDistance.java   
* @version:    1.0  
* Create at:   2015年1月8日 上午7:22:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    GroupBuildingSimpleWithDistance.java
 * @version:    1.0.0
 * Create at:   2015年1月8日 上午7:22:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月8日       shiyl             1.0             1.0 Version
 */
public class GroupBuildingSimpleWithDistance extends GroupBuilding{
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
