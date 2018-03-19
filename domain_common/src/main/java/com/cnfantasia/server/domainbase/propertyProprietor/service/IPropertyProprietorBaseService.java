package com.cnfantasia.server.domainbase.propertyProprietor.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;

/**
 * 描述:(业主信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyProprietorBaseService {
	
	/**
	 * 根据条件查询(业主信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyProprietor> getPropertyProprietorByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(业主信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyProprietor> getPropertyProprietorByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(业主信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyProprietor> getPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(业主信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyProprietor> getPropertyProprietorByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(业主信息表)信息
	 * @param id
	 * @return
	 */
	public PropertyProprietor getPropertyProprietorBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyProprietorCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(业主信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyProprietorCountDim(Map<String,Object> paramMap);
	/**
	 * 往(业主信息表)新增一条记录
	 * @param propertyProprietor
	 * @return
	 */
	public int insertPropertyProprietor(PropertyProprietor propertyProprietor);
	/**
	 * 批量新增(业主信息表)
	 * @param propertyProprietorList
	 * @return
	 */
	public int insertPropertyProprietorBatch(List<PropertyProprietor> propertyProprietorList);
	/**
	 * 更新(业主信息表)信息
	 * @param propertyProprietor
	 * @return
	 */
	public int updatePropertyProprietor(PropertyProprietor propertyProprietor);
	/**
	 * 批量更新(业主信息表)信息
	 * @param propertyProprietorList
	 * @return
	 */
	public int updatePropertyProprietorBatch(List<PropertyProprietor> propertyProprietorList);
	/**
	 * 根据序列号删除(业主信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyProprietorLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(业主信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyProprietorLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(业主信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyProprietor(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(业主信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyProprietorBatch(List<java.math.BigInteger> idList);
	
}
