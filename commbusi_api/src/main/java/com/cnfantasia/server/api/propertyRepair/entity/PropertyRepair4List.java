package com.cnfantasia.server.api.propertyRepair.entity;

import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;

/**
 * 报修单（给列表使用）
 * 
 * @author wenfq 2014-04-06
 * 
 */
public class PropertyRepair4List extends PropertyRepair {
	private static final long serialVersionUID = 1L;
	String repairTypeName;
	String processDesc;

	public String getRepairTypeName() {
		return repairTypeName;
	}

	public void setRepairTypeName(String repairTypeName) {
		this.repairTypeName = repairTypeName;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}

}
