package com.cnfantasia.server.domainbase.flashDealActivity.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;

/**
 * 描述:(秒杀活动表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FlashDealActivityBaseDao extends AbstractBaseDao implements IFlashDealActivityBaseDao{
	/**
	 * 根据条件查询(秒杀活动表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FlashDealActivity> selectFlashDealActivityByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("flashDealActivityBase.select_flashDealActivity",paramMap);
	}
	/**
	 * 按条件分页查询(秒杀活动表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FlashDealActivity> selectFlashDealActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFlashDealActivityCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FlashDealActivity> resMap= sqlSession.selectList("flashDealActivityBase.select_flashDealActivity_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(秒杀活动表)信息
	 * @param id
	 * @return
	 */
	@Override
	public FlashDealActivity selectFlashDealActivityBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("flashDealActivityBase.select_flashDealActivity_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(秒杀活动表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFlashDealActivityCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("flashDealActivityBase.select_flashDealActivity_count", paramMap);
	}
	/**
	 * 往(秒杀活动表)新增一条记录
	 * @param flashDealActivity
	 * @return
	 */
	@Override
	public int insertFlashDealActivity(FlashDealActivity flashDealActivity){
		return sqlSession.insert("flashDealActivityBase.insert_flashDealActivity",flashDealActivity);
	}
	/**
	 * 批量新增(秒杀活动表)信息
	 * @param flashDealActivityList
	 * @return
	 */
	@Override
	public int insertFlashDealActivityBatch(List<FlashDealActivity> flashDealActivityList) {
		return sqlSession.insert("flashDealActivityBase.insert_flashDealActivity_Batch",flashDealActivityList);
	}
	
	/**
	 * 更新(秒杀活动表)信息
	 * @param flashDealActivity
	 * @return
	 */
	@Override
	public int updateFlashDealActivity(FlashDealActivity flashDealActivity){
		return sqlSession.update("flashDealActivityBase.update_flashDealActivity", flashDealActivity);
	}
	/**
	 * 批量更新(秒杀活动表)信息
	 * @param flashDealActivityList
	 * @return
	 */
	@Override
	public int updateFlashDealActivityBatch(List<FlashDealActivity> flashDealActivityList) {
		return sqlSession.update("flashDealActivityBase.update_flashDealActivity_Batch", flashDealActivityList);
	}
	
	/**
	 * 根据序列号删除(秒杀活动表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFlashDealActivityLogic(java.math.BigInteger id){
		FlashDealActivity flashDealActivity = new FlashDealActivity();
		flashDealActivity.setId(id);
		flashDealActivity.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("flashDealActivityBase.delete_flashDealActivity_Logic",flashDealActivity);
	}
	
	/**
	 * 根据Id 批量删除(秒杀活动表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFlashDealActivityLogicBatch(List<java.math.BigInteger> idList) {
		List<FlashDealActivity> delList = new java.util.ArrayList<FlashDealActivity>();
		for(java.math.BigInteger id:idList){
			FlashDealActivity flashDealActivity = new FlashDealActivity();
			flashDealActivity.setId(id);
			flashDealActivity.setSys0DelState(RecordState_DELETED);
			delList.add(flashDealActivity);
		}
		return sqlSession.update("flashDealActivityBase.delete_flashDealActivity_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(秒杀活动表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealActivity(java.math.BigInteger id){
//		return sqlSession.delete("flashDealActivityBase.delete_flashDealActivity", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(秒杀活动表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealActivityBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("flashDealActivityBase.delete_flashDealActivity_Batch", idList);
//	}
	
	
}
