 /**
 * 
 */
package com.cnfantasia.server.api.notice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.notice.dao.INoticeDao;
import com.cnfantasia.server.api.notice.entity.MessageWithReadStatusEntity;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.nuomi.entity.NoticeMonth;
import com.cnfantasia.server.api.redpoint.callable.RedpointAddRunnableMulti;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.message.dao.IMessageBaseDao;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;
import com.cnfantasia.server.domainbase.messageParameter.dao.IMessageParameterBaseDao;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

/**
 * 类说明：
 * 
 * @author hunter
 * @since 2014年6月4日下午3:44:32
 */
public class NoticeService implements INoticeService {

	private INoticeDao noticeDao;
	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	private IMessageBaseDao messageBaseDao;
	public void setMessageBaseDao(IMessageBaseDao messageBaseDao) {
		this.messageBaseDao = messageBaseDao;
	}
	
	private IMessageParameterBaseDao messageParameterBaseDao;

	public void setMessageParameterBaseDao(IMessageParameterBaseDao messageParameterBaseDao) {
		this.messageParameterBaseDao = messageParameterBaseDao;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IUserHasTMessageBaseDao userHasTMessageBaseDao;
	public void setUserHasTMessageBaseDao(IUserHasTMessageBaseDao userHasTMessageBaseDao) {
		this.userHasTMessageBaseDao = userHasTMessageBaseDao;
	}
	
	private IRedpointService redpointService;
	public void setRedpointService(IRedpointService redpointService) {
		this.redpointService = redpointService;
	}
	
	@Override
	public List<NoticeBean> queryPropertyNoticeList(PageModel pageModel, BigInteger userId) {
		return noticeDao.selectAllNoticeByUserid(pageModel, userId);
	}

	@Override
	public List<NoticeBean> queryAddressByMsgid(BigInteger msgId) {
		return noticeDao.selectAddresssByMessageId(msgId);
	}

	// @Override
	// public int saveNoticeMessage(Message message,String[] messageGroups) throws
	// BusinessRuntimeException{
	// try{
	// BigInteger messageId =
	// uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
	// message.setId(messageId);
	// int messageRow = messageDao.insertMessage(message);
	// int groupRow = 0;
	// for(String groupId:messageGroups){
	// BigInteger messageGroupId =
	// uuidManager.getNextUuidBigInteger(SEQConstants.t_message_group);
	// MessageGroup mg = new MessageGroup();
	// mg.setId(messageGroupId);
	// mg.settMessageFId(messageId);
	// mg.setGroupbuildingId(new BigInteger(groupId.trim()));
	// groupRow += messageGroupBaseDao.insertMessageGroup(mg);
	// }
	// return messageRow !=0 && groupRow != 0 ?1:0;
	// }catch(Exception e){
	// logger.error(e);
	// throw new BusinessRuntimeException(e);
	// }
	// }

	@Override
	public int saveNoticeMessage(Message message, String[] messageGroups) throws BusinessRuntimeException {
		int resCount = 0;
		if (messageGroups != null && messageGroups.length > 0) {
			List<BigInteger> groupBIds = new ArrayList<BigInteger>();
			for (String idStr : messageGroups) {
				groupBIds.add(new BigInteger(idStr.trim()));
			}
			// 查询小区下的用户列表
			List<UserSimpleEntity> userList = commonRoomService.getUserListByGroupBuildIds(groupBIds);
			// 新增消息
			BigInteger messageId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
			message.setId(messageId);
			int messageRow = messageBaseDao.insertMessage(message);
			
			MessageParameter tmpMessageParameter = new MessageParameter();
	        tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
	        tmpMessageParameter.setKey("pushId");
	        tmpMessageParameter.settMessageFId(message.getId());
	        tmpMessageParameter.setValue(MsgpushDict.PushId.WyNotice);
	        messageParameterBaseDao.insertMessageParameter(tmpMessageParameter);
			if (messageRow <= 0) {
				throw new BusinessRuntimeException("messageService.insertMessage.failed");
			}
			if (userList != null && userList.size() > 0) {// 新增用户消息关系
				List<UserHasTMessage> userHasTMessageAddList = new ArrayList<UserHasTMessage>();
				List<BigInteger> addIdsList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message,
						userList.size());
				List<CommUserDataEntity> commUserDataEntityList = new ArrayList<CommUserDataEntity>();
				for (int i = 0; i < userList.size(); i++) {
					UserSimpleEntity tmpUserSimpleEntity = userList.get(i);
					BigInteger userHasTMessageAddId = addIdsList.get(i);
					BigInteger tmpUserId = tmpUserSimpleEntity.getId();
					Integer tmpUserType = LoginDict.UserRegistOrTmp.REGIST_USER;// 已注册的用户
					BigInteger tmpDefaultRoomId = tmpUserSimpleEntity.getDefaultRoomId();

					UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
					tmpUserHasTMessage.setId(userHasTMessageAddId);
					tmpUserHasTMessage.setTryFailedCount(0L);
					tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
					tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
					tmpUserHasTMessage.settMessageFId(messageId);
					tmpUserHasTMessage.settUserFId(tmpUserId);
					tmpUserHasTMessage.setUserType(tmpUserType);
					tmpUserHasTMessage.setUserRoomId(tmpDefaultRoomId);
					userHasTMessageAddList.add(tmpUserHasTMessage);

					// 红点信息准备
					CommUserDataEntity tmpCommUserDataEntity = new CommUserDataEntity(tmpUserId, tmpUserType, tmpDefaultRoomId);
					commUserDataEntityList.add(tmpCommUserDataEntity);
				}
				userHasTMessageBaseDao.insertUserHasTMessageBatch(userHasTMessageAddList);

				{// 添加红点提醒
					List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
					sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.SystemMessage, messageId,
							"INSERT"));
					FutureTask<Boolean> task = new FutureTask<Boolean>(new RedpointAddRunnableMulti(redpointService,
							commUserDataEntityList, RedpointConstant.RedpointContent_ModelCode.SYS_NOTICE, sourceList));
					new Thread(task).start();
				}

			}
			// 根据消息类型设定消息的视图
			resCount = 1;
		}
		return resCount;
	}

	@Override
	public List<MessageGroup> getGroupBuildingByMsgid(BigInteger msgId) {
		return noticeDao.selectGroupBuildingByMsgid(msgId);
	}
	
	@Override
	public List<MessageWithReadStatusEntity> querySystemMessageList(BigInteger userId,PageModel pageModel) {
		return noticeDao.selectSystemMessageList(userId,pageModel);
	}

	@Override
	public String queryMobilePhone(BigInteger pcId) {
		return noticeDao.selectmobilePhone(pcId);
	}

	@Override
	public List<NoticeBean> queryMsgForNewUser(BigInteger gbId,BigInteger uId) {
		return noticeDao.selectForNewUser(gbId, uId);
	}

	@Override
	public Map<String, Object> queryGroupBuildingSignAndSummon(BigInteger userId) {
		//查询用户默认门牌Id
		BigInteger defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);

		Map<String, Object> resMap = noticeDao.queryGroupBuildingSignAndSummon(defaultRoomId, userId);

		boolean signStatus = resMap.get("signStatus") != null && isSign((Integer) resMap.get("signStatus"));
		resMap.put("signStatus",signStatus);
		boolean haveSummon = resMap.get("haveSummon") != null ? isSummon(((Long) resMap.get("haveSummon")).intValue()) : false;
		resMap.put("haveSummon",haveSummon);

		return resMap;
	}

	/**
	 * 转换小区签约状态值
	 * @param signStatus
	 * @return
     */
	private boolean isSign(Integer signStatus){
		return signStatus != null && signStatus.compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN) == 0;
	}

	/**
	 * 用户对小区是否已点击召唤 状态值转换
	 * @param haveSummon
	 * @return
     */
	private Boolean isSummon(Integer haveSummon) {
		return haveSummon != null && GroupBuildingDict.GroupBuilding_IsSummon.YES.compareTo(haveSummon) == 0;
	}
	
	/**
	 * 查询公告列表（糯米）
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<NoticeMonth> selectNoticeListForNuomi(Map<String,Object> paramMap){
		return noticeDao.selectNoticeListForNuomi(paramMap);
	}
	
	/**
	 * 查询公告数量（糯米）
	 * @param paramMap
	 * @return
	 */
	@Override
	public Integer selectNoticeCountForNuomi(Map<String,Object> paramMap){
		return noticeDao.selectNoticeCountForNuomi(paramMap);
	}

	
}
