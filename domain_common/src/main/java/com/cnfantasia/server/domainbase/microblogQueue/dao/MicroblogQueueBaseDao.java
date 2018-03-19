package com.cnfantasia.server.domainbase.microblogQueue.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

/**
 * 描述:(小区博客队列表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MicroblogQueueBaseDao extends AbstractBaseDao implements IMicroblogQueueBaseDao{
	/**
	 * 根据条件查询(小区博客队列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogQueue> selectMicroblogQueueByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("microblogQueueBase.select_microblogQueue",paramMap);
	}
	/**
	 * 按条件分页查询(小区博客队列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogQueue> selectMicroblogQueueByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMicroblogQueueCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MicroblogQueue> resMap= sqlSession.selectList("microblogQueueBase.select_microblogQueue_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区博客队列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogQueue selectMicroblogQueueBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("microblogQueueBase.select_microblogQueue_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区博客队列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMicroblogQueueCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("microblogQueueBase.select_microblogQueue_count", paramMap);
	}
	/**
	 * 往(小区博客队列表)新增一条记录
	 * @param microblogQueue
	 * @return
	 */
	@Override
	public int insertMicroblogQueue(MicroblogQueue microblogQueue){
		return sqlSession.insert("microblogQueueBase.insert_microblogQueue",microblogQueue);
	}
	/**
	 * 批量新增(小区博客队列表)信息
	 * @param microblogQueueList
	 * @return
	 */
	@Override
	public int insertMicroblogQueueBatch(List<MicroblogQueue> microblogQueueList) {
		return sqlSession.insert("microblogQueueBase.insert_microblogQueue_Batch",microblogQueueList);
	}
	
	/**
	 * 更新(小区博客队列表)信息
	 * @param microblogQueue
	 * @return
	 */
	@Override
	public int updateMicroblogQueue(MicroblogQueue microblogQueue){
		return sqlSession.update("microblogQueueBase.update_microblogQueue", microblogQueue);
	}
	/**
	 * 批量更新(小区博客队列表)信息
	 * @param microblogQueueList
	 * @return
	 */
	@Override
	public int updateMicroblogQueueBatch(List<MicroblogQueue> microblogQueueList) {
		return sqlSession.update("microblogQueueBase.update_microblogQueue_Batch", microblogQueueList);
	}
	
	/**
	 * 根据序列号删除(小区博客队列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogQueueLogic(java.math.BigInteger id){
		MicroblogQueue microblogQueue = new MicroblogQueue();
		microblogQueue.setId(id);
		microblogQueue.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("microblogQueueBase.delete_microblogQueue_Logic",microblogQueue);
	}
	
	/**
	 * 根据Id 批量删除(小区博客队列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogQueueLogicBatch(List<java.math.BigInteger> idList) {
		List<MicroblogQueue> delList = new java.util.ArrayList<MicroblogQueue>();
		for(java.math.BigInteger id:idList){
			MicroblogQueue microblogQueue = new MicroblogQueue();
			microblogQueue.setId(id);
			microblogQueue.setSys0DelState(RecordState_DELETED);
			delList.add(microblogQueue);
		}
		return sqlSession.update("microblogQueueBase.delete_microblogQueue_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区博客队列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogQueue(java.math.BigInteger id){
//		return sqlSession.delete("microblogQueueBase.delete_microblogQueue", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区博客队列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogQueueBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("microblogQueueBase.delete_microblogQueue_Batch", idList);
//	}
	
	
}
