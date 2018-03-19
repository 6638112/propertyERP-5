package com.cnfantasia.server.domainbase.flashDealBuyRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;

/**
 * 描述:(秒杀活动购买记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FlashDealBuyRecordBaseDao extends AbstractBaseDao implements IFlashDealBuyRecordBaseDao{
	/**
	 * 根据条件查询(秒杀活动购买记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FlashDealBuyRecord> selectFlashDealBuyRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("flashDealBuyRecordBase.select_flashDealBuyRecord",paramMap);
	}
	/**
	 * 按条件分页查询(秒杀活动购买记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FlashDealBuyRecord> selectFlashDealBuyRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFlashDealBuyRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FlashDealBuyRecord> resMap= sqlSession.selectList("flashDealBuyRecordBase.select_flashDealBuyRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(秒杀活动购买记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public FlashDealBuyRecord selectFlashDealBuyRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("flashDealBuyRecordBase.select_flashDealBuyRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(秒杀活动购买记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFlashDealBuyRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("flashDealBuyRecordBase.select_flashDealBuyRecord_count", paramMap);
	}
	/**
	 * 往(秒杀活动购买记录表)新增一条记录
	 * @param flashDealBuyRecord
	 * @return
	 */
	@Override
	public int insertFlashDealBuyRecord(FlashDealBuyRecord flashDealBuyRecord){
		return sqlSession.insert("flashDealBuyRecordBase.insert_flashDealBuyRecord",flashDealBuyRecord);
	}
	/**
	 * 批量新增(秒杀活动购买记录表)信息
	 * @param flashDealBuyRecordList
	 * @return
	 */
	@Override
	public int insertFlashDealBuyRecordBatch(List<FlashDealBuyRecord> flashDealBuyRecordList) {
		return sqlSession.insert("flashDealBuyRecordBase.insert_flashDealBuyRecord_Batch",flashDealBuyRecordList);
	}
	
	/**
	 * 更新(秒杀活动购买记录表)信息
	 * @param flashDealBuyRecord
	 * @return
	 */
	@Override
	public int updateFlashDealBuyRecord(FlashDealBuyRecord flashDealBuyRecord){
		return sqlSession.update("flashDealBuyRecordBase.update_flashDealBuyRecord", flashDealBuyRecord);
	}
	/**
	 * 批量更新(秒杀活动购买记录表)信息
	 * @param flashDealBuyRecordList
	 * @return
	 */
	@Override
	public int updateFlashDealBuyRecordBatch(List<FlashDealBuyRecord> flashDealBuyRecordList) {
		return sqlSession.update("flashDealBuyRecordBase.update_flashDealBuyRecord_Batch", flashDealBuyRecordList);
	}
	
	/**
	 * 根据序列号删除(秒杀活动购买记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFlashDealBuyRecordLogic(java.math.BigInteger id){
		FlashDealBuyRecord flashDealBuyRecord = new FlashDealBuyRecord();
		flashDealBuyRecord.setId(id);
		flashDealBuyRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("flashDealBuyRecordBase.delete_flashDealBuyRecord_Logic",flashDealBuyRecord);
	}
	
	/**
	 * 根据Id 批量删除(秒杀活动购买记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFlashDealBuyRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<FlashDealBuyRecord> delList = new java.util.ArrayList<FlashDealBuyRecord>();
		for(java.math.BigInteger id:idList){
			FlashDealBuyRecord flashDealBuyRecord = new FlashDealBuyRecord();
			flashDealBuyRecord.setId(id);
			flashDealBuyRecord.setSys0DelState(RecordState_DELETED);
			delList.add(flashDealBuyRecord);
		}
		return sqlSession.update("flashDealBuyRecordBase.delete_flashDealBuyRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(秒杀活动购买记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealBuyRecord(java.math.BigInteger id){
//		return sqlSession.delete("flashDealBuyRecordBase.delete_flashDealBuyRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(秒杀活动购买记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealBuyRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("flashDealBuyRecordBase.delete_flashDealBuyRecord_Batch", idList);
//	}
	
	
}
