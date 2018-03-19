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
package com.cnfantasia.server.ms.pub.sysParam;

import javax.annotation.Resource;

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
public abstract class AbstractSysParamParser implements ISysParamParser{

	@Resource
	private ISysParamManager sysParamManager;

	@Override
	public <T> T parseParamValue() {
		String sysParamKey = getSysParamKey();
		String sysParamValue = sysParamManager.getSysParaValue(sysParamKey);
		return doParse(sysParamValue);
	}
	protected abstract <T> T doParse(String sysParamValue);
	protected abstract String getSysParamKey();
}
