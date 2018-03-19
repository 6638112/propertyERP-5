package com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.dao.IPermiRoleHasTPermiFunctionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.entity.PermiRoleHasTPermiFunction;

/**
 * 描述:(角色功能关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PermiRoleHasTPermiFunctionBaseService implements IPermiRoleHasTPermiFunctionBaseService{
	
	private IPermiRoleHasTPermiFunctionBaseDao permiRoleHasTPermiFunctionBaseDao;
	public void setPermiRoleHasTPermiFunctionBaseDao(IPermiRoleHasTPermiFunctionBaseDao permiRoleHasTPermiFunctionBaseDao) {
		this.permiRoleHasTPermiFunctionBaseDao = permiRoleHasTPermiFunctionBaseDao;
	}
	/**
	 * 根据条件查询(角色功能关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap){
		return permiRoleHasTPermiFunctionBaseDao.selectPermiRoleHasTPermiFunctionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(角色功能关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByConditionDim(Map<String,Object> paramMap){
		return permiRoleHasTPermiFunctionBaseDao.selectPermiRoleHasTPermiFunctionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(角色功能关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return permiRoleHasTPermiFunctionBaseDao.selectPermiRoleHasTPermiFunctionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(角色功能关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return permiRoleHasTPermiFunctionBaseDao.selectPermiRoleHasTPermiFunctionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(角色功能关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiRoleHasTPermiFunction getPermiRoleHasTPermiFunctionBySeqId(java.math.BigInteger id){
		return permiRoleHasTPermiFunctionBaseDao.selectPermiRoleHasTPermiFunctionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(角色功能关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiRoleHasTPermiFunctionCount(Map<String,Object> paramMap){
		return permiRoleHasTPermiFunctionBaseDao.selectPermiRoleHasTPermiFunctionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(角色功能关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiRoleHasTPermiFunctionCountDim(Map<String,Object> paramMap){
		return permiRoleHasTPermiFunctionBaseDao.selectPermiRoleHasTPermiFunctionCount(paramMap,true);
	}
	/**
	 * 往(角色功能关系)新增一条记录
	 * @param permiRoleHasTPermiFunction
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTPermiFunction(PermiRoleHasTPermiFunction permiRoleHasTPermiFunction){
		return permiRoleHasTPermiFunctionBaseDao.insertPermiRoleHasTPermiFunction(permiRoleHasTPermiFunction);
	}
	/**
	 * 批量新增(角色功能关系)
	 * @param permiRoleHasTPermiFunctionList
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTPermiFunctionBatch(List<PermiRoleHasTPermiFunction> permiRoleHasTPermiFunctionList){
		return permiRoleHasTPermiFunctionBaseDao.insertPermiRoleHasTPermiFunctionBatch(permiRoleHasTPermiFunctionList);
	}
	/**
	 * 更新(角色功能关系)信息
	 * @param permiRoleHasTPermiFunction
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTPermiFunction(PermiRoleHasTPermiFunction permiRoleHasTPermiFunction){
		return permiRoleHasTPermiFunctionBaseDao.updatePermiRoleHasTPermiFunction(permiRoleHasTPermiFunction);
	}
	/**
	 * 批量更新(角色功能关系)信息
	 * @param permiRoleHasTPermiFunctionList
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTPermiFunctionBatch(List<PermiRoleHasTPermiFunction> permiRoleHasTPermiFunctionList){
		return permiRoleHasTPermiFunctionBaseDao.updatePermiRoleHasTPermiFunctionBatch(permiRoleHasTPermiFunctionList);
	}
	/**
	 * 根据序列号删除(角色功能关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTPermiFunctionLogic(java.math.BigInteger id){
		return permiRoleHasTPermiFunctionBaseDao.deletePermiRoleHasTPermiFunctionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(角色功能关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTPermiFunctionLogicBatch(List<java.math.BigInteger> idList){
		return permiRoleHasTPermiFunctionBaseDao.deletePermiRoleHasTPermiFunctionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(角色功能关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTPermiFunction(java.math.BigInteger id){
//		return permiRoleHasTPermiFunctionBaseDao.deletePermiRoleHasTPermiFunction(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(角色功能关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTPermiFunctionBatch(List<java.math.BigInteger> idList){
//		return permiRoleHasTPermiFunctionBaseDao.deletePermiRoleHasTPermiFunctionBatch(idList);
//	}
	
}
