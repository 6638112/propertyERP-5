package com.cnfantasia.server.domainbase.loanProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.loanProduct.entity.LoanProduct;

/**
 * 描述:(借贷产品表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoanProductBaseService {
	
	/**
	 * 根据条件查询(借贷产品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanProduct> getLoanProductByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(借贷产品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanProduct> getLoanProductByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(借贷产品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanProduct> getLoanProductByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(借贷产品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanProduct> getLoanProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(借贷产品表)信息
	 * @param id
	 * @return
	 */
	public LoanProduct getLoanProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(借贷产品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanProductCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(借贷产品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanProductCountDim(Map<String,Object> paramMap);
	/**
	 * 往(借贷产品表)新增一条记录
	 * @param loanProduct
	 * @return
	 */
	public int insertLoanProduct(LoanProduct loanProduct);
	/**
	 * 批量新增(借贷产品表)
	 * @param loanProductList
	 * @return
	 */
	public int insertLoanProductBatch(List<LoanProduct> loanProductList);
	/**
	 * 更新(借贷产品表)信息
	 * @param loanProduct
	 * @return
	 */
	public int updateLoanProduct(LoanProduct loanProduct);
	/**
	 * 批量更新(借贷产品表)信息
	 * @param loanProductList
	 * @return
	 */
	public int updateLoanProductBatch(List<LoanProduct> loanProductList);
	/**
	 * 根据序列号删除(借贷产品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoanProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(借贷产品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoanProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(借贷产品表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoanProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(借贷产品表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoanProductBatch(List<java.math.BigInteger> idList);
	
}
