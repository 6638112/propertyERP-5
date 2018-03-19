package com.cnfantasia.server.domainbase.bcOfferRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;

/**
 * 描述:(发盘记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcOfferRecordBaseDao extends AbstractBaseDao implements IBcOfferRecordBaseDao{
	/**
	 * 根据条件查询(发盘记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcOfferRecord> selectBcOfferRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcOfferRecordBase.select_bcOfferRecord",paramMap);
	}
	/**
	 * 按条件分页查询(发盘记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcOfferRecord> selectBcOfferRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcOfferRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcOfferRecord> resMap= sqlSession.selectList("bcOfferRecordBase.select_bcOfferRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(发盘记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcOfferRecord selectBcOfferRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcOfferRecordBase.select_bcOfferRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(发盘记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcOfferRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcOfferRecordBase.select_bcOfferRecord_count", paramMap);
	}
	/**
	 * 往(发盘记录)新增一条记录
	 * @param bcOfferRecord
	 * @return
	 */
	@Override
	public int insertBcOfferRecord(BcOfferRecord bcOfferRecord){
		return sqlSession.insert("bcOfferRecordBase.insert_bcOfferRecord",bcOfferRecord);
	}
	/**
	 * 批量新增(发盘记录)信息
	 * @param bcOfferRecordList
	 * @return
	 */
	@Override
	public int insertBcOfferRecordBatch(List<BcOfferRecord> bcOfferRecordList) {
		if (bcOfferRecordList == null || bcOfferRecordList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcOfferRecordList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcOfferRecord> batchList = bcOfferRecordList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcOfferRecordBase.insert_bcOfferRecord_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(发盘记录)信息
	 * @param bcOfferRecord
	 * @return
	 */
	@Override
	public int updateBcOfferRecord(BcOfferRecord bcOfferRecord){
		return sqlSession.update("bcOfferRecordBase.update_bcOfferRecord", bcOfferRecord);
	}
	/**
	 * 批量更新(发盘记录)信息
	 * @param bcOfferRecordList
	 * @return
	 */
	@Override
	public int updateBcOfferRecordBatch(List<BcOfferRecord> bcOfferRecordList) {
		if (bcOfferRecordList == null || bcOfferRecordList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcOfferRecordBase.update_bcOfferRecord_Batch", bcOfferRecordList);
	}
	
	/**
	 * 根据序列号删除(发盘记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteBcOfferRecordLogic(java.math.BigInteger id){
		BcOfferRecord bcOfferRecord = new BcOfferRecord();
		bcOfferRecord.setId(id);
		bcOfferRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcOfferRecordBase.delete_bcOfferRecord_Logic",bcOfferRecord);
	}
	 */
	/**
	 * 根据Id 批量删除(发盘记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteBcOfferRecordLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcOfferRecord> delList = new java.util.ArrayList<BcOfferRecord>();
		for(java.math.BigInteger id:idList){
			BcOfferRecord bcOfferRecord = new BcOfferRecord();
			bcOfferRecord.setId(id);
			bcOfferRecord.setSys0DelState(RecordState_DELETED);
			delList.add(bcOfferRecord);
		}
		return sqlSession.update("bcOfferRecordBase.delete_bcOfferRecord_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(发盘记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcOfferRecord(java.math.BigInteger id){
//		return sqlSession.delete("bcOfferRecordBase.delete_bcOfferRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(发盘记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcOfferRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcOfferRecordBase.delete_bcOfferRecord_Batch", idList);
//	}
	
	
}
