/**   
* Filename:    GroupBuildingAndCommunitySupply.java   
* @version:    1.0  
* Create at:   2014年11月28日 上午8:53:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity02;

import java.math.BigInteger;

/**
 * Filename:    GroupBuildingAndCommunitySupply.java
 * @version:    1.0.0
 * Create at:   2014年11月28日 上午8:53:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月28日       shiyl             1.0             1.0 Version
 */
public class GroupBuildingAndCommunitySupply {
	private BigInteger groupBuildingId;
	private BigInteger communitySupplyId;
	/** 排序,降序,大的排前面 */
	private Integer order;
	/** 小区与商家的距离,米 */
	private Double distance;
	
	public BigInteger getGroupBuildingId() {
		return groupBuildingId;
	}
	public void setGroupBuildingId(BigInteger groupBuildingId) {
		this.groupBuildingId = groupBuildingId;
	}
	public BigInteger getCommunitySupplyId() {
		return communitySupplyId;
	}
	public void setCommunitySupplyId(BigInteger communitySupplyId) {
		this.communitySupplyId = communitySupplyId;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
}
