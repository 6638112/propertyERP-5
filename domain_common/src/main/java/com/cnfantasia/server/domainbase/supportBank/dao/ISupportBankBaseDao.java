package com.cnfantasia.server.domainbase.supportBank.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.supportBank.entity.SupportBank;

/**
 * 描述:(支持的转账银行) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISupportBankBaseDao {
	/**
	 * 根据条件查询(支持的转账银行)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SupportBank> selectSupportBankByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(支持的转账银行)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SupportBank> selectSupportBankByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(支持的转账银行)信息
	 * @param id
	 * @return
	 */
	public SupportBank selectSupportBankBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(支持的转账银行)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectSupportBankCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(支持的转账银行)新增一条记录
	 * @param supportBank
	 * @return
	 */
	public int insertSupportBank(SupportBank supportBank);
	
	/**
	 * 批量新增(支持的转账银行)信息
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
	 * 根据Id 批量删除(支持的转账银行)信息_逻辑删除
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
