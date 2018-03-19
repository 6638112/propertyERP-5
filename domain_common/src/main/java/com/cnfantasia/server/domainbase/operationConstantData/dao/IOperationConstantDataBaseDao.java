package com.cnfantasia.server.domainbase.operationConstantData.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationConstantData.entity.OperationConstantData;

/**
 * 描述:(运维相关的数据) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationConstantDataBaseDao {
	/**
	 * 根据条件查询(运维相关的数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OperationConstantData> selectOperationConstantDataByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(运维相关的数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OperationConstantData> selectOperationConstantDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(运维相关的数据)信息
	 * @param id
	 * @return
	 */
	public OperationConstantData selectOperationConstantDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(运维相关的数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOperationConstantDataCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(运维相关的数据)新增一条记录
	 * @param operationConstantData
	 * @return
	 */
	public int insertOperationConstantData(OperationConstantData operationConstantData);
	
	/**
	 * 批量新增(运维相关的数据)信息
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
	 * 根据Id 批量删除(运维相关的数据)信息_逻辑删除
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
