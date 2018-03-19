/**   
* Filename:    GroupBuildingHasTCommunitySupply_Supply.java   
* @version:    1.0  
* Create at:   2015年1月7日 上午11:07:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity;

import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

/**
 * Filename:    GroupBuildingHasTCommunitySupply_Supply.java
 * @version:    1.0.0
 * Create at:   2015年1月7日 上午11:07:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月7日       shiyl             1.0             1.0 Version
 */
public class GroupBuildingHasTCommunitySupply_Supply extends GroupBuildingHasTCommunitySupply{
	private static final long serialVersionUID = 1L;
	
	/**商家信息*/
	private CommunitySupplyEntity communitySupplyEntity;
	public CommunitySupplyEntity getCommunitySupplyEntity() {
		return communitySupplyEntity;
	}
	public void setCommunitySupplyEntity(CommunitySupplyEntity communitySupplyEntity) {
		this.communitySupplyEntity = communitySupplyEntity;
	}
	
	/**
	 * 当前用户对应的认领信息
	 */
	private CommunitySupplyBelong currUserSupplyBelong;
	public CommunitySupplyBelong getCurrUserSupplyBelong() {
		return currUserSupplyBelong;
	}
	public void setCurrUserSupplyBelong(CommunitySupplyBelong currUserSupplyBelong) {
		this.currUserSupplyBelong = currUserSupplyBelong;
	}
	
	
	/**
	 * 被认领通过的信息
	 */
	private CommunitySupplyBelong successSupplyBelong;
	public CommunitySupplyBelong getSuccessSupplyBelong() {
		return successSupplyBelong;
	}
	public void setSuccessSupplyBelong(CommunitySupplyBelong successSupplyBelong) {
		this.successSupplyBelong = successSupplyBelong;
	}
	
}
