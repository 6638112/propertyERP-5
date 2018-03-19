package com.cnfantasia.server.domainbase.supportBank.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.supportBank.entity.SupportBank;

/**
 * 描述:(支持的转账银行) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISupportBankBaseService {
	
	/**
	 * 根据条件查询(支持的转账银行)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<SupportBank> getSupportBankByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(支持的转账银行)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<SupportBank> getSupportBankByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(支持的转账银行)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SupportBank> getSupportBankByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(支持的转账银行)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SupportBank> getSupportBankByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(支持的转账银行)信息
	 * @param id
	 * @return
	 */
	public SupportBank getSupportBankBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(支持的转账银行)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSupportBankCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(支持的转账银行)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSupportBankCountDim(Map<String,Object> paramMap);
	/**
	 * 往(支持的转账银行)新增一条记录
	 * @param supportBank
	 * @return
	 */
	public int insertSupportBank(SupportBank supportBank);
	/**
	 * 批量新增(支持的转账银行)
	 * @param supportBankList
	 * @return
	 */
	public int insertSupportBankBatch(List<SupportBank> supportBankList);
	/**
	 * 更新(支持的转账银行)信息
	 * @param supportBank
	 * @return
	 */
	public int updateSupportBank(SupportBank supportBank);
	/**
	 * 批量更新(支持的转账银行)信息
	 * @param supportBankList
	 * @return
	 */
	public int updateSupportBankBatch(List<SupportBank> supportBankList);
	/**
	 * 根据序列号删除(支持的转账银行)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteSupportBankLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(支持的转账银行)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteSupportBankLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(支持的转账银行)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSupportBank(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(支持的转账银行)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSupportBankBatch(List<java.math.BigInteger> idList);
	
}
