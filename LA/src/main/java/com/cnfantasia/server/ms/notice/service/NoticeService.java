package com.cnfantasia.server.ms.notice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.message.dao.IMessageBaseDao;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageGroup.dao.IMessageGroupBaseDao;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.constant.LoginDict;
import com.cnfantasia.server.ms.notice.constant.NoticeDict;
import com.cnfantasia.server.ms.notice.dao.INoticeDao;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class NoticeService implements INoticeService {
	private INoticeDao noticeDao; 
	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	private IMessageBaseDao messageBaseDao;
	public void setMessageBaseDao(IMessageBaseDao messageBaseDao) {
		this.messageBaseDao = messageBaseDao;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	private IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private IMessageGroupBaseDao messageGroupBaseDao;

	public void setMessageGroupBaseDao(IMessageGroupBaseDao messageGroupBaseDao) {
		this.messageGroupBaseDao = messageGroupBaseDao;
	}

	//	@Override
	//	public int insertMessage(Message message) {
	//		BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
	//		message.setId(addId);
	//		return super.insertMessage(message);
	//	}

	@Override
	public MessageEntity getMessageDetail(BigInteger messageId) {
		return noticeDao.selectMessageDetail(messageId);
	}

	@Override
	public void deleteMessageById(BigInteger messageId) {
		noticeDao.deleteMessageById(messageId);
	}


	@Override
	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(
			BigInteger userId) {
		return noticeDao.selectGroupBuildingSimpleList(userId);
	}

	@Override
	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(BigInteger userId, int msgId) {
		List<GroupBuildingSimpleEntity> gbList = noticeDao.selectGroupBuildingSimpleList(userId);
		List<Object> bgIds = noticeDao.select_GroupBuildingIds_byMsgId(msgId);
		for (int i = 0; i < bgIds.size(); i++)
			for (int j = 0; j < gbList.size(); j++) {
				long gbId = gbList.get(j).getId().longValue();
				if (gbId == (Long) bgIds.get(i)) {
					gbList.get(j).setIsPushed("yes");
				}
			}
		return gbList;
	}

	@Override
	public List<Message> searchNoticeList(Map<String, Object> paramMap) {
		return noticeDao.searchNoticeList(paramMap);
	}

	@Override
	public int delete_messageGroup_byMsgId(String msgId) {
		return noticeDao.delete_messageGroup_byMsgId(msgId);
	}

	@Override
	@Transactional
	public void saveNotice(String msgId, String title, String content, String pushWay, String pushTime, String[] gdIds) {
		BigInteger messageId = null;
		if ("pushNow".equals(pushWay)) {// 立即推送
			pushTime = dualDao.getNowTime();
		}

		Message msg = new Message();
		msg.setTitle(title);
		msg.setContent(content);
		msg.setTime(pushTime);

		if (!StringUtils.isEmpty(msgId)) {
			messageId = new BigInteger(msgId);
			msg.setId(messageId);
			messageBaseDao.updateMessage(msg);
		} else {
			// 新增消息
			msg.setType(3L);//type=3是物业公告
			messageId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
			msg.setId(messageId);
			msg.setSys0AddUser(UserContext.getCurrUser().getId());
			messageBaseDao.insertMessage(msg);
		}

		//先删除已有的，
		if (!StringUtils.isEmpty(msgId)) {
			noticeDao.delete_messageGroup_byMsgId(msgId);
		}

		int gdIdCount = gdIds.length;
		List<BigInteger> newIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_group, gdIdCount);
		// 往“小区消息”中间表中插入数据
		List<MessageGroup> messageGroupList = new ArrayList<MessageGroup>(gdIdCount);
		for (int i = 0; i < gdIdCount; i++) {
			String gdId = gdIds[i];
			MessageGroup mg = new MessageGroup();
			mg.setId(newIdList.get(i));
			mg.settMessageFId(msg.getId());
			mg.setGroupbuildingId(new BigInteger(gdId));
			mg.setSys0AddUser(UserContext.getCurrUser().getId());
			messageGroupList.add(mg);
		}
		messageGroupBaseDao.insertMessageGroupBatch(messageGroupList);
		//设定关系
		saveNoticeMessage(messageId, gdIds);
	}

	private IUserHasTMessageBaseDao userHasTMessageBaseDao;

	public void setUserHasTMessageBaseDao(IUserHasTMessageBaseDao userHasTMessageBaseDao) {
		this.userHasTMessageBaseDao = userHasTMessageBaseDao;
	}

	@Override
	public int saveNoticeMessage(BigInteger messageId, String[] messageGroups) throws BusinessRuntimeException {
		int resCount = 0;
		if (messageGroups != null && messageGroups.length > 0) {
			List<BigInteger> groupBIds = new ArrayList<BigInteger>();
			for (String idStr : messageGroups) {
				groupBIds.add(new BigInteger(idStr.trim()));
			}
			//查询小区下的<用户,房间>列表
			List<Map> userRoomList = noticeDao.selectUserAndRoomListByGroupBuildIds(groupBIds);
			//新增消息
			if (userRoomList != null && userRoomList.size() > 0) {//新增用户消息关系
				List<UserHasTMessage> userHasTMessageAddList = new ArrayList<UserHasTMessage>();
				List<BigInteger> addIdsList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, userRoomList.size());
				for (int i = 0; i < userRoomList.size(); i++) {
					UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
					tmpUserHasTMessage.setId(addIdsList.get(i));
					tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
					tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
					tmpUserHasTMessage.settMessageFId(messageId);
					tmpUserHasTMessage.settUserFId(new BigInteger(userRoomList.get(i).get("userId").toString()));
					tmpUserHasTMessage.setUserRoomId(new BigInteger(userRoomList.get(i).get("roomId").toString()));
					tmpUserHasTMessage.setUserType(LoginDict.UserRegistOrTmp.REGIST_USER);//已注册的用户
					userHasTMessageAddList.add(tmpUserHasTMessage);
				}
				userHasTMessageBaseDao.insertUserHasTMessageBatch(userHasTMessageAddList);
			}
			//根据消息类型设定消息的视图
			resCount = 1;
		}
		return resCount;
	}

	@Override
	public int searchNoticeList_forCount(Map<String, Object> paramMap) {
		return noticeDao.searchNoticeList_forCount(paramMap);
	}

	@Override
	public List<Message> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return noticeDao.searchNoticeList_forPage(curPageIndex, pageSize, paramMap);
	}
}
