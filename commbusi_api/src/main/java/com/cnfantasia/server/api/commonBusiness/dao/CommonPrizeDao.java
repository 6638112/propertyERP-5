/**   
* Filename:    CommonPrizeDao.java   
* @version:    1.0  
* Create at:   2014年6月27日 上午12:34:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.prize.entity.FirstLastPrizeTimeEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCountWithUserEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * Filename:    CommonPrizeDao.java
 * @version:    1.0.0
 * Create at:   2014年6月27日 上午12:34:01
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月27日       shiyl             1.0             1.0 Version
 */
public class CommonPrizeDao extends AbstractBaseDao implements ICommonPrizeDao{
//	@Override
//	public int selectIsUsedDiscountCount(BigInteger userId, String month) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("userId", userId);
//		qryMap.put("month", month);
//		return sqlSession.selectOne("commonPrize.select_IsUsed_Discount_Count", qryMap);
//	}
	
//	@Override
//	public PrizeRecordEntity selectLeastDiscountFirstByMonth(BigInteger userId, String month) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("userId", userId);
//		qryMap.put("month", month);
//		return sqlSession.selectOne("commonPrize.select_Least_Discount_First_ByMonth", qryMap);
//	}
	
//	@Override
//	public PrizeRecordEntity selectIsUsedPrizeRecordByMonth(BigInteger userId, String month) {
//		//查询已经使用的折扣
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("userId", userId);
//		qryMap.put("month", month);
//		PrizeRecordEntity usedPrizeRecord = sqlSession.selectOne("commonPrize.select_isUsed_PrizeRecord_ByMonth", qryMap);
//		return usedPrizeRecord;
//	}
	/****/
	@Override
	public int selectIsUsedDiscountCount(BigInteger userId, String startDate, String endDate) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("startDate", startDate);
		qryMap.put("endDate", endDate);
		return sqlSession.selectOne("commonPrize.select_IsUsed_Discount_Count_DayInterval", qryMap);
	}

//
//	@Override
//	public int selectDiscountTotalCount(BigInteger userId, String month) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("userId", userId);
//		qryMap.put("month", month);
//		return sqlSession.selectOne("commonPrize.select_discount_total_count", qryMap);
//	}
	

	@Override
	public PrizeRecordEntity selectLeastDiscountFirstByMonth(BigInteger userId, String startDate, String endDate) {
		//syl-upd 2015-5-20 22:06:31 优化
		//获取中奖记录的Id
		//根据Id查询详情
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("startDate", startDate);
		qryMap.put("endDate", endDate);
		return sqlSession.selectOne("commonPrize.select_Least_Discount_First_ByMonth_DayInterval", qryMap);
	}
	
	@Override
	public List<PrizeRecordEntity> selectDiscountListByMonth(BigInteger userId,String startDate,String endDate){
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("startDate", startDate);
		qryMap.put("endDate", endDate);
		return sqlSession.selectList("commonPrize.select_Discount_List_ByMonth_DayInterval", qryMap);
	}
	@Override
	public List<PrizeRecordEntity> selectDiscountListByMonthWithUser(BigInteger userId, String startDate, String endDate, PageModel pageModel,BigInteger leastRecordId){
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("startDate", startDate);
		qryMap.put("endDate", endDate);
		qryMap.put("leastRecordId", leastRecordId);
		String pageSqlKey = "commonPrize.select_Discount_WithUser_List_ByMonth_DayInterval_page";
		String countSqlKey = "commonPrize.select_Discount_WithUser_List_ByMonth_DayInterval_count";
//		return sqlSession.selectList("commonPrize.select_Discount_WithUser_List_ByMonth_DayInterval", qryMap);
		return PageQueryUtil.selectPageList(sqlSession, qryMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public PrizeRecordEntity selectIsUsedPrizeRecordByMonth(BigInteger userId, String startDate, String endDate) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("startDate", startDate);
		qryMap.put("endDate", endDate);
		PrizeRecordEntity usedPrizeRecord = sqlSession.selectOne("commonPrize.select_isUsed_PrizeRecord_ByMonth_DayInterval", qryMap);
		return usedPrizeRecord;
	}
	
//	@Override
//	public Double selectLeastDiscountNotLoginByMonth(String deviceId,Long deviceType, String month) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("deviceId", deviceId);
//		qryMap.put("deviceType", deviceType);
//		qryMap.put("month", month);
//		return sqlSession.selectOne("commonPrize.select_LeastDiscount_NotLogin_ByMonth",qryMap);//返回数据可能为空 因此应使用Double而非double
//	}

	@Override
	public PrizeRecordTmp selectLeastDiscountNotLoginNowDayByTmpUser(BigInteger tmpUserId){
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("tmpUserId", tmpUserId);
		return sqlSession.selectOne("commonPrize.select_LeastDiscount_NotLoginNowDayByTmpUser",qryMap);
	}
	
//	@Override
//	public PrizeRecordTmp selectLeastDiscountNotLoginNowDay(String deviceId, Long deviceType) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("deviceId", deviceId);
//		qryMap.put("deviceType", deviceType);
//		return sqlSession.selectOne("commonPrize.select_LeastDiscount_NotLogin_NowDay",qryMap);
//	}

	@Override
	public Room selectRoomByPrizeRecordId(BigInteger prizeRecordId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("prizeRecordId", prizeRecordId);
		return sqlSession.selectOne("commonPrize.select_Room_By_PrizeRecordId",qryMap);
	}

	@Override
	public Integer updateDiscountAsUsed(BigInteger prizeRecordId,Integer usedType,Long savedMoney) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("prizeRecordId", prizeRecordId);
		qryMap.put("usedType", usedType);
		qryMap.put("savedMoney", savedMoney);
		return sqlSession.update("commonPrize.update_Discount_As_Used", qryMap);
	}
	
	@Override
	public Integer markPrizeRecordAsIsBack(List<BigInteger> toBackIds) {
		if(toBackIds==null||toBackIds.size()<=0){return 0;}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("toBackIds", toBackIds);
		return sqlSession.update("commonPrize.update_PrizeRecord_As_IsBack", tmpMap);
	}
	
	@Override
	public int selectPrizeDoneCountAllTime(BigInteger userId) {
		return sqlSession.selectOne("commonPrize.select_PrizeDoneCount_AllTime",userId);
	}
	
	@Override
	public int selectSignedRealRoomTotalCount() {
		return sqlSession.selectOne("commonPrize.select_Signed_RealRoom_TotalCount");
	}
	
	@Override
	public int selectUnSignedUserTotalCount() {
		return sqlSession.selectOne("commonPrize.select_UnSigned_User_TotalCount");
	}
	
	@Override
	public List<PrizeRecordEntity> selectPrizeRecordEntityMonthAllWithNull(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("startYearMonthDay", startYearMonthDay);
		tmpMap.put("endYearMonthDay", endYearMonthDay);
		return sqlSession.selectList("commonPrize.select_PrizeRecordEntity_MonthAll_WithNull",tmpMap);
	}
	
	@Override
	public PrizeCountWithUserEntity selectFamilyMaxPrizeCountInfoByUserId(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("startYearMonthDay", startYearMonthDay);
		tmpMap.put("endYearMonthDay", endYearMonthDay);
		return sqlSession.selectOne("commonPrize.select_Family_MaxPrizeCountInfo_ByUserId",tmpMap);
	}
	@Override
	public PrizeCountWithUserEntity selectSignalPrizeCountInfoByUserId(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("startYearMonthDay", startYearMonthDay);
		tmpMap.put("endYearMonthDay", endYearMonthDay);
		return sqlSession.selectOne("commonPrize.select_Signal_PrizeCountInfo_ByUserId",tmpMap);
	}
	@Override
	public FirstLastPrizeTimeEntity selectFamilyFirstLastPrizeTimeByUserId(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonPrize.select_Family_FirstLastPrizeTime_ByUserId",tmpMap);
	}
	
	@Override
	public Integer selectAllPrizeDoneCountRegistUser(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonPrize.select_All_PrizeDoneCount_RegistUser",tmpMap);
	}
	
	@Override
	public Integer selectAllPrizeDoneCountTmpUser(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonPrize.select_All_PrizeDoneCount_TmpUser",tmpMap);
	}
	
	@Override
	public UserSimpleEntity selectUserByPrizeRecordId(BigInteger prizeRecordId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("prizeRecordId", prizeRecordId);
		return sqlSession.selectOne("commonPrize.select_User_By_PrizeRecordId",tmpMap);
	}

	@Override
	public PrizeRecordEntity selectMiniPrizeRecordEntityFromRoomNowTime(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("startYearMonthDay", startYearMonthDay);
		tmpMap.put("endYearMonthDay", endYearMonthDay);
		return sqlSession.selectOne("commonPrize.select_Mini_PrizeRecordEntity_FromRoom_NowTime",tmpMap);
	}

	@Override
	public Integer updateMiniDiscountByUserId(BigInteger userId,Double discount,BigInteger prizeRecordId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("discount", discount);
		tmpMap.put("prizeRecordId", prizeRecordId);
		return sqlSession.update("commonPrize.update_Mini_Discount_ByUserId", tmpMap);
	}

	@Override
	public String selectPre2MonthStartDay(BigInteger groupBuildId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildId", groupBuildId);
		return sqlSession.selectOne("commonPrize.select_Pre2Month_StartDay", tmpMap);
	}
	
}
