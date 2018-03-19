/**   
* Filename:    LoginSessionKeyParamParser.java   
* @version:    1.0  
* Create at:   2014年6月11日 上午3:05:06   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.login.entity.LoginSessionKeyConfig;

/**
 * Filename:    LoginSessionKeyParamParser.java
 * @version:    1.0.0
 * Create at:   2014年6月11日 上午3:05:06
 * Description:登录的SessionKey配置
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月11日       shiyl             1.0             1.0 Version
 */
public class LoginSessionKeyParamParser extends AbstractSysParamParser{
	private Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings("unchecked")
	@Override
	protected LoginSessionKeyConfig doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		Integer expiredMinute = Integer.parseInt(params[0]);
		logger.debug("LoginSessionKey expiredMinute is:"+expiredMinute);
		LoginSessionKeyConfig appApplyEntity = new LoginSessionKeyConfig(expiredMinute);
		return appApplyEntity;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.LOGIN_SESSION_KEY;
	}
	
}
