package com.cnfantasia.server.domainbase.messageView.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.messageView.entity.MessageView;

/**
 * 描述:(消息推送的视图配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageViewBaseService {
	
	/**
	 * 根据条件查询(消息推送的视图配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageView> getMessageViewByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(消息推送的视图配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageView> getMessageViewByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(消息推送的视图配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageView> getMessageViewByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(消息推送的视图配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageView> getMessageViewByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(消息推送的视图配置)信息
	 * @param id
	 * @return
	 */
	public MessageView getMessageViewBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息推送的视图配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageViewCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(消息推送的视图配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageViewCountDim(Map<String,Object> paramMap);
	/**
	 * 往(消息推送的视图配置)新增一条记录
	 * @param messageView
	 * @return
	 */
	public int insertMessageView(MessageView messageView);
	/**
	 * 批量新增(消息推送的视图配置)
	 * @param messageViewList
	 * @return
	 */
	public int insertMessageViewBatch(List<MessageView> messageViewList);
	/**
	 * 更新(消息推送的视图配置)信息
	 * @param messageView
	 * @return
	 */
	public int updateMessageView(MessageView messageView);
	/**
	 * 批量更新(消息推送的视图配置)信息
	 * @param messageViewList
	 * @return
	 */
	public int updateMessageViewBatch(List<MessageView> messageViewList);
	/**
	 * 根据序列号删除(消息推送的视图配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMessageViewLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(消息推送的视图配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMessageViewLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(消息推送的视图配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMessageView(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消息推送的视图配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMessageViewBatch(List<java.math.BigInteger> idList);
	
}
