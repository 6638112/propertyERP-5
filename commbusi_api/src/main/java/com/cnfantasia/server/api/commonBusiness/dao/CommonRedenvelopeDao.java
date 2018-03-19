/**   
* Filename:    CommonRedenvelopeDao.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:58:58   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * Filename:    CommonRedenvelopeDao.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:58:58
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
public class CommonRedenvelopeDao extends AbstractBaseDao implements ICommonRedenvelopeDao{
	@Override
	public Long selectTotalHbBalance(BigInteger userId, int type) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("type", type);
		return sqlSession.selectOne("commonRedenvelope.select_Total_HbBalance", tmpMap);
	}

	@Override
	public List<PayRedEnvelope> selectPayRedEnvelopeAvailable(BigInteger userId,int type) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("type", type);
		return sqlSession.selectList("commonRedenvelope.select_PayRedEnvelope_Available", tmpMap);
	}

}
