package com.cnfantasia.server.ms.revenue.task;

import java.math.BigInteger;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RoomValidateForRevenue;

/**
 * 门牌认证收益计算定时任务
* Filename:    RoomValidateSynTaskCompany.java
* @version:    1.0.0
* Create at:   2015年11月19日 下午2:35:32
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       shiyl             1.0             1.0 Version
 */
public class RoomValidateSynTaskCompany extends AbstractSynTask{
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private ISyn2RevSignal<RoomValidateForRevenue,Integer> roomValidate2RevSignal;
	public void setRoomValidate2RevSignal(ISyn2RevSignal<RoomValidateForRevenue,Integer> roomValidate2RevSignal) {
		this.roomValidate2RevSignal = roomValidate2RevSignal;
	}

	@Override
	public UserRole getUserRole() {
		return UserRole.PropertyCompany;
	}

	@Override
	public List<BigInteger> getRoleIdList(Integer projectType,UserRole role) {
		return revenueDao.selectRevConfigCompanyIdList(projectType, role);
	}

	@Override
	public Integer synSignal(RevenueRole revenueRole) {
		Integer projectType = getProjectType();
		return roomValidate2RevSignal.syn2RegSignal(projectType, revenueRole);
	}

	@Override
	public Integer getProjectType() {
		return RevenueDict.RevenueProject.RoomValidate;
	}

	@Override
	public Integer execTask() {
		return super.execTask();
	}

	@Override
	public String getLock() {
		return Lock.RoomValidateSynTaskCompany;
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	protected Integer execTaskOne(UserRole role, BigInteger roleId,
			Integer projectType) {
		return super.execTaskOne(role, roleId, projectType);
	}
	
}
