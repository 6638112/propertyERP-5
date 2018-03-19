package com.cnfantasia.server.ms.inviteReward.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

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
import com.cnfantasia.server.domainbase.payRedEnvelope.dao.IPayRedEnvelopeBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.ms.inviteReward.constant.InviteRewardConstant;
import com.cnfantasia.server.ms.inviteReward.dao.IInviteRewardDao;
import com.cnfantasia.server.ms.inviteReward.entity.InviteRewardConfigEntity;
import com.cnfantasia.server.ms.inviteReward.entity.InviteUserEntity;
import com.cnfantasia.server.ms.notice.constant.LoginDict;
import com.cnfantasia.server.ms.notice.constant.NoticeDict;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;

public class InviteRewardService implements IInviteRewardService {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private IInviteRewardConfigBaseDao inviteRewardConfigBaseDao;
	private IInviteRewardRelationBaseDao inviteRewardRelationBaseDao;
	private IInviteRewardRecordBaseDao inviteRewardRecordBaseDao;
	private IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao; 
	private IInviteRewardDao inviteRewardDao;
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

	public IPayRedEnvelopeBaseDao getPayRedEnvelopeBaseDao() {
		return payRedEnvelopeBaseDao;
	}

	public void setPayRedEnvelopeBaseDao(IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao) {
		this.payRedEnvelopeBaseDao = payRedEnvelopeBaseDao;
	}

	public IInviteRewardDao getInviteRewardDao() {
		return inviteRewardDao;
	}

	public void setInviteRewardDao(IInviteRewardDao inviteRewardDao) {
		this.inviteRewardDao = inviteRewardDao;
	}
	//ICommonRoomService commonRoomService;//房号
	
	@Resource
	IMessageBaseDao messageBaseDao;
	
	@Resource
	IUserHasTMessageBaseDao userHasTMessageBaseDao;
	
	@Resource
	IUserBaseDao userBaseDao;
	
	@Resource
	ISysParamManager sysParamManager;

	@Override
	public void excuteInviteRewardForRegister(BigInteger registerUserId, String inviteNo) {
		try{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("inviteCode", inviteNo);
			List<InviteRewardConfig> configs = this.inviteRewardConfigBaseDao.selectInviteRewardConfigByCondition(paramMap, true);
			if(null!=configs && configs.size()>0){
				InviteRewardConfig config = configs.get(0);
				//1.创建邀请奖励关系记录
				InviteRewardRelation relation = new InviteRewardRelation();
				relation.setRelationTime(CnfantasiaCommUtil.getCurrentTime());
				relation.settInviteUserFId(config.gettInviteUserFId());
				relation.settRegisterUserFId(registerUserId);
				CnfantasiaCommUtil.newStandard(relation, SEQConstants.t_invite_reward_relation);
				if(this.inviteRewardRelationBaseDao.insertInviteRewardRelation(relation)>0){
					//2.给邀请人发注册粮票
					BigInteger inviteRoomId = CnfantasiaCommUtil.getUserDefaultRoomId(relation.gettInviteUserFId());
					if(null!=inviteRoomId){
						//2-1.给邀请人邀请奖励记录
						Long inviteBonus = getInviteRewardBonus(InviteRewardConstant.Config_Type.BONUS_1);
						InviteRewardRecord inviteRecord = new InviteRewardRecord();
						inviteRecord.setRecordBonus(inviteBonus*100);
						inviteRecord.setRecordTime(CnfantasiaCommUtil.getCurrentTime());
						inviteRecord.settInviteRewardRelationFId(relation.getId());
						inviteRecord.setRecordType(InviteRewardConstant.Record_Type.IRW_1);
						inviteRecord.setUserId(relation.gettInviteUserFId());//邀请人ID
						inviteRecord.setRoomId(inviteRoomId);//邀请人房号ID
						CnfantasiaCommUtil.newStandard(inviteRecord, SEQConstants.t_invite_reward_record);
						if(this.inviteRewardRecordBaseDao.insertInviteRewardRecord(inviteRecord)>0){//邀请人粮票记录
							this.addRedenvelope(relation.gettInviteUserFId(), inviteRoomId, inviteRecord.getId(), 
									InviteRewardConstant.PayRedEnvelope_FromType.SM_ACTION, inviteBonus*100);
						}
						//2-2.发送系统通知消息
						StringBuilder inviteMsgContent = new StringBuilder();//邀请方消息内容
						inviteMsgContent.append("恭喜您成功邀请1名新会员【").append(registerUserId).append("】，获得");
						inviteMsgContent.append(inviteBonus).append("元粮票！");
						inviteMsgContent.append("粮票在解放区超市购物可累计使用，无使用门槛！");
						inviteMsgContent.append("如果新用户在30天内上传物业费账单并通过验证，你还能再获得5元粮票。");
						Message inviteMsg = this.packingMessage(relation.gettInviteUserFId(), "恭喜获得"+inviteBonus+"元粮票！", inviteMsgContent.toString(), NoticeDict.Message_Type.SM_Redenvelope);
						if(messageBaseDao.insertMessage(inviteMsg)>0){
							this.insertUserHasMessage(inviteMsg.getId(), relation.gettInviteUserFId(), inviteRoomId);
						}
					}
					//3.给注册新人发注册粮票
					BigInteger registerRoomId = CnfantasiaCommUtil.getUserDefaultRoomId(registerUserId);
					if(null!=registerRoomId){
						//3-1.给注册新人邀请奖励记录
						Long registerBonus = getInviteRewardBonus(InviteRewardConstant.Config_Type.BONUS_2);
						InviteRewardRecord registerRecord = new InviteRewardRecord();
						registerRecord.setRecordBonus(registerBonus*100);
						registerRecord.setRecordTime(CnfantasiaCommUtil.getCurrentTime());
						registerRecord.settInviteRewardRelationFId(relation.getId());
						registerRecord.setRecordType(InviteRewardConstant.Record_Type.RW_3);
						registerRecord.setUserId(registerUserId);//注册人ID
						registerRecord.setRoomId(registerRoomId);//注册人房号ID
						CnfantasiaCommUtil.newStandard(registerRecord, SEQConstants.t_invite_reward_record);
						if(this.inviteRewardRecordBaseDao.insertInviteRewardRecord(registerRecord)>0){//注册人粮票记录
							this.addRedenvelope(registerUserId, registerRoomId, registerRecord.getId(), 
									InviteRewardConstant.PayRedEnvelope_FromType.SM_ACTION, registerBonus*100);
						}
						//3-2.发送系统通知消息
						StringBuilder registerMsgContent = new StringBuilder();//注册方消息内容
						registerMsgContent.append("恭喜您受到码神邀请，获得");
						registerMsgContent.append(registerBonus).append("元粮票！");
						registerMsgContent.append("粮票在解放区超市购物可累计使用，无使用门槛！");
						registerMsgContent.append("注册30天内上传自己的物业费账单，还能按账单上金额，享受每月物业费折扣返粮票福利！");
						Message registerMsg = this.packingMessage(registerUserId, "恭喜获得"+registerBonus+"元粮票！", registerMsgContent.toString(), NoticeDict.Message_Type.SM_Redenvelope);
						if(messageBaseDao.insertMessage(registerMsg)>0){
							this.insertUserHasMessage(registerMsg.getId(), registerUserId, registerRoomId);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
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
	@Transactional
	@Override
	public void excuteInviteRewardForVerifyRoom(BigInteger registerUserId,
			BigInteger roomId) {
		try{
			//验证用户是否已经有验证门牌的奖励，第一次肯定是没有的，如果有了则不能再次派奖
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tRegisterUserFId", registerUserId);//注册用户
			paramMap.put("recordType", InviteRewardConstant.Record_Type.VRW_4);
			if(this.inviteRewardDao.query_registerInviteRewardRecord_forCount(paramMap)>0){
				return;
			}
			
			//处理玫瑰园的小区业主没有门牌奖励2015-07-03黄湛江
			paramMap.clear();
			paramMap.put("userId", registerUserId);//注册用户
			paramMap.put("inviteType", InviteRewardConstant.Invite_Type.Invite_4);
			if(this.inviteRewardDao.query_registerUserIsRG_forCount(paramMap)>0){
				return ;
			}
			
			paramMap.clear();
			paramMap.put("tRegisterUserFId", registerUserId);
			List<InviteRewardRelation> relations = this.inviteRewardRelationBaseDao.selectInviteRewardRelationByCondition(paramMap, true);
			if(null!=relations && relations.size()>0){
				InviteRewardRelation relation = relations.get(0);
				//1.给邀请人发验证门牌粮票
				BigInteger inviteRoomId = CnfantasiaCommUtil.getUserDefaultRoomId(relation.gettInviteUserFId());
				if(null!=inviteRoomId){
					Long inviteBonus = getInviteRewardBonus(InviteRewardConstant.Config_Type.BONUS_3);
					InviteRewardRecord inviteRecord = new InviteRewardRecord();
					inviteRecord.setRecordBonus(inviteBonus*100);
					inviteRecord.setRecordTime(CnfantasiaCommUtil.getCurrentTime());
					inviteRecord.settInviteRewardRelationFId(relation.getId());
					inviteRecord.setRecordType(InviteRewardConstant.Record_Type.IVRW_2);
					inviteRecord.setUserId(relation.gettInviteUserFId());//邀请人ID
					inviteRecord.setRoomId(inviteRoomId);
					CnfantasiaCommUtil.newStandard(inviteRecord, SEQConstants.t_invite_reward_record);
					if(this.inviteRewardRecordBaseDao.insertInviteRewardRecord(inviteRecord)>0){//邀请人粮票记录
						this.addRedenvelope(relation.gettInviteUserFId(), inviteRoomId, inviteRecord.getId(), 
									InviteRewardConstant.PayRedEnvelope_FromType.SM_ACTION, inviteBonus*100);
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
				}
				//2.给注册新人发验证门牌粮票
				/*Long registerBonus = getInviteRewardBonus(InviteRewardConstant.Config_Type.BONUS_4);
				InviteRewardRecord registerRecord = new InviteRewardRecord();
				registerRecord.setRecordBonus(registerBonus);
				registerRecord.setRecordTime(CnfantasiaCommUtil.getCurrentTime());
				registerRecord.settInviteRewardRelationFId(relation.getId());
				registerRecord.setRecordType(InviteRewardConstant.Record_Type.VRW_4);
				registerRecord.setUserId(registerUserId);//注册人ID
				registerRecord.setRoomId(roomId);
				CnfantasiaCommUtil.newStandard(registerRecord, SEQConstants.t_invite_reward_record);
				if(this.inviteRewardRecordBaseDao.insertInviteRewardRecord(registerRecord)>0){//注册人粮票记录
					this.addRedenvelope(registerUserId, roomId, registerRecord.getId(), 
								InviteRewardConstant.PayRedEnvelope_FromType.SM_ACTION, registerBonus);
				}*/
				//2-2.给注册新人发送系统通知消息
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
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入粮票记录
	 * */
	private void addRedenvelope(BigInteger userId,BigInteger roomId,BigInteger fromId,Integer fromType,Long moneyLong){
		PayRedEnvelope payRedEnvelope = new PayRedEnvelope();
		payRedEnvelope.setAmountAvaible(moneyLong);
		payRedEnvelope.setAmountTotal(moneyLong);
		payRedEnvelope.setFromId(fromId);
		payRedEnvelope.setFromTime(CnfantasiaCommUtil.getCurrentTime());
		payRedEnvelope.setFromType(fromType);// 来源类型
		payRedEnvelope.setOutDate(null);// 不失效
		payRedEnvelope.setStatus(InviteRewardConstant.PayRedEnvelope_Status.CAN_USE);
		payRedEnvelope.setUserId(userId);// 拥有者
		payRedEnvelope.setRoomId(roomId);
		payRedEnvelope.setConverterId(userId);// 兑换人
		CnfantasiaCommUtil.newStandard(payRedEnvelope, SEQConstants.t_pay_red_envelope);
		int res = payRedEnvelopeBaseDao.insertPayRedEnvelope(payRedEnvelope);
		if (res <= 0) {
			throw new BusinessRuntimeException("InviteRewardService.addPayRedEnvelope.insertPayRedEnvelope.failed");
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
			messageAdd.setTime(CnfantasiaCommUtil.getCurrentTime());
			messageAdd.setTitle(title);
			messageAdd.setType(msgType);
			CnfantasiaCommUtil.newStandard(messageAdd, SEQConstants.t_message);
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
		CnfantasiaCommUtil.newStandard(tmpUserHasTMessage, SEQConstants.t_user_has_t_message);
		userHasTMessageBaseDao.insertUserHasTMessage(tmpUserHasTMessage);
	}

	@Override
	public List<InviteRewardConfigEntity> query_InviteRewardConfig_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return inviteRewardDao.query_InviteRewardConfig_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int query_InviteRewardConfig_forCount(Map<String, Object> paramMap) {
		return inviteRewardDao.query_InviteRewardConfig_forCount(paramMap);
	}

	@Override
	public InviteRewardConfigEntity query_InviteRewardConfig_byId(BigInteger id) {
		return inviteRewardDao.query_InviteRewardConfig_byId(id);
	}

	@Override
	public int saveOrUpdate_InviteRewardConfig(String id, String inviteUserId, String inviteCode, String inviteType) {
		int i = 0;
		try{
			InviteRewardConfig config = new InviteRewardConfig();
			if(!StringUtils.isEmpty(id)){
				config = this.inviteRewardConfigBaseDao.selectInviteRewardConfigBySeqId(CnfantasiaCommUtil.convert2BigInteger(id));
				CnfantasiaCommUtil.updateStandard(config);
			}else{
				CnfantasiaCommUtil.newStandard(config, SEQConstants.t_invite_reward_config);
			}
			config.settInviteUserFId(CnfantasiaCommUtil.convert2BigInteger(inviteUserId));
			config.setInviteCode(inviteCode);
			config.setInviteType(CnfantasiaCommUtil.convert2Integer(inviteType));
			if(!StringUtils.isEmpty(id)){
				i = this.inviteRewardConfigBaseDao.updateInviteRewardConfig(config);
			}else{
				i = this.inviteRewardConfigBaseDao.insertInviteRewardConfig(config);
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<InviteUserEntity> query_InviteUserList(Map<String, Object> paramMap) {
		return inviteRewardDao.query_InviteUserList(paramMap);
	}

	@Override
	public int enableOrDisableConfig(String id, boolean isEnable) {
		int i = 0;
		try{
			InviteRewardConfig config = this.query_InviteRewardConfig_byId(CnfantasiaCommUtil.convert2BigInteger(id));
			if(null!=config){
				CnfantasiaCommUtil.updateStandard(config);
				if(isEnable==false){
					config.setSys0DelState(1);
				}
				i = this.inviteRewardConfigBaseDao.updateInviteRewardConfig(config);
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int query_InviteRewardConfig_isRepeat(Map<String, Object> paramMap) {
		return inviteRewardDao.query_InviteRewardConfig_isRepeat(paramMap);
	}
}
