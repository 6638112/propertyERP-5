package com.cnfantasia.server.domainbase.operationConstantData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.operationConstantData.entity.OperationConstantData;

/**
 * 描述:(运维相关的数据) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationConstantDataBaseService {
	
	/**
	 * 根据条件查询(运维相关的数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationConstantData> getOperationConstantDataByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(运维相关的数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationConstantData> getOperationConstantDataByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(运维相关的数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationConstantData> getOperationConstantDataByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(运维相关的数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationConstantData> getOperationConstantDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(运维相关的数据)信息
	 * @param id
	 * @return
	 */
	public OperationConstantData getOperationConstantDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(运维相关的数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationConstantDataCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(运维相关的数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationConstantDataCountDim(Map<String,Object> paramMap);
	/**
	 * 往(运维相关的数据)新增一条记录
	 * @param operationConstantData
	 * @return
	 */
	public int insertOperationConstantData(OperationConstantData operationConstantData);
	/**
	 * 批量新增(运维相关的数据)
	 * @param operationConstantDataList
	 * @return
	 */
	public int insertOperationConstantDataBatch(List<OperationConstantData> operationConstantDataList);
	/**
	 * 更新(运维相关的数据)信息
	 * @param operationConstantData
	 * @return
	 */
	public int updateOperationConstantData(OperationConstantData operationConstantData);
	/**
	 * 批量更新(运维相关的数据)信息
	 * @param operationConstantDataList
	 * @return
	 */
	public int updateOperationConstantDataBatch(List<OperationConstantData> operationConstantDataList);
	/**
	 * 根据序列号删除(运维相关的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOperationConstantDataLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(运维相关的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOperationConstantDataLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(运维相关的数据)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOperationConstantData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(运维相关的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOperationConstantDataBatch(List<java.math.BigInteger> idList);
	
}
