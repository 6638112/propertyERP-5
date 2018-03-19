package com.cnfantasia.server.domainbase.commShareRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commShareRecord.entity.CommShareRecord;

/**
 * 描述:(分享记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommShareRecordBaseDao extends AbstractBaseDao implements ICommShareRecordBaseDao{
	/**
	 * 根据条件查询(分享记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommShareRecord> selectCommShareRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commShareRecordBase.select_commShareRecord",paramMap);
	}
	/**
	 * 按条件分页查询(分享记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommShareRecord> selectCommShareRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommShareRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommShareRecord> resMap= sqlSession.selectList("commShareRecordBase.select_commShareRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(分享记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommShareRecord selectCommShareRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commShareRecordBase.select_commShareRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(分享记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommShareRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commShareRecordBase.select_commShareRecord_count", paramMap);
	}
	/**
	 * 往(分享记录表)新增一条记录
	 * @param commShareRecord
	 * @return
	 */
	@Override
	public int insertCommShareRecord(CommShareRecord commShareRecord){
		return sqlSession.insert("commShareRecordBase.insert_commShareRecord",commShareRecord);
	}
	/**
	 * 批量新增(分享记录表)信息
	 * @param commShareRecordList
	 * @return
	 */
	@Override
	public int insertCommShareRecordBatch(List<CommShareRecord> commShareRecordList) {
		return sqlSession.insert("commShareRecordBase.insert_commShareRecord_Batch",commShareRecordList);
	}
	
	/**
	 * 更新(分享记录表)信息
	 * @param commShareRecord
	 * @return
	 */
	@Override
	public int updateCommShareRecord(CommShareRecord commShareRecord){
		return sqlSession.update("commShareRecordBase.update_commShareRecord", commShareRecord);
	}
	/**
	 * 批量更新(分享记录表)信息
	 * @param commShareRecordList
	 * @return
	 */
	@Override
	public int updateCommShareRecordBatch(List<CommShareRecord> commShareRecordList) {
		return sqlSession.update("commShareRecordBase.update_commShareRecord_Batch", commShareRecordList);
	}
	
	/**
	 * 根据序列号删除(分享记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommShareRecordLogic(java.math.BigInteger id){
		CommShareRecord commShareRecord = new CommShareRecord();
		commShareRecord.setId(id);
		commShareRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commShareRecordBase.delete_commShareRecord_Logic",commShareRecord);
	}
	
	/**
	 * 根据Id 批量删除(分享记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommShareRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<CommShareRecord> delList = new java.util.ArrayList<CommShareRecord>();
		for(java.math.BigInteger id:idList){
			CommShareRecord commShareRecord = new CommShareRecord();
			commShareRecord.setId(id);
			commShareRecord.setSys0DelState(RecordState_DELETED);
			delList.add(commShareRecord);
		}
		return sqlSession.update("commShareRecordBase.delete_commShareRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(分享记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommShareRecord(java.math.BigInteger id){
//		return sqlSession.delete("commShareRecordBase.delete_commShareRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(分享记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommShareRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commShareRecordBase.delete_commShareRecord_Batch", idList);
//	}
	
	
}
