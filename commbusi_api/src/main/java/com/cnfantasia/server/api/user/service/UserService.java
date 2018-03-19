package com.cnfantasia.server.api.user.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.accountManage.dao.IAccountManageDao;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.entity.AddAndDelIdsEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommDataUpgradeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.commonBusiness.util.IdsAddDelCheckUtil;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.inviteReward.service.IInviteRewardService;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.api.point.service.IPointService;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.user.dao.IUserDao;
import com.cnfantasia.server.api.user.entity.HobbyEntity;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.user.entity.UserImageParamEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.bindPhoneLog.constant.BindPhoneConstant;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.bindPhoneLog.dao.IBindPhoneLogBaseDao;
import com.cnfantasia.server.domainbase.bindPhoneLog.entity.BindPhoneLog;
import com.cnfantasia.server.domainbase.commUserSession.dao.CommUserSessionBaseDao;
import com.cnfantasia.server.domainbase.commUserSession.entity.CommUserSession;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;
import com.cnfantasia.server.domainbase.loginNo.dao.ILoginNoBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasTHobby.dao.IUserHasTHobbyBaseDao;
import com.cnfantasia.server.domainbase.userHasTHobby.entity.UserHasTHobby;


/**
 * 描述:() 具体业务Service层实现
 *
 * @author null
 * @version 1.00
 */
@Service
public class UserService implements IUserService {
    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    private IFileServerService fileServerService;

    public void setFileServerService(IFileServerService fileServerService) {
        this.fileServerService = fileServerService;
    }

    // private ILoginNoBaseDao loginNoBaseDao;
    // public void setLoginNoBaseDao(ILoginNoBaseDao loginNoBaseDao) {
    // this.loginNoBaseDao = loginNoBaseDao;
    // }

    private ISysParamParser userImageParamParser;

    public void setUserImageParamParser(ISysParamParser userImageParamParser) {
        this.userImageParamParser = userImageParamParser;
    }

    private IUuidManager uuidManager;

    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    private ISmallPicUploadService smallPicUploadService;

    public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
        this.smallPicUploadService = smallPicUploadService;
    }

    // private ICommonLoginService commonLoginService;
    // public void setCommonLoginService(ICommonLoginService commonLoginService)
    // {
    // this.commonLoginService = commonLoginService;
    // }

    private IUserHasTHobbyBaseDao userHasTHobbyBaseDao;

    public void setUserHasTHobbyBaseDao(IUserHasTHobbyBaseDao userHasTHobbyBaseDao) {
        this.userHasTHobbyBaseDao = userHasTHobbyBaseDao;
    }

    private IPointService pointService;

    public void setPointService(IPointService pointService) {
        this.pointService = pointService;
    }

    private ICommDataUpgradeService commDataUpgradeService;

    public void setCommDataUpgradeService(ICommDataUpgradeService commDataUpgradeService) {
        this.commDataUpgradeService = commDataUpgradeService;
    }

    private IInviteRewardService inviteRewardService;

    public IInviteRewardService getInviteRewardService() {
        return inviteRewardService;
    }

    public void setInviteRewardService(IInviteRewardService inviteRewardService) {
        this.inviteRewardService = inviteRewardService;
    }

    @Override
    public UserEntity getUserById(BigInteger userId) {
        return userDao.selectUserById(userId);
    }

    @Override
    public UserEntity updPersonInfo(BigInteger userId, String mobile, String realName, String sex,
                                    String birthday, byte[] userImage, String userImgprofileName, String nickName,
                                    String signature, List<BigInteger> hobbyIds, String inviteNo, String role) {
        // 组装用户个人信息
        UserEntity updUser = new UserEntity();
        updUser.setId(userId);
        updUser.setMobile(mobile);
        updUser.setRealName(realName);
        updUser.setSex(sex);
        updUser.setBirthday(birthday);
        updUser.setNickName(nickName);
        updUser.setSignature(signature);
        updUser.setInviteNo(inviteNo);// syl-add 邀请码
        try {
            if (StringUtils.isEmpty(role)) {
                updUser.setFamilyRole(null);
            } else {
                updUser.setFamilyRole(Integer.parseInt(role));
            }
        } catch (NumberFormatException e1) {
            e1.printStackTrace();
        }
        if (!StringUtils.isEmpty(userImgprofileName)) {
            updUser.setImgprofile(userImgprofileName);
        }
        // 更新用户个人信息
        int res = userDao.updateUserSignatureNull(updUser);
        if (res <= 0) {
            throw new BusinessRuntimeException("user.updPersonInfo.failed");
        }
        // 更新用户图片
        if (userImage != null && userImage.length > 0) {
            UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
            try {
                String imgprofilePathServerPath = new StringBuffer().append(userImageParamEntity.getBasePath()).append(userImgprofileName).toString();
                fileServerService.uploadFile(imgprofilePathServerPath, userImage);
            } catch (IOException e) {
                throw new BusinessRuntimeException("user.updPersonInfo.uploadUserImageFile.exception");
            }
            // 增加生成小图的任务处理 syl-2014-9-4 11:40:43
            FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService, BusinessModelType.User,
                    fileServerService.getFileServierUploadBasePath(), userImageParamEntity.getBasePath(), userImgprofileName, userImage));
            new Thread(task).start();
        }
        // 更新用户兴趣列表
        if (hobbyIds != null) {
            submitAllHobbyOnly(hobbyIds, userId);
        }

        {
            if (!StringUtils.isEmpty(nickName)) {// 昵称积分
                pointService.checkAndAddPoint(userId, PointConstant.PointSourceType.FirstSetNickName);
            }
            if (userImage != null && userImage.length > 0) {// 用户图像积分
                pointService.checkAndAddPoint(userId, PointConstant.PointSourceType.FirstSetUserImage);
            }
        }
        // 返回用户最新信息
        UserEntity userEntity = userDao.selectUserById(userId);
        // 如果是补充邀请码则补发邀请奖励-huangzj2015-06-18
        if (!StringUtils.isEmpty(inviteNo)) {
            inviteRewardService.excuteInviteRewardForCompleteInviteNO(userEntity, inviteNo);
        }
        return userEntity;
    }

/*    @Override
    public void bindPhone(HttpServletRequest request, BigInteger userId, String newPhone) {
        commDataUpgradeService.executeBindActionForPhone(userId, newPhone);
    }*/
    
    
    @Resource ICommonRoomService commonRoomService;
    @Resource IUserBaseDao userBaseDao;
    @Resource ICommonLoginService commonLoginService;
    @Resource IAccountManageDao accountManageDao;
    @Resource ILoginNoBaseDao loginNoBaseDao;
    @Resource IBindPhoneLogBaseDao bindPhoneLogBaseDao;
    
    @Override //modified by Wenfq 2016-04-11
    public void bindPhone(HttpServletRequest request, final BigInteger userId, String newPhone) {
		//Room room = commonRoomService.getDefaultRoomByUserId(userId);
		BigInteger userIdAfterChange = userId;
		int hasDataCount = userDao.selectHasBusinessDataCountByUserId(userId);
		if (hasDataCount == 0) {//无业务时
			User userQry = new User();
			userQry.setSys0DelState(0);
			userQry.setMobile(newPhone);
			List<User> userList = userBaseDao.selectUserByCondition(MapConverter.convertBean(userQry), false);
			if (userList.size() == 0) {
				//0 手机账号不存在，新建手机账号，并将手机绑定到当前账号
				createUserForNewPhoneAndBindToCurrentUser(userId, newPhone);
				{// 刷新登录参数
					UserContext.getCurrLoginNo().getUserEntity().setMobile(newPhone);
					UserContext.getCurrLoginNo().getUserEntity().setPhoneBindState(BindPhoneConstant.User_Bind_State_Success);
				}
			} else if (userList.size() == 1) {
				LoginNo phoneLoginNo = accountManageDao.selectLoginNoDetail(newPhone, LoginDict.LoginType.MOBILE);

				if (judgePhoneHasBindedQQorWechat(phoneLoginNo, UserContext.getCurrLoginNo().getType())){
					updateUserBindState(userId,	null, BindPhoneConstant.User_Bind_State_Fail);
					addBindPhoneLog(userId, userList.get(0).getId(), newPhone, BindPhoneConstant.Bind_Phone_log_State_Fail);
					//刷新登录参数
					UserContext.getCurrLoginNo().getUserEntity().setPhoneBindState(BindPhoneConstant.User_Bind_State_Fail);
					return;
				}

				int phoneUserHasDataCount = userDao.selectHasBusinessDataCountByUserId(userList.get(0).getId());
				if (phoneUserHasDataCount == 0) {//无业务数据
					//1 将手机绑定到当前账号
					LoginNo loginNoUpd = new LoginNo();
					loginNoUpd.setId(phoneLoginNo.getId());
					loginNoUpd.settUserFId(userId);
					loginNoUpd.setSys0UpdUser(userId);
					loginNoBaseDao.updateLoginNo(loginNoUpd);
					updateUserBindState(userId, newPhone, BindPhoneConstant.User_Bind_State_Success);
					addBindPhoneLog(userId, userList.get(0).getId(), newPhone, BindPhoneConstant.Bind_Phone_Log_State_Success);
					offlineTheSlaveUser(userList.get(0).getId());
					{// 重新登录
						autoLoginAfterBindPhone(request, UserContext.getCurrLoginNo());
					}
					userDao.update_user_mobile_to_null(userList.get(0).getId());
				}else{
					//2 将当前账号绑定到手机账号
					bindOtherLoginNo2PhoneAccountTogether(userId, userList.get(0), phoneLoginNo);
					LoginNo loginNoUpd = new LoginNo();
					loginNoUpd.setId(UserContext.getCurrLoginNoId());
					loginNoUpd.settUserFId(userList.get(0).getId());
					//Password QQ(微信)不用设置的密码的
					loginNoUpd.setSys0UpdUser(userId);
					loginNoBaseDao.updateLoginNo(loginNoUpd);
					updateUserBindState(userList.get(0).getId(), newPhone, BindPhoneConstant.User_Bind_State_Success);
					addBindPhoneLog(userList.get(0).getId(), userId, newPhone, BindPhoneConstant.Bind_Phone_Log_State_Success);
					offlineTheSlaveUser(userId);
					{//重新登录
						autoLoginAfterBindPhone(request, UserContext.getCurrLoginNo());
					}
					userIdAfterChange = userList.get(0).getId();
				}
			} else {
				//userList.size()>1, impossible, 也要处理，否则老是要提示绑定 
				updateUserBindState(userId,	null, BindPhoneConstant.User_Bind_State_Fail);
				addBindPhoneLog(userId, userList.get(0).getId(), newPhone, BindPhoneConstant.Bind_Phone_log_State_Fail);
				UserContext.getCurrLoginNo().getUserEntity().setPhoneBindState(BindPhoneConstant.User_Bind_State_Fail);
			}
		}else{//当前用户有业务数据时
			User userQry = new User();
			userQry.setSys0DelState(0);
			userQry.setMobile(newPhone);
			List<User> userList = userBaseDao.selectUserByCondition(MapConverter.convertBean(userQry), false);
			if (userList.size() == 0) {
				//0 手机账号不存在，新建手机账号，并将手机绑定到当前账号
				createUserForNewPhoneAndBindToCurrentUser(userId, newPhone);
				{// 刷新登录参数
					UserContext.getCurrLoginNo().getUserEntity().setMobile(newPhone);
					UserContext.getCurrLoginNo().getUserEntity().setPhoneBindState(BindPhoneConstant.User_Bind_State_Success);
				}
			}else if(userList.size() == 1){
				LoginNo phoneLoginNo = accountManageDao.selectLoginNoDetail(newPhone, LoginDict.LoginType.MOBILE);
				
				if (judgePhoneHasBindedQQorWechat(phoneLoginNo, UserContext.getCurrLoginNo().getType())){
					updateUserBindState(userId,	null, BindPhoneConstant.User_Bind_State_Fail);
					addBindPhoneLog(userId, userList.get(0).getId(), newPhone, BindPhoneConstant.Bind_Phone_log_State_Fail);
					//刷新登录参数
					UserContext.getCurrLoginNo().getUserEntity().setPhoneBindState(BindPhoneConstant.User_Bind_State_Fail);
					return;
				}
				
				int phoneUserHasDataCount = userDao.selectHasBusinessDataCountByUserId(userList.get(0).getId());
				if (phoneUserHasDataCount == 0) {//手机用户没有业务数据
					//1 将手机绑定到当前账号
					LoginNo loginNoUpd = new LoginNo();
					loginNoUpd.setId(phoneLoginNo.getId());
					loginNoUpd.settUserFId(userId);
					loginNoUpd.setSys0UpdUser(userId);
					loginNoBaseDao.updateLoginNo(loginNoUpd);
					
					updateUserBindState(userId, newPhone, BindPhoneConstant.User_Bind_State_Success);
					addBindPhoneLog(userId, userList.get(0).getId(), newPhone, BindPhoneConstant.Bind_Phone_Log_State_Success);
					offlineTheSlaveUser(userList.get(0).getId());
					userDao.update_user_mobile_to_null(userList.get(0).getId());
					{// 重新登录
						autoLoginAfterBindPhone(request, UserContext.getCurrLoginNo());
					}
				}else{
					updateUserBindState(userId,	null, BindPhoneConstant.User_Bind_State_Fail);
					addBindPhoneLog(userId, userList.get(0).getId(), newPhone, BindPhoneConstant.Bind_Phone_log_State_Fail);
					//当前账号和该手机号下均有门牌信息，不能绑定！
					//throw new BusinessRuntimeException("userService.bindPhone.cantBind");
					{// 刷新登录参数
						UserContext.getCurrLoginNo().getUserEntity().setPhoneBindState(BindPhoneConstant.User_Bind_State_Fail);
					}
				}
			}else {
				//userList.size()>1, impossible, 也要处理，否则老是要提示绑定 
				updateUserBindState(userId,	null, BindPhoneConstant.User_Bind_State_Fail);
				addBindPhoneLog(userId, userList.get(0).getId(), newPhone, BindPhoneConstant.Bind_Phone_log_State_Fail);
				UserContext.getCurrLoginNo().getUserEntity().setPhoneBindState(BindPhoneConstant.User_Bind_State_Fail);
			}
		}
		if (userId.equals(userIdAfterChange)) {
			//消费券迁移
			userDao.changeCouponUser(userId, userIdAfterChange);
		}
	}

    /**
     * 当前QQ（微信）绑定到手机账号上时，要顺便将微信（QQ）绑定过去<br>
     * 若手机账号已绑定过微信（QQ），则不绑
     * @param userId 当前账号userId
     * @param userList 手机账号List
     * @param phoneLoginNo 手机账号登录方式
     * @author wenfq 2016-04-21
     */
	private void bindOtherLoginNo2PhoneAccountTogether(BigInteger userId, User phoneUser, LoginNo phoneLoginNo) {
		if(UserContext.getCurrLoginNo().getType() == LoginDict.AccountType.QQ.longValue()){
			bindToPhoneUser(userId, phoneUser, phoneLoginNo, LoginDict.AccountType.WEI_XIN);
			bindToPhoneUser(userId, phoneUser, phoneLoginNo, LoginDict.AccountType.WEI_XIN_LIGHT_APP);
		}else if(UserContext.getCurrLoginNo().getType() == LoginDict.AccountType.WEI_XIN.longValue()){
			bindToPhoneUser(userId, phoneUser, phoneLoginNo, LoginDict.AccountType.WEI_XIN_LIGHT_APP);
			bindToPhoneUser(userId, phoneUser, phoneLoginNo, LoginDict.AccountType.QQ);
		}else if(UserContext.getCurrLoginNo().getType() == LoginDict.AccountType.WEI_XIN_LIGHT_APP.longValue()){
			bindToPhoneUser(userId, phoneUser, phoneLoginNo, LoginDict.AccountType.WEI_XIN);
			bindToPhoneUser(userId, phoneUser, phoneLoginNo, LoginDict.AccountType.QQ);
		}
	}

	/**
	 * 绑定登录方式到手机账号上
	 * @param userId 当前解放号
	 * @param phoneUser 手机解放号
	 * @param phoneLoginNo 手机登录方式
	 * @param loginType 将绑定到手机上的登录方式
	 */
	private void bindToPhoneUser(BigInteger userId, User phoneUser, LoginNo phoneLoginNo, long loginType) {
		if(!judgePhoneHasBindedQQorWechat(phoneLoginNo, loginType)){
			LoginNo loginNoQry = new LoginNo();
			loginNoQry.settUserFId(userId);
			loginNoQry.setType(loginType);
			loginNoQry.setSys0DelState(0);
			List<LoginNo> loginNoList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry), false);
			if(loginNoList.size() == 1){
				LoginNo loginNo = new LoginNo();
				
				loginNo.setId(loginNoList.get(0).getId());
				loginNo.settUserFId(phoneUser.getId());
				loginNo.setSys0UpdUser(userId);
				//Password QQ(微信)不用设置的密码的
				loginNoBaseDao.updateLoginNo(loginNo);
			}
		}
	}

    /**
     *  判断QQ或WeChat想绑定的手机，是否已经绑定过QQ或Wechat <br>
     *  注意：微信和轻应用登录视为同一登录方式 
     * @param phoneLoginNo
     * @return true已绑过，false未绑
     * @author wenfq 2016-04-20
     */
	private boolean judgePhoneHasBindedQQorWechat(LoginNo phoneLoginNo, Long loginNoType) {
		LoginNo loginNoQry = new LoginNo();
		loginNoQry.setType(loginNoType);
		loginNoQry.settUserFId(phoneLoginNo.gettUserFId());
		loginNoQry.setSys0DelState(0);
		int currentLoginTypeCount = loginNoBaseDao.selectLoginNoCount(MapConverter.convertBean(loginNoQry), false);
		
		if(loginNoType == LoginDict.AccountType.WEI_XIN.longValue() || loginNoType == LoginDict.AccountType.WEI_XIN_LIGHT_APP.longValue()){
			loginNoQry.setType((LoginDict.AccountType.WEI_XIN + LoginDict.AccountType.WEI_XIN_LIGHT_APP) - loginNoType);
			currentLoginTypeCount += loginNoBaseDao.selectLoginNoCount(MapConverter.convertBean(loginNoQry), false);
		}
		return currentLoginTypeCount > 0;
	}

	private void autoLoginAfterBindPhone(HttpServletRequest request, LoginNo loginNo) {
		request.setAttribute(LoginConstant.ATTRIBUTE_NAME_ACCOUNT, loginNo.getNo());
		request.setAttribute(LoginConstant.ATTRIBUTE_NAME_PASSWD, loginNo.getPassword());
		request.setAttribute(LoginConstant.ATTRIBUTE_ACCOUNT_TYPE, loginNo.getType());
		
		String account = (String)request.getAttribute(LoginConstant.ATTRIBUTE_NAME_ACCOUNT);
		String password = (String)request.getAttribute(LoginConstant.ATTRIBUTE_NAME_PASSWD);
		Long accountType = (Long)request.getAttribute(LoginConstant.ATTRIBUTE_ACCOUNT_TYPE);
		
		commonLoginService.autoLogin(request, account, password,accountType);
	}
    
    @Resource CommUserSessionBaseDao commUserSessionBaseDao;
    /**
     * 被绑定的userId要下线处理
     * @param salveUserId 被绑定的用户ID
     * @author wenfq 2016-04-13
     */
    private void offlineTheSlaveUser(BigInteger salveUserId){
		CommUserSession commUserSessionQry = new CommUserSession();
		commUserSessionQry.setUserId(salveUserId);
		commUserSessionQry.setSys0DelState(0);
		List<CommUserSession> commUserSessionList = commUserSessionBaseDao.selectCommUserSessionByCondition(MapConverter.toMap(commUserSessionQry), false);
		for (CommUserSession commUserSession : commUserSessionList) {
			commUserSession.setSys0DelState(1);
		}

		if(commUserSessionList.size() > 0){
			commUserSessionBaseDao.updateCommUserSessionBatch(commUserSessionList);
		}
    }
    
    /**
     * 更新用户的绑定状态 
     * @param userId
     * @param bindState
     * @author wenfq 2016-04-13
     * @param newPhone 
     */
    private void updateUserBindState(BigInteger userId, String newPhone, int bindState){
    	User userUpd = new User();
		userUpd.setId(userId);
		userUpd.setMobile(newPhone);
		userUpd.setPhoneBindState(bindState);
		userUpd.setSys0UpdUser(UserContext.getOperIdMustExistBigInteger());
		userBaseDao.updateUser(userUpd);
    }
    
    /**
     * 添加手机绑定日志记录
     * @param sponsorUserId 当前userId
     * @param slaveUserId 被绑定的userId
     * @param phone 绑定的手机号
     * @param bindState 绑定状态，0未绑定，1绑定成功，2绑定失败
     * @author wenfq 2016-04-13
     */
    private void addBindPhoneLog(BigInteger sponsorUserId, BigInteger slaveUserId, String phone, int bindState) {
		BindPhoneLog bindPhoneLog = new BindPhoneLog();
		bindPhoneLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_bind_phone_log));
		bindPhoneLog.setBindPhone(phone);
		bindPhoneLog.setBindState(bindState);
		bindPhoneLog.setSponsorUserId(sponsorUserId);
		bindPhoneLog.setSlaveUserId(slaveUserId);
		bindPhoneLogBaseDao.insertBindPhoneLog(bindPhoneLog);
	}

    /**
     * 为手机创建新登录方式，并绑定到当前用户
     * @param userId 当前用户id
     * @param newPhone 手机号
     * @author wenfq 2016-04-11
     */
	private void createUserForNewPhoneAndBindToCurrentUser(BigInteger userId, String newPhone) {
		LoginNo currMainLoginNo = UserContext.getCurrLoginNo();
		if (currMainLoginNo == null) {
			throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.mainUser.huaInfo.null");
		}
		BigInteger applyLoginNoId = uuidManager.getNextUuidBigInteger(SEQConstants.t_login_no);
		String encodePwd = currMainLoginNo.getPassword();
		LoginNo applyLoginNo = commonLoginService.insertLoginNo(applyLoginNoId, newPhone, LoginDict.AccountType.MOBILE, userId, null, encodePwd,
				LoginDict.LoginNo_CreateType.Bind);
		updateUserPhoneById(userId, newPhone);
		
		addBindPhoneLog(userId, applyLoginNo.gettUserFId(), newPhone, BindPhoneConstant.Bind_Phone_Log_State_Success);
	}
    
	/**
	 * @param mainUserId
	 * @param newPhone
	 */
	private void updateUserPhoneById(BigInteger userId, String newPhone) {
		User userUpd = new User();
		userUpd.setId(userId);
		userUpd.setMobile(newPhone);
		userUpd.setPhoneBindState(1);
		int res = userDao.updateUserSignatureNull(userUpd);
		if(res<=0){
			throw new BusinessRuntimeException("commDataUpgrade.updateUserPhoneById.error");
		}
	}

    // @Override
    // public void bindPhone(HttpServletRequest request,BigInteger userId,
    // String newPhone) {
    // Boolean needUnBindold = null;
    // //查询当前用户已经绑定的手机号
    // LoginNo oldLoginNo = commonLoginService.getUserPhoneAccInfo(userId);
    // if(oldLoginNo==null){
    // needUnBindold = false;
    // }else{
    // if(oldLoginNo.getNo().equals(newPhone)){
    // throw new ValidateRuntimeException("bindPhone.check.phone.same.error");
    // }else{
    // needUnBindold = true;
    // //校验验证码是否正确
    // Boolean valicodeResCache =
    // (Boolean)SessionManager.getSession().getAttribute(LoginConstant.Change_Phone_Bind_Result);
    // if(valicodeResCache==null||valicodeResCache!=true){
    // throw new ValidateRuntimeException("bindPhone.check.oldValicode.error");
    // }else{
    // SessionManager.getSession().setAttribute(LoginConstant.Change_Phone_Bind_Result,
    // null);//清空
    // }
    // }
    // }
    // //校验新手机号是否已被绑定
    // {
    // LoginNo loginNoQry = new LoginNo();
    // loginNoQry.setNo(newPhone);
    // loginNoQry.setType(LoginDict.AccountType.MOBILE);
    // List<LoginNo> tmpList =
    // loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry),
    // false);
    // if(tmpList!=null&&tmpList.size()>0){
    // throw new
    // ValidateRuntimeException("UserService.bindPhone.newPhone.isAlreadyBind.error");
    // }
    // }
    // if(needUnBindold){//解绑原始手机号,绑定新手机号
    //// LoginNo loginNoQry = new LoginNo();
    //// loginNoQry.settUserFId(userId);
    //// loginNoQry.setNo(oldPhone);
    //// loginNoQry.setType(LoginDict.AccountType.MOBILE);
    //// List<LoginNo> tmpList =
    // loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry),
    // false);
    //// if(tmpList==null||tmpList.size()<=0){
    //// throw new
    // ValidateRuntimeException("UserService.bindPhone.oldPhoneLoginNo.empty.error");
    //// }
    //// if(tmpList.size()!=1){
    //// throw new
    // ValidateRuntimeException("UserService.bindPhone.oldPhoneLoginNo.size>1.error");
    //// }
    //// LoginNo oldLoginNo =tmpList.get(0);
    // //更新
    // LoginNo loginNoUpd= new LoginNo();
    // loginNoUpd.setId(oldLoginNo.getId());
    // loginNoUpd.setNo(newPhone);
    // int res = loginNoBaseDao.updateLoginNo(loginNoUpd);
    // if(res<=0){
    // throw new
    // BusinessRuntimeException("UserService.bindPhone.updateLoginNo.error");
    // }else{
    // updateUserPhoneById(userId, newPhone);
    // }
    // }else{//首次绑定新手机号
    // //通过花号获取密码
    // String tmpPasswd=null;
    // {
    // LoginNo loginNoQry = new LoginNo();
    // loginNoQry.settUserFId(userId);
    // loginNoQry.setType(LoginDict.AccountType.HUA_ID);
    // List<LoginNo> tmpList =
    // loginNoBaseDao.selectLoginNoByCondition(MapConverter.toMap(loginNoQry),
    // false);
    // tmpPasswd = tmpList.get(0).getPassword();
    // if(StringUtils.isEmpty(tmpPasswd)){
    // throw new
    // ValidateRuntimeException("UserService.bindPhone.hua.password.empty.error");
    // }
    // }
    // //获取新增的Id
    // BigInteger loginNoId=
    // uuidManager.getNextUuidBigInteger(SEQConstants.t_login_no);
    // LoginNo loginNo=new LoginNo();
    // loginNo.setId(loginNoId);
    // loginNo.setNo(newPhone);
    // loginNo.setType(LoginDict.AccountType.MOBILE);
    // loginNo.settUserFId(userId);
    // loginNo.setPassword(tmpPasswd);
    // int addRes = loginNoBaseDao.insertLoginNo(loginNo);
    // if(addRes<=0){
    // throw new
    // BusinessRuntimeException("UserService.bindPhone.insertLoginNo.error");
    // }else{
    // updateUserPhoneById(userId, newPhone);
    // }
    // }
    // commonLoginService.updateLoginSessionByUserId(userId, newPhone,
    // LoginDict.AccountType.MOBILE);
    //
    // }

    // 更新用户手机号
    // private void updateUserPhoneById(BigInteger userId,String newPhone){
    // User userUpd = new User();
    // userUpd.setId(userId);
    // userUpd.setMobile(newPhone);
    // int res = userDao.updateUserSignatureNull(userUpd);
    // if(res<=0){
    // throw new
    // BusinessRuntimeException("UserService.updateUserPhoneById.error");
    // }
    // }

    @Override
    public List<HobbyEntity> getAllHobbyList(PageModel pageModel, BigInteger userId) {
        return userDao.selectAllHobbyList(pageModel, userId);
        // return hobbyBaseDao.selectHobbyByCondition(new HashMap<String,
        // Object>(), pageModel, false);
    }

    private void submitAllHobbyOnly(List<BigInteger> hobbyIds, BigInteger userId) {
        // 查询当前已经收藏的
        List<Hobby> existList = getHobbyListByUserId(userId);
        List<BigInteger> existIds = new ArrayList<BigInteger>();
        for (Hobby tmpHobby : existList) {
            existIds.add(tmpHobby.getId());
        }
        // 对比当前用户要提交的 筛选出需要新增的和需要删除的
        AddAndDelIdsEntity addAndDelIdsEntity = IdsAddDelCheckUtil.analyze(existIds, hobbyIds);
        Set<BigInteger> toAddIdsSet = addAndDelIdsEntity.getToAddIds();
        Set<BigInteger> toDelIdsSet = addAndDelIdsEntity.getToDelIds();
        if (toAddIdsSet.size() > 0) {// 批量新增
            List<UserHasTHobby> batchList = new ArrayList<UserHasTHobby>();
            List<BigInteger> addIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_hobby, toAddIdsSet.size());
            List<BigInteger> toAddIds = new ArrayList<BigInteger>();
            for (BigInteger tmpSetId : toAddIdsSet) {
                toAddIds.add(tmpSetId);
            }
            for (int i = 0; i < toAddIds.size(); i++) {
                BigInteger hobbyId = toAddIds.get(i);
                BigInteger addId = addIdList.get(i);
                UserHasTHobby userHasTHobby = new UserHasTHobby();
                userHasTHobby.setId(addId);
                userHasTHobby.settHobbyFId(hobbyId);
                userHasTHobby.settUserFId(userId);
                batchList.add(userHasTHobby);
            }
            userHasTHobbyBaseDao.insertUserHasTHobbyBatch(batchList);
        }
        if (toDelIdsSet.size() > 0) {// 批量删除
            List<BigInteger> toDelIds = new ArrayList<BigInteger>();
            for (BigInteger tmpSetId : toDelIdsSet) {
                toDelIds.add(tmpSetId);
            }
            userDao.cancelHobbyIdsBatch(userId, toDelIds);
        }
    }

    @Override
    public List<Hobby> submitAllHobby(List<BigInteger> hobbyIds, BigInteger userId) {
        submitAllHobbyOnly(hobbyIds, userId);
        // 查询返回当前用户最新的类别收藏列表
        return getHobbyListByUserId(userId);
    }

    @Override
    public List<Hobby> getHobbyListByUserId(BigInteger userId) {
        return userDao.selectHobbyListByUserId(userId);
    }

}
