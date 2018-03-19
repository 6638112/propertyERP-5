/**   
* Filename:    PrizePreConditionValidateResult.java   
* @version:    1.0  
* Create at:   2015年6月10日 上午11:08:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

/**
 * Filename:    PrizePreConditionValidateResult.java
 * @version:    1.0.0
 * Create at:   2015年6月10日 上午11:08:54
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月10日       shiyl             1.0             1.0 Version
 */
public class PrizePreConditionValidateResult {
	
	/**默认允许的最大抽奖次数*/
	private Integer defaultMaxCount;
	
	/**已抽奖次数*/
	private Integer doneCount;
	
	public PrizePreConditionValidateResult(Integer defaultMaxCount,Integer doneCount){
		this.defaultMaxCount = defaultMaxCount;
		this.doneCount = doneCount;
	}
	
	public Integer getDefaultMaxCount() {
		return defaultMaxCount;
	}

	public void setDefaultMaxCount(Integer defaultMaxCount) {
		this.defaultMaxCount = defaultMaxCount;
	}

	public Integer getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(Integer doneCount) {
		this.doneCount = doneCount;
	}
	
	public Integer fetchLeftCount(){
		Integer leftCount = this.getDefaultMaxCount()-(this.getDoneCount()+1);
		return leftCount;
	}
	
	public Integer fetchCurrPrizeCount(){
		return this.getDoneCount()+1;
	}
	
}
