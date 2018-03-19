/**   
* Filename:    AbstractSysParamParser.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午8:50:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.omsCommonSysParam.parser;

import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;


/**
 * Filename:    AbstractSysParamParser.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午8:50:48
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractSysParamParser implements IOmsSysParamParser {
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	@Override
	public <T> T parseParamValue() {
		String sysParamKey = getSysParamKey();
		String sysParamValue = sysParamManager.getSysParaValue(sysParamKey);
		return doParse(sysParamValue);
	}
	protected abstract <T> T doParse(String sysParamValue);
	protected abstract String getSysParamKey();
}
