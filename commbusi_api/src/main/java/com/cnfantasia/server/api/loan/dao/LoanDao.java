package com.cnfantasia.server.api.loan.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.loan.entity.ChargeInfoEntity;
import com.cnfantasia.server.api.loan.entity.LoanEntity;
import com.cnfantasia.server.api.loan.entity.LoanUserBaseInfo;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

/**
 * 借贷
 * 
 * @author liyulin
 * @version 1.0 2017年6月7日 上午9:58:49
 */
public class LoanDao extends AbstractBaseDao implements ILoanDao {
	
	/**
	 * 分页查询借贷产品信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LoanEntity> selectLoanProductWithList(Map<String, Object> paramMap) {
		return sqlSession.selectList("loan.selectLoanProductWithList", paramMap);
	}

	/**
	 * 查询借贷产品数量
	 * 
	 * @return
	 */
	@Override
	public int selectLoanProductWithCount() {
		return sqlSession.selectOne("loan.selectLoanProductWithCount");
	}
	
	/**
	 * 根据useId获取用户借贷基本信息
	 * @param userId
	 * @return
	 */
	@Override
	public LoanUserBaseInfo selectLoanUserBaseInfo(BigInteger userId){
		return sqlSession.selectOne("loan.selectLoanUserBaseInfo", userId);
	}
	
	/**
	 * 近6个月月均用水缴费金额
	 * @param userId
	 * @param feeName
	 * @return
	 */
	@Override
	public BigDecimal selectLoanUserWaterOrEnergyFee(BigInteger userId, String feeName){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("feeName", feeName);
		return sqlSession.selectOne("loan.selectLoanUserWaterOrEnergyFee", paramMap);
	}
	
	/**
	 * 近6个月物业缴费记录
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public ChargeInfoEntity selectLoanUserPropertyChargeInfo(BigInteger userId){
		return sqlSession.selectOne("loan.selectLoanUserPropertyChargeInfo", userId);
	}
	
	/**
	 * 近6个月非缴费的交易次数
	 * @param userId
	 * @return
	 */
	@Override
	public Integer selectLoanUserUnPropertyChargeCount(BigInteger userId){
		return sqlSession.selectOne("loan.selectLoanUserUnPropertyChargeCount", userId);
	}
	
	/**
	 * 近6个月理财记录条数
	 * @param userId
	 * @return
	 */
	@Override
	public Integer selectLoanUserFinanceLogCount(BigInteger userId){
		return sqlSession.selectOne("loan.selectLoanUserFinanceLogCount", userId);
	}
}
