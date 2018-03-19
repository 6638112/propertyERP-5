package com.cnfantasia.server.domainbase.propertyDistrict.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;

/**
 * 描述:(物业片区) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyDistrictBaseDao {
	/**
	 * 根据条件查询(物业片区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyDistrict> selectPropertyDistrictByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业片区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyDistrict> selectPropertyDistrictByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业片区)信息
	 * @param id
	 * @return
	 */
	public PropertyDistrict selectPropertyDistrictBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业片区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyDistrictCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业片区)新增一条记录
	 * @param propertyDistrict
	 * @return
	 */
	public int insertPropertyDistrict(PropertyDistrict propertyDistrict);
	
	/**
	 * 批量新增(物业片区)信息
	 * @param propertyDistrictList
	 * @return
	 */
	public int insertPropertyDistrictBatch(List<PropertyDistrict> propertyDistrictList);
	
	/**
	 * 更新(物业片区)信息
	 * @param propertyDistrict
	 * @return
	 */
	public int updatePropertyDistrict(PropertyDistrict propertyDistrict);
	
	/**
	 * 批量更新(物业片区)信息
	 * @param propertyDistrictList
	 * @return
	 */
	public int updatePropertyDistrictBatch(List<PropertyDistrict> propertyDistrictList);
	
	/**
	 * 根据序列号删除(物业片区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyDistrictLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(物业片区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyDistrictLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(物业片区)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyDistrict(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业片区)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyDistrictBatch(List<java.math.BigInteger> idList);
	
	
}
