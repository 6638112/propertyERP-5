/**   
* Filename:    IOSDeviceKeyWordsParamParser.java   
* @version:    1.0  
* Create at:   2014年8月12日 上午2:48:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    IOSDeviceKeyWordsParamParser.java
 * @version:    1.0.0
 * Create at:   2014年8月12日 上午2:48:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月12日       shiyl             1.0             1.0 Version
 */
public class IOSDeviceKeyWordsParamParser extends  AbstractSysParamParser{

	@SuppressWarnings("unchecked")
	@Override
	protected List<String> doParse(String sysParamValue) {
		List<String> resList = new ArrayList<String>();
		String[] params = sysParamValue.split(";");
		for(String s:params){
			if(!StringUtils.isEmpty(s)){
				resList.add(s);
			}
		}
		return resList;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.IOS_Device_KeyWords;
	}
	
}
