/**   
* Filename:    PrizePoolGroupInfoEntity.java   
* @version:    1.0  
* Create at:   2014年9月1日 上午9:33:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.entity;

import java.util.List;

/**
 * Filename:    PrizePoolGroupInfoEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月1日 上午9:33:01
 * Description:奖池信息描述
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月1日       shiyl             1.0             1.0 Version
 */
public class PrizePoolGroupInfoEntity {
	private Integer poolState;
	private List<PrizePoolInfoEntitySimple> simpleList;
	
	
	public PrizePoolGroupInfoEntity(Integer poolState,List<PrizePoolInfoEntitySimple> simpleList){
		this.poolState = poolState;
		this.simpleList = simpleList;
	}
	
	public Integer getPoolState() {
		return poolState;
	}
	public void setPoolState(Integer poolState) {
		this.poolState = poolState;
	}

	public List<PrizePoolInfoEntitySimple> getSimpleList() {
		return simpleList;
	}

	public void setSimpleList(List<PrizePoolInfoEntitySimple> simpleList) {
		this.simpleList = simpleList;
	}

	
}
