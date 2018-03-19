package com.cnfantasia.server.api.propertyRepair.entity;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;

/**
 * 报修单详情
 * 
 * @author wenfq 2015-04-06
 * 
 */
public class PropertyRepairEntity extends PropertyRepair {
	private static final long serialVersionUID = 1L;
	String repairTypeName;//报修类型
	PropertyRepairer propertyRepairer;//维修工
	int commentCount;//评论总数
	double avgStarLevel = 0;//维修师傅评论得分 
	CommentsEntity firstComment;//第一条评论

	public double getAvgStarLevel() {
		return avgStarLevel;
	}

	public void setAvgStarLevel(double avgStarLevel) {
		this.avgStarLevel = avgStarLevel;
	}

	public String getRepairTypeName() {
		return repairTypeName;
	}

	public void setRepairTypeName(String repairTypeName) {
		this.repairTypeName = repairTypeName;
	}

	public PropertyRepairer getPropertyRepairer() {
		return propertyRepairer;
	}

	public void setPropertyRepairer(PropertyRepairer propertyRepairer) {
		this.propertyRepairer = propertyRepairer;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public CommentsEntity getFirstComment() {
		return firstComment;
	}

	public void setFirstComment(CommentsEntity firstComment) {
		this.firstComment = firstComment;
	}

}
