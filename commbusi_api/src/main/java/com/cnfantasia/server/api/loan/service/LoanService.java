package com.cnfantasia.server.api.loan.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.loan.constants.LoanDict;
import com.cnfantasia.server.api.loan.dao.ILoanDao;
import com.cnfantasia.server.api.loan.entity.ChargeInfoEntity;
import com.cnfantasia.server.api.loan.entity.LoanEntity;
import com.cnfantasia.server.api.loan.entity.LoanUserBaseInfo;
import com.cnfantasia.server.api.loan.entity.LoanUserInfoEntity;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.domainbase.roomHasCarNum.dao.IRoomHasCarNumBaseDao;

/**
 * 借贷
 * @author liyulin
 * @version 1.0 2017年6月7日 上午9:57:56
 */
public class LoanService implements ILoanService {
	
	private ILoanDao loanDao;
	private IRoomHasCarNumBaseDao roomHasCarNumBaseDao;
	
	public void setLoanDao(ILoanDao loanDao) {
		this.loanDao = loanDao;
	}
	
	public void setRoomHasCarNumBaseDao(IRoomHasCarNumBaseDao roomHasCarNumBaseDao) {
		this.roomHasCarNumBaseDao = roomHasCarNumBaseDao;
	}



	/**
	 * 分页查询借贷产品信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LoanEntity> getLoanProductWithList(Map<String, Object> paramMap) {
		return loanDao.selectLoanProductWithList(paramMap);
	}

	/**
	 * 查询借贷产品数量
	 * 
	 * @return
	 */
	@Override
	public int getLoanProductWithCount() {
		return loanDao.selectLoanProductWithCount();
	}
	
	/**
	 * 根据useId获取用户借贷信息
	 * @param userId
	 * @return
	 */
	@Override
	public LoanUserInfoEntity getLoanUserInfo(BigInteger userId){
		LoanUserInfoEntity loanUserInfoEntity = new LoanUserInfoEntity();
		//基本信息
		LoanUserBaseInfo loanUserBaseInfo = loanDao.selectLoanUserBaseInfo(userId);
		if(null!=loanUserBaseInfo){
			loanUserInfoEntity.setName(loanUserBaseInfo.getName());
			loanUserInfoEntity.setMobile(loanUserBaseInfo.getMobile());
			loanUserInfoEntity.setCardId(loanUserBaseInfo.getCardId());
			loanUserInfoEntity.setCityName(loanUserBaseInfo.getCityName());
			int residenceTime = 0;
			if(null!=loanUserBaseInfo.getResidenceTime()){
				residenceTime=loanUserBaseInfo.getResidenceTime();
			}
			loanUserInfoEntity.setResidenceTime(residenceTime);
			
			double roomSize = 0.0;
			if(null!=loanUserBaseInfo.getRoomSize()){
				roomSize = loanUserBaseInfo.getRoomSize();
			}
			loanUserInfoEntity.setRoomSize(roomSize);
			
			Boolean isPropertyProprietor = loanUserBaseInfo.getIsPropertyProprietor();
			if(null==isPropertyProprietor){
				loanUserInfoEntity.setIsPropertyProprietor(LoanDict.Sure.UNKNOWN);
			} else if(isPropertyProprietor){
				loanUserInfoEntity.setIsPropertyProprietor(LoanDict.Sure.YES);
			} else {
				loanUserInfoEntity.setIsPropertyProprietor(LoanDict.Sure.NO);
			}
		}
		//近6个月月均用电缴费金额
		BigDecimal energyFeeBigDecimal = loanDao.selectLoanUserWaterOrEnergyFee(userId, "电");
		double energyFee = 0.0;
		if(null!=energyFeeBigDecimal){
			energyFee = BigDecimalUtil.div100(energyFeeBigDecimal).doubleValue();
		}
		loanUserInfoEntity.setEnergyFee(energyFee);
		
		//近6个月月均用水缴费金额
		BigDecimal waterFeeBigDecimal = loanDao.selectLoanUserWaterOrEnergyFee(userId, "水");
		double waterFee = 0.0;
		if(null!=waterFeeBigDecimal){
			waterFee = BigDecimalUtil.div100(waterFeeBigDecimal).doubleValue();
		}
		loanUserInfoEntity.setWaterFee(waterFee);
		
		//近6个月物业缴费记录
		ChargeInfoEntity propertyCharge = loanDao.selectLoanUserPropertyChargeInfo(userId);
		if(null==propertyCharge){
			propertyCharge = new ChargeInfoEntity();
			propertyCharge.setChargeCount(0);
			propertyCharge.setRealAmount(BigDecimal.ZERO);
			propertyCharge.setTotalAmount(BigDecimal.ZERO);
		} else {
			BigDecimal totalAmount = propertyCharge.getTotalAmount();
			if(null!=totalAmount){
				totalAmount = BigDecimalUtil.div100(totalAmount);
			} else {
				totalAmount = BigDecimal.ZERO;
			}
			propertyCharge.setTotalAmount(totalAmount);
			
			BigDecimal realAmount = propertyCharge.getRealAmount();
			if(null!=realAmount){
				realAmount = BigDecimalUtil.div100(realAmount);
			} else {
				realAmount = BigDecimal.ZERO;
			}
			propertyCharge.setRealAmount(realAmount);
		}
		loanUserInfoEntity.setPropertyCharge(propertyCharge);
		
		//是否有车、车位
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserId", userId);
		Integer carCount = roomHasCarNumBaseDao.selectRoomHasCarNumCount(paramMap, false);
		int carSure = LoanDict.Sure.UNKNOWN;
		if(null!=carCount){
			if(carCount==0){carSure = LoanDict.Sure.NO;} else {carSure = LoanDict.Sure.YES;}
		}
		loanUserInfoEntity.setHasCar(carSure);
		loanUserInfoEntity.setHasParkingLot(carSure);
		
		//近6个月非缴费的交易次数
		Integer unPropertyChargeCount = loanDao.selectLoanUserUnPropertyChargeCount(userId);
		if(null==unPropertyChargeCount){
			loanUserInfoEntity.setUnPropertyChargeCount(0);
		} else {
			loanUserInfoEntity.setUnPropertyChargeCount(unPropertyChargeCount);
		}
		
		//近6个月理财记录条数
		Integer financeLogCount = loanDao.selectLoanUserFinanceLogCount(userId);
		if(null==financeLogCount){
			loanUserInfoEntity.setFinanceLogCount(0);
		} else {
			loanUserInfoEntity.setFinanceLogCount(financeLogCount);
		}
		
		return loanUserInfoEntity;
	}

}
