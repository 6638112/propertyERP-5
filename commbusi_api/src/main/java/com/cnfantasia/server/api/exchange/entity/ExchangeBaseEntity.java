/**   
* Filename:    ExchangeBaseEntity.java   
* @version:    1.0  
* Create at:   2014年10月17日 上午3:37:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.entity;

import java.util.List;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.communityExchangeContent.entity.CommunityExchangeContent;
import com.cnfantasia.server.domainbase.communityExchangePic.entity.CommunityExchangePic;

/**
 * Filename:    ExchangeBaseEntity.java
 * @version:    1.0.0
 * Create at:   2014年10月17日 上午3:37:26
 * Description: 增加了图片信息及所属用户信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月17日       shiyl             1.0             1.0 Version
 */
public class ExchangeBaseEntity extends CommunityExchangeContent{
	private static final long serialVersionUID = 1L;
	
	/**已经参与要换的换物数量*/
	private Integer relationCount;
	public Integer getRelationCount() {
		return relationCount;
	}
	public void setRelationCount(Integer relationCount) {
		this.relationCount = relationCount;
	}
	
	/**换物与用户所属关系 0:游客，1:发起者，2参与者*/
	private Integer userBelong;
	public Integer getUserBelong() {
		return userBelong;
	}
	public void setUserBelong(Integer userBelong) {
		this.userBelong = userBelong;
	}
	
	/**拥有者*/
	private UserSimpleEntity owner;
	public UserSimpleEntity getOwner() {
		return owner;
	}
	public void setOwner(UserSimpleEntity owner) {
		this.owner = owner;
	}
	
	/**图片列表*/
	private List<CommunityExchangePic> exchangePicList;
	public List<CommunityExchangePic> getExchangePicList() {
		return exchangePicList;
	}
	public void setExchangePicList(List<CommunityExchangePic> exchangePicList) {
		this.exchangePicList = exchangePicList;
	}
	
	/**成功换物的信息*/
	private ExchangeRelationGoalEntity succExchRelaGoalEntity;
	public ExchangeRelationGoalEntity getSuccExchRelaGoalEntity() {
		return succExchRelaGoalEntity;
	}
	public void setSuccExchRelaGoalEntity(ExchangeRelationGoalEntity succExchRelaGoalEntity) {
		this.succExchRelaGoalEntity = succExchRelaGoalEntity;
	}

	/**是否可删除*/
	private Boolean isDeleteAble;
	public Boolean getIsDeleteAble() {
		return isDeleteAble;
	}
	public void setIsDeleteAble(Boolean isDeleteAble) {
		this.isDeleteAble = isDeleteAble;
	}
	/**是否可编辑*/
	private Boolean isEditAble;
	public Boolean getIsEditAble() {
		return isEditAble;
	}
	public void setIsEditAble(Boolean isEditAble) {
		this.isEditAble = isEditAble;
	}

	/**最新一条评论信息*/
	private CommentsEntity firstComment;
	public CommentsEntity getFirstComment() {
		return firstComment;
	}
	public void setFirstComment(CommentsEntity firstComment) {
		this.firstComment = firstComment;
	}
	/**
	 * 评价总数
	 */
	private Integer commentTotalCount;
	public Integer getCommentTotalCount() {
		return commentTotalCount;
	}
	public void setCommentTotalCount(Integer commentTotalCount) {
		this.commentTotalCount = commentTotalCount;
	}
	
	/**
	 * 是否已点赞，true是false否
	 */
	private Boolean isSupported;
	public Boolean getIsSupported() {
		return isSupported;
	}
	public void setIsSupported(Boolean isSupported) {
		this.isSupported = isSupported;
	}
	/**
	 * 点赞总数
	 */
	private Integer totalSupportCount;
	public Integer getTotalSupportCount() {
		return totalSupportCount;
	}
	public void setTotalSupportCount(Integer totalSupportCount) {
		this.totalSupportCount = totalSupportCount;
	}
	
	/**是否已收藏*/
	private Boolean isCollected;
	public Boolean getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Boolean isCollected) {
		this.isCollected = isCollected;
	}
}
