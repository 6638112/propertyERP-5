/**   
 * Filename:    MybatisSpecialCharFilterUtil.java   
 * @version:    1.0  
 * Create at:   2014年8月5日 上午11:47:09   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月5日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub.utils;

import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: MybatisSpecialCharFilterUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年8月5日 上午11:47:09 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月5日 shiyl 1.0 1.0 Version
 */
public class MybatisSpecialCharFilterUtil {
	
	public static String filterDollarStr(String str) {
		String sReturn = "";
		if (!StringUtils.isEmpty(str)) {
			if (str.indexOf('$', 0) > -1) {
				while (str.length() > 0) {
					if (str.indexOf('$', 0) > -1) {
						sReturn += str.subSequence(0, str.indexOf('$', 0));
						sReturn += "\\$";
						str = str.substring(str.indexOf('$', 0) + 1, str.length());
					} else {
						sReturn += str;
						str = "";
					}
				}
			} else {
				sReturn = str;
			}
		}
		return sReturn;
	}
	
}
