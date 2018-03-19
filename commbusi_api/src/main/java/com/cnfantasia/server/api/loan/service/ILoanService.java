package com.cnfantasia.server.api.loan.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.loan.entity.LoanEntity;
import com.cnfantasia.server.api.loan.entity.LoanUserInfoEntity;

/**
 * 借贷
 * 
 * @author liyulin
 * @version 1.0 2017年6月7日 上午9:33:03
 */
public interface ILoanService {

	/**
	 * 分页查询借贷产品信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<LoanEntity> getLoanProductWithList(Map<String, Object> paramMap);

	/**
	 * 查询借贷产品数量
	 * 
	 * @return
	 */
	public int getLoanProductWithCount();
	
	/**
	 * 根据useId获取用户借贷信息
	 * @param userId
	 * @return
	 */
	public LoanUserInfoEntity getLoanUserInfo(BigInteger userId);

}
