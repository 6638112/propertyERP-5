/**   
* Filename:    ValicodeSysParamParser.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午8:56:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.login.entity.ValicodeParamConfig;

/**
 * Filename:    ValicodeSysParamParser.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午8:56:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public class ValicodeSysParamParser extends AbstractSysParamParser{
	private Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 获取验证码参数配置
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ValicodeParamConfig doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		long timeOut = Integer.parseInt(params[0])*60*1000;//分钟转毫秒
		int errCount = Integer.parseInt(params[1]);
		logger.debug("valicodeParamConfig is:timeOut is :"+timeOut+",errCount is :"+errCount);
		ValicodeParamConfig valicodeParamConfig = new ValicodeParamConfig(timeOut, errCount);
		return valicodeParamConfig;
	}


	@Override
	protected String getSysParamKey() {
		return SysParamKey.VALICODE_RULE;
	}
	
}
