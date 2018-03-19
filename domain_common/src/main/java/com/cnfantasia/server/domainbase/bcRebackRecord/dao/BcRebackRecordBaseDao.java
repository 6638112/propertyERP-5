package com.cnfantasia.server.domainbase.bcRebackRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcRebackRecord.entity.BcRebackRecord;

/**
 * 描述:(回盘记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcRebackRecordBaseDao extends AbstractBaseDao implements IBcRebackRecordBaseDao{
	/**
	 * 根据条件查询(回盘记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcRebackRecord> selectBcRebackRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcRebackRecordBase.select_bcRebackRecord",paramMap);
	}
	/**
	 * 按条件分页查询(回盘记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcRebackRecord> selectBcRebackRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcRebackRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcRebackRecord> resMap= sqlSession.selectList("bcRebackRecordBase.select_bcRebackRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(回盘记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcRebackRecord selectBcRebackRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcRebackRecordBase.select_bcRebackRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(回盘记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcRebackRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcRebackRecordBase.select_bcRebackRecord_count", paramMap);
	}
	/**
	 * 往(回盘记录)新增一条记录
	 * @param bcRebackRecord
	 * @return
	 */
	@Override
	public int insertBcRebackRecord(BcRebackRecord bcRebackRecord){
		return sqlSession.insert("bcRebackRecordBase.insert_bcRebackRecord",bcRebackRecord);
	}
	/**
	 * 批量新增(回盘记录)信息
	 * @param bcRebackRecordList
	 * @return
	 */
	@Override
	public int insertBcRebackRecordBatch(List<BcRebackRecord> bcRebackRecordList) {
		if (bcRebackRecordList == null || bcRebackRecordList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcRebackRecordList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcRebackRecord> batchList = bcRebackRecordList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcRebackRecordBase.insert_bcRebackRecord_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(回盘记录)信息
	 * @param bcRebackRecord
	 * @return
	 */
	@Override
	public int updateBcRebackRecord(BcRebackRecord bcRebackRecord){
		return sqlSession.update("bcRebackRecordBase.update_bcRebackRecord", bcRebackRecord);
	}
	/**
	 * 批量更新(回盘记录)信息
	 * @param bcRebackRecordList
	 * @return
	 */
	@Override
	public int updateBcRebackRecordBatch(List<BcRebackRecord> bcRebackRecordList) {
		if (bcRebackRecordList == null || bcRebackRecordList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcRebackRecordBase.update_bcRebackRecord_Batch", bcRebackRecordList);
	}
	
	/**
	 * 根据序列号删除(回盘记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcRebackRecordLogic(java.math.BigInteger id){
		BcRebackRecord bcRebackRecord = new BcRebackRecord();
		bcRebackRecord.setId(id);
		bcRebackRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcRebackRecordBase.delete_bcRebackRecord_Logic",bcRebackRecord);
	}
	
	/**
	 * 根据Id 批量删除(回盘记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcRebackRecordLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcRebackRecord> delList = new java.util.ArrayList<BcRebackRecord>();
		for(java.math.BigInteger id:idList){
			BcRebackRecord bcRebackRecord = new BcRebackRecord();
			bcRebackRecord.setId(id);
			bcRebackRecord.setSys0DelState(RecordState_DELETED);
			delList.add(bcRebackRecord);
		}
		return sqlSession.update("bcRebackRecordBase.delete_bcRebackRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(回盘记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcRebackRecord(java.math.BigInteger id){
//		return sqlSession.delete("bcRebackRecordBase.delete_bcRebackRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(回盘记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcRebackRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcRebackRecordBase.delete_bcRebackRecord_Batch", idList);
//	}
	
	
}
