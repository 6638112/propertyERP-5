/**   
* Filename:    PermiRoleHasTPermiFunctionEntity.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午9:34:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.permi.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.permiRole.entity.PermiRole;
import com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.entity.PermiRoleHasTPermiFunction;

/**
 * Filename:    PermiRoleHasTPermiFunctionEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午9:34:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
public class PermiRoleHasTPermiFunctionEntity extends PermiRoleHasTPermiFunction{
	private static final long serialVersionUID = 1L;
	/**
	 * 角色信息
	 */
	private PermiRole permiRole;
	public PermiRole getPermiRole() {
		return permiRole;
	}
	public void setPermiRole(PermiRole permiRole) {
		this.permiRole = permiRole;
	}
	@Override
	public BigInteger gettPermiRoleFId() {
		if(permiRole==null){return null;}
		return permiRole.getId();
	}
	@Override
	public void settPermiRoleFId(BigInteger tPermiRoleFId) {
		if(permiRole==null){
			permiRole = new PermiRole();
		}
		permiRole.setId(tPermiRoleFId);
	}
	
}
