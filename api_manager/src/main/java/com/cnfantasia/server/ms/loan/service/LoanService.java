package com.cnfantasia.server.ms.loan.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.loan.dao.LoanDao;
import com.cnfantasia.server.ms.loan.entity.LoanBuyLogEntity;

/**
 * 借贷
 * 
 * @author liyulin
 * @version 1.0 2017年6月7日 下午1:13:21
 */
public class LoanService {
	
	private LoanDao loanDao;

	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}

	public List<LoanBuyLogEntity> getLoanBuyLogWithList(Map<String, Object> paramMap){
		return loanDao.selectLoanBuyLogWithList(paramMap);
	}
	
	public int getLoanBuyLogWithCount(Map<String, Object> paramMap){
		return loanDao.selectLoanBuyLogWithCount(paramMap);
	}
}
