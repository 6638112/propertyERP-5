package com.cnfantasia.server.domainbase.loanType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanType.entity.LoanType;

/**
 * 描述:(借贷类型表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoanTypeBaseDao {
	/**
	 * 根据条件查询(借贷类型表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoanType> selectLoanTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(借贷类型表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoanType> selectLoanTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(借贷类型表)信息
	 * @param id
	 * @return
	 */
	public LoanType selectLoanTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(借贷类型表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLoanTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(借贷类型表)新增一条记录
	 * @param loanType
	 * @return
	 */
	public int insertLoanType(LoanType loanType);
	
	/**
	 * 批量新增(借贷类型表)信息
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
	 * 根据Id 批量删除(借贷类型表)信息_逻辑删除
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
