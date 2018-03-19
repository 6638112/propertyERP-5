/**   
* Filename:    KitchenDao.java   
* @version:    1.0  
* Create at:   2014年7月28日 上午10:21:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.kitchen.entity.DietAnalysisEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookTypeEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.kitchenCookbookMix.entity.KitchenCookbookMix;

/**
 * Filename: KitchenDao.java
 * 
 * @version: 1.0.0 Create at: 2014年7月28日 上午10:21:46 Description:
 *
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年7月28日 shiyl 1.0 1.0 Version
 */
public class KitchenDao extends AbstractBaseDao implements IKitchenDao {

    private Map<String, Object> generateParamMap(BigInteger userId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userId", userId);
        return tmpMap;
    }

    private Map<String, Object> generateParamMap(BigInteger cityId, BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("cityId", cityId);
        return tmpMap;
    }

    @Override
    public KitchenEntity selectKitchenCookbookDetailById(BigInteger cookbookId, BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("cookbookId", cookbookId);
        return sqlSession.selectOne("kitchen.select_KitchenCookbook_Detail_ById", tmpMap);
    }

    @Override
    public List<KitchenCookbookCollectEntity>  selectKitchenCookbookCollectListByIds(List<BigInteger> cookbookIds, BigInteger userId, String timeDay) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("cookbookIds", cookbookIds);
        tmpMap.put("timeDay", timeDay);
        tmpMap.put("ignore_kcc_delete", true);
        return sqlSession.selectList("kitchen.select_KitchenCookbookCollect_List_ByIds", tmpMap);
    }

    @Override
    public List<KitchenCookbookTypeEntity> selectCookbookTypeRecommendList(BigInteger cityId, BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(cityId, userId);
        return sqlSession.selectList("kitchen.select_CookbookType_Recommend_List", tmpMap);
    }

    @Override
    public List<KitchenEntity> selectCookbookRecommendByCount(BigInteger cityId, List<BigInteger> ignoreCoolbookIds, Integer count, BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(cityId, userId);
        tmpMap.put("ignoreCoolbookIds", ignoreCoolbookIds);
        tmpMap.put("count", count);
        return sqlSession.selectList("kitchen.select_Cookbook_Recommend_ByCount", tmpMap);
    }

    @Override
    public KitchenEntity selectKitchenCookbookRecommend(BigInteger cityId, BigInteger userId, List<BigInteger> ignoreCoolbookIds) {
        List<KitchenEntity> resList = selectCookbookRecommendByCount(cityId, ignoreCoolbookIds, 1, userId);
        if (resList != null && resList.size() > 0) {
            return resList.get(0);
        }
        return null;
        // Map<String,Object> tmpMap = new HashMap<String, Object>();
        // tmpMap.put("cityId", cityId);
        // tmpMap.put("userId", userId);
        // return
        // sqlSession.selectOne("kitchen.select_KitchenCookbook_Recommend",
        // tmpMap);
    }

    @Override
    public KitchenEntity selectGodRecommend(BigInteger cityId, List<BigInteger> ignoreCoolbookIds, BigInteger userId) {
        // 灶神推荐暂时使用系统随机推荐的方式
        List<KitchenEntity> tmpList = selectCookbookRecommendByCount(cityId, ignoreCoolbookIds, 1, userId);
        if (tmpList != null && tmpList.size() > 0) {
            return tmpList.get(0);
        }
        return null;
    }

    @Override
    public List<KitchenCookbookTypeEntity> selectCookbookTypeList(BigInteger cityId, PageModel pageModel, BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(cityId, userId);
        String pageSqlKey = "kitchen.select_Cookbook_Type_List_page";
        String countSqlKey = "kitchen.select_Cookbook_Type_List_count";
        return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
    }

    @Override
    public List<KitchenEntity> selectCookbookList(BigInteger cityId, List<Integer> cookbookTypeIds, String searchKey, PageModel pageModel, BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(cityId, userId);
        tmpMap.put("cookBookTypeIds", cookbookTypeIds);
        tmpMap.put("searchKey", searchKey);
        String pageSqlKey = "kitchen.select_Cookbook_List_page";
        String countSqlKey = "kitchen.select_Cookbook_List_count";
        return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
    }

    public List<KitchenEntity> selectCookbookListRandom(BigInteger cityId, List<Integer> cookbookTypeIds, BigInteger userId, Integer count){
        Map<String, Object> tmpMap = generateParamMap(cityId, userId);
        tmpMap.put("cookBookTypeIds", cookbookTypeIds);
        tmpMap.put("count", count);
        return sqlSession.selectList("kitchen.select_Cookbook_List_random", tmpMap);
    }

    @Override
    public KitchenCookbookCollectEntity selectLastCollectCookbookCurrDay(BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        return sqlSession.selectOne("kitchen.select_LastCollect_Cookbook_CurrDay", tmpMap);
    }

    // @Override
    // public List<KitchenCookbookTypeEntity>
    // selectCollectCookbookTypeList(BigInteger userId, PageModel pageModel) {
    // Map<String,Object> tmpMap = new HashMap<String, Object>();
    // tmpMap.put("userId", userId);
    // String pageSqlKey = "kitchen.select_Collect_CookbookType_List_page";
    // String countSqlKey = "kitchen.select_Collect_CookbookType_List_count";
    // return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel,
    // pageSqlKey, countSqlKey);
    // }
    @Override
    public List<KitchenCookbookTypeEntity> selectCollectCookbookTypeList(BigInteger cityId, BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(cityId, userId);
        String pageSqlKey = "kitchen.select_Collect_CookbookType_List_page";
        return sqlSession.selectList(pageSqlKey, tmpMap);
    }

    @Override
    public List<KitchenCookbookCollectEntity> selectCollectCookbookListCurrWeek(BigInteger userId) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        return sqlSession.selectList("kitchen.select_Collect_Cookbook_List_CurrWeek", tmpMap);
    }

    @Override
    public List<String> selectExistCollectCookbookDayListHistory(BigInteger userId, PageModel pageModel) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        String pageSqlKey = "kitchen.select_Exist_CollectCookbook_Day_List_History_page";
        String countSqlKey = "kitchen.select_Exist_CollectCookbook_Day_List_History_count";
        return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
    }

    @Override
    public List<KitchenCookbookCollectEntity> selectCollectCookbookListHistory(BigInteger userId, List<String> dayTimeList) {
        if (dayTimeList == null || dayTimeList.size() <= 0) {
            return new ArrayList<KitchenCookbookCollectEntity>();
        }
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("dayTimeList", dayTimeList);
        return sqlSession.selectList("kitchen.select_Collect_Cookbook_List_History", tmpMap);
    }

    @Override
    public Integer selectCollectCookbookCountByDay(BigInteger userId, BigInteger cookBookId, String timeDay) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("cookBookId", cookBookId);
        tmpMap.put("timeDay", timeDay);
        return sqlSession.selectOne("kitchen.select_Collect_Cookbook_Count_ByDay", tmpMap);
    }

    @Override
    public Integer selectCollectMultipleCookbookCountByDay(final BigInteger userId, final List<BigInteger> cookBookList, final String timeDay) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("cookBookIds", cookBookList);
        tmpMap.put("timeDay", timeDay);
        return sqlSession.selectOne("kitchen.select_Collect_Multi_Cookbook_Count_ByDay", tmpMap);
    }

    @Override
    public Integer cancelCollectCookbookByDay(BigInteger userId, BigInteger cookBookId, String timeDay) {
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("cookBookId", cookBookId);
        tmpMap.put("timeDay", timeDay);
        return sqlSession.update("kitchen.cancel_Collect_Cookbook_ByDay", tmpMap);
    }

    @Override
    public Integer cancelMultiCollectCookbookByDay(List<BigInteger> userIds, List<BigInteger> cookBookIds, String timeDay) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userIds", userIds);
        tmpMap.put("cookBookIds", cookBookIds);
        tmpMap.put("timeDay", timeDay);
        return sqlSession.update("kitchen.cancel_Multi_Collect_Cookbook_ByDay", tmpMap);
    }

    @Override
    public Integer cancelCollectCookbookTypeBatch(BigInteger userId, List<BigInteger> cookBookTypeIds) {
        if (cookBookTypeIds == null || cookBookTypeIds.size() <= 0) {
            return 0;
        }
        Map<String, Object> tmpMap = generateParamMap(userId);
        tmpMap.put("cookBookTypeIds", cookBookTypeIds);
        return sqlSession.update("kitchen.cancel_Collect_CookbookType_Batch", tmpMap);
    }

    @Override
    public List<KitchenCookbookMix> selectKitchenCookbookMixByCookbookId(BigInteger cookbookId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("cookbookId", cookbookId);
        return sqlSession.selectList("kitchen.select_Kitchen_CookbookMix_ByCookbookId", tmpMap);
    }

    @Override
    public List<KitchenEntity> selectCookbookListByMixId(BigInteger cookbookMixId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("cookbookMixId", cookbookMixId);
        return sqlSession.selectList("kitchen.select_Cookbook_List_ByMixId", tmpMap);
    }

    @Override
    public String selectAllKitchenLastUpdTime(List<Integer> cookbookTypeIds, PageModel pageModel, BigInteger userId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("cookbookTypeIds", cookbookTypeIds);
        // tmpMap.put("userId", userId);
        // tmpMap.put("_begin", pageModel.begin);
        // tmpMap.put("_length", pageModel.length);
        return sqlSession.selectOne("kitchen.select_All_Kitchen_LastUpdTime", tmpMap);
    }

    @Override
    public KitchenCookbookCollectEntity selectKitchenCookbookCollectDetailById(BigInteger cookCollectId, BigInteger userId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("cookCollectId", cookCollectId);
        tmpMap.put("userId", userId);
        return sqlSession.selectOne("kitchen.select_KitchenCookbookCollect_Detail_ById", tmpMap);
    }

    @Override
    public List<KitchenCookbookCollectEntity> selectKitchenCookbookCollectListToday(BigInteger userId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userId", userId);
        return sqlSession.selectList("kitchen.select_KitchenCookbookCollect_List_Today", tmpMap);
    }

    @Override
    public int selectKitchenCookbookCollectIslikeCount(List<BigInteger> cookCollectIds, BigInteger userId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userId", userId);
        tmpMap.put("cookIds", cookCollectIds);
        return sqlSession.selectOne("kitchen.select_Count_KitchenCookbookCollect_IsLike_Today", tmpMap);
    }

    public List<BigInteger> selectKitchenCookbookCollectIslike(final BigInteger userId){
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userId", userId);
        return sqlSession.selectList("kitchen.select_KitchenCookbookCollectId_IsLike_Today", tmpMap);
    }

    @Override
    public DietAnalysisEntity qryDietAnalysis(BigInteger userId) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userId", userId);
        return sqlSession.selectOne("kitchen.select_DietAnalysis", tmpMap);
    }


    // @Override
    // public List<KitchenEntity> selectCollectCookbookListHistory(BigInteger
    // userId,PageModel pageModel) {
    // Map<String,Object> tmpMap = new HashMap<String, Object>();
    // tmpMap.put("userId", userId);
    // String pageSqlKey = "kitchen.select_Collect_Cookbook_List_History_page";
    // String countSqlKey =
    // "kitchen.select_Collect_Cookbook_List_History_count";
    // return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel,
    // pageSqlKey, countSqlKey);
    // }

}
