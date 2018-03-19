/**   
* Filename:    XanaduDao.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午3:34:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.xanadu.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.userXanaduRecord.entity.UserXanaduRecord;

/**
 * Filename:    XanaduDao.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午3:34:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public class XanaduDao extends AbstractBaseDao implements IXanaduDao{

	@Override
	public Integer selectXanaduTrueCount(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("xanadu.select_Xanadu_TrueCount", tmpMap);
	}

	@Override
	public List<UserXanaduRecord> selectXanaduRecordList(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("xanadu.select_XanaduRecord_List", tmpMap);
	}
	
}
