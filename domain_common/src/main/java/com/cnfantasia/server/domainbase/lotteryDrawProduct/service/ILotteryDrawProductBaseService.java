package com.cnfantasia.server.domainbase.lotteryDrawProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.lotteryDrawProduct.entity.LotteryDrawProduct;

/**
 * 描述:(幸运抽奖奖品表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILotteryDrawProductBaseService {
	
	/**
	 * 根据条件查询(幸运抽奖奖品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<LotteryDrawProduct> getLotteryDrawProductByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(幸运抽奖奖品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<LotteryDrawProduct> getLotteryDrawProductByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(幸运抽奖奖品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<LotteryDrawProduct> getLotteryDrawProductByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(幸运抽奖奖品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<LotteryDrawProduct> getLotteryDrawProductByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(幸运抽奖奖品表)信息
	 * @param id
	 * @return
	 */
	LotteryDrawProduct getLotteryDrawProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(幸运抽奖奖品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getLotteryDrawProductCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(幸运抽奖奖品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getLotteryDrawProductCountDim(Map<String, Object> paramMap);
	/**
	 * 往(幸运抽奖奖品表)新增一条记录
	 * @param lotteryDrawProduct
	 * @return
	 */
	int insertLotteryDrawProduct(LotteryDrawProduct lotteryDrawProduct);
	/**
	 * 批量新增(幸运抽奖奖品表)
	 * @param lotteryDrawProductList
	 * @return
	 */
	int insertLotteryDrawProductBatch(List<LotteryDrawProduct> lotteryDrawProductList);
	/**
	 * 更新(幸运抽奖奖品表)信息
	 * @param lotteryDrawProduct
	 * @return
	 */
	int updateLotteryDrawProduct(LotteryDrawProduct lotteryDrawProduct);
	/**
	 * 批量更新(幸运抽奖奖品表)信息
	 * @param lotteryDrawProductList
	 * @return
	 */
	int updateLotteryDrawProductBatch(List<LotteryDrawProduct> lotteryDrawProductList);
	/**
	 * 根据序列号删除(幸运抽奖奖品表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteLotteryDrawProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(幸运抽奖奖品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteLotteryDrawProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(幸运抽奖奖品表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLotteryDrawProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(幸运抽奖奖品表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLotteryDrawProductBatch(List<java.math.BigInteger> idList);
	
}
