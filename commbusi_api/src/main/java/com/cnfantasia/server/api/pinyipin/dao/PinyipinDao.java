/**   
* Filename:    PinyipinDao.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:25:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pinyipin.entity.PinyipinContentEntity;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinReserveEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;

/**
 * Filename:    PinyipinDao.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:25:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public class PinyipinDao extends AbstractBaseDao implements IPinyipinDao{

	@Override
	public PinyipinContentEntity selectMostHotContent(BigInteger userId, BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		return sqlSession.selectOne("pinyipin.select_MostHot_Content", tmpMap);
	}

	@Override
	public List<PinyipinContentEntity> selectHotContentList(BigInteger userId, BigInteger groupBuildingId,PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "pinyipin.select_HotContent_List_Page";
		String countSqlKey = "pinyipin.select_HotContent_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<PinyipinContentEntity> selectLaunchContentList(BigInteger userId, BigInteger groupBuildingId,
			PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "pinyipin.select_LaunchContent_List_Page";
		String countSqlKey = "pinyipin.select_LaunchContent_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<PinyipinContentEntity> selectJoinContentList(BigInteger userId, BigInteger groupBuildingId,
			PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "pinyipin.select_JoinContent_List_Page";
		String countSqlKey = "pinyipin.select_JoinContent_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<PinyipinContentEntity> selectLaunchAndJoinContentList(BigInteger userId, BigInteger groupBuildingId,
			PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "pinyipin.select_LaunchAndJoin_Content_List_Page";
		String countSqlKey = "pinyipin.select_LaunchAndJoin_Content_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public Integer selectJoinTotalCount(BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("contentId", contentId);
		return sqlSession.selectOne("pinyipin.select_Join_TotalCount", tmpMap);
	}

	@Override
	public Integer deleteLaunchContent(BigInteger userId, BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("contentId", contentId);
		return sqlSession.delete("pinyipin.delete_LaunchContent", tmpMap);
	}

	@Override
	public List<CommunityPinyipinReserve> selectPinyipinReserveById(BigInteger joinUserId, BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", joinUserId);
		tmpMap.put("contentId", contentId);
		return sqlSession.selectList("pinyipin.select_PinyipinReserve_ById", tmpMap);
	}

	@Override
	public List<PinyipinReserveEntity> selectLaunchPinyipinReserveList(BigInteger userId, BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("contentId", contentId);
//		String pageSqlKey = "pinyipin.select_Launch_PinyipinReserve_List_Page";
//		String countSqlKey = "pinyipin.select_Launch_PinyipinReserve_List_Count";
//		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
		return sqlSession.selectList("pinyipin.select_Launch_PinyipinReserve_List", tmpMap);
	}
	@Override
	public List<UserSimpleEntity> selectLaunchPinyipinReserveUserListByTime(BigInteger userId, BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("contentId", contentId);
		return sqlSession.selectList("pinyipin.select_Launch_PinyipinReserve_UserList_ByTime", tmpMap);
	}
	
	@Override
	public Integer updReserveContentByTotalCount(BigInteger currUserId,BigInteger pinyipinReverseId, Integer totalCount) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", currUserId);
		tmpMap.put("pinyipinReverseId", pinyipinReverseId);
		tmpMap.put("totalCount", totalCount);
		return sqlSession.update("pinyipin.upd_ReserveContent_ByTotalCount", tmpMap);
	}

	@Override
	public Integer updReserveContentByChangeCount(BigInteger currUserId,BigInteger pinyipinReverseId, Integer changeCount) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", currUserId);
		tmpMap.put("pinyipinReverseId", pinyipinReverseId);
		tmpMap.put("changeCount", changeCount);
		return sqlSession.update("pinyipin.upd_ReserveContent_ByChangeCount", tmpMap);
	}

	@Override
	public Integer updateClaimStatus(BigInteger currUserId,BigInteger pinyipinReverseId,Integer calimStatus) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", currUserId);
		tmpMap.put("pinyipinReverseId", pinyipinReverseId);
		tmpMap.put("calimStatus", calimStatus);
		return sqlSession.update("pinyipin.update_Claim_Status", tmpMap);
	}
	
	@Override
	public Integer updatePinyipinContent2Delivery(BigInteger userId, BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("contentId", contentId);
		return sqlSession.update("pinyipin.update_PinyipinContent2Delivery", tmpMap);
	}
	
	@Override
	public PinyipinContentEntity selectPinyipinContentDetail(BigInteger userId, BigInteger contentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("contentId", contentId);
		return sqlSession.selectOne("pinyipin.select_PinyipinContent_Detail", tmpMap);
	}

	@Override
	public Integer updatePinyipinContentTimeOut() {
		return sqlSession.update("pinyipin.update_PinyipinContent_TimeOut");
	}

	@Override
	public String selectAllHotContentListLastUpdTime(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("pinyipin.select_All_HotContentList_LastUpdTime", tmpMap);
	}

	@Override
	public String selectAllLaunchAndJoinContentListLastUpdTime(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("pinyipin.select_All_LaunchAndJoinContentList_LastUpdTime", tmpMap);
	}

}
