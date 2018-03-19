package com.cnfantasia.server.domainbase.loanThird.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.loanThird.entity.LoanThird;

/**
 * 描述:(借贷第三方对接信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoanThirdBaseService {
	
	/**
	 * 根据条件查询(借贷第三方对接信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanThird> getLoanThirdByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(借贷第三方对接信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanThird> getLoanThirdByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(借贷第三方对接信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanThird> getLoanThirdByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(借贷第三方对接信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanThird> getLoanThirdByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(借贷第三方对接信息表)信息
	 * @param id
	 * @return
	 */
	public LoanThird getLoanThirdBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(借贷第三方对接信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanThirdCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(借贷第三方对接信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanThirdCountDim(Map<String,Object> paramMap);
	/**
	 * 往(借贷第三方对接信息表)新增一条记录
	 * @param loanThird
	 * @return
	 */
	public int insertLoanThird(LoanThird loanThird);
	/**
	 * 批量新增(借贷第三方对接信息表)
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
	 * 根据序列号批量删除(借贷第三方对接信息表)信息_逻辑删除
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
