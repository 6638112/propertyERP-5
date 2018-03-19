/**   
* Filename:    LoginInfoWithBindEntity.java   
* @version:    1.0  
* Create at:   2015年5月7日 上午7:58:58   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.accountManage.entity;

import java.io.Serializable;
import java.util.List;

import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * Filename:    LoginInfoWithBindEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月7日 上午7:58:58
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月7日       shiyl             1.0             1.0 Version
 */
public class LoginInfoWithBindEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**登录账号Id对应的详情*/
	private LoginNoSimpleEntity loginNoSimpleEntity;
	
	/**用户绑定的账号列表*/
	private List<LoginNo> loginNoList;
	
	public LoginInfoWithBindEntity(LoginNoSimpleEntity loginNoSimpleEntity,List<LoginNo> loginNoList){
		this.loginNoSimpleEntity = loginNoSimpleEntity;
		this.loginNoList = loginNoList;
	}
	
	public LoginNoSimpleEntity getLoginNoSimpleEntity() {
		return loginNoSimpleEntity;
	}

	public void setLoginNoSimpleEntity(LoginNoSimpleEntity loginNoSimpleEntity) {
		this.loginNoSimpleEntity = loginNoSimpleEntity;
	}

	public List<LoginNo> getLoginNoList() {
		return loginNoList;
	}

	public void setLoginNoList(List<LoginNo> loginNoList) {
		this.loginNoList = loginNoList;
	}
	
	
}
