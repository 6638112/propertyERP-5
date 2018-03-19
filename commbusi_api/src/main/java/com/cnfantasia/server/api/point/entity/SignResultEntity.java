/**   
* Filename:    SignResultEntity.java   
* @version:    1.0  
* Create at:   2015年1月7日 上午2:55:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.entity;

import java.io.Serializable;

import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * Filename:    SignResultEntity.java
 * @version:    1.0.0
 * Create at:   2015年1月7日 上午2:55:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月7日       shiyl             1.0             1.0 Version
 */
public class SignResultEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**签到记录*/
	private CommSignRecord commSignRecord;
	public CommSignRecord getCommSignRecord() {
		return commSignRecord;
	}
	public void setCommSignRecord(CommSignRecord commSignRecord) {
		this.commSignRecord = commSignRecord;
	}
	
	
	/**当次签到状态 true表示当天首次签到 false表示非首次签到*/
	private Boolean todayFirst;
	public Boolean getTodayFirst() {
		return todayFirst;
	}
	public void setTodayFirst(Boolean todayFirst) {
		this.todayFirst = todayFirst;
	}
	/**返回是否已经签到*/
	public boolean getIsFetched(){
		if(todayFirst!=null&&todayFirst==false){
			return true;
		}else{
			return false;
		}
	}
	
}
