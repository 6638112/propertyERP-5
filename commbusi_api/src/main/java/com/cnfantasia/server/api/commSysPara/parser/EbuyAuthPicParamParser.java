/**   
* Filename:    EbuyAuthPicParamParser.java   
* @version:    1.0  
* Create at:   2014年6月15日 上午7:49:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.entity.EbuyAuthPicConfig;

/**
 * Filename:    EbuyAuthPicParamParser.java
 * @version:    1.0.0
 * Create at:   2014年6月15日 上午7:49:37
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月15日       shiyl             1.0             1.0 Version
 */
public class EbuyAuthPicParamParser extends AbstractSysParamParser{
//	private Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings("unchecked")
	@Override
	protected EbuyAuthPicConfig doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		String authIcoBasePath = params[0];
		String picAuthFilePath = params[1];
//		logger.debug("authIcoBasePath url is:"+authIcoBasePath+",picAuthFilePath is :"+picAuthFilePath);
		EbuyAuthPicConfig euyAuthPicConfig = new EbuyAuthPicConfig(authIcoBasePath, picAuthFilePath);
		return euyAuthPicConfig;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.EBUY_AUTH_PIC_CONFIG;
	}

}
