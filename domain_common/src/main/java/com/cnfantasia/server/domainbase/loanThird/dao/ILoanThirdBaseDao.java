package com.cnfantasia.server.domainbase.loanThird.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanThird.entity.LoanThird;

/**
 * 描述:(借贷第三方对接信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoanThirdBaseDao {
	/**
	 * 根据条件查询(借贷第三方对接信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoanThird> selectLoanThirdByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(借贷第三方对接信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoanThird> selectLoanThirdByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(借贷第三方对接信息表)信息
	 * @param id
	 * @return
	 */
	public LoanThird selectLoanThirdBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(借贷第三方对接信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLoanThirdCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(借贷第三方对接信息表)新增一条记录
	 * @param loanThird
	 * @return
	 */
	public int insertLoanThird(LoanThird loanThird);
	
	/**
	 * 批量新增(借贷第三方对接信息表)信息
	 * @param loanThirdList
	 * @return
	 */
	public int insertLoanThirdBatch(List<LoanThird> loanThirdList);
	
	/**
	 * 更新(借贷第三方对接信息表)信息
	 * @param loanThird
	 * @return
	 */
	public int updateLoanThird(LoanThird loanThird);
	
	/**
	 * 批量更新(借贷第三方对接信息表)信息
	 * @param loanThirdList
	 * @return
	 */
	public int updateLoanThirdBatch(List<LoanThird> loanThirdList);
	
	/**
	 * 根据序列号删除(借贷第三方对接信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoanThirdLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(借贷第三方对接信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoanThirdLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(借贷第三方对接信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoanThird(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(借贷第三方对接信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoanThirdBatch(List<java.math.BigInteger> idList);
	
	
}
