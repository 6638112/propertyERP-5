package com.cnfantasia.server.domainbase.dataChangeRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dataChangeRecord.entity.DataChangeRecord;

/**
 * 描述:(数据变更记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DataChangeRecordBaseDao extends AbstractBaseDao implements IDataChangeRecordBaseDao{
	/**
	 * 根据条件查询(数据变更记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DataChangeRecord> selectDataChangeRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dataChangeRecordBase.select_dataChangeRecord",paramMap);
	}
	/**
	 * 按条件分页查询(数据变更记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DataChangeRecord> selectDataChangeRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDataChangeRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DataChangeRecord> resMap= sqlSession.selectList("dataChangeRecordBase.select_dataChangeRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(数据变更记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public DataChangeRecord selectDataChangeRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dataChangeRecordBase.select_dataChangeRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(数据变更记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDataChangeRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dataChangeRecordBase.select_dataChangeRecord_count", paramMap);
	}
	/**
	 * 往(数据变更记录)新增一条记录
	 * @param dataChangeRecord
	 * @return
	 */
	@Override
	public int insertDataChangeRecord(DataChangeRecord dataChangeRecord){
		return sqlSession.insert("dataChangeRecordBase.insert_dataChangeRecord",dataChangeRecord);
	}
	/**
	 * 批量新增(数据变更记录)信息
	 * @param dataChangeRecordList
	 * @return
	 */
	@Override
	public int insertDataChangeRecordBatch(List<DataChangeRecord> dataChangeRecordList) {
		if (dataChangeRecordList == null || dataChangeRecordList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = dataChangeRecordList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<DataChangeRecord> batchList = dataChangeRecordList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("dataChangeRecordBase.insert_dataChangeRecord_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(数据变更记录)信息
	 * @param dataChangeRecord
	 * @return
	 */
	@Override
	public int updateDataChangeRecord(DataChangeRecord dataChangeRecord){
		return sqlSession.update("dataChangeRecordBase.update_dataChangeRecord", dataChangeRecord);
	}
	/**
	 * 批量更新(数据变更记录)信息
	 * @param dataChangeRecordList
	 * @return
	 */
	@Override
	public int updateDataChangeRecordBatch(List<DataChangeRecord> dataChangeRecordList) {
		if (dataChangeRecordList == null || dataChangeRecordList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("dataChangeRecordBase.update_dataChangeRecord_Batch", dataChangeRecordList);
	}
	
	/**
	 * 根据序列号删除(数据变更记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDataChangeRecordLogic(java.math.BigInteger id){
		DataChangeRecord dataChangeRecord = new DataChangeRecord();
		dataChangeRecord.setId(id);
		dataChangeRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dataChangeRecordBase.delete_dataChangeRecord_Logic",dataChangeRecord);
	}
	 */
	/**
	 * 根据Id 批量删除(数据变更记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDataChangeRecordLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<DataChangeRecord> delList = new java.util.ArrayList<DataChangeRecord>();
		for(java.math.BigInteger id:idList){
			DataChangeRecord dataChangeRecord = new DataChangeRecord();
			dataChangeRecord.setId(id);
			dataChangeRecord.setSys0DelState(RecordState_DELETED);
			delList.add(dataChangeRecord);
		}
		return sqlSession.update("dataChangeRecordBase.delete_dataChangeRecord_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(数据变更记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDataChangeRecord(java.math.BigInteger id){
//		return sqlSession.delete("dataChangeRecordBase.delete_dataChangeRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(数据变更记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDataChangeRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dataChangeRecordBase.delete_dataChangeRecord_Batch", idList);
//	}
	
	
}
