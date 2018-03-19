package com.cnfantasia.server.domainbase.pointSourceRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointSourceRecord.entity.PointSourceRecord;

/**
 * 描述:(积分来源记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PointSourceRecordBaseDao extends AbstractBaseDao implements IPointSourceRecordBaseDao{
	/**
	 * 根据条件查询(积分来源记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PointSourceRecord> selectPointSourceRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("pointSourceRecordBase.select_pointSourceRecord",paramMap);
	}
	/**
	 * 按条件分页查询(积分来源记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PointSourceRecord> selectPointSourceRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPointSourceRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PointSourceRecord> resMap= sqlSession.selectList("pointSourceRecordBase.select_pointSourceRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(积分来源记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PointSourceRecord selectPointSourceRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("pointSourceRecordBase.select_pointSourceRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(积分来源记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPointSourceRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("pointSourceRecordBase.select_pointSourceRecord_count", paramMap);
	}
	/**
	 * 往(积分来源记录表)新增一条记录
	 * @param pointSourceRecord
	 * @return
	 */
	@Override
	public int insertPointSourceRecord(PointSourceRecord pointSourceRecord){
		return sqlSession.insert("pointSourceRecordBase.insert_pointSourceRecord",pointSourceRecord);
	}
	/**
	 * 批量新增(积分来源记录表)信息
	 * @param pointSourceRecordList
	 * @return
	 */
	@Override
	public int insertPointSourceRecordBatch(List<PointSourceRecord> pointSourceRecordList) {
		return sqlSession.insert("pointSourceRecordBase.insert_pointSourceRecord_Batch",pointSourceRecordList);
	}
	
	/**
	 * 更新(积分来源记录表)信息
	 * @param pointSourceRecord
	 * @return
	 */
	@Override
	public int updatePointSourceRecord(PointSourceRecord pointSourceRecord){
		return sqlSession.update("pointSourceRecordBase.update_pointSourceRecord", pointSourceRecord);
	}
	/**
	 * 批量更新(积分来源记录表)信息
	 * @param pointSourceRecordList
	 * @return
	 */
	@Override
	public int updatePointSourceRecordBatch(List<PointSourceRecord> pointSourceRecordList) {
		return sqlSession.update("pointSourceRecordBase.update_pointSourceRecord_Batch", pointSourceRecordList);
	}
	
	/**
	 * 根据序列号删除(积分来源记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePointSourceRecordLogic(java.math.BigInteger id){
		PointSourceRecord pointSourceRecord = new PointSourceRecord();
		pointSourceRecord.setId(id);
		pointSourceRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("pointSourceRecordBase.delete_pointSourceRecord_Logic",pointSourceRecord);
	}
	
	/**
	 * 根据Id 批量删除(积分来源记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePointSourceRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<PointSourceRecord> delList = new java.util.ArrayList<PointSourceRecord>();
		for(java.math.BigInteger id:idList){
			PointSourceRecord pointSourceRecord = new PointSourceRecord();
			pointSourceRecord.setId(id);
			pointSourceRecord.setSys0DelState(RecordState_DELETED);
			delList.add(pointSourceRecord);
		}
		return sqlSession.update("pointSourceRecordBase.delete_pointSourceRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(积分来源记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePointSourceRecord(java.math.BigInteger id){
//		return sqlSession.delete("pointSourceRecordBase.delete_pointSourceRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(积分来源记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePointSourceRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("pointSourceRecordBase.delete_pointSourceRecord_Batch", idList);
//	}
	
	
}
