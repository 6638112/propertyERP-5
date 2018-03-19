/**   
* Filename:    HbConvertRuleDescEntity.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午9:41:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.entity;

import java.math.BigDecimal;

import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.redenvelope.util.IDiscount2hbRule;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    HbConvertRuleDescEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午9:41:11
 * Description:粮票兑换规则的单行描述
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public class HbConvertRuleDescEntity {
	
	/**起始折扣*/
	private DiscountValueEntity startDiscount;
	/**截止折扣*/
	private DiscountValueEntity endDiscount;
	
	
	/**
	 * 构造方法
	 * @param startDiscount 起始折扣
	 * @param endDiscount 截止折扣
	 */
	public HbConvertRuleDescEntity(DiscountValueEntity startDiscount,DiscountValueEntity endDiscount){
		this.startDiscount = startDiscount;
		this.endDiscount = endDiscount;
	}
	
	public DiscountValueEntity getStartDiscount() {
		return startDiscount;
	}
	public void setStartDiscount(DiscountValueEntity startDiscount) {
		this.startDiscount = startDiscount;
	}
	public DiscountValueEntity getEndDiscount() {
		return endDiscount;
	}
	public void setEndDiscount(DiscountValueEntity endDiscount) {
		this.endDiscount = endDiscount;
	}
	
	public String getLeftStr(){
		return startDiscount.doubleValue()+"至"+endDiscount.doubleValue();
	}
	
	public String getRightStr(UserHasTRoom currUserHasTRoom,IDiscount2hbRule discount2hbRule){
		BigDecimal start = PriceUtil.div100(discount2hbRule.getMoneyByDiscount(currUserHasTRoom,startDiscount.doubleValue()));
		BigDecimal end = PriceUtil.div100(discount2hbRule.getMoneyByDiscount(currUserHasTRoom,endDiscount.doubleValue()));
		if(start.compareTo(end)==0){
			return start+"";
		}else{
			return 
					start+"至"
					+end;
		}
	}
	
}
