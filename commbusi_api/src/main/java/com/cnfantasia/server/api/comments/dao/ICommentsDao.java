package com.cnfantasia.server.api.comments.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsAvgEntity;
import com.cnfantasia.server.api.comments.entity.GoalIdCommentsEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
/**
 * 描述:(评论) 具体业务Dao层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsDao{
	
	/**
	 * 查询最近一条的评论内容详细
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	CommentsEntity selectFirstComentDetail(BigInteger targetId, Integer targetType);
	
	/**
	 * 查询评论列表
	 * @param goalType
	 * @param goalId
	 * @param pageModel 
	 * @return
	 */
	List<CommentsEntity> selectCommentsList(Integer goalType, BigInteger goalId, PageModel pageModel);
	List<CommentsEntity> selectCommentsList(Integer goalType, BigInteger goalId);
	
	/**
	 * 查询评论列表 
	 * @param goalType
	 * @param goalIdList 目标IdList
	 * @param commentsLength 对应每个目标Id所要查询的评论长度 为空表示全部
	 * @return
	 */
	List<GoalIdCommentsEntity> selectCommentsListMulti(Integer goalType, List<BigInteger> goalIdList, Integer commentsLength);
	
	/**
	 * 查询评论详情
	 * @param commentId
	 * @return
	 */
	CommentsEntity selectCommentsDetail(BigInteger commentId);
	
	/**
	 * 删除评论
	 * @param userId
	 * @param commentId
	 */
	int delCommentsLogic(BigInteger userId, BigInteger commentId);
	
	/**
	 * 查询指定记录的平均评分取值
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	Double selectCommentsAverageLevel(Integer goalType, BigInteger goalId);
	
	/**
	 * 查询指定记录的其它评分项的平均取值
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	List<CommentsPointsAvgEntity> selectCommentsPointsAverageLevel(Integer goalType, BigInteger goalId);

	/**
	 * 查询营销活动埋点
	 * @param placeCode 埋点 地方code
	 * @param codeIdList 范围IDlist(城市，小区)
	 * @return
	 */
	String qryActivityUrl(String placeCode, List<BigInteger> codeIdList);
}
