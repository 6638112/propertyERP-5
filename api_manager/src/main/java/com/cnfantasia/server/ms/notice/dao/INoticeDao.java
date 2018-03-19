package com.cnfantasia.server.ms.notice.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.dto.NoticeListBean;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;

public interface INoticeDao {
	
	public MessageEntity selectMessageDetail(BigInteger messageId);

	public void deleteMessageById(BigInteger messageId);

	public List<GroupBuildingSimpleEntity> selectGroupBuildingSimpleList(Map<String, Object> paramMap);
	
	public List<GroupBuildingSimpleEntity> selectBlockList(Map<String, Object> paramMap);

	public int delete_messageGroup_byMsgId(String msgId);

	public List<Object> select_GroupBuildingIds_byMsgId(int msgId);

	public List<Map> selectUserAndRoomListByGroupBuildIds(List<BigInteger> groupBuildIdList);

	public List<NoticeListBean> searchNoticeList(Map<String, Object> paramMap);

	int searchNoticeList_forCount(Map<String, Object> paramMap);

	List<NoticeListBean> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	
	/**
	 * 根据消息Id删除历史数据
	 * @param messageId
	 */
	public Integer deleteUserHasTMessageByMsgId(BigInteger messageId);
	
	/**
	 * 小区autocomplete框后台实现
	 * 
	 * @param gbName
	 * @return
	 */
	public List<Map<String, Object>> gbFilter(String gbName);
	
	/**
	 * 物业autocomplete框后台实现
	 * 
	 * @param pcName
	 * @return
	 */
	public List<Map<String, Object>> propertyCompanyFilter(String pcName);
	
	/**
	 * 查询公告对应的小区
	 * @param msgId
	 * @return
	 */
	public List<BigInteger> selectGbIdsForNotice(BigInteger msgId);
}
