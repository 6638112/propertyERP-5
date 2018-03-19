/**   
* Filename:    PrizeResultDiscountEntityExtend.java   
* @version:    1.0  
* Create at:   2015年6月10日 上午11:27:17   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * Filename:    PrizeResultDiscountEntityExtend.java
 * @version:    1.0.0
 * Create at:   2015年6月10日 上午11:27:17
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月10日       shiyl             1.0             1.0 Version
 */
public class PrizeResultDiscountEntityExtend extends PrizeResultDiscountEntity{

	/**
	 * @param discountValueEntity
	 * @param prizeSurpriseGift
	 * @param prizePreConditionValidateResult
	 */
	public PrizeResultDiscountEntityExtend(DiscountValueEntity discountValueEntity,
			PrizeRecordTmpData prizeRecordTmpData) {
		super(discountValueEntity, prizeRecordTmpData);
	}

	/**
	 * 上次抽奖折扣
	 */
	private Double lastDiscount;
	public Double getLastDiscount() {
		return lastDiscount;
	}
	public void setLastDiscount(Double lastDiscount) {
		this.lastDiscount = lastDiscount;
	}
	
	public BigInteger getTmpPrizeRecordId() {
		return ((PrizeRecordTmpData)baseSurpriseGift).getId();
	}
//	private void setTmpPrizeRecordId(BigInteger tmpPrizeRecordId) {
//		this.tmpPrizeRecordId = tmpPrizeRecordId;
//	}
	
	public PrizeRecordTmpData getPrizeRecordTmpData() {
		return (PrizeRecordTmpData)baseSurpriseGift;
	}
	
}
