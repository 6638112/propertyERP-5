package com.cnfantasia.server.domainbase.dredgeBillPushTime.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillPushTime.entity.DredgeBillPushTime;

/**
 * 描述:(疏通工单推送时间记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillPushTimeBaseDao extends AbstractBaseDao implements IDredgeBillPushTimeBaseDao{
	/**
	 * 根据条件查询(疏通工单推送时间记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillPushTime> selectDredgeBillPushTimeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillPushTimeBase.select_dredgeBillPushTime",paramMap);
	}
	/**
	 * 按条件分页查询(疏通工单推送时间记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillPushTime> selectDredgeBillPushTimeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillPushTimeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBillPushTime> resMap= sqlSession.selectList("dredgeBillPushTimeBase.select_dredgeBillPushTime_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(疏通工单推送时间记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillPushTime selectDredgeBillPushTimeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillPushTimeBase.select_dredgeBillPushTime_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(疏通工单推送时间记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillPushTimeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillPushTimeBase.select_dredgeBillPushTime_count", paramMap);
	}
	/**
	 * 往(疏通工单推送时间记录表)新增一条记录
	 * @param dredgeBillPushTime
	 * @return
	 */
	@Override
	public int insertDredgeBillPushTime(DredgeBillPushTime dredgeBillPushTime){
		return sqlSession.insert("dredgeBillPushTimeBase.insert_dredgeBillPushTime",dredgeBillPushTime);
	}
	/**
	 * 批量新增(疏通工单推送时间记录表)信息
	 * @param dredgeBillPushTimeList
	 * @return
	 */
	@Override
	public int insertDredgeBillPushTimeBatch(List<DredgeBillPushTime> dredgeBillPushTimeList) {
		return sqlSession.insert("dredgeBillPushTimeBase.insert_dredgeBillPushTime_Batch",dredgeBillPushTimeList);
	}
	
	/**
	 * 更新(疏通工单推送时间记录表)信息
	 * @param dredgeBillPushTime
	 * @return
	 */
	@Override
	public int updateDredgeBillPushTime(DredgeBillPushTime dredgeBillPushTime){
		return sqlSession.update("dredgeBillPushTimeBase.update_dredgeBillPushTime", dredgeBillPushTime);
	}
	/**
	 * 批量更新(疏通工单推送时间记录表)信息
	 * @param dredgeBillPushTimeList
	 * @return
	 */
	@Override
	public int updateDredgeBillPushTimeBatch(List<DredgeBillPushTime> dredgeBillPushTimeList) {
		return sqlSession.update("dredgeBillPushTimeBase.update_dredgeBillPushTime_Batch", dredgeBillPushTimeList);
	}
	
	/**
	 * 根据序列号删除(疏通工单推送时间记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBillPushTimeLogic(java.math.BigInteger id){
		DredgeBillPushTime dredgeBillPushTime = new DredgeBillPushTime();
		dredgeBillPushTime.setId(id);
		dredgeBillPushTime.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillPushTimeBase.delete_dredgeBillPushTime_Logic",dredgeBillPushTime);
	}
	 */
	/**
	 * 根据Id 批量删除(疏通工单推送时间记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBillPushTimeLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeBillPushTime> delList = new java.util.ArrayList<DredgeBillPushTime>();
		for(java.math.BigInteger id:idList){
			DredgeBillPushTime dredgeBillPushTime = new DredgeBillPushTime();
			dredgeBillPushTime.setId(id);
			dredgeBillPushTime.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBillPushTime);
		}
		return sqlSession.update("dredgeBillPushTimeBase.delete_dredgeBillPushTime_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(疏通工单推送时间记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillPushTime(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillPushTimeBase.delete_dredgeBillPushTime", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通工单推送时间记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillPushTimeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillPushTimeBase.delete_dredgeBillPushTime_Batch", idList);
//	}
	
	
}
