package com.cnfantasia.server.domainbase.pointCostRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointCostRecord.entity.PointCostRecord;

/**
 * 描述:(积分消费记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PointCostRecordBaseDao extends AbstractBaseDao implements IPointCostRecordBaseDao{
	/**
	 * 根据条件查询(积分消费记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PointCostRecord> selectPointCostRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("pointCostRecordBase.select_pointCostRecord",paramMap);
	}
	/**
	 * 按条件分页查询(积分消费记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PointCostRecord> selectPointCostRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPointCostRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PointCostRecord> resMap= sqlSession.selectList("pointCostRecordBase.select_pointCostRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(积分消费记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public PointCostRecord selectPointCostRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("pointCostRecordBase.select_pointCostRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(积分消费记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPointCostRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("pointCostRecordBase.select_pointCostRecord_count", paramMap);
	}
	/**
	 * 往(积分消费记录)新增一条记录
	 * @param pointCostRecord
	 * @return
	 */
	@Override
	public int insertPointCostRecord(PointCostRecord pointCostRecord){
		return sqlSession.insert("pointCostRecordBase.insert_pointCostRecord",pointCostRecord);
	}
	/**
	 * 批量新增(积分消费记录)信息
	 * @param pointCostRecordList
	 * @return
	 */
	@Override
	public int insertPointCostRecordBatch(List<PointCostRecord> pointCostRecordList) {
		return sqlSession.insert("pointCostRecordBase.insert_pointCostRecord_Batch",pointCostRecordList);
	}
	
	/**
	 * 更新(积分消费记录)信息
	 * @param pointCostRecord
	 * @return
	 */
	@Override
	public int updatePointCostRecord(PointCostRecord pointCostRecord){
		return sqlSession.update("pointCostRecordBase.update_pointCostRecord", pointCostRecord);
	}
	/**
	 * 批量更新(积分消费记录)信息
	 * @param pointCostRecordList
	 * @return
	 */
	@Override
	public int updatePointCostRecordBatch(List<PointCostRecord> pointCostRecordList) {
		return sqlSession.update("pointCostRecordBase.update_pointCostRecord_Batch", pointCostRecordList);
	}
	
	/**
	 * 根据序列号删除(积分消费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePointCostRecordLogic(java.math.BigInteger id){
		PointCostRecord pointCostRecord = new PointCostRecord();
		pointCostRecord.setId(id);
		pointCostRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("pointCostRecordBase.delete_pointCostRecord_Logic",pointCostRecord);
	}
	
	/**
	 * 根据Id 批量删除(积分消费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePointCostRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<PointCostRecord> delList = new java.util.ArrayList<PointCostRecord>();
		for(java.math.BigInteger id:idList){
			PointCostRecord pointCostRecord = new PointCostRecord();
			pointCostRecord.setId(id);
			pointCostRecord.setSys0DelState(RecordState_DELETED);
			delList.add(pointCostRecord);
		}
		return sqlSession.update("pointCostRecordBase.delete_pointCostRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(积分消费记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePointCostRecord(java.math.BigInteger id){
//		return sqlSession.delete("pointCostRecordBase.delete_pointCostRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(积分消费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePointCostRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("pointCostRecordBase.delete_pointCostRecord_Batch", idList);
//	}
	
	
}
