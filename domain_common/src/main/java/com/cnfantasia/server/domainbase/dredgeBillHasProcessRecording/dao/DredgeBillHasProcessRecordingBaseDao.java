package com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;

/**
 * 描述:(上门服务单流程记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillHasProcessRecordingBaseDao extends AbstractBaseDao implements IDredgeBillHasProcessRecordingBaseDao{
	/**
	 * 根据条件查询(上门服务单流程记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillHasProcessRecording> selectDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillHasProcessRecordingBase.select_dredgeBillHasProcessRecording",paramMap);
	}
	/**
	 * 按条件分页查询(上门服务单流程记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillHasProcessRecording> selectDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillHasProcessRecordingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBillHasProcessRecording> resMap= sqlSession.selectList("dredgeBillHasProcessRecordingBase.select_dredgeBillHasProcessRecording_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(上门服务单流程记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillHasProcessRecording selectDredgeBillHasProcessRecordingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillHasProcessRecordingBase.select_dredgeBillHasProcessRecording_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(上门服务单流程记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillHasProcessRecordingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillHasProcessRecordingBase.select_dredgeBillHasProcessRecording_count", paramMap);
	}
	/**
	 * 往(上门服务单流程记录)新增一条记录
	 * @param dredgeBillHasProcessRecording
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProcessRecording(DredgeBillHasProcessRecording dredgeBillHasProcessRecording){
		return sqlSession.insert("dredgeBillHasProcessRecordingBase.insert_dredgeBillHasProcessRecording",dredgeBillHasProcessRecording);
	}
	/**
	 * 批量新增(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecordingList
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProcessRecordingBatch(List<DredgeBillHasProcessRecording> dredgeBillHasProcessRecordingList) {
		if (dredgeBillHasProcessRecordingList == null || dredgeBillHasProcessRecordingList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = dredgeBillHasProcessRecordingList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<DredgeBillHasProcessRecording> batchList = dredgeBillHasProcessRecordingList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("dredgeBillHasProcessRecordingBase.insert_dredgeBillHasProcessRecording_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecording
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProcessRecording(DredgeBillHasProcessRecording dredgeBillHasProcessRecording){
		return sqlSession.update("dredgeBillHasProcessRecordingBase.update_dredgeBillHasProcessRecording", dredgeBillHasProcessRecording);
	}
	/**
	 * 批量更新(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecordingList
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProcessRecordingBatch(List<DredgeBillHasProcessRecording> dredgeBillHasProcessRecordingList) {
		if (dredgeBillHasProcessRecordingList == null || dredgeBillHasProcessRecordingList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("dredgeBillHasProcessRecordingBase.update_dredgeBillHasProcessRecording_Batch", dredgeBillHasProcessRecordingList);
	}
	
	/**
	 * 根据序列号删除(上门服务单流程记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProcessRecordingLogic(java.math.BigInteger id){
		DredgeBillHasProcessRecording dredgeBillHasProcessRecording = new DredgeBillHasProcessRecording();
		dredgeBillHasProcessRecording.setId(id);
		dredgeBillHasProcessRecording.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillHasProcessRecordingBase.delete_dredgeBillHasProcessRecording_Logic",dredgeBillHasProcessRecording);
	}
	
	/**
	 * 根据Id 批量删除(上门服务单流程记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProcessRecordingLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<DredgeBillHasProcessRecording> delList = new java.util.ArrayList<DredgeBillHasProcessRecording>();
		for(java.math.BigInteger id:idList){
			DredgeBillHasProcessRecording dredgeBillHasProcessRecording = new DredgeBillHasProcessRecording();
			dredgeBillHasProcessRecording.setId(id);
			dredgeBillHasProcessRecording.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBillHasProcessRecording);
		}
		return sqlSession.update("dredgeBillHasProcessRecordingBase.delete_dredgeBillHasProcessRecording_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(上门服务单流程记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProcessRecording(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillHasProcessRecordingBase.delete_dredgeBillHasProcessRecording", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(上门服务单流程记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProcessRecordingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillHasProcessRecordingBase.delete_dredgeBillHasProcessRecording_Batch", idList);
//	}
	
	
}
