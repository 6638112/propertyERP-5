/**   
* Filename:    IPermiDao.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午10:02:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.permi.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.ms.permi.entity.PermiFunctionEntity;

/**
 * Filename:    IPermiDao.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午10:02:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
public interface IPermiDao {
	/**
	 * 根据条件查询功能列表
	 * @param status 功能状态
	 * @return
	 */
	public List<PermiFunctionEntity> selectFuncList(Integer status);
	
	/**
	 * 根据用户Id查询对应的角色Ids
	 * @param userId
	 * @return
	 */
	public List<BigInteger> selectRoleIdsByUserId(BigInteger userId);
	
}
