/**   
* Filename:    NewPushParamParser.java   
* @version:    1.0  
* Create at:   2015年10月22日 下午2:27:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;

/**
 * Filename:    NewPushParamParser.java
 * @version:    1.0.0
 * Create at:   2015年10月22日 下午2:27:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月22日       shiyl             1.0             1.0 Version
 */
public class BaiduPushParamMasterAndroidParser extends BaiduPushParamParser {

	@Override
	protected String getSysParamKey() {
		return SysParamKey.Baidu_PUSH_CONFIG_Master_Android;
	}
	
}
