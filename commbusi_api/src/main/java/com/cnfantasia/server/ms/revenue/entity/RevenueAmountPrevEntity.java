package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * 收益额总览信息实体类
 */
public class RevenueAmountPrevEntity {
	/**角色Id*/
	private BigInteger roleId;
	/**角色type*/
	private Integer roleType;
	/**角色名称*/
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}

	/**收益总额预览信息*/
	private List<RevenueAmountInfo> amountInfoList;
	
	public BigInteger getRoleId() {
		return roleId;
	}

	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RevenueAmountInfo> getAmountInfoList() {
		return amountInfoList;
	}

	public void setAmountInfoList(List<RevenueAmountInfo> amountInfoList) {
		this.amountInfoList = amountInfoList;
	}
	
}
