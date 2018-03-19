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
package com.cnfantasia.server.ms.permi.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.msbase.omsPermiRoleHasTOmsPermiFunction.entity.OmsPermiRoleHasTOmsPermiFunction;

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
public class PermiRoleHasTPermiFunctionEntity extends OmsPermiRoleHasTOmsPermiFunction{
	private static final long serialVersionUID = 1L;
	/**
	 * 角色信息
	 */
	private OmsPermiRole permiRole;
	public OmsPermiRole getPermiRole() {
		return permiRole;
	}
	public void setPermiRole(OmsPermiRole permiRole) {
		this.permiRole = permiRole;
	}
	
	@Override
	public BigInteger gettOmsPermiRoleFId() {
		if(permiRole==null){return null;}
		return permiRole.getId();
	}
	@Override
	public void settOmsPermiRoleFId(BigInteger tOmsPermiRoleFId) {
		if(permiRole==null){
			permiRole = new OmsPermiRole();
		}
		permiRole.setId(tOmsPermiRoleFId);
	}
	
}
