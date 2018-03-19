/**   
* Filename:    CompanyInfoParamParser.java   
* @version:    1.0  
* Create at:   2014年6月11日 下午12:03:35   
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
import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;

/**
 * Filename:    CompanyInfoParamParser.java
 * @version:    1.0.0
 * Create at:   2014年6月11日 下午12:03:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月11日       shiyl             1.0             1.0 Version
 */
public class CompanyInfoParamParser extends AbstractSysParamParser{
	private Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	protected CompanyInfoConfig doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		String tel = params[0];
		String qqGroup = params[1];
		String serviceTel = params[2];
		CompanyInfoConfig appApplyEntity = new CompanyInfoConfig(tel,qqGroup,serviceTel);
		logger.debug("CompanyInfoConfig is:"+appApplyEntity);
		return appApplyEntity;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.COMPANY_INFO;
	}
	
}
