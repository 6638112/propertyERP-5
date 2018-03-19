package com.cnfantasia.server.domainbase.loanThird.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loanThird.dao.ILoanThirdBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanThird.entity.LoanThird;

/**
 * 描述:(借贷第三方对接信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoanThirdBaseService implements ILoanThirdBaseService{
	
	private ILoanThirdBaseDao loanThirdBaseDao;
	public void setLoanThirdBaseDao(ILoanThirdBaseDao loanThirdBaseDao) {
		this.loanThirdBaseDao = loanThirdBaseDao;
	}
	/**
	 * 根据条件查询(借贷第三方对接信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanThird> getLoanThirdByCondition(Map<String,Object> paramMap){
		return loanThirdBaseDao.selectLoanThirdByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(借贷第三方对接信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanThird> getLoanThirdByConditionDim(Map<String,Object> paramMap){
		return loanThirdBaseDao.selectLoanThirdByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(借贷第三方对接信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanThird> getLoanThirdByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loanThirdBaseDao.selectLoanThirdByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(借贷第三方对接信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanThird> getLoanThirdByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loanThirdBaseDao.selectLoanThirdByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(借贷第三方对接信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoanThird getLoanThirdBySeqId(java.math.BigInteger id){
		return loanThirdBaseDao.selectLoanThirdBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(借贷第三方对接信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanThirdCount(Map<String,Object> paramMap){
		return loanThirdBaseDao.selectLoanThirdCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(借贷第三方对接信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanThirdCountDim(Map<String,Object> paramMap){
		return loanThirdBaseDao.selectLoanThirdCount(paramMap,true);
	}
	/**
	 * 往(借贷第三方对接信息表)新增一条记录
	 * @param loanThird
	 * @return
	 */
	@Override
	public int insertLoanThird(LoanThird loanThird){
		return loanThirdBaseDao.insertLoanThird(loanThird);
	}
	/**
	 * 批量新增(借贷第三方对接信息表)
	 * @param loanThirdList
	 * @return
	 */
	@Override
	public int insertLoanThirdBatch(List<LoanThird> loanThirdList){
		return loanThirdBaseDao.insertLoanThirdBatch(loanThirdList);
	}
	/**
	 * 更新(借贷第三方对接信息表)信息
	 * @param loanThird
	 * @return
	 */
	@Override
	public int updateLoanThird(LoanThird loanThird){
		return loanThirdBaseDao.updateLoanThird(loanThird);
	}
	/**
	 * 批量更新(借贷第三方对接信息表)信息
	 * @param loanThirdList
	 * @return
	 */
	@Override
	public int updateLoanThirdBatch(List<LoanThird> loanThirdList){
		return loanThirdBaseDao.updateLoanThirdBatch(loanThirdList);
	}
	/**
	 * 根据序列号删除(借贷第三方对接信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoanThirdLogic(java.math.BigInteger id){
		return loanThirdBaseDao.deleteLoanThirdLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(借贷第三方对接信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoanThirdLogicBatch(List<java.math.BigInteger> idList){
		return loanThirdBaseDao.deleteLoanThirdLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(借贷第三方对接信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoanThird(java.math.BigInteger id){
//		return loanThirdBaseDao.deleteLoanThird(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(借贷第三方对接信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoanThirdBatch(List<java.math.BigInteger> idList){
//		return loanThirdBaseDao.deleteLoanThirdBatch(idList);
//	}
	
}
