package com.cnfantasia.server.domainbase.commSignRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * 描述:(签到记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommSignRecordBaseDao extends AbstractBaseDao implements ICommSignRecordBaseDao{
	/**
	 * 根据条件查询(签到记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommSignRecord> selectCommSignRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commSignRecordBase.select_commSignRecord",paramMap);
	}
	/**
	 * 按条件分页查询(签到记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommSignRecord> selectCommSignRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommSignRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommSignRecord> resMap= sqlSession.selectList("commSignRecordBase.select_commSignRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(签到记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommSignRecord selectCommSignRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commSignRecordBase.select_commSignRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(签到记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommSignRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commSignRecordBase.select_commSignRecord_count", paramMap);
	}
	/**
	 * 往(签到记录)新增一条记录
	 * @param commSignRecord
	 * @return
	 */
	@Override
	public int insertCommSignRecord(CommSignRecord commSignRecord){
		return sqlSession.insert("commSignRecordBase.insert_commSignRecord",commSignRecord);
	}
	/**
	 * 批量新增(签到记录)信息
	 * @param commSignRecordList
	 * @return
	 */
	@Override
	public int insertCommSignRecordBatch(List<CommSignRecord> commSignRecordList) {
		return sqlSession.insert("commSignRecordBase.insert_commSignRecord_Batch",commSignRecordList);
	}
	
	/**
	 * 更新(签到记录)信息
	 * @param commSignRecord
	 * @return
	 */
	@Override
	public int updateCommSignRecord(CommSignRecord commSignRecord){
		return sqlSession.update("commSignRecordBase.update_commSignRecord", commSignRecord);
	}
	/**
	 * 批量更新(签到记录)信息
	 * @param commSignRecordList
	 * @return
	 */
	@Override
	public int updateCommSignRecordBatch(List<CommSignRecord> commSignRecordList) {
		return sqlSession.update("commSignRecordBase.update_commSignRecord_Batch", commSignRecordList);
	}
	
	/**
	 * 根据序列号删除(签到记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommSignRecordLogic(java.math.BigInteger id){
		CommSignRecord commSignRecord = new CommSignRecord();
		commSignRecord.setId(id);
		commSignRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commSignRecordBase.delete_commSignRecord_Logic",commSignRecord);
	}
	
	/**
	 * 根据Id 批量删除(签到记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommSignRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<CommSignRecord> delList = new java.util.ArrayList<CommSignRecord>();
		for(java.math.BigInteger id:idList){
			CommSignRecord commSignRecord = new CommSignRecord();
			commSignRecord.setId(id);
			commSignRecord.setSys0DelState(RecordState_DELETED);
			delList.add(commSignRecord);
		}
		return sqlSession.update("commSignRecordBase.delete_commSignRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(签到记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommSignRecord(java.math.BigInteger id){
//		return sqlSession.delete("commSignRecordBase.delete_commSignRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(签到记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommSignRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commSignRecordBase.delete_commSignRecord_Batch", idList);
//	}
	
	
}
