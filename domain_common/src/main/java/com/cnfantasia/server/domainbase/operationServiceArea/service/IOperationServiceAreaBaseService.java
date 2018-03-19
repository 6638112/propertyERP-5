package com.cnfantasia.server.domainbase.operationServiceArea.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;

/**
 * 描述:(服务范围的运营数据表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationServiceAreaBaseService {
	
	/**
	 * 根据条件查询(服务范围的运营数据表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationServiceArea> getOperationServiceAreaByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(服务范围的运营数据表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationServiceArea> getOperationServiceAreaByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(服务范围的运营数据表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationServiceArea> getOperationServiceAreaByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(服务范围的运营数据表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationServiceArea> getOperationServiceAreaByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(服务范围的运营数据表)信息
	 * @param id
	 * @return
	 */
	public OperationServiceArea getOperationServiceAreaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(服务范围的运营数据表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationServiceAreaCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(服务范围的运营数据表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationServiceAreaCountDim(Map<String,Object> paramMap);
	/**
	 * 往(服务范围的运营数据表)新增一条记录
	 * @param operationServiceArea
	 * @return
	 */
	public int insertOperationServiceArea(OperationServiceArea operationServiceArea);
	/**
	 * 批量新增(服务范围的运营数据表)
	 * @param operationServiceAreaList
	 * @return
	 */
	public int insertOperationServiceAreaBatch(List<OperationServiceArea> operationServiceAreaList);
	/**
	 * 更新(服务范围的运营数据表)信息
	 * @param operationServiceArea
	 * @return
	 */
	public int updateOperationServiceArea(OperationServiceArea operationServiceArea);
	/**
	 * 批量更新(服务范围的运营数据表)信息
	 * @param operationServiceAreaList
	 * @return
	 */
	public int updateOperationServiceAreaBatch(List<OperationServiceArea> operationServiceAreaList);
	/**
	 * 根据序列号删除(服务范围的运营数据表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOperationServiceAreaLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(服务范围的运营数据表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOperationServiceAreaLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(服务范围的运营数据表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOperationServiceArea(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(服务范围的运营数据表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOperationServiceAreaBatch(List<java.math.BigInteger> idList);
	
}
