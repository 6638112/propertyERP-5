package com.cnfantasia.server.domainbase.prizeRecordTmpData.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRecordTmpDataBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRecordTmpData> selectPrizeRecordTmpDataByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRecordTmpData> selectPrizeRecordTmpDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public PrizeRecordTmpData selectPrizeRecordTmpDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeRecordTmpDataCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param prizeRecordTmpData
	 * @return
	 */
	public int insertPrizeRecordTmpData(PrizeRecordTmpData prizeRecordTmpData);
	
	/**
	 * 批量新增()信息
	 * @param prizeRecordTmpDataList
	 * @return
	 */
	public int insertPrizeRecordTmpDataBatch(List<PrizeRecordTmpData> prizeRecordTmpDataList);
	
	/**
	 * 更新()信息
	 * @param prizeRecordTmpData
	 * @return
	 */
	public int updatePrizeRecordTmpData(PrizeRecordTmpData prizeRecordTmpData);
	
	/**
	 * 批量更新()信息
	 * @param prizeRecordTmpDataList
	 * @return
	 */
	public int updatePrizeRecordTmpDataBatch(List<PrizeRecordTmpData> prizeRecordTmpDataList);
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRecordTmpDataLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRecordTmpDataLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRecordTmpData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRecordTmpDataBatch(List<java.math.BigInteger> idList);
	
	
}
