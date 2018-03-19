package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;

import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;

/**
 * 收益人角色
* Filename:    RevenueRole.java
* @version:    1.0.0
* Create at:   2015年11月16日 下午5:51:39
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月16日       shiyl             1.0             1.0 Version
 */
public class RevenueRole {
	/**所属父类角色*/
	private RevenueRole parentRole;
	/**角色*/
	private UserRole role;
	/**角色id*/
	private BigInteger roleId;
	public RevenueRole(){}
	public RevenueRole(UserRole role, BigInteger roleId,RevenueRole parentRole) {
		this(role, roleId);
		this.parentRole = parentRole;
	}
	
	public RevenueRole(UserRole role, BigInteger roleId) {
		super();
		this.role = role;
		this.roleId = roleId;
	}
	
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}

	public RevenueRole getParentRole() {
		return parentRole;
	}

	public void setParentRole(RevenueRole parentRole) {
		this.parentRole = parentRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parentRole == null) ? 0 : parentRole.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RevenueRole other = (RevenueRole) obj;
		if (parentRole == null) {
			if (other.parentRole != null)
				return false;
		} else if (!parentRole.equals(other.parentRole))
			return false;
		if (role != other.role)
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}
	
	
}
