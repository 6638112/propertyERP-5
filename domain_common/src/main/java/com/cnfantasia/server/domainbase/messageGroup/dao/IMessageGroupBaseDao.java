package com.cnfantasia.server.domainbase.messageGroup.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;

/**
 * 描述:(消息小区关联表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageGroupBaseDao {
	/**
	 * 根据条件查询(消息小区关联表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessageGroup> selectMessageGroupByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(消息小区关联表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessageGroup> selectMessageGroupByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(消息小区关联表)信息
	 * @param id
	 * @return
	 */
	public MessageGroup selectMessageGroupBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息小区关联表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMessageGroupCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(消息小区关联表)新增一条记录
	 * @param messageGroup
	 * @return
	 */
	public int insertMessageGroup(MessageGroup messageGroup);
	
	/**
	 * 批量新增(消息小区关联表)信息
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
	 * 根据Id 批量删除(消息小区关联表)信息_逻辑删除
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
