package com.cnfantasia.server.domainbase.prizeRecordTmpData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRecordTmpDataBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRecordTmpData> getPrizeRecordTmpDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public PrizeRecordTmpData getPrizeRecordTmpDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRecordTmpDataCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRecordTmpDataCountDim(Map<String,Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param prizeRecordTmpData
	 * @return
	 */
	public int insertPrizeRecordTmpData(PrizeRecordTmpData prizeRecordTmpData);
	/**
	 * 批量新增()
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
	 * 根据序列号批量删除()信息_逻辑删除
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
