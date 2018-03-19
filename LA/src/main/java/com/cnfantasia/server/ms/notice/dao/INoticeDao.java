package com.cnfantasia.server.ms.notice.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;

public interface INoticeDao {
	
	public MessageEntity selectMessageDetail(BigInteger messageId);

	public void deleteMessageById(BigInteger messageId);

	public List<GroupBuildingSimpleEntity> selectGroupBuildingSimpleList(
			BigInteger userId);

	public int delete_messageGroup_byMsgId(String msgId);

	public List<Object> select_GroupBuildingIds_byMsgId(int msgId);

	public List<Map> selectUserAndRoomListByGroupBuildIds(List<BigInteger> groupBuildIdList);

	public List<Message> searchNoticeList(Map<String, Object> paramMap);

	int searchNoticeList_forCount(Map<String, Object> paramMap);

	List<Message> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
}
