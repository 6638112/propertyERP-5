package com.cnfantasia.server.ms.revenue.entity;

import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;

/**
 * 拥有所有权限的角色
* Filename:    RevenueRoleAll.java
* @version:    1.0.0
* Create at:   2015年11月23日 上午10:46:35
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月23日       shiyl             1.0             1.0 Version
 */
public class RevenueRoleAll extends RevenueRole{
	public RevenueRoleAll(UserRole role){
		super();
		this.setRole(role);
	}
}
