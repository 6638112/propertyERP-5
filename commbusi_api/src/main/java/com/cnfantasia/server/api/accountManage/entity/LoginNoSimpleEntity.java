/**   
* Filename:    LoginNoSimpleEntity.java   
* @version:    1.0  
* Create at:   2015年5月7日 上午3:32:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.accountManage.entity;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * Filename:    LoginNoSimpleEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月7日 上午3:32:34
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月7日       shiyl             1.0             1.0 Version
 */
public class LoginNoSimpleEntity extends LoginNo{
	private static final long serialVersionUID = 1L;
	
	private UserSimpleEntity userSimpleEntity;

	public UserSimpleEntity getUserSimpleEntity() {
		return userSimpleEntity;
	}
	public void setUserSimpleEntity(UserSimpleEntity userSimpleEntity) {
		this.userSimpleEntity = userSimpleEntity;
	}
	
	
}
