package com.cnfantasia.server.api.loan.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.loan.entity.ChargeInfoEntity;
import com.cnfantasia.server.api.loan.entity.LoanEntity;
import com.cnfantasia.server.api.loan.entity.LoanUserBaseInfo;

/**
 * 借贷
 * 
 * @author liyulin
 * @version 1.0 2017年6月7日 上午9:58:49
 */
public interface ILoanDao {

	/**
	 * 分页查询借贷产品信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<LoanEntity> selectLoanProductWithList(Map<String, Object> paramMap);

	/**
	 * 查询借贷产品数量
	 * 
	 * @return
	 */
	public int selectLoanProductWithCount();
	
	/**
	 * 根据useId获取用户借贷基本信息
	 * @param userId
	 * @return
	 */
	public LoanUserBaseInfo selectLoanUserBaseInfo(BigInteger userId);
	
	/**
	 * 近6个月月均用水缴费金额
	 * @param userId
	 * @param feeName
	 * @return
	 */
	public BigDecimal selectLoanUserWaterOrEnergyFee(BigInteger userId, String feeName);
	
	/**
	 * 近6个月物业缴费记录
	 * 
	 * @param userId
	 * @return
	 */
	public ChargeInfoEntity selectLoanUserPropertyChargeInfo(BigInteger userId);
	
	/**
	 * 近6个月非缴费的交易次数
	 * @param userId
	 * @return
	 */
	public Integer selectLoanUserUnPropertyChargeCount(BigInteger userId);
	
	/**
	 * 近6个月理财记录条数
	 * @param userId
	 * @return
	 */
	public Integer selectLoanUserFinanceLogCount(BigInteger userId);
	
}
