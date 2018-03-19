package com.cnfantasia.server.domainbase.omsUserBankInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsUserBankInfo.entity.OmsUserBankInfo;

/**
 * 描述:(OMS用户银行信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserBankInfoBaseService {
	
	/**
	 * 根据条件查询(OMS用户银行信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserBankInfo> getOmsUserBankInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(OMS用户银行信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserBankInfo> getOmsUserBankInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(OMS用户银行信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserBankInfo> getOmsUserBankInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(OMS用户银行信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserBankInfo> getOmsUserBankInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(OMS用户银行信息表)信息
	 * @param id
	 * @return
	 */
	public OmsUserBankInfo getOmsUserBankInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS用户银行信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserBankInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS用户银行信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserBankInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(OMS用户银行信息表)新增一条记录
	 * @param omsUserBankInfo
	 * @return
	 */
	public int insertOmsUserBankInfo(OmsUserBankInfo omsUserBankInfo);
	/**
	 * 批量新增(OMS用户银行信息表)
	 * @param omsUserBankInfoList
	 * @return
	 */
	public int insertOmsUserBankInfoBatch(List<OmsUserBankInfo> omsUserBankInfoList);
	/**
	 * 更新(OMS用户银行信息表)信息
	 * @param omsUserBankInfo
	 * @return
	 */
	public int updateOmsUserBankInfo(OmsUserBankInfo omsUserBankInfo);
	/**
	 * 批量更新(OMS用户银行信息表)信息
	 * @param omsUserBankInfoList
	 * @return
	 */
	public int updateOmsUserBankInfoBatch(List<OmsUserBankInfo> omsUserBankInfoList);
	/**
	 * 根据序列号删除(OMS用户银行信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsUserBankInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(OMS用户银行信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsUserBankInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(OMS用户银行信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsUserBankInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(OMS用户银行信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsUserBankInfoBatch(List<java.math.BigInteger> idList);
	
}
