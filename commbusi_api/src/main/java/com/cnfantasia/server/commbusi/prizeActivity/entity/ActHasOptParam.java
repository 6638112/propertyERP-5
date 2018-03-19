/**   
* Filename:    ActHasOptParam.java   
* @version:    1.0  
* Create at:   2015年9月15日 下午5:33:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.math.BigInteger;

/**
 * Filename:    ActHasOptParam.java
 * @version:    1.0.0
 * Create at:   2015年9月15日 下午5:33:38
 * Description:活动奖项关系参数
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月15日       shiyl             1.0             1.0 Version
 */
public class ActHasOptParam {
	/**活动奖项关系Id*/
	private BigInteger actHasOptId;
	/**奖项总数*/
	private Long totalMaxCount;
	/**日均派发数*/
	private Long dayMaxCount;
	
	/**
	 * 构造方法
	 * @param actHasOptId 活动奖项关系Id
	 * @param totalMaxCount 奖项总数
	 * @param dayMaxCount 日均派发数
	 */
	public ActHasOptParam(BigInteger actHasOptId,Long totalMaxCount,Long dayMaxCount){
		this.actHasOptId = actHasOptId;
		this.totalMaxCount = totalMaxCount;
		this.dayMaxCount = dayMaxCount;
	}
	
	public BigInteger getActHasOptId() {
		return actHasOptId;
	}
	public void setActHasOptId(BigInteger actHasOptId) {
		this.actHasOptId = actHasOptId;
	}
	public Long getTotalMaxCount() {
		return totalMaxCount;
	}
	public void setTotalMaxCount(Long totalMaxCount) {
		this.totalMaxCount = totalMaxCount;
	}
	public Long getDayMaxCount() {
		return dayMaxCount;
	}
	public void setDayMaxCount(Long dayMaxCount) {
		this.dayMaxCount = dayMaxCount;
	}
	
}
