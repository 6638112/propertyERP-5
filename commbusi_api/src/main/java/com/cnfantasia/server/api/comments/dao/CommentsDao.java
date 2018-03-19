package com.cnfantasia.server.api.comments.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsAvgEntity;
import com.cnfantasia.server.api.comments.entity.GoalIdCommentsEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.comments.entity.Comments;
/**
 * 描述:(评论) 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommentsDao extends AbstractBaseDao implements ICommentsDao{

	@Override
	public CommentsEntity selectFirstComentDetail(BigInteger targetId, Integer targetType) {
		Comments comments = new Comments();
		comments.setTargetId(targetId);
		comments.setTargetType(targetType);
		return sqlSession.selectOne("comments.select_firstComentDetail_by_TargetIdType",MapConverter.toMap(comments));
	}
	
	@Override
	public List<CommentsEntity> selectCommentsList(Integer goalType, BigInteger goalId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalId", goalId);
		String pageSqlKey = "comments.select_comments_list_page";
		String countSqlKey = "comments.select_comments_list_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<CommentsEntity> selectCommentsList(Integer goalType, BigInteger goalId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalId", goalId);
		return sqlSession.selectList("comments.select_comments_list", tmpMap);
	}

	@Override
	public List<GoalIdCommentsEntity> selectCommentsListMulti(Integer goalType, List<BigInteger> goalIdList,Integer commentsLength) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalIdList", goalIdList);
		tmpMap.put("commentsLength", commentsLength);
		return sqlSession.selectList("comments.select_comments_list_Multi", tmpMap);
	}
	
	
	@Override
	public CommentsEntity selectCommentsDetail(BigInteger commentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("commentId", commentId);
		CommentsEntity resCommentsEntity = sqlSession.selectOne("comments.select_comments_detail", tmpMap);
		return resCommentsEntity;
	}

	@Override
	public int delCommentsLogic(BigInteger userId, BigInteger commentId) {
		Comments commentsUpd = new Comments();//使用集成自BaseEntity的对象
		commentsUpd.setUserId(userId);
		commentsUpd.setId(commentId);
		return sqlSession.update("comments.del_comments_logic", commentsUpd);
	}

	@Override
	public Double selectCommentsAverageLevel(Integer goalType, BigInteger goalId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalId", goalId);
		return sqlSession.selectOne("comments.select_Comments_Average_Level", tmpMap);
	}

	@Override
	public List<CommentsPointsAvgEntity> selectCommentsPointsAverageLevel(Integer goalType, BigInteger goalId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalId", goalId);
		return sqlSession.selectList("comments.select_CommentsPoints_Average_Level", tmpMap);
	}

	@Override
	public String qryActivityUrl(String placeCode, List<BigInteger> codeIdList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("placeCode", placeCode);
		tmpMap.put("codeIdList", codeIdList);
		return sqlSession.selectOne("comments.select_activityUrl_by_placeCode", tmpMap);
	}
}
