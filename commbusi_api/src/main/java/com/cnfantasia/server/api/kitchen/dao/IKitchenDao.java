/**   
* Filename:    IKitchenDao.java   
* @version:    1.0  
* Create at:   2014年7月28日 上午10:14:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.kitchen.entity.DietAnalysisEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookTypeEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookMix.entity.KitchenCookbookMix;

/**
 * Filename:    IKitchenDao.java
 * @version:    1.0.0
 * Create at:   2014年7月28日 上午10:14:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月28日       shiyl             1.0             1.0 Version
 */
public interface IKitchenDao {
	/**
	 * 根据Id查询菜谱详情
	 * @param cookbookId
	 * @return
	 */
	public KitchenEntity selectKitchenCookbookDetailById(BigInteger cookbookId,BigInteger userId);

	/**
	 * @author wangzhe
	 * @date 2015年9月15日
	 * @description 批量根据id查询详情
	 *
	 * @param cookbookIds
	 * @param userId
	 * @param createTime TODO
	 * @return
	 */
	public List<KitchenCookbookCollectEntity> selectKitchenCookbookCollectListByIds(List<BigInteger> cookbookIds, BigInteger userId, String timeDay);

	/**
	 * @author wangzhe
	 * @date 2015年8月31日
	 * @description 查询多个cookbookid的点赞量
	 *
	 * @param cookCollectIds cookCollectIds
	 * @param userId userId
	 * @return 点赞量
	 */
	public int selectKitchenCookbookCollectIslikeCount(final List<BigInteger> cookCollectIds, final BigInteger userId);

    /**
     * @author wangzhe
     * @date 2015年8月31日
     * @description 查询用户当日对哪些菜谱收藏点了赞
     *
     * @param cookCollectIds cookCollectIds
     * @param userId userId
     * @return 当日点赞内容
     */
    public List<BigInteger> selectKitchenCookbookCollectIslike(final BigInteger userId);
	
	/**
	 * 查询推荐的菜谱类别列表
	 * @param cityId
	 * @param userId
	 * @return
	 */
	public List<KitchenCookbookTypeEntity> selectCookbookTypeRecommendList(BigInteger cityId,BigInteger userId);
	
	/**
	 * 查询今日推荐的n个菜
	 * @param ignoreCoolbookIds 希望忽略的菜谱Id
	 * @param count 期望推荐的菜的数量
	 * @param userId
	 * @return
	 */
	public List<KitchenEntity> selectCookbookRecommendByCount(BigInteger cityId,List<BigInteger> ignoreCoolbookIds,Integer count,BigInteger userId);
	
	/**
	 * 查询推荐的一个是菜谱
	 * @param cityId
	 * @return
	 */
	public KitchenEntity selectKitchenCookbookRecommend(BigInteger cityId,BigInteger userId,List<BigInteger> ignoreCoolbookIds);
	
	/**
	 * 查询灶神推荐的1个菜
	 * @param ignoreCoolbookIds
	 * @param userId
	 * @return
	 */
	public KitchenEntity selectGodRecommend(BigInteger cityId,List<BigInteger> ignoreCoolbookIds,BigInteger userId);
	
	/**
	 * 查询厨房菜谱类别列表
	 * @param cityId
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTypeEntity> selectCookbookTypeList(BigInteger cityId,PageModel pageModel,BigInteger userId);
	
	/**
	 * 查询厨房菜谱列表
	 * @param cityId TODO
	 * @param pageModel
	 * @param typeId
	 * @return
	 */
	public List<KitchenEntity> selectCookbookList(BigInteger cityId,List<Integer> cookbookTypeIds,String searchKey,PageModel pageModel, BigInteger userId);
	

    /**
     * 随机推荐厨房菜谱列表
     * @param cityId
     * @param count TODO
     * @param pageModel
     * @param typeId
     * @return
     */
    public List<KitchenEntity> selectCookbookListRandom(BigInteger cityId, List<Integer> cookbookTypeIds, BigInteger userId, Integer count);
	
	//2014-9-15 11:08:36 add
	
	/**
	 * 查询当天最近收藏的一个菜
	 * @param userId
	 * @return
	 */
	public KitchenCookbookCollectEntity selectLastCollectCookbookCurrDay(BigInteger userId);
	
	/**
	 * 查询用户收藏的菜谱类别列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTypeEntity> selectCollectCookbookTypeList(BigInteger cityId,BigInteger userId);
	
	/**
	 * 查询用户本周的菜单列表
	 * @param userId
	 * @return
	 */
	public List<KitchenCookbookCollectEntity> selectCollectCookbookListCurrWeek(BigInteger userId);
	
	/**
	 * 分页查询用户有收藏菜品的日期列表，不包含本周
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<String> selectExistCollectCookbookDayListHistory(BigInteger userId,PageModel pageModel);
	
	/**
	 * 查询用户历史的菜单列表，不包含本周
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookCollectEntity> selectCollectCookbookListHistory(BigInteger userId,List<String> dayTimeList);
	
	/**
	 * 查询用户某天对某个菜谱收藏的次数
	 * @param userId
	 * @param cookBookId
	 * @param timeDay YYYY-MM-DD
	 * @return
	 */
	public Integer selectCollectCookbookCountByDay(BigInteger userId,BigInteger cookBookId,String timeDay);

    /**
     * 查询用户某天对某些菜谱收藏的次数
     * @param userId
     * @param cookBookId
     * @param timeDay YYYY-MM-DD
     * @return
     */
    public Integer selectCollectMultipleCookbookCountByDay(BigInteger userId,List<BigInteger> cookBookList,String timeDay);
	
	/**
	 * 用户取消某天指定的菜谱收藏
	 * @param userId
	 * @param cookBookId
	 * @param timeDay YYYY-MM-DD
	 * @return
	 */
	public Integer cancelCollectCookbookByDay(BigInteger userId,BigInteger cookBookId,String timeDay);
	

    /**
     * @author wangzhe
     * @date 2015年9月11日
     * @description 批量取消某天指定的菜谱收藏
     *
     * @param userIds
     * @param cookBookIds
     * @param timeDay
     * @return
     */
    public Integer cancelMultiCollectCookbookByDay(List<BigInteger> userIds, List<BigInteger> cookBookIds, String timeDay);
	
	/**
	 * 批量取消收藏菜谱类别
	 * @param userId
	 * @param cookBookTypeIds
	 * @return
	 */
	public Integer cancelCollectCookbookTypeBatch(BigInteger userId,List<BigInteger> cookBookTypeIds);
	
	/**
	 * 根据菜谱Id查询对应的组合列表
	 * @param cookBookId
	 * @return
	 */
	public List<KitchenCookbookMix> selectKitchenCookbookMixByCookbookId(BigInteger cookbookId);
	
	/**
	 * 根据组合菜Id查询对应包含的组合菜
	 * @param cookbookMixId
	 * @return
	 */
	public List<KitchenEntity> selectCookbookListByMixId(BigInteger cookbookMixId);
	
	/**
	 * 查询所有厨房菜谱最新更新时间
	 * @return
	 */
	public String selectAllKitchenLastUpdTime(List<Integer> typeId,PageModel pageModel,BigInteger userId);
	
	/**
	 * 查询收藏菜谱详情
	 * @param cookCollectId
	 * @param userId
	 * @return
	 */
	public KitchenCookbookCollectEntity selectKitchenCookbookCollectDetailById(BigInteger cookCollectId,BigInteger userId);
	
	/**
	 * 查询用户今日收藏的菜谱列表
	 * @param cookCollectId
	 * @param userId
	 * @return
	 */
	public List<KitchenCookbookCollectEntity> selectKitchenCookbookCollectListToday(BigInteger userId);

    /**
     * @author wangzhe
     * @date 2015年11月5日
     * @description 查询饮食分析
     *
     * @param userId
     * @return
     */
    public DietAnalysisEntity qryDietAnalysis(BigInteger userId);

}
