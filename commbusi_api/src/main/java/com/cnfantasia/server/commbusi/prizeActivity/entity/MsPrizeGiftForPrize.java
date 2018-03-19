/**   
* Filename:    MsPrizeGiftForPrize.java   
* @version:    1.0  
* Create at:   2015年9月17日 下午6:36:14   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    MsPrizeGiftForPrize.java
 * @version:    1.0.0
 * Create at:   2015年9月17日 下午6:36:14
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月17日       shiyl             1.0             1.0 Version
 */
public class MsPrizeGiftForPrize extends MsPrizeGift{

	private static final long serialVersionUID = 1L;
	/**所属奖项信息*/
	private MsPrizeOption msPrizeOption;
	public MsPrizeOption getMsPrizeOption() {
		return msPrizeOption;
	}
	public void setMsPrizeOption(MsPrizeOption msPrizeOption) {
		this.msPrizeOption = msPrizeOption;
	}
	
	public String fetchShowCount(){
		Double number = this.getNumber();
		String showCount = number.intValue()==number?(number.intValue()+this.getUnit()):(number+this.getUnit());
		return showCount;
	}
	public String fetchMarketIcon(){
		return msPrizeOption==null?null:msPrizeOption.getIcon();
	}
	
}
