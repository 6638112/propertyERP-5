package com.cnfantasia.server.ms.channelPartner.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;
import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;

public class ChannelPartnerRecommendEntity extends ChannelPartnerRecommend {
	int signStatus;//'是否签约标志=={"0":"否","1":"是"}'
	String lockDate; //锁定到期日

	public String getLockDate() {
		return lockDate;
	}

	public void setLockDate(String lockDate) {
		this.lockDate = lockDate;
	}

	public int getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(int signStatus) {
		this.signStatus = signStatus;
	}

	List<GroupBuildingRegister> groupBuildingRegister;

	public List<GroupBuildingRegister> getGroupBuildingRegister() {
		return groupBuildingRegister;
	}

	public void setGroupBuildingRegister(List<GroupBuildingRegister> groupBuildingRegister) {
		this.groupBuildingRegister = groupBuildingRegister;
	}

}
