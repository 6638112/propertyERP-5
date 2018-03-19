package com.cnfantasia.server.domainbase.selfActivity.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;

/**
 * 描述:(运营消息推送配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SelfActivityBaseDao extends AbstractBaseDao implements ISelfActivityBaseDao{
	/**
	 * 根据条件查询(运营消息推送配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SelfActivity> selectSelfActivityByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("selfActivityBase.select_selfActivity",paramMap);
	}
	/**
	 * 按条件分页查询(运营消息推送配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SelfActivity> selectSelfActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSelfActivityCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<SelfActivity> resMap= sqlSession.selectList("selfActivityBase.select_selfActivity_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(运营消息推送配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public SelfActivity selectSelfActivityBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("selfActivityBase.select_selfActivity_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSelfActivityCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("selfActivityBase.select_selfActivity_count", paramMap);
	}
	/**
	 * 往(运营消息推送配置表)新增一条记录
	 * @param selfActivity
	 * @return
	 */
	@Override
	public int insertSelfActivity(SelfActivity selfActivity){
		return sqlSession.insert("selfActivityBase.insert_selfActivity",selfActivity);
	}
	/**
	 * 批量新增(运营消息推送配置表)信息
	 * @param selfActivityList
	 * @return
	 */
	@Override
	public int insertSelfActivityBatch(List<SelfActivity> selfActivityList) {
		if (selfActivityList == null || selfActivityList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = selfActivityList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<SelfActivity> batchList = selfActivityList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("selfActivityBase.insert_selfActivity_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(运营消息推送配置表)信息
	 * @param selfActivity
	 * @return
	 */
	@Override
	public int updateSelfActivity(SelfActivity selfActivity){
		return sqlSession.update("selfActivityBase.update_selfActivity", selfActivity);
	}
	/**
	 * 批量更新(运营消息推送配置表)信息
	 * @param selfActivityList
	 * @return
	 */
	@Override
	public int updateSelfActivityBatch(List<SelfActivity> selfActivityList) {
		if (selfActivityList == null || selfActivityList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("selfActivityBase.update_selfActivity_Batch", selfActivityList);
	}
	
	/**
	 * 根据序列号删除(运营消息推送配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityLogic(java.math.BigInteger id){
		SelfActivity selfActivity = new SelfActivity();
		selfActivity.setId(id);
		selfActivity.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("selfActivityBase.delete_selfActivity_Logic",selfActivity);
	}
	
	/**
	 * 根据Id 批量删除(运营消息推送配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<SelfActivity> delList = new java.util.ArrayList<SelfActivity>();
		for(java.math.BigInteger id:idList){
			SelfActivity selfActivity = new SelfActivity();
			selfActivity.setId(id);
			selfActivity.setSys0DelState(RecordState_DELETED);
			delList.add(selfActivity);
		}
		return sqlSession.update("selfActivityBase.delete_selfActivity_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(运营消息推送配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivity(java.math.BigInteger id){
//		return sqlSession.delete("selfActivityBase.delete_selfActivity", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运营消息推送配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivityBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("selfActivityBase.delete_selfActivity_Batch", idList);
//	}
	
	
}
