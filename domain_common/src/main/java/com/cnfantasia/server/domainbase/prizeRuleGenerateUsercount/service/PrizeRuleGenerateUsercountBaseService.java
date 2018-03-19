package com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.dao.IPrizeRuleGenerateUsercountBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.entity.PrizeRuleGenerateUsercount;

/**
 * 描述:(折扣生成规则-用户数量因素) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeRuleGenerateUsercountBaseService implements IPrizeRuleGenerateUsercountBaseService{
	
	private IPrizeRuleGenerateUsercountBaseDao prizeRuleGenerateUsercountBaseDao;
	public void setPrizeRuleGenerateUsercountBaseDao(IPrizeRuleGenerateUsercountBaseDao prizeRuleGenerateUsercountBaseDao) {
		this.prizeRuleGenerateUsercountBaseDao = prizeRuleGenerateUsercountBaseDao;
	}
	/**
	 * 根据条件查询(折扣生成规则-用户数量因素)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap){
		return prizeRuleGenerateUsercountBaseDao.selectPrizeRuleGenerateUsercountByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(折扣生成规则-用户数量因素)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByConditionDim(Map<String,Object> paramMap){
		return prizeRuleGenerateUsercountBaseDao.selectPrizeRuleGenerateUsercountByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(折扣生成规则-用户数量因素)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleGenerateUsercountBaseDao.selectPrizeRuleGenerateUsercountByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(折扣生成规则-用户数量因素)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleGenerateUsercountBaseDao.selectPrizeRuleGenerateUsercountByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(折扣生成规则-用户数量因素)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleGenerateUsercount getPrizeRuleGenerateUsercountBySeqId(java.math.BigInteger id){
		return prizeRuleGenerateUsercountBaseDao.selectPrizeRuleGenerateUsercountBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(折扣生成规则-用户数量因素)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleGenerateUsercountCount(Map<String,Object> paramMap){
		return prizeRuleGenerateUsercountBaseDao.selectPrizeRuleGenerateUsercountCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(折扣生成规则-用户数量因素)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleGenerateUsercountCountDim(Map<String,Object> paramMap){
		return prizeRuleGenerateUsercountBaseDao.selectPrizeRuleGenerateUsercountCount(paramMap,true);
	}
	/**
	 * 往(折扣生成规则-用户数量因素)新增一条记录
	 * @param prizeRuleGenerateUsercount
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateUsercount(PrizeRuleGenerateUsercount prizeRuleGenerateUsercount){
		return prizeRuleGenerateUsercountBaseDao.insertPrizeRuleGenerateUsercount(prizeRuleGenerateUsercount);
	}
	/**
	 * 批量新增(折扣生成规则-用户数量因素)
	 * @param prizeRuleGenerateUsercountList
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateUsercountBatch(List<PrizeRuleGenerateUsercount> prizeRuleGenerateUsercountList){
		return prizeRuleGenerateUsercountBaseDao.insertPrizeRuleGenerateUsercountBatch(prizeRuleGenerateUsercountList);
	}
	/**
	 * 更新(折扣生成规则-用户数量因素)信息
	 * @param prizeRuleGenerateUsercount
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateUsercount(PrizeRuleGenerateUsercount prizeRuleGenerateUsercount){
		return prizeRuleGenerateUsercountBaseDao.updatePrizeRuleGenerateUsercount(prizeRuleGenerateUsercount);
	}
	/**
	 * 批量更新(折扣生成规则-用户数量因素)信息
	 * @param prizeRuleGenerateUsercountList
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateUsercountBatch(List<PrizeRuleGenerateUsercount> prizeRuleGenerateUsercountList){
		return prizeRuleGenerateUsercountBaseDao.updatePrizeRuleGenerateUsercountBatch(prizeRuleGenerateUsercountList);
	}
	/**
	 * 根据序列号删除(折扣生成规则-用户数量因素)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateUsercountLogic(java.math.BigInteger id){
		return prizeRuleGenerateUsercountBaseDao.deletePrizeRuleGenerateUsercountLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(折扣生成规则-用户数量因素)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateUsercountLogicBatch(List<java.math.BigInteger> idList){
		return prizeRuleGenerateUsercountBaseDao.deletePrizeRuleGenerateUsercountLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(折扣生成规则-用户数量因素)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateUsercount(java.math.BigInteger id){
//		return prizeRuleGenerateUsercountBaseDao.deletePrizeRuleGenerateUsercount(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(折扣生成规则-用户数量因素)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateUsercountBatch(List<java.math.BigInteger> idList){
//		return prizeRuleGenerateUsercountBaseDao.deletePrizeRuleGenerateUsercountBatch(idList);
//	}
	
}
