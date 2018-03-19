/**   
* Filename:    PrizeActivityDao.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午7:56:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftUqMarkCode;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActHasOptEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActivityEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftForPrize;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionForList;
import com.cnfantasia.server.commbusi.prizeActivity.entity.TimeRange;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    PrizeActivityDao.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午7:56:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public class PrizeActivityDao extends AbstractBaseDao implements IPrizeActivityDao{

	@Override
	public List<MsPrizeActivity> selectMsPrizeActivityList(String name,
			Integer activityStatus, String startTime, String endTime,
			PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("name", name);
		tmpMap.put("activityStatus", activityStatus);
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		String pageSqlKey = "prizeActivity.select_MsPrizeActivity_List_page";
		String countSqlKey = "prizeActivity.select_MsPrizeActivity_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public MsPrizeActivityEntity selectMsPrizeActivityDetail(BigInteger actId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("actId", actId);
		return sqlSession.selectOne("prizeActivity.select_MsPrizeActivity_Detail",tmpMap);
	}
	
	@Override
	public Integer selectMsPrizeActivityQryStatus(BigInteger actId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("actId", actId);
		return sqlSession.selectOne("prizeActivity.select_MsPrizeActivity_QryStatus",tmpMap);
	}
	
	@Override
	public List<MsPrizeActHasOptEntity> selectActHasOptList(BigInteger actId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("actId", actId);
		return sqlSession.selectList("prizeActivity.select_ActHasOpt_List",tmpMap);
	}
	
	
	@Override
	public List<MsPrizeOptionForList> selectMsPrizeOptionList(String name,
			Integer qryStatus,Integer useStatus, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("name", name);
		tmpMap.put("qryStatus", qryStatus);
		tmpMap.put("useStatus", useStatus);
		String pageSqlKey = "prizeActivity.select_MsPrizeOption_List_page";
		String countSqlKey = "prizeActivity.select_MsPrizeOption_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<MsPrizeOption> selectMsPrizeOptionAvalList(String actStartTime,String actEndTime,BigInteger ignoreActId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("actStartTime", actStartTime);
		tmpMap.put("actEndTime", actEndTime);
		tmpMap.put("ignoreActId", ignoreActId);
		return sqlSession.selectList("prizeActivity.select_MsPrizeOption_Aval_List",tmpMap);
	}
	
	@Override
	public MsPrizeOptionEntity selectMsPrizeOptionDetail(BigInteger priOptId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("priOptId", priOptId);
		return sqlSession.selectOne("prizeActivity.select_MsPrizeOption_Detail", tmpMap);
	}

	@Override
	public List<String> selectMsPrizeOptionIsUsedPriActList(BigInteger priOptId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("priOptId", priOptId);
		return sqlSession.selectList("prizeActivity.select_MsPrizeOption_IsUsedPriAct_List",tmpMap);
	}
	
	@Override
	public List<TimeRange> selectPrizeActivityTimeRangeList() {
		return sqlSession.selectList("prizeActivity.select_PrizeActivity_TimeRange_List");
	}

	@Override
	public Integer selectLeftPrizeOptionCount(BigInteger priOptId) {
		//1.查询奖项总的奖品总数量
		Integer avaTotalCount = 0;
		{
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("priOptId", priOptId);
			avaTotalCount =  sqlSession.selectOne("prizeActivity.select_MsPrizeGift_TotalCount", tmpMap);
		}
		//2.查询未结束活动占用的奖品总数量
		Integer notFinishedCount = 0;
		if(avaTotalCount>0){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("priOptId", priOptId);
			notFinishedCount = sqlSession.selectOne("prizeActivity.select_notFinished_MsPrizeActivity_TotalGiftCount", tmpMap);
			if(notFinishedCount==null){notFinishedCount = 0;}
		}
		//3.查询已结束活动使用的奖品总数量
		Integer finishedUsedCount = 0;
		if(avaTotalCount>0){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("priOptId", priOptId);
			finishedUsedCount = sqlSession.selectOne("prizeActivity.select_finished_MsPrizeActivity_UsedGiftCount", tmpMap);
		}
		return avaTotalCount-notFinishedCount-finishedUsedCount;
	}

	@Override
	public Set<GiftUqMarkCode> selectRepeatCodeSet(
			Set<GiftUqMarkCode> toCheckCodeSet) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("codeList", toCheckCodeSet);
		SetHandler tmpHandler = new SetHandler();
		sqlSession.select("prizeActivity.select_RepeatCode_Set", tmpMap, tmpHandler);
		return tmpHandler.getResultSet();
	}

	@Override
	public List<MsPrizeGiftEntity> selectMsPrizeGiftList(BigInteger priOptId,
			String codeName, String valueCode, String uqMark,
			Integer qryStatus, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("priOptId", priOptId);
		tmpMap.put("codeName", codeName);
		tmpMap.put("valueCode", valueCode);
		tmpMap.put("uqMark", uqMark);
		tmpMap.put("qryStatus", qryStatus);
		String pageSqlKey = "prizeActivity.select_MsPrizeGiftList_Page";
		String countSqlKey = "prizeActivity.select_MsPrizeGiftList_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public Integer deleteMsPrizeGiftAndCodeLogic(BigInteger giftId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("giftId", giftId);
		return sqlSession.update("prizeActivity.delete_MsPrizeGiftAndCode_Logic", tmpMap);
	}

	@Override
	public Integer deletePirzeOptionCity(BigInteger optId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("optId", optId);
		return sqlSession.delete("prizeActivity.delete_PirzeOption_City", tmpMap);
	}

	@Override
	public Integer deleteMsPrizeActivityWithRelaLogic(BigInteger actId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("actId", actId);
		return sqlSession.update("prizeActivity.delete_MsPrizeActivity_WithRela_Logic",tmpMap);
	}

	@Override
	public MsPrizeActivity selectMsPrizeActivityCurrDoing() {
		return sqlSession.selectOne("prizeActivity.select_MsPrizeActivity_CurrDoing");
	}

	@Override
	public MsPrizeGiftForPrize selectMsPrizeGiftDetail(BigInteger giftId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("giftId", giftId);
		return sqlSession.selectOne("prizeActivity.select_MsPrizeGift_Detail",tmpMap);
	}

	
}
