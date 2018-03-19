package com.cnfantasia.server.domainbase.lotteryDrawRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lotteryDrawRecord.entity.LotteryDrawRecord;

/**
 * 描述:(幸运抽奖记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LotteryDrawRecordBaseDao extends AbstractBaseDao implements ILotteryDrawRecordBaseDao{
	/**
	 * 根据条件查询(幸运抽奖记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LotteryDrawRecord> selectLotteryDrawRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("lotteryDrawRecordBase.select_lotteryDrawRecord",paramMap);
	}
	/**
	 * 按条件分页查询(幸运抽奖记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LotteryDrawRecord> selectLotteryDrawRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLotteryDrawRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LotteryDrawRecord> resMap= sqlSession.selectList("lotteryDrawRecordBase.select_lotteryDrawRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(幸运抽奖记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LotteryDrawRecord selectLotteryDrawRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("lotteryDrawRecordBase.select_lotteryDrawRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(幸运抽奖记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLotteryDrawRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("lotteryDrawRecordBase.select_lotteryDrawRecord_count", paramMap);
	}
	/**
	 * 往(幸运抽奖记录表)新增一条记录
	 * @param lotteryDrawRecord
	 * @return
	 */
	@Override
	public int insertLotteryDrawRecord(LotteryDrawRecord lotteryDrawRecord){
		return sqlSession.insert("lotteryDrawRecordBase.insert_lotteryDrawRecord",lotteryDrawRecord);
	}
	/**
	 * 批量新增(幸运抽奖记录表)信息
	 * @param lotteryDrawRecordList
	 * @return
	 */
	@Override
	public int insertLotteryDrawRecordBatch(List<LotteryDrawRecord> lotteryDrawRecordList) {
		return sqlSession.insert("lotteryDrawRecordBase.insert_lotteryDrawRecord_Batch",lotteryDrawRecordList);
	}
	
	/**
	 * 更新(幸运抽奖记录表)信息
	 * @param lotteryDrawRecord
	 * @return
	 */
	@Override
	public int updateLotteryDrawRecord(LotteryDrawRecord lotteryDrawRecord){
		return sqlSession.update("lotteryDrawRecordBase.update_lotteryDrawRecord", lotteryDrawRecord);
	}
	/**
	 * 批量更新(幸运抽奖记录表)信息
	 * @param lotteryDrawRecordList
	 * @return
	 */
	@Override
	public int updateLotteryDrawRecordBatch(List<LotteryDrawRecord> lotteryDrawRecordList) {
		return sqlSession.update("lotteryDrawRecordBase.update_lotteryDrawRecord_Batch", lotteryDrawRecordList);
	}
	
	/**
	 * 根据序列号删除(幸运抽奖记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawRecordLogic(java.math.BigInteger id){
		LotteryDrawRecord lotteryDrawRecord = new LotteryDrawRecord();
		lotteryDrawRecord.setId(id);
		lotteryDrawRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("lotteryDrawRecordBase.delete_lotteryDrawRecord_Logic",lotteryDrawRecord);
	}
	
	/**
	 * 根据Id 批量删除(幸运抽奖记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<LotteryDrawRecord> delList = new java.util.ArrayList<LotteryDrawRecord>();
		for(java.math.BigInteger id:idList){
			LotteryDrawRecord lotteryDrawRecord = new LotteryDrawRecord();
			lotteryDrawRecord.setId(id);
			lotteryDrawRecord.setSys0DelState(RecordState_DELETED);
			delList.add(lotteryDrawRecord);
		}
		return sqlSession.update("lotteryDrawRecordBase.delete_lotteryDrawRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(幸运抽奖记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawRecord(java.math.BigInteger id){
//		return sqlSession.delete("lotteryDrawRecordBase.delete_lotteryDrawRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(幸运抽奖记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("lotteryDrawRecordBase.delete_lotteryDrawRecord_Batch", idList);
//	}
	
	
}
