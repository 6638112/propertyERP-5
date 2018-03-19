package com.cnfantasia.server.domainbase.loanProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanProduct.entity.LoanProduct;

/**
 * 描述:(借贷产品表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoanProductBaseDao {
	/**
	 * 根据条件查询(借贷产品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoanProduct> selectLoanProductByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(借贷产品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoanProduct> selectLoanProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(借贷产品表)信息
	 * @param id
	 * @return
	 */
	public LoanProduct selectLoanProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(借贷产品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLoanProductCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(借贷产品表)新增一条记录
	 * @param loanProduct
	 * @return
	 */
	public int insertLoanProduct(LoanProduct loanProduct);
	
	/**
	 * 批量新增(借贷产品表)信息
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
	 * 根据Id 批量删除(借贷产品表)信息_逻辑删除
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
