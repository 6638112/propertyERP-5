/**   
* Filename:    FamilyMsgExtDataEntity.java   
* @version:    1.0  
* Create at:   2015年3月12日 上午9:52:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.entity;

import java.util.List;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.familyMsg.entity.FamilyMsg;
import com.cnfantasia.server.domainbase.familyMsgExtData.entity.FamilyMsgExtData;
import com.cnfantasia.server.domainbase.familyMsgHasTUser.entity.FamilyMsgHasTUser;
import com.cnfantasia.server.domainbase.familyMsgPic.entity.FamilyMsgPic;

/**
 * Filename:    FamilyMsgExtDataEntity.java
 * @version:    1.0.0
 * Create at:   2015年3月12日 上午9:52:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月12日       shiyl             1.0             1.0 Version
 */
public class FamilyMsgEntity extends FamilyMsg{
	private static final long serialVersionUID = 1L;
	
	/**创建者信息*/
	private UserSimpleEntity createrInfo;
	public UserSimpleEntity getCreaterInfo() {
		return createrInfo;
	}
	public void setCreaterInfo(UserSimpleEntity createrInfo) {
		this.createrInfo = createrInfo;
	}
	
	/**家庭留言图片列表*/
	public List<FamilyMsgPic> familyMsgPicList;
	public List<FamilyMsgPic> getFamilyMsgPicList() {
		return familyMsgPicList;
	}
	public void setFamilyMsgPicList(List<FamilyMsgPic> familyMsgPicList) {
		this.familyMsgPicList = familyMsgPicList;
	}

	/**艾特的用户列表信息*/
	private List<FamilyMsgHasTUser> familyMsgHasTUserList;
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserList() {
		return familyMsgHasTUserList;
	}
	public void setFamilyMsgHasTUserList(List<FamilyMsgHasTUser> familyMsgHasTUserList) {
		this.familyMsgHasTUserList = familyMsgHasTUserList;
	}
	
	/**留言的拓展列表信息*/
	private List<FamilyMsgExtData> familyMsgExtDataList;
	public List<FamilyMsgExtData> getFamilyMsgExtDataList() {
		return familyMsgExtDataList;
	}
	public void setFamilyMsgExtDataList(List<FamilyMsgExtData> familyMsgExtDataList) {
		this.familyMsgExtDataList = familyMsgExtDataList;
	}
	
	/**默认加载的评论列表*/
	private List<CommentsEntity> preCommentsEntityList;
	public List<CommentsEntity> getPreCommentsEntityList() {
		return preCommentsEntityList;
	}
	public void setPreCommentsEntityList(List<CommentsEntity> preCommentsEntityList) {
		this.preCommentsEntityList = preCommentsEntityList;
	}
	
	/**是否被当前用户点赞,1是0否*/
	private Boolean extIsFavour;
	public Boolean getExtIsFavour() {
		return extIsFavour;
	}
	public void setExtIsFavour(Boolean extIsFavour) {
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
