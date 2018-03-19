/**   
* Filename:    CommonPointDao.java   
* @version:    1.0  
* Create at:   2015年1月5日 上午6:37:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * Filename:    CommonPointDao.java
 * @version:    1.0.0
 * Create at:   2015年1月5日 上午6:37:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月5日       shiyl             1.0             1.0 Version
 */
public class CommonPointDao extends AbstractBaseDao implements ICommonPointDao{
	
	@Override
	public List<PointData> selectPointDataByUserId(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("commonPoint.select_PointData_ByUserId", tmpMap);
	}
	
}
