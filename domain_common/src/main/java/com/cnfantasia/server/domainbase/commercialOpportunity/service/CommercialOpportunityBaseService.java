package com.cnfantasia.server.domainbase.commercialOpportunity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commercialOpportunity.dao.ICommercialOpportunityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commercialOpportunity.entity.CommercialOpportunity;

/**
 * 描述:(商机) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommercialOpportunityBaseService implements ICommercialOpportunityBaseService{
	
	private ICommercialOpportunityBaseDao commercialOpportunityBaseDao;
	public void setCommercialOpportunityBaseDao(ICommercialOpportunityBaseDao commercialOpportunityBaseDao) {
		this.commercialOpportunityBaseDao = commercialOpportunityBaseDao;
	}
	/**
	 * 根据条件查询(商机)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommercialOpportunity> getCommercialOpportunityByCondition(Map<String,Object> paramMap){
		return commercialOpportunityBaseDao.selectCommercialOpportunityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商机)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommercialOpportunity> getCommercialOpportunityByConditionDim(Map<String,Object> paramMap){
		return commercialOpportunityBaseDao.selectCommercialOpportunityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商机)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommercialOpportunity> getCommercialOpportunityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commercialOpportunityBaseDao.selectCommercialOpportunityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商机)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommercialOpportunity> getCommercialOpportunityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commercialOpportunityBaseDao.selectCommercialOpportunityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商机)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommercialOpportunity getCommercialOpportunityBySeqId(java.math.BigInteger id){
		return commercialOpportunityBaseDao.selectCommercialOpportunityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商机)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommercialOpportunityCount(Map<String,Object> paramMap){
		return commercialOpportunityBaseDao.selectCommercialOpportunityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商机)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommercialOpportunityCountDim(Map<String,Object> paramMap){
		return commercialOpportunityBaseDao.selectCommercialOpportunityCount(paramMap,true);
	}
	/**
	 * 往(商机)新增一条记录
	 * @param commercialOpportunity
	 * @return
	 */
	@Override
	public int insertCommercialOpportunity(CommercialOpportunity commercialOpportunity){
		return commercialOpportunityBaseDao.insertCommercialOpportunity(commercialOpportunity);
	}
	/**
	 * 批量新增(商机)
	 * @param commercialOpportunityList
	 * @return
	 */
	@Override
	public int insertCommercialOpportunityBatch(List<CommercialOpportunity> commercialOpportunityList){
		return commercialOpportunityBaseDao.insertCommercialOpportunityBatch(commercialOpportunityList);
	}
	/**
	 * 更新(商机)信息
	 * @param commercialOpportunity
	 * @return
	 */
	@Override
	public int updateCommercialOpportunity(CommercialOpportunity commercialOpportunity){
		return commercialOpportunityBaseDao.updateCommercialOpportunity(commercialOpportunity);
	}
	/**
	 * 批量更新(商机)信息
	 * @param commercialOpportunityList
	 * @return
	 */
	@Override
	public int updateCommercialOpportunityBatch(List<CommercialOpportunity> commercialOpportunityList){
		return commercialOpportunityBaseDao.updateCommercialOpportunityBatch(commercialOpportunityList);
	}
	/**
	 * 根据序列号删除(商机)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommercialOpportunityLogic(java.math.BigInteger id){
		return commercialOpportunityBaseDao.deleteCommercialOpportunityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商机)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommercialOpportunityLogicBatch(List<java.math.BigInteger> idList){
		return commercialOpportunityBaseDao.deleteCommercialOpportunityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商机)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommercialOpportunity(java.math.BigInteger id){
//		return commercialOpportunityBaseDao.deleteCommercialOpportunity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商机)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommercialOpportunityBatch(List<java.math.BigInteger> idList){
//		return commercialOpportunityBaseDao.deleteCommercialOpportunityBatch(idList);
//	}
	
}
