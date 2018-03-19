package com.cnfantasia.server.ms.notice.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;

public interface INoticeService {
	/**
	 * 根据Id查询详情
	 * @param messageId
	 * @return
	 */
	public MessageEntity getMessageDetail(BigInteger messageId);
	
	public List<Message> searchNoticeList(Map<String, Object> paramMap);
	
	public void deleteMessageById(BigInteger messageId);
	
	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(BigInteger userId);
	
	public int delete_messageGroup_byMsgId(String msgId);

	/**
	 * 
	 * @param msgId
	 * @param title
	 * @param content
	 * @param pushWay
	 * @param pushTime
	 * @param gdIds
	 */
	public void saveNotice(String msgId, String title, String content, String pushWay, String pushTime, String[] gdIds);

	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(BigInteger userId, int msgId);

	public int saveNoticeMessage(BigInteger messageId, String[] messageGroups) throws BusinessRuntimeException;

	public int searchNoticeList_forCount(Map<String, Object> paramMap);

	public List<Message> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
}
