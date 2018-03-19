/**   
* Filename:    EbuyHandleAddressEntity.java   
* @version:    1.0  
* Create at:   2014年6月9日 上午3:03:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.domainbase.ebuyHandleAddress.entity.EbuyHandleAddress;

/**
 * Filename:    EbuyHandleAddressEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月9日 上午3:03:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月9日       shiyl             1.0             1.0 Version
 */
public class EbuyHandleAddressEntity extends EbuyHandleAddress{
	private static final long serialVersionUID = 1L;
	private GroupBuildingEntity groupBuildingEntity;
	public GroupBuildingEntity getGroupBuildingEntity() {
		return groupBuildingEntity;
	}
	public void setGroupBuildingEntity(GroupBuildingEntity groupBuildingEntity) {
		this.groupBuildingEntity = groupBuildingEntity;
	}
	@Override
	public BigInteger gettGroupBuildingFId() {
		if(this.groupBuildingEntity==null){return null;}
		return groupBuildingEntity.getId();
	}
	@Override
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		if(this.groupBuildingEntity==null){groupBuildingEntity = new GroupBuildingEntity();}
		groupBuildingEntity.setId(tGroupBuildingFId);
	}
	
}
