/**   
* Filename:    PrizeSurpriseGiftEntityDetail.java   
* @version:    1.0  
* Create at:   2015年9月18日 下午3:53:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.jfq.share.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;
import com.cnfantasia.server.domainbase.msPrizeGiftCode.entity.MsPrizeGiftCode;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    PrizeSurpriseGiftEntityDetail.java
 * @version:    1.0.0
 * Create at:   2015年9月18日 下午3:53:49
 * Description: 奖项详情，包含礼品基础信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月18日       shiyl             1.0             1.0 Version
 */
public class PrizeSurpriseGiftEntityDetail extends PrizeSurpriseGiftEntity{
	private static final long serialVersionUID = 1L;
	
	private MsPrizeOption msPrizeOption;
	public MsPrizeOption getMsPrizeOption() {
		return msPrizeOption;
	}
	public void setMsPrizeOption(MsPrizeOption msPrizeOption) {
		this.msPrizeOption = msPrizeOption;
	}
	
	private MsPrizeGift msPrizeGift;
	public MsPrizeGift getMsPrizeGift() {
		return msPrizeGift;
	}
	public void setMsPrizeGift(MsPrizeGift msPrizeGift) {
		this.msPrizeGift = msPrizeGift;
	}
	
	private List<MsPrizeGiftCode> msPrizeGiftCodeList;
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeList() {
		return msPrizeGiftCodeList;
	}
	public void setMsPrizeGiftCodeList(List<MsPrizeGiftCode> msPrizeGiftCodeList) {
		this.msPrizeGiftCodeList = msPrizeGiftCodeList;
	}
	
	public String getTicketValue(){
		if(msPrizeGiftCodeList==null||msPrizeGiftCodeList.size()<=0){return "";}
		StringBuffer sbf = new StringBuffer();
		for(MsPrizeGiftCode tmpCode:msPrizeGiftCodeList){
			sbf.append(tmpCode.getCodeName());
			sbf.append("&nbsp;");
			sbf.append(tmpCode.getValueCode());
			sbf.append("<br/>");
		}
		return sbf.toString();
	}
}
