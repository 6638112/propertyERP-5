package com.cnfantasia.server.domainbase.propertyRepairer.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyRepairer.dao.IPropertyRepairerBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;

/**
 * 描述:(管理处维修工) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyRepairerBaseService implements IPropertyRepairerBaseService{
	
	private IPropertyRepairerBaseDao propertyRepairerBaseDao;
	public void setPropertyRepairerBaseDao(IPropertyRepairerBaseDao propertyRepairerBaseDao) {
		this.propertyRepairerBaseDao = propertyRepairerBaseDao;
	}
	/**
	 * 根据条件查询(管理处维修工)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepairer> getPropertyRepairerByCondition(Map<String,Object> paramMap){
		return propertyRepairerBaseDao.selectPropertyRepairerByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(管理处维修工)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepairer> getPropertyRepairerByConditionDim(Map<String,Object> paramMap){
		return propertyRepairerBaseDao.selectPropertyRepairerByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(管理处维修工)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepairer> getPropertyRepairerByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairerBaseDao.selectPropertyRepairerByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(管理处维修工)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepairer> getPropertyRepairerByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairerBaseDao.selectPropertyRepairerByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(管理处维修工)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRepairer getPropertyRepairerBySeqId(java.math.BigInteger id){
		return propertyRepairerBaseDao.selectPropertyRepairerBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(管理处维修工)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairerCount(Map<String,Object> paramMap){
		return propertyRepairerBaseDao.selectPropertyRepairerCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(管理处维修工)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairerCountDim(Map<String,Object> paramMap){
		return propertyRepairerBaseDao.selectPropertyRepairerCount(paramMap,true);
	}
	/**
	 * 往(管理处维修工)新增一条记录
	 * @param propertyRepairer
	 * @return
	 */
	@Override
	public int insertPropertyRepairer(PropertyRepairer propertyRepairer){
		return propertyRepairerBaseDao.insertPropertyRepairer(propertyRepairer);
	}
	/**
	 * 批量新增(管理处维修工)
	 * @param propertyRepairerList
	 * @return
	 */
	@Override
	public int insertPropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList){
		return propertyRepairerBaseDao.insertPropertyRepairerBatch(propertyRepairerList);
	}
	/**
	 * 更新(管理处维修工)信息
	 * @param propertyRepairer
	 * @return
	 */
	@Override
	public int updatePropertyRepairer(PropertyRepairer propertyRepairer){
		return propertyRepairerBaseDao.updatePropertyRepairer(propertyRepairer);
	}
	/**
	 * 批量更新(管理处维修工)信息
	 * @param propertyRepairerList
	 * @return
	 */
	@Override
	public int updatePropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList){
		return propertyRepairerBaseDao.updatePropertyRepairerBatch(propertyRepairerList);
	}
	/**
	 * 根据序列号删除(管理处维修工)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairerLogic(java.math.BigInteger id){
		return propertyRepairerBaseDao.deletePropertyRepairerLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(管理处维修工)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairerLogicBatch(List<java.math.BigInteger> idList){
		return propertyRepairerBaseDao.deletePropertyRepairerLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(管理处维修工)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairer(java.math.BigInteger id){
//		return propertyRepairerBaseDao.deletePropertyRepairer(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(管理处维修工)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairerBatch(List<java.math.BigInteger> idList){
//		return propertyRepairerBaseDao.deletePropertyRepairerBatch(idList);
//	}
	
}
