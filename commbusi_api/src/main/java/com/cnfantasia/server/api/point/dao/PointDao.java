/**   
* Filename:    PointDao.java   
* @version:    1.0  
* Create at:   2014年12月30日 下午1:44:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.pointSourceRecord.entity.PointSourceRecord;

/**
 * Filename:    PointDao.java
 * @version:    1.0.0
 * Create at:   2014年12月30日 下午1:44:23
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月30日       shiyl             1.0             1.0 Version
 */
public class PointDao extends AbstractBaseDao implements IPointDao{

	@Override
	public Integer addPointData(BigInteger userId, Long value) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("value", value);
		return sqlSession.update("point.add_PointData", tmpMap);
	}

	@Override
	public Integer costPointData(BigInteger userId, Long value) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("value", value);
		return sqlSession.update("point.cost_PointData", tmpMap);
	}

	@Override
	public PointSourceRecord selectLastPointSourceRecord(BigInteger userId, Integer type) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("type", type);
		return sqlSession.selectOne("point.select_Last_PointSourceRecord", tmpMap);
	}
	
	
}
