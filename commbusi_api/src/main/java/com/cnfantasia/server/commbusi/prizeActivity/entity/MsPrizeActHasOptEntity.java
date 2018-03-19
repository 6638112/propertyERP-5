/**   
* Filename:    MsPrizeActHasOptEntity.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午8:29:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import com.cnfantasia.server.domainbase.msPrizeActHasOpt.entity.MsPrizeActHasOpt;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    MsPrizeActHasOptEntity.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午8:29:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public class MsPrizeActHasOptEntity extends MsPrizeActHasOpt{
	private static final long serialVersionUID = 1L;
	/**奖项基本信息*/
	private MsPrizeOption msPrizeOption;
	public MsPrizeOption getMsPrizeOption() {
		return msPrizeOption;
	}
	public void setMsPrizeOption(MsPrizeOption msPrizeOption) {
		this.msPrizeOption = msPrizeOption;
	}
	
	
}
