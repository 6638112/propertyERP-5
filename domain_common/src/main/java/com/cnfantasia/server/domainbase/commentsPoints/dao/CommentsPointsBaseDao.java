package com.cnfantasia.server.domainbase.commentsPoints.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;

/**
 * 描述:(评论的评分项) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommentsPointsBaseDao extends AbstractBaseDao implements ICommentsPointsBaseDao{
	/**
	 * 根据条件查询(评论的评分项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsPoints> selectCommentsPointsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commentsPointsBase.select_commentsPoints",paramMap);
	}
	/**
	 * 按条件分页查询(评论的评分项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsPoints> selectCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommentsPointsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommentsPoints> resMap= sqlSession.selectList("commentsPointsBase.select_commentsPoints_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(评论的评分项)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsPoints selectCommentsPointsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commentsPointsBase.select_commentsPoints_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(评论的评分项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommentsPointsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commentsPointsBase.select_commentsPoints_count", paramMap);
	}
	/**
	 * 往(评论的评分项)新增一条记录
	 * @param commentsPoints
	 * @return
	 */
	@Override
	public int insertCommentsPoints(CommentsPoints commentsPoints){
		return sqlSession.insert("commentsPointsBase.insert_commentsPoints",commentsPoints);
	}
	/**
	 * 批量新增(评论的评分项)信息
	 * @param commentsPointsList
	 * @return
	 */
	@Override
	public int insertCommentsPointsBatch(List<CommentsPoints> commentsPointsList) {
		return sqlSession.insert("commentsPointsBase.insert_commentsPoints_Batch",commentsPointsList);
	}
	
	/**
	 * 更新(评论的评分项)信息
	 * @param commentsPoints
	 * @return
	 */
	@Override
	public int updateCommentsPoints(CommentsPoints commentsPoints){
		return sqlSession.update("commentsPointsBase.update_commentsPoints", commentsPoints);
	}
	/**
	 * 批量更新(评论的评分项)信息
	 * @param commentsPointsList
	 * @return
	 */
	@Override
	public int updateCommentsPointsBatch(List<CommentsPoints> commentsPointsList) {
		return sqlSession.update("commentsPointsBase.update_commentsPoints_Batch", commentsPointsList);
	}
	
	/**
	 * 根据序列号删除(评论的评分项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsPointsLogic(java.math.BigInteger id){
		CommentsPoints commentsPoints = new CommentsPoints();
		commentsPoints.setId(id);
		commentsPoints.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commentsPointsBase.delete_commentsPoints_Logic",commentsPoints);
	}
	
	/**
	 * 根据Id 批量删除(评论的评分项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsPointsLogicBatch(List<java.math.BigInteger> idList) {
		List<CommentsPoints> delList = new java.util.ArrayList<CommentsPoints>();
		for(java.math.BigInteger id:idList){
			CommentsPoints commentsPoints = new CommentsPoints();
			commentsPoints.setId(id);
			commentsPoints.setSys0DelState(RecordState_DELETED);
			delList.add(commentsPoints);
		}
		return sqlSession.update("commentsPointsBase.delete_commentsPoints_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(评论的评分项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsPoints(java.math.BigInteger id){
//		return sqlSession.delete("commentsPointsBase.delete_commentsPoints", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论的评分项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsPointsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commentsPointsBase.delete_commentsPoints_Batch", idList);
//	}
	
	
}
