/**   
* Filename:    GoalIdTypeForGatherData.java   
* @version:    1.0  
* Create at:   2015年7月1日 上午3:43:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    GoalIdTypeForGatherData.java
 * @version:    1.0.0
 * Create at:   2015年7月1日 上午3:43:53
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月1日       shiyl             1.0             1.0 Version
 */
public class GoalIdTypeForGatherData implements Serializable{
	private static final long serialVersionUID = -7983477904187614251L;
	/** 目标类别 */
	private Integer targetType;
	/** 目标Id */
	private BigInteger targetId;
	
	public GoalIdTypeForGatherData(){}
	public GoalIdTypeForGatherData(Integer targetType,BigInteger targetId){
		this.targetType = targetType;
		this.targetId = targetId;
	}
	
	public Integer getTargetType() {
		return targetType;
	}
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}
	public BigInteger getTargetId() {
		return targetId;
	}
	public void setTargetId(BigInteger targetId) {
		this.targetId = targetId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((targetId == null) ? 0 : targetId.hashCode());
		result = prime * result + ((targetType == null) ? 0 : targetType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoalIdTypeForGatherData other = (GoalIdTypeForGatherData) obj;
		if (targetId == null) {
			if (other.targetId != null)
				return false;
		} else if (!targetId.equals(other.targetId))
			return false;
		if (targetType == null) {
			if (other.targetType != null)
				return false;
		} else if (!targetType.equals(other.targetType))
			return false;
		return true;
	}
	
}
