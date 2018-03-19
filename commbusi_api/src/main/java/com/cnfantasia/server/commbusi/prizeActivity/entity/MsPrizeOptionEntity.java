/**   
* Filename:    MsPrizeOptionEntity.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午9:10:14   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.util.List;

import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityConstant;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityDict;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    MsPrizeOptionEntity.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午9:10:14
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public class MsPrizeOptionEntity extends MsPrizeOption{
	private static final long serialVersionUID = 1L;
	/**包含的奖品总数*/
	private Integer priGiftCount;
	public Integer getPriGiftCount() {
		return priGiftCount;
	}
	public void setPriGiftCount(Integer priGiftCount) {
		this.priGiftCount = priGiftCount;
	}
	
	/**派奖范围*/
	private List<String> cityNameList;
	public List<String> getCityNameList() {
		return cityNameList;
	}
	public void setCityNameList(List<String> cityNameList) {
		this.cityNameList = cityNameList;
	}
	
	/**
	 * 是否为全国
	 */
	public Integer fetchIsGlobal() {
		return cityNameList!=null&&cityNameList.contains(PrizeActivityConstant.DEFAULT_PRIOPT_ADDRESS)?PrizeActivityDict.MsPrizeOption_AdressType.GLOBAL:PrizeActivityDict.MsPrizeOption_AdressType.CITY;
	}
	
}
