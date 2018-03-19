/**   
* Filename:    BaiduPushParamParser.java   
* @version:    1.0  
* Create at:   2014年9月22日 上午2:42:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.msgpush.entity.BaiduPushConfigEntity;

/**
 * Filename:    BaiduPushParamParser.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午2:42:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public class BaiduPushParamParser extends AbstractSysParamParser{
	private Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	protected BaiduPushConfigEntity doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		String apiUrl = params[0];
		String apiKey = params[1];
		String secretKey = params[2];
		logger.debug("BaiduPush apiUrl is:"+apiUrl+",apiKey is :"+apiKey+",secretKey is :"+secretKey);
		BaiduPushConfigEntity tmpEntity = new BaiduPushConfigEntity(apiUrl, apiKey, secretKey);
		return tmpEntity;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.BAIDU_PUSH_CONFIG;
	}

}
