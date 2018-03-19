/**   
* Filename:    ISysLoggerManager.java   
* @version:    1.0  
* Create at:   2014年8月5日 上午3:06:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.commSysLogger.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filename:    ISysLoggerManager.java
 * @version:    1.0.0
 * Create at:   2014年8月5日 上午3:06:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月5日       shiyl             1.0             1.0 Version
 */
public interface ISysLoggerManager<T> {//T : CommLogger
	/**
	 * 新增日志记录
	 * @param commLogger
	 */
	public void insertLog2Catche(T commLogger);
	
	/**
	 * 获取当前缓存的日志记录数
	 * @return 
	 */
	public int getCatcheLogSize();
	
	/**
	 * 将缓存的数据同步到数据库
	 * 默认创建子线程执行日志处理
	 */
	public void synch2Database();
	/**
	 * 使用当前主线程执行
	 */
	public void synch2DatabaseCurrThread();
	
	/**
	 * 刷新缓存数据
	 */
	public void freshInitData();
	
	/**
	 * 判断是否忽略参数记录
	 * @param urlId
	 * @return true 忽略，false不忽略
	 */
	public boolean checkIfIgnoreParams(BigInteger urlId);
	
	/**
	 * 判断是否忽略返回数据
	 * @param urlId
	 * @return true 忽略，false不忽略
	 */
	public boolean checkIfIgnoreResponseData(BigInteger urlId);
	
	
	/**
	 * 判断是否需要记录日志
	 * @param urlId
	 * @return true需要记录 false不需要记录
	 */
	public boolean checkIfNeedRecord(BigInteger urlId);
	
	/**
	 * 执行日志处理
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex01
	 */
	public void doLoggerRecord(HttpServletRequest request, HttpServletResponse response,Object handler,Exception ex01);
}
