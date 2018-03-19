package com.cnfantasia.server.domainbase.dredgeProductSpecification.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeProductSpecification.dao.IDredgeProductSpecificationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;

/**
 * 描述:(维修服务商品规格表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeProductSpecificationBaseService implements IDredgeProductSpecificationBaseService{
	
	private IDredgeProductSpecificationBaseDao dredgeProductSpecificationBaseDao;
	public void setDredgeProductSpecificationBaseDao(IDredgeProductSpecificationBaseDao dredgeProductSpecificationBaseDao) {
		this.dredgeProductSpecificationBaseDao = dredgeProductSpecificationBaseDao;
	}
	/**
	 * 根据条件查询(维修服务商品规格表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeProductSpecification> getDredgeProductSpecificationByCondition(Map<String,Object> paramMap){
		return dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修服务商品规格表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeProductSpecification> getDredgeProductSpecificationByConditionDim(Map<String,Object> paramMap){
		return dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修服务商品规格表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeProductSpecification> getDredgeProductSpecificationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修服务商品规格表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeProductSpecification> getDredgeProductSpecificationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修服务商品规格表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeProductSpecification getDredgeProductSpecificationBySeqId(java.math.BigInteger id){
		return dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeProductSpecificationCount(Map<String,Object> paramMap){
		return dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeProductSpecificationCountDim(Map<String,Object> paramMap){
		return dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationCount(paramMap,true);
	}
	/**
	 * 往(维修服务商品规格表)新增一条记录
	 * @param dredgeProductSpecification
	 * @return
	 */
	@Override
	public int insertDredgeProductSpecification(DredgeProductSpecification dredgeProductSpecification){
		return dredgeProductSpecificationBaseDao.insertDredgeProductSpecification(dredgeProductSpecification);
	}
	/**
	 * 批量新增(维修服务商品规格表)
	 * @param dredgeProductSpecificationList
	 * @return
	 */
	@Override
	public int insertDredgeProductSpecificationBatch(List<DredgeProductSpecification> dredgeProductSpecificationList){
		return dredgeProductSpecificationBaseDao.insertDredgeProductSpecificationBatch(dredgeProductSpecificationList);
	}
	/**
	 * 更新(维修服务商品规格表)信息
	 * @param dredgeProductSpecification
	 * @return
	 */
	@Override
	public int updateDredgeProductSpecification(DredgeProductSpecification dredgeProductSpecification){
		return dredgeProductSpecificationBaseDao.updateDredgeProductSpecification(dredgeProductSpecification);
	}
	/**
	 * 批量更新(维修服务商品规格表)信息
	 * @param dredgeProductSpecificationList
	 * @return
	 */
	@Override
	public int updateDredgeProductSpecificationBatch(List<DredgeProductSpecification> dredgeProductSpecificationList){
		return dredgeProductSpecificationBaseDao.updateDredgeProductSpecificationBatch(dredgeProductSpecificationList);
	}
	/**
	 * 根据序列号删除(维修服务商品规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductSpecificationLogic(java.math.BigInteger id){
		return dredgeProductSpecificationBaseDao.deleteDredgeProductSpecificationLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修服务商品规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductSpecificationLogicBatch(List<java.math.BigInteger> idList){
		return dredgeProductSpecificationBaseDao.deleteDredgeProductSpecificationLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修服务商品规格表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProductSpecification(java.math.BigInteger id){
//		return dredgeProductSpecificationBaseDao.deleteDredgeProductSpecification(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProductSpecificationBatch(List<java.math.BigInteger> idList){
//		return dredgeProductSpecificationBaseDao.deleteDredgeProductSpecificationBatch(idList);
//	}
	
}
