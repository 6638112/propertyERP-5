/**   
* Filename:    HobbyEntity.java   
* @version:    1.0  
* Create at:   2014年10月11日 上午3:33:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.user.entity;

import com.cnfantasia.server.api.user.constant.UserDict;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;

/**
 * Filename:    HobbyEntity.java
 * @version:    1.0.0
 * Create at:   2014年10月11日 上午3:33:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月11日       shiyl             1.0             1.0 Version
 */
public class HobbyEntity extends Hobby{
	private static final long serialVersionUID = 1L;
	
	/**是否已选择的标识*/
	private Integer haveSelectFlag;
	public Integer getHaveSelectFlag() {
		return haveSelectFlag;
	}
	public void setHaveSelectFlag(Integer haveSelectFlag) {
		this.haveSelectFlag = haveSelectFlag;
	}
	
	public boolean fetchIsSelected(){
		boolean resBool = false;
		if(haveSelectFlag!=null&&UserDict.Hobby_HaveSelectFlag.YES.compareTo(haveSelectFlag)==0){
			resBool = true;
		}
		return resBool;
	}
	
}
