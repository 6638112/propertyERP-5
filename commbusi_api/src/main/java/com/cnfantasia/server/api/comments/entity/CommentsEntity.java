package com.cnfantasia.server.api.comments.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.comments.entity.Comments;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
/**
 * 描述:(评论) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class CommentsEntity extends Comments{
	private static final long serialVersionUID = 1L;
	
	/**评论者基本信息*/
	private UserSimpleEntity user;
	public UserSimpleEntity getUser() {
		return user;
	}
	public void setUser(UserSimpleEntity user) {
		this.user = user;
	}
	@Override
	public BigInteger getUserId() {
		if(user==null){return null;}
		return user.getId();
	}
	@Override
	public void setUserId(BigInteger userId) {
		if(user==null){
			user = new UserSimpleEntity();
		}
		user.setId(userId);
	}
	
	/**
	 * 用户所属小区信息
	 */
	private GroupBuilding userGroupBuilding;
	public GroupBuilding getUserGroupBuilding() {
		return userGroupBuilding;
	}
	public void setUserGroupBuilding(GroupBuilding userGroupBuilding) {
		this.userGroupBuilding = userGroupBuilding;
	}

	/**被艾特的用户*/
	private List<UserSimpleEntity> noticeUserList;
	public List<UserSimpleEntity> getNoticeUserList() {
		return noticeUserList;
	}
	public void setNoticeUserList(List<UserSimpleEntity> noticeUserList) {
		this.noticeUserList = noticeUserList;
	}
	
	
	/**评论使用的标签*/
	private List<CommentsLabel> commentsLabelList;
	public List<CommentsLabel> getCommentsLabelList() {
		return commentsLabelList;
	}
	public void setCommentsLabelList(List<CommentsLabel> commentsLabelList) {
		this.commentsLabelList = commentsLabelList;
	}
	
	/**评论使用的评分项*/
	private List<CommentsHasTCommentsPointsEntity> commentsHasTCommentsPointsEntityList;
	public List<CommentsHasTCommentsPointsEntity> getCommentsHasTCommentsPointsEntityList() {
		return commentsHasTCommentsPointsEntityList;
	}
	public void setCommentsHasTCommentsPointsEntityList(
			List<CommentsHasTCommentsPointsEntity> commentsHasTCommentsPointsEntityList) {
		this.commentsHasTCommentsPointsEntityList = commentsHasTCommentsPointsEntityList;
	}
	
	private Double avgTotalStarLevel;
	public Double getAvgTotalStarLevel() {
		return avgTotalStarLevel;
	}
	public void setAvgTotalStarLevel(Double avgTotalStarLevel) {
		this.avgTotalStarLevel = avgTotalStarLevel;
	}
	
}
