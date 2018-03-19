package com.cnfantasia.server.ms.logger.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ISysLoggermsManager<T> {
	/**
	 * 执行日志处理
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex01
	 */
	public void doLoggerRecord(HttpServletRequest request, HttpServletResponse response,Object handler,Exception ex01);

}
