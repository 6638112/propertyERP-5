package com.cnfantasia.server.domainbase.operationHomeSupplyType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;

/**
 * 描述:(首页商家类别运营表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOperationHomeSupplyTypeBaseDao {
	/**
	 * 根据条件查询(首页商家类别运营表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OperationHomeSupplyType> selectOperationHomeSupplyTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(首页商家类别运营表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OperationHomeSupplyType> selectOperationHomeSupplyTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(首页商家类别运营表)信息
	 * @param id
	 * @return
	 */
	public OperationHomeSupplyType selectOperationHomeSupplyTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页商家类别运营表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOperationHomeSupplyTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(首页商家类别运营表)新增一条记录
	 * @param operationHomeSupplyType
	 * @return
	 */
	public int insertOperationHomeSupplyType(OperationHomeSupplyType operationHomeSupplyType);
	
	/**
	 * 批量新增(首页商家类别运营表)信息
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
	 * 根据Id 批量删除(首页商家类别运营表)信息_逻辑删除
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
