package com.cnfantasia.server.domainbase.msPrizeGiftCode.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.msPrizeGiftCode.entity.MsPrizeGiftCode;

/**
 * 描述:(抽奖奖品编码表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeGiftCodeBaseService {
	
	/**
	 * 根据条件查询(抽奖奖品编码表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(抽奖奖品编码表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(抽奖奖品编码表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(抽奖奖品编码表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeGiftCode> getMsPrizeGiftCodeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(抽奖奖品编码表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeGiftCode getMsPrizeGiftCodeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抽奖奖品编码表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeGiftCodeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抽奖奖品编码表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeGiftCodeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(抽奖奖品编码表)新增一条记录
	 * @param msPrizeGiftCode
	 * @return
	 */
	public int insertMsPrizeGiftCode(MsPrizeGiftCode msPrizeGiftCode);
	/**
	 * 批量新增(抽奖奖品编码表)
	 * @param msPrizeGiftCodeList
	 * @return
	 */
	public int insertMsPrizeGiftCodeBatch(List<MsPrizeGiftCode> msPrizeGiftCodeList);
	/**
	 * 更新(抽奖奖品编码表)信息
	 * @param msPrizeGiftCode
	 * @return
	 */
	public int updateMsPrizeGiftCode(MsPrizeGiftCode msPrizeGiftCode);
	/**
	 * 批量更新(抽奖奖品编码表)信息
	 * @param msPrizeGiftCodeList
	 * @return
	 */
	public int updateMsPrizeGiftCodeBatch(List<MsPrizeGiftCode> msPrizeGiftCodeList);
	/**
	 * 根据序列号删除(抽奖奖品编码表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMsPrizeGiftCodeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抽奖奖品编码表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMsPrizeGiftCodeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抽奖奖品编码表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMsPrizeGiftCode(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抽奖奖品编码表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMsPrizeGiftCodeBatch(List<java.math.BigInteger> idList);
	
}
