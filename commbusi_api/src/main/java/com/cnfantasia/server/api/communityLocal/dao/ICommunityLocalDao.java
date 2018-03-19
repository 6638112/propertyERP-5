/**   
* Filename:    ICommunityLocalDao.java   
* @version:    1.0  
* Create at:   2014年7月13日 下午12:00:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.communityLocal.entity.CommunityForumContentEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;
import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;

/**
 * Filename:    ICommunityLocalDao.java
 * @version:    1.0.0
 * Create at:   2014年7月13日 下午12:00:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月13日       shiyl             1.0             1.0 Version
 */
public interface ICommunityLocalDao {
	
	/**
	 * 查询帖子类别列表
	 * "1":"八大模块类别","2":"社区类别","3":"拼一拼","4":"换一换"
	 */
	public List<CommunityForumType> selectCommunityForumTypeList(Integer type,PageModel pageModel);
	/**
	 * 查询不属于八大模块的所有类别
	 * @param type
	 * @param pageModel
	 * @return
	 */
	public List<CommunityForumType> selectCommunityForumTypeNot8List(PageModel pageModel);
	
	/**
	 * 查询帖子列表
	 * @param forumType
	 * @param groupBuildId
	 * @param pageModel
	 * @param userId
	 * @return
	 */
	public List<CommunityForumContentEntity> selectForumList(BigInteger forumType,BigInteger groupBuildId,PageModel pageModel,BigInteger userId);
	
	/**
	 * 查询帖子详情
	 * @param microBolgId
	 * @return
	 */
	public CommunityForumContentEntity selectForumDetail(BigInteger forumId,BigInteger userId);
	
	/**
	 * 查询最热门的帖子
	 */
	public CommunityForumContent selectMostHotForum(BigInteger forumType,BigInteger groupBuildId) ;

}
