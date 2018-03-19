package com.cnfantasia.server.domainbase.msPrizeGift.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;

/**
 * 描述:(奖品详情表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeGiftBaseService {
	
	/**
	 * 根据条件查询(奖品详情表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeGift> getMsPrizeGiftByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(奖品详情表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeGift> getMsPrizeGiftByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(奖品详情表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeGift> getMsPrizeGiftByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(奖品详情表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeGift> getMsPrizeGiftByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(奖品详情表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeGift getMsPrizeGiftBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(奖品详情表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeGiftCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(奖品详情表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeGiftCountDim(Map<String,Object> paramMap);
	/**
	 * 往(奖品详情表)新增一条记录
	 * @param msPrizeGift
	 * @return
	 */
	public int insertMsPrizeGift(MsPrizeGift msPrizeGift);
	/**
	 * 批量新增(奖品详情表)
	 * @param msPrizeGiftList
	 * @return
	 */
	public int insertMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList);
	/**
	 * 更新(奖品详情表)信息
	 * @param msPrizeGift
	 * @return
	 */
	public int updateMsPrizeGift(MsPrizeGift msPrizeGift);
	/**
	 * 批量更新(奖品详情表)信息
	 * @param msPrizeGiftList
	 * @return
	 */
	public int updateMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList);
	/**
	 * 根据序列号删除(奖品详情表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMsPrizeGiftLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(奖品详情表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMsPrizeGiftLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(奖品详情表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMsPrizeGift(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(奖品详情表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMsPrizeGiftBatch(List<java.math.BigInteger> idList);
	
}
