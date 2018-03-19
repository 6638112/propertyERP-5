package com.cnfantasia.server.domainbase.commentsLabel.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;

/**
 * 描述:(评论标签) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommentsLabelBaseDao extends AbstractBaseDao implements ICommentsLabelBaseDao{
	/**
	 * 根据条件查询(评论标签)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsLabel> selectCommentsLabelByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commentsLabelBase.select_commentsLabel",paramMap);
	}
	/**
	 * 按条件分页查询(评论标签)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsLabel> selectCommentsLabelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommentsLabelCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommentsLabel> resMap= sqlSession.selectList("commentsLabelBase.select_commentsLabel_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(评论标签)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsLabel selectCommentsLabelBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commentsLabelBase.select_commentsLabel_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(评论标签)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommentsLabelCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commentsLabelBase.select_commentsLabel_count", paramMap);
	}
	/**
	 * 往(评论标签)新增一条记录
	 * @param commentsLabel
	 * @return
	 */
	@Override
	public int insertCommentsLabel(CommentsLabel commentsLabel){
		return sqlSession.insert("commentsLabelBase.insert_commentsLabel",commentsLabel);
	}
	/**
	 * 批量新增(评论标签)信息
	 * @param commentsLabelList
	 * @return
	 */
	@Override
	public int insertCommentsLabelBatch(List<CommentsLabel> commentsLabelList) {
		return sqlSession.insert("commentsLabelBase.insert_commentsLabel_Batch",commentsLabelList);
	}
	
	/**
	 * 更新(评论标签)信息
	 * @param commentsLabel
	 * @return
	 */
	@Override
	public int updateCommentsLabel(CommentsLabel commentsLabel){
		return sqlSession.update("commentsLabelBase.update_commentsLabel", commentsLabel);
	}
	/**
	 * 批量更新(评论标签)信息
	 * @param commentsLabelList
	 * @return
	 */
	@Override
	public int updateCommentsLabelBatch(List<CommentsLabel> commentsLabelList) {
		return sqlSession.update("commentsLabelBase.update_commentsLabel_Batch", commentsLabelList);
	}
	
	/**
	 * 根据序列号删除(评论标签)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsLabelLogic(java.math.BigInteger id){
		CommentsLabel commentsLabel = new CommentsLabel();
		commentsLabel.setId(id);
		commentsLabel.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commentsLabelBase.delete_commentsLabel_Logic",commentsLabel);
	}
	
	/**
	 * 根据Id 批量删除(评论标签)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsLabelLogicBatch(List<java.math.BigInteger> idList) {
		List<CommentsLabel> delList = new java.util.ArrayList<CommentsLabel>();
		for(java.math.BigInteger id:idList){
			CommentsLabel commentsLabel = new CommentsLabel();
			commentsLabel.setId(id);
			commentsLabel.setSys0DelState(RecordState_DELETED);
			delList.add(commentsLabel);
		}
		return sqlSession.update("commentsLabelBase.delete_commentsLabel_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(评论标签)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsLabel(java.math.BigInteger id){
//		return sqlSession.delete("commentsLabelBase.delete_commentsLabel", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论标签)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsLabelBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commentsLabelBase.delete_commentsLabel_Batch", idList);
//	}
	
	
}
