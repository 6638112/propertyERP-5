/**   
* Filename:    ExchangeDao.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:26:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.exchange.entity.ExchangeBaseEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeRelationGoalEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;

/**
 * Filename:    ExchangeDao.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:26:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public class ExchangeDao extends AbstractBaseDao implements IExchangeDao{

	@Override
	public ExchangeEntity selectMostHotContent(BigInteger userId, BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		ExchangeEntity resExchangeEntity = sqlSession.selectOne("exchange.select_MostHot_Content", tmpMap);
		return resExchangeEntity;
	}

	@Override
	public List<ExchangeBaseEntity> selectHotContentList(BigInteger userId, BigInteger groupBuildingId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "exchange.select_HotContent_List_Page";
		String countSqlKey = "exchange.select_HotContent_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<ExchangeBaseEntity> selectLaunchContentList(BigInteger userId, BigInteger groupBuildingId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "exchange.select_LaunchContent_List_Page";
		String countSqlKey = "exchange.select_LaunchContent_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<ExchangeBaseEntity> selectJoinContentList(BigInteger userId, BigInteger groupBuildingId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "exchange.select_JoinContent_List_Page";
		String countSqlKey = "exchange.select_JoinContent_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<ExchangeBaseEntity> selectLaunchAndJoinContentList(BigInteger userId, BigInteger groupBuildingId,
			PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "exchange.select_LaunchAndJoin_Content_List_Page";
		String countSqlKey = "exchange.select_LaunchAndJoin_Content_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public ExchangeEntity selectExchangeContentDetail(BigInteger userId, BigInteger exchangeContentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("exchangeContentId", exchangeContentId);
		ExchangeEntity resExchangeEntity =  sqlSession.selectOne("exchange.select_ExchangeContent_Detail", tmpMap);
		return resExchangeEntity;
	}
	
	@Override
	public List<ExchangeRelationGoalEntity> selectSubJoinContentListWithRelation(BigInteger userId,
			BigInteger exchangeContentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("exchangeContentId", exchangeContentId);
		return sqlSession.selectList("exchange.select_Sub_JoinContent_List_WithRelation", tmpMap);
	}
	
	@Override
	public ExchangeRelationGoalEntity selectSubJoinContentWithRelationSucc(BigInteger userId,
			BigInteger exchangeContentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("exchangeContentId", exchangeContentId);
		return sqlSession.selectOne("exchange.select_Sub_JoinContent_WithRelation_Succ", tmpMap);
	}
	
	@Override
	public List<ExchangeBaseEntity> selectSubJoinContentList(BigInteger userId, BigInteger exchangeContentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("exchangeContentId", exchangeContentId);
		return sqlSession.selectList("exchange.select_Sub_JoinContent_List", tmpMap);
	}

	@Override
	public Integer updateExchangeRelation2Finished(BigInteger userId, BigInteger exchangeRelationId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("exchangeRelationId", exchangeRelationId);
		return sqlSession.update("exchange.update_ExchangeRelation_2Finished", tmpMap);
	}

	@Override
	public Integer deleteLaunchContent(BigInteger userId, BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("contentId", contentId);
		return sqlSession.delete("exchange.delete_LaunchContent", tmpMap);
	}
	
	@Override
	public Integer selectJoinTotalCount(BigInteger updContentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("contentId", updContentId);
		return sqlSession.selectOne("exchange.select_Join_TotalCount", tmpMap);
	}

	@Override
	public Boolean selectCanWantedBoolean(BigInteger userId, BigInteger exchangeRelationId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("exchangeRelationId", exchangeRelationId);
		return sqlSession.selectOne("exchange.select_Can_Wanted_Boolean", tmpMap);
	}

	@Override
	public Integer selectCanWantedCount(BigInteger userId, BigInteger exchangeRelationId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("exchangeRelationId", exchangeRelationId);
		return sqlSession.selectOne("exchange.select_Can_Wanted_Count", tmpMap);
	}
	
	@Override
	public String selectAllHotContentListLastUpdTime(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("exchange.select_All_HotContentList_LastUpdTime", tmpMap);
	}

	@Override
	public String selectAllLaunchAndJoinContentListLastUpdTime(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("exchange.select_All_LaunchAndJoinContentList_LastUpdTime", tmpMap);
	}

}
