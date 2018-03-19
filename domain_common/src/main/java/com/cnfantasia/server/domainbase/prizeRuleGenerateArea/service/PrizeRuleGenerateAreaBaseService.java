package com.cnfantasia.server.domainbase.prizeRuleGenerateArea.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeRuleGenerateArea.dao.IPrizeRuleGenerateAreaBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleGenerateArea.entity.PrizeRuleGenerateArea;

/**
 * 描述:(折扣生成规则-折扣区间分配因素) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeRuleGenerateAreaBaseService implements IPrizeRuleGenerateAreaBaseService{
	
	private IPrizeRuleGenerateAreaBaseDao prizeRuleGenerateAreaBaseDao;
	public void setPrizeRuleGenerateAreaBaseDao(IPrizeRuleGenerateAreaBaseDao prizeRuleGenerateAreaBaseDao) {
		this.prizeRuleGenerateAreaBaseDao = prizeRuleGenerateAreaBaseDao;
	}
	/**
	 * 根据条件查询(折扣生成规则-折扣区间分配因素)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap){
		return prizeRuleGenerateAreaBaseDao.selectPrizeRuleGenerateAreaByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(折扣生成规则-折扣区间分配因素)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByConditionDim(Map<String,Object> paramMap){
		return prizeRuleGenerateAreaBaseDao.selectPrizeRuleGenerateAreaByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(折扣生成规则-折扣区间分配因素)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleGenerateAreaBaseDao.selectPrizeRuleGenerateAreaByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(折扣生成规则-折扣区间分配因素)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleGenerateAreaBaseDao.selectPrizeRuleGenerateAreaByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(折扣生成规则-折扣区间分配因素)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleGenerateArea getPrizeRuleGenerateAreaBySeqId(java.math.BigInteger id){
		return prizeRuleGenerateAreaBaseDao.selectPrizeRuleGenerateAreaBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(折扣生成规则-折扣区间分配因素)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleGenerateAreaCount(Map<String,Object> paramMap){
		return prizeRuleGenerateAreaBaseDao.selectPrizeRuleGenerateAreaCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(折扣生成规则-折扣区间分配因素)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleGenerateAreaCountDim(Map<String,Object> paramMap){
		return prizeRuleGenerateAreaBaseDao.selectPrizeRuleGenerateAreaCount(paramMap,true);
	}
	/**
	 * 往(折扣生成规则-折扣区间分配因素)新增一条记录
	 * @param prizeRuleGenerateArea
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateArea(PrizeRuleGenerateArea prizeRuleGenerateArea){
		return prizeRuleGenerateAreaBaseDao.insertPrizeRuleGenerateArea(prizeRuleGenerateArea);
	}
	/**
	 * 批量新增(折扣生成规则-折扣区间分配因素)
	 * @param prizeRuleGenerateAreaList
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateAreaBatch(List<PrizeRuleGenerateArea> prizeRuleGenerateAreaList){
		return prizeRuleGenerateAreaBaseDao.insertPrizeRuleGenerateAreaBatch(prizeRuleGenerateAreaList);
	}
	/**
	 * 更新(折扣生成规则-折扣区间分配因素)信息
	 * @param prizeRuleGenerateArea
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateArea(PrizeRuleGenerateArea prizeRuleGenerateArea){
		return prizeRuleGenerateAreaBaseDao.updatePrizeRuleGenerateArea(prizeRuleGenerateArea);
	}
	/**
	 * 批量更新(折扣生成规则-折扣区间分配因素)信息
	 * @param prizeRuleGenerateAreaList
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateAreaBatch(List<PrizeRuleGenerateArea> prizeRuleGenerateAreaList){
		return prizeRuleGenerateAreaBaseDao.updatePrizeRuleGenerateAreaBatch(prizeRuleGenerateAreaList);
	}
	/**
	 * 根据序列号删除(折扣生成规则-折扣区间分配因素)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateAreaLogic(java.math.BigInteger id){
		return prizeRuleGenerateAreaBaseDao.deletePrizeRuleGenerateAreaLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(折扣生成规则-折扣区间分配因素)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateAreaLogicBatch(List<java.math.BigInteger> idList){
		return prizeRuleGenerateAreaBaseDao.deletePrizeRuleGenerateAreaLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(折扣生成规则-折扣区间分配因素)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateArea(java.math.BigInteger id){
//		return prizeRuleGenerateAreaBaseDao.deletePrizeRuleGenerateArea(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(折扣生成规则-折扣区间分配因素)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateAreaBatch(List<java.math.BigInteger> idList){
//		return prizeRuleGenerateAreaBaseDao.deletePrizeRuleGenerateAreaBatch(idList);
//	}
	
}
