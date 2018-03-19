/**   
* Filename:    IMicroblogService.java   
* @version:    1.0  
* Create at:   2014年7月22日 上午8:46:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.microblog.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.microblog.entity.MicroblogContentEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoGroupBuilding;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;
import com.cnfantasia.server.domainbase.microblogReport.entity.MicroblogReport;
import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;

/**
 * Filename:    IMicroblogService.java
 * @version:    1.0.0
 * Create at:   2014年7月22日 上午8:46:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月22日       shiyl             1.0             1.0 Version
 */
public interface IMicroblogService {
	/**
	 * 查询微博类别
	 */
	public List<MicroblogType> getMicroblogTypeList(PageModel pageModel);
	
	/**
	 * 查询某个类别下的微博列表
	 * @param microBlogType
	 * @return
	 */
	public List<MicroblogContentEntity> getMicroblogList(BigInteger microBlogType,PageModel pageModel,BigInteger userId);
	/**
	 * 查询所有微博列表
	 * @return
	 */
	public List<MicroblogContentEntity> getMicroblogList(PageModel pageModel,BigInteger userId);
	
	/**
	 * 发布微博
	 * @param userId
	 * @param text
	 * @param activtyName 
	 * @param typeId
	 * @param picList
	 * @return
	 */
	public void postMicroblog(BigInteger userId,String text,String activtyName, BigInteger typeId,List<RequestFileEntity> picList);
	
	/**
	 * 查看微博详情
	 * @param microBolgId
	 * @return
	 */
	public MicroblogContentEntity getMicroblogDetail(BigInteger microBolgId,BigInteger userId);

	/**
	 * 根据用户Id查询所属小区
	 * @param userId
	 * @return
	 */
	public GroupBuilding getGroupBuildingByUserId(BigInteger userId);

	/**
	 * 查询对应小区下的首页展示微博信息
	 * @param broupBuildId
	 * @param userId
	 * @return
	 */
	public Map<MicroblogType, MicroblogContent> getHomeInfo(BigInteger broupBuildId,BigInteger userId);
	
	/**
	 * 查询所有微博记录的最新更新时间
	 * @return
	 */
	public String fetchAllMicroblogLastUpdTime(BigInteger microblobTypeId,PageModel pageModel,BigInteger userId);
	
	/**
	 * 查询用户所在小区已经发布过微博的用户数量
	 * @param userId
	 * @return
	 */
	public Integer getHaveSendBlogUserCount(BigInteger userId);
	
	/**
	 * 查看微博链接详情数据
	 * @param microBolgId
	 * @return
	 */
	public String getMicroblogLinkDetail(BigInteger microBolgId);

	public BigInteger getGroupBuildingIdByUserId(BigInteger userId);
	
	public void saveReport(MicroblogReport microblogReport);
	
	public List<XibaoEntity> selectXibaoList(Map<String,Object> paramMap);
	
	public XibaoGroupBuilding selectXibaoGroupBuilding(Map<String,Object> paramMap);

	public int getSelectXibaoListCount(Map<String, Object> paramMap);

	public List<LanternFestivalPrize> selectLanternFestivalPrizeList(Map<String, Object> paramMap);

	public int getSelectLanternFestivalPrizeCount(Map<String, Object> paramMap);
}
