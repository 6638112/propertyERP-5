package com.cnfantasia.server.domainbase.parkingRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;

/**
 * 描述:(停车记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ParkingRecordBaseDao extends AbstractBaseDao implements IParkingRecordBaseDao{
	/**
	 * 根据条件查询(停车记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ParkingRecord> selectParkingRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("parkingRecordBase.select_parkingRecord",paramMap);
	}
	/**
	 * 按条件分页查询(停车记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ParkingRecord> selectParkingRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectParkingRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ParkingRecord> resMap= sqlSession.selectList("parkingRecordBase.select_parkingRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(停车记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ParkingRecord selectParkingRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("parkingRecordBase.select_parkingRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(停车记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectParkingRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("parkingRecordBase.select_parkingRecord_count", paramMap);
	}
	/**
	 * 往(停车记录表)新增一条记录
	 * @param parkingRecord
	 * @return
	 */
	@Override
	public int insertParkingRecord(ParkingRecord parkingRecord){
		return sqlSession.insert("parkingRecordBase.insert_parkingRecord",parkingRecord);
	}
	/**
	 * 批量新增(停车记录表)信息
	 * @param parkingRecordList
	 * @return
	 */
	@Override
	public int insertParkingRecordBatch(List<ParkingRecord> parkingRecordList) {
		return sqlSession.insert("parkingRecordBase.insert_parkingRecord_Batch",parkingRecordList);
	}
	
	/**
	 * 更新(停车记录表)信息
	 * @param parkingRecord
	 * @return
	 */
	@Override
	public int updateParkingRecord(ParkingRecord parkingRecord){
		return sqlSession.update("parkingRecordBase.update_parkingRecord", parkingRecord);
	}
	/**
	 * 批量更新(停车记录表)信息
	 * @param parkingRecordList
	 * @return
	 */
	@Override
	public int updateParkingRecordBatch(List<ParkingRecord> parkingRecordList) {
		return sqlSession.update("parkingRecordBase.update_parkingRecord_Batch", parkingRecordList);
	}
	
	/**
	 * 根据序列号删除(停车记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteParkingRecordLogic(java.math.BigInteger id){
		ParkingRecord parkingRecord = new ParkingRecord();
		parkingRecord.setId(id);
		parkingRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("parkingRecordBase.delete_parkingRecord_Logic",parkingRecord);
	}
	
	/**
	 * 根据Id 批量删除(停车记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteParkingRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<ParkingRecord> delList = new java.util.ArrayList<ParkingRecord>();
		for(java.math.BigInteger id:idList){
			ParkingRecord parkingRecord = new ParkingRecord();
			parkingRecord.setId(id);
			parkingRecord.setSys0DelState(RecordState_DELETED);
			delList.add(parkingRecord);
		}
		return sqlSession.update("parkingRecordBase.delete_parkingRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(停车记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteParkingRecord(java.math.BigInteger id){
//		return sqlSession.delete("parkingRecordBase.delete_parkingRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(停车记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteParkingRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("parkingRecordBase.delete_parkingRecord_Batch", idList);
//	}
	
	
}
