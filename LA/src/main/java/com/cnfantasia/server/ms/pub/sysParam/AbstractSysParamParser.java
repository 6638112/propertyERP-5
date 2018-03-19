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

	@Override
	public <T> T parseParamValue() {
		String sysParamKey = getSysParamKey();
		String sysParamValue = SysParamManager.getSysParaValue(sysParamKey);
		return doParse(sysParamValue);
	}
	protected abstract <T> T doParse(String sysParamValue);
	protected abstract String getSysParamKey();
}
