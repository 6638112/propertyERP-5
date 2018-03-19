package com.cnfantasia.server.domainbase.lotteryDrawProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lotteryDrawProduct.entity.LotteryDrawProduct;

/**
 * 描述:(幸运抽奖奖品表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILotteryDrawProductBaseDao {
	/**
	 * 根据条件查询(幸运抽奖奖品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<LotteryDrawProduct> selectLotteryDrawProductByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(幸运抽奖奖品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<LotteryDrawProduct> selectLotteryDrawProductByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(幸运抽奖奖品表)信息
	 * @param id
	 * @return
	 */
	LotteryDrawProduct selectLotteryDrawProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(幸运抽奖奖品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectLotteryDrawProductCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(幸运抽奖奖品表)新增一条记录
	 * @param lotteryDrawProduct
	 * @return
	 */
	int insertLotteryDrawProduct(LotteryDrawProduct lotteryDrawProduct);
	
	/**
	 * 批量新增(幸运抽奖奖品表)信息
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
	 * 根据Id 批量删除(幸运抽奖奖品表)信息_逻辑删除
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
