/**   
* Filename:    MsPrizeActivityForList.java   
* @version:    1.0  
* Create at:   2015年9月11日 下午6:29:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;

/**
 * Filename:    MsPrizeActivityForList.java
 * @version:    1.0.0
 * Create at:   2015年9月11日 下午6:29:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月11日       shiyl             1.0             1.0 Version
 */
public class MsPrizeActivityForList extends MsPrizeActivity{
	private static final long serialVersionUID = 1L;
	
	/**活动实时状态*/
	private Integer qryStatus;
	public Integer getQryStatus() {
		return qryStatus;
	}
	public void setQryStatus(Integer qryStatus) {
		this.qryStatus = qryStatus;
	}
	
}
