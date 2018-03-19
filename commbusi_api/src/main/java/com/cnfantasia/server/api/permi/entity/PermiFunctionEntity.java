/**   
* Filename:    PermiFunctionEntity.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午9:21:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.permi.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.permiFunction.entity.PermiFunction;
import com.cnfantasia.server.domainbase.permiRole.entity.PermiRole;

/**
 * Filename:    PermiFunctionEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午9:21:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
public class PermiFunctionEntity extends PermiFunction{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色信息列表
	 */
	private List<PermiRole> permiRoleList;

	public List<PermiRole> getPermiRoleList() {
		return permiRoleList;
	}
	public void setPermiRoleList(List<PermiRole> permiRoleList) {
		this.permiRoleList = permiRoleList;
	}
	
	
}
