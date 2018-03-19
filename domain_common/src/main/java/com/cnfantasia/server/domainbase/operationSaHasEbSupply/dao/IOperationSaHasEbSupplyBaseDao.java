package com.cnfantasia.server.domainbase.operationSaHasEbSupply.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationSaHasEbSupply.entity.OperationSaHasEbSupply;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationSaHasEbSupplyBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OperationSaHasEbSupply> selectOperationSaHasEbSupplyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OperationSaHasEbSupply> selectOperationSaHasEbSupplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public OperationSaHasEbSupply selectOperationSaHasEbSupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOperationSaHasEbSupplyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param operationSaHasEbSupply
	 * @return
	 */
	public int insertOperationSaHasEbSupply(OperationSaHasEbSupply operationSaHasEbSupply);
	
	/**
	 * 批量新增()信息
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
	 * 根据Id 批量删除()信息_逻辑删除
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
