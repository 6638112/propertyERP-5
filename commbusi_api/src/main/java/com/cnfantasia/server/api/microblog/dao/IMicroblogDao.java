/**   
* Filename:    IMicroblogDao.java   
* @version:    1.0  
* Create at:   2014年7月22日 上午10:18:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.microblog.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.microblog.entity.MicroblogContentEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoGroupBuilding;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;

/**
 * Filename:    IMicroblogDao.java
 * @version:    1.0.0
 * Create at:   2014年7月22日 上午10:18:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月22日       shiyl             1.0             1.0 Version
 */
public interface IMicroblogDao {
	/**
	 * 查询微博列表
	 * @param microBlogType
	 * @param groupBuildId
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogContentEntity> selectMicroblogList(BigInteger microBlogType,BigInteger groupBuildId,PageModel pageModel,BigInteger userId);
	
	/**
	 * 查询微博详情
	 * @param microBolgId
	 * @return
	 */
	public MicroblogContentEntity selectMicroblogDetail(BigInteger microBolgId,BigInteger userId);
	
	/**
	 * 查询最热门的微博
	 */
	public MicroblogContent selectMostHotMicBlog(BigInteger microBlogType,BigInteger groupBuildId) ;
	
	
	/**
	 * 查询所有微博记录的最新更新时间
	 * @return
	 */
	public String selectAllMicroblogLastUpdTime(BigInteger microBlogType,BigInteger groupBuildId,PageModel pageModel,BigInteger userId);

	/**
	 * 查询用户所在小区已经发布过微博的用户数量
	 * @param groupBuildId
	 * @return
	 */
	public Integer selectHaveSendBlogUserCount(BigInteger groupBuildId);
	
	/**
	 * 查询用户当天是否已经发过微博
	 * @param userId
	 * @param groupBuildId
	 * @return
	 */
	public Integer selectUserSendBlogForToday(BigInteger userId, BigInteger groupbuildingId);

	/**
	 * 查看微博链接详情数据
	 * @param microBolgId
	 * @return
	 */
	public String selectMicroblogLinkDetail(BigInteger microBolgId);
	
	public List<XibaoEntity> selectXibaoList(Map<String,Object> paramMap);
	
	public XibaoGroupBuilding selectXibaoGroupBuilding(Map<String,Object> paramMap);

	public int getSelectXibaoListCount(Map<String, Object> paramMap);

	public List<LanternFestivalPrize> selectLanternFestivalPrizeList(Map<String, Object> paramMap);

	public int getSelectLanternFestivalPrizeCount(Map<String, Object> paramMap);
}
