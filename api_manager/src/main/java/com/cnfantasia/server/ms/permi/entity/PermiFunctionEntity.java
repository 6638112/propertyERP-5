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
package com.cnfantasia.server.ms.permi.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;

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
public class PermiFunctionEntity extends OmsPermiFunction{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色信息列表
	 */
	private List<OmsPermiRole> permiRoleList;

	public List<OmsPermiRole> getPermiRoleList() {
		return permiRoleList;
	}
	public void setPermiRoleList(List<OmsPermiRole> permiRoleList) {
		this.permiRoleList = permiRoleList;
	}
	
	
}
