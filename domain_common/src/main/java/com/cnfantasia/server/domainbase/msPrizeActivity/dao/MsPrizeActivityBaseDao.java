package com.cnfantasia.server.domainbase.msPrizeActivity.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;

/**
 * 描述:(抽奖活动表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MsPrizeActivityBaseDao extends AbstractBaseDao implements IMsPrizeActivityBaseDao{
	/**
	 * 根据条件查询(抽奖活动表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeActivity> selectMsPrizeActivityByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("msPrizeActivityBase.select_msPrizeActivity",paramMap);
	}
	/**
	 * 按条件分页查询(抽奖活动表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeActivity> selectMsPrizeActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMsPrizeActivityCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MsPrizeActivity> resMap= sqlSession.selectList("msPrizeActivityBase.select_msPrizeActivity_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抽奖活动表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeActivity selectMsPrizeActivityBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("msPrizeActivityBase.select_msPrizeActivity_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖活动表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMsPrizeActivityCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("msPrizeActivityBase.select_msPrizeActivity_count", paramMap);
	}
	/**
	 * 往(抽奖活动表)新增一条记录
	 * @param msPrizeActivity
	 * @return
	 */
	@Override
	public int insertMsPrizeActivity(MsPrizeActivity msPrizeActivity){
		return sqlSession.insert("msPrizeActivityBase.insert_msPrizeActivity",msPrizeActivity);
	}
	/**
	 * 批量新增(抽奖活动表)信息
	 * @param msPrizeActivityList
	 * @return
	 */
	@Override
	public int insertMsPrizeActivityBatch(List<MsPrizeActivity> msPrizeActivityList) {
		return sqlSession.insert("msPrizeActivityBase.insert_msPrizeActivity_Batch",msPrizeActivityList);
	}
	
	/**
	 * 更新(抽奖活动表)信息
	 * @param msPrizeActivity
	 * @return
	 */
	@Override
	public int updateMsPrizeActivity(MsPrizeActivity msPrizeActivity){
		return sqlSession.update("msPrizeActivityBase.update_msPrizeActivity", msPrizeActivity);
	}
	/**
	 * 批量更新(抽奖活动表)信息
	 * @param msPrizeActivityList
	 * @return
	 */
	@Override
	public int updateMsPrizeActivityBatch(List<MsPrizeActivity> msPrizeActivityList) {
		return sqlSession.update("msPrizeActivityBase.update_msPrizeActivity_Batch", msPrizeActivityList);
	}
	
	/**
	 * 根据序列号删除(抽奖活动表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActivityLogic(java.math.BigInteger id){
		MsPrizeActivity msPrizeActivity = new MsPrizeActivity();
		msPrizeActivity.setId(id);
		msPrizeActivity.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("msPrizeActivityBase.delete_msPrizeActivity_Logic",msPrizeActivity);
	}
	
	/**
	 * 根据Id 批量删除(抽奖活动表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActivityLogicBatch(List<java.math.BigInteger> idList) {
		List<MsPrizeActivity> delList = new java.util.ArrayList<MsPrizeActivity>();
		for(java.math.BigInteger id:idList){
			MsPrizeActivity msPrizeActivity = new MsPrizeActivity();
			msPrizeActivity.setId(id);
			msPrizeActivity.setSys0DelState(RecordState_DELETED);
			delList.add(msPrizeActivity);
		}
		return sqlSession.update("msPrizeActivityBase.delete_msPrizeActivity_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖活动表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActivity(java.math.BigInteger id){
//		return sqlSession.delete("msPrizeActivityBase.delete_msPrizeActivity", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖活动表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActivityBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("msPrizeActivityBase.delete_msPrizeActivity_Batch", idList);
//	}
	
	
}
