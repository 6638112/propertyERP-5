package com.cnfantasia.server.ms.omsPermiRole.entity;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;

public class OmsPermiFunctionEntity extends OmsPermiFunction {

	/**
	 * 是否已分配给某角色
	 */
	private int isAssignedToRole = 0;

	public int getIsAssignedToRole() {
		return isAssignedToRole;
	}

	public void setIsAssignedToRole(int isAssignedToRole) {
		this.isAssignedToRole = isAssignedToRole;
	}

}
