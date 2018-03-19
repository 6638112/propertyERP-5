/**   
* Filename:    SimpleHbDiscountEntity.java   
* @version:    1.0  
* Create at:   2014年6月26日 上午2:36:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * Filename:    SimpleHbDiscountEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月26日 上午2:36:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月26日       shiyl             1.0             1.0 Version
 */
public class SimpleHbDiscountEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**折扣*/
	private PrizeRecordEntity prizeRecord;
	/**兑换得到的金额*/
	private Long moneyLong;
	/**是否可兑换*/
	private Boolean isConvertAble;
	/**是否已经兑换*/
	private Boolean hasConvert;
//	/**剩余多少天即可兑换*/
//	private Integer leftDay2Convert;
	/**不可兑换的原因标识*/
	private Integer unConvertAbleReasonFlag;
	
	/**这个被使用的类别,例如是缴物业费还是兑换了粮票 参见 com.cnfantasia.server.api.prize.constant.PrizeDict.PrizeRecord_UsedType*/
	private Integer discountUsedType;
	
	public BigDecimal getMoneyYuan(){
		if(moneyLong==null){return BigDecimal.valueOf(0L);}
		BigDecimal moneyYuan = PriceUtil.div100(moneyLong);
		return moneyYuan;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public SimpleHbDiscountEntity(Boolean hasConvert,PrizeRecordEntity prizeRecord,Long moneyLong
			,Boolean isConvertAble/*,Integer leftDay2Convert*/,Integer unConvertAbleReasonFlag,Integer discountUsedType){
		this.hasConvert = hasConvert;
		this.prizeRecord = prizeRecord;
		this.moneyLong = moneyLong;
		this.isConvertAble = isConvertAble;
//		this.leftDay2Convert = leftDay2Convert;
		this.unConvertAbleReasonFlag = unConvertAbleReasonFlag;
		this.discountUsedType = discountUsedType;
	}
	
	public PrizeRecordEntity getPrizeRecord() {
		return prizeRecord;
	}

	public void setPrizeRecord(PrizeRecordEntity prizeRecord) {
		this.prizeRecord = prizeRecord;
	}

	public Long getMoneyLong() {
		return moneyLong;
	}
	public void setMoneyLong(Long moneyLong) {
		this.moneyLong = moneyLong;
	}
	

//	public Integer getLeftDay2Convert() {
//		return leftDay2Convert;
//	}
//
//	public void setLeftDay2Convert(Integer leftDay2Convert) {
//		this.leftDay2Convert = leftDay2Convert;
//	}

	public Boolean getIsConvertAble() {
		return isConvertAble;
	}

	public void setIsConvertAble(Boolean isConvertAble) {
		this.isConvertAble = isConvertAble;
	}

	public Boolean getHasConvert() {
		return hasConvert;
	}

	public void setHasConvert(Boolean hasConvert) {
		this.hasConvert = hasConvert;
	}

	public Integer getUnConvertAbleReasonFlag() {
		return unConvertAbleReasonFlag;
	}

	public void setUnConvertAbleReasonFlag(Integer unConvertAbleReasonFlag) {
		this.unConvertAbleReasonFlag = unConvertAbleReasonFlag;
	}

	public Integer getDiscountUsedType() {
		return discountUsedType;
	}

	public void setDiscountUsedType(Integer discountUsedType) {
		this.discountUsedType = discountUsedType;
	}
	
	/**已使用折扣缴纳物业费节省的金额*/
	private Long disCountSavedMoney_bill;
	public Long getDisCountSavedMoney_bill() {
		return disCountSavedMoney_bill;
	}
	public void setDisCountSavedMoney_bill(Long disCountSavedMoney_bill) {
		this.disCountSavedMoney_bill = disCountSavedMoney_bill;
	}
	
}
