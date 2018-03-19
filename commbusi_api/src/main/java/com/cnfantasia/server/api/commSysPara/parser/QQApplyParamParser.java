/**   
* Filename:    QQApplyParamParser.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午9:04:39   
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
import com.cnfantasia.server.api.login.entity.QQApplyEntity;

/**
 * Filename:    QQApplyParamParser.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午9:04:39
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public class QQApplyParamParser extends AbstractSysParamParser{
	private Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	protected QQApplyEntity doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		String url = params[0];
		String appKey = params[1];
		String secret = params[2];
		logger.debug("QQ url is:"+url+",appKey is :"+appKey+",secret is :"+secret);
		QQApplyEntity qqApplyEntity = new QQApplyEntity(url, appKey, secret);
		return qqApplyEntity;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.APP_APPLY_INFO_QQ;
	}
	
}
