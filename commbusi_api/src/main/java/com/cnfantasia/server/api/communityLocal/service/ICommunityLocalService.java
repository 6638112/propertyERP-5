/**   
* Filename:    ICommunityLocalService.java   
* @version:    1.0  
* Create at:   2014年7月13日 上午10:47:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.communityLocal.entity.CommunityForumContentEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;
import com.cnfantasia.server.domainbase.communityForumPic.entity.CommunityForumPic;
import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;


/**
 * Filename:    ICommunityLocalService.java
 * @version:    1.0.0
 * Create at:   2014年7月13日 上午10:47:46
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月13日       shiyl             1.0             1.0 Version
 */
public interface ICommunityLocalService {
	/**
	 * 查询帖子类别列表
	 * "1":"八大模块类别","2":"社区类别","3":"拼一拼","4":"换一换"
	 */
	public List<CommunityForumType> getCommunityForumTypeList(Integer type,PageModel pageModel);
	
	/**
	 * 查询不属于八大模块的所有类别
	 * @param type
	 * @param pageModel
	 * @return
	 */
	public List<CommunityForumType> getCommunityForumTypeNot8List(PageModel pageModel);
	
	/**
	 * 根据帖子类别Id查询对应的基本信息
	 */
	public CommunityForumType getCommunityForumTypeById(BigInteger forumTypeId);
	/**
	 * 查询某个类别下的帖子列表
	 * @param forumType
	 * @return
	 */
	public List<CommunityForumContentEntity> getForumList(BigInteger forumType,PageModel pageModel,BigInteger userId);
	/**
	 * 查询所有帖子列表
	 * @return
	 */
	public List<CommunityForumContentEntity> getForumList(PageModel pageModel,BigInteger userId);
	
	/**
	 * 发布帖子
	 * @param userId
	 * @param text
	 * @param typeId
	 * @param picList
	 * @return
	 */
	public void postForum(BigInteger userId,String text,BigInteger typeId,List<RequestFileEntity> picList);
	
	/**
	 * 查看帖子详情
	 * @param forumId
	 * @return
	 */
	public CommunityForumContentEntity getForumDetail(BigInteger forumId,BigInteger userId);

	/**
	 * 根据用户Id查询所属小区
	 * @param userId
	 * @return
	 */
	public GroupBuilding getGroupBuildingByUserId(BigInteger userId);

	/**
	 * 查询对应小区下的首页展示帖子信息
	 * @param groupBuildId
	 * @param userId
	 * @return
	 */
	public Map<CommunityForumType, CommunityForumContent> getHomeInfo(BigInteger groupBuildId,BigInteger userId,Integer is8Type);

	/**
	 * @param communityForumType
	 * @param basePath
	 * @return
	 */
	public Map<String, Object> communityForumType2Map(BigInteger userId,CommunityForumType communityForumType, String basePath);
	
	/**
	 * @param icoBasePath
	 * @param topicId
	 * @param topicName
	 * @param iconTail
	 * @param updTime
	 * @param detailUrlAll
	 * @return
	 */
	/*public Map<String, Object> communityForumType2Map8Type(String icoBasePath, BigInteger topicId, String topicName,
			String iconTail, String updTime, String detailUrlAll);*/
	public Map<String,Object> communityForumType2Map8Type(/*String icoBasePath,*//*BigInteger topicId,*/String topicName,/*String iconTail,String updTime,*/String detailUrlAll);
	
	/**
	 * @param tmpObj
	 * @param communityForumPicList
	 * @param userInfo
	 * @param nowTimeLong
	 * @param isFavour
	 * @param favourCount
	 * @param commentCount
	 * @return
	 */
	public Map<String, Object> communityForumContent2Map(CommunityForumContent tmpObj,
			List<CommunityForumPic> communityForumPicList, UserSimpleEntity userInfo, Long nowTimeLong, Integer isFavour,
			Integer favourCount, Integer commentCount);

	
	
}
