package com.cnfantasia.server.domainbase.dredgeType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeType.dao.IDredgeTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;

/**
 * 描述:(疏通类型) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeTypeBaseService implements IDredgeTypeBaseService{
	
	private IDredgeTypeBaseDao dredgeTypeBaseDao;
	public void setDredgeTypeBaseDao(IDredgeTypeBaseDao dredgeTypeBaseDao) {
		this.dredgeTypeBaseDao = dredgeTypeBaseDao;
	}
	/**
	 * 根据条件查询(疏通类型)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeType> getDredgeTypeByCondition(Map<String,Object> paramMap){
		return dredgeTypeBaseDao.selectDredgeTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通类型)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeType> getDredgeTypeByConditionDim(Map<String,Object> paramMap){
		return dredgeTypeBaseDao.selectDredgeTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通类型)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeType> getDredgeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeTypeBaseDao.selectDredgeTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通类型)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeType> getDredgeTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeTypeBaseDao.selectDredgeTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeType getDredgeTypeBySeqId(java.math.BigInteger id){
		return dredgeTypeBaseDao.selectDredgeTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通类型)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeTypeCount(Map<String,Object> paramMap){
		return dredgeTypeBaseDao.selectDredgeTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通类型)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeTypeCountDim(Map<String,Object> paramMap){
		return dredgeTypeBaseDao.selectDredgeTypeCount(paramMap,true);
	}
	/**
	 * 往(疏通类型)新增一条记录
	 * @param dredgeType
	 * @return
	 */
	@Override
	public int insertDredgeType(DredgeType dredgeType){
		return dredgeTypeBaseDao.insertDredgeType(dredgeType);
	}
	/**
	 * 批量新增(疏通类型)
	 * @param dredgeTypeList
	 * @return
	 */
	@Override
	public int insertDredgeTypeBatch(List<DredgeType> dredgeTypeList){
		return dredgeTypeBaseDao.insertDredgeTypeBatch(dredgeTypeList);
	}
	/**
	 * 更新(疏通类型)信息
	 * @param dredgeType
	 * @return
	 */
	@Override
	public int updateDredgeType(DredgeType dredgeType){
		return dredgeTypeBaseDao.updateDredgeType(dredgeType);
	}
	/**
	 * 批量更新(疏通类型)信息
	 * @param dredgeTypeList
	 * @return
	 */
	@Override
	public int updateDredgeTypeBatch(List<DredgeType> dredgeTypeList){
		return dredgeTypeBaseDao.updateDredgeTypeBatch(dredgeTypeList);
	}
	/**
	 * 根据序列号删除(疏通类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypeLogic(java.math.BigInteger id){
		return dredgeTypeBaseDao.deleteDredgeTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(疏通类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypeLogicBatch(List<java.math.BigInteger> idList){
		return dredgeTypeBaseDao.deleteDredgeTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(疏通类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeType(java.math.BigInteger id){
//		return dredgeTypeBaseDao.deleteDredgeType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeTypeBatch(List<java.math.BigInteger> idList){
//		return dredgeTypeBaseDao.deleteDredgeTypeBatch(idList);
//	}
	
}
