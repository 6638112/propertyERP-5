/**   
* Filename:    PrizeIgnoreUsedEntity.java   
* @version:    1.0  
* Create at:   2015年7月27日 下午3:06:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.prize.constant.PrizeDict.PrizeRecord_UsedType;
import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * Filename:    PrizeIgnoreUsedEntity.java
 * @version:    1.0.0
 * Create at:   2015年7月27日 下午3:06:23
 * Description:判断折扣是否已使用，忽略最低折扣是否使用
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月27日       shiyl             1.0             1.0 Version
 */
public class PrizeIgnoreUsedEntity {
	/**是否已使用*/
	private boolean isUsed;
	/**使用获得的金额*/
	private Long savedMoney;
	/**使用的方式*/
	private Integer usedType;
	/**当前折扣是否为最低*/
	private boolean isLeastDiscount;
	
	public PrizeIgnoreUsedEntity(){
		this.isUsed = false;
		this.savedMoney = null;
		this.usedType = null;
		this.isLeastDiscount = false;
	}
	
	public PrizeIgnoreUsedEntity(boolean isUsed,Long savedMoney,Integer usedType,boolean isLeastDiscount){
		this.isUsed = isUsed;
		this.savedMoney = savedMoney;
		this.usedType = usedType;
		this.isLeastDiscount = isLeastDiscount;
	}
	
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public Long getSavedMoney() {
		return savedMoney;
	}
	public void setSavedMoney(Long savedMoney) {
		this.savedMoney = savedMoney;
	}
	public Integer getUsedType() {
		return usedType;
	}
	public void setUsedType(Integer usedType) {
		this.usedType = usedType;
	}
	public boolean isLeastDiscount() {
		return isLeastDiscount;
	}
	public void setLeastDiscount(boolean isLeastDiscount) {
		this.isLeastDiscount = isLeastDiscount;
	}
	
	public String getDesc(){
		StringBuffer resStr = new StringBuffer();
		if(isUsed&&usedType!=null){
			if(usedType.compareTo(PrizeRecord_UsedType.Plotproperty)==0){
				resStr.append(createText("您冲抵物业费", savedMoney));
			}else if(usedType.compareTo(PrizeRecord_UsedType.Redenvelope)==0){
				resStr.append(createText("您已兑换粮票", savedMoney));
			}
			
		}
		return resStr.toString();
	}
	
	private String createText(String text,Long money){
		StringBuffer resStr = new StringBuffer();
		resStr.append("<span style=\"color:#fff;font-size:15px\">");
		resStr.append(text);
		resStr.append("</span>");
		if(money!=null){
			resStr.append("<span style=\"color:#f29708;font-size:15px\">");
			resStr.append("￥"+PriceUtil.div100(savedMoney));
			resStr.append("</span>");
		}
		return resStr.toString();
	}
	
}
