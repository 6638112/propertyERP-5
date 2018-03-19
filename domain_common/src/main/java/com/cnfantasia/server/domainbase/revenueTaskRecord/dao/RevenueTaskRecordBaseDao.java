package com.cnfantasia.server.domainbase.revenueTaskRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueTaskRecord.entity.RevenueTaskRecord;

/**
 * 描述:(定时同步收益额记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RevenueTaskRecordBaseDao extends AbstractBaseDao implements IRevenueTaskRecordBaseDao{
	/**
	 * 根据条件查询(定时同步收益额记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueTaskRecord> selectRevenueTaskRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("revenueTaskRecordBase.select_revenueTaskRecord",paramMap);
	}
	/**
	 * 按条件分页查询(定时同步收益额记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueTaskRecord> selectRevenueTaskRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRevenueTaskRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RevenueTaskRecord> resMap= sqlSession.selectList("revenueTaskRecordBase.select_revenueTaskRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(定时同步收益额记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueTaskRecord selectRevenueTaskRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("revenueTaskRecordBase.select_revenueTaskRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(定时同步收益额记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRevenueTaskRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("revenueTaskRecordBase.select_revenueTaskRecord_count", paramMap);
	}
	/**
	 * 往(定时同步收益额记录表)新增一条记录
	 * @param revenueTaskRecord
	 * @return
	 */
	@Override
	public int insertRevenueTaskRecord(RevenueTaskRecord revenueTaskRecord){
		return sqlSession.insert("revenueTaskRecordBase.insert_revenueTaskRecord",revenueTaskRecord);
	}
	/**
	 * 批量新增(定时同步收益额记录表)信息
	 * @param revenueTaskRecordList
	 * @return
	 */
	@Override
	public int insertRevenueTaskRecordBatch(List<RevenueTaskRecord> revenueTaskRecordList) {
		return sqlSession.insert("revenueTaskRecordBase.insert_revenueTaskRecord_Batch",revenueTaskRecordList);
	}
	
	/**
	 * 更新(定时同步收益额记录表)信息
	 * @param revenueTaskRecord
	 * @return
	 */
	@Override
	public int updateRevenueTaskRecord(RevenueTaskRecord revenueTaskRecord){
		return sqlSession.update("revenueTaskRecordBase.update_revenueTaskRecord", revenueTaskRecord);
	}
	/**
	 * 批量更新(定时同步收益额记录表)信息
	 * @param revenueTaskRecordList
	 * @return
	 */
	@Override
	public int updateRevenueTaskRecordBatch(List<RevenueTaskRecord> revenueTaskRecordList) {
		return sqlSession.update("revenueTaskRecordBase.update_revenueTaskRecord_Batch", revenueTaskRecordList);
	}
	
	/**
	 * 根据序列号删除(定时同步收益额记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueTaskRecordLogic(java.math.BigInteger id){
		RevenueTaskRecord revenueTaskRecord = new RevenueTaskRecord();
		revenueTaskRecord.setId(id);
		revenueTaskRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("revenueTaskRecordBase.delete_revenueTaskRecord_Logic",revenueTaskRecord);
	}
	
	/**
	 * 根据Id 批量删除(定时同步收益额记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueTaskRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<RevenueTaskRecord> delList = new java.util.ArrayList<RevenueTaskRecord>();
		for(java.math.BigInteger id:idList){
			RevenueTaskRecord revenueTaskRecord = new RevenueTaskRecord();
			revenueTaskRecord.setId(id);
			revenueTaskRecord.setSys0DelState(RecordState_DELETED);
			delList.add(revenueTaskRecord);
		}
		return sqlSession.update("revenueTaskRecordBase.delete_revenueTaskRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(定时同步收益额记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueTaskRecord(java.math.BigInteger id){
//		return sqlSession.delete("revenueTaskRecordBase.delete_revenueTaskRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(定时同步收益额记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueTaskRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("revenueTaskRecordBase.delete_revenueTaskRecord_Batch", idList);
//	}
	
	
}
