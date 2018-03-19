/**   
* Filename:    CommunityLocalDao.java   
* @version:    1.0  
* Create at:   2014年7月13日 下午12:00:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.communityLocal.entity.CommunityForumContentEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;
import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;


/**
 * Filename:    CommunityLocalDao.java
 * @version:    1.0.0
 * Create at:   2014年7月13日 下午12:00:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月13日       shiyl             1.0             1.0 Version
 */
public class CommunityLocalDao extends AbstractBaseDao implements ICommunityLocalDao{

	@Override
	public List<CommunityForumContentEntity> selectForumList(BigInteger forumType, BigInteger groupBuildId,
			PageModel pageModel,BigInteger userId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("forumType", forumType);
		qryMap.put("groupBuildId", groupBuildId);
		qryMap.put("userId", userId);
		String pageSqlKey="communityLocal.select_forum_list_page";
		String countSqlKey="communityLocal.select_forum_list_count";
		return PageQueryUtil.selectPageList(sqlSession, qryMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public CommunityForumContentEntity selectForumDetail(BigInteger forumId,BigInteger userId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("forumId", forumId);
		qryMap.put("userId", userId);
		return sqlSession.selectOne("communityLocal.select_forum_detail", qryMap);
	}

	@Override
	public CommunityForumContent selectMostHotForum(BigInteger forumType,BigInteger groupBuildId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("forumType", forumType);
		qryMap.put("groupBuildId", groupBuildId);
		return sqlSession.selectOne("communityLocal.select_mostHot_forum", qryMap);
	}
	
	@Override
	public List<CommunityForumType> selectCommunityForumTypeList(Integer type, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("modelType", type);
		String pageSqlKey = "communityLocal.select_CommunityForum_TypeList_page";
		String countSqlKey = "communityLocal.select_CommunityForum_TypeList_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<CommunityForumType> selectCommunityForumTypeNot8List(PageModel pageModel) {
		String pageSqlKey = "communityLocal.select_CommunityForum_Type_Not8_List_page";
		String countSqlKey = "communityLocal.select_CommunityForum_Type_Not8_List_count";
		return PageQueryUtil.selectPageList(sqlSession, null, pageModel, pageSqlKey, countSqlKey);
	}
	
}

