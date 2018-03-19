/**   
* Filename:    DiscountValueEntity.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午7:53:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleConstant;
import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * Filename:    DiscountValueEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午7:53:26
 * Description:折扣信息对象
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public class DiscountValueEntity {
	private static final Long DEFAULT_PRIZE_DISCOUNT_DIVNUM = PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM;
	public static final DiscountValueEntity unPrizedEntity = new DiscountValueEntity(99L,10L);//TODO 需要传入两个参数
	
	/**折扣整数取值*/
	private Long discount;
	/**折扣需要除掉的值 */
	private Long divNum;
	
	/**
	 * 构造方法
	 * @param discount
	 * @param divNum
	 */
	public DiscountValueEntity(Long discount,Long divNum){
		this.discount = discount;
		this.divNum = divNum;
	}
	public DiscountValueEntity(Long discount){
		this.discount = discount;
		this.divNum = DEFAULT_PRIZE_DISCOUNT_DIVNUM;
	}
	public Double doubleValue(){//9.800
		return PriceUtil.divByDivNum(discount, divNum).doubleValue();
	}
	
	public Integer multiplyValue(Long toMulValue){//例如toMulValue=10时,98
		return PriceUtil.divByDivNum(discount*toMulValue, divNum).intValue();
	}
	
	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return this.doubleValue().toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((divNum == null) ? 0 : divNum.hashCode());
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
		DiscountValueEntity other = (DiscountValueEntity) obj;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (divNum == null) {
			if (other.divNum != null)
				return false;
		} else if (!divNum.equals(other.divNum))
			return false;
		return true;
	}
	
	
}
