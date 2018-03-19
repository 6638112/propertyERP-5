package com.cnfantasia.server.msbase.omsPermiRoleHasTOmsPermiFunction.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.msbase.omsPermiRoleHasTOmsPermiFunction.dao.IOmsPermiRoleHasTOmsPermiFunctionBaseDao;
import com.cnfantasia.server.msbase.omsPermiRoleHasTOmsPermiFunction.entity.OmsPermiRoleHasTOmsPermiFunction;

/**
 * 描述:(OMS角色功能关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsPermiRoleHasTOmsPermiFunctionBaseService implements IOmsPermiRoleHasTOmsPermiFunctionBaseService{
	
	private IOmsPermiRoleHasTOmsPermiFunctionBaseDao omsPermiRoleHasTOmsPermiFunctionBaseDao;
	public void setOmsPermiRoleHasTOmsPermiFunctionBaseDao(IOmsPermiRoleHasTOmsPermiFunctionBaseDao omsPermiRoleHasTOmsPermiFunctionBaseDao) {
		this.omsPermiRoleHasTOmsPermiFunctionBaseDao = omsPermiRoleHasTOmsPermiFunctionBaseDao;
	}
	/**
	 * 根据条件查询(OMS角色功能关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.selectOmsPermiRoleHasTOmsPermiFunctionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS角色功能关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByConditionDim(Map<String,Object> paramMap){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.selectOmsPermiRoleHasTOmsPermiFunctionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS角色功能关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.selectOmsPermiRoleHasTOmsPermiFunctionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS角色功能关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.selectOmsPermiRoleHasTOmsPermiFunctionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS角色功能关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsPermiRoleHasTOmsPermiFunction getOmsPermiRoleHasTOmsPermiFunctionBySeqId(java.math.BigInteger id){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.selectOmsPermiRoleHasTOmsPermiFunctionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色功能关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsPermiRoleHasTOmsPermiFunctionCount(Map<String,Object> paramMap){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.selectOmsPermiRoleHasTOmsPermiFunctionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色功能关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsPermiRoleHasTOmsPermiFunctionCountDim(Map<String,Object> paramMap){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.selectOmsPermiRoleHasTOmsPermiFunctionCount(paramMap,true);
	}
	/**
	 * 往(OMS角色功能关系)新增一条记录
	 * @param omsPermiRoleHasTOmsPermiFunction
	 * @return
	 */
	@Override
	public int insertOmsPermiRoleHasTOmsPermiFunction(OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.insertOmsPermiRoleHasTOmsPermiFunction(omsPermiRoleHasTOmsPermiFunction);
	}
	/**
	 * 批量新增(OMS角色功能关系)
	 * @param omsPermiRoleHasTOmsPermiFunctionList
	 * @return
	 */
	@Override
	public int insertOmsPermiRoleHasTOmsPermiFunctionBatch(List<OmsPermiRoleHasTOmsPermiFunction> omsPermiRoleHasTOmsPermiFunctionList){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.insertOmsPermiRoleHasTOmsPermiFunctionBatch(omsPermiRoleHasTOmsPermiFunctionList);
	}
	/**
	 * 更新(OMS角色功能关系)信息
	 * @param omsPermiRoleHasTOmsPermiFunction
	 * @return
	 */
	@Override
	public int updateOmsPermiRoleHasTOmsPermiFunction(OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.updateOmsPermiRoleHasTOmsPermiFunction(omsPermiRoleHasTOmsPermiFunction);
	}
	/**
	 * 批量更新(OMS角色功能关系)信息
	 * @param omsPermiRoleHasTOmsPermiFunctionList
	 * @return
	 */
	@Override
	public int updateOmsPermiRoleHasTOmsPermiFunctionBatch(List<OmsPermiRoleHasTOmsPermiFunction> omsPermiRoleHasTOmsPermiFunctionList){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.updateOmsPermiRoleHasTOmsPermiFunctionBatch(omsPermiRoleHasTOmsPermiFunctionList);
	}
	/**
	 * 根据序列号删除(OMS角色功能关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleHasTOmsPermiFunctionLogic(java.math.BigInteger id){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.deleteOmsPermiRoleHasTOmsPermiFunctionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(OMS角色功能关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleHasTOmsPermiFunctionLogicBatch(List<java.math.BigInteger> idList){
		return omsPermiRoleHasTOmsPermiFunctionBaseDao.deleteOmsPermiRoleHasTOmsPermiFunctionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(OMS角色功能关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRoleHasTOmsPermiFunction(java.math.BigInteger id){
//		return omsPermiRoleHasTOmsPermiFunctionBaseDao.deleteOmsPermiRoleHasTOmsPermiFunction(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS角色功能关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRoleHasTOmsPermiFunctionBatch(List<java.math.BigInteger> idList){
//		return omsPermiRoleHasTOmsPermiFunctionBaseDao.deleteOmsPermiRoleHasTOmsPermiFunctionBatch(idList);
//	}
	
}
