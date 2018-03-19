/**   
* Filename:    VersionTransferUtil.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午8:03:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.util;

/**
 * Filename:    VersionTransferUtil.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午8:03:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public class VersionTransferUtil {
	private static final int areaSize = 100;
	public static void main(String[] args) {
		String s = "1.0.0";
		Long dataL = versionStr2Long(s);
		System.out.println(dataL);
		System.out.println(long2VersionStr(dataL));
	}
	/**
	 * 将Version格式a.b.c转为Long类型
	 * @param version
	 * @return
	 */
	public static Long versionStr2Long(String version){
		if(version == null) {
			return null;
		}
		String[] versionArr = version.split("\\.");
		Long a = Long.parseLong(versionArr[0])*areaSize*areaSize;
		Long b = Long.parseLong(versionArr[1])*areaSize;
		Long c = Long.parseLong(versionArr[2].substring(0, 1));
		return a + b + c;
	}
	
	public static String long2VersionStr(Long longData){
		Long a = longData/(areaSize*areaSize);
		Long b = (longData-a*(areaSize*areaSize))/areaSize;
		Long c = longData-a*(areaSize*areaSize)-b*areaSize;
		StringBuffer sbf =  new StringBuffer().append(a).append(".").append(b).append(".").append(c);
		return sbf.toString();
	}
}
