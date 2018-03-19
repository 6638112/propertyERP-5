package com.cnfantasia.server.domainbase.propertyProprietor.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;

/**
 * 描述:(业主信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyProprietorBaseDao {
	/**
	 * 根据条件查询(业主信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyProprietor> selectPropertyProprietorByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(业主信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyProprietor> selectPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(业主信息表)信息
	 * @param id
	 * @return
	 */
	public PropertyProprietor selectPropertyProprietorBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyProprietorCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(业主信息表)新增一条记录
	 * @param propertyProprietor
	 * @return
	 */
	public int insertPropertyProprietor(PropertyProprietor propertyProprietor);
	
	/**
	 * 批量新增(业主信息表)信息
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
	 * 根据Id 批量删除(业主信息表)信息_逻辑删除
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
