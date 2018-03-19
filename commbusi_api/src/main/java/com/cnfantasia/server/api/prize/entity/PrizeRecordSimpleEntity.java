/**   
* Filename:    PrizeRecordSimpleEntity.java   
* @version:    1.0  
* Create at:   2014年8月15日 上午8:54:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * Filename:    PrizeRecordSimpleEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月15日 上午8:54:49
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月15日       shiyl             1.0             1.0 Version
 */
public class PrizeRecordSimpleEntity extends PrizeRecord{

	private static final long serialVersionUID = 1L;
	/**
	 * 记录是否已经被使用
	 * @return
	 */
	public Boolean hasUsed(){
		Boolean usedState = null;
		if(this.getStatus()!=null){
			if(PrizeDict.PrizeRecord_Status.IS_USED.compareTo(this.getStatus())==0){
				usedState = true;
			}else if(PrizeDict.PrizeRecord_Status.NOT_USE.compareTo(this.getStatus())==0){
				usedState = false;
			}
		}
		return usedState;
	}
	
}
