package com.cnfantasia.server.domainbase.operationHomeSupplyType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;

/**
 * 描述:(首页商家类别运营表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationHomeSupplyTypeBaseService {
	
	/**
	 * 根据条件查询(首页商家类别运营表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(首页商家类别运营表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(首页商家类别运营表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(首页商家类别运营表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(首页商家类别运营表)信息
	 * @param id
	 * @return
	 */
	public OperationHomeSupplyType getOperationHomeSupplyTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页商家类别运营表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationHomeSupplyTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(首页商家类别运营表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOperationHomeSupplyTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(首页商家类别运营表)新增一条记录
	 * @param operationHomeSupplyType
	 * @return
	 */
	public int insertOperationHomeSupplyType(OperationHomeSupplyType operationHomeSupplyType);
	/**
	 * 批量新增(首页商家类别运营表)
	 * @param operationHomeSupplyTypeList
	 * @return
	 */
	public int insertOperationHomeSupplyTypeBatch(List<OperationHomeSupplyType> operationHomeSupplyTypeList);
	/**
	 * 更新(首页商家类别运营表)信息
	 * @param operationHomeSupplyType
	 * @return
	 */
	public int updateOperationHomeSupplyType(OperationHomeSupplyType operationHomeSupplyType);
	/**
	 * 批量更新(首页商家类别运营表)信息
	 * @param operationHomeSupplyTypeList
	 * @return
	 */
	public int updateOperationHomeSupplyTypeBatch(List<OperationHomeSupplyType> operationHomeSupplyTypeList);
	/**
	 * 根据序列号删除(首页商家类别运营表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOperationHomeSupplyTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(首页商家类别运营表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOperationHomeSupplyTypeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(首页商家类别运营表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOperationHomeSupplyType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(首页商家类别运营表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOperationHomeSupplyTypeBatch(List<java.math.BigInteger> idList);
	
}
