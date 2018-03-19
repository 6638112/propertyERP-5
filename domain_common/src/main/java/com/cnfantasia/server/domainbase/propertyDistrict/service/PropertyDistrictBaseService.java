package com.cnfantasia.server.domainbase.propertyDistrict.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyDistrict.dao.IPropertyDistrictBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;

/**
 * 描述:(物业片区) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyDistrictBaseService implements IPropertyDistrictBaseService{
	
	private IPropertyDistrictBaseDao propertyDistrictBaseDao;
	public void setPropertyDistrictBaseDao(IPropertyDistrictBaseDao propertyDistrictBaseDao) {
		this.propertyDistrictBaseDao = propertyDistrictBaseDao;
	}
	/**
	 * 根据条件查询(物业片区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyDistrict> getPropertyDistrictByCondition(Map<String,Object> paramMap){
		return propertyDistrictBaseDao.selectPropertyDistrictByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业片区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyDistrict> getPropertyDistrictByConditionDim(Map<String,Object> paramMap){
		return propertyDistrictBaseDao.selectPropertyDistrictByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业片区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyDistrict> getPropertyDistrictByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyDistrictBaseDao.selectPropertyDistrictByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业片区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyDistrict> getPropertyDistrictByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyDistrictBaseDao.selectPropertyDistrictByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业片区)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyDistrict getPropertyDistrictBySeqId(java.math.BigInteger id){
		return propertyDistrictBaseDao.selectPropertyDistrictBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业片区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyDistrictCount(Map<String,Object> paramMap){
		return propertyDistrictBaseDao.selectPropertyDistrictCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业片区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyDistrictCountDim(Map<String,Object> paramMap){
		return propertyDistrictBaseDao.selectPropertyDistrictCount(paramMap,true);
	}
	/**
	 * 往(物业片区)新增一条记录
	 * @param propertyDistrict
	 * @return
	 */
	@Override
	public int insertPropertyDistrict(PropertyDistrict propertyDistrict){
		return propertyDistrictBaseDao.insertPropertyDistrict(propertyDistrict);
	}
	/**
	 * 批量新增(物业片区)
	 * @param propertyDistrictList
	 * @return
	 */
	@Override
	public int insertPropertyDistrictBatch(List<PropertyDistrict> propertyDistrictList){
		return propertyDistrictBaseDao.insertPropertyDistrictBatch(propertyDistrictList);
	}
	/**
	 * 更新(物业片区)信息
	 * @param propertyDistrict
	 * @return
	 */
	@Override
	public int updatePropertyDistrict(PropertyDistrict propertyDistrict){
		return propertyDistrictBaseDao.updatePropertyDistrict(propertyDistrict);
	}
	/**
	 * 批量更新(物业片区)信息
	 * @param propertyDistrictList
	 * @return
	 */
	@Override
	public int updatePropertyDistrictBatch(List<PropertyDistrict> propertyDistrictList){
		return propertyDistrictBaseDao.updatePropertyDistrictBatch(propertyDistrictList);
	}
	/**
	 * 根据序列号删除(物业片区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictLogic(java.math.BigInteger id){
		return propertyDistrictBaseDao.deletePropertyDistrictLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业片区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictLogicBatch(List<java.math.BigInteger> idList){
		return propertyDistrictBaseDao.deletePropertyDistrictLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业片区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrict(java.math.BigInteger id){
//		return propertyDistrictBaseDao.deletePropertyDistrict(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业片区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrictBatch(List<java.math.BigInteger> idList){
//		return propertyDistrictBaseDao.deletePropertyDistrictBatch(idList);
//	}
	
}
