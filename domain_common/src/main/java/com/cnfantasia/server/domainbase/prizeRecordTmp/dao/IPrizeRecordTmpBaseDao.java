package com.cnfantasia.server.domainbase.prizeRecordTmp.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

/**
 * 描述:(临时用户中奖记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRecordTmpBaseDao {
	/**
	 * 根据条件查询(临时用户中奖记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRecordTmp> selectPrizeRecordTmpByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(临时用户中奖记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRecordTmp> selectPrizeRecordTmpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(临时用户中奖记录)信息
	 * @param id
	 * @return
	 */
	public PrizeRecordTmp selectPrizeRecordTmpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(临时用户中奖记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeRecordTmpCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(临时用户中奖记录)新增一条记录
	 * @param prizeRecordTmp
	 * @return
	 */
	public int insertPrizeRecordTmp(PrizeRecordTmp prizeRecordTmp);
	
	/**
	 * 批量新增(临时用户中奖记录)信息
	 * @param prizeRecordTmpList
	 * @return
	 */
	public int insertPrizeRecordTmpBatch(List<PrizeRecordTmp> prizeRecordTmpList);
	
	/**
	 * 更新(临时用户中奖记录)信息
	 * @param prizeRecordTmp
	 * @return
	 */
	public int updatePrizeRecordTmp(PrizeRecordTmp prizeRecordTmp);
	
	/**
	 * 批量更新(临时用户中奖记录)信息
	 * @param prizeRecordTmpList
	 * @return
	 */
	public int updatePrizeRecordTmpBatch(List<PrizeRecordTmp> prizeRecordTmpList);
	
	/**
	 * 根据序列号删除(临时用户中奖记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRecordTmpLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(临时用户中奖记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRecordTmpLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(临时用户中奖记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRecordTmp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(临时用户中奖记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRecordTmpBatch(List<java.math.BigInteger> idList);
	
	
}
