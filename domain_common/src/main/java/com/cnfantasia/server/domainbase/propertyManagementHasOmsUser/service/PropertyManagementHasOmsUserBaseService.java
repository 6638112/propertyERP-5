package com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.dao.IPropertyManagementHasOmsUserBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.entity.PropertyManagementHasOmsUser;

/**
 * 描述:(物业管理与后账号关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyManagementHasOmsUserBaseService implements IPropertyManagementHasOmsUserBaseService{
	
	private IPropertyManagementHasOmsUserBaseDao propertyManagementHasOmsUserBaseDao;
	public void setPropertyManagementHasOmsUserBaseDao(IPropertyManagementHasOmsUserBaseDao propertyManagementHasOmsUserBaseDao) {
		this.propertyManagementHasOmsUserBaseDao = propertyManagementHasOmsUserBaseDao;
	}
	/**
	 * 根据条件查询(物业管理与后账号关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByCondition(Map<String,Object> paramMap){
		return propertyManagementHasOmsUserBaseDao.selectPropertyManagementHasOmsUserByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业管理与后账号关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByConditionDim(Map<String,Object> paramMap){
		return propertyManagementHasOmsUserBaseDao.selectPropertyManagementHasOmsUserByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业管理与后账号关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyManagementHasOmsUserBaseDao.selectPropertyManagementHasOmsUserByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业管理与后账号关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyManagementHasOmsUserBaseDao.selectPropertyManagementHasOmsUserByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业管理与后账号关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyManagementHasOmsUser getPropertyManagementHasOmsUserBySeqId(java.math.BigInteger id){
		return propertyManagementHasOmsUserBaseDao.selectPropertyManagementHasOmsUserBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业管理与后账号关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyManagementHasOmsUserCount(Map<String,Object> paramMap){
		return propertyManagementHasOmsUserBaseDao.selectPropertyManagementHasOmsUserCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业管理与后账号关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyManagementHasOmsUserCountDim(Map<String,Object> paramMap){
		return propertyManagementHasOmsUserBaseDao.selectPropertyManagementHasOmsUserCount(paramMap,true);
	}
	/**
	 * 往(物业管理与后账号关系)新增一条记录
	 * @param propertyManagementHasOmsUser
	 * @return
	 */
	@Override
	public int insertPropertyManagementHasOmsUser(PropertyManagementHasOmsUser propertyManagementHasOmsUser){
		return propertyManagementHasOmsUserBaseDao.insertPropertyManagementHasOmsUser(propertyManagementHasOmsUser);
	}
	/**
	 * 批量新增(物业管理与后账号关系)
	 * @param propertyManagementHasOmsUserList
	 * @return
	 */
	@Override
	public int insertPropertyManagementHasOmsUserBatch(List<PropertyManagementHasOmsUser> propertyManagementHasOmsUserList){
		return propertyManagementHasOmsUserBaseDao.insertPropertyManagementHasOmsUserBatch(propertyManagementHasOmsUserList);
	}
	/**
	 * 更新(物业管理与后账号关系)信息
	 * @param propertyManagementHasOmsUser
	 * @return
	 */
	@Override
	public int updatePropertyManagementHasOmsUser(PropertyManagementHasOmsUser propertyManagementHasOmsUser){
		return propertyManagementHasOmsUserBaseDao.updatePropertyManagementHasOmsUser(propertyManagementHasOmsUser);
	}
	/**
	 * 批量更新(物业管理与后账号关系)信息
	 * @param propertyManagementHasOmsUserList
	 * @return
	 */
	@Override
	public int updatePropertyManagementHasOmsUserBatch(List<PropertyManagementHasOmsUser> propertyManagementHasOmsUserList){
		return propertyManagementHasOmsUserBaseDao.updatePropertyManagementHasOmsUserBatch(propertyManagementHasOmsUserList);
	}
	/**
	 * 根据序列号删除(物业管理与后账号关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementHasOmsUserLogic(java.math.BigInteger id){
		return propertyManagementHasOmsUserBaseDao.deletePropertyManagementHasOmsUserLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业管理与后账号关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementHasOmsUserLogicBatch(List<java.math.BigInteger> idList){
		return propertyManagementHasOmsUserBaseDao.deletePropertyManagementHasOmsUserLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业管理与后账号关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagementHasOmsUser(java.math.BigInteger id){
//		return propertyManagementHasOmsUserBaseDao.deletePropertyManagementHasOmsUser(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业管理与后账号关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagementHasOmsUserBatch(List<java.math.BigInteger> idList){
//		return propertyManagementHasOmsUserBaseDao.deletePropertyManagementHasOmsUserBatch(idList);
//	}
	
}
