package com.cnfantasia.server.domainbase.prizeRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * 描述:(中奖记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeRecordBaseDao extends AbstractBaseDao implements IPrizeRecordBaseDao{
	/**
	 * 根据条件查询(中奖记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRecord> selectPrizeRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeRecordBase.select_prizeRecord",paramMap);
	}
	/**
	 * 按条件分页查询(中奖记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRecord> selectPrizeRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeRecord> resMap= sqlSession.selectList("prizeRecordBase.select_prizeRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(中奖记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRecord selectPrizeRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeRecordBase.select_prizeRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(中奖记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeRecordBase.select_prizeRecord_count", paramMap);
	}
	/**
	 * 往(中奖记录)新增一条记录
	 * @param prizeRecord
	 * @return
	 */
	@Override
	public int insertPrizeRecord(PrizeRecord prizeRecord){
		return sqlSession.insert("prizeRecordBase.insert_prizeRecord",prizeRecord);
	}
	/**
	 * 批量新增(中奖记录)信息
	 * @param prizeRecordList
	 * @return
	 */
	@Override
	public int insertPrizeRecordBatch(List<PrizeRecord> prizeRecordList) {
		return sqlSession.insert("prizeRecordBase.insert_prizeRecord_Batch",prizeRecordList);
	}
	
	/**
	 * 更新(中奖记录)信息
	 * @param prizeRecord
	 * @return
	 */
	@Override
	public int updatePrizeRecord(PrizeRecord prizeRecord){
		return sqlSession.update("prizeRecordBase.update_prizeRecord", prizeRecord);
	}
	/**
	 * 批量更新(中奖记录)信息
	 * @param prizeRecordList
	 * @return
	 */
	@Override
	public int updatePrizeRecordBatch(List<PrizeRecord> prizeRecordList) {
		return sqlSession.update("prizeRecordBase.update_prizeRecord_Batch", prizeRecordList);
	}
	
	/**
	 * 根据序列号删除(中奖记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordLogic(java.math.BigInteger id){
		PrizeRecord prizeRecord = new PrizeRecord();
		prizeRecord.setId(id);
		prizeRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeRecordBase.delete_prizeRecord_Logic",prizeRecord);
	}
	
	/**
	 * 根据Id 批量删除(中奖记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeRecord> delList = new java.util.ArrayList<PrizeRecord>();
		for(java.math.BigInteger id:idList){
			PrizeRecord prizeRecord = new PrizeRecord();
			prizeRecord.setId(id);
			prizeRecord.setSys0DelState(RecordState_DELETED);
			delList.add(prizeRecord);
		}
		return sqlSession.update("prizeRecordBase.delete_prizeRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(中奖记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecord(java.math.BigInteger id){
//		return sqlSession.delete("prizeRecordBase.delete_prizeRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(中奖记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeRecordBase.delete_prizeRecord_Batch", idList);
//	}
	
	
}
