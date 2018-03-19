package com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.entity.CommentsHasTCommentsPoints;

/**
 * 描述:(评论包含哪些评分值) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommentsHasTCommentsPointsBaseDao extends AbstractBaseDao implements ICommentsHasTCommentsPointsBaseDao{
	/**
	 * 根据条件查询(评论包含哪些评分值)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsPoints> selectCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commentsHasTCommentsPointsBase.select_commentsHasTCommentsPoints",paramMap);
	}
	/**
	 * 按条件分页查询(评论包含哪些评分值)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsPoints> selectCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommentsHasTCommentsPointsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommentsHasTCommentsPoints> resMap= sqlSession.selectList("commentsHasTCommentsPointsBase.select_commentsHasTCommentsPoints_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(评论包含哪些评分值)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsHasTCommentsPoints selectCommentsHasTCommentsPointsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commentsHasTCommentsPointsBase.select_commentsHasTCommentsPoints_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(评论包含哪些评分值)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommentsHasTCommentsPointsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commentsHasTCommentsPointsBase.select_commentsHasTCommentsPoints_count", paramMap);
	}
	/**
	 * 往(评论包含哪些评分值)新增一条记录
	 * @param commentsHasTCommentsPoints
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsPoints(CommentsHasTCommentsPoints commentsHasTCommentsPoints){
		return sqlSession.insert("commentsHasTCommentsPointsBase.insert_commentsHasTCommentsPoints",commentsHasTCommentsPoints);
	}
	/**
	 * 批量新增(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsPointsBatch(List<CommentsHasTCommentsPoints> commentsHasTCommentsPointsList) {
		return sqlSession.insert("commentsHasTCommentsPointsBase.insert_commentsHasTCommentsPoints_Batch",commentsHasTCommentsPointsList);
	}
	
	/**
	 * 更新(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPoints
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsPoints(CommentsHasTCommentsPoints commentsHasTCommentsPoints){
		return sqlSession.update("commentsHasTCommentsPointsBase.update_commentsHasTCommentsPoints", commentsHasTCommentsPoints);
	}
	/**
	 * 批量更新(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsPointsBatch(List<CommentsHasTCommentsPoints> commentsHasTCommentsPointsList) {
		return sqlSession.update("commentsHasTCommentsPointsBase.update_commentsHasTCommentsPoints_Batch", commentsHasTCommentsPointsList);
	}
	
	/**
	 * 根据序列号删除(评论包含哪些评分值)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsPointsLogic(java.math.BigInteger id){
		CommentsHasTCommentsPoints commentsHasTCommentsPoints = new CommentsHasTCommentsPoints();
		commentsHasTCommentsPoints.setId(id);
		commentsHasTCommentsPoints.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commentsHasTCommentsPointsBase.delete_commentsHasTCommentsPoints_Logic",commentsHasTCommentsPoints);
	}
	
	/**
	 * 根据Id 批量删除(评论包含哪些评分值)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsPointsLogicBatch(List<java.math.BigInteger> idList) {
		List<CommentsHasTCommentsPoints> delList = new java.util.ArrayList<CommentsHasTCommentsPoints>();
		for(java.math.BigInteger id:idList){
			CommentsHasTCommentsPoints commentsHasTCommentsPoints = new CommentsHasTCommentsPoints();
			commentsHasTCommentsPoints.setId(id);
			commentsHasTCommentsPoints.setSys0DelState(RecordState_DELETED);
			delList.add(commentsHasTCommentsPoints);
		}
		return sqlSession.update("commentsHasTCommentsPointsBase.delete_commentsHasTCommentsPoints_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(评论包含哪些评分值)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsPoints(java.math.BigInteger id){
//		return sqlSession.delete("commentsHasTCommentsPointsBase.delete_commentsHasTCommentsPoints", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论包含哪些评分值)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsPointsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commentsHasTCommentsPointsBase.delete_commentsHasTCommentsPoints_Batch", idList);
//	}
	
	
}
