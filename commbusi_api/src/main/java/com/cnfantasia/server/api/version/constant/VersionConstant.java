/**   
 * Filename:    VersionConstant.java   
 * @version:    1.0  
 * Create at:   2014年8月8日 上午7:21:32   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.version.constant;

import java.math.BigInteger;

/**
 * Filename: VersionConstant.java
 * 
 * @version: 1.0.0 Create at: 2014年8月8日 上午7:21:32 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月8日 shiyl 1.0 1.0 Version
 */
public class VersionConstant {
	/** 安卓应用下载Id */
	public static final BigInteger AndroidDownAppId = new BigInteger("101");
	/** IOS应用下载Id */
	public static final BigInteger IOSDownAppId = new BigInteger("102");
	/**
	 * 师傅端-安卓应用下载Id
	 */
	public static final BigInteger Master_AndroidDownAppId = new BigInteger("201");
	/**
	 * 师傅端-IOS应用下载Id
	 */
	public static final BigInteger Master_IOSDownAppId = new BigInteger("202");

    /**
     * 万能插下载Id
     */
    public static final BigInteger Might_PluginId = new BigInteger("301");
	
}
