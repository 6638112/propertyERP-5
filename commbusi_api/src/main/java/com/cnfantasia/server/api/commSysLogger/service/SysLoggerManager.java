/**   
 * Filename:    SysLoggerManager.java   
 * @version:    1.0  
 * Create at:   2014年8月5日 上午3:12:51   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月5日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commSysLogger.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.FutureTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysLogger.callable.SysLoggerSynch2DatabaseRunnable;
import com.cnfantasia.server.api.commSysLogger.constant.CommSysLoggerDict;
import com.cnfantasia.server.api.commSysLogger.utils.SysLoggerPool;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.exception.ExceptionConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.logger.SysLoggerProcessInterceptor;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerManager;
import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerService;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.sysUrl.ISysUrlManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MybatisSpecialCharFilterUtil;
import com.cnfantasia.server.common.exception.IBaseException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;
import com.cnfantasia.server.domainbase.commLoggerControl.entity.CommLoggerControl;
import com.cnfantasia.server.domainbase.commSysUrl.entity.CommSysUrl;

/**
 * Filename: SysLoggerManager.java
 * 
 * @version: 1.0.0 Create at: 2014年8月5日 上午3:12:51 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月5日 shiyl 1.0 1.0 Version
 */
public class SysLoggerManager implements ISysLoggerManager<CommLogger> {
	private Log logger = LogFactory.getLog(getClass());

	private static final int DEFAULT_CATCHE_SIZE = 100;
	/** 缓存的日志列表 */
	private List<CommLogger> logCatcheList;

	/** 日志控制缓存的信息 */
	private Map<BigInteger, CommLoggerControl> loggerControlMap;

	private ISysUrlManager<CommSysUrl> sysUrlManager;

	public void setSysUrlManager(ISysUrlManager<CommSysUrl> sysUrlManager) {
		this.sysUrlManager = sysUrlManager;
	}

	private ISysLoggerService<CommLogger,CommLoggerControl> sysLoggerService;

	public void setSysLoggerService(ISysLoggerService<CommLogger,CommLoggerControl> sysLoggerService) {
		this.sysLoggerService = sysLoggerService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	/**
	 * 缓存的最大记录数
	 */
	private int catcheSize;

	public SysLoggerManager() {
		this.catcheSize = DEFAULT_CATCHE_SIZE;
	}

	public SysLoggerManager(int catcheSize) {
		this.catcheSize = catcheSize;
	}

	public void init() {
		logCatcheList = new Vector<CommLogger>(this.catcheSize);
		initLogCtrlMap();
	}

	private void initLogCtrlMap() {
		loggerControlMap = new HashMap<BigInteger, CommLoggerControl>();
		List<CommLoggerControl> logCtrlList = sysLoggerService.getLoggerCtrlList();
		for (CommLoggerControl tmpCtrl : logCtrlList) {
			loggerControlMap.put(tmpCtrl.gettCommSysUrlFId(), tmpCtrl);
		}
	}

	@Override
	public synchronized void insertLog2Catche(CommLogger commLogger) {
		logCatcheList.add(commLogger);
		if (logCatcheList.size() >= catcheSize) {
			synch2Database();
		}
	}

	@Override
	public void synch2DatabaseCurrThread() {
		List<CommLogger> cloneLogList = new ArrayList<CommLogger>(logCatcheList);// 使用复制的对象
		logCatcheList.removeAll(cloneLogList);// 要考虑到在多线程执行过程中logCatcheList的数据会增加
		sysLoggerService.synch2Database(cloneLogList);
	}

	@Override
	public synchronized void synch2Database() {
		List<CommLogger> cloneLogList = new ArrayList<CommLogger>(logCatcheList);// 使用复制的对象
		logCatcheList.removeAll(cloneLogList);// 要考虑到在多线程执行过程中logCatcheList的数据会增加
		FutureTask<List<CommLogger>> task = new FutureTask<List<CommLogger>>(new SysLoggerSynch2DatabaseRunnable(
				sysLoggerService, cloneLogList));
		new Thread(task).start();
		// TODO 后面不要加代码，否则会因为上面的线程执行导致同步锁不能释放，进而影响其他例如 insertLog2Catche 执行的等待。
		// try {
		// List<CommLogger> resultList = task.get();
		// logCatcheList.addAll(resultList);
		// } catch (Exception e) {
		// logger.error("SysLoggerManager synch2Database FutureTask .get() cause exception,curr logCatcheList size is "+logCatcheList.size()+",curr cloneLogList size is "+cloneLogList.size()+"",
		// e);;
		// }
	}

	// /**
	// *
	// * @param args
	// */
	// public static void main(String[] args) {
	// List<CommLogger> logCatcheList = new
	// Vector<CommLogger>();//内部list使用的是引用，remove和removeAll是遍历使用equals方法作移除
	// {
	// CommLogger commLogger = new CommLogger(new BigInteger("1"), new
	// BigInteger("40011"), null, null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null);
	// logCatcheList.add(commLogger);
	// }
	// {
	// CommLogger commLogger = new CommLogger(new BigInteger("2"), new
	// BigInteger("40011"), "aaa", null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null);
	// logCatcheList.add(commLogger);
	// }
	// {
	// CommLogger commLogger = new CommLogger(new BigInteger("3"), new
	// BigInteger("40011"), "ccc", null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null);
	// logCatcheList.add(commLogger);
	// }
	// List<CommLogger> cloneLogList = new
	// Vector<CommLogger>(logCatcheList);//使用复制的对象
	// System.out.println("logCatcheList="+JSON.toJSONString(logCatcheList));
	// System.out.println("cloneLogList="+JSON.toJSONString(cloneLogList));
	// System.out.println("logCatcheList size="+logCatcheList.size()+",cloneLogList size="+cloneLogList.size());
	// {
	// CommLogger commLogger = new CommLogger(new BigInteger("4"), new
	// BigInteger("40011"), "ccc", null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null);
	// logCatcheList.add(commLogger);
	// }
	// cloneLogList.get(0).setId(new BigInteger("889"));
	// {
	// CommLogger commLogger = new CommLogger(new BigInteger("99"), new
	// BigInteger("40011"), "ccc", null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null, null, null, null, null,
	// null, null, null, null, null, null, null, null);
	// logCatcheList.remove(commLogger);
	// }
	// // logCatcheList.removeAll(cloneLogList);
	// // cloneLogList.clear();
	// System.out.println("logCatcheList="+JSON.toJSONString(logCatcheList));
	// System.out.println("cloneLogList="+JSON.toJSONString(cloneLogList));
	// System.out.println("logCatcheList size="+logCatcheList.size()+",cloneLogList size="+cloneLogList.size());
	// }

	@Override
	public synchronized void freshInitData() {
		synch2DatabaseCurrThread();// 将缓存的日志存入数据库
		initLogCtrlMap();// 重新加载日志控制信息
	}

	@Override
	public boolean checkIfIgnoreParams(BigInteger urlId) {
		CommLoggerControl commLoggerControl = loggerControlMap.get(urlId);
		if (commLoggerControl != null) {
			Integer ignoreParams = commLoggerControl.getIgnoreParams();
			if (ignoreParams != null && CommSysLoggerDict.CommLoggerControl_ignoreParams.YES.compareTo(ignoreParams) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkIfIgnoreResponseData(BigInteger urlId) {
		CommLoggerControl commLoggerControl = loggerControlMap.get(urlId);
		if (commLoggerControl != null) {
			Integer ignoreResponse = commLoggerControl.getIgnoreResponse();
			if (ignoreResponse != null && CommSysLoggerDict.CommLoggerControl_ignoreResponse.YES.compareTo(ignoreResponse) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkIfNeedRecord(BigInteger urlId) {
		CommLoggerControl commLoggerControl = loggerControlMap.get(urlId);
		if (commLoggerControl != null && commLoggerControl.getNeedRecord() != null
				&& CommSysLoggerDict.CommLogger_needRecord.NO.compareTo(commLoggerControl.getNeedRecord()) == 0) {
			return false;
		}
		return true;// 默认需要记录日志
	}

	@Override
	public void doLoggerRecord(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex01) {
		logger.debug(request.getSession().getId() + ":-3-" + System.currentTimeMillis());
		Boolean isDoneLoggerFlag = (Boolean) request.getAttribute(SysLoggerProcessInterceptor.REQUEST_KEY_LOGGER_ISDONE_FLAG);
		if (isDoneLoggerFlag != null && isDoneLoggerFlag == true && ex01 == null) {
			return;// 已经记录了日志，且未发现异常
		}
		CommLogger commLogger = new CommLogger();
		String reqUrl = sysUrlManager.parseUrlFromRequest(request);
		logger.debug(request.getSession().getId() + ":-4-" + System.currentTimeMillis());
		CommSysUrl currCommSysUrl = sysUrlManager.getUrlDetail(reqUrl);
		if (currCommSysUrl == null) {
			currCommSysUrl = sysUrlManager.addUrl(null, reqUrl, "autoAdd");
		}
		BigInteger urlId = currCommSysUrl.getId();
		logger.debug(request.getSession().getId() + ":-5-" + System.currentTimeMillis());
		boolean needRecord = this.checkIfNeedRecord(urlId);
		logger.debug(request.getSession().getId() + ":-6-" + System.currentTimeMillis());
		if (!needRecord) {
			return;
		}// 为空，表示不记录日志

		// commLogger.setId(id);
		{// 请求地址信息
			String actionDesc = currCommSysUrl.getName();
			BigInteger tCommSysUrlFId = currCommSysUrl.getId();
			String actionUrlTail = reqUrl;
			String actionUrlAll = request.getRequestURL().toString();
			commLogger.settCommSysUrlFId(tCommSysUrlFId);
			commLogger.setActionDesc(actionDesc);
			commLogger.setActionUrlTail(actionUrlTail);
			commLogger.setActionUrlAll(actionUrlAll);
			if (!this.checkIfIgnoreParams(urlId)) {// 保存请求的参数信息
				String requestParams = JSON.toJSONString(request.getParameterMap());
				commLogger.setRequestParams(requestParams);
			}
			logger.debug(request.getSession().getId() + ":-7-" + System.currentTimeMillis());
			if (!this.checkIfIgnoreResponseData(urlId)) {// 保存返回结果信息
				String responseData = (String) request.getAttribute(BaseController.REQUEST_KEY_RESPONSE_DATA);
				commLogger.setResponseData(responseData);
			}
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
			String ip = getAddr(request);
			if(request.getHeader("ipTest") != null) {
				ip = request.getHeader("ipTest");
			}
			String os = request.getHeader("User-Agent");
			if(request.getHeader("osTest") != null) {
				os = request.getHeader("osTest");
			}
			String osVer = null;
//			osVer = "sessionId:" + request.getSession().getId();

			commLogger.setChannel(channel);
			commLogger.setDeviceId(deviceId);
			commLogger.setVersion(version);
			commLogger.setIp(ip);
			commLogger.setOs(os);
			commLogger.setOsVer(osVer);
		}
		logger.debug(request.getSession().getId() + ":-8-" + System.currentTimeMillis());
		{// 用户信息
			String userAccNo = null;
			Long userAccType = null;
			BigInteger userId = null;
			BigInteger defaultRoomId = null;
			LoginNoEntity loginNoEntity = UserContext.getCurrLoginNo();
			if (loginNoEntity != null) {
				userId = loginNoEntity.gettUserFId();
				userAccNo = loginNoEntity.getNo();
				userAccType = loginNoEntity.getType();
				defaultRoomId= loginNoEntity.getUserEntity()==null?null:(loginNoEntity.getUserEntity().getDefaultRoomId());//采用缓存的方式
//				if(!StringUtils.isEmpty(userId)){
//					defaultRoomId= commonRoomService.getDefaultRoomIdByUserId(userId);
//				}
			}
			commLogger.setUserAccNo(userAccNo);
			commLogger.setUserAccType(userAccType);
			commLogger.setUserId(userId);
			commLogger.setDefaultRoomId(defaultRoomId);
		}
		{// 执行时间
			logger.debug(request.getSession().getId() + ":-9-" + System.currentTimeMillis());
			Date reqStartDate = (Date) request.getAttribute(SysLoggerProcessInterceptor.REQUEST_KEY_STARTTIME);
			Date reqEndDate = new Date();
			Long reqDealTime = reqEndDate.getTime() - reqStartDate.getTime();

			String reqStartTime = DateUtil.formatSecond.get().format(reqStartDate);
			String reqEndTime = DateUtil.formatSecond.get().format(reqEndDate);

			commLogger.setReqStartTime(reqStartTime);
			commLogger.setReqDealTime(reqDealTime);
			commLogger.setReqEndTime(reqEndTime);
		}
		{// 日志信息唯一标识
			String transNo = (String) request.getAttribute(SysLoggerProcessInterceptor.REQUEST_KEY_UQEXPKEY);
			commLogger.setTransNo(transNo);
		}
		SysLoggerPool.addCommLogger(commLogger);
		request.setAttribute(SysLoggerProcessInterceptor.REQUEST_KEY_LOGGER_ISDONE_FLAG, true);
//		try {
//			this.insertLog2Catche(commLogger);
//			if (ex != null && ex.getCause() != null && !(ex.getCause() instanceof IBaseException)) {// 有未知异常堆栈信息
//				this.synch2Database();// 立即刷新 存入数据库
//			}
//			request.setAttribute(SysLoggerProcessInterceptor.REQUEST_KEY_LOGGER_ISDONE_FLAG, true);
//		} catch (Exception e) {
//			logger.error(
//					"SysLoggerManager insertLog2Catche cause exception,the commLogger is " + JSON.toJSONString(commLogger), e);
//		}
	}

	@Override
	public int getCatcheLogSize() {
		return logCatcheList == null ? -1 : logCatcheList.size();
	}
	
	/** 
	* 获取ip地址,防止集群、代理
	* @param request 
	* @return ip
	*/ 

	public static String getAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");      
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  

	    return ip;  
	}


}
