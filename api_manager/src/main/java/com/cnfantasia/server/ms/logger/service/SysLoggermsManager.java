package com.cnfantasia.server.ms.logger.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysLogger.constant.CommSysLoggerDict;
import com.cnfantasia.server.api.pub.exception.ExceptionConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MybatisSpecialCharFilterUtil;
import com.cnfantasia.server.common.exception.IBaseException;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.logger.SysLoggerManagerInterceptor;
import com.cnfantasia.server.ms.logger.SysLoggermsPool;
import com.cnfantasia.server.ms.logger.entity.ManagerLogger;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class SysLoggermsManager implements ISysLoggermsManager<ManagerLogger>{
	private Log logger = LogFactory.getLog(getClass());

	private static final int DEFAULT_CATCHE_SIZE = 100;
	/** 缓存的日志列表 */
	private List<ManagerLogger> logCatcheList;

	/** 日志控制缓存的信息 */
	
	private ISysLoggerMsService sysLoggerService;
	public void setSysLoggerService(ISysLoggerMsService sysLoggerService) {
		this.sysLoggerService = sysLoggerService;
	}

	/**
	 * 缓存的最大记录数
	 */
	private int catcheSize;

	public SysLoggermsManager() {
		this.catcheSize = DEFAULT_CATCHE_SIZE;
	}

	public SysLoggermsManager(int catcheSize) {
		this.catcheSize = catcheSize;
	}

	public void init() {
		logCatcheList = new Vector<ManagerLogger>(this.catcheSize);
	}

	@Override
	public void doLoggerRecord(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex01) {
		Boolean isDoneLoggerFlag = (Boolean) request.getAttribute(SysLoggerManagerInterceptor.REQUEST_KEY_LOGGER_ISDONE_FLAG);
		if (isDoneLoggerFlag != null && isDoneLoggerFlag == true && ex01 == null) {
			return;// 已经记录了日志，且未发现异常
		}
		ManagerLogger commLogger = new ManagerLogger();
		{// 请求地址信息
			String reqUrl = request.getRequestURI();
			String actionUrlTail = reqUrl;
			String actionUrlAll = request.getRequestURL().toString();
			commLogger.setActionUrlTail(actionUrlTail);
			commLogger.setActionUrlAll(actionUrlAll);
			String requestParams = JSON.toJSONString(request.getParameterMap());
			if(requestParams.length() > 300){//参数太大，不要记录太多数据，像JS压缩图片就是用参数值传过来的，不需要记录在日志表中  Added by wenfq 2017-03-30
				commLogger.setRequestParams(requestParams.substring(0, 300) + "...");
			}else{
				commLogger.setRequestParams(requestParams);
			}
			String responseData = (String) request.getAttribute(BaseController.REQUEST_KEY_RESPONSE_DATA);
			commLogger.setResponseData(responseData);
		}
		Exception ex = null;
		String excepStackInfo = null;// errStack
		{// 异常结果信息
			// 率先使用当前返回的异常信息
			ex = ex01;
			if (ex == null) {
				ex = (Exception) request.getAttribute(ExceptionConstant.REQUEST_KEY_EXCEPTION);
			}
			Integer isUndefinedException = null;
			if (ex != null && !(ex instanceof IBaseException)) {// 异常不为空，且不是系统定义的异常
				isUndefinedException = CommSysLoggerDict.CommLogger_isUndefinedException.YES;// 说明异常未定义
			} else {
				isUndefinedException = CommSysLoggerDict.CommLogger_isUndefinedException.NO;
			}
			String actionResStatus = null;// status状态码
			String actionResCode = null;// errCode
			String actionResMsg = null;// errMessage
			if (ex != null) {
				actionResStatus = ExceptionParseUtil.parseStatus(ex, true);
				actionResCode = ExceptionParseUtil.parseErrorCode(ex, true);
				actionResMsg = ExceptionParseUtil.parseErrorMsg(ex, true);
				excepStackInfo = ExceptionParseUtil.parseExceptionMessage(ex);
			}
			commLogger.setActionResCode(actionResCode);
			commLogger.setActionResMsg(actionResMsg);
			commLogger.setActionResStatus(actionResStatus);
			commLogger.setExcepStackInfo(MybatisSpecialCharFilterUtil.filterDollarStr(excepStackInfo));
			commLogger.setIsUndefinedException(isUndefinedException);
		}
		{// 设备信息
			String deviceId = null;// 设备Id
			try {
				deviceId = HeaderManager.getDeviceId();// 设备Id
			} catch (Exception e) {
			}
			String channel = null;
			try {
				channel = HeaderManager.getSubChannelIdLong().toString();
			} catch (Exception e) {
			}
			String version = null;
			try {
				version = HeaderManager.getVersion();
			} catch (Exception e) {
			}
			String ip = request.getRemoteAddr();
			String os = request.getHeader("User-Agent");
			commLogger.setChannel(channel);
			commLogger.setDeviceId(deviceId);
			commLogger.setVersion(version);
			commLogger.setIp(ip);
			commLogger.setOs(os);
		}
		{// 用户信息
			String userAccNo = null;
			Long userAccType = null;
			BigInteger userId = null;
			OmsUser loginNoEntity = UserContext.getCurrUser();
			if (loginNoEntity != null) {
				userId = loginNoEntity.getId();
				userAccNo = loginNoEntity.getUserAccount();
				userAccType = loginNoEntity.getIsadmin().longValue();
			}
			commLogger.setUserAccNo(userAccNo);
			commLogger.setUserAccType(userAccType);
			commLogger.setUserId(userId);
		}
		{// 执行时间
			Date reqStartDate = (Date) request.getAttribute(SysLoggerManagerInterceptor.REQUEST_KEY_STARTTIME);
			Date reqEndDate = new Date();
			Long reqDealTime = reqEndDate.getTime() - reqStartDate.getTime();

			String reqStartTime = DateUtil.formatSecond.get().format(reqStartDate);
			String reqEndTime = DateUtil.formatSecond.get().format(reqEndDate);

			commLogger.setReqStartTime(reqStartTime);
			commLogger.setReqDealTime(reqDealTime);
			commLogger.setReqEndTime(reqEndTime);
		}
		{// 日志信息唯一标识
			String transNo = (String) request.getAttribute(SysLoggerManagerInterceptor.REQUEST_KEY_UQEXPKEY);
			commLogger.setTransNo(transNo);
		}
		SysLoggermsPool.addCommLogger(commLogger);
		request.setAttribute(SysLoggerManagerInterceptor.REQUEST_KEY_LOGGER_ISDONE_FLAG, true);
	}
	
}
