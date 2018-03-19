package com.cnfantasia.server.domainbase.propertyRepairer.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;

/**
 * 描述:(管理处维修工) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyRepairerBaseDao {
	/**
	 * 根据条件查询(管理处维修工)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyRepairer> selectPropertyRepairerByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(管理处维修工)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyRepairer> selectPropertyRepairerByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(管理处维修工)信息
	 * @param id
	 * @return
	 */
	public PropertyRepairer selectPropertyRepairerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(管理处维修工)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyRepairerCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(管理处维修工)新增一条记录
	 * @param propertyRepairer
	 * @return
	 */
	public int insertPropertyRepairer(PropertyRepairer propertyRepairer);
	
	/**
	 * 批量新增(管理处维修工)信息
	 * @param propertyRepairerList
	 * @return
	 */
	public int insertPropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList);
	
	/**
	 * 更新(管理处维修工)信息
	 * @param propertyRepairer
	 * @return
	 */
	public int updatePropertyRepairer(PropertyRepairer propertyRepairer);
	
	/**
	 * 批量更新(管理处维修工)信息
	 * @param propertyRepairerList
	 * @return
	 */
	public int updatePropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList);
	
	/**
	 * 根据序列号删除(管理处维修工)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyRepairerLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(管理处维修工)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyRepairerLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(管理处维修工)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyRepairer(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(管理处维修工)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyRepairerBatch(List<java.math.BigInteger> idList);
	
	
}
