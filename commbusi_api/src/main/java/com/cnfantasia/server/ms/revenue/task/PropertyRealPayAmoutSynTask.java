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
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 物业费实收定时任务
* Filename:    PropertyRealPayAmoutSynTask.java
* @version:    1.0.0
* Create at:   2015年11月19日 下午2:28:02
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       shiyl             1.0             1.0 Version
 */
@Repository
public class PropertyRealPayAmoutSynTask extends AbstractSynTask{
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private ISyn2RevSignal<PayBillForRevenue,BigDecimal> propertyRealPayAmout2RevSignal;
	public void setPropertyRealPayAmout2RevSignal(ISyn2RevSignal<PayBillForRevenue,BigDecimal> propertyRealPayAmout2RevSignal) {
		this.propertyRealPayAmout2RevSignal = propertyRealPayAmout2RevSignal;
	}
	
	@Override
	public List<BigInteger> getRoleIdList(Integer projectType,UserRole role){
		List<BigInteger> companyIdList = revenueDao.selectRevConfigCompanyIdList(projectType, role);
		return companyIdList;
	}
	
	@Override
	public Integer synSignal(RevenueRole revenueRole){
		Integer projectType = getProjectType();
		Integer succCount = propertyRealPayAmout2RevSignal.syn2RegSignal(projectType, revenueRole);
		return succCount;
	}
	
	@Override
	public Integer getProjectType(){
		return RevenueDict.RevenueProject.PropertyRealPayAmout;
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
		return Lock.PropertyRealPayAmoutSynTask;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	protected Integer execTaskOne(UserRole role, BigInteger roleId,
			Integer projectType) {
		return super.execTaskOne(role, roleId, projectType);
	}
	
}
