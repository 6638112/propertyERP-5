package com.cnfantasia.server.domainbase.loanType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loanType.dao.ILoanTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanType.entity.LoanType;

/**
 * 描述:(借贷类型表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoanTypeBaseService implements ILoanTypeBaseService{
	
	private ILoanTypeBaseDao loanTypeBaseDao;
	public void setLoanTypeBaseDao(ILoanTypeBaseDao loanTypeBaseDao) {
		this.loanTypeBaseDao = loanTypeBaseDao;
	}
	/**
	 * 根据条件查询(借贷类型表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanType> getLoanTypeByCondition(Map<String,Object> paramMap){
		return loanTypeBaseDao.selectLoanTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(借贷类型表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanType> getLoanTypeByConditionDim(Map<String,Object> paramMap){
		return loanTypeBaseDao.selectLoanTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(借贷类型表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanType> getLoanTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loanTypeBaseDao.selectLoanTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(借贷类型表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanType> getLoanTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loanTypeBaseDao.selectLoanTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(借贷类型表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoanType getLoanTypeBySeqId(java.math.BigInteger id){
		return loanTypeBaseDao.selectLoanTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(借贷类型表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanTypeCount(Map<String,Object> paramMap){
		return loanTypeBaseDao.selectLoanTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(借贷类型表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanTypeCountDim(Map<String,Object> paramMap){
		return loanTypeBaseDao.selectLoanTypeCount(paramMap,true);
	}
	/**
	 * 往(借贷类型表)新增一条记录
	 * @param loanType
	 * @return
	 */
	@Override
	public int insertLoanType(LoanType loanType){
		return loanTypeBaseDao.insertLoanType(loanType);
	}
	/**
	 * 批量新增(借贷类型表)
	 * @param loanTypeList
	 * @return
	 */
	@Override
	public int insertLoanTypeBatch(List<LoanType> loanTypeList){
		return loanTypeBaseDao.insertLoanTypeBatch(loanTypeList);
	}
	/**
	 * 更新(借贷类型表)信息
	 * @param loanType
	 * @return
	 */
	@Override
	public int updateLoanType(LoanType loanType){
		return loanTypeBaseDao.updateLoanType(loanType);
	}
	/**
	 * 批量更新(借贷类型表)信息
	 * @param loanTypeList
	 * @return
	 */
	@Override
	public int updateLoanTypeBatch(List<LoanType> loanTypeList){
		return loanTypeBaseDao.updateLoanTypeBatch(loanTypeList);
	}
	/**
	 * 根据序列号删除(借贷类型表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoanTypeLogic(java.math.BigInteger id){
		return loanTypeBaseDao.deleteLoanTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(借贷类型表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoanTypeLogicBatch(List<java.math.BigInteger> idList){
		return loanTypeBaseDao.deleteLoanTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(借贷类型表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoanType(java.math.BigInteger id){
//		return loanTypeBaseDao.deleteLoanType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(借贷类型表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoanTypeBatch(List<java.math.BigInteger> idList){
//		return loanTypeBaseDao.deleteLoanTypeBatch(idList);
//	}
	
}
