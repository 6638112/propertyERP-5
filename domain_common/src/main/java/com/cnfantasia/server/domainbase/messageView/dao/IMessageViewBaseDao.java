package com.cnfantasia.server.domainbase.messageView.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageView.entity.MessageView;

/**
 * 描述:(消息推送的视图配置) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageViewBaseDao {
	/**
	 * 根据条件查询(消息推送的视图配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessageView> selectMessageViewByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(消息推送的视图配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessageView> selectMessageViewByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(消息推送的视图配置)信息
	 * @param id
	 * @return
	 */
	public MessageView selectMessageViewBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息推送的视图配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMessageViewCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(消息推送的视图配置)新增一条记录
	 * @param messageView
	 * @return
	 */
	public int insertMessageView(MessageView messageView);
	
	/**
	 * 批量新增(消息推送的视图配置)信息
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
	 * 根据Id 批量删除(消息推送的视图配置)信息_逻辑删除
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
