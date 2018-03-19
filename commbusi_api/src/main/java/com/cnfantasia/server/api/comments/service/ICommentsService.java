package com.cnfantasia.server.api.comments.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsAvgEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsParamEntity;
import com.cnfantasia.server.api.comments.entity.GoalIdCommentsEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
/**
 * 描述:(评论) 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsService {
	
	/**
	 * 查询最近一条的评论内容详细
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	CommentsEntity getFirstComentDetail(BigInteger targetId, Integer targetType);
	
	/**
	 * 发表评论
	 * @param goalType
	 * @param goalId 首要评价目标ID
	 * @param goalId2nd 二级评价目标ID
	 * @param txtContent
	 * @param userId
	 * @param labelIds 评论中使用的标签Ids
	 * @param starLevel 评论星级
	 * @param pointsList 其他评分项
	 */
	void postComments(Integer goalType, BigInteger goalId, BigInteger goalId2nd, String txtContent, BigInteger userId,
					  List<BigInteger> labelIds, Double starLevel, List<CommentsPointsParamEntity> pointsList);
	/**
	 * 查询评论列表
	 * @param goalType
	 * @param goalId
	 * @param pageModel
	 * @return
	 */
	List<CommentsEntity> getCommentsList(Integer goalType, BigInteger goalId, PageModel pageModel);
	List<CommentsEntity> getCommentsList(Integer goalType, BigInteger goalId);
	
	/**
	 * 查询评论列表 
	 * @param goalType
	 * @param goalIdList 目标IdList
	 * @param commentsLength 对应每个目标Id所要查询的评论长度
	 * @return
	 */
	List<GoalIdCommentsEntity> getCommentsListMulti(Integer goalType, List<BigInteger> goalIdList, Integer commentsLength);
	
	/**
	 * 查询第一条评论信息
	 * @param goalType
	 * @param goalId
	 * @param pageModel
	 * @return
	 */
	CommentsEntity getFirstComments(Integer goalType, BigInteger goalId);
	
	/**
	 * 查看评论详情
	 * @param commentId
	 * @return
	 */
	CommentsEntity getCommentsDetail(BigInteger commentId);
	
	/**
	 * 删除评论
	 * @param userId
	 * @param commentId
	 */
	void delComments(BigInteger userId, BigInteger commentId);
	
	/**
	 * 查询指定记录的平均评分取值
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	Double getCommentsAverageLevel(Integer goalType, BigInteger goalId);
	
	/**
	 * 查询指定记录的其它评分项的平均取值
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	List<CommentsPointsAvgEntity> getCommentsPointsAverageLevel(Integer goalType, BigInteger goalId);
	
	void testUuid();

	/**
	 * 查询营销活动埋点
	 * @param placeCode
	 * @param userId
	 * @return
     */
	Map<String,Object> qryActivityUrl(String placeCode, BigInteger userId);
}
