package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;


public class RevenueRoleMultiId extends  RevenueRole{
	
	public RevenueRoleMultiId(UserRole role, List<BigInteger> roleIdList) {
		super();
		this.roleIdList = roleIdList;
		this.setRole(role);
	}

	/**角色id*/
	private List<BigInteger> roleIdList;
	public List<BigInteger> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<BigInteger> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
	
}
