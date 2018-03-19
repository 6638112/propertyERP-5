package com.cnfantasia.server.domainbase.bankCollectionDate.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bankCollectionDate.entity.BankCollectionDate;

/**
 * 描述:(物业公司银行托收日期配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBankCollectionDateBaseService {
	
	/**
	 * 根据条件查询(物业公司银行托收日期配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BankCollectionDate> getBankCollectionDateByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业公司银行托收日期配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BankCollectionDate> getBankCollectionDateByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业公司银行托收日期配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BankCollectionDate> getBankCollectionDateByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业公司银行托收日期配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BankCollectionDate> getBankCollectionDateByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业公司银行托收日期配置)信息
	 * @param id
	 * @return
	 */
	public BankCollectionDate getBankCollectionDateBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司银行托收日期配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBankCollectionDateCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业公司银行托收日期配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBankCollectionDateCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业公司银行托收日期配置)新增一条记录
	 * @param bankCollectionDate
	 * @return
	 */
	public int insertBankCollectionDate(BankCollectionDate bankCollectionDate);
	/**
	 * 批量新增(物业公司银行托收日期配置)
	 * @param bankCollectionDateList
	 * @return
	 */
	public int insertBankCollectionDateBatch(List<BankCollectionDate> bankCollectionDateList);
	/**
	 * 更新(物业公司银行托收日期配置)信息
	 * @param bankCollectionDate
	 * @return
	 */
	public int updateBankCollectionDate(BankCollectionDate bankCollectionDate);
	/**
	 * 批量更新(物业公司银行托收日期配置)信息
	 * @param bankCollectionDateList
	 * @return
	 */
	public int updateBankCollectionDateBatch(List<BankCollectionDate> bankCollectionDateList);
	/**
	 * 根据序列号删除(物业公司银行托收日期配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBankCollectionDateLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业公司银行托收日期配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBankCollectionDateLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业公司银行托收日期配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBankCollectionDate(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业公司银行托收日期配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBankCollectionDateBatch(List<java.math.BigInteger> idList);
	
}
