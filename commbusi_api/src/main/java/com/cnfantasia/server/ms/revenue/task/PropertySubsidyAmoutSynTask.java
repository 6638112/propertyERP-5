package com.cnfantasia.server.ms.revenue.task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 物业费补贴定时任务
* Filename:    PropertySubsidyAmoutSynTask.java
* @version:    1.0.0
* Create at:   2015年11月24日 下午1:38:58
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月24日       shiyl             1.0             1.0 Version
 */
public class PropertySubsidyAmoutSynTask extends AbstractSynTask{
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private ISyn2RevSignal<PayBillForRevenue,BigDecimal> propertySubsidyAmout2RevSignal;
	public void setPropertySubsidyAmout2RevSignal(
			ISyn2RevSignal<PayBillForRevenue, BigDecimal> propertySubsidyAmout2RevSignal) {
		this.propertySubsidyAmout2RevSignal = propertySubsidyAmout2RevSignal;
	}

	@Override
	public List<BigInteger> getRoleIdList(Integer projectType,UserRole role) {
		List<BigInteger> companyIdList = revenueDao.selectRevConfigCompanyIdList(projectType, role);
		return companyIdList;
	}

	@Override
	public Integer synSignal(RevenueRole revenueRole) {
		Integer projectType = getProjectType();
		Integer succCount = propertySubsidyAmout2RevSignal.syn2RegSignal(projectType, revenueRole);
		return succCount;
	}

	@Override
	public Integer getProjectType() {
		return RevenueDict.RevenueProject.PropertySubsidyAmout;
	}
	
	@Override
	public UserRole getUserRole() {
		return UserRole.PropertyCompany;
	}

	@Override
	public Integer execTask() {
		return super.execTask();
	}

	@Override
	public String getLock() {
		return Lock.PropertySubsidyAmoutSynTask;
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	protected Integer execTaskOne(UserRole role, BigInteger roleId,
			Integer projectType) {
		return super.execTaskOne(role, roleId, projectType);
	}
}
