package com.cnfantasia.server.ms.revenue.task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenueOtherFee;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 物业费其它费用定时任务
 * @author shiyl
 *
 */
@Repository
public class PropertyOtherFeeSynTask extends AbstractSynTask{
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private ISyn2RevSignal<PayBillForRevenueOtherFee,BigDecimal> propertyOtherFee2RevSignal;
	public void setPropertyOtherFee2RevSignal(ISyn2RevSignal<PayBillForRevenueOtherFee,BigDecimal> propertyOtherFee2RevSignal) {
		this.propertyOtherFee2RevSignal = propertyOtherFee2RevSignal;
	}
	
	@Override
	public List<BigInteger> getRoleIdList(Integer projectType,UserRole role){
		List<BigInteger> companyIdList = revenueDao.selectRevConfigCompanyIdList(projectType, role);
		return companyIdList;
	}
	
	@Override
	public Integer synSignal(RevenueRole revenueRole){
		Integer projectType = getProjectType();
		Integer succCount = propertyOtherFee2RevSignal.syn2RegSignal(projectType, revenueRole);
		return succCount;
	}
	
	@Override
	public Integer getProjectType(){
		return RevenueDict.RevenueProject.PropertyOtherFee;
	}
	
	@Override
	public UserRole getUserRole(){
		return UserRole.PropertyCompany;
	}


	@Override
	public Integer execTask() {
		return super.execTask();
	}

	@Override
	public String getLock() {
		return Lock.PropertyOtherFeeSynTask;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	protected Integer execTaskOne(UserRole role, BigInteger roleId,
			Integer projectType) {
		return super.execTaskOne(role, roleId, projectType);
	}
	
}
