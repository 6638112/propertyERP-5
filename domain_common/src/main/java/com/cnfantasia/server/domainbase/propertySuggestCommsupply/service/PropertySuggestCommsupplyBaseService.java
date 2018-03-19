package com.cnfantasia.server.domainbase.propertySuggestCommsupply.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertySuggestCommsupply.dao.IPropertySuggestCommsupplyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity.PropertySuggestCommsupply;

/**
 * 描述:(物业推荐信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertySuggestCommsupplyBaseService implements IPropertySuggestCommsupplyBaseService{
	
	private IPropertySuggestCommsupplyBaseDao propertySuggestCommsupplyBaseDao;
	public void setPropertySuggestCommsupplyBaseDao(IPropertySuggestCommsupplyBaseDao propertySuggestCommsupplyBaseDao) {
		this.propertySuggestCommsupplyBaseDao = propertySuggestCommsupplyBaseDao;
	}
	/**
	 * 根据条件查询(物业推荐信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByCondition(Map<String,Object> paramMap){
		return propertySuggestCommsupplyBaseDao.selectPropertySuggestCommsupplyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业推荐信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByConditionDim(Map<String,Object> paramMap){
		return propertySuggestCommsupplyBaseDao.selectPropertySuggestCommsupplyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业推荐信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertySuggestCommsupplyBaseDao.selectPropertySuggestCommsupplyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业推荐信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertySuggestCommsupply> getPropertySuggestCommsupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertySuggestCommsupplyBaseDao.selectPropertySuggestCommsupplyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业推荐信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertySuggestCommsupply getPropertySuggestCommsupplyBySeqId(java.math.BigInteger id){
		return propertySuggestCommsupplyBaseDao.selectPropertySuggestCommsupplyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业推荐信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertySuggestCommsupplyCount(Map<String,Object> paramMap){
		return propertySuggestCommsupplyBaseDao.selectPropertySuggestCommsupplyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业推荐信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertySuggestCommsupplyCountDim(Map<String,Object> paramMap){
		return propertySuggestCommsupplyBaseDao.selectPropertySuggestCommsupplyCount(paramMap,true);
	}
	/**
	 * 往(物业推荐信息表)新增一条记录
	 * @param propertySuggestCommsupply
	 * @return
	 */
	@Override
	public int insertPropertySuggestCommsupply(PropertySuggestCommsupply propertySuggestCommsupply){
		return propertySuggestCommsupplyBaseDao.insertPropertySuggestCommsupply(propertySuggestCommsupply);
	}
	/**
	 * 批量新增(物业推荐信息表)
	 * @param propertySuggestCommsupplyList
	 * @return
	 */
	@Override
	public int insertPropertySuggestCommsupplyBatch(List<PropertySuggestCommsupply> propertySuggestCommsupplyList){
		return propertySuggestCommsupplyBaseDao.insertPropertySuggestCommsupplyBatch(propertySuggestCommsupplyList);
	}
	/**
	 * 更新(物业推荐信息表)信息
	 * @param propertySuggestCommsupply
	 * @return
	 */
	@Override
	public int updatePropertySuggestCommsupply(PropertySuggestCommsupply propertySuggestCommsupply){
		return propertySuggestCommsupplyBaseDao.updatePropertySuggestCommsupply(propertySuggestCommsupply);
	}
	/**
	 * 批量更新(物业推荐信息表)信息
	 * @param propertySuggestCommsupplyList
	 * @return
	 */
	@Override
	public int updatePropertySuggestCommsupplyBatch(List<PropertySuggestCommsupply> propertySuggestCommsupplyList){
		return propertySuggestCommsupplyBaseDao.updatePropertySuggestCommsupplyBatch(propertySuggestCommsupplyList);
	}
	/**
	 * 根据序列号删除(物业推荐信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertySuggestCommsupplyLogic(java.math.BigInteger id){
		return propertySuggestCommsupplyBaseDao.deletePropertySuggestCommsupplyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业推荐信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertySuggestCommsupplyLogicBatch(List<java.math.BigInteger> idList){
		return propertySuggestCommsupplyBaseDao.deletePropertySuggestCommsupplyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业推荐信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertySuggestCommsupply(java.math.BigInteger id){
//		return propertySuggestCommsupplyBaseDao.deletePropertySuggestCommsupply(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业推荐信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertySuggestCommsupplyBatch(List<java.math.BigInteger> idList){
//		return propertySuggestCommsupplyBaseDao.deletePropertySuggestCommsupplyBatch(idList);
//	}
	
}
