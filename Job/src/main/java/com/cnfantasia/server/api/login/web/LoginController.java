package com.cnfantasia.server.api.login.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.commonBusiness.util.CommonUserImageProfileUtil;
import com.cnfantasia.server.api.login.callable.IAddOtherInfoAfterRegister;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.login.constant.LoginDict.DoForgetPwd_Type;
import com.cnfantasia.server.api.login.service.AddMasterInfoAfterRegister;
import com.cnfantasia.server.api.login.service.ILoginService;
import com.cnfantasia.server.api.login.service.IValicodeManager;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.user.entity.UserImageParamEntity;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private ILoginService loginService;
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
//	private ILoginNoBaseService loginNoBaseService;
//	public void setLoginNoBaseService(ILoginNoBaseService loginNoBaseService) {
//		this.loginNoBaseService = loginNoBaseService;
//	}
	
	private ISysParamParser userImageParamParser;
	public void setUserImageParamParser(ISysParamParser userImageParamParser) {
		this.userImageParamParser = userImageParamParser;
	}
	
	private IValicodeManager valicodeManager;
	public void setValicodeManager(IValicodeManager valicodeManager) {
		this.valicodeManager = valicodeManager;
	}
	
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @return
	 */
	@RequestMapping("/getValidateCode.json")
	@ResponseBody
	public JsonResponse getValidateCode(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String mobile = request.getParameter("mobile");
		Integer type = Integer.parseInt(request.getParameter("type"));
		//2.交互
		
//		if(LoginDict.ValiCodeGetType.UNBIND_PHONE.compareTo(type)==0){
//			BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//			LoginNo loginNo = commonLoginService.getUserPhoneAccInfo(userId);
//			if(loginNo==null){
//				throw new ValidateRuntimeException("login.getValidateCode.notBind.error");
//			}else{
		//				mobile = loginNo.getNo();//设定手机号
//			}
//		}
		
		//校验手机号是否已经注册
		boolean isRegist = loginService.checkIsRegist(mobile, LoginDict.AccountType.MOBILE);
		if(LoginDict.ValiCodeGetType.REGIST.compareTo(type)==0){
//			if(isRegist){
//				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
//				jsonResponse.setMessageKey("login.getValidateCode.REGIST.isRegist.error");
//				return jsonResponse;
//			}
		}else if(LoginDict.ValiCodeGetType.LOGIN.compareTo(type)==0){
			
		} else if (LoginDict.ValiCodeGetType.UPDATE_PASSWORD.compareTo(type) == 0) {
			/** 修改密码、绑定新手机 */
//			if(!isRegist){
//				throw new ValidateRuntimeException("login.getValidateCode.MOBILE_VALICODE.notRegist.error");
//			}
			UserContext.getOperIdMustExistBigInteger();//需要登录
		}else if(LoginDict.ValiCodeGetType.FORGET_PASSWORD.compareTo(type)==0){
			if(!isRegist){
				throw new ValidateRuntimeException("login.getValidateCode.MOBILE_VALICODE.notRegist.error");
			}
		} else if (LoginDict.ValiCodeGetType.UNBIND_PHONE.compareTo(type) == 0) {//如果时解除绑定，则输入的手机号应该为当前用户绑定的
			//			UserContext.getOperIdMustExistBigInteger();//需要登录
			BigInteger userId = UserContext.getOperIdMustExistBigInteger();
			if(!isRegist){
				throw new ValidateRuntimeException("login.getValidateCode.MOBILE_VALICODE.notRegist.error");
			}
			LoginNo loginNo = commonLoginService.getUserPhoneAccInfo(userId);
			if(loginNo==null){
				throw new ValidateRuntimeException("login.getValidateCode.notBind.error");
			}else{
				mobile = loginNo.getNo();//设定手机号
			}
		}else if(LoginDict.ValiCodeGetType.BIND_CAR.compareTo(type)==0){
			
		}else{
			throw new ValidateRuntimeException("login.getValidateCode.unkonwnType.error");
		}
		//生产6位数字的随机短信验证码
		String validateCode=valicodeManager.generateValicode(type,mobile);
//				RandomUtils.createRandom(true, FocConstants.DEFAULT_PHONE_VALICODE_LENGTH);
		String msg = "";
		if(LoginDict.ValiCodeGetType.REGIST.compareTo(type)==0){
			msg = MessageSourceUtil.getMessage("msg.REGIST", new Object[]{validateCode});
			//			msg=String.format("注册验证码（%s），欢迎您使用解放区！", validateCode);
		}else if(LoginDict.ValiCodeGetType.FORGET_PASSWORD.compareTo(type)==0){
			msg = MessageSourceUtil.getMessage("msg.FORGET_PASSWORD", new Object[]{validateCode});
			//			msg=String.format("验证码（%s）--忘记密码使用,欢迎您使用解放区！", validateCode);
		}else if(LoginDict.ValiCodeGetType.LOGIN.compareTo(type)==0){
			msg = MessageSourceUtil.getMessage("msg.LOGIN", new Object[]{validateCode});
			//			msg=String.format("登录验证码（%s），欢迎您使用解放区！", validateCode);
		}else if(LoginDict.ValiCodeGetType.UPDATE_PASSWORD.compareTo(type)==0){
			msg = MessageSourceUtil.getMessage("msg.UPDATE_PASSWORD", new Object[]{validateCode});
			//			msg=String.format("验证码（%s）--修改（/设置）密码使用,欢迎您使用解放区！", validateCode);
		}else if(LoginDict.ValiCodeGetType.UNBIND_PHONE.compareTo(type)==0){
			msg = MessageSourceUtil.getMessage("msg.UNBIND_PHONE", new Object[]{validateCode});
			//			msg=String.format("验证码（%s）--解绑手机号使用，欢迎您使用解放区！", validateCode);
		}else if(LoginDict.ValiCodeGetType.BIND_CAR.compareTo(type)==0){
            msg = MessageSourceUtil.getMessage("msg.BIND_CAR", new Object[]{validateCode});
            //          msg=String.format("验证码（%s）--绑定车使用，欢迎您使用解放区！", validateCode);
        }
		//3.结果处理
		boolean msgSendRes = loginService.sendMsg(mobile,msg);
		if(!msgSendRes){
			throw new ValidateRuntimeException("login.getValidateCode.get.error");
		}else{
			logger.debug("valicode for " + mobile + " is " + validateCode + "。");
			valicodeManager.putValicode2Session(type,mobile, validateCode);
//			if(logger.isDebugEnabled()){
//				jsonResponse.putData("code", validateCode);
//			}
		}
		return jsonResponse;
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/regist.json")
	public String regist(HttpServletRequest request){
//		Enumeration params = request.getParameterNames();
//		while(params.hasMoreElements()){
//			String name = (String)params.nextElement();
//			System.out.println(name+"="+request.getParameter(name));
//		}
//		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer regType = Integer.parseInt(request.getParameter("regType"));//注册方式
		String account = request.getParameter("mobile");//接收短信验证码的手机号
		String verifyCode = request.getParameter("verifyCode");//验证码
		String utmCampaign = request.getParameter("utmCampaign");//用户注册参加的活动
		String utmContent = request.getParameter("utmContent");//推广内容
		String inviteNo = request.getParameter("inviteNo");//邀请码
		
		String password = request.getParameter("password");
		
		Long subChannelId = Long.parseLong(HeaderManager.getSubChannelId());//注册媒介
		String deviceId = HeaderManager.getDeviceId();//设备Id
		String version = HeaderManager.getVersion();//版本号
		//2.交互
		//校验验证码是否正确
		valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.REGIST,account,verifyCode);
		valicodeManager.clearValicode(LoginDict.ValiCodeGetType.REGIST);//清除验证码
		
		//注册
		LoginNo registRes = loginService.regist(account, regType, subChannelId,version, utmCampaign, utmContent, inviteNo,deviceId,password);

		addMasterInfo(registRes.gettUserFId());

		//设置账号信息到attribute
		setLogNoToAttr(request, registRes);
		//3.结果处理
		//跳转到自动登录处理
		return "forward:/security/autoLoginAfterRegist.json";
//		return afterLoginCheck(loginRes);
	}
	
	/**
	 * 添加师傅相关额外信息
	 * 
	 * @param userId
	 */
	private void addMasterInfo(BigInteger userId) {
		Long subChannelId = Long.parseLong(HeaderManager.getSubChannelId());//注册媒介
		if (HeaderConstant.SubChannelId.Jfq_Master_App_Android.compareTo(subChannelId)==0
				|| HeaderConstant.SubChannelId.Jfq_Master_App_ISO.compareTo(subChannelId)==0) {
			IAddOtherInfoAfterRegister addOtherInfoAfterRegister = new AddMasterInfoAfterRegister();
			addOtherInfoAfterRegister.addOtherInfo(userId);
		}
	}

	/**
	 * 检查验证码是否正确，如果不type进来，默认验证的是注册时的验证码
	 * 
	 * @param request
	 * @return 
	 */
	@RequestMapping("/isVarifyCodeCorrect.json")
	@ResponseBody
	public JsonResponse isVarifyCodeCorrect(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String verifyCode = ParamUtils.getString(request, "verifyCode", null);
		String mobile = ParamUtils.getString(request, "mobile", null);
		Integer type = ParamUtils.getInteger(request, "type", LoginDict.ValiCodeGetType.REGIST);

		valicodeManager.checkWithSession(type, mobile, verifyCode);
		return jsonResponse;
	}

	/**
	 * 社区用户注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/registForClub.json")
	public String registForClub(HttpServletRequest request){
//		Enumeration params = request.getParameterNames();
//		while(params.hasMoreElements()){
//			String name = (String)params.nextElement();
//			System.out.println(name+"="+request.getParameter(name));
//		}
//		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer regType = Integer.parseInt(request.getParameter("regType"));//注册方式
		String account = request.getParameter("mobile");//接收短信验证码的手机号
		//		String verifyCode = request.getParameter("verifyCode");//验证码//syl-2014-11-29 13:23:24 del
		//		String utmCampaign = request.getParameter("utmCampaign");//用户注册参加的活动
		//		String utmContent = request.getParameter("utmContent");//推广内容
		//		String inviteNo = request.getParameter("inviteNo");//邀请码
		
		String password = null;
		{//密码
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			if(!StringUtils.isEmpty(password1)&&!StringUtils.isEmpty(password2)&&password1.equals(password2)){
				password = password1;
			}else{
				throw new ValidateRuntimeException("login.registForClub.password.error");
			}
		}
		
		String nickName = request.getParameter("nickName");//昵称
		
		Long subChannelId = Long.parseLong(HeaderManager.getSubChannelId());//注册媒介
		String deviceId = HeaderManager.getDeviceId();//设备Id
		String version = HeaderManager.getVersion();//版本号
		//2.交互
		//校验验证码是否正确 TODO 
//		valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.REGIST,account,verifyCode);
		//		valicodeManager.clearValicode(LoginDict.ValiCodeGetType.REGIST);//清除验证码
		
		//注册
		LoginNo registRes = loginService.registClub(account, regType, subChannelId,version, deviceId, password, nickName);
		//设置账号信息到attribute
		setLogNoToAttr(request, registRes);
		//3.结果处理
		//跳转到自动登录处理
		return "forward:/security/autoLoginAfterRegist.json";
//		return afterLoginCheck(loginRes);
	}
	
	/**
	 * 第三方注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/regist3rd.json")
	public String regist3rd(HttpServletRequest request){
		//1.搜集参数
		Integer regType = Integer.parseInt(request.getParameter("regType"));//注册方式
		String openId = request.getParameter("openId");//当前注册方式下的用户唯一标识
		String accessToken = request.getParameter("accessToken");//合法性校验token
		String unionId = request.getParameter("unionId");//微信联合Id unionId
		
		String mobile = request.getParameter("mobile");
		String realName = request.getParameter("realName");
		String nickName = request.getParameter("nickName");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		
		//解析用户图像信息
		UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
		RequestFileEntity userImageEntity = CommonUserImageProfileUtil.parseRequsetImageInfo(userImageParamEntity,request);
		String userImgprofileName = userImageEntity.getFileName();
		byte[] userImage = userImageEntity.getFileContent();
		
		Long subChannelId = Long.parseLong(HeaderManager.getSubChannelId());//注册媒介
		String deviceId = HeaderManager.getDeviceId();//设备Id
		String version = HeaderManager.getVersion();//版本号
		//2.交互
		LoginNo registRes = loginService.regist3rd(regType, openId, accessToken, mobile, realName,nickName,sex, birthday,userImage,userImgprofileName, subChannelId,version, deviceId,unionId);
		//设置账号信息到attribute
		setLogNoToAttr(request, registRes);
		//3.结果处理
		return "forward:/security/autoLoginAfterRegist.json";
	}
	
//	@RequestMapping("/doLogin.json")
//	@ResponseBody
//	public JsonResponse doLogin(HttpServletRequest request){
////		JsonResponse jsonResponse = new JsonResponse();
	//		//1.搜集参数
//		SimpleLoginInfo simpleLoginInfo = SimpleLoginInfo.parseLoginInfo(request);
//		validateLoginType(simpleLoginInfo);
	//		//2.交互
//		LoginNoEntity loginRes = loginService.login(simpleLoginInfo.getNumber(), simpleLoginInfo.getPassword(), simpleLoginInfo.getLoginType());
	//		//3.结果处理
//		return afterLoginCheck(loginRes);
//	}
	
//	/**
	//	 * 退出
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/doLogout.json")
//	@ResponseBody
//	public JsonResponse doLogout(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
	//		//1.搜集参数
//		HttpSession session = SessionManager.getSession();
	//		//2.交互
//		if(session!=null){
//			session.invalidate();
//		}
//		if(UserContext.getCurrUser()!=null){
//			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
//			jsonResponse.setMessageKey(("login.doLogout.getCurrUser.notnull"));
//			return jsonResponse;
//		}
	//		//3.结果处理
//		return jsonResponse;
//	}
	/**
	 * 找回方式，校验验证码是否正确
	 * @param request
	 * @return
	 */
	@RequestMapping("/doForgetPwd.json")
	@ResponseBody
	public JsonResponse doForgetPwd(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer backType = Integer.valueOf(request.getParameter("backType"));
		String number = request.getParameter("number");
		String verifyCode = request.getParameter("verifyCode");
		Integer type = ParamUtils.getInteger(request, "type", LoginDict.ValiCodeGetType.FORGET_PASSWORD);
		//2.交互
		//		//随机生产6位密码
//		String newPasswd=RandomUtils.createRandom(true, FocConstants.DEFAULT_PASSWORD_LENGTH);
		if(DoForgetPwd_Type.MOBILE.compareTo(backType)==0){
			//校验验证码是否正确
			valicodeManager.checkWithSession(type,number,verifyCode);
			valicodeManager.clearValicode(LoginDict.ValiCodeGetType.FORGET_PASSWORD);//清除验证码
			//将验证成功的信息缓存,以便在输入新密码时验证
			SessionManager.getSession().setAttribute(LoginConstant.Forget_Password_Result, true);
		}else{
			throw new ValidateRuntimeException("login.doForgetPwd.backType.unsupport");
		}
		
//		if(!valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.FORGET_PASSWORD, verifyCode)){
//			throw new ValidateRuntimeException("login.valicode.check.error");
//		}else {
////			SessionManager.getSession().setAttribute(SessionManager.Forget_Password_Result, true);
		////			logger.debug("Forget passwd result is: account="+number+",passwd="+newPasswd+"。");
		////			//更改数据库密码信息,并发送邮件或短信
////			boolean setNewPwdRes= loginService.setNewPassword(number, newPasswd,backType);
////			if(!setNewPwdRes){
////				throw new ValidateRuntimeException("login.password.forget.failed");
////			}
//		}
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 找回方式，设置指定密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/setNewPwd.json")
	public String setNewPwd(HttpServletRequest request){
		//1.搜集参数
		Long backType = Long.parseLong(request.getParameter("backType"));
		String number = request.getParameter("number");
		//		String verifyCode = request.getParameter("verifyCode");//此处失效
		String password = request.getParameter("password");
		//2.交互
		LoginNo loginNo = null;
		//校验验证码是否正确
		Boolean valicodeResCache = (Boolean)SessionManager.getSession().getAttribute(LoginConstant.Forget_Password_Result);
		if(valicodeResCache==null||valicodeResCache!=true){
			throw new ValidateRuntimeException("login.valicode.check.error");
		}else{
			SessionManager.getSession().setAttribute(LoginConstant.Forget_Password_Result, null);//清空
			//更改数据库密码信息
			loginNo= loginService.setNewPassword(number, password,backType);
		}
		//3.结果处理
		//设置账号信息到attribute
		setLogNoToAttr(request, loginNo);
		return "forward:/security/autoLoginAfterRegist.json";
	}
	
	/**
	 * 登陆后—修改密码—step01校验验证码 是否正确
	 * @param request
	 * @return
	 */
	@RequestMapping("/resetPwdCheckVerify.json")
	@ResponseBody
	public JsonResponse resetPwdCheckVerify(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String verifyCode = request.getParameter("verifyCode");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		LoginNo loginNo = commonLoginService.getUserPhoneAccInfo(userId);
		if(loginNo==null){
			throw new ValidateRuntimeException("login.getValidateCode.notBind.error");
		}else{
			String mobile = loginNo.getNo();//设定手机号
			//校验验证码是否正确
			valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.UPDATE_PASSWORD,mobile,verifyCode);
			valicodeManager.clearValicode(LoginDict.ValiCodeGetType.UPDATE_PASSWORD);//清除验证码
			//将验证成功的信息缓存,以便设定新密码时验证
			SessionManager.getSession().setAttribute(LoginConstant.Change_Password_Result, true);
		}
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 登录后，修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/resetPwd.json")
	@ResponseBody
	public JsonResponse changePwd(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String password = request.getParameter("password");
//		String oldPassword = request.getParameter("oldPassword");
//		String verifyCode = request.getParameter("verifyCode");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		
		LoginNo loginNo = commonLoginService.getUserPhoneAccInfo(userId);
		if(loginNo==null){
			throw new ValidateRuntimeException("login.getValidateCode.notBind.error");
		}else{
			//			String mobile = loginNo.getNo();//设定手机号
			//			//校验验证码是否正确
//			valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.UPDATE_PASSWORD,mobile,verifyCode);
			//			valicodeManager.clearValicode(LoginDict.ValiCodeGetType.UPDATE_PASSWORD);//清除验证码
			Boolean valicodeResCache = (Boolean)SessionManager.getSession().getAttribute(LoginConstant.Change_Password_Result);
			if(valicodeResCache==null||valicodeResCache!=true){
				throw new ValidateRuntimeException("login.changePwd.valicode.error");
			}else{
				//				SessionManager.getSession().setAttribute(LoginConstant.Change_Password_Result, null);//清空
			}
			
			//更改数据库密码信息
			boolean setNewPwdRes= loginService.changePassword(userId, password);
			if(!setNewPwdRes){
				throw new BusinessRuntimeException("login.password.update.failed");
			}else{
				SessionManager.getSession().setAttribute(LoginConstant.Change_Password_Result, null);//清空
			}
		}
		//3.结果处理
		return jsonResponse;
	}
	
	@RequestMapping("/isLoginExpire.json")
	@ResponseBody
	public JsonResponse isLoginExpire(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		UserContext.getOperIdMustExistBigInteger();//需要登录
		return jsonResponse;
	}
	
	
//	/**
	//	 * 成功获取登录信息后的处理
//	 * @param obj
//	 * @return
//	 */
//	public static JsonResponse afterLoginCheck(LoginNoEntity obj){
//		JsonResponse jsonResponse = new JsonResponse();
//		if(obj==null){
//			throw new BusinessRuntimeException("login.afterLoginCheck.failed");
//		}
	//		//存入Session
////		UserContext.setCurrUser(obj);
//		jsonResponse.putData("realName", obj.getUserEntity().getRealName());
//		jsonResponse.putData("userId",  obj.getUserEntity().getId());
//		jsonResponse.putData("loginType", obj.getType());
//		jsonResponse.putData("imgProfile",obj.getUserEntity().getImgprofile());
//		jsonResponse.putData("defaultRoomId", obj.getUserEntity().getDefaultRoomId());
//		jsonResponse.putData("discount", obj.getLeastDiscount());
//		jsonResponse.putData("huaId", obj.getUserEntity().getHuaId());
//		RoomEntity defaultRoomEntity = obj.getUserEntity().getDefaultRoomEntity();
//		if(defaultRoomEntity!=null&&defaultRoomEntity.containsAdmin(obj.getUserEntity().getId())){
//			jsonResponse.putData("isAdmin", true);
//		}else{
//			jsonResponse.putData("isAdmin", false);
//		}
	//		if(!FocConstants.ROOM_NULL_ID_REAL.equals(defaultRoomEntity.gettRealRoomFId())){//如果不是空门牌，才返回默认门牌信息
//			jsonResponse.putData("defaultRoomInfo", CommonRoomUtil.getRoomBaseInfo(defaultRoomEntity));
//		}else{
	//			jsonResponse.putData("defaultRoomInfo",null);//否则返回null
//		}
	//		jsonResponse.putData("isFirstRegist",  obj.getIsFirstRegist());//设置是否首次注册标识
//		return jsonResponse;
//	}
	
	/**
	 * 将账号信息设置到attribute，方便后续自动登录使用
	 * @param request
	 * @param loginNo
	 */
	private void setLogNoToAttr(HttpServletRequest request,LoginNo loginNo){
		request.setAttribute(LoginConstant.ATTRIBUTE_NAME_ACCOUNT, loginNo.getNo());
		request.setAttribute(LoginConstant.ATTRIBUTE_NAME_PASSWD, loginNo.getPassword());
		request.setAttribute(LoginConstant.ATTRIBUTE_ACCOUNT_TYPE, loginNo.getType());
	}
	
//	@RequestMapping("/doLogin.json")
//	public String doLogin(HttpServletRequest request){
//		SimpleLoginInfo simpleLoginInfo = SimpleLoginInfo.parseLoginInfo(request);
//		String account = simpleLoginInfo.getNumber();
//		String password = simpleLoginInfo.getPassword();
//		Long accountType = simpleLoginInfo.getAccountType();
//		return "redirect:http://192.168.9.111:8084/cas/registerLogin?username="+account+"&password="+password+"&type="+accountType+"&service=http://shiyl:8080/API/j_spring_cas_security_check";
//	}
	
}
