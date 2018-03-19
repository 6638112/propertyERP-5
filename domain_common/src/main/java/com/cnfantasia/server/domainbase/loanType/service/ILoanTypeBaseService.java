package com.cnfantasia.server.domainbase.loanType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.loanType.entity.LoanType;

/**
 * 描述:(借贷类型表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoanTypeBaseService {
	
	/**
	 * 根据条件查询(借贷类型表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanType> getLoanTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(借贷类型表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanType> getLoanTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(借贷类型表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanType> getLoanTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(借贷类型表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanType> getLoanTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(借贷类型表)信息
	 * @param id
	 * @return
	 */
	public LoanType getLoanTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(借贷类型表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(借贷类型表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(借贷类型表)新增一条记录
	 * @param loanType
	 * @return
	 */
	public int insertLoanType(LoanType loanType);
	/**
	 * 批量新增(借贷类型表)
	 * @param loanTypeList
	 * @return
	 */
	public int insertLoanTypeBatch(List<LoanType> loanTypeList);
	/**
	 * 更新(借贷类型表)信息
	 * @param loanType
	 * @return
	 */
	public int updateLoanType(LoanType loanType);
	/**
	 * 批量更新(借贷类型表)信息
	 * @param loanTypeList
	 * @return
	 */
	public int updateLoanTypeBatch(List<LoanType> loanTypeList);
	/**
	 * 根据序列号删除(借贷类型表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoanTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(借贷类型表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoanTypeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(借贷类型表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoanType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(借贷类型表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoanTypeBatch(List<java.math.BigInteger> idList);
	
}
