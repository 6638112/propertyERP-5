package com.cnfantasia.server.domainbase.advertiseClickRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.advertiseClickRecord.entity.AdvertiseClickRecord;

/**
 * 描述:(广告点击记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AdvertiseClickRecordBaseDao extends AbstractBaseDao implements IAdvertiseClickRecordBaseDao{
	/**
	 * 根据条件查询(广告点击记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AdvertiseClickRecord> selectAdvertiseClickRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("advertiseClickRecordBase.select_advertiseClickRecord",paramMap);
	}
	/**
	 * 按条件分页查询(广告点击记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AdvertiseClickRecord> selectAdvertiseClickRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAdvertiseClickRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AdvertiseClickRecord> resMap= sqlSession.selectList("advertiseClickRecordBase.select_advertiseClickRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(广告点击记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AdvertiseClickRecord selectAdvertiseClickRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("advertiseClickRecordBase.select_advertiseClickRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(广告点击记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAdvertiseClickRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("advertiseClickRecordBase.select_advertiseClickRecord_count", paramMap);
	}
	/**
	 * 往(广告点击记录表)新增一条记录
	 * @param advertiseClickRecord
	 * @return
	 */
	@Override
	public int insertAdvertiseClickRecord(AdvertiseClickRecord advertiseClickRecord){
		return sqlSession.insert("advertiseClickRecordBase.insert_advertiseClickRecord",advertiseClickRecord);
	}
	/**
	 * 批量新增(广告点击记录表)信息
	 * @param advertiseClickRecordList
	 * @return
	 */
	@Override
	public int insertAdvertiseClickRecordBatch(List<AdvertiseClickRecord> advertiseClickRecordList) {
		return sqlSession.insert("advertiseClickRecordBase.insert_advertiseClickRecord_Batch",advertiseClickRecordList);
	}
	
	/**
	 * 更新(广告点击记录表)信息
	 * @param advertiseClickRecord
	 * @return
	 */
	@Override
	public int updateAdvertiseClickRecord(AdvertiseClickRecord advertiseClickRecord){
		return sqlSession.update("advertiseClickRecordBase.update_advertiseClickRecord", advertiseClickRecord);
	}
	/**
	 * 批量更新(广告点击记录表)信息
	 * @param advertiseClickRecordList
	 * @return
	 */
	@Override
	public int updateAdvertiseClickRecordBatch(List<AdvertiseClickRecord> advertiseClickRecordList) {
		return sqlSession.update("advertiseClickRecordBase.update_advertiseClickRecord_Batch", advertiseClickRecordList);
	}
	
	/**
	 * 根据序列号删除(广告点击记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAdvertiseClickRecordLogic(java.math.BigInteger id){
		AdvertiseClickRecord advertiseClickRecord = new AdvertiseClickRecord();
		advertiseClickRecord.setId(id);
		advertiseClickRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("advertiseClickRecordBase.delete_advertiseClickRecord_Logic",advertiseClickRecord);
	}
	
	/**
	 * 根据Id 批量删除(广告点击记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAdvertiseClickRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<AdvertiseClickRecord> delList = new java.util.ArrayList<AdvertiseClickRecord>();
		for(java.math.BigInteger id:idList){
			AdvertiseClickRecord advertiseClickRecord = new AdvertiseClickRecord();
			advertiseClickRecord.setId(id);
			advertiseClickRecord.setSys0DelState(RecordState_DELETED);
			delList.add(advertiseClickRecord);
		}
		return sqlSession.update("advertiseClickRecordBase.delete_advertiseClickRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(广告点击记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAdvertiseClickRecord(java.math.BigInteger id){
//		return sqlSession.delete("advertiseClickRecordBase.delete_advertiseClickRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(广告点击记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAdvertiseClickRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("advertiseClickRecordBase.delete_advertiseClickRecord_Batch", idList);
//	}
	
	
}
