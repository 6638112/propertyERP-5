/**   
* Filename:    CommunitySupplyDao.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午7:31:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.communitySupply.entity.CommunityPhoneTypeEntity;
import com.cnfantasia.server.api.communitySupply.entity.CommunitySupplyTypeEntity;
import com.cnfantasia.server.api.communitySupply.entity.GroupBuildingHasTCommunitySupply_Supply;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;

/**
 * Filename:    CommunitySupplyDao.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午7:31:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyDao extends AbstractBaseDao implements ICommunitySupplyDao{
	
	@Override
	public void cleanCache() {
		sqlSession.selectOne("communitySupply.cleanCache");
	}
	
	@Override
	public GroupBuildingHasTCommunitySupply_Supply selectCommunitySupplyDetail(BigInteger communitySupplyId,BigInteger userId,BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("communitySupplyId", communitySupplyId);
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		return sqlSession.selectOne("communitySupply.select_CommunitySupply_Detail", tmpMap);
	}

	@Override
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeList(BigInteger parentTypeId, Integer importanceLevel,
			PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("parentTypeId", parentTypeId);
		tmpMap.put("importanceLevel", importanceLevel);
		String pageSqlKey = "communitySupply.select_CommunitySupply_Type_List_Page";
		String countSqlKey = "communitySupply.select_CommunitySupply_Type_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<GroupBuildingHasTCommunitySupply_Supply> searchCommunitySupplyList(BigInteger supplyTypeId, String supplyNameKey,
			PageModel pageModel,BigInteger userId,BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("supplyTypeId", supplyTypeId);
		tmpMap.put("searchKey", supplyNameKey);
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildingId", groupBuildingId);
		String pageSqlKey = "communitySupply.search_CommunitySupply_List_Page";
		String countSqlKey = "communitySupply.search_CommunitySupply_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<CommentsLabel> selectCommentsLabelListBySupplyType(BigInteger supplyTypeId) {
		return sqlSession.selectList("communitySupply.select_CommentsLabel_List_BySupplyType", supplyTypeId);
	}
	
	@Override
	public List<CommentsPoints> selectCommentsPointsListBySupplyType(BigInteger supplyTypeId) {
		return sqlSession.selectList("communitySupply.select_CommentsPoints_List_BySupplyType", supplyTypeId);
	}

	@Override
	public Integer updateSupplyContectCallCount(BigInteger contectId) {
		return sqlSession.update("communitySupply.update_Supply_Contect_CallCount", contectId);
	}

	@Override
	public Long selectSupplyContectCallCount(BigInteger contectId) {
		return sqlSession.selectOne("communitySupply.select_Supply_Contect_CallCount",contectId);
	}

	@Override
	public Long selectSupplyContectTotalCallCountBySupplyId(BigInteger communitySupplyId) {
		return sqlSession.selectOne("communitySupply.select_SupplyContect_TotalCallCount_BySupplyId",communitySupplyId);
	}

	@Override
	public Long selectSupplyContectTotalCallCountByContectId(BigInteger contectId) {
		return sqlSession.selectOne("communitySupply.select_SupplyContect_TotalCallCount_ByContectId",contectId);
	}

	@Override
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeRecommend() {
		return sqlSession.selectList("communitySupply.select_CommunitySupply_Type_Recommend");
	}

	@Override
	public CommunitySupplyTypeEntity selectCommunitySupplyTypeBySupplyTypeId(BigInteger suppluTypeId) {
		return sqlSession.selectOne("communitySupply.select_CommunitySupply_Type_By_SupplyTypeId",suppluTypeId);
	}

	@Override
	public List<CommunitySupplyContect> selectCommunitySupplyContect(BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildingId", groupBuildingId);
		return sqlSession.selectList("communitySupply.select_CommunitySupply_Contect_All",tmpMap);
	}

	@Override
	public List<CommunitySupply> selectCommunitySupply(BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildingId", groupBuildingId);
		return sqlSession.selectList("communitySupply.select_CommunitySupply_All",tmpMap);
	}

//	@Override
//	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeAll() {
//		return sqlSession.selectList("communitySupply.select_CommunitySupplyType_All");
//	}

	@Override
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeLevel01() {
		return sqlSession.selectList("communitySupply.select_CommunitySupplyType_Level01");
	}
	@Override
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeLevel02() {
		return sqlSession.selectList("communitySupply.select_CommunitySupplyType_Level02");
	}

	@Override
	public List<CommunityPhoneTypeEntity> getCommunityPhoneTypeList(Integer version, BigInteger gbId) {
		Map<String, Object> param = new HashMap<>();
		param.put("version", version);
		param.put("gbId", gbId);//避免因gbId不同引起的mybatis缓存不对
		return sqlSession.selectList("communitySupply.getCommunityPhoneTypeList", param);
	}

}
