package com.cnfantasia.server.domainbase.messageGroup.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;

/**
 * 描述:(消息小区关联表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageGroupBaseService {
	
	/**
	 * 根据条件查询(消息小区关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageGroup> getMessageGroupByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(消息小区关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageGroup> getMessageGroupByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(消息小区关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageGroup> getMessageGroupByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(消息小区关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageGroup> getMessageGroupByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(消息小区关联表)信息
	 * @param id
	 * @return
	 */
	public MessageGroup getMessageGroupBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息小区关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageGroupCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(消息小区关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageGroupCountDim(Map<String,Object> paramMap);
	/**
	 * 往(消息小区关联表)新增一条记录
	 * @param messageGroup
	 * @return
	 */
	public int insertMessageGroup(MessageGroup messageGroup);
	/**
	 * 批量新增(消息小区关联表)
	 * @param messageGroupList
	 * @return
	 */
	public int insertMessageGroupBatch(List<MessageGroup> messageGroupList);
	/**
	 * 更新(消息小区关联表)信息
	 * @param messageGroup
	 * @return
	 */
	public int updateMessageGroup(MessageGroup messageGroup);
	/**
	 * 批量更新(消息小区关联表)信息
	 * @param messageGroupList
	 * @return
	 */
	public int updateMessageGroupBatch(List<MessageGroup> messageGroupList);
	/**
	 * 根据序列号删除(消息小区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMessageGroupLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(消息小区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMessageGroupLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(消息小区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMessageGroup(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消息小区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMessageGroupBatch(List<java.math.BigInteger> idList);
	
}
