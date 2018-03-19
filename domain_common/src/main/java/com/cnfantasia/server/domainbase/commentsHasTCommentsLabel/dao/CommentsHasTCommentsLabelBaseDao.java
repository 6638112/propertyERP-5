package com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.entity.CommentsHasTCommentsLabel;

/**
 * 描述:(评论包含的标签信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommentsHasTCommentsLabelBaseDao extends AbstractBaseDao implements ICommentsHasTCommentsLabelBaseDao{
	/**
	 * 根据条件查询(评论包含的标签信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsLabel> selectCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commentsHasTCommentsLabelBase.select_commentsHasTCommentsLabel",paramMap);
	}
	/**
	 * 按条件分页查询(评论包含的标签信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsLabel> selectCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommentsHasTCommentsLabelCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommentsHasTCommentsLabel> resMap= sqlSession.selectList("commentsHasTCommentsLabelBase.select_commentsHasTCommentsLabel_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(评论包含的标签信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsHasTCommentsLabel selectCommentsHasTCommentsLabelBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commentsHasTCommentsLabelBase.select_commentsHasTCommentsLabel_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(评论包含的标签信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommentsHasTCommentsLabelCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commentsHasTCommentsLabelBase.select_commentsHasTCommentsLabel_count", paramMap);
	}
	/**
	 * 往(评论包含的标签信息)新增一条记录
	 * @param commentsHasTCommentsLabel
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsLabel(CommentsHasTCommentsLabel commentsHasTCommentsLabel){
		return sqlSession.insert("commentsHasTCommentsLabelBase.insert_commentsHasTCommentsLabel",commentsHasTCommentsLabel);
	}
	/**
	 * 批量新增(评论包含的标签信息)信息
	 * @param commentsHasTCommentsLabelList
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsLabelBatch(List<CommentsHasTCommentsLabel> commentsHasTCommentsLabelList) {
		return sqlSession.insert("commentsHasTCommentsLabelBase.insert_commentsHasTCommentsLabel_Batch",commentsHasTCommentsLabelList);
	}
	
	/**
	 * 更新(评论包含的标签信息)信息
	 * @param commentsHasTCommentsLabel
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsLabel(CommentsHasTCommentsLabel commentsHasTCommentsLabel){
		return sqlSession.update("commentsHasTCommentsLabelBase.update_commentsHasTCommentsLabel", commentsHasTCommentsLabel);
	}
	/**
	 * 批量更新(评论包含的标签信息)信息
	 * @param commentsHasTCommentsLabelList
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsLabelBatch(List<CommentsHasTCommentsLabel> commentsHasTCommentsLabelList) {
		return sqlSession.update("commentsHasTCommentsLabelBase.update_commentsHasTCommentsLabel_Batch", commentsHasTCommentsLabelList);
	}
	
	/**
	 * 根据序列号删除(评论包含的标签信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsLabelLogic(java.math.BigInteger id){
		CommentsHasTCommentsLabel commentsHasTCommentsLabel = new CommentsHasTCommentsLabel();
		commentsHasTCommentsLabel.setId(id);
		commentsHasTCommentsLabel.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commentsHasTCommentsLabelBase.delete_commentsHasTCommentsLabel_Logic",commentsHasTCommentsLabel);
	}
	
	/**
	 * 根据Id 批量删除(评论包含的标签信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsLabelLogicBatch(List<java.math.BigInteger> idList) {
		List<CommentsHasTCommentsLabel> delList = new java.util.ArrayList<CommentsHasTCommentsLabel>();
		for(java.math.BigInteger id:idList){
			CommentsHasTCommentsLabel commentsHasTCommentsLabel = new CommentsHasTCommentsLabel();
			commentsHasTCommentsLabel.setId(id);
			commentsHasTCommentsLabel.setSys0DelState(RecordState_DELETED);
			delList.add(commentsHasTCommentsLabel);
		}
		return sqlSession.update("commentsHasTCommentsLabelBase.delete_commentsHasTCommentsLabel_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(评论包含的标签信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsLabel(java.math.BigInteger id){
//		return sqlSession.delete("commentsHasTCommentsLabelBase.delete_commentsHasTCommentsLabel", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论包含的标签信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsLabelBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commentsHasTCommentsLabelBase.delete_commentsHasTCommentsLabel_Batch", idList);
//	}
	
	
}
