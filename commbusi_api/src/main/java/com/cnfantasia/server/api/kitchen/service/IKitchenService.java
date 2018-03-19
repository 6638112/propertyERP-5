/**   
* Filename:    IKitchenService.java   
* @version:    1.0  
* Create at:   2014年7月28日 上午9:59:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.kitchen.entity.DietAnalysisEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookTypeEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    IKitchenService.java
 * @version:    1.0.0
 * Create at:   2014年7月28日 上午9:59:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月28日       shiyl             1.0             1.0 Version
 */
public interface IKitchenService {
	/**
	 * 查询菜谱详情
	 * @param cookbookId
	 * @return
	 */
	public KitchenEntity getCookbookDetail(BigInteger cookbookId,BigInteger userId);
	/**
	 * 查询某个菜谱可搭配的菜
	 * @param cookbookId
	 * @return
	 */
	public List<KitchenEntity> getMixCookbookListByCookbookId(BigInteger cityId,BigInteger cookbookId,BigInteger userId);
	public List<KitchenEntity> getMixCookbookListByCookbookId(String cityName,BigInteger cookbookId,BigInteger userId);
	
	/**
	 * 查询一个推荐的菜谱
	 * @return
	 */
//	public KitchenEntity getRecommend(BigInteger cityId,BigInteger userId);
	public KitchenEntity getRecommend(String cityName,BigInteger userId,List<BigInteger> ignoreCoolbookIds);
	/**
	 * 查询菜谱类别列表
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTypeEntity> getCookbookTypeList(BigInteger cityId,PageModel pageModel,BigInteger userId);
	public List<KitchenCookbookTypeEntity> getCookbookTypeList(String cityName,PageModel pageModel,BigInteger userId);
	
	/**
	 * 查询菜谱列表
	 * @param cityName TODO
	 * @param typeId
	 * @param pageModel
	 * @return
	 */
	public List<KitchenEntity> getCookbookList(String cityName,List<Integer> cookbookTypeIds,String searchKey,PageModel pageModel, BigInteger userId);

    
    /**
     * 随机推荐菜谱列表
     * @param cityName cityname
     * @param count TODO
     * @param typeId
     * @param pageModel
     * @return
     */
    public List<KitchenEntity> getCookbookListRandom(String cityName,List<Integer> cookbookTypeIds, BigInteger userId, Integer count);
	
	/**
	 * 查询今日推荐的n个菜
	 * @param ignoreCoolbookIds 希望忽略的菜谱Id
	 * @param count 期望推荐的菜的数量
	 * @param userId
	 * @return
	 */
	public List<KitchenEntity> getCookbookRecommendByCount(BigInteger cityId,List<BigInteger> ignoreCoolbookIds,Integer count,BigInteger userId);
	public List<KitchenEntity> getCookbookRecommendByCount(String cityName,List<BigInteger> ignoreCoolbookIds,Integer count,BigInteger userId);
	
	/**
	 * 查询灶神推荐的1个菜
	 * @param ignoreCoolbookIds
	 * @param userId
	 * @return
	 */
	public KitchenEntity getGodRecommend(BigInteger cityId,List<BigInteger> ignoreCoolbookIds,BigInteger userId);
	public KitchenEntity getGodRecommend(String cityName,List<BigInteger> ignoreCoolbookIds,BigInteger userId);
	
	/**
	 * 查询当天最近收藏的一个菜
	 * @param userId
	 * @return
	 */
	public KitchenCookbookCollectEntity getLastCollectCookbookCurrDay(BigInteger userId);
//	/**
//	 * 查询默认的菜谱收藏信息
//	 * @return
//	 */
//	public KitchenEntity getDefaultCollectCookbookCurrDay(BigInteger userId);
	
	/**
	 * 查询用户收藏的菜谱类别列表
	 * @param userId
	 * @return
	 */
	public List<KitchenCookbookTypeEntity> getCollectCookbookTypeList(BigInteger cityId,BigInteger userId);
	public List<KitchenCookbookTypeEntity> getCollectCookbookTypeList(String cityName,BigInteger userId);
	/**
	 * 查询默认的收藏的菜谱类别列表
	 * @return
	 */
	public List<KitchenCookbookTypeEntity> getDefaultCollectCookbookTypeList(BigInteger cityId,BigInteger userId);
	public List<KitchenCookbookTypeEntity> getDefaultCollectCookbookTypeList(String cityName,BigInteger userId);
	
	/**
	 * 将指定的多个菜谱类别设置为当前收藏
	 * @param collectTypeIds 待收藏的类别Id集合
	 * @param userId
	 * @return 返回用户收藏的菜谱类别列表
	 */
	public List<KitchenCookbookTypeEntity> submitAllCollectCookbookTypes(BigInteger cityId,List<BigInteger> collectTypeIds,BigInteger userId);
	public List<KitchenCookbookTypeEntity> submitAllCollectCookbookTypes(String cityName,List<BigInteger> collectTypeIds,BigInteger userId);
	
	/**
	 * 用户添加收藏某个菜谱类别
	 * @param cookbookTypeId
	 * @param userId
	 */
	public void doCollectCookbookType(BigInteger cookbookTypeId,BigInteger userId);
	
	/**
	 * 用户取消收藏某个菜谱类别
	 * @param cookbookTypeId
	 * @param userId
	 */
	public void cancelCollectCookbookType(BigInteger cookbookTypeId,BigInteger userId);
	
	/**
	 * 查询用户本周的菜单列表
	 * @param userId
	 * @return
	 */
	public List<KitchenCookbookCollectEntity> getCollectCookbookListCurrWeek(BigInteger userId);
	
	/**
	 * 查询用户历史的菜单列表，不包含本周
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookCollectEntity> getCollectCookbookListHistory(BigInteger userId,PageModel pageModel);
	
	/**
	 * 用户添加收藏某个菜谱
	 * @param cookbookId
	 * @param userId
     * @deprecated useless after ver.310, Use {@link #doCollectTodayCookbook(List<BigInteger> cookbookId, BigInteger userId)} instead 
	 */
    @Deprecated
	public void doCollectCookbook(BigInteger cookbookId,BigInteger userId);

    /**
     * 用户添加收藏某些菜谱
     *
     * @param cookbookId
     * @param comment TODO
     * @param atUserIdSet TODO
     * @param userId
     */
    public void doCollectTodayCookbook(List<BigInteger> cookbookId, String comment, Set<BigInteger> atUserIdSet, BigInteger userId);
    
    
	/**
	 * 用户取消收藏某个菜谱
	 * @param cookbookId
	 * @param userId
	 */
	public void cancelCollectCookbook(BigInteger cookbookId,BigInteger userId);
	

    /**
     * @author wangzhe
     * @date 2015年9月11日
     * @description 用户批量取消收藏菜谱
     *
     * @param cbList
     * @param userId
     */
    public void cancelMultiCollectCookbook(List<BigInteger> cookBookIds, BigInteger userId);
	
	/**
	 * 查询所有厨房菜谱最新更新时间
	 * @return
	 */
	public String fetchAllKitchenLastUpdTime(List<Integer> typeId,String searchKey,PageModel pageModel,BigInteger userId);
	
	/**
	 * 根据用户信息获取城市Id
	 * @param cityName
	 * @param userId
	 * @return
	 */
	public BigInteger getCityIdByInfo(String cityName, BigInteger userId);
	
	/**
	 * 家庭用户点击喜欢吃某个菜
	 * @param cookCollectId
	 * @param userId
	 */
	public void doLikeEatCookbookCollect(BigInteger cookCollectId,BigInteger userId);

    /**
     * 家庭用户点击喜欢吃某些菜
     *
     * @param cookCollectIds
     * @param userId
     */
    public void doLikeEatMultiCookbookCollect(List<BigInteger> cookCollectIds, BigInteger userId);
	
	/**
	 * 查询收藏菜谱详情
	 * @param cookCollectId
	 * @param userId
	 * @return
	 */
	public KitchenCookbookCollectEntity qryKitchenCookbookCollectDetailById(BigInteger cookCollectId,BigInteger userId);
	
	/**
	 * 查询用户今日收藏的菜谱列表
	 * @param cookCollectId
	 * @param userId
	 * @return
	 */
	public List<KitchenCookbookCollectEntity> qryKitchenCookbookCollectListToday(BigInteger userId);
	
	
    /**
     * @author wangzhe
     * @date 2015年11月5日
     * @description 查询用户饮食情况
     *
     * @param userId
     */
    public DietAnalysisEntity qryDietAnalysis(BigInteger userId);
	
}
