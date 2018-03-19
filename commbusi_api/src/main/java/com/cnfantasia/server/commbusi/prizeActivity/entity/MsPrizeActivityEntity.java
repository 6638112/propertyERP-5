/**   
* Filename:    MsPrizeActivityEntity.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午8:18:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;

/**
 * Filename:    MsPrizeActivityEntity.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午8:18:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public class MsPrizeActivityEntity extends MsPrizeActivity{
	private static final long serialVersionUID = 1L;
	
	/**包含的奖项列表*/
	private List<MsPrizeActHasOptEntity> msPrizeActHasOptList;
	public List<MsPrizeActHasOptEntity> getMsPrizeActHasOptList() {
		return msPrizeActHasOptList;
	}
	public void setMsPrizeActHasOptList(List<MsPrizeActHasOptEntity> msPrizeActHasOptList) {
		this.msPrizeActHasOptList = msPrizeActHasOptList;
	}
	
	private String test;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
	
	
}
