/**   
* Filename:    FirstLastPrizeTimeEntity.java   
* @version:    1.0  
* Create at:   2015年2月3日 上午2:23:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月3日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

/**
 * Filename:    FirstLastPrizeTimeEntity.java
 * @version:    1.0.0
 * Create at:   2015年2月3日 上午2:23:28
 * Description: 起止抽奖时间实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月3日       shiyl             1.0             1.0 Version
 */
public class FirstLastPrizeTimeEntity {
	
	/**首次抽奖时间*/
	private String firstPrizeTime;
	/**最后一次抽奖时间*/
	private String lastPrizeTime;
	
	public FirstLastPrizeTimeEntity(){}
	
	public String getFirstPrizeTime() {
		return firstPrizeTime;
	}
	public void setFirstPrizeTime(String firstPrizeTime) {
		this.firstPrizeTime = firstPrizeTime;
	}
	public String getLastPrizeTime() {
		return lastPrizeTime;
	}
	public void setLastPrizeTime(String lastPrizeTime) {
		this.lastPrizeTime = lastPrizeTime;
	}
	
	
}
