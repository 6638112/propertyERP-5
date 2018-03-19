package com.cnfantasia.server.domainbase.dredgeBillFollowRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity.DredgeBillFollowRecord;

/**
 * 描述:(维修订单跟进记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillFollowRecordBaseDao extends AbstractBaseDao implements IDredgeBillFollowRecordBaseDao{
	/**
	 * 根据条件查询(维修订单跟进记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillFollowRecord> selectDredgeBillFollowRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillFollowRecordBase.select_dredgeBillFollowRecord",paramMap);
	}
	/**
	 * 按条件分页查询(维修订单跟进记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillFollowRecord> selectDredgeBillFollowRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillFollowRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBillFollowRecord> resMap= sqlSession.selectList("dredgeBillFollowRecordBase.select_dredgeBillFollowRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修订单跟进记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillFollowRecord selectDredgeBillFollowRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillFollowRecordBase.select_dredgeBillFollowRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修订单跟进记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillFollowRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillFollowRecordBase.select_dredgeBillFollowRecord_count", paramMap);
	}
	/**
	 * 往(维修订单跟进记录)新增一条记录
	 * @param dredgeBillFollowRecord
	 * @return
	 */
	@Override
	public int insertDredgeBillFollowRecord(DredgeBillFollowRecord dredgeBillFollowRecord){
		return sqlSession.insert("dredgeBillFollowRecordBase.insert_dredgeBillFollowRecord",dredgeBillFollowRecord);
	}
	/**
	 * 批量新增(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecordList
	 * @return
	 */
	@Override
	public int insertDredgeBillFollowRecordBatch(List<DredgeBillFollowRecord> dredgeBillFollowRecordList) {
		return sqlSession.insert("dredgeBillFollowRecordBase.insert_dredgeBillFollowRecord_Batch",dredgeBillFollowRecordList);
	}
	
	/**
	 * 更新(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecord
	 * @return
	 */
	@Override
	public int updateDredgeBillFollowRecord(DredgeBillFollowRecord dredgeBillFollowRecord){
		return sqlSession.update("dredgeBillFollowRecordBase.update_dredgeBillFollowRecord", dredgeBillFollowRecord);
	}
	/**
	 * 批量更新(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecordList
	 * @return
	 */
	@Override
	public int updateDredgeBillFollowRecordBatch(List<DredgeBillFollowRecord> dredgeBillFollowRecordList) {
		return sqlSession.update("dredgeBillFollowRecordBase.update_dredgeBillFollowRecord_Batch", dredgeBillFollowRecordList);
	}
	
	/**
	 * 根据序列号删除(维修订单跟进记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillFollowRecordLogic(java.math.BigInteger id){
		DredgeBillFollowRecord dredgeBillFollowRecord = new DredgeBillFollowRecord();
		dredgeBillFollowRecord.setId(id);
		dredgeBillFollowRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillFollowRecordBase.delete_dredgeBillFollowRecord_Logic",dredgeBillFollowRecord);
	}
	
	/**
	 * 根据Id 批量删除(维修订单跟进记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillFollowRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeBillFollowRecord> delList = new java.util.ArrayList<DredgeBillFollowRecord>();
		for(java.math.BigInteger id:idList){
			DredgeBillFollowRecord dredgeBillFollowRecord = new DredgeBillFollowRecord();
			dredgeBillFollowRecord.setId(id);
			dredgeBillFollowRecord.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBillFollowRecord);
		}
		return sqlSession.update("dredgeBillFollowRecordBase.delete_dredgeBillFollowRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修订单跟进记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillFollowRecord(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillFollowRecordBase.delete_dredgeBillFollowRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修订单跟进记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillFollowRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillFollowRecordBase.delete_dredgeBillFollowRecord_Batch", idList);
//	}
	
	
}
