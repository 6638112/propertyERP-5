package com.cnfantasia.server.domainbase.permiRole.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.permiRole.dao.IPermiRoleBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRole.entity.PermiRole;

/**
 * 描述:(角色表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PermiRoleBaseService implements IPermiRoleBaseService{
	
	private IPermiRoleBaseDao permiRoleBaseDao;
	public void setPermiRoleBaseDao(IPermiRoleBaseDao permiRoleBaseDao) {
		this.permiRoleBaseDao = permiRoleBaseDao;
	}
	/**
	 * 根据条件查询(角色表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiRole> getPermiRoleByCondition(Map<String,Object> paramMap){
		return permiRoleBaseDao.selectPermiRoleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(角色表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiRole> getPermiRoleByConditionDim(Map<String,Object> paramMap){
		return permiRoleBaseDao.selectPermiRoleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(角色表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiRole> getPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return permiRoleBaseDao.selectPermiRoleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(角色表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiRole> getPermiRoleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return permiRoleBaseDao.selectPermiRoleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(角色表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiRole getPermiRoleBySeqId(java.math.BigInteger id){
		return permiRoleBaseDao.selectPermiRoleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(角色表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiRoleCount(Map<String,Object> paramMap){
		return permiRoleBaseDao.selectPermiRoleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(角色表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiRoleCountDim(Map<String,Object> paramMap){
		return permiRoleBaseDao.selectPermiRoleCount(paramMap,true);
	}
	/**
	 * 往(角色表)新增一条记录
	 * @param permiRole
	 * @return
	 */
	@Override
	public int insertPermiRole(PermiRole permiRole){
		return permiRoleBaseDao.insertPermiRole(permiRole);
	}
	/**
	 * 批量新增(角色表)
	 * @param permiRoleList
	 * @return
	 */
	@Override
	public int insertPermiRoleBatch(List<PermiRole> permiRoleList){
		return permiRoleBaseDao.insertPermiRoleBatch(permiRoleList);
	}
	/**
	 * 更新(角色表)信息
	 * @param permiRole
	 * @return
	 */
	@Override
	public int updatePermiRole(PermiRole permiRole){
		return permiRoleBaseDao.updatePermiRole(permiRole);
	}
	/**
	 * 批量更新(角色表)信息
	 * @param permiRoleList
	 * @return
	 */
	@Override
	public int updatePermiRoleBatch(List<PermiRole> permiRoleList){
		return permiRoleBaseDao.updatePermiRoleBatch(permiRoleList);
	}
	/**
	 * 根据序列号删除(角色表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiRoleLogic(java.math.BigInteger id){
		return permiRoleBaseDao.deletePermiRoleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(角色表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiRoleLogicBatch(List<java.math.BigInteger> idList){
		return permiRoleBaseDao.deletePermiRoleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(角色表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiRole(java.math.BigInteger id){
//		return permiRoleBaseDao.deletePermiRole(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(角色表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleBatch(List<java.math.BigInteger> idList){
//		return permiRoleBaseDao.deletePermiRoleBatch(idList);
//	}
	
}
