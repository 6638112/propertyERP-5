package com.cnfantasia.server.domainbase.limitBuyActivity.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.limitBuyActivity.entity.LimitBuyActivity;

/**
 * 描述:(限时促销) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LimitBuyActivityBaseDao extends AbstractBaseDao implements ILimitBuyActivityBaseDao{
	/**
	 * 根据条件查询(限时促销)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LimitBuyActivity> selectLimitBuyActivityByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("limitBuyActivityBase.select_limitBuyActivity",paramMap);
	}
	/**
	 * 按条件分页查询(限时促销)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LimitBuyActivity> selectLimitBuyActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLimitBuyActivityCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LimitBuyActivity> resMap= sqlSession.selectList("limitBuyActivityBase.select_limitBuyActivity_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(限时促销)信息
	 * @param id
	 * @return
	 */
	@Override
	public LimitBuyActivity selectLimitBuyActivityBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("limitBuyActivityBase.select_limitBuyActivity_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(限时促销)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLimitBuyActivityCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("limitBuyActivityBase.select_limitBuyActivity_count", paramMap);
	}
	/**
	 * 往(限时促销)新增一条记录
	 * @param limitBuyActivity
	 * @return
	 */
	@Override
	public int insertLimitBuyActivity(LimitBuyActivity limitBuyActivity){
		return sqlSession.insert("limitBuyActivityBase.insert_limitBuyActivity",limitBuyActivity);
	}
	/**
	 * 批量新增(限时促销)信息
	 * @param limitBuyActivityList
	 * @return
	 */
	@Override
	public int insertLimitBuyActivityBatch(List<LimitBuyActivity> limitBuyActivityList) {
		if (limitBuyActivityList == null || limitBuyActivityList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = limitBuyActivityList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<LimitBuyActivity> batchList = limitBuyActivityList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("limitBuyActivityBase.insert_limitBuyActivity_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(限时促销)信息
	 * @param limitBuyActivity
	 * @return
	 */
	@Override
	public int updateLimitBuyActivity(LimitBuyActivity limitBuyActivity){
		return sqlSession.update("limitBuyActivityBase.update_limitBuyActivity", limitBuyActivity);
	}
	/**
	 * 批量更新(限时促销)信息
	 * @param limitBuyActivityList
	 * @return
	 */
	@Override
	public int updateLimitBuyActivityBatch(List<LimitBuyActivity> limitBuyActivityList) {
		if (limitBuyActivityList == null || limitBuyActivityList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("limitBuyActivityBase.update_limitBuyActivity_Batch", limitBuyActivityList);
	}
	
	/**
	 * 根据序列号删除(限时促销)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLimitBuyActivityLogic(java.math.BigInteger id){
		LimitBuyActivity limitBuyActivity = new LimitBuyActivity();
		limitBuyActivity.setId(id);
		limitBuyActivity.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("limitBuyActivityBase.delete_limitBuyActivity_Logic",limitBuyActivity);
	}
	
	/**
	 * 根据Id 批量删除(限时促销)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLimitBuyActivityLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<LimitBuyActivity> delList = new java.util.ArrayList<LimitBuyActivity>();
		for(java.math.BigInteger id:idList){
			LimitBuyActivity limitBuyActivity = new LimitBuyActivity();
			limitBuyActivity.setId(id);
			limitBuyActivity.setSys0DelState(RecordState_DELETED);
			delList.add(limitBuyActivity);
		}
		return sqlSession.update("limitBuyActivityBase.delete_limitBuyActivity_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(限时促销)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLimitBuyActivity(java.math.BigInteger id){
//		return sqlSession.delete("limitBuyActivityBase.delete_limitBuyActivity", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(限时促销)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLimitBuyActivityBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("limitBuyActivityBase.delete_limitBuyActivity_Batch", idList);
//	}
	
	
}
