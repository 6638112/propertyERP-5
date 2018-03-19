package com.cnfantasia.server.domainbase.operationMsgPushConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.operationMsgPushConfig.entity.OperationMsgPushConfig;

/**
 * 描述:(运营消息推送配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationMsgPushConfigBaseService {
	
	/**
	 * 根据条件查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(运营消息推送配置表)信息
	 * @param id
	 * @return
	 */
	public OperationMsgPushConfig getOperationMsgPushConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationMsgPushConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationMsgPushConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(运营消息推送配置表)新增一条记录
	 * @param operationMsgPushConfig
	 * @return
	 */
	public int insertOperationMsgPushConfig(OperationMsgPushConfig operationMsgPushConfig);
	/**
	 * 批量新增(运营消息推送配置表)
	 * @param operationMsgPushConfigList
	 * @return
	 */
	public int insertOperationMsgPushConfigBatch(List<OperationMsgPushConfig> operationMsgPushConfigList);
	/**
	 * 更新(运营消息推送配置表)信息
	 * @param operationMsgPushConfig
	 * @return
	 */
	public int updateOperationMsgPushConfig(OperationMsgPushConfig operationMsgPushConfig);
	/**
	 * 批量更新(运营消息推送配置表)信息
	 * @param operationMsgPushConfigList
	 * @return
	 */
	public int updateOperationMsgPushConfigBatch(List<OperationMsgPushConfig> operationMsgPushConfigList);
	/**
	 * 根据序列号删除(运营消息推送配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOperationMsgPushConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(运营消息推送配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOperationMsgPushConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(运营消息推送配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOperationMsgPushConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(运营消息推送配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOperationMsgPushConfigBatch(List<java.math.BigInteger> idList);
	
}
