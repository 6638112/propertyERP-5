package com.cnfantasia.server.ms.notice.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.message.dao.MessageBaseDao;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.dto.NoticeListBean;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;

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
	public List<GroupBuildingSimpleEntity> selectGroupBuildingSimpleList(Map<String, Object> paramMap) {
		return sqlSession.selectList("notice.select_GroupBuilding_Simple_List", paramMap);
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectBlockList(Map<String, Object> paramMap){
		return sqlSession.selectList("notice.selectBlockList", paramMap);
	}
	
	@Override
	public List<NoticeListBean> searchNoticeList(Map<String, Object> paramMap) {
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
		return sqlSession.selectOne("notice.select_Notice_List_count", paramMap);
	}

	@Override
	public List<NoticeListBean> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("notice.select_Notice_List_byCondition", paramMap);
	}

	@Override
	public Integer deleteUserHasTMessageByMsgId(BigInteger messageId) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("messageId", messageId);
		return sqlSession.update("notice.delete_UserHasTMessage_ByMsgId", tmpMap);
	}
	
	/**
	 * 小区autocomplete框后台实现
	 * 
	 * @param gbName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> gbFilter(String gbName){
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbName", gbName);
		return sqlSession.selectList("notice.group_building_filter", tmpMap);
	}
	
	/**
	 * 物业autocomplete框后台实现
	 * 
	 * @param pcName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> propertyCompanyFilter(String pcName){
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("pcName", pcName);
		return sqlSession.selectList("notice.property_company_filter", tmpMap);
	}
	
	/**
	 * 查询公告对应的小区
	 * @param msgId
	 * @return
	 */
	@Override
	public List<BigInteger> selectGbIdsForNotice(BigInteger msgId){
		return sqlSession.selectList("notice.select_gbIdsForNotice", msgId);
	}
}
