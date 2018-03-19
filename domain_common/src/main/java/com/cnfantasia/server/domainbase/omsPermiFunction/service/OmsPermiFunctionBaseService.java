package com.cnfantasia.server.domainbase.omsPermiFunction.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.omsPermiFunction.dao.IOmsPermiFunctionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;

/**
 * 描述:(OMS功能表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsPermiFunctionBaseService implements IOmsPermiFunctionBaseService{
	
	private IOmsPermiFunctionBaseDao omsPermiFunctionBaseDao;
	public void setOmsPermiFunctionBaseDao(IOmsPermiFunctionBaseDao omsPermiFunctionBaseDao) {
		this.omsPermiFunctionBaseDao = omsPermiFunctionBaseDao;
	}
	/**
	 * 根据条件查询(OMS功能表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsPermiFunction> getOmsPermiFunctionByCondition(Map<String,Object> paramMap){
		return omsPermiFunctionBaseDao.selectOmsPermiFunctionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS功能表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsPermiFunction> getOmsPermiFunctionByConditionDim(Map<String,Object> paramMap){
		return omsPermiFunctionBaseDao.selectOmsPermiFunctionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS功能表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsPermiFunction> getOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiFunctionBaseDao.selectOmsPermiFunctionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS功能表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsPermiFunction> getOmsPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiFunctionBaseDao.selectOmsPermiFunctionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS功能表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsPermiFunction getOmsPermiFunctionBySeqId(java.math.BigInteger id){
		return omsPermiFunctionBaseDao.selectOmsPermiFunctionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(OMS功能表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsPermiFunctionCount(Map<String,Object> paramMap){
		return omsPermiFunctionBaseDao.selectOmsPermiFunctionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS功能表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsPermiFunctionCountDim(Map<String,Object> paramMap){
		return omsPermiFunctionBaseDao.selectOmsPermiFunctionCount(paramMap,true);
	}
	/**
	 * 往(OMS功能表)新增一条记录
	 * @param omsPermiFunction
	 * @return
	 */
	@Override
	public int insertOmsPermiFunction(OmsPermiFunction omsPermiFunction){
		return omsPermiFunctionBaseDao.insertOmsPermiFunction(omsPermiFunction);
	}
	/**
	 * 批量新增(OMS功能表)
	 * @param omsPermiFunctionList
	 * @return
	 */
	@Override
	public int insertOmsPermiFunctionBatch(List<OmsPermiFunction> omsPermiFunctionList){
		return omsPermiFunctionBaseDao.insertOmsPermiFunctionBatch(omsPermiFunctionList);
	}
	/**
	 * 更新(OMS功能表)信息
	 * @param omsPermiFunction
	 * @return
	 */
	@Override
	public int updateOmsPermiFunction(OmsPermiFunction omsPermiFunction){
		return omsPermiFunctionBaseDao.updateOmsPermiFunction(omsPermiFunction);
	}
	/**
	 * 批量更新(OMS功能表)信息
	 * @param omsPermiFunctionList
	 * @return
	 */
	@Override
	public int updateOmsPermiFunctionBatch(List<OmsPermiFunction> omsPermiFunctionList){
		return omsPermiFunctionBaseDao.updateOmsPermiFunctionBatch(omsPermiFunctionList);
	}
	/**
	 * 根据序列号删除(OMS功能表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiFunctionLogic(java.math.BigInteger id){
		return omsPermiFunctionBaseDao.deleteOmsPermiFunctionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(OMS功能表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiFunctionLogicBatch(List<java.math.BigInteger> idList){
		return omsPermiFunctionBaseDao.deleteOmsPermiFunctionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(OMS功能表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiFunction(java.math.BigInteger id){
//		return omsPermiFunctionBaseDao.deleteOmsPermiFunction(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS功能表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiFunctionBatch(List<java.math.BigInteger> idList){
//		return omsPermiFunctionBaseDao.deleteOmsPermiFunctionBatch(idList);
//	}
	
}
