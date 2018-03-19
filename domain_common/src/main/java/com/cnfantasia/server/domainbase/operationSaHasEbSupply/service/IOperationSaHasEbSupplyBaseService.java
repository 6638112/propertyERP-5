package com.cnfantasia.server.domainbase.operationSaHasEbSupply.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.operationSaHasEbSupply.entity.OperationSaHasEbSupply;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationSaHasEbSupplyBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public OperationSaHasEbSupply getOperationSaHasEbSupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationSaHasEbSupplyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationSaHasEbSupplyCountDim(Map<String,Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param operationSaHasEbSupply
	 * @return
	 */
	public int insertOperationSaHasEbSupply(OperationSaHasEbSupply operationSaHasEbSupply);
	/**
	 * 批量新增()
	 * @param operationSaHasEbSupplyList
	 * @return
	 */
	public int insertOperationSaHasEbSupplyBatch(List<OperationSaHasEbSupply> operationSaHasEbSupplyList);
	/**
	 * 更新()信息
	 * @param operationSaHasEbSupply
	 * @return
	 */
	public int updateOperationSaHasEbSupply(OperationSaHasEbSupply operationSaHasEbSupply);
	/**
	 * 批量更新()信息
	 * @param operationSaHasEbSupplyList
	 * @return
	 */
	public int updateOperationSaHasEbSupplyBatch(List<OperationSaHasEbSupply> operationSaHasEbSupplyList);
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOperationSaHasEbSupplyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOperationSaHasEbSupplyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOperationSaHasEbSupply(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOperationSaHasEbSupplyBatch(List<java.math.BigInteger> idList);
	
}
