package com.cnfantasia.server.domainbase.permiRoleHasTUser.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.permiRoleHasTUser.dao.IPermiRoleHasTUserBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRoleHasTUser.entity.PermiRoleHasTUser;

/**
 * 描述:(用户角色关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PermiRoleHasTUserBaseService implements IPermiRoleHasTUserBaseService{
	
	private IPermiRoleHasTUserBaseDao permiRoleHasTUserBaseDao;
	public void setPermiRoleHasTUserBaseDao(IPermiRoleHasTUserBaseDao permiRoleHasTUserBaseDao) {
		this.permiRoleHasTUserBaseDao = permiRoleHasTUserBaseDao;
	}
	/**
	 * 根据条件查询(用户角色关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByCondition(Map<String,Object> paramMap){
		return permiRoleHasTUserBaseDao.selectPermiRoleHasTUserByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户角色关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByConditionDim(Map<String,Object> paramMap){
		return permiRoleHasTUserBaseDao.selectPermiRoleHasTUserByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户角色关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return permiRoleHasTUserBaseDao.selectPermiRoleHasTUserByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户角色关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return permiRoleHasTUserBaseDao.selectPermiRoleHasTUserByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户角色关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiRoleHasTUser getPermiRoleHasTUserBySeqId(java.math.BigInteger id){
		return permiRoleHasTUserBaseDao.selectPermiRoleHasTUserBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户角色关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiRoleHasTUserCount(Map<String,Object> paramMap){
		return permiRoleHasTUserBaseDao.selectPermiRoleHasTUserCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户角色关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiRoleHasTUserCountDim(Map<String,Object> paramMap){
		return permiRoleHasTUserBaseDao.selectPermiRoleHasTUserCount(paramMap,true);
	}
	/**
	 * 往(用户角色关系)新增一条记录
	 * @param permiRoleHasTUser
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTUser(PermiRoleHasTUser permiRoleHasTUser){
		return permiRoleHasTUserBaseDao.insertPermiRoleHasTUser(permiRoleHasTUser);
	}
	/**
	 * 批量新增(用户角色关系)
	 * @param permiRoleHasTUserList
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTUserBatch(List<PermiRoleHasTUser> permiRoleHasTUserList){
		return permiRoleHasTUserBaseDao.insertPermiRoleHasTUserBatch(permiRoleHasTUserList);
	}
	/**
	 * 更新(用户角色关系)信息
	 * @param permiRoleHasTUser
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTUser(PermiRoleHasTUser permiRoleHasTUser){
		return permiRoleHasTUserBaseDao.updatePermiRoleHasTUser(permiRoleHasTUser);
	}
	/**
	 * 批量更新(用户角色关系)信息
	 * @param permiRoleHasTUserList
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTUserBatch(List<PermiRoleHasTUser> permiRoleHasTUserList){
		return permiRoleHasTUserBaseDao.updatePermiRoleHasTUserBatch(permiRoleHasTUserList);
	}
	/**
	 * 根据序列号删除(用户角色关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTUserLogic(java.math.BigInteger id){
		return permiRoleHasTUserBaseDao.deletePermiRoleHasTUserLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户角色关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTUserLogicBatch(List<java.math.BigInteger> idList){
		return permiRoleHasTUserBaseDao.deletePermiRoleHasTUserLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户角色关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTUser(java.math.BigInteger id){
//		return permiRoleHasTUserBaseDao.deletePermiRoleHasTUser(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户角色关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTUserBatch(List<java.math.BigInteger> idList){
//		return permiRoleHasTUserBaseDao.deletePermiRoleHasTUserBatch(idList);
//	}
	
}
