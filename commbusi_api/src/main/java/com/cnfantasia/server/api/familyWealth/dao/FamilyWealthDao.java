/**   
* Filename:    FamilyWealthDao.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午2:51:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyWealth.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.familyWealth.entity.FamilyWealthOptionEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

/**
 * Filename:    FamilyWealthDao.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午2:51:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public class FamilyWealthDao extends AbstractBaseDao implements IFamilyWealthDao{

	@Override
	public List<FamilyWealthOptionEntity> selectFamilyWealthOptionList(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("familyWealth.select_FamilyWealthOption_List", tmpMap);
	}
	
}
