package com.cnfantasia.server.domainbase.permiFunction.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.permiFunction.dao.IPermiFunctionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiFunction.entity.PermiFunction;

/**
 * 描述:(功能表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PermiFunctionBaseService implements IPermiFunctionBaseService{
	
	private IPermiFunctionBaseDao permiFunctionBaseDao;
	public void setPermiFunctionBaseDao(IPermiFunctionBaseDao permiFunctionBaseDao) {
		this.permiFunctionBaseDao = permiFunctionBaseDao;
	}
	/**
	 * 根据条件查询(功能表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiFunction> getPermiFunctionByCondition(Map<String,Object> paramMap){
		return permiFunctionBaseDao.selectPermiFunctionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(功能表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PermiFunction> getPermiFunctionByConditionDim(Map<String,Object> paramMap){
		return permiFunctionBaseDao.selectPermiFunctionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(功能表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiFunction> getPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return permiFunctionBaseDao.selectPermiFunctionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(功能表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PermiFunction> getPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return permiFunctionBaseDao.selectPermiFunctionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(功能表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiFunction getPermiFunctionBySeqId(java.math.BigInteger id){
		return permiFunctionBaseDao.selectPermiFunctionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(功能表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiFunctionCount(Map<String,Object> paramMap){
		return permiFunctionBaseDao.selectPermiFunctionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(功能表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPermiFunctionCountDim(Map<String,Object> paramMap){
		return permiFunctionBaseDao.selectPermiFunctionCount(paramMap,true);
	}
	/**
	 * 往(功能表)新增一条记录
	 * @param permiFunction
	 * @return
	 */
	@Override
	public int insertPermiFunction(PermiFunction permiFunction){
		return permiFunctionBaseDao.insertPermiFunction(permiFunction);
	}
	/**
	 * 批量新增(功能表)
	 * @param permiFunctionList
	 * @return
	 */
	@Override
	public int insertPermiFunctionBatch(List<PermiFunction> permiFunctionList){
		return permiFunctionBaseDao.insertPermiFunctionBatch(permiFunctionList);
	}
	/**
	 * 更新(功能表)信息
	 * @param permiFunction
	 * @return
	 */
	@Override
	public int updatePermiFunction(PermiFunction permiFunction){
		return permiFunctionBaseDao.updatePermiFunction(permiFunction);
	}
	/**
	 * 批量更新(功能表)信息
	 * @param permiFunctionList
	 * @return
	 */
	@Override
	public int updatePermiFunctionBatch(List<PermiFunction> permiFunctionList){
		return permiFunctionBaseDao.updatePermiFunctionBatch(permiFunctionList);
	}
	/**
	 * 根据序列号删除(功能表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiFunctionLogic(java.math.BigInteger id){
		return permiFunctionBaseDao.deletePermiFunctionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(功能表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiFunctionLogicBatch(List<java.math.BigInteger> idList){
		return permiFunctionBaseDao.deletePermiFunctionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(功能表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiFunction(java.math.BigInteger id){
//		return permiFunctionBaseDao.deletePermiFunction(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(功能表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiFunctionBatch(List<java.math.BigInteger> idList){
//		return permiFunctionBaseDao.deletePermiFunctionBatch(idList);
//	}
	
}
