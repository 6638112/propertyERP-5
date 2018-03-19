/**   
* Filename:    CommunitySupplyTypeEntity.java   
* @version:    1.0  
* Create at:   2014年9月1日 上午3:43:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity;

import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    CommunitySupplyTypeEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月1日 上午3:43:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月1日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyTypeEntity extends CommunitySupplyType{
	private static final long serialVersionUID = 1L;
	
	/**所属顶级类别*/
	private CommunitySupplyType topCommunitySupplyType;

	public CommunitySupplyType getTopCommunitySupplyType() {
		return topCommunitySupplyType;
	}

	public void setTopCommunitySupplyType(CommunitySupplyType topCommunitySupplyType) {
		this.topCommunitySupplyType = topCommunitySupplyType;
	}
	
}
