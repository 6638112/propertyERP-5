/**   
 * Filename:    VersionDict.java   
 * @version:    1.0  
 * Create at:   2014年8月11日 上午8:51:52   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月11日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.version.constant;

/**
 * Filename: VersionDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月11日 上午8:51:52 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月11日 shiyl 1.0 1.0 Version
 */
public class VersionDict {
	/**
	 * 应用是否需要强制更新
	 */
	public static class AppVersion_ForceUpdate {
		/** "0":"否" */
		public static final Integer FALSE = 0;
		/** "1":"是" */
		public static final Integer TRUE = 1;
	}
	
	/**
	 * 灰度发布类别=={"1":"全部","2":"特殊用户"}
	 */
	public static class AppVersion_GateType {
		/** "1":"全部" */
		public static final Integer ALL = 1;
		/** "2":"特殊用户" */
		public static final Integer GateUser = 2;
	}
	
}
