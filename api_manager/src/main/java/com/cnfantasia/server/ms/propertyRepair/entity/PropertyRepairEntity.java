package com.cnfantasia.server.ms.propertyRepair.entity;

import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;

/**
 * 报修单详情
 * 
 * @author wenfq 2015-04-06
 * 
 */
public class PropertyRepairEntity extends PropertyRepair {
	String repairTypeName;//报修类型
	PropertyRepairer propertyRepairer;//维修工
	String gbName;//小区名称
	String pmName;//管理处理名称
	String userNickName;//用户名称
	String roomName;//报修门牌号

	//耗材费
	private Integer selfBuyAmount;
	//人工费
	private Integer manuFeeAmount;
	//其它费
	private Integer otherAmount;

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRepairTypeName() {
		return repairTypeName;
	}

	public void setRepairTypeName(String repairTypeName) {
		this.repairTypeName = repairTypeName;
	}

	public PropertyRepairer getPropertyRepairer() {
		return propertyRepairer;
	}

	public void setPropertyRepairer(PropertyRepairer propertyRepairer) {
		this.propertyRepairer = propertyRepairer;
	}

	public Integer getSelfBuyAmount() {
		return selfBuyAmount;
	}

	public void setSelfBuyAmount(Integer selfBuyAmount) {
		this.selfBuyAmount = selfBuyAmount;
	}

	public Integer getManuFeeAmount() {
		return manuFeeAmount;
	}

	public void setManuFeeAmount(Integer manuFeeAmount) {
		this.manuFeeAmount = manuFeeAmount;
	}

	public Integer getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(Integer otherAmount) {
		this.otherAmount = otherAmount;
	}
}
