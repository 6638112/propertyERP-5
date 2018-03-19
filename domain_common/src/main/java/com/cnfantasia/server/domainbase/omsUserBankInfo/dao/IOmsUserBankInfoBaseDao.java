package com.cnfantasia.server.domainbase.omsUserBankInfo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserBankInfo.entity.OmsUserBankInfo;

/**
 * 描述:(OMS用户银行信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserBankInfoBaseDao {
	/**
	 * 根据条件查询(OMS用户银行信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserBankInfo> selectOmsUserBankInfoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS用户银行信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserBankInfo> selectOmsUserBankInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS用户银行信息表)信息
	 * @param id
	 * @return
	 */
	public OmsUserBankInfo selectOmsUserBankInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS用户银行信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsUserBankInfoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS用户银行信息表)新增一条记录
	 * @param omsUserBankInfo
	 * @return
	 */
	public int insertOmsUserBankInfo(OmsUserBankInfo omsUserBankInfo);
	
	/**
	 * 批量新增(OMS用户银行信息表)信息
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
	 * 根据Id 批量删除(OMS用户银行信息表)信息_逻辑删除
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
