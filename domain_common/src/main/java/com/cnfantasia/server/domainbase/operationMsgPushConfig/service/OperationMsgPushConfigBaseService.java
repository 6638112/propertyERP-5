package com.cnfantasia.server.domainbase.operationMsgPushConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.operationMsgPushConfig.dao.IOperationMsgPushConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationMsgPushConfig.entity.OperationMsgPushConfig;

/**
 * 描述:(运营消息推送配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OperationMsgPushConfigBaseService implements IOperationMsgPushConfigBaseService{
	
	private IOperationMsgPushConfigBaseDao operationMsgPushConfigBaseDao;
	public void setOperationMsgPushConfigBaseDao(IOperationMsgPushConfigBaseDao operationMsgPushConfigBaseDao) {
		this.operationMsgPushConfigBaseDao = operationMsgPushConfigBaseDao;
	}
	/**
	 * 根据条件查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByCondition(Map<String,Object> paramMap){
		return operationMsgPushConfigBaseDao.selectOperationMsgPushConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByConditionDim(Map<String,Object> paramMap){
		return operationMsgPushConfigBaseDao.selectOperationMsgPushConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return operationMsgPushConfigBaseDao.selectOperationMsgPushConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationMsgPushConfig> getOperationMsgPushConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return operationMsgPushConfigBaseDao.selectOperationMsgPushConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(运营消息推送配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationMsgPushConfig getOperationMsgPushConfigBySeqId(java.math.BigInteger id){
		return operationMsgPushConfigBaseDao.selectOperationMsgPushConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationMsgPushConfigCount(Map<String,Object> paramMap){
		return operationMsgPushConfigBaseDao.selectOperationMsgPushConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationMsgPushConfigCountDim(Map<String,Object> paramMap){
		return operationMsgPushConfigBaseDao.selectOperationMsgPushConfigCount(paramMap,true);
	}
	/**
	 * 往(运营消息推送配置表)新增一条记录
	 * @param operationMsgPushConfig
	 * @return
	 */
	@Override
	public int insertOperationMsgPushConfig(OperationMsgPushConfig operationMsgPushConfig){
		return operationMsgPushConfigBaseDao.insertOperationMsgPushConfig(operationMsgPushConfig);
	}
	/**
	 * 批量新增(运营消息推送配置表)
	 * @param operationMsgPushConfigList
	 * @return
	 */
	@Override
	public int insertOperationMsgPushConfigBatch(List<OperationMsgPushConfig> operationMsgPushConfigList){
		return operationMsgPushConfigBaseDao.insertOperationMsgPushConfigBatch(operationMsgPushConfigList);
	}
	/**
	 * 更新(运营消息推送配置表)信息
	 * @param operationMsgPushConfig
	 * @return
	 */
	@Override
	public int updateOperationMsgPushConfig(OperationMsgPushConfig operationMsgPushConfig){
		return operationMsgPushConfigBaseDao.updateOperationMsgPushConfig(operationMsgPushConfig);
	}
	/**
	 * 批量更新(运营消息推送配置表)信息
	 * @param operationMsgPushConfigList
	 * @return
	 */
	@Override
	public int updateOperationMsgPushConfigBatch(List<OperationMsgPushConfig> operationMsgPushConfigList){
		return operationMsgPushConfigBaseDao.updateOperationMsgPushConfigBatch(operationMsgPushConfigList);
	}
	/**
	 * 根据序列号删除(运营消息推送配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationMsgPushConfigLogic(java.math.BigInteger id){
		return operationMsgPushConfigBaseDao.deleteOperationMsgPushConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(运营消息推送配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationMsgPushConfigLogicBatch(List<java.math.BigInteger> idList){
		return operationMsgPushConfigBaseDao.deleteOperationMsgPushConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(运营消息推送配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationMsgPushConfig(java.math.BigInteger id){
//		return operationMsgPushConfigBaseDao.deleteOperationMsgPushConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运营消息推送配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationMsgPushConfigBatch(List<java.math.BigInteger> idList){
//		return operationMsgPushConfigBaseDao.deleteOperationMsgPushConfigBatch(idList);
//	}
	
}
