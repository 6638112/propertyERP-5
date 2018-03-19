/**   
* Filename:    RedenvelopeDao.java   
* @version:    1.0  
* Create at:   2014年6月23日 上午3:13:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.redenvelope.entity.HbConvertConsumEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeDetailEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;

/**
 * Filename:    RedenvelopeDao.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 上午3:13:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
public class RedenvelopeDao extends AbstractBaseDao implements IRedenvelopeDao{

//	@Override
//	public int selectIsConvertCount(BigInteger userId, String month) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("userId", userId);
//		qryMap.put("month", month);
//		return sqlSession.selectOne("redenvelope.select_IsConvert_Count", qryMap);
//	}


	@Override
	public Long selectTotalConvertMoney(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("redenvelope.select_Total_ConvertMoney", tmpMap);
	}

	@Override
	public Long selectTotalConsumMoney(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("redenvelope.select_Total_ConsumMoney", tmpMap);
	}

	@Override
	public List<PayRedEnvelopeEntity> selectRecord2hbRecord(BigInteger userId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		return sqlSession.selectList("redenvelope.select_Record2hb_Record", qryMap);
	}
	
	@Override
	public List<PayRedEnvelopeEntity> selectRecord2hbRecord(BigInteger userId, int type, PageModel pageModel) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("type", type);
		String pageSqlKey ="redenvelope.select_Record2hb_Record_withPage";
		String countSqlKey ="redenvelope.select_Record2hb_Record_withPage_count";
		return PageQueryUtil.selectPageList(sqlSession, qryMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<PayRedEnvelopeDetailEntity> selectHbConsumRecord(BigInteger userId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		return sqlSession.selectList("redenvelope.select_HbConsum_Record", qryMap);
	}
	
	@Override
	public List<PayRedEnvelopeDetailEntity> selectHbConsumRecord(BigInteger userId, int type, PageModel pageModel) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("type", type);
		String pageSqlKey = "redenvelope.select_HbConsum_Record_withPage";
		String countSqlKey = "redenvelope.select_HbConsum_Record_withPage_count";
		return PageQueryUtil.selectPageList(sqlSession, qryMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<HbConvertConsumEntity> selectHbConvertConsumBothRecord(BigInteger userId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		return sqlSession.selectList("redenvelope.select_HbConvert_Consum_BothRecord", qryMap);
	}
	
	@Override
	public List<HbConvertConsumEntity> selectHbConvertConsumBothRecord(BigInteger userId,PageModel pageModel) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		String pageSqlKey="redenvelope.select_HbConvert_Consum_BothRecord_withPage";
		String countSqlKey="redenvelope.select_HbConvert_Consum_BothRecord_withPage_count";
		return PageQueryUtil.selectPageList(sqlSession, qryMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public Long selectMoneyByDiscount(Double discount) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("discount", discount);
		return sqlSession.selectOne("redenvelope.select_Money_By_Discount", tmpMap);
	}

//	@Override
//	public List<BigInteger> selectAllConvertUserIds() {
//		return sqlSession.selectList("redenvelope.select_AllConvert_UserIds");
//	}
	
	
}
