package com.cnfantasia.server.api.login.service;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.entity.LoginAccNoAndType;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommDataUpgradeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPointService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.inviteReward.service.IInviteRewardService;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.login.dao.ILoginDao;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.login.entity.SimpleLoginInfo;
import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.api.point.service.IPointService;
import com.cnfantasia.server.api.prize.dao.IPrizeDao;
import com.cnfantasia.server.api.prizeRule.service.IPrizeRuleManager;
import com.cnfantasia.server.api.pub.generator.HuaGenerator;
import com.cnfantasia.server.api.pub.springSecurity.EncodeImpl;
import com.cnfantasia.server.api.redenvelope.util.IDiscount2hbRule;
import com.cnfantasia.server.api.user.constant.UserDict;
import com.cnfantasia.server.api.user.entity.UserImageParamEntity;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.loginNo.dao.ILoginNoBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
import com.cnfantasia.server.domainbase.prizeRecord.dao.IPrizeRecordBaseDao;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;
import com.cnfantasia.server.domainbase.remoteUser.entity.RemoteUser;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;
import com.cnfantasia.server.domainbase.userTmp.dao.IUserTmpBaseDao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
	private Log logger = LogFactory.getLog(getClass());
	private ICommonRoomService commonRoomService;

	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

//	private IUserDao userDao;
//	public void setUserDao(IUserDao userDao) {
//		this.userDao = userDao;
//	}
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}

	private ILoginNoBaseDao loginNoBaseDao;
	public void setLoginNoBaseDao(ILoginNoBaseDao loginNoBaseDao) {
		this.loginNoBaseDao = loginNoBaseDao;
	}

	private ILoginDao loginDao;

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	private IUserTmpBaseDao userTmpBaseDao;
	public void setUserTmpBaseDao(IUserTmpBaseDao userTmpBaseDao) {
		this.userTmpBaseDao = userTmpBaseDao;
	}

//	private IDualDao dualDao;
//	public void setDualDao(IDualDao dualDao) {
//		this.dualDao = dualDao;
//	}

	private IPrizeDao prizeDao;
	public void setPrizeDao(IPrizeDao prizeDao) {
		this.prizeDao = prizeDao;
	}

	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamParser userImageParamParser;
	public void setUserImageParamParser(ISysParamParser userImageParamParser) {
		this.userImageParamParser = userImageParamParser;
	}

	private IValicodeManager valicodeManager;
	public void setValicodeManager(IValicodeManager valicodeManager) {
		this.valicodeManager = valicodeManager;
	}

	private IPrizeRecordBaseDao prizeRecordBaseDao;
	public void setPrizeRecordBaseDao(IPrizeRecordBaseDao prizeRecordBaseDao) {
		this.prizeRecordBaseDao = prizeRecordBaseDao;
	}

	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}

	private ICommonEbuyService commonEbuyService;
	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	

	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}
	
	private ICommonRedenvelopeService commonRedenvelopeService;
	public void setCommonRedenvelopeService(ICommonRedenvelopeService commonRedenvelopeService) {
		this.commonRedenvelopeService = commonRedenvelopeService;
	}
	
	private IPrizeRuleManager prizeRuleManager;
	public void setPrizeRuleManager(IPrizeRuleManager prizeRuleManager) {
		this.prizeRuleManager = prizeRuleManager;
	}
	
	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	private ICommMobileService commMobileService;
	public void setCommMobileService(ICommMobileService commMobileService) {
		this.commMobileService = commMobileService;
	}
	
	private ICommonPointService commonPointService;
	public void setCommonPointService(ICommonPointService commonPointService) {
		this.commonPointService = commonPointService;
	}
	
	private IPointService pointService;
	public void setPointService(IPointService pointService) {
		this.pointService = pointService;
	}

	private IDiscount2hbRule discount2hbRule;
	public void setDiscount2hbRule(IDiscount2hbRule discount2hbRule) {
		this.discount2hbRule = discount2hbRule;
	}
	
	private ICommDataUpgradeService commDataUpgradeService;
	public void setCommDataUpgradeService(ICommDataUpgradeService commDataUpgradeService) {
		this.commDataUpgradeService = commDataUpgradeService;
	}
	
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}
	
	private IInviteRewardService inviteRewardService;
	public void setInviteRewardService(IInviteRewardService inviteRewardService) {
		this.inviteRewardService = inviteRewardService;
	}
	
//	private ILoginLogBaseDao loginLogBaseDao;
//	public void setLoginLogBaseDao(ILoginLogBaseDao loginLogBaseDao) {
//		this.loginLogBaseDao = loginLogBaseDao;
//	}

	/**
	 * 此接口已经不再使用
	 */
	@Override
	@Deprecated
	public boolean sendMsg(String mobile, String msg) {
		return commMobileService.sendMsg(mobile, msg);
	}

	@Override
	public LoginNo registSimple(String account, Long accountType, Long subChannelId,String version, String utmCampaign,
			String utmContent, String inviteNo, String deviceId, String mobile, String realName,String nickName, String sex, String birthday,
			String imgprofile,String password,String unionId) {
		{// 判断账号和账号类别是否已经注册过
			if (checkIsRegist(account, accountType)) {
				throw new BusinessRuntimeException("LoginService.registSimple.selectLoginNoCount.isExist.failed");
			}
		}
		// 设置用户信息
		// 获取用户表id
		BigInteger userId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user);
		if (StringUtils.isEmpty(userId)) {
			throw new BusinessRuntimeException("login.regist.getNextUserId.failed");
		}
		// 新增用户
		String huaId = HuaGenerator.getUserHuaId(userId);
		User user = new User();
		user.setId(userId);
		user.setHuaId(huaId);
		user.setNickName(nickName);//设置昵称
		if (LoginDict.AccountType.MOBILE.compareTo(accountType) == 0) {
			user.setMobile(account);
		} else {
			if (!StringUtils.isEmpty(mobile)) {
				user.setMobile(mobile);
			}
		}
		user.settChannelSubFId(subChannelId);
		user.setVersion(version);
		user.setUtmCampaign(utmCampaign);
		user.setUtmContent(utmContent);
		user.setInviteNo(inviteNo);
		user.setRealName(realName);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setImgprofile(imgprofile);
		user.setUserState(UserDict.UserState.IN_USE);
		user.setIsFirstLoginStatus(UserDict.User_IsFirst_LoginStatus.NEVER_LOGIN);
		user.setDeviceId(deviceId);// 设备Id
		int addUserCount = userBaseDao.insertUser(user);
		if (addUserCount <= 0) {
			throw new BusinessRuntimeException("login.regist.insertUser.failed");
		}
		// 默认设置空门牌
		UserHasTRoom userHasTRoom = commonRoomService.addNullRoom(userId);
		// 设置登录账号信息
		String tmpPasswd = null;
		if(StringUtils.isEmpty(password)){
			tmpPasswd = RandomUtils.createRandom(true, LoginConstant.DEFAULT_PASSWORD_LENGTH);
		}else{
			tmpPasswd = password;
		}
		
		LoginNo loginNo = null;
		{//新增登录账号信息
			// 密码加密
	//		String encodePwd = PasswordEcoderUtil.encodePassword(tmpPasswd, loginNo.getNo(), loginNo.getType());
			String encodePwd = EncodeImpl.doEncodePassword(tmpPasswd);//syl-upd-2015-4-2 19:15:55
			List<BigInteger> loginNoAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_login_no,2);
			{//创建主登录账号
				BigInteger loginNoId = loginNoAddIdList.get(0);// 获取登录账号表序id
				loginNo = commonLoginService.insertLoginNo(loginNoId, account, accountType, userId, unionId, encodePwd,LoginDict.LoginNo_CreateType.CommLogIn);
				loginNo.setOldPwd(tmpPasswd);
			}
			{
				BigInteger huaLoginNoId =loginNoAddIdList.get(1);
				commonLoginService.insertLoginNo(huaLoginNoId, huaId, LoginDict.AccountType.HUA_ID, userId, null, encodePwd,LoginDict.LoginNo_CreateType.CommLogIn);
				{// 远程注册
					remoteRegist(huaId, encodePwd, huaId, userId);
				}
			}
		}
		
		/*
		{// 录入中奖记录信息
			UserTmp userTmpQry = new UserTmp();
			userTmpQry.setDeviceId(deviceId);
			userTmpQry.setDeviceType(subChannelId);
			List<UserTmp> userTmpList = userTmpBaseDao.selectUserTmpByCondition(MapConverter.toMap(userTmpQry), false);
//			if (userTmpList != null && userTmpList.size() == 1) {
			if (userTmpList != null && userTmpList.size() >= 1) {
				PrizeRecordTmp prTmp = commonPrizeService.getLeastDiscountNotLoginNowDayTmpUser(userTmpList.get(0).getId());
//				PrizeRecordTmp prTmp = commonPrizeService.getLeastDiscountNotLoginNowDay(deviceId, subChannelId);// 查询当天最低的中奖记录
//				PrizeRecordTmp prTmp = loginDao.selectLastRecord(userTmpList.get(0).getId());// 查询最近一次的中奖记录
				if (prTmp != null) {
					List<PrizeRecord> prList = new ArrayList<PrizeRecord>();
					PrizeRecord prAA = new PrizeRecord();
					prAA.setDiscount(prTmp.getDiscount());
					prAA.setEndTime(prTmp.getEndTime());
					prAA.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_prize_record));
					prAA.setPrizeTime(prTmp.getPrizeTime());
					prAA.settUserHasTRoomFId(userHasTRoom.getId());
					prAA.setStatus(PrizeDict.PrizeRecord_Status.NOT_USE);
					prList.add(prAA);
					// 录入中奖记录
					prizeRecordBaseDao.insertPrizeRecordBatch(prList);
					// 删除临时中奖记录
//					loginDao.deletePrizeRecordTmpByUserTmpId(userTmpList.get(0).getId());// 批量逻辑删除
					loginDao.deletePrizeRecordTmpByUserTmpId(deviceId,subChannelId);// 批量逻辑删除
				}
				// 删除临时用户
//				userTmpBaseDao.deleteUserTmpLogic(userTmpList.get(0).getId());
				List<BigInteger> delTmpUserIdList = new ArrayList<BigInteger>();
				for(UserTmp userTmp:userTmpList){
					delTmpUserIdList.add(userTmp.getId());
				}
				userTmpBaseDao.deleteUserTmpLogicBatch(delTmpUserIdList);
			} 
//			else {
//				if (userTmpList != null && userTmpList.size() > 1) {
//					throw new BusinessRuntimeException("LoginService.registSimple.multiTmpUser.error");
//				}
//			}
		}
		 */
		{// 录入购物车信息
			UserIdType userIdType = commonDeviceService.getUserIdType();
			{//检查当前登录的用户是否有购物车,若无则创建
				commonEbuyService.checkAndCreateEbuyBuyCar(userId, LoginDict.UserRegistOrTmp.REGIST_USER);
			}
			commonEbuyService.collectEbuyBuyCar(userIdType.getUserId(), userIdType.getUserType(), userId,
					LoginDict.UserRegistOrTmp.REGIST_USER);
		}
		// //发送密码
		// logger.debug("The password for "+loginNo.getNo()+" to send is "+tmpPasswd+".");
		// boolean sendRes = sendInfoByType(loginNo.getType(), loginNo.getNo(),
		// tmpPasswd);
		// if(!sendRes){
		// throw new BusinessRuntimeException("login.regist.sendInfoByType.failed");
		// }
		/**
		 * 神码行动处理  huangzj2015-05-11
		 * */
		if(!StringUtils.isEmpty(user.getInviteNo())){//如果邀请码不为空，则根据邀请码获取邀请奖励配置信息
			inviteRewardService.excuteInviteRewardForRegister(user.getId(), user.getInviteNo(), user.getMobile());
		}
		return loginNo;
	}

	@Override
	public LoginNo regist(String account, Integer regType, Long subChannelId,String version, String utmCampaign, String utmContent,
			String inviteNo, String deviceId,String password) {
		String unionId = null;
		String mobile = null;
		// 通过注册方式判定账号类别
		Long accType = null;
		if (LoginDict.RegistComm_Type.MAIL.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.MAIL;
		} else if (LoginDict.RegistComm_Type.MOBILE.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.MOBILE;
			mobile = account;// 录入手机号
		} else {
			throw new ValidateRuntimeException("loginService.regist.unsupportRegType.error");
		}
		// 校验账号是否已被注册
		LoginNo loginNoQry = new LoginNo();
		loginNoQry.setType(accType);
		loginNoQry.setNo(account);
		int existCount = loginNoBaseDao.selectLoginNoCount(MapConverter.toMap(loginNoQry), false);
		if (existCount > 0) {
			throw new ValidateRuntimeException("login.regist.loginNoCheck.failed");
		}
		// 执行注册
		LoginNo loginNoRegistTmp = registSimple(account, accType, subChannelId,version, utmCampaign, utmContent, inviteNo,
				deviceId, mobile, null,null, null, null, null, password,unionId);
		// //进行登录，获取登录信息
		// LoginNoEntity loginResult=
		// loginDao.selectLoginNoEntityByAccount(loginNoRegistTmp.getNo(),
		// loginNoRegistTmp.getType());
		// 返回
		return loginNoRegistTmp;
	}

	@Override
	public synchronized LoginNo regist3rd(Integer regType, String openId, String accessToken, String mobile, String realName,String nickName,
			String sex, String birthday, byte[] userImage, String userImgprofileName, Long subChannelId,String version, String deviceId
			,String unionId) {
		{//syl-add 2015-5-29 17:41:14 check And update UnionId
			String realUnionId = commonLoginService.getUnionIdByWeiXinInfo(regType,openId, accessToken);
			if(!StringUtils.isEmpty(unionId)&&!StringUtils.isEmpty(realUnionId)){
				if(!unionId.equals(realUnionId)){
					logger.info("LoginService.regist3rd,illegality unionId,request unionId is:"+unionId+",realUnionId is:"+realUnionId+",openId is:"+openId+",accessToken is:"+accessToken);
				}
			}
			if(!StringUtils.isEmpty(realUnionId)){
				unionId = realUnionId;
			}
		}
		
		LoginAccNoAndType loginAccNoAndType = commonLoginService.validateAccessToken(regType, openId, accessToken);
		String accountNo = loginAccNoAndType.getAccountNo();
		Long accType = loginAccNoAndType.getAccType();
		
		// 是否需要首次注册
		LoginNo currRegLogNo = null;
		{
			List<LoginNo> existLogResList = null;
			if(
					(LoginDict.AccountType.WEI_XIN.compareTo(accType)==0||LoginDict.AccountType.WEI_XIN_LIGHT_APP.compareTo(accType)==0)
					&& !StringUtils.isEmpty(unionId)//传入了unionId则表示是尝试新的联合登录的方式
					){
				//syl-add 2015-5-5 14:25:15 录入union数据,不存在则录入
				//查询当前账号对应的unionId是否有值，若无则更新对应的unionId,对于有unionId但是没有账号记录的情况，新增一条账号绑定记录
				commDataUpgradeService.updateWeiXinUnionIdIfNotExist(accType, accountNo, unionId);
//				LoginNo loginNoTmp = commDataUpgradeService.qryExistLoginNoForWeiXin(accType, accountNo,unionId);//syl-upd 2015-5-11 20:37:46
				//结合unionId获取登录账号,accType,accNo对应的记录必须存在
				LoginNo loginNoTmp = commDataUpgradeService.qryExistLoginNoForWeiXin(accType, accountNo, unionId, subChannelId, deviceId,version);
				existLogResList = new ArrayList<LoginNo>();
				if(loginNoTmp!=null){
					existLogResList.add(loginNoTmp);
				}
			}else{
				LoginNo loginNoQry = new LoginNo();
				loginNoQry.setType(accType);
				loginNoQry.setNo(accountNo);
				existLogResList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry), false);
			}
			
			if (existLogResList!=null&&existLogResList.size() > 0) {
				currRegLogNo = existLogResList.get(0);
			} else {
				currRegLogNo = registSimple(accountNo, accType, subChannelId,version, null, null, null, deviceId, mobile, realName,nickName,
						sex, birthday, userImgprofileName, null,unionId);
			}
			
			//syl-add 2015-4-22 15:04:41 数据合并 
			if(!StringUtils.isEmpty(unionId)){
				commDataUpgradeService.mergeDataWeiXin(currRegLogNo.gettUserFId(),unionId);
			}
			
		}
		// 更新用户图片
		if (userImage != null && userImage.length > 0) {
			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
			try {
				String imgprofilePathServerPath = new StringBuffer().append(userImageParamEntity.getBasePath())
						.append(userImgprofileName).toString();
				fileServerService.uploadFile(imgprofilePathServerPath, userImage);
			} catch (IOException e) {
				throw new BusinessRuntimeException("login.regist3rd.uploadUserImageFile.exception", e);
			}
			//增加生成小图的任务处理 syl-2014-9-4 11:40:43
			FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.User,fileServerService.getFileServierUploadBasePath(),userImageParamEntity.getBasePath(), userImgprofileName, userImage));
			new Thread(task).start();
		}
		// //进行登录，获取登录信息
		// LoginNoEntity loginRes= loginDao.selectLoginNoEntityByAccount(accountNo,
		// accType);

		// loginRes.setIsFirstRegist(isFirstRegist);//设置是否首次注册的标识
		// //加载首页需要的其他信息
		// appendIndexPageInfoAfterLogin(loginRes);
		
		{
			BigInteger userId = currRegLogNo.gettUserFId();
			if(!StringUtils.isEmpty(nickName)){//昵称积分
				pointService.checkAndAddPoint(userId, PointConstant.PointSourceType.FirstSetNickName);
			}
			if(userImage!=null&&userImage.length>0){//用户图像积分
				pointService.checkAndAddPoint(userId, PointConstant.PointSourceType.FirstSetUserImage);
			}
		}
		// 返回
		return currRegLogNo;
	}

	@Override
	public LoginNoEntity login(SimpleLoginInfo simpleLoginInfo, Long subChannelId,String version, String deviceId) {
		if (simpleLoginInfo == null) {
			throw new ValidateRuntimeException("loginService.login.simpleLoginInfo.isnull");
		}
		LoginNoEntity logRes = null;
		String accNo = simpleLoginInfo.getNumber();// 账号
		Long loginType = simpleLoginInfo.getLoginType();// 登录方式
		Long accountType = simpleLoginInfo.getAccountType();// 账号类别
//		String srcPassword = simpleLoginInfo.getPassword();// 原始密码
//		String encodePwd = PasswordEcoderUtil.encodePassword(simpleLoginInfo.getPassword(), accNo, accountType);// 加密密码 syl-del-2015-4-2 19:05:05
		String encodePwd = simpleLoginInfo.getPassword();// 加密密码 syl-add-2015-4-2 19:05:11
		if (accNo == null) {
			throw new ValidateRuntimeException("loginService.login.accountNo.isNull");
		}
		if (loginType == null && accountType == null) {
			throw new ValidateRuntimeException("loginService.login.accountTypeAndloginType.bothNull");
		}

		if (null == loginType) {// 登录方式为空，使用账号类型直接登录
//			LoginNo logResAA = checkAndRegist(accNo, accountType, subChannelId, deviceId);
//			if (logResAA != null) {
//				accNo = logResAA.getNo();
//				encodePwd = logResAA.getPassword();
//				accountType = logResAA.getType();
//			}
			logRes = commonLogin(accNo, encodePwd, accountType);
		} else if (LoginDict.LoginType.MOBILE_VALICODE.compareTo(loginType) == 0) {// 手机短信验证码登录
			String verifyCode = encodePwd;// 校验验证码是否正确,此时的验证码取srcPassword
			valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.LOGIN,accNo, verifyCode);
			valicodeManager.clearValicode(LoginDict.ValiCodeGetType.LOGIN);//清除验证码
			String unionId = null;
			LoginNo logResAA = checkAndRegist(accNo, LoginDict.AccountType.MOBILE, subChannelId,version, deviceId,unionId);
			if (logResAA != null) {
				accNo = logResAA.getNo();
				encodePwd = logResAA.getPassword();
				accountType = logResAA.getType();
			}
			logRes = commonLogin(accNo, LoginDict.AccountType.MOBILE);// 根据账号获得用户信息,对应账号类型为手机
		} else if (LoginDict.LoginType.MOBILE.compareTo(loginType) == 0) {
//			LoginNo logResAA = checkAndRegist(accNo, LoginDict.AccountType.MOBILE, subChannelId, deviceId);
//			if (logResAA != null) {
//				accNo = logResAA.getNo();
//				encodePwd = logResAA.getPassword();
//				accountType = logResAA.getType();
//			}
			logRes = commonLogin(accNo, encodePwd, LoginDict.AccountType.MOBILE);
		} else if (LoginDict.LoginType.MAIL.compareTo(loginType) == 0) {
//			LoginNo logResAA = checkAndRegist(accNo, LoginDict.AccountType.MAIL, subChannelId, deviceId);
//			if (logResAA != null) {
//				accNo = logResAA.getNo();
//				encodePwd = logResAA.getPassword();
//				accountType = logResAA.getType();
//			}
			logRes = commonLogin(accNo, encodePwd, LoginDict.AccountType.MAIL);
		} else if (LoginDict.LoginType.HUA_ID.compareTo(loginType) == 0) {
//			LoginNo logResAA = checkAndRegist(accNo, LoginDict.AccountType.HUA_ID, subChannelId, deviceId);
//			if (logResAA != null) {
//				accNo = logResAA.getNo();
//				encodePwd = logResAA.getPassword();
//				accountType = logResAA.getType();
//			}
			logRes = commonLogin(accNo, encodePwd, LoginDict.AccountType.HUA_ID);
		} else {
			throw new BusinessRuntimeException("login.login.unknownLoginType.failed", new Object[] { loginType });// 未知登录方式
		}
		if (logRes == null) {
			throw new BusinessRuntimeException("login.login.selectLoginNoEntity.failed");
		}
		// 加载首页需要的其他信息
		appendIndexPageInfoAfterLogin(logRes);
		
		{// 录入购物车信息
			UserIdType userIdType = commonDeviceService.getUserIdType();
			{//检查当前登录的用户是否有购物车,若无则创建
				commonEbuyService.checkAndCreateEbuyBuyCar(logRes.gettUserFId(), LoginDict.UserRegistOrTmp.REGIST_USER);
			}
			if(userIdType.getUserType().compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)!=0){
				commonEbuyService.collectEbuyBuyCar(userIdType.getUserId(), userIdType.getUserType(), logRes.gettUserFId(),
						LoginDict.UserRegistOrTmp.REGIST_USER);
			} 
		}
		
		return logRes;
	}

	private LoginNo checkAndRegist(String account, Long accountType, Long subChannelId,String version, String deviceId,String unionId) {
		boolean isRegist = checkIsRegist(account, accountType);
		LoginNo logResAA = null;
		if (!isRegist) {
			logResAA = registSimple(account, accountType, subChannelId,version, null, null, null, deviceId, null,null, null, null, null,
					null,null,unionId);
		}
		return logResAA;
	}

	@Override
	public LoginNo setNewPassword(String account, String password, Long type) {
		String encodePassword = EncodeImpl.doEncodePassword(password);
		// 获取用户Id
		BigInteger tUserFId = getUserIdByAccount(account, type);
		// 根据用户Id设置新密码
		boolean resBool = loginDao.setNewPassword(tUserFId, encodePassword);
		if (resBool) {
			// 查询对应的账号信息
			LoginNo loginNoQry = new LoginNo();
			loginNoQry.setNo(account);
			loginNoQry.setType(type);
			List<LoginNo> lnList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry), false);
			if (lnList != null && lnList.size() == 1) {
				updateRemoteUpdPwdByUserId(tUserFId, encodePassword);
				return lnList.get(0);
			} else {
				throw new BusinessRuntimeException("loginService.setNewPassword.mutilLogno.error");
			}
		} else {
			throw new BusinessRuntimeException("loginService.setNewPassword.failed");
		}
	}

	@Override
	public BigInteger getUserIdByAccount(String account, Long type) {
		BigInteger tUserFId = loginDao.selectUserIdByAccount(account, type);
		return tUserFId;
	}

	@Override
	public boolean changePassword(BigInteger id, String newPassword) {
		String encodePassword = EncodeImpl.doEncodePassword(newPassword);
		// 根据用户Id设置新密码
		LoginNoEntity updEntity = new LoginNoEntity();
		updEntity.settUserFId(id);
		updEntity.setPassword(encodePassword);
		boolean boolRes = loginDao.setNewPassword(id, encodePassword);
		if (boolRes) {
			updateRemoteUpdPwdByUserId(id, encodePassword);
		}
		return boolRes;
	}

	@Override
	public boolean validatePwdByUserId(BigInteger id, String oldPassword) {
		int count = loginDao.selectCountByIdPwd(id, oldPassword);
		return count > 0;
	}

	@Override
	public LoginNoEntity getLoginNoEntityByAccount(String accountNo, Long type) {
		LoginNoEntity logRes = loginDao.selectLoginNoEntityByAccountSupportBind(accountNo, type);
		return logRes;
	}

	// /**
	// * 通过账号类型，发送对应message
	// * @param type 账号类型
	// * @param account 账号
	// * @param message 信息内容
	// * @return
	// */
	// private boolean sendInfoByType(Long type,String account,String message){
	// if(LoginDict.AccountType.MOBILE.compareTo(type)==0){//手机方式
	// return sendMsg(account,message);
	// }else if(LoginDict.AccountType.MAIL.compareTo(type)==0){//邮箱方式
	// return sendMail(account, message);
	// }else{
	// //通过其它方式
	// return false;
	// }
	// }


	// public static void main(String[] args) throws OpensnsException {
	// String method = "post";
	// String scriptName = QQApplyEntity.API_NAME_GET_USERINFO;
	// String secret = "O1RMqUGx9IkHXSyQ&";
	// HashMap<String, String> params = new HashMap<String, String>();
	// params.put("openid", "B398B92551E3A8EEEC9519121CDB02FE");
	// params.put("openkey", "96348E19D88988ECAEBA641FD1256BC3");
	// params.put("format", "json");
	// params.put("pf", "qzone");
	// params.put("appid", "1101365148");
	// // 计算签名
	// String sig = SnsSigCheck.makeSig(method, scriptName, params, secret);
	// System.out.println(sig);
	// }
	
	
//	public static void main(String[] args) {
//		String accessToken = "zXqmgcQowfDR4bawxz2wRZjFRve_Ubr9IDxXE1ixzBvSIWVwf0YVgn1NBtflAQoH0-sSc8bCB-um3FOB6A3Zjxo6aAY6VRnpkTb4c_KZ5pk";
//		String openId = "o33fQt28_THzvbBIHgG6N_w5uaTI";
//		System.out.println(new LoginService().validateAccessTokenWeiXinLightApp(accessToken, openId));;
//	}
	
//	@Override
	//syl-del 2015-4-23 10:33:05 
//	private boolean validateAccessTokenWeiXinLightApp(String accessToken, String openId) {
//		String validateUrl = weiXinLoginConfigParamParser.parseParamValue();
//		String postData = validateUrl+"?access_token=" + accessToken + "&openid=" + openId;
//		HttpUriRequest httpUriRequest = new HttpGet(postData);
//		CookieStore cookieStore = new BasicCookieStore();
//		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//		try {
//			CloseableHttpResponse response = client.execute(httpUriRequest);
//			{// response.getEntity()
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//						String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
//						Map<String, Object> resMap = JSON.parseObject(res);
//						Object errcodeObj = resMap.get("errcode");
//						Object errmsgObj = resMap.get("errmsg");
//						Object openidObj = resMap.get("openid");
//						if(errcodeObj!=null&&!errcodeObj.toString().equals("0")//其它错误码
//								||(openidObj==null||!openidObj.toString().equals(openId))){//或者获取到的openid不一致
//							logger.debug("ValidateAccessTokenWeiXin failed, response data is " + res );
//							throw new BusinessRuntimeException("LoginService.validateAccessTokenWeiXinLightApp.validateFailed", new Object[] {
//									errcodeObj, errmsgObj });
//						}else{
//							return true;
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.debug("ValidateAccessTokenWeiXinLightApp cause Exception,errMsg is " + e.getMessage(), e);
//			throw new BusinessRuntimeException("LoginService.validateAccessTokenWeiXinLightApp.exception", e);
//		}
//		return false;
//	}
	
	// public static void main(String[] args) throws ApiException {
	// // String url = "https://oauth.tbsandbox.com";
	// // String url = "http://gw.api.tbsandbox.com";
	// // String url = "https://oauth.taobao.com/";
	// String url = "http://gw.api.tbsandbox.com/router/rest";
	// String appkey = "1021782613";
	// String secret = "sandbox0e996eba029d2209ae49c6e74";
	// String sessionKey =
	// "6302200692d778671844fefdcc9b309805558bd5364a5a32055852574";
	// {
	// TaobaoClient client=new DefaultTaobaoClient(url, appkey,secret);
	// UserBuyerGetRequest req=new UserBuyerGetRequest();
	// req.setFields("user_id,uid,nick,sex");
	// UserBuyerGetResponse response = client.execute(req , sessionKey);
	// com.taobao.api.domain.User taobaoUser = response.getUser();
	// System.out.println(JSON.toJSON(taobaoUser));
	// }
	// {/*
	// TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
	// UserGetRequest req=new UserGetRequest();
	// req.setFields("user_id,nick,seller_credit");
	// req.setNick("hz0799");
	// UserGetResponse response = client.execute(req , sessionKey);
	// */}
	// {/*
	// TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
	// UserGetRequest req=new UserGetRequest();
	// req.setFields("user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind");
	// // req.setNick("user_id");
	// UserGetResponse response = client.execute(req , sessionKey);
	// */}
	// }
	
//	@Override
//	public PrizeRecordSimpleEntity getLeastDiscountAfterLogin(BigInteger userId) {
//		// 获取数据库当前时间
//		String nowTime = dualDao.getNowTime();
//		String monthFirstDay = DateUtil.getMonthFirstDay(nowTime);
//		String monthLastDay = DateUtil.getMonthLastDay(nowTime);
//		// 查询用户门牌关系
//		UserHasTRoom userHasTRoomQry = new UserHasTRoom();
//		userHasTRoomQry.settUserFId(userId);
//		userHasTRoomQry.settRoomFId(roomId);
//		List<UserHasTRoom> tmpList = userHasTRoomBaseDao.selectUserHasTRoomByCondition(MapConverter.toMap(userHasTRoomQry),
//				false);
//		if (tmpList.size() <= 0) {
//			return null;
//			// throw new
//			// BusinessRuntimeException("loginService.getLeastDiscountAfterLogin.defaultUserHasTRoom.notExist.error");
//		}
//		return prizeDao.selectLeastDiscountIsLognWithDefault(tmpList.get(0).getId(), monthFirstDay, monthLastDay);
//		
//	}

	// @Override
	// public void freshUserContext() {
	// //进行登录，获取登录信息
	// LoginNoEntity loginRes=
	// loginDao.selectLoginNoEntityByAccount(UserContext.getCurrUser().getNo(),
	// UserContext.getCurrUser().getType());
	// UserContext.setCurrUser(loginRes);
	// }
	//
	// @Override
	// public void validateLoginType(SimpleLoginInfo simpleLoginInfo){
	// if(simpleLoginInfo==null){
	// throw new
	// ValidateRuntimeException("login.doLogin.loginInfo.parseResult.isnull.error");
	// }
	// Long loginType = simpleLoginInfo.getLoginType();
	// //数据验证
	// if(loginType==null||DictConstants.LoginType.MOBILE.compareTo(loginType)==0||DictConstants.LoginType.MAIL.compareTo(loginType)==0
	// ||DictConstants.LoginType.HUA_ID.compareTo(loginType)==0||DictConstants.LoginType.THIRD_TYPES.compareTo(loginType)==0){
	//
	// }else
	// if(DictConstants.LoginType.MOBILE_VALICODE.compareTo(loginType)==0){//手机短信验证码登录
	// //校验验证码是否正确
	// String verifyCode = encodePwd;//验证码
	// valicodeManager.checkWithSession(DictConstants.ValiCodeGetType.LOGIN,
	// verifyCode);
	// //
	// if(!valicodeManager.checkWithSession(DictConstants.ValiCodeGetType.LOGIN,
	// verifyCode)){
	// // throw new ValidateRuntimeException("login.doLogin.checkValicode.error");
	// // }
	// }
	// else{//登录方式不正确
	// throw new ValidateRuntimeException("login.doLogin.valiLoginType.error");
	// }
	// }

	/**
	 * 加载登录后，首页需要的信息
	 * 
	 * @param logRes
	 */
	private void appendIndexPageInfoAfterLogin(LoginNoEntity logRes) {
		BigInteger userId = logRes.getUserEntity().getId();
//		ValidateRoomTread validateRoomThread = new ValidateRoomTread(commonRoomService, userId, logRes.getUserEntity().getMobile());
//		validateRoomThread.start();
//		try {
//			validateRoomThread.join(3000L);
//		} catch (InterruptedException e) {
//			logger.info(e.getMessage(), e);
//		}
		
//		{// 最低折扣
//			// 获取数据库当前时间
//			String nowTime = dualDao.getNowTime();
//			BusinessMonthYearWithGB monthYearWithGB = new BusinessMonthYearWithGB(nowTime, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.time);
//			PrizeRecordSimpleEntity leastDiscount = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
//			PrizeRecordEntityWithBusinessMonthYear prizeRecordEntityExtend = commonPrizeService.getLeastDiscountFirstByMonthAndUsedNowTime(userId);
//			PrizeRecordEntity leastDiscount = prizeRecordEntityExtend.getPrizeRecordEntity();
//			logRes.setLeastDiscount(leastDiscount);
//			if(leastDiscount!=null){
//				Long discountConvertMoney = discount2hbRule.getMoneyByDiscount(userId, leastDiscount.getDiscount());
//				logRes.setDiscountConvertMoney(discountConvertMoney);
//			}
//		}
//		{// 剩余抽奖次数
////			int defaultMaxCount = Integer.parseInt(sysParamManager.getSysParaValue(SysParamKey.MAX_PRIZE_COUNT));
//			int defaultMaxCount = prizeRuleManager.getMaxPrizeCount();
//			int doneCount = prizeDao.selectPrizeDoneCount(logRes.getUserEntity().getId(),LoginDict.UserRegistOrTmp.REGIST_USER);
//			int leftCount = (defaultMaxCount - doneCount) <= 0 ? 0 : (defaultMaxCount - doneCount);
//			logRes.setLeftPrizeCount(leftCount);
//		}
		{// 粮票信息
			Long hbBalance = commonRedenvelopeService.getTotalHbBalance(userId);
			logRes.setHbBalance(hbBalance);
		}
		{//积分信息
			PointData pointData = commonPointService.getPointDataByUserId(userId);
			logRes.setPointData(pointData);
		}
		
		
	}
	
	static class ValidateRoomTread extends Thread {
		
		private ICommonRoomService commonRoomService;
		
		private BigInteger userId;
		
		private String mobile;
		
//		private RedisTemplate<String, Integer> redisTemplate;
		
		public ValidateRoomTread(ICommonRoomService commonRoomService, BigInteger userId, String mobile) {
			this.commonRoomService = commonRoomService;
			this.userId = userId;
			this.mobile = mobile;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
//			redisTemplate = (RedisTemplate<String, Integer>) CnfantasiaCommbusiUtil.getBeanManager("redisTemplate");
//			if(redisTemplate.opsForValue().getAndSet(userId + mobile, 1) == null) {
				commonRoomService.autoValidateRoomForBindPhone(userId, mobile);
//			}
//			redisTemplate.expire(userId + mobile, 13, TimeUnit.SECONDS);
		}
	}

	@Override
	public PrizeRecordTmp getLastRecord(BigInteger userTmpId) {
		return loginDao.selectLastRecord(userTmpId);
	}

	/**
	 * 常规登录
	 */
	private LoginNoEntity commonLogin(String account, Long accType) {
		LoginNoEntity logRes = loginDao.selectLoginNoEntityByAccountSupportBind(account, accType);
		RemoteUser remoteUser = remoteLogin(logRes.gettUserFId());
		logRes.getUserEntity().setRemoteUser(remoteUser);
		return logRes;
	}
	
	private LoginNoEntity commonLogin(String account, String password, Long accType) {
		// 判断是否本地有
		LoginNoEntity logRes = loginDao.selectLoginNoEntitySupportBind(account, password, accType);
		if(logRes!=null){
			// 远程登录
			RemoteUser remoteUser = remoteLogin(logRes.gettUserFId());
			logRes.getUserEntity().setRemoteUser(remoteUser);
		}
		return logRes;
	}
	
	
	
	private void remoteRegist(String no,String password,String huaId,BigInteger userId) {
	//		//远程注册
	//		Integer remoteUserId = accountDao.userRegister(no,password,
	//				EmailGenerator.getTmpEmail(huaId));
	//		RemoteUser remoteUser = new RemoteUser(remoteUserId.longValue(), userId, no,
	//				password, EmailGenerator.getTmpEmail(huaId));
	//		remoteUserBaseDao.insertRemoteUser(remoteUser);
	}
	
	private RemoteUser remoteLogin(BigInteger userId) {
		return null;
//		// 根据用户Id查询远程账号
//		RemoteUser remoteUserQry = new RemoteUser();
//		remoteUserQry.settUserFId(userId);
//		List<RemoteUser> userList = remoteUserBaseDao.selectRemoteUserByCondition(MapConverter.toMap(remoteUserQry), false);
//		if (userList.size() == 1) {
//			// RemoteUser remoteUserEntity =
//			// accountDao.userLogin(userList.get(0).getUserName(),
//			// userList.get(0).getPassword());
//			// remoteUserEntity.settUserFId(userId);
//			// return remoteUserEntity;
//			RemoteUser remoteUserEntity = remoteRegistOrLogin(userId, userList.get(0).getUserName(), userList.get(0)
//					.getPassword());
//			if (remoteUserEntity == null) {
//				remoteUserEntity = userList.get(0);
//			}
//			return remoteUserEntity;
//		} else {// TODO 远程用户未注册时,自动注册
//			LoginNo loginNo = new LoginNo();
//			loginNo.settUserFId(userId);
//			loginNo.setType(LoginDict.AccountType.HUA_ID);
//			List<LoginNo> loginNoList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNo), false);
//			if (loginNoList != null && loginNoList.size() == 1) {
//				// Integer remoteUserId =
//				// accountDao.userRegister(loginNoList.get(0).getNo(),
//				// loginNoList.get(0).getPassword(),
//				// EmailGenerator.getTmpEmail(loginNoList.get(0).getNo()));
//				// RemoteUser remoteUserAdd = new RemoteUser(remoteUserId.longValue(),
//				// userId, loginNoList.get(0).getNo(), loginNoList.get(0).getPassword(),
//				// EmailGenerator.getTmpEmail(loginNoList.get(0).getNo()));
//				RemoteUser remoteUserAdd = remoteRegistOrLogin(userId, loginNoList.get(0).getNo(), loginNoList.get(0)
//						.getPassword());
//				if (remoteUserAdd != null) {
//					int res = remoteUserBaseDao.insertRemoteUser(remoteUserAdd);
//					if (res <= 0) {
//						throw new BusinessRuntimeException("loginService.remoteLogin.insertRemoteUser.failed");
//					}
//				}
//				return remoteUserAdd;
//			}
//		}
//		throw new BusinessRuntimeException("LoginService.remoteLogin.failed");
	}

	/**
	 * 未注册则注册，已注册则登录
	 */
//	private RemoteUser remoteRegistOrLogin(BigInteger userId, String remoteUserName, String password) {
//		RemoteUser remoteUserEntity = null;
//		boolean isRemoteRegist = accountDao.checkIsRegist(remoteUserName);
//		if (isRemoteRegist) {
//			try {
//				FutureTask<Boolean> task = new FutureTask<Boolean>(
//						new UpdpasswordRunnable(accountDao, remoteUserName, password));// 将本机密码同步到远程
//				new Thread(task).start();
//				RemoteUser remoteUserLoginEntity = accountDao.userLogin(remoteUserName, password);
//				remoteUserLoginEntity.settUserFId(userId);
//				remoteUserEntity = remoteUserLoginEntity;
//			} catch (Exception e) {
//				logger.debug("remoteRegistOrLogin.userLogin.failed", e);
//			}
//		} else {
//			try {
//				Integer remoteUserId = accountDao.userRegister(remoteUserName, password,
//						EmailGenerator.getTmpEmail(remoteUserName));
//				RemoteUser remoteUserAdd = new RemoteUser(remoteUserId.longValue(), userId, remoteUserName, password,
//						EmailGenerator.getTmpEmail(remoteUserName));
//				remoteUserEntity = remoteUserAdd;
//			} catch (Exception e) {
//				logger.debug("remoteRegistOrLogin.userRegister.failed", e);
//			}
//		}
//		return remoteUserEntity;
//	}

	private void updateRemoteUpdPwdByUserId(BigInteger userId, String newPwd) {
//		RemoteUser remoteUserQry = new RemoteUser();
//		remoteUserQry.settUserFId(userId);
//		List<RemoteUser> userList = remoteUserBaseDao.selectRemoteUserByCondition(MapConverter.toMap(remoteUserQry), false);
//		if (userList != null && userList.size() == 1) {
//			remoteUserQry.setPassword(newPwd);
//			remoteUserQry.setUid(userList.get(0).getUid());
//			int res = remoteUserBaseDao.updateRemoteUser(remoteUserQry);
//			if (res <= 0) {
//				throw new BusinessRuntimeException("loginService.remoteLogin.updateRemoteUpdPwdByUserId.failed");
//			}
//			FutureTask<Boolean> task = new FutureTask<Boolean>(new UpdpasswordRunnable(accountDao, userList.get(0)
//					.getUserName(), newPwd));
//			new Thread(task).start();
//			// boolean resRemote = accountDao.updPwd(userList.get(0).getUserName(),
//			// newPwd);
//			// if(!resRemote){
//			// throw new
//			// BusinessRuntimeException("loginService.remoteLogin.updateRemoteUpdPwdByUserId.failed");
//			// }
//			return;
//		}
	}

	@Override
	public synchronized boolean checkIsRegist(String account, Long accType) {
		LoginNo loginNo = new LoginNo();
		loginNo.setNo(account);
		loginNo.setType(accType);
		int count = loginNoBaseDao.selectLoginNoCount(MapConverter.toMap(loginNo), false);
		return count > 0;
	}

	@Override
	public LoginNo registClub(String account, Integer regType, Long subChannelId,String version, String deviceId, String password,String nickName) {
		String mobile = null;
		// 通过注册方式判定账号类别
		Long accType = null;
		if (LoginDict.RegistComm_Type.MAIL.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.MAIL;
		} else if (LoginDict.RegistComm_Type.MOBILE.compareTo(regType) == 0) {
			accType = LoginDict.AccountType.MOBILE;
			mobile = account;// 录入手机号
		} else {
			throw new ValidateRuntimeException("loginService.regist.unsupportRegType.error");
		}
		// 校验账号是否已被注册
		LoginNo loginNoQry = new LoginNo();
		loginNoQry.setType(accType);
		loginNoQry.setNo(account);
		int existCount = loginNoBaseDao.selectLoginNoCount(MapConverter.toMap(loginNoQry), false);
		if (existCount > 0) {
			throw new ValidateRuntimeException("login.regist.loginNoCheck.failed");
		}
		// 执行注册
		String unionId = null;
		LoginNo loginNoRegistTmp = registSimple(account, accType, subChannelId,version, null, null, null, deviceId, mobile, null, nickName, null, null, null,password,unionId);
		// //进行登录，获取登录信息
		// LoginNoEntity loginResult=
		// loginDao.selectLoginNoEntityByAccount(loginNoRegistTmp.getNo(),
		// loginNoRegistTmp.getType());
		// 返回
		return loginNoRegistTmp;
	}

//	@Override
//	public void insertLoginLogBatchForQueueAutoId(List<LoginLog> loginLogList) {
//		try{
//			if(null!=loginLogList && loginLogList.size()>0){
//				CnfantasiaCommbusiUtil.newStandardList(loginLogList, SEQConstants.t_login_log);
//				this.loginLogBaseDao.insertLoginLogBatch(loginLogList);
//			}
//		}catch(Exception e){
//			logger.error(e);
//			e.printStackTrace();
//		}
//		
//	}
	
}
