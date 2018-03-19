package com.cnfantasia.server.domainbase.refundRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.refundRecord.entity.RefundRecord;

/**
 * 描述:(退款记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RefundRecordBaseDao extends AbstractBaseDao implements IRefundRecordBaseDao{
	/**
	 * 根据条件查询(退款记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RefundRecord> selectRefundRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("refundRecordBase.select_refundRecord",paramMap);
	}
	/**
	 * 按条件分页查询(退款记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RefundRecord> selectRefundRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRefundRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RefundRecord> resMap= sqlSession.selectList("refundRecordBase.select_refundRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(退款记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public RefundRecord selectRefundRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("refundRecordBase.select_refundRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(退款记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRefundRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("refundRecordBase.select_refundRecord_count", paramMap);
	}
	/**
	 * 往(退款记录)新增一条记录
	 * @param refundRecord
	 * @return
	 */
	@Override
	public int insertRefundRecord(RefundRecord refundRecord){
		return sqlSession.insert("refundRecordBase.insert_refundRecord",refundRecord);
	}
	/**
	 * 批量新增(退款记录)信息
	 * @param refundRecordList
	 * @return
	 */
	@Override
	public int insertRefundRecordBatch(List<RefundRecord> refundRecordList) {
		return sqlSession.insert("refundRecordBase.insert_refundRecord_Batch",refundRecordList);
	}
	
	/**
	 * 更新(退款记录)信息
	 * @param refundRecord
	 * @return
	 */
	@Override
	public int updateRefundRecord(RefundRecord refundRecord){
		return sqlSession.update("refundRecordBase.update_refundRecord", refundRecord);
	}
	/**
	 * 批量更新(退款记录)信息
	 * @param refundRecordList
	 * @return
	 */
	@Override
	public int updateRefundRecordBatch(List<RefundRecord> refundRecordList) {
		return sqlSession.update("refundRecordBase.update_refundRecord_Batch", refundRecordList);
	}
	
	/**
	 * 根据序列号删除(退款记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRefundRecordLogic(java.math.BigInteger id){
		RefundRecord refundRecord = new RefundRecord();
		refundRecord.setId(id);
		refundRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("refundRecordBase.delete_refundRecord_Logic",refundRecord);
	}
	
	/**
	 * 根据Id 批量删除(退款记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRefundRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<RefundRecord> delList = new java.util.ArrayList<RefundRecord>();
		for(java.math.BigInteger id:idList){
			RefundRecord refundRecord = new RefundRecord();
			refundRecord.setId(id);
			refundRecord.setSys0DelState(RecordState_DELETED);
			delList.add(refundRecord);
		}
		return sqlSession.update("refundRecordBase.delete_refundRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(退款记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRefundRecord(java.math.BigInteger id){
//		return sqlSession.delete("refundRecordBase.delete_refundRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(退款记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRefundRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("refundRecordBase.delete_refundRecord_Batch", idList);
//	}
	
	
}
