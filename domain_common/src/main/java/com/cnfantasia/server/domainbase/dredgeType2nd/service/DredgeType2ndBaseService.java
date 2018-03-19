package com.cnfantasia.server.domainbase.dredgeType2nd.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeType2nd.dao.IDredgeType2ndBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeType2nd.entity.DredgeType2nd;

/**
 * 描述:(疏通二级类型) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeType2ndBaseService implements IDredgeType2ndBaseService{
	
	private IDredgeType2ndBaseDao dredgeType2ndBaseDao;
	public void setDredgeType2ndBaseDao(IDredgeType2ndBaseDao dredgeType2ndBaseDao) {
		this.dredgeType2ndBaseDao = dredgeType2ndBaseDao;
	}
	/**
	 * 根据条件查询(疏通二级类型)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeType2nd> getDredgeType2ndByCondition(Map<String,Object> paramMap){
		return dredgeType2ndBaseDao.selectDredgeType2ndByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通二级类型)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeType2nd> getDredgeType2ndByConditionDim(Map<String,Object> paramMap){
		return dredgeType2ndBaseDao.selectDredgeType2ndByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通二级类型)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeType2nd> getDredgeType2ndByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeType2ndBaseDao.selectDredgeType2ndByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通二级类型)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeType2nd> getDredgeType2ndByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeType2ndBaseDao.selectDredgeType2ndByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通二级类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeType2nd getDredgeType2ndBySeqId(java.math.BigInteger id){
		return dredgeType2ndBaseDao.selectDredgeType2ndBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通二级类型)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeType2ndCount(Map<String,Object> paramMap){
		return dredgeType2ndBaseDao.selectDredgeType2ndCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通二级类型)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeType2ndCountDim(Map<String,Object> paramMap){
		return dredgeType2ndBaseDao.selectDredgeType2ndCount(paramMap,true);
	}
	/**
	 * 往(疏通二级类型)新增一条记录
	 * @param dredgeType2nd
	 * @return
	 */
	@Override
	public int insertDredgeType2nd(DredgeType2nd dredgeType2nd){
		return dredgeType2ndBaseDao.insertDredgeType2nd(dredgeType2nd);
	}
	/**
	 * 批量新增(疏通二级类型)
	 * @param dredgeType2ndList
	 * @return
	 */
	@Override
	public int insertDredgeType2ndBatch(List<DredgeType2nd> dredgeType2ndList){
		return dredgeType2ndBaseDao.insertDredgeType2ndBatch(dredgeType2ndList);
	}
	/**
	 * 更新(疏通二级类型)信息
	 * @param dredgeType2nd
	 * @return
	 */
	@Override
	public int updateDredgeType2nd(DredgeType2nd dredgeType2nd){
		return dredgeType2ndBaseDao.updateDredgeType2nd(dredgeType2nd);
	}
	/**
	 * 批量更新(疏通二级类型)信息
	 * @param dredgeType2ndList
	 * @return
	 */
	@Override
	public int updateDredgeType2ndBatch(List<DredgeType2nd> dredgeType2ndList){
		return dredgeType2ndBaseDao.updateDredgeType2ndBatch(dredgeType2ndList);
	}
	/**
	 * 根据序列号删除(疏通二级类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeType2ndLogic(java.math.BigInteger id){
		return dredgeType2ndBaseDao.deleteDredgeType2ndLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(疏通二级类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeType2ndLogicBatch(List<java.math.BigInteger> idList){
		return dredgeType2ndBaseDao.deleteDredgeType2ndLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(疏通二级类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeType2nd(java.math.BigInteger id){
//		return dredgeType2ndBaseDao.deleteDredgeType2nd(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通二级类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeType2ndBatch(List<java.math.BigInteger> idList){
//		return dredgeType2ndBaseDao.deleteDredgeType2ndBatch(idList);
//	}
	
}
