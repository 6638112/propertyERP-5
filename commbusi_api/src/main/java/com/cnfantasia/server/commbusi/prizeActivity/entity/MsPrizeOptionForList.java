/**   
* Filename:    MsPrizeOptionForList.java   
* @version:    1.0  
* Create at:   2015年9月15日 上午11:44:56   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    MsPrizeOptionForList.java
 * @version:    1.0.0
 * Create at:   2015年9月15日 上午11:44:56
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月15日       shiyl             1.0             1.0 Version
 */
public class MsPrizeOptionForList extends MsPrizeOption{
	private static final long serialVersionUID = 1L;
	
	/**活动奖项状态*/
	private Integer qryStatus;
	public Integer getQryStatus() {
		return qryStatus;
	}
	public void setQryStatus(Integer qryStatus) {
		this.qryStatus = qryStatus;
	}
	
	/**是否被使用:1未使用2已使用*/
	private Integer useStatus;
	public Integer getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	
}
