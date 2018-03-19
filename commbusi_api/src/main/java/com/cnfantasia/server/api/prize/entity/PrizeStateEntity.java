/**   
* Filename:    PrizeStateEntity.java   
* @version:    1.0  
* Create at:   2014年8月28日 上午7:11:17   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

/**
 * Filename:    PrizeStateEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月28日 上午7:11:17
 * Description: 当前抽奖状态信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月28日       shiyl             1.0             1.0 Version
 */
public class PrizeStateEntity {
	/**已经抽奖的次数*/
	private Integer donePrizeCount;
	/**最低折扣*/
	private Double leastDiscount;
	/**折扣是否已经被使用*/
	private Boolean hasUsed;
	
	/**折扣可兑换的粮票金额*/
	private Long savedHbMoney;
	
	/**
	 * 构造方式
	 * @param leftPrizeCount 剩余抽奖次数
	 * @param leastDiscount 最低折扣
	 */
	public PrizeStateEntity(Integer donePrizeCount,Double leastDiscount,Boolean hasUsed,Long savedHbMoney){
		this.donePrizeCount = donePrizeCount;
		this.leastDiscount = leastDiscount;
		this.hasUsed = hasUsed;
		this.savedHbMoney = savedHbMoney;
	}
	
	public Integer getDonePrizeCount() {
		return donePrizeCount;
	}

	public void setDonePrizeCount(Integer donePrizeCount) {
		this.donePrizeCount = donePrizeCount;
	}

	public Double getLeastDiscount() {
		return leastDiscount;
	}
	public void setLeastDiscount(Double leastDiscount) {
		this.leastDiscount = leastDiscount;
	}

	public Boolean getHasUsed() {
		return hasUsed;
	}

	public void setHasUsed(Boolean hasUsed) {
		this.hasUsed = hasUsed;
	}

	public Long getSavedHbMoney() {
		return savedHbMoney;
	}

	public void setSavedHbMoney(Long savedHbMoney) {
		this.savedHbMoney = savedHbMoney;
	}
	
	
	
}
