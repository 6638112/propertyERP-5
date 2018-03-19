/**   
* Filename:    PrizeResultDiscountEntity.java   
* @version:    1.0  
* Create at:   2015年1月20日 上午3:24:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月20日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;


/**
 * Filename:    PrizeResultDiscountEntity.java
 * @version:    1.0.0
 * Create at:   2015年1月20日 上午3:24:21
 * Description:奖池抽奖结果折扣信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月20日       shiyl             1.0             1.0 Version
 */
public class PrizeResultDiscountEntity {
	/**折扣信息*/
	private DiscountValueEntity discountValueEntity;
	/**是否临时生成*/
	private Boolean isTmpGenerate;
	
	public PrizeResultDiscountEntity(DiscountValueEntity discountValueEntity,PrizeSurpriseGiftEntity baseSurpriseGift){
		this(discountValueEntity, (Object)baseSurpriseGift);
	}
	protected PrizeResultDiscountEntity(DiscountValueEntity discountValueEntity,Object baseSurpriseGift){
		this.discountValueEntity = discountValueEntity;
		this.baseSurpriseGift = baseSurpriseGift;
	}
	
	@Deprecated
	public PrizeResultDiscountEntity(DiscountValueEntity discountValueEntity,Boolean isTmpGenerate){
		this.discountValueEntity = discountValueEntity;
		this.isTmpGenerate = isTmpGenerate;
	}
	
	public DiscountValueEntity getDiscountValueEntity() {
		return discountValueEntity;
	}
	public void setDiscountValueEntity(DiscountValueEntity discountValueEntity) {
		this.discountValueEntity = discountValueEntity;
	}
	public Boolean getIsTmpGenerate() {
		return isTmpGenerate;
	}
	public void setIsTmpGenerate(Boolean isTmpGenerate) {
		this.isTmpGenerate = isTmpGenerate;
	}
	
//	/**
//	 * 中奖保持的记录Id
//	 */
//	private BigInteger recordId;
//	public BigInteger getRecordId() {
//		return recordId;
//	}
//	public void setRecordId(BigInteger recordId) {
//		this.recordId = recordId;
//	}
	
	/**抽奖结果意外惊喜*/
	protected Object baseSurpriseGift;
	public void setBaseSurpriseGift(Object baseSurpriseGift) {
		this.baseSurpriseGift = baseSurpriseGift;
	}
	public Object getBaseSurpriseGift() {
		return baseSurpriseGift;
	}
	
	public PrizeSurpriseGiftEntity getPrizeSurpriseGift() {
		return (PrizeSurpriseGiftEntity)this.baseSurpriseGift;
	}
	
	/**
	 * 校验结果信息
	 */
	private PrizePreConditionValidateResult prizePreConditionValidateResult;
	public PrizePreConditionValidateResult getPrizePreConditionValidateResult() {
		return prizePreConditionValidateResult;
	}
	public void setPrizePreConditionValidateResult(PrizePreConditionValidateResult prizePreConditionValidateResult) {
		this.prizePreConditionValidateResult = prizePreConditionValidateResult;
	}
	
}
