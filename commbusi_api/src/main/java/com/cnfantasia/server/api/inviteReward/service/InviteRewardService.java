package com.cnfantasia.server.api.inviteReward.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.inviteReward.constant.InviteRewardConstant;
import com.cnfantasia.server.api.inviteReward.dao.IInviteRewardDao;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.service.IRedenvelopeService;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.inviteRewardConfig.dao.IInviteRewardConfigBaseDao;
import com.cnfantasia.server.domainbase.inviteRewardConfig.entity.InviteRewardConfig;
import com.cnfantasia.server.domainbase.inviteRewardRecord.dao.IInviteRewardRecordBaseDao;
import com.cnfantasia.server.domainbase.inviteRewardRecord.entity.InviteRewardRecord;
import com.cnfantasia.server.domainbase.inviteRewardRelation.dao.IInviteRewardRelationBaseDao;
import com.cnfantasia.server.domainbase.inviteRewardRelation.entity.InviteRewardRelation;
import com.cnfantasia.server.domainbase.message.dao.IMessageBaseDao;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

public class InviteRewardService implements IInviteRewardService {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private IInviteRewardConfigBaseDao inviteRewardConfigBaseDao;
	private IInviteRewardRelationBaseDao inviteRewardRelationBaseDao;
	private IInviteRewardRecordBaseDao inviteRewardRecordBaseDao;
	private IRedenvelopeService redenvelopeService;
	private ISysParamManager sysParamManager;
	public IInviteRewardConfigBaseDao getInviteRewardConfigBaseDao() {
		return inviteRewardConfigBaseDao;
	}

	public void setInviteRewardConfigBaseDao(
			IInviteRewardConfigBaseDao inviteRewardConfigBaseDao) {
		this.inviteRewardConfigBaseDao = inviteRewardConfigBaseDao;
	}

	public IInviteRewardRelationBaseDao getInviteRewardRelationBaseDao() {
		return inviteRewardRelationBaseDao;
	}

	public void setInviteRewardRelationBaseDao(
			IInviteRewardRelationBaseDao inviteRewardRelationBaseDao) {
		this.inviteRewardRelationBaseDao = inviteRewardRelationBaseDao;
	}

	public IInviteRewardRecordBaseDao getInviteRewardRecordBaseDao() {
		return inviteRewardRecordBaseDao;
	}

	public void setInviteRewardRecordBaseDao(
			IInviteRewardRecordBaseDao inviteRewardRecordBaseDao) {
		this.inviteRewardRecordBaseDao = inviteRewardRecordBaseDao;
	}

	public IRedenvelopeService getRedenvelopeService() {
		return redenvelopeService;
	}

	public void setRedenvelopeService(IRedenvelopeService redenvelopeService) {
		this.redenvelopeService = redenvelopeService;
	}

	public ISysParamManager getSysParamManager() {
		return sysParamManager;
	}

	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private ICommonRoomService commonRoomService;//房号
	private IMessageBaseDao messageBaseDao;//系统消息
	private IUserHasTMessageBaseDao userHasTMessageBaseDao;//用户关联消息
	
	public ICommonRoomService getCommonRoomService() {
		return commonRoomService;
	}

	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	public IMessageBaseDao getMessageBaseDao() {
		return messageBaseDao;
	}

	public void setMessageBaseDao(IMessageBaseDao messageBaseDao) {
		this.messageBaseDao = messageBaseDao;
	}

	public IUserHasTMessageBaseDao getUserHasTMessageBaseDao() {
		return userHasTMessageBaseDao;
	}

	public void setUserHasTMessageBaseDao(
			IUserHasTMessageBaseDao userHasTMessageBaseDao) {
		this.userHasTMessageBaseDao = userHasTMessageBaseDao;
	}
	private IInviteRewardDao inviteRewardDao;
	
	public void setInviteRewardDao(IInviteRewardDao inviteRewardDao) {
		this.inviteRewardDao = inviteRewardDao;
	}

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public void excuteInviteRewardForRegister(BigInteger registerUserId, String inviteNo, String mobile) {
		try{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("inviteCode", inviteNo);
			List<InviteRewardConfig> configs = this.inviteRewardConfigBaseDao.selectInviteRewardConfigByCondition(paramMap, true);
			if(null!=configs && configs.size()>0){
				InviteRewardConfig config = configs.get(0);
				if(config.getInviteType()==InviteRewardConstant.Invite_Type.Invite_4){//玫瑰园小区特殊处理
					this.excuteForRoseGraden(config, registerUserId, inviteNo, mobile);
				}else{//派奖
					this.excuteInviteReward(config, config.gettInviteUserFId(), registerUserId,
							InviteRewardConstant.Config_Type.BONUS_1, InviteRewardConstant.Config_Type.BONUS_2);
				}
			}
		}catch(Exception e){
			logger.error(e);
			throw new BusinessRuntimeException(e);
		}
	}
	
	/**
	 * 获取奖励粮票值
	 * */
	private Long getInviteRewardBonus(String bonusKey){
		String bonus = sysParamManager.getSysParaValue(bonusKey);
		if(!StringUtils.isEmpty(bonus)){
			return Long.valueOf(bonus);
		}else{
			return null;
		}
	}
	
	public Map<String, Object> appendParam(String gbName,BigInteger blockId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbName", gbName);
		tmpMap.put("blockId", blockId);
		return tmpMap;
	}
	/**
	 * 玫瑰园小区派奖
	 * 只有当邀请和被邀请双方都属于玫瑰园小区的业主才行（物业提供了玫瑰园的业主信息）
	 * */
	private void excuteForRoseGraden(InviteRewardConfig config, BigInteger registerUserId, String inviteNo, String mobile){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//1.验证验证邀请的手机是否注册
		paramMap.put("userPhone", inviteNo);
		paramMap.put("loginType", LoginDict.AccountType.MOBILE);
		BigInteger userId = this.inviteRewardDao.query_userId_forInvitePhone(paramMap);
		if(null!=userId){
			//2.验证邀请人是否有添加玫瑰园小区的房号
			boolean isExist = false;
			{
				List<Map<String,Object>> paramMapList = new ArrayList<Map<String,Object>>();
				paramMapList.add(appendParam("玫瑰园", new BigInteger("2150")));
				paramMapList.add(appendParam("文化小区", new BigInteger("5157")));
				paramMapList.add(appendParam("合兴花园", new BigInteger("5157")));
				paramMapList.add(appendParam("吉利家园", new BigInteger("5157")));
				paramMapList.add(appendParam("城市峰景", new BigInteger("5157")));
				paramMapList.add(appendParam("碧水庄园", new BigInteger("5157")));
				paramMapList.add(appendParam("馨家园", new BigInteger("5157")));
				isExist = commonRoomService.queryUserIsRegisterGroupbuilding(userId,paramMapList);
			}
//			{//玫瑰园
//				paramMap.clear();
//				paramMap.put("userId", userId);
//				paramMap.put("gbName", "玫瑰园");
//				paramMap.put("blockId", 2150);//深圳-南山区
//				List<GroupBuildingEntity> gbList = commonRoomService.queryUserIsRegisterGroupbuilding(paramMap);
//				if(null!=gbList && gbList.size()>0){
//					isExist = true;
//				}
//			}
			if(isExist){
				//3.验证注册用户手机是否是玫瑰园小区用户
				paramMap.clear();
				paramMap.put("inviteCode", mobile);
				paramMap.put("inviteType", InviteRewardConstant.Invite_Type.Invite_4);
				if(this.inviteRewardConfigBaseDao.selectInviteRewardConfigCount(paramMap, false)>0){
					//4.给双方派发粮票
					this.excuteInviteReward(config, userId, registerUserId,
							InviteRewardConstant.Config_Type.MGY_REG1, InviteRewardConstant.Config_Type.MGY_REG2);
				}
			}
		}
	}
	
	/**
	 * 邀请派奖操作
	 * @param config
	 * @param inviteUserId
	 * @param registerUserId
	 * @param inviteBonusConstant
	 * @param registerBonusConstant
	 * */
	private void excuteInviteReward(InviteRewardConfig config, BigInteger inviteUserId, BigInteger registerUserId, 
			String inviteBonusConstant, String registerBonusConstant){
		//1.创建邀请奖励关系记录
		InviteRewardRelation relation = new InviteRewardRelation();
		relation.setRelationTime(CnfantasiaCommbusiUtil.getCurrentTime());
		relation.settInviteUserFId(inviteUserId);
		relation.settRegisterUserFId(registerUserId);
		CnfantasiaCommbusiUtil.newStandard(relation, SEQConstants.t_invite_reward_relation);
		if(this.inviteRewardRelationBaseDao.insertInviteRewardRelation(relation)>0){
			//2.给邀请人发注册粮票
			BigInteger inviteRoomId = this.commonRoomService.getDefaultRoomIdByUserIdAllowNull(relation.gettInviteUserFId());
			if(null!=inviteRoomId){
				//2-1.给邀请人邀请奖励记录
				Long inviteBonus = getInviteRewardBonus(inviteBonusConstant);
				InviteRewardRecord inviteRecord = new InviteRewardRecord();
				inviteRecord.setRecordBonus(inviteBonus*100);
				inviteRecord.setRecordTime(CnfantasiaCommbusiUtil.getCurrentTime());
				inviteRecord.settInviteRewardRelationFId(relation.getId());
				inviteRecord.setRecordType(InviteRewardConstant.Record_Type.IRW_1);
				inviteRecord.setUserId(relation.gettInviteUserFId());//邀请人ID
				inviteRecord.setRoomId(inviteRoomId);//邀请人房号ID
				CnfantasiaCommbusiUtil.newStandard(inviteRecord, SEQConstants.t_invite_reward_record);
				if(this.inviteRewardRecordBaseDao.insertInviteRewardRecord(inviteRecord)>0){//邀请人粮票记录
					this.redenvelopeService.appendShenmaHb(relation.gettInviteUserFId(), inviteRoomId, inviteRecord.getId(), inviteBonus*100);
				}
				//2-2.发送系统通知消息
				StringBuilder inviteMsgContent = new StringBuilder();//邀请方消息内容
				inviteMsgContent.append("恭喜您成功邀请1名新会员【").append(registerUserId).append("】，获得");
				inviteMsgContent.append(inviteBonus).append("元粮票！");
				inviteMsgContent.append("粮票在解放区超市购物可累计使用，无使用门槛！");
				if(inviteBonusConstant.equals(InviteRewardConstant.Config_Type.MGY_REG1)){//玫瑰园文案修改
					inviteMsgContent.append("再接再厉，爆发小宇宙，邀请下一位新用户！");
				}else{
					inviteMsgContent.append("如果新用户在30天内上传物业费账单并通过验证，你还能再获得5元粮票。");
				}
				Message inviteMsg = this.packingMessage(relation.gettInviteUserFId(), "恭喜获得"+inviteBonus+"元粮票！", inviteMsgContent.toString(), NoticeDict.Message_Type.SM_Redenvelope);
				if(messageBaseDao.insertMessage(inviteMsg)>0){
					this.insertUserHasMessage(inviteMsg.getId(), relation.gettInviteUserFId(), inviteRoomId);
				}
			}
			//3.给注册新人发注册粮票
			BigInteger registerRoomId = this.commonRoomService.getDefaultRoomIdByUserIdAllowNull(registerUserId);
			if(null!=registerRoomId){
				//3-1.给注册新人邀请奖励记录
				Long registerBonus = getInviteRewardBonus(registerBonusConstant);
				InviteRewardRecord registerRecord = new InviteRewardRecord();
				registerRecord.setRecordBonus(registerBonus*100);
				registerRecord.setRecordTime(CnfantasiaCommbusiUtil.getCurrentTime());
				registerRecord.settInviteRewardRelationFId(relation.getId());
				registerRecord.setRecordType(InviteRewardConstant.Record_Type.RW_3);
				registerRecord.setUserId(registerUserId);//注册人ID
				registerRecord.setRoomId(registerRoomId);//注册人房号ID
				CnfantasiaCommbusiUtil.newStandard(registerRecord, SEQConstants.t_invite_reward_record);
				if(this.inviteRewardRecordBaseDao.insertInviteRewardRecord(registerRecord)>0){//注册人粮票记录
					this.redenvelopeService.appendShenmaHb(registerUserId, registerRoomId, registerRecord.getId(), registerBonus*100);
				}
				//3-2.发送系统通知消息
				StringBuilder registerMsgContent = new StringBuilder();//注册方消息内容
				registerMsgContent.append("恭喜您受到码神邀请，获得");
				registerMsgContent.append(registerBonus).append("元粮票！");
				registerMsgContent.append("粮票在解放区超市购物可累计使用，无使用门槛！");
				if(registerBonusConstant.equals(InviteRewardConstant.Config_Type.MGY_REG2)){//玫瑰园文案修改
					registerMsgContent.append("您也可以邀请玫瑰园其他业主注册解放区，各得10元粮票哦。详情请咨询解放区客服。");
				}else{
					registerMsgContent.append("注册30天内上传自己的物业费账单，还能按账单上金额，享受每月物业费折扣返粮票福利！");
				}
				Message registerMsg = this.packingMessage(registerUserId, "恭喜获得"+registerBonus+"元粮票！", registerMsgContent.toString(), NoticeDict.Message_Type.SM_Redenvelope);
				if(messageBaseDao.insertMessage(registerMsg)>0){
					this.insertUserHasMessage(registerMsg.getId(), registerUserId, registerRoomId);
				}
			}
		}
	}
	
	@Override
	public void excuteInviteRewardForVerifyRoom(BigInteger registerUserId,
			BigInteger roomId) {
		try{
			//1.验证用户是否已经有验证门牌的奖励，第一次肯定是没有的，如果有了则不能再次派奖
			if(this.checkUserIsGetReward(registerUserId, InviteRewardConstant.Record_Type.VRW_4)){
				return;
			}
			if(this.excuteVerifyRoomReward(registerUserId)){//派奖成功
				//2.给注册新人发送系统通知消息
				StringBuilder inviteMsgContent = new StringBuilder();//邀请方消息内容
				inviteMsgContent.append("恭喜您门牌验证已成功，每月可以享受物业费折扣返粮票福利！");
				inviteMsgContent.append("粮票金额=（1-本月最低折扣）*物业费。");
				inviteMsgContent.append("如：300元物业费抽中5折，粮票金额=（1-0.5）*300=150元粮票在解放区超市购物可累计使用，无使用门槛！");
				Message inviteMsg = this.packingMessage(registerUserId, "恭喜门牌验证成功！", inviteMsgContent.toString(), NoticeDict.Message_Type.SM_Redenvelope);
				if(messageBaseDao.insertMessage(inviteMsg)>0){
					this.insertUserHasMessage(inviteMsg.getId(), registerUserId, roomId);
				}
			}
		}catch(Exception e){
			logger.error(e);
			throw new BusinessRuntimeException(e);
		}
	}
	
	/**
	 * 封装消息
	 * */
	private Message packingMessage(BigInteger userId, String title, String content,Long msgType){
		//新增一条消息
		Message messageAdd = null;
		{//组装消息信息
			messageAdd = new Message();
			messageAdd.setAndroidTargetView(null);
			messageAdd.setContent(content);
			messageAdd.setCreater(userId);
			messageAdd.setIosTargetView(null);
			messageAdd.setMsggroupFid(null);
			messageAdd.setPicUrl(null);
			messageAdd.setTime(CnfantasiaCommbusiUtil.getCurrentTime());
			messageAdd.setTitle(title);
			messageAdd.setType(msgType);
			CnfantasiaCommbusiUtil.newStandard(messageAdd, SEQConstants.t_message);
		}
		return messageAdd;
	}
	
	/**
	 * 插入用户关联消息记录
	 * */
	private void insertUserHasMessage(BigInteger messageId, BigInteger userId, BigInteger roomId){
		UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
		tmpUserHasTMessage.setTryFailedCount(0L);
		tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
		tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
		tmpUserHasTMessage.settMessageFId(messageId);
		tmpUserHasTMessage.settUserFId(userId);
		tmpUserHasTMessage.setUserType(LoginDict.UserRegistOrTmp.REGIST_USER);
		tmpUserHasTMessage.setExpiryTime(null);
		tmpUserHasTMessage.setUserRoomId(roomId);
		CnfantasiaCommbusiUtil.newStandard(tmpUserHasTMessage, SEQConstants.t_user_has_t_message);
		userHasTMessageBaseDao.insertUserHasTMessage(tmpUserHasTMessage);
	}
	
	/**
	 * 验证用户是否已经派奖了
	 * @param userId--注册新人ID
	 * @param rewradType--派奖类型
	 * */
	private boolean checkUserIsGetReward(BigInteger userId, int rewardType){
		boolean flag = false;
		//验证用户是否已经有验证门牌的奖励，第一次肯定是没有的，如果有了则不能再次派奖
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tRegisterUserFId", userId);//注册用户
		paramMap.put("recordType", rewardType);
		if(this.inviteRewardDao.query_registerInviteRewardRecord_forCount(paramMap)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 验证用户是否已经派奖过期
	 * @param userRegisterDate--用户注册时间
	 * @param overdueDateCostant--派奖类型
	 * */
	private boolean checkUserIsOverdueRewrad(String userRegisterDate, String overdueDateCostant){
		boolean flag = true;
		String overdueDate = sysParamManager.getSysParaValue(overdueDateCostant);
		try {
			if(!StringUtils.isEmpty(userRegisterDate)){
				Date registerDate = format.parse(userRegisterDate);
				if(registerDate.compareTo(format.parse(overdueDate))>=0){//用户注册时间对比系统设置的过期时间
					flag = false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 验证门牌派奖
	 * @param registerUserId--注册新人ID
	 * */
	private boolean excuteVerifyRoomReward(BigInteger registerUserId){
		boolean flag = false;
		try{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//处理玫瑰园的小区业主没有门牌奖励2015-07-03黄湛江
			paramMap.put("userId", registerUserId);//注册用户
			paramMap.put("inviteType", InviteRewardConstant.Invite_Type.Invite_4);
			if(this.inviteRewardDao.query_registerUserIsRG_forCount(paramMap)>0){
				return false;
			}
			paramMap.clear();
			paramMap.put("tRegisterUserFId", registerUserId);
			List<InviteRewardRelation> relations = this.inviteRewardRelationBaseDao.selectInviteRewardRelationByCondition(paramMap, true);
			if(null!=relations && relations.size()>0){
				InviteRewardRelation relation = relations.get(0);
				//1.给邀请人发验证门牌粮票
				BigInteger inviteRoomId = this.commonRoomService.getDefaultRoomIdByUserIdAllowNull(relation.gettInviteUserFId());
				if(null!=inviteRoomId){
					//-1.给邀请人验证门牌记录
					Long inviteBonus = getInviteRewardBonus(InviteRewardConstant.Config_Type.BONUS_3);
					InviteRewardRecord inviteRecord = new InviteRewardRecord();
					inviteRecord.setRecordBonus(inviteBonus*100);
					inviteRecord.setRecordTime(CnfantasiaCommbusiUtil.getCurrentTime());
					inviteRecord.settInviteRewardRelationFId(relation.getId());
					inviteRecord.setRecordType(InviteRewardConstant.Record_Type.IVRW_2);
					inviteRecord.setUserId(relation.gettInviteUserFId());//邀请人ID
					inviteRecord.setRoomId(inviteRoomId);
					CnfantasiaCommbusiUtil.newStandard(inviteRecord, SEQConstants.t_invite_reward_record);
					if(this.inviteRewardRecordBaseDao.insertInviteRewardRecord(inviteRecord)>0){//邀请人粮票记录
						this.redenvelopeService.appendShenmaHb(relation.gettInviteUserFId(), inviteRoomId, inviteRecord.getId(), inviteBonus*100);
					}
					//1-2.给邀请人发送系统通知消息
					StringBuilder inviteMsgContent = new StringBuilder();//邀请方消息内容
					inviteMsgContent.append("您邀请的新会员【").append(registerUserId).append("】已经成功验证门牌，您获得");
					inviteMsgContent.append(inviteBonus).append("元粮票！");
					inviteMsgContent.append("再接再厉，爆发小宇宙，邀请下一位新用户！");
					Message inviteMsg = this.packingMessage(relation.gettInviteUserFId(), "恭喜获得"+inviteBonus+"元粮票！", inviteMsgContent.toString(), NoticeDict.Message_Type.SM_Redenvelope);
					if(messageBaseDao.insertMessage(inviteMsg)>0){
						this.insertUserHasMessage(inviteMsg.getId(), relation.gettInviteUserFId(), inviteRoomId);
					}
					flag = true;//执行完成
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public void excuteInviteRewardForCompleteInviteNO(User user, String inviteNo) {
		//1.验证用户是否已经派过注册的奖励
		if(this.checkUserIsGetReward(user.getId(), InviteRewardConstant.Record_Type.RW_3)){
			return;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("inviteCode", inviteNo);
		List<InviteRewardConfig> configs = this.inviteRewardConfigBaseDao.selectInviteRewardConfigByCondition(paramMap, true);
		if(null!=configs && configs.size()>0){
			InviteRewardConfig config = configs.get(0);
			if(config.getInviteType()==InviteRewardConstant.Invite_Type.Invite_4){//玫瑰园小区特殊处理
				if(this.checkUserIsOverdueRewrad(user.getSys0AddTime(), InviteRewardConstant.MGY_Invite_Overdue_Date)){
					return;
				}
				this.excuteForRoseGraden(config, user.getId(), inviteNo, user.getMobile());
			}else{//补充派奖
				//1-2.验证是否用户过期，和系统设置的过期时间比
				if(this.checkUserIsOverdueRewrad(user.getSys0AddTime(), InviteRewardConstant.Invite_Overdue_Date)){
					return;
				}
				this.excuteInviteReward(config, config.gettInviteUserFId(), user.getId(),
						InviteRewardConstant.Config_Type.BONUS_1, InviteRewardConstant.Config_Type.BONUS_2);
			}
			//1-3.如果是玫瑰园的小区则不派验证门牌的奖励
			if(config.getInviteType()==InviteRewardConstant.Invite_Type.Invite_4){
				return;
			}else{
				//2.验证用户是否已经派过验证门牌的奖励
				if(this.checkUserIsGetReward(user.getId(), InviteRewardConstant.Record_Type.VRW_4)){
					return;
				}
				if(this.excuteVerifyRoomReward(user.getId())){//补充验证门牌奖励成功
					//是否给用户发送消息提醒，派奖消息内容是否需要修改
					BigInteger roomId = this.commonRoomService.getDefaultRoomIdByUserIdAllowNull(user.getId());
					if(roomId!=null){
						//2-2.给注册新人发送系统通知消息
						StringBuilder inviteMsgContent = new StringBuilder();//邀请方消息内容
						inviteMsgContent.append("恭喜您门牌验证已成功，每月可以享受物业费折扣返粮票福利！");
						inviteMsgContent.append("粮票金额=（1-本月最低折扣）*物业费。");
						inviteMsgContent.append("如：300元物业费抽中5折，粮票金额=（1-0.5）*300=150元粮票在解放区超市购物可累计使用，无使用门槛！");
						Message inviteMsg = this.packingMessage(user.getId(), "恭喜门牌验证成功！", inviteMsgContent.toString(), NoticeDict.Message_Type.SM_Redenvelope);
						if(messageBaseDao.insertMessage(inviteMsg)>0){
							this.insertUserHasMessage(inviteMsg.getId(), user.getId(), roomId);
						}
					}
				}
			}
		}
	}
}
