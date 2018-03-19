package com.cnfantasia.server.domainbase.lotteryDrawProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.lotteryDrawProduct.dao.ILotteryDrawProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lotteryDrawProduct.entity.LotteryDrawProduct;

/**
 * 描述:(幸运抽奖奖品表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LotteryDrawProductBaseService implements ILotteryDrawProductBaseService{
	
	private ILotteryDrawProductBaseDao lotteryDrawProductBaseDao;
	public void setLotteryDrawProductBaseDao(ILotteryDrawProductBaseDao lotteryDrawProductBaseDao) {
		this.lotteryDrawProductBaseDao = lotteryDrawProductBaseDao;
	}
	/**
	 * 根据条件查询(幸运抽奖奖品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LotteryDrawProduct> getLotteryDrawProductByCondition(Map<String,Object> paramMap){
		return lotteryDrawProductBaseDao.selectLotteryDrawProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(幸运抽奖奖品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LotteryDrawProduct> getLotteryDrawProductByConditionDim(Map<String,Object> paramMap){
		return lotteryDrawProductBaseDao.selectLotteryDrawProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(幸运抽奖奖品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LotteryDrawProduct> getLotteryDrawProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return lotteryDrawProductBaseDao.selectLotteryDrawProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(幸运抽奖奖品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LotteryDrawProduct> getLotteryDrawProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return lotteryDrawProductBaseDao.selectLotteryDrawProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(幸运抽奖奖品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LotteryDrawProduct getLotteryDrawProductBySeqId(java.math.BigInteger id){
		return lotteryDrawProductBaseDao.selectLotteryDrawProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(幸运抽奖奖品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLotteryDrawProductCount(Map<String,Object> paramMap){
		return lotteryDrawProductBaseDao.selectLotteryDrawProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(幸运抽奖奖品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLotteryDrawProductCountDim(Map<String,Object> paramMap){
		return lotteryDrawProductBaseDao.selectLotteryDrawProductCount(paramMap,true);
	}
	/**
	 * 往(幸运抽奖奖品表)新增一条记录
	 * @param lotteryDrawProduct
	 * @return
	 */
	@Override
	public int insertLotteryDrawProduct(LotteryDrawProduct lotteryDrawProduct){
		return lotteryDrawProductBaseDao.insertLotteryDrawProduct(lotteryDrawProduct);
	}
	/**
	 * 批量新增(幸运抽奖奖品表)
	 * @param lotteryDrawProductList
	 * @return
	 */
	@Override
	public int insertLotteryDrawProductBatch(List<LotteryDrawProduct> lotteryDrawProductList){
		return lotteryDrawProductBaseDao.insertLotteryDrawProductBatch(lotteryDrawProductList);
	}
	/**
	 * 更新(幸运抽奖奖品表)信息
	 * @param lotteryDrawProduct
	 * @return
	 */
	@Override
	public int updateLotteryDrawProduct(LotteryDrawProduct lotteryDrawProduct){
		return lotteryDrawProductBaseDao.updateLotteryDrawProduct(lotteryDrawProduct);
	}
	/**
	 * 批量更新(幸运抽奖奖品表)信息
	 * @param lotteryDrawProductList
	 * @return
	 */
	@Override
	public int updateLotteryDrawProductBatch(List<LotteryDrawProduct> lotteryDrawProductList){
		return lotteryDrawProductBaseDao.updateLotteryDrawProductBatch(lotteryDrawProductList);
	}
	/**
	 * 根据序列号删除(幸运抽奖奖品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawProductLogic(java.math.BigInteger id){
		return lotteryDrawProductBaseDao.deleteLotteryDrawProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(幸运抽奖奖品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawProductLogicBatch(List<java.math.BigInteger> idList){
		return lotteryDrawProductBaseDao.deleteLotteryDrawProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(幸运抽奖奖品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawProduct(java.math.BigInteger id){
//		return lotteryDrawProductBaseDao.deleteLotteryDrawProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(幸运抽奖奖品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawProductBatch(List<java.math.BigInteger> idList){
//		return lotteryDrawProductBaseDao.deleteLotteryDrawProductBatch(idList);
//	}
	
}
