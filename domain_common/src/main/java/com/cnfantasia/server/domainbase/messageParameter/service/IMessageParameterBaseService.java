package com.cnfantasia.server.domainbase.messageParameter.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;

/**
 * 描述:(消息参数表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageParameterBaseService {
	
	/**
	 * 根据条件查询(消息参数表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageParameter> getMessageParameterByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(消息参数表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageParameter> getMessageParameterByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(消息参数表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageParameter> getMessageParameterByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(消息参数表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageParameter> getMessageParameterByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(消息参数表)信息
	 * @param id
	 * @return
	 */
	public MessageParameter getMessageParameterBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息参数表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageParameterCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(消息参数表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageParameterCountDim(Map<String,Object> paramMap);
	/**
	 * 往(消息参数表)新增一条记录
	 * @param messageParameter
	 * @return
	 */
	public int insertMessageParameter(MessageParameter messageParameter);
	/**
	 * 批量新增(消息参数表)
	 * @param messageParameterList
	 * @return
	 */
	public int insertMessageParameterBatch(List<MessageParameter> messageParameterList);
	/**
	 * 更新(消息参数表)信息
	 * @param messageParameter
	 * @return
	 */
	public int updateMessageParameter(MessageParameter messageParameter);
	/**
	 * 批量更新(消息参数表)信息
	 * @param messageParameterList
	 * @return
	 */
	public int updateMessageParameterBatch(List<MessageParameter> messageParameterList);
	/**
	 * 根据序列号删除(消息参数表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMessageParameterLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(消息参数表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMessageParameterLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(消息参数表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMessageParameter(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消息参数表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMessageParameterBatch(List<java.math.BigInteger> idList);
	
}
