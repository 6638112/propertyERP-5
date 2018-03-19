/**   
* Filename:    ActOptionParam.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午8:39:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.math.BigInteger;

/**
 * Filename:    ActOptionParam.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午8:39:20
 * Description:活动包含的奖项信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public class ActOptionParam {
	/**奖项Id*/
	private BigInteger prizeOptionId;
	/**奖项总数*/
	private Long totalMaxCount;
	/**日均派发数*/
	private Long dayMaxCount;
	
	/**
	 * 构造方法
	 * @param prizeOptionId 奖项Id
	 * @param totalMaxCount 奖项总数
	 * @param dayMaxCount 日均派发数
	 */
	public ActOptionParam(BigInteger prizeOptionId,Long totalMaxCount,Long dayMaxCount){
		this.prizeOptionId = prizeOptionId;
		this.totalMaxCount = totalMaxCount;
		this.dayMaxCount = dayMaxCount;
	}
	
	public BigInteger getPrizeOptionId() {
		return prizeOptionId;
	}
	public void setPrizeOptionId(BigInteger prizeOptionId) {
		this.prizeOptionId = prizeOptionId;
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
