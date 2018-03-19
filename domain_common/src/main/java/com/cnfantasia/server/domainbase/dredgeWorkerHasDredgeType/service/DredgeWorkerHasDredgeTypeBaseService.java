package com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.dao.IDredgeWorkerHasDredgeTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.entity.DredgeWorkerHasDredgeType;

/**
 * 描述:(疏通师傅支持的疏通类型) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeWorkerHasDredgeTypeBaseService implements IDredgeWorkerHasDredgeTypeBaseService{
	
	private IDredgeWorkerHasDredgeTypeBaseDao dredgeWorkerHasDredgeTypeBaseDao;
	public void setDredgeWorkerHasDredgeTypeBaseDao(IDredgeWorkerHasDredgeTypeBaseDao dredgeWorkerHasDredgeTypeBaseDao) {
		this.dredgeWorkerHasDredgeTypeBaseDao = dredgeWorkerHasDredgeTypeBaseDao;
	}
	/**
	 * 根据条件查询(疏通师傅支持的疏通类型)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap){
		return dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通师傅支持的疏通类型)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByConditionDim(Map<String,Object> paramMap){
		return dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通师傅支持的疏通类型)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通师傅支持的疏通类型)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerHasDredgeType> getDredgeWorkerHasDredgeTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通师傅支持的疏通类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorkerHasDredgeType getDredgeWorkerHasDredgeTypeBySeqId(java.math.BigInteger id){
		return dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅支持的疏通类型)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerHasDredgeTypeCount(Map<String,Object> paramMap){
		return dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅支持的疏通类型)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerHasDredgeTypeCountDim(Map<String,Object> paramMap){
		return dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeCount(paramMap,true);
	}
	/**
	 * 往(疏通师傅支持的疏通类型)新增一条记录
	 * @param dredgeWorkerHasDredgeType
	 * @return
	 */
	@Override
	public int insertDredgeWorkerHasDredgeType(DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType){
		return dredgeWorkerHasDredgeTypeBaseDao.insertDredgeWorkerHasDredgeType(dredgeWorkerHasDredgeType);
	}
	/**
	 * 批量新增(疏通师傅支持的疏通类型)
	 * @param dredgeWorkerHasDredgeTypeList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerHasDredgeTypeBatch(List<DredgeWorkerHasDredgeType> dredgeWorkerHasDredgeTypeList){
		return dredgeWorkerHasDredgeTypeBaseDao.insertDredgeWorkerHasDredgeTypeBatch(dredgeWorkerHasDredgeTypeList);
	}
	/**
	 * 更新(疏通师傅支持的疏通类型)信息
	 * @param dredgeWorkerHasDredgeType
	 * @return
	 */
	@Override
	public int updateDredgeWorkerHasDredgeType(DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType){
		return dredgeWorkerHasDredgeTypeBaseDao.updateDredgeWorkerHasDredgeType(dredgeWorkerHasDredgeType);
	}
	/**
	 * 批量更新(疏通师傅支持的疏通类型)信息
	 * @param dredgeWorkerHasDredgeTypeList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerHasDredgeTypeBatch(List<DredgeWorkerHasDredgeType> dredgeWorkerHasDredgeTypeList){
		return dredgeWorkerHasDredgeTypeBaseDao.updateDredgeWorkerHasDredgeTypeBatch(dredgeWorkerHasDredgeTypeList);
	}
	/**
	 * 根据序列号删除(疏通师傅支持的疏通类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerHasDredgeTypeLogic(java.math.BigInteger id){
		return dredgeWorkerHasDredgeTypeBaseDao.deleteDredgeWorkerHasDredgeTypeLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(疏通师傅支持的疏通类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerHasDredgeTypeLogicBatch(List<java.math.BigInteger> idList){
		return dredgeWorkerHasDredgeTypeBaseDao.deleteDredgeWorkerHasDredgeTypeLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(疏通师傅支持的疏通类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerHasDredgeType(java.math.BigInteger id){
//		return dredgeWorkerHasDredgeTypeBaseDao.deleteDredgeWorkerHasDredgeType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅支持的疏通类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerHasDredgeTypeBatch(List<java.math.BigInteger> idList){
//		return dredgeWorkerHasDredgeTypeBaseDao.deleteDredgeWorkerHasDredgeTypeBatch(idList);
//	}
	
}
