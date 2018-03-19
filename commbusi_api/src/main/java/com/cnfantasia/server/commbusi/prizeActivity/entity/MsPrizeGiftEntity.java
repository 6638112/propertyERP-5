/**   
* Filename:    MsPrizeGiftEntity.java   
* @version:    1.0  
* Create at:   2015年9月16日 下午2:13:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;
import com.cnfantasia.server.domainbase.msPrizeGiftCode.entity.MsPrizeGiftCode;

/**
 * Filename:    MsPrizeGiftEntity.java
 * @version:    1.0.0
 * Create at:   2015年9月16日 下午2:13:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月16日       shiyl             1.0             1.0 Version
 */
public class MsPrizeGiftEntity extends MsPrizeGift{
	private static final long serialVersionUID = 1L;
	
	/**奖品兑换码列表*/
	private List<MsPrizeGiftCode> msPrizeGiftCodeList;
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeList() {
		return msPrizeGiftCodeList;
	}
	public void setMsPrizeGiftCodeList(List<MsPrizeGiftCode> msPrizeGiftCodeList) {
		this.msPrizeGiftCodeList = msPrizeGiftCodeList;
	}
	
}
