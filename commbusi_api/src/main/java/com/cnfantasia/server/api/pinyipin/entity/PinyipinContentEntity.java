/**   
* Filename:    PinyipinContentEntity.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:59:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.communityPinyipinContent.entity.CommunityPinyipinContent;
import com.cnfantasia.server.domainbase.communityPinyipinPic.entity.CommunityPinyipinPic;

/**
 * Filename:    PinyipinContentEntity.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:59:11
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public class PinyipinContentEntity extends CommunityPinyipinContent{
	private static final long serialVersionUID = 1L;
	
	/**当前用户已拼数量之和*/
	private Long currUserReserveCount;
	public Long getCurrUserReserveCount() {
		return currUserReserveCount;
	}
	public void setCurrUserReserveCount(Long currUserReserveCount) {
		this.currUserReserveCount = currUserReserveCount;
	}

	/**是否可以生成发货清单*/
	private Boolean deliverAble;
	public Boolean getDeliverAble() {
		return deliverAble;
	}
	public void setDeliverAble(Boolean deliverAble) {
		this.deliverAble = deliverAble;
	}
	
	/**拼单与用户所属关系 0:游客，1:发起者，2参与者*/
	private Integer userBelong;
	public Integer getUserBelong() {
		return userBelong;
	}
	public void setUserBelong(Integer userBelong) {
		this.userBelong = userBelong;
	}
	
	/**已预订总量*/
	private Long reserveCount;
	public Long getReserveCount() {
		return reserveCount;
	}
	public void setReserveCount(Long reserveCount) {
		this.reserveCount = reserveCount;
	}

	/**当前预订总人数*/
	private Integer currUserCount;
	public Integer getCurrUserCount() {
		return currUserCount;
	}
	public void setCurrUserCount(Integer currUserCount) {
		this.currUserCount = currUserCount;
	}

	/**拼单创建者*/
	private UserSimpleEntity createUser;
	public UserSimpleEntity getCreateUser() {
		return createUser;
	}
	public void setCreateUser(UserSimpleEntity createUser) {
		this.createUser = createUser;
	}
	
	
	@Override
	public BigInteger getUserId() {
		if(createUser==null){
			return null;
		}
		return createUser.getId();
	}
	@Override
	public void setUserId(BigInteger userId) {
		if(createUser==null){
			createUser = new UserSimpleEntity();
		}
		createUser.setId(userId);
	}

	/**图片列表*/
	private List<CommunityPinyipinPic> pinyipinPicList;
	public List<CommunityPinyipinPic> getPinyipinPicList() {
		return pinyipinPicList;
	}
	public void setPinyipinPicList(List<CommunityPinyipinPic> pinyipinPicList) {
		this.pinyipinPicList = pinyipinPicList;
	}
	
	/**拼单用户列表*/
	private List<UserSimpleEntity> reserveUserList;
	public List<UserSimpleEntity> getReserveUserList() {
		return reserveUserList;
	}
	public void setReserveUserList(List<UserSimpleEntity> reserveUserList) {
		this.reserveUserList = reserveUserList;
	}
//	private List<PinyipinReserveEntity> pinyipinReserveList;
//	public List<PinyipinReserveEntity> getPinyipinReserveList() {
//		return pinyipinReserveList;
//	}
//	public void setPinyipinReserveList(List<PinyipinReserveEntity> pinyipinReserveList) {
//		this.pinyipinReserveList = pinyipinReserveList;
//	}

//	/**
//	 * 默认评论信息
//	 */
//	private List<Comments> topCommentsList;
//	public List<Comments> getTopCommentsList() {
//		return topCommentsList;
//	}
//	public void setTopCommentsList(List<Comments> topCommentsList) {
//		this.topCommentsList = topCommentsList;
//	}
	


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
