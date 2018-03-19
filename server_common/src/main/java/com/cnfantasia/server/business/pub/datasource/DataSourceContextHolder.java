/**   
 * Filename:    DataSourceContextHolder.java   
 * @version:    1.0  
 * Create at:   2014年6月12日 上午12:58:04   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月12日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub.datasource;

/**
 * Filename: DataSourceContextHolder.java
 * 
 * @version: 1.0.0 Create at: 2014年6月12日 上午12:58:04 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月12日 shiyl 1.0 1.0 Version
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		return ((String) contextHolder.get());
	}

	public static void clearDbType() {
		contextHolder.remove();
	}
	
}
