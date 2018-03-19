/**   
* Filename:    PermiDao.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午10:04:56   
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
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.ms.permi.entity.PermiFunctionEntity;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * Filename:    PermiDao.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午10:04:56
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
@Repository
public class PermiDao extends AbstractBaseDao implements IPermiDao{

	@Override
	public List<PermiFunctionEntity> selectFuncList(Integer status) {
		OmsPermiFunction permiFunction = new OmsPermiFunction();
		permiFunction.setStatus(status);
		return sqlSession.selectList("permi.select_permiFunction", MapConverter.toMap(permiFunction));
	}
	
	@Override
	public List<BigInteger> selectRoleIdsByUserId(BigInteger userId) {
		return sqlSession.selectList("permi.select_roleIds_byUserId", userId);
	}

	@Override
	public List<Map<String, Object>> selectUserByRoleCode(String roleCode) {
		return sqlSession.selectList("omsPermiRole.getUserInfoByRoleCode", roleCode);
	}

	@Override
	public List<OmsPermiRole> getOmsPermiRoleByUserId(BigInteger userId) {
		return sqlSession.selectList("permi.select_roleList_byUserId", userId);	
	}
}
