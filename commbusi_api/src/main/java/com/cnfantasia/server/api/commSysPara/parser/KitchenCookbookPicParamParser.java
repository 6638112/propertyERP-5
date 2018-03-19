/**   
* Filename:    KitchenCookbookPicParamParser.java   
* @version:    1.0  
* Create at:   2014年7月28日 上午9:44:04   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;

/**
 * Filename:    KitchenCookbookPicParamParser.java
 * @version:    1.0.0
 * Create at:   2014年7月28日 上午9:44:04
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月28日       shiyl             1.0             1.0 Version
 */
public class KitchenCookbookPicParamParser extends AbstractSysParamParser{

	@SuppressWarnings("unchecked")
	@Override
	protected String doParse(String sysParamValue) {
		return sysParamValue;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.KITCHEN_COOKBOOK_PIC_BASEPATH;
	}
	
}
