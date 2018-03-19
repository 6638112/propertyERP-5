/**   
* Filename:    WeiXinLoginConfigParamParser.java   
* @version:    1.0  
* Create at:   2014年8月12日 上午2:50:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;

/**
 * Filename:    WeiXinLoginConfigParamParser.java
 * @version:    1.0.0
 * Create at:   2014年8月12日 上午2:50:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月12日       shiyl             1.0             1.0 Version
 */
public class WeiXinLoginConfigParamParser extends AbstractSysParamParser{

	@SuppressWarnings("unchecked")
	@Override
	protected String doParse(String sysParamValue) {
		return sysParamValue;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.WeiXin_Login_Config;
	}
	
}
