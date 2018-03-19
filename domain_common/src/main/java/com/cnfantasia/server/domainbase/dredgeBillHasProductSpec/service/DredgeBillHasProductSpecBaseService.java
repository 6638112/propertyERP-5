package com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.dao.IDredgeBillHasProductSpecBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.entity.DredgeBillHasProductSpec;

/**
 * 描述:(维修服务商品规格表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillHasProductSpecBaseService implements IDredgeBillHasProductSpecBaseService{
	
	private IDredgeBillHasProductSpecBaseDao dredgeBillHasProductSpecBaseDao;
	public void setDredgeBillHasProductSpecBaseDao(IDredgeBillHasProductSpecBaseDao dredgeBillHasProductSpecBaseDao) {
		this.dredgeBillHasProductSpecBaseDao = dredgeBillHasProductSpecBaseDao;
	}
	/**
	 * 根据条件查询(维修服务商品规格表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByCondition(Map<String,Object> paramMap){
		return dredgeBillHasProductSpecBaseDao.selectDredgeBillHasProductSpecByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修服务商品规格表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByConditionDim(Map<String,Object> paramMap){
		return dredgeBillHasProductSpecBaseDao.selectDredgeBillHasProductSpecByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修服务商品规格表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillHasProductSpecBaseDao.selectDredgeBillHasProductSpecByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修服务商品规格表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillHasProductSpecBaseDao.selectDredgeBillHasProductSpecByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修服务商品规格表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillHasProductSpec getDredgeBillHasProductSpecBySeqId(java.math.BigInteger id){
		return dredgeBillHasProductSpecBaseDao.selectDredgeBillHasProductSpecBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillHasProductSpecCount(Map<String,Object> paramMap){
		return dredgeBillHasProductSpecBaseDao.selectDredgeBillHasProductSpecCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillHasProductSpecCountDim(Map<String,Object> paramMap){
		return dredgeBillHasProductSpecBaseDao.selectDredgeBillHasProductSpecCount(paramMap,true);
	}
	/**
	 * 往(维修服务商品规格表)新增一条记录
	 * @param dredgeBillHasProductSpec
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProductSpec(DredgeBillHasProductSpec dredgeBillHasProductSpec){
		return dredgeBillHasProductSpecBaseDao.insertDredgeBillHasProductSpec(dredgeBillHasProductSpec);
	}
	/**
	 * 批量新增(维修服务商品规格表)
	 * @param dredgeBillHasProductSpecList
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProductSpecBatch(List<DredgeBillHasProductSpec> dredgeBillHasProductSpecList){
		return dredgeBillHasProductSpecBaseDao.insertDredgeBillHasProductSpecBatch(dredgeBillHasProductSpecList);
	}
	/**
	 * 更新(维修服务商品规格表)信息
	 * @param dredgeBillHasProductSpec
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProductSpec(DredgeBillHasProductSpec dredgeBillHasProductSpec){
		return dredgeBillHasProductSpecBaseDao.updateDredgeBillHasProductSpec(dredgeBillHasProductSpec);
	}
	/**
	 * 批量更新(维修服务商品规格表)信息
	 * @param dredgeBillHasProductSpecList
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProductSpecBatch(List<DredgeBillHasProductSpec> dredgeBillHasProductSpecList){
		return dredgeBillHasProductSpecBaseDao.updateDredgeBillHasProductSpecBatch(dredgeBillHasProductSpecList);
	}
	/**
	 * 根据序列号删除(维修服务商品规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProductSpecLogic(java.math.BigInteger id){
		return dredgeBillHasProductSpecBaseDao.deleteDredgeBillHasProductSpecLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修服务商品规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProductSpecLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillHasProductSpecBaseDao.deleteDredgeBillHasProductSpecLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修服务商品规格表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProductSpec(java.math.BigInteger id){
//		return dredgeBillHasProductSpecBaseDao.deleteDredgeBillHasProductSpec(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProductSpecBatch(List<java.math.BigInteger> idList){
//		return dredgeBillHasProductSpecBaseDao.deleteDredgeBillHasProductSpecBatch(idList);
//	}
	
}
