/**   
 * Filename:    CommonLoginService.java   
 * @version:    1.0  
 * Create at:   2014年6月3日 上午2:18:29   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月3日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.accountManage.dao.IAccountManageDao;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonLoginDao;
import com.cnfantasia.server.api.commonBusiness.entity.LoginAccNoAndType;
import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.login.entity.LoginSessionKeyConfig;
import com.cnfantasia.server.api.login.entity.QQApplyEntity;
import com.cnfantasia.server.api.login.entity.TaobaoApplyEntity;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.api.pub.session.SimpleSessionUser;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.springSecurity.EncodeImpl;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.session.SessionCryptUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commUserSession.dao.ICommUserSessionBaseDao;
import com.cnfantasia.server.domainbase.commUserSession.entity.CommUserSession;
import com.cnfantasia.server.domainbase.loginNo.dao.ILoginNoBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
import com.qq.open.OpenApiV3;
import com.qq.open.OpensnsException;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserBuyerGetRequest;
import com.taobao.api.response.UserBuyerGetResponse;

/**
 * Filename: CommonLoginService.java
 * 
 * @version: 1.0.0 Create at: 2014年6月3日 上午2:18:29 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月3日 shiyl 1.0 1.0 Version
 */
@Service
public class CommonLoginService implements ICommonLoginService {
	private Log logger = LogFactory.getLog(getClass());
	
	private static final String WlightAppValidateUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private ICommUserSessionBaseDao commUserSessionBaseDao;
	public void setCommUserSessionBaseDao(ICommUserSessionBaseDao commUserSessionBaseDao) {
		this.commUserSessionBaseDao = commUserSessionBaseDao;
	}

	protected AuthenticationManager authenticationManager;
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	private ILoginNoBaseDao loginNoBaseDao;
	public void setLoginNoBaseDao(ILoginNoBaseDao loginNoBaseDao) {
		this.loginNoBaseDao = loginNoBaseDao;
	}

	private ISysParamParser loginSessionKeyParamParser;
	public void setLoginSessionKeyParamParser(ISysParamParser loginSessionKeyParamParser) {
		this.loginSessionKeyParamParser = loginSessionKeyParamParser;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommonLoginDao commonLoginDao;
	public void setCommonLoginDao(ICommonLoginDao commonLoginDao) {
		this.commonLoginDao = commonLoginDao;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	
	private ISysParamParser weiXinLoginConfigParamParser;
	public void setWeiXinLoginConfigParamParser(ISysParamParser weiXinLoginConfigParamParser) {
		this.weiXinLoginConfigParamParser = weiXinLoginConfigParamParser;
	}
	
	private ISysParamParser qqApplyParamParser;
	public void setQqApplyParamParser(ISysParamParser qqApplyParamParser) {
		this.qqApplyParamParser = qqApplyParamParser;
	}

	private ISysParamParser taobaoApplyParamParser;
	public void setTaobaoApplyParamParser(ISysParamParser taobaoApplyParamParser) {
		this.taobaoApplyParamParser = taobaoApplyParamParser;
	}
	
	private IAccountManageDao accountManageDao;
	public void setAccountManageDao(IAccountManageDao accountManageDao) {
		this.accountManageDao = accountManageDao;
	}

	@Override
	public JsonResponse afterLoginCheck(HttpServletRequest request,LoginNoEntity obj) {
		JsonResponse jsonResponse = new JsonResponse();
		if (obj == null) {
			throw new BusinessRuntimeException("login.afterLoginCheck.failed");
		}
		// 存入Session
		// UserContext.setCurrUser(obj);
		{
			UserSimpleEntity userSimpleEntity = obj.getUserEntity();
			Map<String,Object> tmpUserMap = commEntityConvertService.baseUser2MapForLoginRes(userSimpleEntity);
			jsonResponse.putDataAll(tmpUserMap);
			{
				jsonResponse.putData("isAdmin", userSimpleEntity.fetchIsAdmin());
			}
			
//			{
//				RoomEntityWithValidate defaultRoomEntity = obj.getUserEntity().getDefaultRoomEntity();
//				if (defaultRoomEntity != null && defaultRoomEntity.getId() != null) {
//					List<BigInteger> adminIdsList = commonRoomDao.selectAdminIdsByRoomId(defaultRoomEntity.getId());
//					if (adminIdsList != null && adminIdsList.contains(obj.getUserEntity().getId())) {//TODO 
//						jsonResponse.putData("isAdmin", true);
//					} else {
//						jsonResponse.putData("isAdmin", false);
//					}
//				} else {
//					jsonResponse.putData("isAdmin", false);
//				}
//			}
			
//			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
//			jsonResponse.putData("realName", obj.getUserEntity().getRealName());
//			jsonResponse.putData("nickName", obj.getUserEntity().getNickName());
//			jsonResponse.putData("userId", obj.getUserEntity().getId());
//			jsonResponse.putData("mobile", obj.getUserEntity().getMobile());
//			jsonResponse.putData("sex", obj.getUserEntity().getSex());
//			jsonResponse.putData("birthday", obj.getUserEntity().getBirthday());
//			// jsonResponse.putData("imgProfile",obj.getUserEntity().getImgprofile());
//			jsonResponse.putData("imgProfile",fileServerService.getAccessUrl(userImageParamEntity.getBasePath() + obj.getUserEntity().getImgprofile(),obj));
//			jsonResponse.putData("defaultRoomId", obj.getUserEntity().getDefaultRoomId());
//			jsonResponse.putData("huaId", obj.getUserEntity().getHuaId());
		}
		
		jsonResponse.putData("loginType", obj.getType());
		jsonResponse.putData("discount", obj.getLeastDiscount()==null?null:obj.getLeastDiscount().getDiscount());// 最低折扣
		jsonResponse.putData("savedHbMoney", obj.getDiscountConvertMoney()==null?null:PriceUtil.div100(obj.getDiscountConvertMoney()));// 最低折扣可兑换的粮票金额
		jsonResponse.putData("isDiscountUsed", obj.getLeastDiscount()==null?null:obj.getLeastDiscount().hasUsed());// 最低折扣是否已被使用
		
		jsonResponse.putData("leftPrizeCount", obj.getLeftPrizeCount());// 剩余抽奖次数
		{
			RoomEntityWithValidate defaultRoomEntity = obj.getUserEntity().getDefaultRoomEntity();
			if (defaultRoomEntity==null||defaultRoomEntity.checkIsEmptyRoom()) {
				jsonResponse.putData("defaultRoomInfo", null);
			} else {// 如果不是空门牌，才返回默认门牌信息
//				jsonResponse.putData("defaultRoomInfo", CommonRoomUtil.getRoomBaseInfo(defaultRoomEntity));
				Map<String,Object> defaultRoomInfoMap = CommonRoomUtil.getRoomInfo(obj.getUserEntity().getId(),defaultRoomEntity, defaultRoomEntity.getRoomValidate(),defaultRoomEntity.getId());
				jsonResponse.putData("defaultRoomInfo",defaultRoomInfoMap);
			}
		}
		
		{//粮票可用余额
			jsonResponse.putData("hbBalance", obj.getHbBalance()==null?null:(PriceUtil.div100(obj.getHbBalance())));
		}
		{
			boolean isFirstRegist = false;
			RoomEntityWithValidate defaultRoomEntity = obj.getUserEntity().getDefaultRoomEntity();
			if (defaultRoomEntity==null||defaultRoomEntity.checkIsEmptyRoom()) {//空门牌
				String nowTime = dualDao.getNowTime();
				String addTime = obj.getSys0AddTime();
				if(addTime!=null){
					long distance = DateUtil.timeToLong(nowTime)-DateUtil.timeToLong(addTime);
					
					if (distance < 1*60*1000) {//1*60*1000 1分钟之内再次登录会提示
						isFirstRegist = true;
					}
				}
			}
			jsonResponse.putData("isFirstRegist", isFirstRegist);// 设置是否首次注册标识
		}
		
		// 存入Header的SessionKey
		{
			String sessionKey = getSessionKeyAnd2DB(request,obj.gettUserFId(),obj.getNo(), obj.getType());
			jsonResponse.putData("sessionKey", sessionKey);
		}
		{//剩余积分
			PointData pointData = obj.getPointData();
			jsonResponse.putData("leftPoint", pointData==null?0:pointData.getPointValue());
		}
		return jsonResponse;
	}

	@Override
	public String getSessionKeyAnd2DB(HttpServletRequest request,BigInteger userId,String accountNo, Long accType) {
		String nowTime = dualDao.getNowTime();
		try {
			String sessionKey = null;
		//	sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
			
			int subChannel = 0;
			
			try {
				subChannel = Integer.parseInt(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL));
			}catch(Exception e){
				
			}
			
			sessionKey = dealKickOut(request, userId);
			
			if(StringUtils.isEmpty(sessionKey)){
				sessionKey = SessionCryptUtil.generateSessionKey(accountNo, accType.intValue(), nowTime);
			}
			
			//将userId的所有sessionKey逻辑删除
			boolean is313 = checkIs313(request);
			commonLoginDao.deleteUserSessionLogicByUserId(accountNo,accType,(is313 && subChannel != HeaderConstant.SubChannelId.EBUY_MERCHANT_APP.intValue())?0:subChannel);
			//创建当前用户的sessionKey记录
			{
				// 存入数据库
				BigInteger commUserSessionAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_user_session);
				CommUserSession commUserSession = new CommUserSession();
				commUserSession.setAccNo(accountNo);
				commUserSession.setAccType(accType);
				commUserSession.setAutoSessionId(SessionManager.getSession().getId());
				commUserSession.setCreateTime(nowTime);
				commUserSession.setId(commUserSessionAddId);
				commUserSession.setSessionKey(sessionKey);
				commUserSession.setUserId(userId);
				commUserSession.setSubChannel(subChannel);
				commUserSessionBaseDao.insertCommUserSession(commUserSession);
			}
			
//			CommUserSession commUserSessionQry = new CommUserSession();
//			// commUserSessionQry.setSessionKey(sessionKey);
//			commUserSessionQry.setAccNo(accountNo);
//			commUserSessionQry.setAccType(accType);
//			List<CommUserSession> list = commUserSessionBaseDao.selectCommUserSessionByCondition(
//					MapConverter.toMap(commUserSessionQry), false);
//			if (list == null || list.size() <= 0) {
//				// 存入数据库
//				BigInteger commUserSessionAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_user_session);
//				CommUserSession commUserSession = new CommUserSession();
//				commUserSession.setAccNo(accountNo);
//				commUserSession.setAccType(accType);
//				commUserSession.setAutoSessionId(SessionManager.getSession().getId());
//				commUserSession.setCreateTime(nowTime);
//				commUserSession.setId(commUserSessionAddId);
//				commUserSession.setSessionKey(sessionKey);
//				commUserSession.setUserId(userId);
//				commUserSessionBaseDao.insertCommUserSession(commUserSession);
//			} else {
//				CommUserSession commUserSessionUpd = list.get(0);
//				commUserSessionUpd.setAccNo(accountNo);
//				commUserSessionUpd.setAccType(accType);
//				commUserSessionUpd.setAutoSessionId(SessionManager.getSession().getId());
//				commUserSessionUpd.setCreateTime(nowTime);
//				commUserSessionUpd.setSessionKey(sessionKey);
//				commUserSessionUpd.setUserId(userId);
//				commUserSessionBaseDao.updateCommUserSession(commUserSessionUpd);
//			}
			return sessionKey;
		} catch (Exception e) {
			// throw new
			// TimeOutRuntimeException("CommonLoginService.getSessionKeyAnd2DB.generateSessionKey.failed",e);
			throw new BusinessRuntimeException("CommonLoginService.getSessionKeyAnd2DB.generateSessionKey.failed", e);
		}
	}

	/**
	 * 处理是否要踢出原来用户 ,
	 * @param request
	 * @param userId
	 * @param sessionKey
	 * @return 返回null表示要踢出，否则找到之前的SessionKey，继续拿来用
	 */
	private String dealKickOut(HttpServletRequest request, BigInteger userId) {
		Long subChannelId = 0L;
		try {
			subChannelId = Long.valueOf(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL));
		}catch(Exception e){
			
		}
		
		
		int validSessionKeyCount = commonLoginDao.selectValidSessionKeyCountBy(userId, subChannelId);
		
		//如果>0, 则说明此用户在相同/相似渠道已经登录，再次登录需要踢出
		if (validSessionKeyCount > 0)
			return null;
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("sys0DelState", 0);
		paramMap.put("subChannel", subChannelId);
		
		List<CommUserSession> commUserSessionList = commUserSessionBaseDao.selectCommUserSessionByCondition(paramMap, false);
		if(commUserSessionList.size()>0){
			return commUserSessionList.get(0).getSessionKey();
		}
		
		return null;
	}

	/**
	 * 返回null表示sessionKey非法 若超时也会正常返回数据信息
	 * @param subChannel 
	 */
	@Override
	public SimpleSessionUser validateSessionKey(String sessionKey, int subChannel) {
		SimpleSessionUser resSimpleSessionUser = null;
		CommUserSession commUserSession = new CommUserSession();
		commUserSession.setSessionKey(sessionKey);
		if(subChannel!=0){
			commUserSession.setSubChannel(subChannel);
		}
		List<CommUserSession> list = commUserSessionBaseDao.selectCommUserSessionByCondition(
				MapConverter.toMap(commUserSession), false);
		if (list != null && list.size() > 0) {
			CommUserSession currSession = list.get(0);
			try {
				// SimpleSessionUser simpleSessionUser =
				// SessionCryptUtil.getSimpleSessionUser(sessionKey,
				// currSession.getAccNo(),
				// currSession.getAccType().intValue(), currSession.getCreateTime());
				if(UserContext.getOperIdBigInteger() != null && !UserContext.getOperIdBigInteger().equals(currSession.getUserId())) {
					SecurityContextHolder.clearContext();//清空上下文
				}
				SimpleSessionUser simpleSessionUser = new SimpleSessionUser(currSession.getAccNo(), currSession.getAccType()
						.intValue(), currSession.getCreateTime());
				resSimpleSessionUser = simpleSessionUser;
			} catch (Exception e) {
				logger.debug(
						"validateSessionKey for :" + sessionKey + " cause exception,userId is "+currSession.getUserId()+" ,accountNo is:" + currSession.getAccNo()
								+ ";accType is:" + currSession.getAccType().intValue() + ";createTime is:"
								+ currSession.getCreateTime(), e);
			}
		}
		return resSimpleSessionUser;
	}

	/**
	 * 自动登录 UsernamePasswordAuthenticationToken
	 * @param request
	 * @return
	 */
	@Override
	public void autoLogin(HttpServletRequest request, String accountNo, String password, Long accountType) {
		{// 适配SpringSecuty自动登录的解析
			request.setAttribute(LoginConstant.ATTRIBUTE_NAME_ACCOUNT, accountNo);
			request.setAttribute(LoginConstant.ATTRIBUTE_NAME_PASSWD, password);
			request.setAttribute(LoginConstant.ATTRIBUTE_ACCOUNT_TYPE, accountType);
		}
		
		// if(StringUtils.isEmpty(password)){//解决SpringSecurity的Bad credentials问题
		// password = RandomUtils.createRandom(false, 6);
		// request.setAttribute(LoginController.ATTRIBUTE_NAME_PASSWD, password);
		// }
//		CasAuthenticationToken cas = new CasAuthenticationToken(key, principal, credentials, authorities, userDetails, assertion);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountNo, password);
		try {
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			HttpSession session = request.getSession();
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());
		} catch (AuthenticationException e) {
			String msKey = e.getMessage();
			if (!StringUtils.isEmpty(MessageSourceUtil.getMessage(msKey))) {
				throw new BusinessRuntimeException(msKey);// 尽量抛出自定义的异常信息
			} else {
				logger.debug("autoLogin cause exception,the message is:" + e.getMessage());
				throw new TimeOutRuntimeException("CommonLoginService.autoLogin.AuthenticationException", e);
				// throw new
				// BusinessRuntimeException("CommonLoginService.autoLogin.AuthenticationException",
				// e);
			}
		}
	}

	/**
	 * 自动登录
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public void autoLoginWithNoAndType(HttpServletRequest request, String accountNo, Long accType) {
		// 查询账户信息
		LoginNo loginNoQry = new LoginNo();
		loginNoQry.setNo(accountNo);
		loginNoQry.setType(accType);
		List<LoginNo> accList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry), false);
		if (accList == null || accList.size() <= 0) {
			throw new BusinessRuntimeException(
					"CommonLoginService.autoLogin.autoLoginNoPassword.selectLoginNoByCondition.empty");
		} else {
			String password = accList.get(0).getPassword();
			autoLogin(request, accountNo, password, accType);
		}
	}
	
	private boolean checkIs313(HttpServletRequest request){
		boolean is313 = true;
		if(!StringUtils.isEmpty(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION))){
			Long version = VersionTransferUtil.versionStr2Long(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION));
			Long version313 = VersionTransferUtil.versionStr2Long("3.1.3");
			if(version.compareTo(version313)>0){
				is313 = false;
			}
			
		}
		return is313;
	}
	
	@Override
	public void checkSessionKeyAndAutoLogin(HttpServletRequest request) {
		String sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
//		if(StringUtils.isEmpty(sessionKey)){//syl-add 增加parameter的支持
//			sessionKey = request.getParameter(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
//		}
		logger.debug("The sessionKey is :"+sessionKey + ";SessionId:" + request.getSession().getId());
		Long version = VersionTransferUtil.versionStr2Long(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION));
		if(version != null) {
			request.getSession().setAttribute("version", version);
		}
		
		
//		if (!UserContext.isUserInContext()) {// 用户上下文不存在时
			if (!StringUtils.isEmpty(sessionKey)) {// sessionKey不为空
				boolean is313 = checkIs313(request);
				int subChannel = Integer.valueOf(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL));
				SimpleSessionUser simpleSessionUser = validateSessionKey(sessionKey,is313?0:subChannel);
				String nowTime = dualDao.getNowTime();
				if(simpleSessionUser == null){// 为空
					SecurityContextHolder.clearContext();//清空上下文
					throw new TimeOutRuntimeException("HeaderInterceptor.simpleSessionUser.isEmpty");
				}else if(isSessionUserExpired(simpleSessionUser, nowTime)){// 已失效
					SecurityContextHolder.clearContext();//清空上下文
					throw new TimeOutRuntimeException("HeaderInterceptor.simpleSessionUser.isExpired");
				}else {// 未失效
					synchronized (sessionKey) {
						if(!UserContext.isUserInContext()){
							autoLoginWithNoAndType(request, simpleSessionUser.getAccount(), simpleSessionUser.getAccType().longValue());
							if(!UserContext.isUserInContext()){
								logger.debug("autoLoginWithNoAndType failed The sessionKey is :"+sessionKey+",simpleSessionUser is "+JSON.toJSONString(simpleSessionUser));
							}
						}
					}
				}
			}
//		}
	}

	public boolean isSessionUserExpired(SimpleSessionUser simpleSessionUser, String nowTime) {
		String createTime = simpleSessionUser.getCreateTime();
		if (createTime == null) {
			return true;
		}
		try {
			LoginSessionKeyConfig loginSessionKeyConfig = loginSessionKeyParamParser.parseParamValue();
			Long outTimeLimit = loginSessionKeyConfig.getExpiredMinuteTimeMillions();
			if (outTimeLimit < 0L) {// 负数永不超时
				return false;
			}
			Long nowLong = DateUtil.timeToLong(nowTime);
			Long createTimeLong = DateUtil.timeToLong(createTime);
			if (nowLong!=null&&createTimeLong!=null&&(nowLong-createTimeLong<=outTimeLimit)) {
				return false;
			}
		} catch (Exception e) {
			logger.warn(
					"Check account is Expired err,account is:" + simpleSessionUser.getAccount() + ";errMsg is:" + e.getMessage(),
					e);
			return true;
		}
		return true;
	}

	@Override
	public void deleteSessionUserFromDB(String sessionKey) {
		if(StringUtils.isEmpty(sessionKey)){
			return;
		}
		CommUserSession commUserSession = new CommUserSession();
		commUserSession.setSessionKey(sessionKey);
		List<CommUserSession> list = commUserSessionBaseDao.selectCommUserSessionByCondition(
				MapConverter.toMap(commUserSession), false);
		if (list != null && list.size() > 0) {
			List<BigInteger> idDelList = new ArrayList<BigInteger>();
			for(CommUserSession tmpCommUserSession:list){
				idDelList.add(tmpCommUserSession.getId());
			}
			int resCount = commUserSessionBaseDao.deleteCommUserSessionLogicBatch(idDelList);// 逻辑删除
			logger.debug("deleteCommUserSessionLogicBatch resCount is :"+resCount);
		}
	}

	@Override
	public boolean verifyAccount(HttpServletRequest request) {
		String accountNo = request.getParameter("number");
		String password = EncodeImpl.doEncodePassword(request.getParameter("password"));
		Long accountType = Long.valueOf(request.getParameter("accType"));
		Integer count = commonLoginDao.selectUserCountByAccInfo(accountNo, password, accountType);
		//			autoLogin(request, accountNo, password, accountType);
		return count != null && count > 0;
	}

	@Override
	public LoginNo getUserPhoneAccInfo(BigInteger userId) {
		return accountManageDao.selectUserPhoneAccInfo(userId);
//		LoginNo currBindPhoneAcc = null;
//		//查询当前用户已经绑定的手机号
//		if(userId!=null){
//			LoginNo loginNoQry = new LoginNo();
//			loginNoQry.settUserFId(userId);ss
//			loginNoQry.setType(LoginDict.AccountType.MOBILE);
//			List<LoginNo> tmpList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry), false);
//			if(tmpList==null||tmpList.size()<=0){
//				
//			}else{
//				currBindPhoneAcc =tmpList.get(0);
//			}
//			if((tmpList.size()>1)){//将错误数据全部逻辑删除
//				List<BigInteger> idDelList = new ArrayList<BigInteger>();
//				for(int i=1;i<tmpList.size();i++){
//					idDelList.add(tmpList.get(i).getId());
//				}
//				int res = loginNoBaseDao.deleteLoginNoLogicBatch(idDelList);
//				if(res<=0){
//					throw new ValidateRuntimeException("CommonLoginService.getUserPhoneAccInfo.loginNo.size>1.error");
//				}
//			}
//		}
//		return currBindPhoneAcc;
	}

	@Override
	public Integer updateLoginSessionByUserId(BigInteger userId, String no, Long type) {
		return commonLoginDao.updateLoginSessionByUserId(userId, no, type);
	}

	@Override
	public LoginAccNoAndType validateAccessToken(Integer regType, String openId, String accessToken) {
		String accountNo = openId;// 使用openId作为账号
		Long accType = null;
		if (LoginDict.Regist3rd_Type.TAOBAO.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.TAOBAO;
			boolean validateTokenRes = validateAccessTokenTaobao(accessToken);
			if (!validateTokenRes) {
				throw new ValidateRuntimeException("login.regist3rd.taobaoAccessToken.validate.error");
			}
		} else if (LoginDict.Regist3rd_Type.QQ.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.QQ;
			boolean validateTokenRes = validateAccessTokenQQ(accessToken, openId);
			if (!validateTokenRes) {
				throw new ValidateRuntimeException("login.regist3rd.qqAccessTokenOpenId.validate.error");
			}
		} else if (LoginDict.Regist3rd_Type.WEI_XIN.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.WEI_XIN;
			boolean validateTokenRes = validateAccessTokenWeiXin(accessToken, openId);
			if (!validateTokenRes) {
				throw new ValidateRuntimeException("login.regist3rd.weixinAccessTokenOpenId.validate.error");
			}
		} else if (LoginDict.Regist3rd_Type.WEI_XIN_LIGHT_APP.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.WEI_XIN_LIGHT_APP;
			boolean validateTokenRes = validateAccessTokenWeiXinLightApp(accessToken, openId);//syl-del 2015-4-21 20:05:59
//			boolean validateTokenRes = validateAccessTokenWeiXin(accessToken, openId);
			if (!validateTokenRes) {
				throw new ValidateRuntimeException("login.regist3rd.weixinLightAppAccessTokenOpenId.validate.error");
			}
		}/*
			 * else if(regType==DictConstants.Regist3rd_Type.SINA_MICROBLOG){ accType
			 * = LoginDict.AccountType.SINA_MICROBLOG; }else
			 * if(regType==DictConstants.Regist3rd_Type.TECENT_MICROBLOG){ accType =
			 * LoginDict.AccountType.TECENT_MICROBLOG; }
			 */
		else {
			throw new ValidateRuntimeException("login.regist3rd.unknownType.error");
		}
		return new LoginAccNoAndType(accountNo, accType);
	}
	
	private boolean validateAccessTokenQQ(String accessToken, String openId) {
		QQApplyEntity applyEntity = qqApplyParamParser.parseParamValue();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("openid", openId);
		params.put("openkey", accessToken);
		params.put("format", applyEntity.getFormat());
		params.put("pf", applyEntity.getPf());
		OpenApiV3 sdk = new OpenApiV3(applyEntity.getAppKey(), applyEntity.getSecret());
		sdk.setServerName(applyEntity.getServerName());
		try {
			String resp = sdk.api(QQApplyEntity.API_NAME_GET_USERINFO, params, applyEntity.getProtocol());
			logger.debug(resp);
			JSONObject jsonObject = JSON.parseObject(resp);
			if (jsonObject.getInteger("ret") != null && jsonObject.getInteger("ret") == 0) {// 0表示正常返回
				return true;
			}
		} catch (OpensnsException e) {
			logger.debug(
					"ValidateAccessTokenQQ cause Exception, errcode is " + e.getErrorCode() + ";errMsg is " + e.getMessage(), e);
		}
		return false;
	}

	@Override
	public String getUnionIdByWeiXinInfo(Integer regType,String openId, String accessToken) {
		String unionId = null;
		logger.debug("CommonLoginService.getUnionIdByWeiXinInfo params,regType is:"+regType+",openId is:"+openId+",accessToken is:"+accessToken);
		if (LoginDict.Regist3rd_Type.WEI_XIN.compareTo(regType) == 0||LoginDict.Regist3rd_Type.WEI_XIN_LIGHT_APP.compareTo(regType) == 0) {
			String userinfoUrl = "https://api.weixin.qq.com/sns/userinfo";
			if(LoginDict.Regist3rd_Type.WEI_XIN_LIGHT_APP.compareTo(regType) == 0){
				userinfoUrl = WlightAppValidateUrl;
			}
			String postData = userinfoUrl+"?access_token=" + accessToken + "&openid=" + openId;
			logger.info("userinfoUrl is : " + postData);
			HttpUriRequest httpUriRequest = new HttpGet(postData);
			CookieStore cookieStore = new BasicCookieStore();
			CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			try {
				CloseableHttpResponse response = client.execute(httpUriRequest);
				{// response.getEntity()
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
							String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
							Map<String, Object> resMap = JSON.parseObject(res);
							unionId = resMap.get("unionid").toString();
						}
					}
				}
			} catch (Exception e) {
				logger.info("CommonLoginService.getUnionIdByWeiXinInfo cause Exception,errMsg is " + e.getMessage(), e);
			}
		}
		return unionId;
	}
	
	private boolean validateAccessTokenWeiXin(String accessToken, String openId) {
		String validateUrl = weiXinLoginConfigParamParser.parseParamValue();
		String postData = validateUrl+"?access_token=" + accessToken + "&openid=" + openId;
		HttpUriRequest httpUriRequest = new HttpGet(postData);
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try {
			CloseableHttpResponse response = client.execute(httpUriRequest);
			{// response.getEntity()
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
						Map<String, Object> resMap = JSON.parseObject(res);
						String errcode = resMap.get("errcode").toString();
						String errmsg = resMap.get("errmsg").toString();
						if ("0".equals(errcode)) {
							return true;
						} else {
							logger.debug("ValidateAccessTokenWeiXin failed, errcode is " + errcode + ";errMsg is " + errmsg);
							throw new BusinessRuntimeException("LoginService.validateAccessTokenWeiXin.validateFailed", new Object[] {
									errcode, errmsg });
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("ValidateAccessTokenWeiXin cause Exception,errMsg is " + e.getMessage(), e);
			throw new BusinessRuntimeException("LoginService.validateAccessTokenWeiXin.exception", e);
		}
		return false;
	}
	
	private boolean validateAccessTokenWeiXinLightApp(String accessToken, String openId) {
		String validateUrl = WlightAppValidateUrl;
		String postData = validateUrl+"?access_token=" + accessToken + "&openid=" + openId;
		logger.info("validateAccessTokenWeiXinLightApp.posData is:" + postData);
		HttpUriRequest httpUriRequest = new HttpGet(postData);
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try {
			CloseableHttpResponse response = client.execute(httpUriRequest);
			{// response.getEntity()
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
						logger.info("validateAccessTokenWeiXinLightApp.res is:" + res);
						Map<String, Object> resMap = JSON.parseObject(res);
						String openid = resMap.get("openid").toString();
						String errcode = resMap.get("errcode")==null?null:resMap.get("errcode").toString();
						String errmsg = resMap.get("errmsg")==null?null:resMap.get("errmsg").toString();
						if (!StringUtils.isEmpty(openid)) {
							return true;
						} else {
							logger.debug("validateAccessTokenWeiXinLightApp failed, errcode is " + errcode + ";errMsg is " + errmsg);
							throw new BusinessRuntimeException("LoginService.validateAccessTokenWeiXin.validateFailed", new Object[] {
									errcode, errmsg });
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("validateAccessTokenWeiXinLightApp cause Exception,errMsg is " + e.getMessage(), e);
			throw new BusinessRuntimeException("LoginService.validateAccessTokenWeiXin.exception", e);
		}
		return false;
	}
	
	private boolean validateAccessTokenTaobao(String accessToken) {
		TaobaoApplyEntity applyEntity = taobaoApplyParamParser.parseParamValue();
		TaobaoClient client = new DefaultTaobaoClient(applyEntity.getUrl(), applyEntity.getAppKey(),
				applyEntity.getSecret());
		UserBuyerGetRequest req = new UserBuyerGetRequest();
		req.setFields(TaobaoApplyEntity.FIELDS_OF_GET_USERINFO);
		try {
			UserBuyerGetResponse response = client.execute(req, accessToken);
			com.taobao.api.domain.User taobaoUser = response.getUser();
			logger.debug(JSON.toJSON(taobaoUser));
			if (!StringUtils.isEmpty(taobaoUser)) {
				return true;
			}
		} catch (ApiException e) {
			logger.debug("ValidateAccessTokenTaobao cause Exception, errcode is " + e.getErrCode() + ";errMsg is "
					+ e.getErrMsg());
		}
		return false;
	}
	
	@Override
	public LoginNo insertLoginNo(BigInteger addId,String account,Long accountType,BigInteger userId,String unionId,String encodePwd,Integer createType) {
		// 获取登录账号表序id
		BigInteger loginNoId = addId;
		LoginNo loginNo = new LoginNo();
		loginNo.setId(loginNoId);
		loginNo.setNo(account);
		loginNo.setType(accountType);
		loginNo.settUserFId(userId);
		loginNo.setCreateType(createType);
		if(StringUtils.isEmpty(unionId)){
//				loginNo.setUnionId("0");
		}else{
			loginNo.setUnionId(unionId);
		}
		{// 密码加密
//				String encodePwd = PasswordEcoderUtil.encodePassword(tmpPasswd, loginNo.getNo(), loginNo.getType());
			loginNo.setPassword(encodePwd);
		}

		// 新增账号
		{
			int addLoginNoCount = loginNoBaseDao.insertLoginNo(loginNo);
			if (addLoginNoCount <= 0) {
				throw new BusinessRuntimeException("login.regist.insertUserNo.failed");
			}
		}
		return loginNo;
	}

	@Override
	public void expiredLoginSessionByUserId(BigInteger userId,String accNo,Long accType) {
		commonLoginDao.deleteUserSessionLogicByUserId(accNo, accType);
	}

}
