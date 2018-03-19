/**   
* Filename:    CommDataUpgradeService.java   
* @version:    1.0  
* Create at:   2015年4月22日 上午3:11:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.accountManage.dao.IAccountManageDao;
import com.cnfantasia.server.api.commonBusiness.dao.ICommDataUpgradeDao;
import com.cnfantasia.server.api.commonBusiness.entity.UserHasTRoomParamEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserHasTRoomWithRoomSimpleEntity;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.util.RoomStatusCheckUtil;
import com.cnfantasia.server.api.user.dao.IUserDao;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.loginNo.dao.ILoginNoBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.loginNoBindRelation.dao.ILoginNoBindRelationBaseDao;
import com.cnfantasia.server.domainbase.loginNoBindRelation.entity.LoginNoBindRelation;
import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.dao.ILoginNoOldUpgradeDateBaseDao;
import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity.LoginNoOldUpgradeDate;
import com.cnfantasia.server.domainbase.room.dao.IRoomBaseDao;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasTRoom.dao.IUserHasTRoomBaseDao;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    CommDataUpgradeService.java
 * @version:    1.0.0
 * Create at:   2015年4月22日 上午3:11:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月22日       shiyl             1.0             1.0 Version
 */
public class CommDataUpgradeService implements ICommDataUpgradeService{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommDataUpgradeDao commDataUpgradeDao;
	public void setCommDataUpgradeDao(ICommDataUpgradeDao commDataUpgradeDao) {
		this.commDataUpgradeDao = commDataUpgradeDao;
	}
	
	private ILoginNoOldUpgradeDateBaseDao loginNoOldUpgradeDateBaseDao;
	public void setLoginNoOldUpgradeDateBaseDao(ILoginNoOldUpgradeDateBaseDao loginNoOldUpgradeDateBaseDao) {
		this.loginNoOldUpgradeDateBaseDao = loginNoOldUpgradeDateBaseDao;
	}
	
	private ILoginNoBaseDao loginNoBaseDao;
	public void setLoginNoBaseDao(ILoginNoBaseDao loginNoBaseDao) {
		this.loginNoBaseDao = loginNoBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ILoginNoBindRelationBaseDao loginNoBindRelationBaseDao;
	public void setLoginNoBindRelationBaseDao(ILoginNoBindRelationBaseDao loginNoBindRelationBaseDao) {
		this.loginNoBindRelationBaseDao = loginNoBindRelationBaseDao;
	}
	
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}
	
	private IAccountManageDao accountManageDao;
	public void setAccountManageDao(IAccountManageDao accountManageDao) {
		this.accountManageDao = accountManageDao;
	}
	
	private IRoomBaseDao roomBaseDao;
	public void setRoomBaseDao(IRoomBaseDao roomBaseDao) {
		this.roomBaseDao = roomBaseDao;
	}
	
	private IUserHasTRoomBaseDao userHasTRoomBaseDao;
	public void setUserHasTRoomBaseDao(IUserHasTRoomBaseDao userHasTRoomBaseDao) {
		this.userHasTRoomBaseDao = userHasTRoomBaseDao;
	}
	
	private IUserDao userDao;
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	
	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}

	@Override
	public void convertSurpriseGiftDataByRoomId(BigInteger oldUserId, BigInteger oldRoomId,BigInteger newUserId, BigInteger newRoomId) {
		Integer resCount = commDataUpgradeDao.updateOldSurpriseGift2NewRoomId(oldUserId, oldRoomId, newUserId, newRoomId);
		if(resCount==null||resCount<=0){
			logger.info("CommDataUpgradeService.convertSurpriseGiftDataByRoomId.updateOldSurpriseGift2NewRoomId.failed,oldUserId is:"+oldUserId+",oldRoomId is:"+oldRoomId+",newUserId is"+newUserId+",newRoomId is:"+newRoomId);
		}
	}

	@Override
	public LoginNo qryExistLoginNoForWeiXin(Long accType, String accountNo, String unionId,Long subChannelId,String deviceId,String version) {
		//根据账号查设备，根据设备查询历史登录同类型的(微信)账号信息
		//如果只有一个，则返回对应的账号信息
		logger.info("CommDataUpgradeService.qryExistLoginNoForWeiXin,param info,accType is:"+accType+",subChannelId is:"+subChannelId+",deviceId is:"+deviceId+",unionId is:"+unionId+",version is:"+version);
		if(accType.compareTo(LoginDict.AccountType.WEI_XIN)==0
		&&!StringUtils.isEmpty(subChannelId)
		&&!StringUtils.isEmpty(version)
		&&!StringUtils.isEmpty(deviceId)
		&&!StringUtils.isEmpty(unionId)){
			Long versionLong = VersionTransferUtil.versionStr2Long(version);
			Long versionLong_2_0_3 = VersionTransferUtil.versionStr2Long("2.0.3");
			if(versionLong>=versionLong_2_0_3){//对2.0.3及以上版本处理
				logger.info("CommDataUpgradeService.qryExistLoginNoForWeiXin,start to check..");
				List<LoginNoOldUpgradeDate> loginNoOldUpgradeDateList = commDataUpgradeDao.selectLoginNoOldUpgradeDateByDevice(subChannelId, deviceId, accType);
				if(loginNoOldUpgradeDateList!=null&&loginNoOldUpgradeDateList.size()>0){
					if(loginNoOldUpgradeDateList.size()==1){
						LoginNoOldUpgradeDate tmpLoginNoOldUpgradeDate = loginNoOldUpgradeDateList.get(0);
						List<LoginNo> oldLoginNoList = commDataUpgradeDao.selectOldWeiXinByDeviceInfo(tmpLoginNoOldUpgradeDate.getId());
						if(oldLoginNoList!=null&&oldLoginNoList.size()>0){
							if(oldLoginNoList.size()==1){
								LoginNo tmpLoginNo = oldLoginNoList.get(0);
								if(StringUtils.isEmpty(tmpLoginNo.getUnionId())){
									{
										LoginNo loginNoUpd = new LoginNo();
										loginNoUpd.setId(tmpLoginNo.getId());
										loginNoUpd.setUnionId(unionId);
										loginNoUpd.setSys0UpdUser(new BigInteger("20150512"));
										Integer resCount = loginNoBaseDao.updateLoginNo(loginNoUpd);
										logger.info("CommDataUpgradeService.qryExistLoginNoForWeiXin,loginNoBaseDao.updateLoginNo resCount:"+resCount+",id is:"+tmpLoginNo.getId()+",unionId is:"+unionId);
										if(resCount!=null&&resCount>0){
											LoginNoOldUpgradeDate loginNoOldUpgradeDateUpd = new LoginNoOldUpgradeDate();
											loginNoOldUpgradeDateUpd.setId(tmpLoginNoOldUpgradeDate.getId());
											loginNoOldUpgradeDateUpd.setDealStatus(2);//处理状态=={"1":"未处理","2":"已处理"}
											Integer resCount02 = loginNoOldUpgradeDateBaseDao.updateLoginNoOldUpgradeDate(loginNoOldUpgradeDateUpd);//标记为已处理
											logger.info("CommDataUpgradeService.qryExistLoginNoForWeiXin,loginNoBaseDao.updateLoginNo resCount:"+resCount02+",id is:"+tmpLoginNoOldUpgradeDate.getId());
										}
									}
								}
								return tmpLoginNo;
							}else{
								logger.info("CommDataUpgradeService.qryExistLoginNoForWeiXin,tmpLoginNoOldUpgradeDate.id:"+tmpLoginNoOldUpgradeDate.getId()+",commDataUpgradeDao.selectOldWeiXinByDeviceInfo res size is:"+oldLoginNoList.size());
							}
						}
					}else{
						logger.info("CommDataUpgradeService.qryExistLoginNoForWeiXin,subChannelId:"+subChannelId+",deviceId is:"+deviceId+",accType is:"+accType+",commDataUpgradeDao.selectLoginNoOldUpgradeDateByDevice res size is:"+loginNoOldUpgradeDateList.size());
					}
					
				}
			}
		}
		
		return commDataUpgradeDao.selectExistLoginNoForWeiXin(accType, accountNo, unionId);
	}

	@Override
	public void updateWeiXinUnionIdIfNotExist(Long accType, String accountNo, String unionId) {
		logger.info("CommDataUpgradeService.updateWeiXinUnionIdIfNotExist params,accType is"+accType+",accountNo is"+accountNo+",unionId is"+unionId);
		//查询当前accType，accountNo账号对应的记录
		LoginNo currLoginNo = commDataUpgradeDao.selectLoginNoByAccountNoAndType(accountNo,accType);
		if(currLoginNo!=null){//若有
			if(StringUtils.isEmpty(currLoginNo.getUnionId())){//判断unionId是否为空，若为空则更新unionId
				LoginNo loginNoUpd = new LoginNo();
				loginNoUpd.setId(currLoginNo.getId());
				loginNoUpd.setUnionId(unionId);
				Integer resCount = loginNoBaseDao.updateLoginNo(loginNoUpd);
				if(resCount==null||resCount<=0){
					logger.info("CommDataUpgradeService.updateWeiXinUnionIdIfNotExist.updateLoginNo.failed,goal LoginNoId is:"+loginNoUpd.getId()+",goal unionId is:"+unionId);
					throw new BusinessRuntimeException("CommDataUpgradeService.updateWeiXinUnionIdIfNotExist.updateUnionId.failed");
				}
			}
		}else{//若无则新增，新增内容依据unionId的数据
			//根据unionId获取对应的主账号信息
			LoginNo defaultLoginNo = commDataUpgradeDao.selectDefaultLoginNoByWeiXinUnionId(unionId);
			if(defaultLoginNo!=null){
				if(accType.compareTo(defaultLoginNo.getType())==0&&accountNo.equals(defaultLoginNo.getNo())){//相同账号
					//此时不会进入此流程
				}else{//新增账号
					BigInteger _addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_login_no);
					String _account = accountNo;
					Long _accountType = accType;
					BigInteger _userId = defaultLoginNo.gettUserFId();
					String _unionId = unionId;
					String _encodePwd = defaultLoginNo.getPassword();
					Integer createType = LoginDict.LoginNo_CreateType.WeiXinUnionIdBind;
					commonLoginService.insertLoginNo(_addId, _account, _accountType, _userId, _unionId, _encodePwd, createType);
				}
			}else{
				//用户尚未注册，后续流程会处理，进行注册，此处无需处理
			}
		}
//		Integer resCount = commDataUpgradeDao.updateWeiXinUnionIdIfNotExist(accType, accountNo, unionId);
//		if(resCount==null||resCount<=0){
//			logger.info("CommDataUpgradeService.updateWeiXinUnionIdIfNotExist.updateWeiXinUnionIdIfNotExist.failed,accType is:"+accType+",accountNo is:"+accountNo+",unionId is:"+unionId);
//		}
		
	}

	@Override
	public void transferPrizeRecordFromOld2New(BigInteger oldUserId, BigInteger oldRoomId,BigInteger newUserId, BigInteger newRoomId) {
		Integer resCount = commDataUpgradeDao.updatePrizeRecFromOne2Another(oldUserId, oldRoomId, newUserId, newRoomId);
		if(resCount==null||resCount<=0){
			logger.info("CommDataUpgradeService.transferPrizeRecordFromOld2New.updatePrizeRecFromOne2Another.failed,oldUserId is:"+oldUserId+",oldRoomId is:"+oldRoomId+",newUserId is"+newUserId+",newRoomId is:"+newRoomId);
		}
	}
	
	@Override
	public void convertShenMaHbOld2New(BigInteger oldUserId, BigInteger oldRoomId,BigInteger newUserId, BigInteger newRoomId) {
		Integer resCount = commDataUpgradeDao.updateShenMaHbFromOne2Another(oldUserId, oldRoomId, newUserId, newRoomId);
		if(resCount==null||resCount<=0){
			logger.info("CommDataUpgradeService.convertShenMaHbOld2New.updateShenMaHbFromOne2Another.failed,oldUserId is:"+oldUserId+",oldRoomId is:"+oldRoomId+",newUserId is"+newUserId+",newRoomId is:"+newRoomId);
		}
	}
	
//	@Override
//	public void doOldWeixinLoginNoUpgrade(String openId, Long accType, Long subChannelId, String version,
//			String deviceId, String unionId) {
//		logger.info("CommDataUpgradeService.doOldWeixinLoginNoUpgrade,param info,openId is:"+openId+",accType is:"+accType+",subChannelId is:"+subChannelId
//				+",version is:"+version+",deviceId is:"+deviceId+",unionId is:"+unionId);
//		//version为2.0.3及以后的
//		if(accType.compareTo(LoginDict.AccountType.WEI_XIN)==0
//				&&!StringUtils.isEmpty(subChannelId)
//				&&!StringUtils.isEmpty(version)
//				&&!StringUtils.isEmpty(deviceId)
//				&&!StringUtils.isEmpty(unionId)){
//			Long versionLong = VersionTransferUtil.versionStr2Long(version);
//			Long versionLong_2_0_3 = VersionTransferUtil.versionStr2Long("2.0.3");
//			if(versionLong>=versionLong_2_0_3){//对2.0.3及以上版本处理
//				//1.根据当前账号查询其登录过的设备列表
//				//2.如果设备有且只有一个，根据设备查询其2.0.3之前版本的登录过的微信账号列表
//				//3.如果之前的账号有且只有一个
//				//4.将当前的unionId更新到历史的loginNo
//				
//				//根据账号(只在一个设备上登录过的)，找到设备(一个的)，然后根据设备升级
//				//2.0.3版本之前的找到一个，2.0.3及以后的找一个
//			}
//		}
//		
//	}
	
	
	//==================================================账号绑定相关处理===============================================
	
	@Override
	public void executeBindActionForPhone(BigInteger mainUserId, String newPhone) {
		Long applyAccType = LoginDict.AccountType.MOBILE;
		boolean isApplyAccCanAdd = true;
		String applyUnionId = null;
		String applyAccountNo = newPhone;
		executeBindAction(mainUserId, applyAccountNo, applyAccType, applyUnionId, isApplyAccCanAdd);
	}
	
	@Override
	public synchronized void executeBindAction(BigInteger mainUserId,String applyAccountNo,Long applyAccType,String applyUnionId,boolean isApplyAccCanAdd){
		logger.info("accountManage.executeBindAction param,mainUserId is:"+mainUserId+",applyAccountNo is:"+applyAccountNo+",applyAccType is:"+applyAccType+",isApplyAccCanAdd is:"+isApplyAccCanAdd);
		if(mainUserId==null||StringUtils.isEmpty(applyAccountNo)||applyAccType==null){
			throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.param.hasNull");
		}
		if(applyAccType.compareTo(LoginDict.AccountType.HUA_ID)==0){//花号不支持参与绑定
			throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.applyAccType.isHua");
		}
		
		boolean isTypeExist = false;
		{//同类型的账号只能绑定一次
			List<LoginNo> loginNoList = accountManageDao.selectBindAccList(mainUserId);
			if(loginNoList==null||loginNoList.size()<=0){//当前用户对应账号信息为空,可能账号已经在别处被绑定
				throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.selectBindAccList.size0");
			}
			for(LoginNo tmpLoginNo:loginNoList){
				if(tmpLoginNo.getType().compareTo(applyAccType)==0){
					isTypeExist = true;
					break;
				}
			}
			if(isTypeExist){
				if(applyAccType.compareTo(LoginDict.AccountType.MOBILE)==0){
					
				}else{
					throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.isTypeExist.failed");
				}
			}
		}
		
		//查询当前用户的花号账户信息
		LoginNo currMainLoginNo = accountManageDao.selectUserHuaLoginNoInfo(mainUserId);
		if(currMainLoginNo==null){
			throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.mainUser.huaInfo.null");
		}
		
		//判断待绑定账号是否已注册
		this.updateWeiXinUnionIdIfNotExist(applyAccType, applyAccountNo, applyUnionId);
		LoginNo applyLoginNo = accountManageDao.selectLoginNoDetail(applyAccountNo,applyAccType);
		Boolean isRegist = null;
		isRegist = applyLoginNo != null;
		
		if(isTypeExist&&applyAccType.compareTo(LoginDict.AccountType.MOBILE)==0&&isRegist){//当前已有手机号，待绑定的也是手机号，且待绑定的手机号已经注册
			throw new BusinessRuntimeException("commDataUpgrade.checkAccNoAndTypeIsBind.phone.isRegist.failed");
		}
		
		if(!isRegist){//待绑定的账号是否已经注册，未注册则新增
			if(isApplyAccCanAdd){//可以注册
				if(applyAccType.compareTo(LoginDict.AccountType.MOBILE)==0){//手机的账号类型//手机号的换绑
					//查询当前主账号用户已经绑定的手机号
					//当前用户是否已包含手机类型的账号
					//未包含则新增，已包含则更新
					String newPhone = applyAccountNo;
					LoginNo oldLoginNo = commonLoginService.getUserPhoneAccInfo(mainUserId);
					if(oldLoginNo==null){//手机号-新增
						BigInteger applyLoginNoId = null;
						applyLoginNoId = uuidManager.getNextUuidBigInteger(SEQConstants.t_login_no);
						String encodePwd = currMainLoginNo.getPassword();
						applyLoginNo = commonLoginService.insertLoginNo(applyLoginNoId, applyAccountNo, applyAccType, mainUserId, applyUnionId, encodePwd,LoginDict.LoginNo_CreateType.Bind);
						updateUserPhoneById(mainUserId, newPhone);
					}else{//手机号-换绑
						if(oldLoginNo.getNo().equals(newPhone)){
							throw new ValidateRuntimeException("commDataUpgrade.bindPhone.check.phone.same.error");
						}else{
							//校验验证码是否正确
							Boolean valicodeResCache = (Boolean)SessionManager.getSession().getAttribute(LoginConstant.Change_Phone_Bind_Result);
							if(valicodeResCache==null||valicodeResCache!=true){
								throw new ValidateRuntimeException("commDataUpgrade.bindPhone.check.oldValicode.error");
							}else{
								SessionManager.getSession().setAttribute(LoginConstant.Change_Phone_Bind_Result, null);//清空
							}
							LoginNo loginNoUpd= new LoginNo();
							loginNoUpd.setId(oldLoginNo.getId());
							loginNoUpd.setNo(newPhone);
							int res = loginNoBaseDao.updateLoginNo(loginNoUpd);
							if(res<=0){
								throw new BusinessRuntimeException("commDataUpgrade.bindPhone.updateLoginNo.error");
							}else{
								updateUserPhoneById(mainUserId, newPhone);
							}
							applyLoginNo = oldLoginNo;
						}
						//换绑才更新用户登录的session
						commonLoginService.updateLoginSessionByUserId(mainUserId, newPhone, LoginDict.AccountType.MOBILE);
					}
				}else{//其他账号 新增
					BigInteger applyLoginNoId = null;
					applyLoginNoId = uuidManager.getNextUuidBigInteger(SEQConstants.t_login_no);
					String encodePwd = currMainLoginNo.getPassword();
					applyLoginNo = commonLoginService.insertLoginNo(applyLoginNoId, applyAccountNo, applyAccType, mainUserId, applyUnionId, encodePwd,LoginDict.LoginNo_CreateType.Bind);
				}
			}else{
				throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.applyAcc.notRegist");
			}
			
		}else{
			if(applyLoginNo.gettUserFId().compareTo(mainUserId)==0){//已注册的 校验 对应的userId 是否为当前登录用户，若是则提示
				throw new BusinessRuntimeException("commDataUpgrade.executeBindAction.userId.isCurrent");
			}
		}
		
		if(isRegist){//已注册则校验是否已绑定
			//syl-upd-2015-11-5 10:27:25 账号绑定增加校验处理
			throw new BusinessRuntimeException("commDataUpgrade.isRegist.failed");
//			boolean isBinded = checkAccNoAndTypeIsBind(applyAccountNo, applyAccType);
//			if(isBinded){
//				throw new BusinessRuntimeException("commDataUpgrade.checkAccNoAndTypeIsBind.isBinded.failed");
//			}
		}
		
		boolean isNewRegist = !isRegist;//是否为新注册的用户
		{//已注册或者未注册的账号，进行用户信息的绑定
			mergeLogInUserDataTotallyForCommBind(mainUserId, currMainLoginNo, applyLoginNo,isNewRegist);
		}
		
		//执行绑定完成后，如果被绑定的是手机，则校验用户门牌列表是否自动验证通
		if(applyAccType.compareTo(LoginDict.AccountType.MOBILE)==0){
			String bindPhone = applyAccountNo;
			commonRoomService.autoValidateRoomForBindPhone(mainUserId, bindPhone);
		}
		
	}
	
	
	/**
	 * @param mainUserId
	 * @param newPhone
	 */
	private void updateUserPhoneById(BigInteger userId, String newPhone) {
		User userUpd = new User();
		userUpd.setId(userId);
		userUpd.setMobile(newPhone);
		int res = userDao.updateUserSignatureNull(userUpd);//TODO 待完善
		if(res<=0){
			throw new BusinessRuntimeException("commDataUpgrade.updateUserPhoneById.error");
		}
	}

	@Override
	public void mergeDataWeiXin(BigInteger currUserId,String weixinUnionId) {
		if(!StringUtils.isEmpty(weixinUnionId)){
			//根据unionId查询默认登录的主账号
			LoginNo defaultLoginNo = commDataUpgradeDao.selectDefaultLoginNoByWeiXinUnionId(weixinUnionId);
			if(defaultLoginNo!=null){
				//根据unionId查询所有其他的账号列表
				List<LoginNo> existLoginNoList = commDataUpgradeDao.selectLoginNoListByWeiXinUnionId(weixinUnionId);
				if(existLoginNoList!=null&&existLoginNoList.size()>0){
					for(LoginNo currLoginNo:existLoginNoList){
						if(defaultLoginNo.getId().compareTo(currLoginNo.getId())==0){
							continue;
						}
						//将其他的账号数据归到到主账号下面
						mergeLogInUserDataTotallyForWeixinUnionBind(currUserId, defaultLoginNo, currLoginNo);
					}
				}
			}
		}
	}
	
	//step1创建绑定关系
	private void mergeLogInUserData_addBindRelation(BigInteger currUserId,LoginNo currMainLoginNo,LoginNo applyLoginNo){
		//用户相同 也创建绑定关系
		//通过创建用户的绑定关系，具体绑定数据通过业务的查询来处理
		BigInteger bindRelationAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_login_no_bind_relation);
		LoginNoBindRelation loginNoBindRelation = new LoginNoBindRelation();
		loginNoBindRelation.setApplyAccNo(applyLoginNo.getNo());
		loginNoBindRelation.setApplyAccType(applyLoginNo.getType());
		loginNoBindRelation.setApplyLoginNoId(applyLoginNo.getId());
		loginNoBindRelation.setApplyUserId(applyLoginNo.gettUserFId());
		try {
			loginNoBindRelation.setCreateRoomId(currUserId==null?null:commonRoomService.getDefaultRoomIdByUserId(currUserId));
		} catch (Exception e) {
			logger.info("CommDataUpgradeService.mergeLogInUserDataTotally.commonRoomService.getDefaultRoomIdByUserId(currUserId) cause exception,currUserId is:"+currUserId, e);
		}
		loginNoBindRelation.setCreateTime(dualDao.getNowTime());
		loginNoBindRelation.setCreateUserId(currUserId);
		loginNoBindRelation.setId(bindRelationAddId);
		loginNoBindRelation.setMainAccNo(currMainLoginNo.getNo());
		loginNoBindRelation.setMainAccType(currMainLoginNo.getType());
		loginNoBindRelation.setMainLoginNoId(currMainLoginNo.getId());
		loginNoBindRelation.setMainUserId(currMainLoginNo.gettUserFId());
		loginNoBindRelationBaseDao.insertLoginNoBindRelation(loginNoBindRelation);
		//密码更新 syl-add-2015-5-20 17:18:50
		accountManageDao.updateApplyPwdByMainUser(currMainLoginNo.gettUserFId());
	}
	
	//step2门牌数据的合并处理
	private void mergeLogInUserData_mergeDeepData(BigInteger currUserId,LoginNo currMainLoginNo,LoginNo applyLoginNo){
		//1.获取两个用户的门牌
		List<UserHasTRoomWithRoomSimpleEntity> bothHaveRoomList_main = new ArrayList<UserHasTRoomWithRoomSimpleEntity>();//共同包含的门牌_主
		List<UserHasTRoomWithRoomSimpleEntity> bothHaveRoomList_apply = new ArrayList<UserHasTRoomWithRoomSimpleEntity>();//共同包含的门牌_从
		//key:bothHaveRoomList_main  value bothHaveRoomList_apply
		Map<UserHasTRoomWithRoomSimpleEntity,UserHasTRoomWithRoomSimpleEntity> bothHaveRoomMap = new HashMap<UserHasTRoomWithRoomSimpleEntity, UserHasTRoomWithRoomSimpleEntity>();
		
		List<UserHasTRoomWithRoomSimpleEntity> onlyMainRoomList = new ArrayList<UserHasTRoomWithRoomSimpleEntity>();//仅主账号包含的门牌
		List<UserHasTRoomWithRoomSimpleEntity> onlyApplyRoomList = new ArrayList<UserHasTRoomWithRoomSimpleEntity>();//仅申请账号包含的门牌
		{
			List<UserHasTRoomWithRoomSimpleEntity> mainRoomList = commonRoomService.getUserHasTRoomWithRoomSimpleList(currMainLoginNo.gettUserFId());
			List<UserHasTRoomWithRoomSimpleEntity> applyRoomList = commonRoomService.getUserHasTRoomWithRoomSimpleList(applyLoginNo.gettUserFId());
			if(mainRoomList!=null&&mainRoomList.size()>0&&applyRoomList!=null&&applyRoomList.size()>0){
				//设定公共门牌
				for(UserHasTRoomWithRoomSimpleEntity tmpMainRoom:mainRoomList){
					for(UserHasTRoomWithRoomSimpleEntity tmpApplyRoom:applyRoomList){
						if(tmpMainRoom.getRealRoomId().compareTo(tmpApplyRoom.getRealRoomId())==0){//真实门牌相同
							bothHaveRoomList_main.add(tmpMainRoom);
							bothHaveRoomList_apply.add(tmpApplyRoom);
							bothHaveRoomMap.put(tmpMainRoom, tmpApplyRoom);
							break;
						}
					}
				}
			}
			//设定仅主账号包含的门牌
			if(mainRoomList!=null&&mainRoomList.size()>0){
				onlyMainRoomList.addAll(mainRoomList);
				onlyMainRoomList.removeAll(bothHaveRoomList_main);
			}
			//设定仅申请账号包含的门牌
			if(applyRoomList!=null&&applyRoomList.size()>0){
				onlyApplyRoomList.addAll(applyRoomList);
				onlyApplyRoomList.removeAll(bothHaveRoomList_apply);
			}
		}
		
		//=============统计待处理的数据=============
		List<UserHasTRoomParamEntity> toAddUserHasTRoomList = new ArrayList<UserHasTRoomParamEntity>();
		List<BigInteger> toDelRoomIdList = new ArrayList<BigInteger>();//待删除的门牌列表
		List<BigInteger> toDelUserHasTRoomIdList = new ArrayList<BigInteger>();//待删除的用户门牌关系列表
		List<UserHasTRoom> toUpdUserHasTRoomList = new ArrayList<UserHasTRoom>();//待更新的数据
		List<User> toUpdUserList = new ArrayList<User>();
		List<BigInteger> toUpdateAndCheckRealRoomValidateInfoList = new ArrayList<BigInteger>();
		//申请的账号为手机，则更新mainUser的手机号
		if(applyLoginNo.getType().compareTo(LoginDict.AccountType.MOBILE)==0){
			//前面已经验证过，此处不需要验证
//			LoginNo userPhoneAccInfo = commonLoginService.getUserPhoneAccInfo(currMainLoginNo.gettUserFId());
//			if(userPhoneAccInfo!=null){//确保当前账号下没有手机
//				throw new BusinessRuntimeException("commDataUpgrade.mergeLogInUserData_mergeDeepData.phone2phone.failed");
//			}
			User userUpd = new User();
			userUpd.setId(currMainLoginNo.gettUserFId());
			userUpd.setMobile(applyLoginNo.getNo());
			toUpdUserList.add(userUpd);
		}
		//2.仅申请账号包含的门牌，合并到主用户上
		if(onlyApplyRoomList!=null&&onlyApplyRoomList.size()>0){
			for(UserHasTRoomWithRoomSimpleEntity tmpRoom:onlyApplyRoomList){
				{
					BigInteger _roomId = tmpRoom.gettRoomFId();
					BigInteger _userId = currMainLoginNo.gettUserFId();
					BigInteger _inviterUserId = applyLoginNo.gettUserFId();
					BigInteger _bindUserId = currMainLoginNo.gettUserFId();//绑定用户使用主账号
					Integer _adminType = RoomDict.UserHasTRoom_IsAdmin.FALSE;
					toAddUserHasTRoomList.add(new UserHasTRoomParamEntity(_roomId, _userId, _adminType, _inviterUserId, _bindUserId));
				}
				{//更新apply账号的room的bindUserId
					UserHasTRoom userHasTRoomUpd = new UserHasTRoom();
					userHasTRoomUpd.setId(tmpRoom.getId());
					BigInteger _bindUserId = currMainLoginNo.gettUserFId();//绑定用户使用主账号,非常重要!!!
					userHasTRoomUpd.setBindUserId(_bindUserId);
					userHasTRoomUpd.setFinalUserId(_bindUserId);//syl-add-2015-6-24 17:41:53 冗余finalUserId
					toUpdUserHasTRoomList.add(userHasTRoomUpd);
				}
				toUpdateAndCheckRealRoomValidateInfoList.add(tmpRoom.gettRoomFId());
			}
		}
		//3.相同的门牌，判断优先级，取验证过的
		Room mainUserDefaultRoom = commonRoomService.getDefaultRoomByUserId(currMainLoginNo.gettUserFId());
		Room applyUserDefaultRoom = commonRoomService.getDefaultRoomByUserId(applyLoginNo.gettUserFId());
		BigInteger mainUserDefaultRoomId = mainUserDefaultRoom.getId();
		BigInteger applyUserDefaultRoomId = mainUserDefaultRoom.getId();
		BigInteger mainUserDefaultRoomId_new = mainUserDefaultRoomId;
		if(bothHaveRoomMap!=null&&bothHaveRoomMap.size()>0){
			for(UserHasTRoomWithRoomSimpleEntity mainTmp:bothHaveRoomMap.keySet()){
				UserHasTRoomWithRoomSimpleEntity applyTmp = bothHaveRoomMap.get(mainTmp);
				//main为空门牌或者apply为空门牌
				if(RoomStatusCheckUtil.checkIsRealRoomEmpty(mainTmp.getRealRoomId())||RoomStatusCheckUtil.checkIsRealRoomEmpty(applyTmp.getRealRoomId())){
					{//apply的账号默认门牌统一设置为空门牌
						User userUpd = new User();
						userUpd.setId(applyLoginNo.gettUserFId());
						userUpd.setDefaultRoomId(applyTmp.gettRoomFId());
						toUpdUserList.add(userUpd);
					}
					continue;//空门牌不处理
				}
				
				if(applyTmp.compareToImportant(mainTmp)>0){//申请的重要程度高,需要合并数据 取用从账户数据,不能取等于
					//删除main的room
					toDelRoomIdList.add(mainTmp.gettRoomFId());
					toDelUserHasTRoomIdList.add(mainTmp.getId());
					{//新增main相关到apply的room
						BigInteger _roomId = applyTmp.gettRoomFId();
						BigInteger _userId = currMainLoginNo.gettUserFId();
						BigInteger _inviterUserId = applyLoginNo.gettUserFId();
						BigInteger _bindUserId = currMainLoginNo.gettUserFId();//绑定用户使用主账号
						Integer _adminType = RoomDict.UserHasTRoom_IsAdmin.FALSE;
						toAddUserHasTRoomList.add(new UserHasTRoomParamEntity(_roomId, _userId, _adminType, _inviterUserId, _bindUserId));
					}
					{//更新 apply的room
						UserHasTRoom userHasTRoomUpd = new UserHasTRoom();
						userHasTRoomUpd.setId(applyTmp.getId());
						BigInteger _bindUserId = currMainLoginNo.gettUserFId();//绑定用户使用主账号,非常重要!!!
						userHasTRoomUpd.setBindUserId(_bindUserId);
						toUpdUserHasTRoomList.add(userHasTRoomUpd);
					}
					//判断是否需要更新用户的默认门牌
					if(mainUserDefaultRoomId!=null&&mainUserDefaultRoomId.compareTo(mainTmp.gettRoomFId())==0){
						User userUpd = new User();
						userUpd.setId(currMainLoginNo.gettUserFId());
						userUpd.setDefaultRoomId(applyTmp.gettRoomFId());
						toUpdUserList.add(userUpd);
						mainUserDefaultRoomId_new = applyTmp.gettRoomFId();//缓存
					}
					//门牌管理员的处理,如果是门牌管理,则需要更新相关的验证数据
					if(applyTmp.getRoomIsValidate()){//校验修改realRoom的验证信息及roomValidate的信息
						toUpdateAndCheckRealRoomValidateInfoList.add(applyTmp.gettRoomFId());
					}
				}else{//使用主账号的数据
					//删除apply的room
					toDelRoomIdList.add(applyTmp.gettRoomFId());
					toDelUserHasTRoomIdList.add(applyTmp.getId());
				}
			}
			
		}
		
		//判断是否需要更新用户的默认门牌 写到外面
		if(RoomStatusCheckUtil.checkIsRealRoomEmpty(mainUserDefaultRoom.gettRealRoomFId())&&//主用户门牌为空门牌
				!RoomStatusCheckUtil.checkIsRealRoomEmpty(applyUserDefaultRoom.gettRealRoomFId())){//且apply用户门牌不为空门牌
			//目前业务上，可以保证此时applyUserDefaultRoom.getId()是不会被逻辑删除的
			User userUpd = new User();
			userUpd.setId(currMainLoginNo.gettUserFId());
			userUpd.setDefaultRoomId(applyUserDefaultRoomId);
			toUpdUserList.add(userUpd);
		}
		//4.提交到数据库 注意数据的提交顺序
		commonRoomService.addUserHasTRoomBatch(toAddUserHasTRoomList);
		if(toDelRoomIdList!=null&&toDelRoomIdList.size()>0){
			roomBaseDao.deleteRoomLogicBatch(toDelRoomIdList);
		}
		if(toDelUserHasTRoomIdList!=null&&toDelUserHasTRoomIdList.size()>0){
			userHasTRoomBaseDao.deleteUserHasTRoomLogicBatch(toDelUserHasTRoomIdList);
		}
		if(toUpdUserHasTRoomList!=null&&toUpdUserHasTRoomList.size()>0){
			userHasTRoomBaseDao.updateUserHasTRoomBatch(toUpdUserHasTRoomList);
		}
		if(toUpdUserList!=null&&toUpdUserList.size()>0){
			userBaseDao.updateUserBatch(toUpdUserList);
		}
		if(toUpdateAndCheckRealRoomValidateInfoList!=null&&toUpdateAndCheckRealRoomValidateInfoList.size()>0){
			for(BigInteger applyTmpRoomId:toUpdateAndCheckRealRoomValidateInfoList){
				//applyTmp.gettRoomFId()
				commDataUpgradeDao.updateAndCheckRealRoomValidateInfo(applyLoginNo.gettUserFId(),currMainLoginNo.gettUserFId(),applyTmpRoomId);
			}
		}
		//如果apply默认门牌是-1，main门牌是-1 or 是A301
		//将-1意外惊喜数据合并到main当前的默认门牌下
		if(RoomStatusCheckUtil.checkIsRealRoomEmpty(applyUserDefaultRoom.gettRealRoomFId())){//数据转移
			this.convertDataFromOld2New(applyLoginNo.gettUserFId(), applyUserDefaultRoom.getId(), currMainLoginNo.gettUserFId(), mainUserDefaultRoomId_new);
		}
	}
	
	//step3注销对应账号的session信息
	private void mergeLogInUserData_expireSession(BigInteger currUserId,LoginNo currMainLoginNo,LoginNo applyLoginNo){
		commonLoginService.expiredLoginSessionByUserId(applyLoginNo.gettUserFId(), applyLoginNo.getNo(), applyLoginNo.getType());
	}
	/**
	 * 
	 * 执行用户普通操作的 用户信息的绑定
	 * @param currUserId 当前登录的用户Id
	 * @param currMainLoginNo 主账号Id
	 * @param applyLoginNo 申请的账号Id
	 * @param isNewRegist 申请的账号是否刚刚新注册的
	 */
	private void mergeLogInUserDataTotallyForCommBind(BigInteger currUserId,LoginNo currMainLoginNo,LoginNo applyLoginNo,boolean isNewRegist){
		mergeLogInUserData_addBindRelation(currUserId, currMainLoginNo, applyLoginNo);//创建绑定关系
		if(isNewRegist){//目标账号为新注册用户,数据不需要合并
			
		}else{
			if(applyLoginNo.getType().compareTo(LoginDict.AccountType.MOBILE)==0){//已注册，手机的账号类型
				if(currMainLoginNo.gettUserFId().compareTo(applyLoginNo.gettUserFId())==0){//账号目标用户与当前用户是否相同-是
					throw new BusinessRuntimeException("commDataUpgrade.mergeLogInUserDataTotallyForCommBind.phone.haveSelfBind");
				}else{//账号目标用户与当前用户是否相同-否
					//当前没有手机号账号 待绑定的手机号已经注册了账户
//					throw new BusinessRuntimeException("commDataUpgrade.mergeLogInUserDataTotallyForCommBind.phone.haveOtherBind");
					//门牌数据的合并处理
					mergeLogInUserData_mergeDeepData(currUserId, currMainLoginNo, applyLoginNo);
					//注销对应账号的session信息
					mergeLogInUserData_expireSession(currUserId, currMainLoginNo, applyLoginNo);
				}
			}else{
				if(currMainLoginNo.gettUserFId().compareTo(applyLoginNo.gettUserFId())==0){//账号目标用户与当前用户是否相同-是
					throw new BusinessRuntimeException("commDataUpgrade.mergeLogInUserDataTotallyForCommBind.otherType.haveSelfBind");
				}else{
					//门牌数据的合并处理
					mergeLogInUserData_mergeDeepData(currUserId, currMainLoginNo, applyLoginNo);
					//注销对应账号的session信息
					mergeLogInUserData_expireSession(currUserId, currMainLoginNo, applyLoginNo);
				}
			}
		}
		
		commonPrizeService.freshMiniDiscountByUserId(currMainLoginNo.gettUserFId(),null,null);//syl-add 2015-5-22 13:04:12 刷新门牌最低折扣
	}
	
	/**
	 * 合并微信账号的数据处理 增强
	 * 执行相同Union信息的微信用户信息的绑定
	 * @param currUserId 当前登录的用户Id
	 * @param currMainLoginNo 主账号Id
	 * @param applyLoginNo 申请的账号Id
	 */
	public void mergeLogInUserDataTotallyForWeixinUnionBind(BigInteger currUserId,LoginNo currMainLoginNo,LoginNo applyLoginNo){
		if(currMainLoginNo.gettUserFId().compareTo(applyLoginNo.gettUserFId())==0){//相同用户不合并
			
		}else{
			//查询是否已存在对应绑定关系
			String applyAccountNo = applyLoginNo.getNo();
			Long applyAccType = applyLoginNo.getType();
			boolean isBind = checkAccNoAndTypeIsBind(applyAccountNo, applyAccType);
			if(!isBind){//未绑定过的则执行绑定
				Boolean isNewRegist = false;//是否为刚刚新注册的
				mergeLogInUserDataTotallyForCommBind(currUserId, currMainLoginNo, applyLoginNo,isNewRegist);
			}
		}
	}
	
//	@Override
//	public void mergeLogInUserData(BigInteger srcUserId,BigInteger goalUserId){
//		if(srcUserId==null||goalUserId==null||srcUserId.compareTo(goalUserId)==0){
//			return;
//		}
//		if(srcUserId.compareTo(goalUserId)==0){//用户相同
//			return;
//		}
//		//当前仅处理登录用户，不区分门牌，全部归属到目标用户的默认门牌下面
//		//意外惊喜数据的合并
//		Integer resCount = commDataUpgradeDao.updateSurpriseGiftFromSrc2Goal(srcUserId,goalUserId);
//		if(resCount==null||resCount<=0){
//			logger.info("CommDataUpgradeService.mergeLogInUserData.updateSurpriseGiftFromSrc2Goal.failed,srcUserId is:"+srcUserId+",goalUserId is:"+goalUserId);
//		}
//	}
	
	/**
	 * 已经被绑定过的账号 不能再绑定到别的地方
	 * @param applyAccountNo
	 * @param applyAccType
	 */
	private boolean checkAccNoAndTypeIsBind(String applyAccountNo,Long applyAccType){
		//已经被绑定过的账号 不能再绑定到别的地方
		logger.info("commDataUpgrade.checkAccNoAndTypeIsBind param,toBindAccountNo is:"+applyAccountNo+",toBindAccType is:"+applyAccType);
		boolean isBinded = false;
		Integer bindRelaCount = accountManageDao.selectBindRelationCount(applyAccountNo, applyAccType);
		if(bindRelaCount!=null&&bindRelaCount>0){//有绑定关系
			isBinded = true;
		}
		//TODO 升级绑定的处理 QQ,WinXin -> Phone,Taobao,是否都带过来，目前不允许这样的处理,校验 当前账号对应的用户有多个账号，判断是否与目标用户下有重复的账号
		return isBinded;
	}

	@Override
	public void convertDataFromOld2New(BigInteger oldUserId,BigInteger oldRoomId,BigInteger newUserId,BigInteger newRoomId) {
		//数据转移
		//将old门牌下的抽奖折扣转移到新门牌下
		this.transferPrizeRecordFromOld2New(oldUserId, oldRoomId, newUserId, newRoomId);
		//syl-add 将old门牌下的意外惊喜转移到新门牌下
		this.convertSurpriseGiftDataByRoomId(oldUserId, oldRoomId, newUserId, newRoomId);
		//syl-add 2015-5-15 16:34:41 将old门牌下的神马粮票转移到新门牌下
		this.convertShenMaHbOld2New(oldUserId, oldRoomId, newUserId, newRoomId);
	}

}
