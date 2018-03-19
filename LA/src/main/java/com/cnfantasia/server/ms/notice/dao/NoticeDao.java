package com.cnfantasia.server.ms.notice.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.message.dao.MessageBaseDao;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;
import com.cnfantasia.server.ms.pub.pagination.TotalCountGetter;

public class NoticeDao extends MessageBaseDao implements INoticeDao {

	@Override
	public MessageEntity selectMessageDetail(BigInteger messageId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("messageId", messageId);
		return sqlSession.selectOne("notice.select_Message_Detail",tmpMap);
	}

	@Override
	public void deleteMessageById(BigInteger messageId) {
		sqlSession.delete("notice.delete_message_byId", messageId);
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectGroupBuildingSimpleList(BigInteger userId) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("notice.select_GroupBuilding_Simple_List", tmpMap);
	}

	@Override
	public List<Message> searchNoticeList(Map<String, Object> paramMap) {
		return sqlSession.selectList("notice.select_Notice_List_byCondition", paramMap);
	}

	@Override
	public int delete_messageGroup_byMsgId(String msgId) {
		return sqlSession.delete("notice.delete_messageGroup_byMsgId", msgId);
	}

	@Override
	public List<Object> select_GroupBuildingIds_byMsgId(int msgId) {
		return sqlSession.selectList("notice.select_GroupBuildingIds_byMsgId", msgId);
	}

	@Override
	public List<Map> selectUserAndRoomListByGroupBuildIds(List<BigInteger> groupBuildIdList) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildIdList", groupBuildIdList);
		return sqlSession.selectList("notice.select_UserAndRoom_List_By_GroupBuildIds", tmpMap);
	}

	@Override
	public int searchNoticeList_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "notice.select_Notice_List_byCondition", paramMap);
	}

	@Override
	public List<Message> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("notice.select_Notice_List_byCondition", paramMap);
	}
}
