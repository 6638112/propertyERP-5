/**   
 * Filename:    SecurityController.java   
 * @version:    1.0  
 * Create at:   2014年5月20日 上午5:16:23   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月20日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.login.callable.IAddOtherInfoAfterRegister;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.login.queue.LoginLogPool;
import com.cnfantasia.server.api.login.service.AddMasterInfoAfterRegister;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.loginLog.entity.LoginLog;
import com.cnfantasia.server.domainbase.loginLog.service.ILoginLogBaseService;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;

/**
 * Filename: SecurityController.java
 * 
 * @version: 1.0.0 Create at: 2014年5月20日 上午5:16:23 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月20日 shiyl 1.0 1.0 Version
 */
@Controller
@RequestMapping("/security")
public class SecurityController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
//	private Log logger = LogFactory.getLog(getClass());
	private IUserBaseService userBaseService;
	public void setUserBaseService(IUserBaseService userBaseService) {
		this.userBaseService = userBaseService;
	}
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}
	private ILoginLogBaseService loginLogBaseService;
	public void setLoginLogBaseService(ILoginLogBaseService loginLogBaseService) {
		this.loginLogBaseService = loginLogBaseService;
	}
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	/**
	 * 登录成功
	 * @param request
	 * @return
	 */
	@RequestMapping("/doLoginSuccess.json")
	@ResponseBody
	public JsonResponse doLoginSuccess(HttpServletRequest request) {
		logger.info("doLoginSuccess.json------start");
		insertLoginLog(request);
		setIsFirstLoginStatus();//更改首次登录标识
		LoginNoEntity loginRes = UserContext.getCurrLoginNo();
		logger.info("doLoginSuccess.json------before addMasterInfo "  + MapConverter.toMap(loginRes));
		addMasterInfo(loginRes);
		return commonLoginService.afterLoginCheck(request,loginRes);
//		return LoginController.afterLoginCheck(loginRes);
	}

	/**
	 * 添加师傅相关额外信息
	 * @param loginRes
	 */
	private void addMasterInfo(LoginNoEntity loginRes) {
		Long subChannelId = 0L;
		try {
			subChannelId = Long.parseLong(HeaderManager.getSubChannelId());//注册媒介
		} catch(Exception e) {
		}
		
		logger.info("addMasterInfo--------start----subChannal is: " + subChannelId);
		if (HeaderConstant.SubChannelId.Jfq_Master_App_Android.compareTo(subChannelId)==0
				|| HeaderConstant.SubChannelId.Jfq_Master_App_ISO.compareTo(subChannelId)==0) {
			IAddOtherInfoAfterRegister addOtherInfoAfterRegister = new AddMasterInfoAfterRegister();
			addOtherInfoAfterRegister.addOtherInfo(loginRes.gettUserFId());
		}
	}

	@RequestMapping("/doLoginSuccessCas.json")
	public String doLoginSuccessCas(HttpServletRequest request) {
		logger.debug("forward to /security/doLoginSuccess.json,from /doLoginSuccessCas.json");
		return "forward:/security/doLoginSuccess.json";
	}
	//记录登录日志
	private void insertLoginLog(HttpServletRequest request){
		try {
			LoginNoEntity loginRes = UserContext.getCurrLoginNo();
			//用户，设备，ip，操作系统
			LoginLog loginLog = new LoginLog();
			try {
				loginLog.setChannel(HeaderManager.getSubChannelId());
			} catch (Exception e) {}
			try {
				loginLog.setDeviceId(HeaderManager.getDeviceId());
			} catch (Exception e) {}
			try {
				loginLog.setVersion(HeaderManager.getVersion());
			} catch (Exception e) {}
			
			loginLog.setIp(request.getRemoteAddr());
			{
//				Enumeration<String> enumD = request.getHeaderNames();
//				while(enumD.hasMoreElements()){
//					String name = enumD.nextElement();
//					System.out.println(name+" ="+request.getHeader(name));
//				}
				String agent = request.getHeader("User-Agent");
				loginLog.setOs(agent);
				loginLog.setOsVer(null);
			}
			if(loginRes!=null){
				loginLog.settUserFId(loginRes.gettUserFId());
				loginLog.setUserName(loginRes.getNo());
				loginLog.setAccType(loginRes.getType());
			}else{
				loginLog.settUserFId(UserContext.getOperIdBigInteger());
			}
			loginLog.setLoginDay(dualDao.getNowDay());
			loginLog.setLoginTime(dualDao.getNowTime());
			LoginLogPool.addActivity(loginLog);
//			loginLogBaseService.insertLoginLog(loginLog);
		} catch (Exception e) {
			logger.debug("insert login log error",e);
		}
			
	}
	
	/**
	 * 登录失败
	 * @param request
	 * @return
	 */
	@RequestMapping("/doLoginFailed.json")
	@ResponseBody
	public JsonResponse doLoginFailed(HttpServletRequest request) {
		insertLoginLog(request);
		HttpSession session = SessionManager.getSession();
		if(session!=null){
			Exception exp = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (exp!=null&&!StringUtils.isEmpty(MessageSourceUtil.getMessage(exp.getMessage()))) {
				throw new BusinessRuntimeException(exp.getMessage());
			}else{
				throw new BusinessRuntimeException("login.doLoginFailed.info",exp);
			}
		}else{
			throw new BusinessRuntimeException("login.doLoginFailed.info");
		}
	}
	@RequestMapping("/doLoginFailedCas.json")
	public String doLoginFailedCas(HttpServletRequest request) {
		logger.debug("forward to /security/doLoginFailed.json,from /doLoginFailedCas.json");
		return "forward:/security/doLoginFailed.json";
	}
	/**
	 * 注册后自动登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/autoLoginAfterRegist.json")
	@ResponseBody
	public JsonResponse autoLoginAfterRegist(HttpServletRequest request) {
		String account = (String)request.getAttribute(LoginConstant.ATTRIBUTE_NAME_ACCOUNT);
		String password = (String)request.getAttribute(LoginConstant.ATTRIBUTE_NAME_PASSWD);
		Long accountType = (Long)request.getAttribute(LoginConstant.ATTRIBUTE_ACCOUNT_TYPE);
		logger.info("account :" + account); 
		logger.info("password :" + password); 
		logger.info("accountType :" + password); 
		commonLoginService.autoLogin(request, account, password,accountType);
		insertLoginLog(request);//syl-add-2015-5-11 18:00:243 注册直接登录也记录日志
		setIsFirstLoginStatus();//更改首次登录标识
		return commonLoginService.afterLoginCheck(request,UserContext.getCurrLoginNo());
	}
//	/**
//	 * 注册后自动登录
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/autoLoginAfterRegist.json")
//	public String autoLoginAfterRegist(HttpServletRequest request) {
//		setIsFirstLoginStatus();//更改首次登录标识
//		String account = (String)request.getAttribute(LoginController.ATTRIBUTE_NAME_ACCOUNT);
//		String password = (String)request.getAttribute(LoginController.ATTRIBUTE_NAME_PASSWD);
//		Long accountType = (Long)request.getAttribute(LoginController.ATTRIBUTE_ACCOUNT_TYPE);
//		return "redirect:https://shiyl:8443/cas-server-webapp-3.5.2.1/registerLogin?username="+account+"&password="+password+"&type="+accountType;
//	}
	
	//更新用户账号的登录状态为已登录
	private void setIsFirstLoginStatus(){
		//syl-del-2015-7-22 18:35:07 改为使用门牌查询的方式
//		User user = new User();
//		user.setId(UserContext.getOperIdBigInteger());
//		user.setIsFirstLoginStatus(UserDict.User_IsFirst_LoginStatus.HAVE_LOGIN);
//		int res = userBaseService.updateUser(user);
//		if(res<=0){
//			throw new BusinessRuntimeException("security.doLoginSuccess.setIsFirstLoginStatus.error");
//		}
	}
	
	@RequestMapping("/loginPage.json")
	@ResponseBody
	public JsonResponse loginPage(HttpServletRequest request) {
		throw new BusinessRuntimeException("security.loginPage.error");
	}
	@RequestMapping("/accessDenied.json")
	@ResponseBody
	public JsonResponse accessDenied(HttpServletRequest request) {
		throw new BusinessRuntimeException("security.accessDenied.error");
	}
	@RequestMapping("/accessExpired.json")
	@ResponseBody
	public JsonResponse accessExpired(HttpServletRequest request) {
		throw new BusinessRuntimeException("security.accessExpired.error");
	}
	/**
	 * 系统超时
	 * @param request
	 * @return
	 */
	@RequestMapping("/sessionTimeout.json")
	public void sessionTimeout(HttpServletRequest request) {//syl-upd-2014-6-11 18:00:27 return void
//		commonLoginService.checkSessionKeyAndAutoLogin(request);//检查SessionKey，并判断根据情况自动登录 syl-del-2014-11-1 19:50:07
		
//		request.getSession(true);

		
//		throw new TimeOutRuntimeException();
		
		
//		String realUrl = RequestParseUtil.getRealUrl(request);
//		if("/security/sessionTimeout.json".equals(realUrl)){
//			throw new TimeOutRuntimeException("security.sessionTimeout.error");
//		}
//		return "forward:"+realUrl;
	}
	@RequestMapping("/doLogoutSuccess.json")
	@ResponseBody
	public JsonResponse doLogout(HttpServletRequest request) {
	JsonResponse jsonResponse = new JsonResponse();
	//1.搜集参数
	HttpSession session = SessionManager.getSession();
	//2.交互
	if(session!=null){
		session.invalidate();
	}
	{//删除SessionUser表信息
		String sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		if(!StringUtils.isEmpty(sessionKey)){
			commonLoginService.deleteSessionUserFromDB(sessionKey);
		}
	}
	{//删除用户 绑定的推送信息
		
	}
	if(UserContext.getCurrLoginNo()!=null){
		logger.info("security.doLogout.getCurrUser.notnull,user id is："+UserContext.getOperId());
		SecurityContextHolder.clearContext();
		logger.info("security.doLogout.getCurrUser.operRes,user id is："+UserContext.getOperId());
//		jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
//		jsonResponse.setMessageKey(("security.doLogout.getCurrUser.notnull"));
//		return jsonResponse;
	}
	//3.结果处理
	return jsonResponse;
	}
}
