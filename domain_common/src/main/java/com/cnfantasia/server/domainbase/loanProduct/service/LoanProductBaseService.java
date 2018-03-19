package com.cnfantasia.server.domainbase.loanProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loanProduct.dao.ILoanProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanProduct.entity.LoanProduct;

/**
 * 描述:(借贷产品表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoanProductBaseService implements ILoanProductBaseService{
	
	private ILoanProductBaseDao loanProductBaseDao;
	public void setLoanProductBaseDao(ILoanProductBaseDao loanProductBaseDao) {
		this.loanProductBaseDao = loanProductBaseDao;
	}
	/**
	 * 根据条件查询(借贷产品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanProduct> getLoanProductByCondition(Map<String,Object> paramMap){
		return loanProductBaseDao.selectLoanProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(借贷产品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanProduct> getLoanProductByConditionDim(Map<String,Object> paramMap){
		return loanProductBaseDao.selectLoanProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(借贷产品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanProduct> getLoanProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loanProductBaseDao.selectLoanProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(借贷产品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanProduct> getLoanProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loanProductBaseDao.selectLoanProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(借贷产品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoanProduct getLoanProductBySeqId(java.math.BigInteger id){
		return loanProductBaseDao.selectLoanProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(借贷产品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanProductCount(Map<String,Object> paramMap){
		return loanProductBaseDao.selectLoanProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(借贷产品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanProductCountDim(Map<String,Object> paramMap){
		return loanProductBaseDao.selectLoanProductCount(paramMap,true);
	}
	/**
	 * 往(借贷产品表)新增一条记录
	 * @param loanProduct
	 * @return
	 */
	@Override
	public int insertLoanProduct(LoanProduct loanProduct){
		return loanProductBaseDao.insertLoanProduct(loanProduct);
	}
	/**
	 * 批量新增(借贷产品表)
	 * @param loanProductList
	 * @return
	 */
	@Override
	public int insertLoanProductBatch(List<LoanProduct> loanProductList){
		return loanProductBaseDao.insertLoanProductBatch(loanProductList);
	}
	/**
	 * 更新(借贷产品表)信息
	 * @param loanProduct
	 * @return
	 */
	@Override
	public int updateLoanProduct(LoanProduct loanProduct){
		return loanProductBaseDao.updateLoanProduct(loanProduct);
	}
	/**
	 * 批量更新(借贷产品表)信息
	 * @param loanProductList
	 * @return
	 */
	@Override
	public int updateLoanProductBatch(List<LoanProduct> loanProductList){
		return loanProductBaseDao.updateLoanProductBatch(loanProductList);
	}
	/**
	 * 根据序列号删除(借贷产品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoanProductLogic(java.math.BigInteger id){
		return loanProductBaseDao.deleteLoanProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(借贷产品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoanProductLogicBatch(List<java.math.BigInteger> idList){
		return loanProductBaseDao.deleteLoanProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(借贷产品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoanProduct(java.math.BigInteger id){
//		return loanProductBaseDao.deleteLoanProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(借贷产品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoanProductBatch(List<java.math.BigInteger> idList){
//		return loanProductBaseDao.deleteLoanProductBatch(idList);
//	}
	
}
