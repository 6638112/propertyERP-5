/**   
* Filename:    LoginLogQuery.java   
* @version:    1.0  
* Create at:   2014年5月12日 上午6:16:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

import com.cnfantasia.server.domainbase.loginLog.entity.LoginLog;

/**
 * Filename:    LoginLogQuery.java
 * @version:    1.0.0
 * Create at:   2014年5月12日 上午6:16:59
 * Description:登录日志查询条件
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月12日       shiyl             1.0             1.0 Version
 */
public class LoginLogQuery extends LoginLog{
	private static final long serialVersionUID = 1L;
	/**查询抽奖时间：开始*/
	private String loginDay_START;//此处命名需与Mybatis的sql处理保持一致
	/**查询抽奖时间：结束*/
	private String loginDay_END;
	public String getLoginDay_START() {
		return loginDay_START;
	}
	public void setLoginDay_START(String loginDay_START) {
		this.loginDay_START = loginDay_START;
	}
	public String getLoginDay_END() {
		return loginDay_END;
	}
	public void setLoginDay_END(String loginDay_END) {
		this.loginDay_END = loginDay_END;
	}
	
}
