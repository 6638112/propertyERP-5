package com.cnfantasia.server.ms.notice.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.dto.NoticeListBean;
import com.cnfantasia.server.ms.notice.dto.NoticeRequest;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;

public interface INoticeService {
	/**
	 * 根据Id查询详情
	 * @param messageId
	 * @return
	 */
	public MessageEntity getMessageDetail(BigInteger messageId);
	
	public List<NoticeListBean> searchNoticeList(Map<String, Object> paramMap);
	
	public void deleteMessageById(BigInteger messageId);
	
	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(Map<String, Object> paramMap);
	
	public List<GroupBuildingSimpleEntity> getBlockList(Map<String, Object> paramMap);
	
	public int delete_messageGroup_byMsgId(String msgId);

	/**
	 * 
	 * @param noticeRequest
	 */
	public void saveNotice(NoticeRequest noticeRequest);
	
	/**
	 * 公告发布
	 * @param id
	 * @return
	 */
	public JsonResponse publish(BigInteger id);

	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(BigInteger userId, int msgId);

	public int saveNoticeMessage(BigInteger messageId, String[] messageGroups) throws BusinessRuntimeException;

	public int searchNoticeList_forCount(Map<String, Object> paramMap);

	public List<NoticeListBean> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	
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
}
