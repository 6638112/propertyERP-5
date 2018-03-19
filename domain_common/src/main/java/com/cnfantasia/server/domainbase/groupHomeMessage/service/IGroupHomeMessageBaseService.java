package com.cnfantasia.server.domainbase.groupHomeMessage.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;

/**
 * 描述:(首页公共消息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupHomeMessageBaseService {
	
	/**
	 * 根据条件查询(首页公共消息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupHomeMessage> getGroupHomeMessageByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(首页公共消息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupHomeMessage> getGroupHomeMessageByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(首页公共消息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupHomeMessage> getGroupHomeMessageByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(首页公共消息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupHomeMessage> getGroupHomeMessageByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(首页公共消息表)信息
	 * @param id
	 * @return
	 */
	public GroupHomeMessage getGroupHomeMessageBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页公共消息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupHomeMessageCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(首页公共消息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupHomeMessageCountDim(Map<String, Object> paramMap);
	/**
	 * 往(首页公共消息表)新增一条记录
	 * @param groupHomeMessage
	 * @return
	 */
	public int insertGroupHomeMessage(GroupHomeMessage groupHomeMessage);
	/**
	 * 批量新增(首页公共消息表)
	 * @param groupHomeMessageList
	 * @return
	 */
	public int insertGroupHomeMessageBatch(List<GroupHomeMessage> groupHomeMessageList);
	/**
	 * 更新(首页公共消息表)信息
	 * @param groupHomeMessage
	 * @return
	 */
	public int updateGroupHomeMessage(GroupHomeMessage groupHomeMessage);
	/**
	 * 批量更新(首页公共消息表)信息
	 * @param groupHomeMessageList
	 * @return
	 */
	public int updateGroupHomeMessageBatch(List<GroupHomeMessage> groupHomeMessageList);
	/**
	 * 根据序列号删除(首页公共消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGroupHomeMessageLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(首页公共消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGroupHomeMessageLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(首页公共消息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupHomeMessage(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(首页公共消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupHomeMessageBatch(List<java.math.BigInteger> idList);
	
}
