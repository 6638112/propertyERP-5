/**   
* Filename:    CollectionsDao.java   
* @version:    1.0  
* Create at:   2014年8月27日 上午3:47:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.collections.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.user.entity.CollectionsEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;

/**
 * Filename:    CollectionsDao.java
 * @version:    1.0.0
 * Create at:   2014年8月27日 上午3:47:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月27日       shiyl             1.0             1.0 Version
 */
public class CollectionsDao extends AbstractBaseDao implements ICollectionsDao{
//	@Override
//	public List<CollectionsEntity> selectCollectionsList(BigInteger userId,Integer type, PageModel pageModel) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("targetType", type);
//		tmpMap.put("userId", userId);
//		List<CollectionsEntity> resList = null;
//		String pageSqlKey = "collections.select_collectionList_byCondition_Page";
//		String countSqlKey = "collections.select_collectionList_byCondition_Count";
//		resList = PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
//		return resList;
//	}

	@Override
	public List<CollectionsEntity> selectCollectionsCommunitySupplyList(BigInteger userId, PageModel pageModel) {
		Integer type = CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_SUPPLY;
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("targetType", type);
		tmpMap.put("userId", userId);
		List<CollectionsEntity> resList = null;
		String pageSqlKey = "collections.select_collectionList_communitySupply_byCondition_Page";
		String countSqlKey = "collections.select_collectionList_communitySupply_byCondition_Count";
		resList = PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
		return resList;
	}
}
