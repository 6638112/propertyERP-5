/**   
* Filename:    CommunityForumContentEntity.java   
* @version:    1.0  
* Create at:   2014年7月22日 上午8:48:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;
import com.cnfantasia.server.domainbase.communityForumPic.entity.CommunityForumPic;
import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;

/**
 * Filename:    CommunityForumContentEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月22日 上午8:48:21
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月22日       shiyl             1.0             1.0 Version
 */
public class CommunityForumContentEntity extends CommunityForumContent{
	private static final long serialVersionUID = 1L;
	/**帖子图片列表*/
	private List<CommunityForumPic> communityForumPicList;
	public List<CommunityForumPic> getCommunityForumPicList() {
		return communityForumPicList;
	}
	public void setCommunityForumPicList(List<CommunityForumPic> communityForumPicList) {
		this.communityForumPicList = communityForumPicList;
	}

	/**帖子所属类别*/
	private CommunityForumType communityForumType;
	public CommunityForumType getCommunityForumType() {
		return communityForumType;
	}
	public void setCommunityForumType(CommunityForumType communityForumType) {
		this.communityForumType = communityForumType;
	}
	@Override
	public BigInteger gettCommunityForumTypeFId() {
		if(communityForumType==null){ return null; }
		return communityForumType.getId();
	}
	@Override
	public void settCommunityForumTypeFId(BigInteger tCommunityForumTypeFId) {
		if(communityForumType==null){communityForumType = new CommunityForumType();}
		communityForumType.setId(tCommunityForumTypeFId);
	}
	
	/**用户信息*/
	private UserSimpleEntity userInfo;
	public UserSimpleEntity getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserSimpleEntity userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public BigInteger getUserId() {
		if(userInfo==null){
			return null;
		}
		return userInfo.getId();
	}
	@Override
	public void setUserId(BigInteger user) {
		if(userInfo==null){
			userInfo = new UserSimpleEntity();
		}
		userInfo.setId(user);
	}
	
	/**是否被当前用户点赞,1是0否*/
	private Integer extIsFavour;
	public Integer getExtIsFavour() {
		return extIsFavour;
	}
	public void setExtIsFavour(Integer extIsFavour) {
		this.extIsFavour = extIsFavour;
	}

	/**点赞总数*/
	private Integer extFavourCount;
	public Integer getExtFavourCount() {
		return extFavourCount;
	}
	public void setExtFavourCount(Integer extFavourCount) {
		this.extFavourCount = extFavourCount;
	}
	
	/**评论总数*/
	private Integer extCommentCount;
	public Integer getExtCommentCount() {
		return extCommentCount;
	}
	public void setExtCommentCount(Integer extCommentCount) {
		this.extCommentCount = extCommentCount;
	}
	
}
