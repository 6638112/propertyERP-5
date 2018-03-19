package com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.dao.IOmsUserHasTOmsPermiRoleBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;

/**
 * 描述:(OMS用户角色关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsUserHasTOmsPermiRoleBaseService implements IOmsUserHasTOmsPermiRoleBaseService{
	
	private IOmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao;
	public void setOmsUserHasTOmsPermiRoleBaseDao(IOmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao) {
		this.omsUserHasTOmsPermiRoleBaseDao = omsUserHasTOmsPermiRoleBaseDao;
	}
	/**
	 * 根据条件查询(OMS用户角色关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap){
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS用户角色关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByConditionDim(Map<String,Object> paramMap){
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS用户角色关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS用户角色关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS用户角色关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUserHasTOmsPermiRole getOmsUserHasTOmsPermiRoleBySeqId(java.math.BigInteger id){
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户角色关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserHasTOmsPermiRoleCount(Map<String,Object> paramMap){
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户角色关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserHasTOmsPermiRoleCountDim(Map<String,Object> paramMap){
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleCount(paramMap,true);
	}
	/**
	 * 往(OMS用户角色关系)新增一条记录
	 * @param omsUserHasTOmsPermiRole
	 * @return
	 */
	@Override
	public int insertOmsUserHasTOmsPermiRole(OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole){
		return omsUserHasTOmsPermiRoleBaseDao.insertOmsUserHasTOmsPermiRole(omsUserHasTOmsPermiRole);
	}
	/**
	 * 批量新增(OMS用户角色关系)
	 * @param omsUserHasTOmsPermiRoleList
	 * @return
	 */
	@Override
	public int insertOmsUserHasTOmsPermiRoleBatch(List<OmsUserHasTOmsPermiRole> omsUserHasTOmsPermiRoleList){
		return omsUserHasTOmsPermiRoleBaseDao.insertOmsUserHasTOmsPermiRoleBatch(omsUserHasTOmsPermiRoleList);
	}
	/**
	 * 更新(OMS用户角色关系)信息
	 * @param omsUserHasTOmsPermiRole
	 * @return
	 */
	@Override
	public int updateOmsUserHasTOmsPermiRole(OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole){
		return omsUserHasTOmsPermiRoleBaseDao.updateOmsUserHasTOmsPermiRole(omsUserHasTOmsPermiRole);
	}
	/**
	 * 批量更新(OMS用户角色关系)信息
	 * @param omsUserHasTOmsPermiRoleList
	 * @return
	 */
	@Override
	public int updateOmsUserHasTOmsPermiRoleBatch(List<OmsUserHasTOmsPermiRole> omsUserHasTOmsPermiRoleList){
		return omsUserHasTOmsPermiRoleBaseDao.updateOmsUserHasTOmsPermiRoleBatch(omsUserHasTOmsPermiRoleList);
	}
	/**
	 * 根据序列号删除(OMS用户角色关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTOmsPermiRoleLogic(java.math.BigInteger id){
		return omsUserHasTOmsPermiRoleBaseDao.deleteOmsUserHasTOmsPermiRoleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(OMS用户角色关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTOmsPermiRoleLogicBatch(List<java.math.BigInteger> idList){
		return omsUserHasTOmsPermiRoleBaseDao.deleteOmsUserHasTOmsPermiRoleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(OMS用户角色关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTOmsPermiRole(java.math.BigInteger id){
//		return omsUserHasTOmsPermiRoleBaseDao.deleteOmsUserHasTOmsPermiRole(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS用户角色关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTOmsPermiRoleBatch(List<java.math.BigInteger> idList){
//		return omsUserHasTOmsPermiRoleBaseDao.deleteOmsUserHasTOmsPermiRoleBatch(idList);
//	}
	
}
