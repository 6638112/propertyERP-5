/**   
* Filename:    CommunityForumTypeIcoParamParser.java   
* @version:    1.0  
* Create at:   2014年7月22日 上午9:48:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;

/**
 * Filename:    CommunityForumTypeIcoParamParser.java
 * @version:    1.0.0
 * Create at:   2014年7月22日 上午9:48:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月22日       shiyl             1.0             1.0 Version
 */
public class CommunityForumTypeIcoParamParser extends AbstractSysParamParser{

	@SuppressWarnings("unchecked")
	@Override
	protected String doParse(String sysParamValue) {
		return sysParamValue;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.CommunityForum_Type_Ico_BasePath;
	}

}
