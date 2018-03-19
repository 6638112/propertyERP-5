package com.cnfantasia.server.api.propertycard.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

public class UserHasCardWithGroupBuildingNames extends UserHasPropertyCard {
	List<String> gbNames;
	
	String notifyPhone;
	
	String gbName;
	
	int openState;
	
    Long totalRedEnvelopeMoney;
    
    private List<String> userRoomInfoList;

	public String getNotifyPhone() {
		return notifyPhone;
	}

	public void setNotifyPhone(String notifyPhone) {
		this.notifyPhone = notifyPhone;
	}

	public List<String> getGbNames() {
		return (gbNames == null || gbNames.isEmpty()) ? null: gbNames;
	}

	public void setGbNames(List<String> gbNames) {
		this.gbNames = gbNames;
	}

	public int getOpenState() {
		return openState;
	}

	public void setOpenState(int openState) {
		this.openState = openState;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public Long getTotalRedEnvelopeMoney() {
		return totalRedEnvelopeMoney;
	}

	public void setTotalRedEnvelopeMoney(Long totalRedEnvelopeMoney) {
		this.totalRedEnvelopeMoney = totalRedEnvelopeMoney;
	}

	public List<String> getUserRoomInfoList() {
		return userRoomInfoList;
	}

	public void setUserRoomInfoList(List<String> userRoomInfoList) {
		this.userRoomInfoList = userRoomInfoList;
	}
	
}
