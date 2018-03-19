/**   
* Filename:    SignDao.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午2:42:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * Filename:    SignDao.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午2:42:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public class SignDao extends AbstractBaseDao implements ISignDao{

	@Override
	public CommSignRecord selectLastCommSignRecord(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("sign.select_Last_CommSignRecord", tmpMap);
	}
	
}
