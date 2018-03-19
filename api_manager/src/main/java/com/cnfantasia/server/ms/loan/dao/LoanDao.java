package com.cnfantasia.server.ms.loan.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.loan.entity.LoanBuyLogEntity;

/**
 * 借贷
 * 
 * @author liyulin
 * @version 1.0 2017年6月7日 下午4:07:15
 */
public class LoanDao extends AbstractBaseDao {
	
	public List<LoanBuyLogEntity> selectLoanBuyLogWithList(Map<String, Object> paramMap) {
		return sqlSession.selectList("loan.selectLoanBuyLogWithList", paramMap);
	}

	public int selectLoanBuyLogWithCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("loan.selectLoanBuyLogWithCount", paramMap);
	}
}
