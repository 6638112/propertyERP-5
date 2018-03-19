/**   
* Filename:    PrizeDao.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午2:58:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.prize.entity.PrizeRecordQuery;
import com.cnfantasia.server.api.prize.entity.UserWithLeastDiscountEntity;
import com.cnfantasia.server.api.prize.entityDB.PrizeResultForDB;
import com.cnfantasia.server.api.prize.entityDB.SupriseGiftForDB;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict.PrizeSurpriseGift_FromType;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

/**
 * Filename:    PrizeDao.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午2:58:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
@Repository
public class PrizeDao extends AbstractBaseDao implements IPrizeDao{
//	@Override
//	public Double selectLeastDiscountIsLognWithDefault(BigInteger userHasTRoomId, String monthFirstDay,
//			String monthLastDay) {
//		PrizeRecordQuery prizeRecordQry = new PrizeRecordQuery();
//		prizeRecordQry.settUserHasTRoomFId(userHasTRoomId);
//		prizeRecordQry.setPrizeTime_START(monthFirstDay);
//		prizeRecordQry.setPrizeTime_END(monthLastDay);
//		Map<String,Object> paramMap = MapConverter.toMap(prizeRecordQry);
//		paramMap.put(QUERY_TYPE_IF_DIM, false);
//		return sqlSession.selectOne("prize.select_LeastDiscount_IsLogin_WithDefault",paramMap);
//	}

	private IPrizeRecordTmpDataDao prizeRecordTmpDataDao;
	public void setPrizeRecordTmpDataDao(IPrizeRecordTmpDataDao prizeRecordTmpDataDao) {
		this.prizeRecordTmpDataDao = prizeRecordTmpDataDao;
	}
	
	private ICommonRoomDao commonRoomDao;
	public void setCommonRoomDao(ICommonRoomDao commonRoomDao) {
		this.commonRoomDao = commonRoomDao;
	}

	@Override
	public List<PrizeRecord> selectPrizeRecordOrderByPrizeTime(PrizeRecordQuery prizeRecordQuery, PageModel pageModel) {
		return PageQueryUtil.selectPageList(sqlSession, prizeRecordQuery, pageModel, "prize.select_prizeRecord_withPage", "prize.select_prizeRecord_count");
	}

	@Override 
	public int selectPrizeRecordCount(PrizeRecordQuery prizeRecordQuery) {
		return PageQueryUtil.selectTotalCount(sqlSession, prizeRecordQuery, "prize.select_prizeRecord_count");
	}

	@Override
	public Integer selectPrizeDoneCount(BigInteger userId,Integer userType) {
		Integer resCount = null;
		if(userType!=null&&userId!=null){
			if(userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){
				resCount = 0;
				{//查询正式表记录数
					Map<String,Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("userId", userId);
					Integer formalCount = sqlSession.selectOne("prize.select_prizeDoneCount_currDay",tmpMap);
					resCount+=formalCount;
				}
				{
					Map<String,Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("userId", userId);
					tmpMap.put("userType", userType);
					BigInteger roomId = commonRoomDao.selectDefaultRoomIdByUserId(userId);
					//查询临时表中待处理的记录数
					Integer tmpCount = prizeRecordTmpDataDao.selectPrizeRecordTmpData2DealCount(userId, userType, roomId);
					resCount+=tmpCount;
				}
			}else if(userType.compareTo(LoginDict.UserRegistOrTmp.TMP_USER)==0){
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("userTmpId", userId);
				resCount = sqlSession.selectOne("prize.select_prizeDoneCount_currDay_unLogin",tmpMap);
			}
		}
		return resCount;
	}
	
	@Override
	public Double selectLastDiscount(BigInteger userId, Integer userType) {
		Double resDiscount = null;
		if(userType!=null&&userId!=null){
			if(userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("userId", userId);
				resDiscount = sqlSession.selectOne("prize.select_lastDiscount_currDay",tmpMap);
			}else if(userType.compareTo(LoginDict.UserRegistOrTmp.TMP_USER)==0){
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("userTmpId", userId);
				resDiscount = sqlSession.selectOne("prize.select_lastDiscount_currDay_unLogin",tmpMap);
			}
		}
		return resDiscount;
	}
	
//	@Override
//	public List<PrizeRecordEntity> selectPrizeRecordByMonth(BigInteger userId, String month, PageModel pageModel) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("userId", userId);
//		qryMap.put("month", month);
//		String pageSqlKey = "prize.select_PrizeRecord_ByMonth_withPage";
//		String countSqlKey = "prize.select_PrizeRecord_ByMonth_withPage_count";
//		return PageQueryUtil.selectPageList(sqlSession, qryMap, pageModel, pageSqlKey, countSqlKey);
//	}

	@Override
	public List<PrizeRecord> selectPrizeRecordCurrMonthSign() {
		return sqlSession.selectList("prize.select_PrizeRecord_Curr_Month_Sign");
	}
	@Override
	public List<PrizeRecord> selectPrizeRecordCurrMonthUnSign() {
		return sqlSession.selectList("prize.select_PrizeRecord_Curr_Month_UnSign");
	}

	@Override
	public List<PrizeRecordTmp> selectPrizeRecordTmpCurrMonth() {
		return sqlSession.selectList("prize.select_PrizeRecordTmp_Curr_Month");
	}

	@Override
	public PrizeResultForDB doPrizeFromDB(BigInteger userId, Integer userType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		return sqlSession.selectOne("prize.do_Prize_From_DB", tmpMap);
	}

	@Override
	public SupriseGiftForDB fetchSupriseGiftFromDB(BigInteger userId, Integer userType, Integer fromType,BigInteger fromId,String cityName) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("fromType", fromType);
		tmpMap.put("fromId", fromId);
		String sql = null;
		if(PrizeSurpriseGift_FromType.Share.compareTo(fromType)==0||PrizeSurpriseGift_FromType.Neighbour_Activity.compareTo(fromType)==0){
			sql = "prize.fetch_SupriseGift_FromDB_Share";
		}else if(PrizeSurpriseGift_FromType.LightApp_TGLJ.compareTo(fromType)==0){
			sql = "prize.fetch_SupriseGift_FromDB_LightApp_TGLJ";
			tmpMap.put("cityName", cityName);
		}else if(PrizeSurpriseGift_FromType.LightApp_Prize.compareTo(fromType)==0){
			sql = "prize.fetch_SupriseGift_FromDB_LightApp_Prize";
		}else{
			throw new BusinessRuntimeException("PrizeDao.fetchSupriseGiftFromDB.fromType.unsupport");
		}
		return sqlSession.selectOne(sql, tmpMap);
	}

	@Override
	public List<UserWithLeastDiscountEntity> selectUserWithLeastDiscountList(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("startYearMonthDay", startYearMonthDay);
		tmpMap.put("endYearMonthDay", endYearMonthDay);
		return sqlSession.selectList("prize.select_User_WithLeastDiscount_List", tmpMap);
	}

	@Override
	public Integer testTrans() {
//		return sqlSession.update("prize.testTrans");
		return 0;
	}

}
