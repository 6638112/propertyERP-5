package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.ms.revenue.entity.AbstractRevSrcEntity;
import com.cnfantasia.server.ms.revenue.entity.BeginEndDate;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 收益明细数据同步
* Filename:    AbstractSyn2RevSignal.java
* @version:    1.0.0
* Create at:   2015年11月19日 上午11:31:11
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       shiyl             1.0             1.0 Version
 * @param <G>
 */
public interface ISyn2RevSignal<T extends AbstractRevSrcEntity<G>, G> {
	
	/**
	 * 同步操作
	 * @param projectType
	 * @param revenueRole
	 * @return
	 */
	public Integer syn2RegSignal(Integer projectType, RevenueRole revenueRole);
	public Integer syn2RegSignal(Integer projectType, RevenueRole revenueRole,BeginEndDate givenRange);
}
