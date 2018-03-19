/**   
 * Filename:    CommSysLoggerDict.java   
 * @version:    1.0  
 * Create at:   2014年8月11日 上午9:04:17   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月11日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commSysLogger.constant;

/**
 * Filename: CommSysLoggerDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月11日 上午9:04:17 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月11日 shiyl 1.0 1.0 Version
 */
public class CommSysLoggerDict {
	/**
	 * 日志控制表-是否忽略请求的参数信息
	 */
	public static class CommLoggerControl_ignoreParams {
		/** 是 */
		public static final Integer YES = 1;
	}

	/**
	 * 日志控制表-是否忽略返回的结果信息
	 */
	public static class CommLoggerControl_ignoreResponse {
		/** 是 */
		public static final Integer YES = 1;
	}

	/**
	 * 日志表-是否为未处理的异常
	 */
	public static class CommLogger_isUndefinedException {
		/** 是 */
		public static final Integer YES = 1;
		/** 否 */
		public static final Integer NO = 2;
	}

	/**
	 * 日志表-是否需要记录日志
	 */
	public static class CommLogger_needRecord {
		/** 否 */
		public static final Integer NO = 2;
	}

}
