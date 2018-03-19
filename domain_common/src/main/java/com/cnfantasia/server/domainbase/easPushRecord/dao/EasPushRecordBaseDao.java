package com.cnfantasia.server.domainbase.easPushRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.easPushRecord.entity.EasPushRecord;

/**
 * 描述:(EAS接口推送记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EasPushRecordBaseDao extends AbstractBaseDao implements IEasPushRecordBaseDao{
	/**
	 * 根据条件查询(EAS接口推送记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EasPushRecord> selectEasPushRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("easPushRecordBase.select_easPushRecord",paramMap);
	}
	/**
	 * 按条件分页查询(EAS接口推送记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EasPushRecord> selectEasPushRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEasPushRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EasPushRecord> resMap= sqlSession.selectList("easPushRecordBase.select_easPushRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(EAS接口推送记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public EasPushRecord selectEasPushRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("easPushRecordBase.select_easPushRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(EAS接口推送记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEasPushRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("easPushRecordBase.select_easPushRecord_count", paramMap);
	}
	/**
	 * 往(EAS接口推送记录)新增一条记录
	 * @param easPushRecord
	 * @return
	 */
	@Override
	public int insertEasPushRecord(EasPushRecord easPushRecord){
		return sqlSession.insert("easPushRecordBase.insert_easPushRecord",easPushRecord);
	}
	/**
	 * 批量新增(EAS接口推送记录)信息
	 * @param easPushRecordList
	 * @return
	 */
	@Override
	public int insertEasPushRecordBatch(List<EasPushRecord> easPushRecordList) {
		return sqlSession.insert("easPushRecordBase.insert_easPushRecord_Batch",easPushRecordList);
	}
	
	/**
	 * 更新(EAS接口推送记录)信息
	 * @param easPushRecord
	 * @return
	 */
	@Override
	public int updateEasPushRecord(EasPushRecord easPushRecord){
		return sqlSession.update("easPushRecordBase.update_easPushRecord", easPushRecord);
	}
	/**
	 * 批量更新(EAS接口推送记录)信息
	 * @param easPushRecordList
	 * @return
	 */
	@Override
	public int updateEasPushRecordBatch(List<EasPushRecord> easPushRecordList) {
		return sqlSession.update("easPushRecordBase.update_easPushRecord_Batch", easPushRecordList);
	}
	
	/**
	 * 根据序列号删除(EAS接口推送记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEasPushRecordLogic(java.math.BigInteger id){
		EasPushRecord easPushRecord = new EasPushRecord();
		easPushRecord.setId(id);
		easPushRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("easPushRecordBase.delete_easPushRecord_Logic",easPushRecord);
	}
	
	/**
	 * 根据Id 批量删除(EAS接口推送记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEasPushRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<EasPushRecord> delList = new java.util.ArrayList<EasPushRecord>();
		for(java.math.BigInteger id:idList){
			EasPushRecord easPushRecord = new EasPushRecord();
			easPushRecord.setId(id);
			easPushRecord.setSys0DelState(RecordState_DELETED);
			delList.add(easPushRecord);
		}
		return sqlSession.update("easPushRecordBase.delete_easPushRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(EAS接口推送记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushRecord(java.math.BigInteger id){
//		return sqlSession.delete("easPushRecordBase.delete_easPushRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(EAS接口推送记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("easPushRecordBase.delete_easPushRecord_Batch", idList);
//	}
	
	
}
