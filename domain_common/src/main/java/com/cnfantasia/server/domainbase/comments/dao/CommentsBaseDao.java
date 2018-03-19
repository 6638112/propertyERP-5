package com.cnfantasia.server.domainbase.comments.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.comments.entity.Comments;

/**
 * 描述:(评论) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommentsBaseDao extends AbstractBaseDao implements ICommentsBaseDao{
	/**
	 * 根据条件查询(评论)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Comments> selectCommentsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commentsBase.select_comments",paramMap);
	}
	/**
	 * 按条件分页查询(评论)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Comments> selectCommentsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommentsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Comments> resMap= sqlSession.selectList("commentsBase.select_comments_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(评论)信息
	 * @param id
	 * @return
	 */
	@Override
	public Comments selectCommentsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commentsBase.select_comments_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(评论)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommentsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commentsBase.select_comments_count", paramMap);
	}
	/**
	 * 往(评论)新增一条记录
	 * @param comments
	 * @return
	 */
	@Override
	public int insertComments(Comments comments){
		return sqlSession.insert("commentsBase.insert_comments",comments);
	}
	/**
	 * 批量新增(评论)信息
	 * @param commentsList
	 * @return
	 */
	@Override
	public int insertCommentsBatch(List<Comments> commentsList) {
		return sqlSession.insert("commentsBase.insert_comments_Batch",commentsList);
	}
	
	/**
	 * 更新(评论)信息
	 * @param comments
	 * @return
	 */
	@Override
	public int updateComments(Comments comments){
		return sqlSession.update("commentsBase.update_comments", comments);
	}
	/**
	 * 批量更新(评论)信息
	 * @param commentsList
	 * @return
	 */
	@Override
	public int updateCommentsBatch(List<Comments> commentsList) {
		return sqlSession.update("commentsBase.update_comments_Batch", commentsList);
	}
	
	/**
	 * 根据序列号删除(评论)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsLogic(java.math.BigInteger id){
		Comments comments = new Comments();
		comments.setId(id);
		comments.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commentsBase.delete_comments_Logic",comments);
	}
	
	/**
	 * 根据Id 批量删除(评论)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsLogicBatch(List<java.math.BigInteger> idList) {
		List<Comments> delList = new java.util.ArrayList<Comments>();
		for(java.math.BigInteger id:idList){
			Comments comments = new Comments();
			comments.setId(id);
			comments.setSys0DelState(RecordState_DELETED);
			delList.add(comments);
		}
		return sqlSession.update("commentsBase.delete_comments_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(评论)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteComments(java.math.BigInteger id){
//		return sqlSession.delete("commentsBase.delete_comments", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commentsBase.delete_comments_Batch", idList);
//	}
	
	
}
