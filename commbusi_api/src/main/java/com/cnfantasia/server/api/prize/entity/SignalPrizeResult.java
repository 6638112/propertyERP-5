/**   
* Filename:    SignalPrizeResult.java   
* @version:    1.0  
* Create at:   2014年5月12日 上午1:34:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import java.io.Serializable;

import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * Filename:    SignalPrizeResult.java
 * @version:    1.0.0
 * Create at:   2014年5月12日 上午1:34:21
 * Description: 单次抽奖结果信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月12日       shiyl             1.0             1.0 Version
 */
public class SignalPrizeResult implements Serializable{
	private static final long serialVersionUID = 1L;
	/**当次折扣*/
	private Double discount;
	/**剩余抽奖次数*/
	private Integer leftCount;
	/**当前最低折扣*/
	private Double leastDiscount;
	/**当前折扣是否为最低*/
	private Boolean isLeastDiscount;
	/**折扣可兑换的粮票金额*/
	private Long savedHbMoney;
	/**抽奖时间*/
	private Long prizeTime;
	
	/**折扣使用说明*/
	private PrizeIgnoreUsedEntity ignoreUsedEntity;
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("discount=").append(discount).append(";");
		sbf.append("leftCount=").append(leftCount).append(";");
		sbf.append("leastDiscount=").append(leastDiscount).append(";");
		sbf.append("isLeastDiscount=").append(isLeastDiscount).append(";");
		sbf.append("savedHbMoney=").append(savedHbMoney).append(";");
		sbf.append("prizeTime=").append(prizeTime).append(";");
		return sbf.toString();
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}
	public Double getLeastDiscount() {
		return leastDiscount;
	}
	public void setLeastDiscount(Double leastDiscount) {
		this.leastDiscount = leastDiscount;
	}
	public Boolean getIsLeastDiscount() {
		return isLeastDiscount;
	}
	public void setIsLeastDiscount(Boolean isLeastDiscount) {
		this.isLeastDiscount = isLeastDiscount;
	}
	public Long getSavedHbMoney() {
		return savedHbMoney;
	}
	public void setSavedHbMoney(Long savedHbMoney) {
		this.savedHbMoney = savedHbMoney;
	}
	public Long getPrizeTime() {
		return prizeTime;
	}
	public void setPrizeTime(Long prizeTime) {
		this.prizeTime = prizeTime;
	}
	public PrizeIgnoreUsedEntity getIgnoreUsedEntity() {
		return ignoreUsedEntity;
	}
	public void setIgnoreUsedEntity(PrizeIgnoreUsedEntity ignoreUsedEntity) {
		this.ignoreUsedEntity = ignoreUsedEntity;
	}



	/**抽奖结果信息备份*/
	private PrizeResultDiscountEntity prizeResultDiscountEntity;
	public PrizeResultDiscountEntity getPrizeResultDiscountEntity() {
		return prizeResultDiscountEntity;
	}
	public void setPrizeResultDiscountEntity(PrizeResultDiscountEntity prizeResultDiscountEntity) {
		this.prizeResultDiscountEntity = prizeResultDiscountEntity;
	}
	
	/**首页图标信息*/
	private SurpriseGiftConfigPic surpriseGiftConfigPic;
	public SurpriseGiftConfigPic getSurpriseGiftConfigPic() {
		return surpriseGiftConfigPic;
	}
	public void setSurpriseGiftConfigPic(SurpriseGiftConfigPic surpriseGiftConfigPic) {
		this.surpriseGiftConfigPic = surpriseGiftConfigPic;
	}
	
}
