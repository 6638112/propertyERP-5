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
package com.cnfantasia.server.ms.login.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.omsUser.service.IOmsUserService;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanySimple;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.session.SessionManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

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
	private IOmsUserService omsUserService;
	
	@Resource
	private IRevenueService revenueService;

	public void setOmsUserService(IOmsUserService omsUserService) {
		this.omsUserService = omsUserService;
	}

	protected AuthenticationManager authenticationManager;
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	/**
	 * 登录成功
	 * @param request
	 * @return
	 */
	@RequestMapping("/doLoginSuccess.html")
	public ModelAndView doLoginSuccess(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		OmsUser loginUser = UserContext.getCurrUser();
		request.getSession(true).setAttribute("currUser", loginUser);

		if (loginUser == null) {//还没有登录
			mav.setViewName("forward:/security/loginPage2.html");
			return mav;
		}else{
			//登陆成功回写最后时间-huangzj2015-05-26
			OmsUser omsUser = new OmsUser();
			omsUser.setId(loginUser.getId());
			omsUser.setLastLoginTime(CnfantasiaCommUtil.getCurrentTime());
			//omsUserService.updateOmsUser(omsUser);
		}

		List<OmsPermiFunction> permiFunctionList = omsUserService.select_OmsPermiFunction_byOmsUserId(loginUser.getId());
		
		// 开启银行托收的小区数量 
		Integer openBCGbCount = omsUserService.selectOpenBCGbCount(loginUser.getId());
		/**处理银行托收菜单*/
		if(openBCGbCount==0){
			/**银行托收菜单：银行托收、托收信息维护、托收数据处理*/
			String[] bcMenus = new String[]{"02bankCollection", "01bcBankInfo", "02bcBankData"};
			Iterator<OmsPermiFunction> iterator = permiFunctionList.iterator();
			while(iterator.hasNext()) {
				OmsPermiFunction permiFunction = iterator.next();
				for(String bcMenu:bcMenus){
					if(bcMenu.equals(permiFunction.getNumber())){
						iterator.remove();
						break;
					}
				}
			}
		}
		
		request.setAttribute("permiFunctionList", permiFunctionList);
		request.getSession(true).setAttribute("permiFunctionList", permiFunctionList);

		PropertyCompanySimple pc = omsUserService.getWelcomMsgInfo_byOmsuserId(loginUser.getId());
		if (pc != null) {
			UserContext.getUser().setCooperationType(pc.getCooperationType());
			request.setAttribute("welcomeMsg", pc.getPcName());
		}
		
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			RevenueRole role = null;
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(userRoleSet.contains(UserRole.PropertyCompany)) {
				UserRole userRole = UserRole.PropertyCompany;
				paramMap.put("userRole", RevenueDict.MiniRoleType.PropertyCompany);
				role = revenueService.createRevenueRoleExcep(UserContext.getOperIdBigIntegerMustExist(), userRole);
			} else if(userRoleSet.contains(UserRole.PCManagement)) {
				UserRole userRole = UserRole.PCManagement;
				paramMap.put("userRole", RevenueDict.MiniRoleType.PCManagement);
				role = revenueService.createRevenueRoleExcep(UserContext.getOperIdBigIntegerMustExist(), userRole);
				paramMap.put("roleId", role.getRoleId());
				
				boolean isPcManagementRevenue = revenueService.isPropertyManagerRevenuet(paramMap);
				if(isPcManagementRevenue == false) {
					request.setAttribute("isPcManagementRevenue", isPcManagementRevenue);
				}
			} else if(userRoleSet.contains(UserRole.PropertyAgent)) {
				UserRole userRole = UserRole.PropertyAgent;
				paramMap.put("userRole", RevenueDict.MiniRoleType.PropertyAgent);
				role = revenueService.createRevenueRoleExcep(UserContext.getOperIdBigIntegerMustExist(), userRole);
			}
			
			if(role != null) {
				paramMap.put("projectType", 4);
				paramMap.put("roleId", role.getRoleId());
				int ebuyRevenueCount = revenueService.selectRevenueSignalAmountCount(paramMap);
				request.setAttribute("ebuyRevenueCount", ebuyRevenueCount);
			}
		}
		

	//	mav.setViewName("/security/main");
		mav.setViewName("/security/mainPage");
		return mav;
	}
	
	/**
	 * 成功的中间区域的欢迎界面
	 * @author wenfq 2014-11-4
	 */
	@RequestMapping("/welcome.html")
	public ModelAndView welcome(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/security/welcome");
		return mav;
	}
	
	/**
	 * 登录失败
	 * @param request
	 * @return
	 */
	@RequestMapping("/doLoginFailed.html")
//	@ResponseBody
	public ModelAndView doLoginFailed(HttpServletRequest request) {
		HttpSession session = SessionManager.getSession();
		if(session!=null){
			Exception exp = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			logger.info(exp.getMessage(), exp);
			/*if (exp!=null&&!StringUtils.isEmpty(MessageSourceUtil.getMessage(exp.getMessage()))) {
				throw new BusinessRuntimeException(exp.getMessage());
			}else{
				throw new BusinessRuntimeException("security.doLoginFailed.info",exp);
			}*/
		}else{
			//throw new BusinessRuntimeException("security.doLoginFailed.info");
		}
		request.setAttribute("rtInfo", "passwordError");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/security/loginPage2.html");
		return mav;
	}
	
	
	@RequestMapping("/loginPage2.html")
	public ModelAndView loginPage2(HttpServletRequest request) {
//		throw new BusinessRuntimeException("security.loginPage.error");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/security/loginPage");
		return mav;
	}

	@RequestMapping("/passwordModify.html")
	public ModelAndView passwordModify(HttpServletRequest request) {
		request.setAttribute("omsUser", UserContext.getCurrUser());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/security/passwordModify");
		return mav;
	}

	@RequestMapping("/loginPage.html")
	public String loginPage(HttpServletRequest request) {
		return "redirect:/security/toIndex.html";
	}
	
	@RequestMapping("/toIndex.html")
	public ModelAndView toIndex(HttpServletRequest request) {
//		throw new BusinessRuntimeException("security.loginPage.error");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/security/index");
		return mav;
	}
	
	@RequestMapping("/accessNoPermission.html")
	public ModelAndView accessNoPermission(HttpServletRequest request) {
		return new ModelAndView("/pub/noPermission");
	}

	@RequestMapping("/accessDenied.html")
	//@ResponseBody
	public JsonResponse accessDenied(HttpServletRequest request) {
		throw new BusinessRuntimeException("security.accessDenied.error");
	}
	@RequestMapping("/accessExpired.html")
	//@ResponseBody
	public JsonResponse accessExpired(HttpServletRequest request) {
		throw new BusinessRuntimeException("security.accessExpired.error");
	}
//	@RequestMapping("/sessionTimeout.html")
//	public ModelAndView sessionTimeout(HttpServletRequest request) {
////		throw new TimeOutRuntimeException("security.sessionTimeout.error");
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/security/loginPage");
//		return mav;
//	}
	@RequestMapping("/sessionTimeout.html")
	public String sessionTimeout(HttpServletRequest request) {
		return "redirect:/security/toIndex.html";
	}
	@RequestMapping("/doLogoutSuccess.html")
//	@ResponseBody
	public String doLogout(HttpServletRequest request) {
	JsonResponse jsonResponse = new JsonResponse();
	//1.搜集参数
	HttpSession session = SessionManager.getSession();
	//2.交互
	if(session!=null){
		session.invalidate();
	}
	if(UserContext.getCurrUser()!=null){
		throw new BusinessRuntimeException("security.doLogout.getCurrUser.notnull");
	}
	//3.结果处理
//	return jsonResponse;
	return "redirect:/security/toIndex.html";
	}
}
