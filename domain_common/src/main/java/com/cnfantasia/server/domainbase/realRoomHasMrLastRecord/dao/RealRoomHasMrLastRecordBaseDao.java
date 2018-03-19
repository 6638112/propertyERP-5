package com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;

/**
 * 描述:(房间最后一次抄表读数) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RealRoomHasMrLastRecordBaseDao extends AbstractBaseDao implements IRealRoomHasMrLastRecordBaseDao{
	/**
	 * 根据条件查询(房间最后一次抄表读数)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealRoomHasMrLastRecord> selectRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("realRoomHasMrLastRecordBase.select_realRoomHasMrLastRecord",paramMap);
	}
	/**
	 * 按条件分页查询(房间最后一次抄表读数)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealRoomHasMrLastRecord> selectRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRealRoomHasMrLastRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RealRoomHasMrLastRecord> resMap= sqlSession.selectList("realRoomHasMrLastRecordBase.select_realRoomHasMrLastRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(房间最后一次抄表读数)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealRoomHasMrLastRecord selectRealRoomHasMrLastRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("realRoomHasMrLastRecordBase.select_realRoomHasMrLastRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(房间最后一次抄表读数)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRealRoomHasMrLastRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("realRoomHasMrLastRecordBase.select_realRoomHasMrLastRecord_count", paramMap);
	}
	/**
	 * 往(房间最后一次抄表读数)新增一条记录
	 * @param realRoomHasMrLastRecord
	 * @return
	 */
	@Override
	public int insertRealRoomHasMrLastRecord(RealRoomHasMrLastRecord realRoomHasMrLastRecord){
		return sqlSession.insert("realRoomHasMrLastRecordBase.insert_realRoomHasMrLastRecord",realRoomHasMrLastRecord);
	}
	/**
	 * 批量新增(房间最后一次抄表读数)信息
	 * @param realRoomHasMrLastRecordList
	 * @return
	 */
	@Override
	public int insertRealRoomHasMrLastRecordBatch(List<RealRoomHasMrLastRecord> realRoomHasMrLastRecordList) {
		if (realRoomHasMrLastRecordList == null || realRoomHasMrLastRecordList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = realRoomHasMrLastRecordList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<RealRoomHasMrLastRecord> batchList = realRoomHasMrLastRecordList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("realRoomHasMrLastRecordBase.insert_realRoomHasMrLastRecord_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(房间最后一次抄表读数)信息
	 * @param realRoomHasMrLastRecord
	 * @return
	 */
	@Override
	public int updateRealRoomHasMrLastRecord(RealRoomHasMrLastRecord realRoomHasMrLastRecord){
		return sqlSession.update("realRoomHasMrLastRecordBase.update_realRoomHasMrLastRecord", realRoomHasMrLastRecord);
	}
	/**
	 * 批量更新(房间最后一次抄表读数)信息
	 * @param realRoomHasMrLastRecordList
	 * @return
	 */
	@Override
	public int updateRealRoomHasMrLastRecordBatch(List<RealRoomHasMrLastRecord> realRoomHasMrLastRecordList) {
		if (realRoomHasMrLastRecordList == null || realRoomHasMrLastRecordList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("realRoomHasMrLastRecordBase.update_realRoomHasMrLastRecord_Batch", realRoomHasMrLastRecordList);
	}
	
	/**
	 * 根据序列号删除(房间最后一次抄表读数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasMrLastRecordLogic(java.math.BigInteger id){
		RealRoomHasMrLastRecord realRoomHasMrLastRecord = new RealRoomHasMrLastRecord();
		realRoomHasMrLastRecord.setId(id);
		realRoomHasMrLastRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("realRoomHasMrLastRecordBase.delete_realRoomHasMrLastRecord_Logic",realRoomHasMrLastRecord);
	}
	
	/**
	 * 根据Id 批量删除(房间最后一次抄表读数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasMrLastRecordLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<RealRoomHasMrLastRecord> delList = new java.util.ArrayList<RealRoomHasMrLastRecord>();
		for(java.math.BigInteger id:idList){
			RealRoomHasMrLastRecord realRoomHasMrLastRecord = new RealRoomHasMrLastRecord();
			realRoomHasMrLastRecord.setId(id);
			realRoomHasMrLastRecord.setSys0DelState(RecordState_DELETED);
			delList.add(realRoomHasMrLastRecord);
		}
		return sqlSession.update("realRoomHasMrLastRecordBase.delete_realRoomHasMrLastRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(房间最后一次抄表读数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasMrLastRecord(java.math.BigInteger id){
//		return sqlSession.delete("realRoomHasMrLastRecordBase.delete_realRoomHasMrLastRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间最后一次抄表读数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasMrLastRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("realRoomHasMrLastRecordBase.delete_realRoomHasMrLastRecord_Batch", idList);
//	}
	
	
}
