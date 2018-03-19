package com.cnfantasia.server.domainbase.sourceOfSupply.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.sourceOfSupply.dao.ISourceOfSupplyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.sourceOfSupply.entity.SourceOfSupply;

/**
 * 描述:(供应商的货源地) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SourceOfSupplyBaseService implements ISourceOfSupplyBaseService{
	
	private ISourceOfSupplyBaseDao sourceOfSupplyBaseDao;
	public void setSourceOfSupplyBaseDao(ISourceOfSupplyBaseDao sourceOfSupplyBaseDao) {
		this.sourceOfSupplyBaseDao = sourceOfSupplyBaseDao;
	}
	/**
	 * 根据条件查询(供应商的货源地)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SourceOfSupply> getSourceOfSupplyByCondition(Map<String,Object> paramMap){
		return sourceOfSupplyBaseDao.selectSourceOfSupplyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(供应商的货源地)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SourceOfSupply> getSourceOfSupplyByConditionDim(Map<String,Object> paramMap){
		return sourceOfSupplyBaseDao.selectSourceOfSupplyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(供应商的货源地)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SourceOfSupply> getSourceOfSupplyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return sourceOfSupplyBaseDao.selectSourceOfSupplyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(供应商的货源地)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SourceOfSupply> getSourceOfSupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return sourceOfSupplyBaseDao.selectSourceOfSupplyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(供应商的货源地)信息
	 * @param id
	 * @return
	 */
	@Override
	public SourceOfSupply getSourceOfSupplyBySeqId(java.math.BigInteger id){
		return sourceOfSupplyBaseDao.selectSourceOfSupplyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(供应商的货源地)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSourceOfSupplyCount(Map<String,Object> paramMap){
		return sourceOfSupplyBaseDao.selectSourceOfSupplyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(供应商的货源地)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSourceOfSupplyCountDim(Map<String,Object> paramMap){
		return sourceOfSupplyBaseDao.selectSourceOfSupplyCount(paramMap,true);
	}
	/**
	 * 往(供应商的货源地)新增一条记录
	 * @param sourceOfSupply
	 * @return
	 */
	@Override
	public int insertSourceOfSupply(SourceOfSupply sourceOfSupply){
		return sourceOfSupplyBaseDao.insertSourceOfSupply(sourceOfSupply);
	}
	/**
	 * 批量新增(供应商的货源地)
	 * @param sourceOfSupplyList
	 * @return
	 */
	@Override
	public int insertSourceOfSupplyBatch(List<SourceOfSupply> sourceOfSupplyList){
		return sourceOfSupplyBaseDao.insertSourceOfSupplyBatch(sourceOfSupplyList);
	}
	/**
	 * 更新(供应商的货源地)信息
	 * @param sourceOfSupply
	 * @return
	 */
	@Override
	public int updateSourceOfSupply(SourceOfSupply sourceOfSupply){
		return sourceOfSupplyBaseDao.updateSourceOfSupply(sourceOfSupply);
	}
	/**
	 * 批量更新(供应商的货源地)信息
	 * @param sourceOfSupplyList
	 * @return
	 */
	@Override
	public int updateSourceOfSupplyBatch(List<SourceOfSupply> sourceOfSupplyList){
		return sourceOfSupplyBaseDao.updateSourceOfSupplyBatch(sourceOfSupplyList);
	}
	/**
	 * 根据序列号删除(供应商的货源地)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSourceOfSupplyLogic(java.math.BigInteger id){
		return sourceOfSupplyBaseDao.deleteSourceOfSupplyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(供应商的货源地)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSourceOfSupplyLogicBatch(List<java.math.BigInteger> idList){
		return sourceOfSupplyBaseDao.deleteSourceOfSupplyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(供应商的货源地)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSourceOfSupply(java.math.BigInteger id){
//		return sourceOfSupplyBaseDao.deleteSourceOfSupply(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商的货源地)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSourceOfSupplyBatch(List<java.math.BigInteger> idList){
//		return sourceOfSupplyBaseDao.deleteSourceOfSupplyBatch(idList);
//	}
	
}
