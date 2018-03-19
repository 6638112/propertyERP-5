package com.cnfantasia.server.domainbase.commFeedback.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commFeedback.entity.CommFeedback;

/**
 * 描述:(意见反馈表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommFeedbackBaseDao extends AbstractBaseDao implements ICommFeedbackBaseDao{
	/**
	 * 根据条件查询(意见反馈表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommFeedback> selectCommFeedbackByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commFeedbackBase.select_commFeedback",paramMap);
	}
	/**
	 * 按条件分页查询(意见反馈表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommFeedback> selectCommFeedbackByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommFeedbackCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommFeedback> resMap= sqlSession.selectList("commFeedbackBase.select_commFeedback_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(意见反馈表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommFeedback selectCommFeedbackBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commFeedbackBase.select_commFeedback_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(意见反馈表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommFeedbackCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commFeedbackBase.select_commFeedback_count", paramMap);
	}
	/**
	 * 往(意见反馈表)新增一条记录
	 * @param commFeedback
	 * @return
	 */
	@Override
	public int insertCommFeedback(CommFeedback commFeedback){
		return sqlSession.insert("commFeedbackBase.insert_commFeedback",commFeedback);
	}
	/**
	 * 批量新增(意见反馈表)信息
	 * @param commFeedbackList
	 * @return
	 */
	@Override
	public int insertCommFeedbackBatch(List<CommFeedback> commFeedbackList) {
		return sqlSession.insert("commFeedbackBase.insert_commFeedback_Batch",commFeedbackList);
	}
	
	/**
	 * 更新(意见反馈表)信息
	 * @param commFeedback
	 * @return
	 */
	@Override
	public int updateCommFeedback(CommFeedback commFeedback){
		return sqlSession.update("commFeedbackBase.update_commFeedback", commFeedback);
	}
	/**
	 * 批量更新(意见反馈表)信息
	 * @param commFeedbackList
	 * @return
	 */
	@Override
	public int updateCommFeedbackBatch(List<CommFeedback> commFeedbackList) {
		return sqlSession.update("commFeedbackBase.update_commFeedback_Batch", commFeedbackList);
	}
	
	/**
	 * 根据序列号删除(意见反馈表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommFeedbackLogic(java.math.BigInteger id){
		CommFeedback commFeedback = new CommFeedback();
		commFeedback.setId(id);
		commFeedback.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commFeedbackBase.delete_commFeedback_Logic",commFeedback);
	}
	
	/**
	 * 根据Id 批量删除(意见反馈表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommFeedbackLogicBatch(List<java.math.BigInteger> idList) {
		List<CommFeedback> delList = new java.util.ArrayList<CommFeedback>();
		for(java.math.BigInteger id:idList){
			CommFeedback commFeedback = new CommFeedback();
			commFeedback.setId(id);
			commFeedback.setSys0DelState(RecordState_DELETED);
			delList.add(commFeedback);
		}
		return sqlSession.update("commFeedbackBase.delete_commFeedback_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(意见反馈表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommFeedback(java.math.BigInteger id){
//		return sqlSession.delete("commFeedbackBase.delete_commFeedback", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(意见反馈表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommFeedbackBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commFeedbackBase.delete_commFeedback_Batch", idList);
//	}
	
	
}
