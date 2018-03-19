package com.cnfantasia.server.ms.propertyRepair.entity;

import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;

/**
 * 维修工
 * 
 * @author wenfq 2015-04-09
 * 
 */
public class PropertyRepairerEntity extends PropertyRepairer {
	String repairTypeName;//报修类型

	String propertyManagmentName;//管理处名称

	Double starLevel; //评论得分

	public String getRepairTypeName() {
		return repairTypeName;
	}

	public void setRepairTypeName(String repairTypeName) {
		this.repairTypeName = repairTypeName;
	}

	public String getPropertyManagmentName() {
		return propertyManagmentName;
	}

	public void setPropertyManagmentName(String propertyManagmentName) {
		this.propertyManagmentName = propertyManagmentName;
	}

	public Double getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Double starLevel) {
		this.starLevel = starLevel;
	}

}
