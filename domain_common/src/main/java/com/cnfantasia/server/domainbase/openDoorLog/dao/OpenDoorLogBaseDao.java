package com.cnfantasia.server.domainbase.openDoorLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.openDoorLog.entity.OpenDoorLog;

/**
 * 描述:(车牌表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OpenDoorLogBaseDao extends AbstractBaseDao implements IOpenDoorLogBaseDao{
	/**
	 * 根据条件查询(车牌表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OpenDoorLog> selectOpenDoorLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("openDoorLogBase.select_openDoorLog",paramMap);
	}
	/**
	 * 按条件分页查询(车牌表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OpenDoorLog> selectOpenDoorLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOpenDoorLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OpenDoorLog> resMap= sqlSession.selectList("openDoorLogBase.select_openDoorLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(车牌表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OpenDoorLog selectOpenDoorLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("openDoorLogBase.select_openDoorLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(车牌表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOpenDoorLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("openDoorLogBase.select_openDoorLog_count", paramMap);
	}
	/**
	 * 往(车牌表)新增一条记录
	 * @param openDoorLog
	 * @return
	 */
	@Override
	public int insertOpenDoorLog(OpenDoorLog openDoorLog){
		return sqlSession.insert("openDoorLogBase.insert_openDoorLog",openDoorLog);
	}
	/**
	 * 批量新增(车牌表)信息
	 * @param openDoorLogList
	 * @return
	 */
	@Override
	public int insertOpenDoorLogBatch(List<OpenDoorLog> openDoorLogList) {
		return sqlSession.insert("openDoorLogBase.insert_openDoorLog_Batch",openDoorLogList);
	}
	
	/**
	 * 更新(车牌表)信息
	 * @param openDoorLog
	 * @return
	 */
	@Override
	public int updateOpenDoorLog(OpenDoorLog openDoorLog){
		return sqlSession.update("openDoorLogBase.update_openDoorLog", openDoorLog);
	}
	/**
	 * 批量更新(车牌表)信息
	 * @param openDoorLogList
	 * @return
	 */
	@Override
	public int updateOpenDoorLogBatch(List<OpenDoorLog> openDoorLogList) {
		return sqlSession.update("openDoorLogBase.update_openDoorLog_Batch", openDoorLogList);
	}
	
	/**
	 * 根据序列号删除(车牌表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOpenDoorLogLogic(java.math.BigInteger id){
		OpenDoorLog openDoorLog = new OpenDoorLog();
		openDoorLog.setId(id);
		openDoorLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("openDoorLogBase.delete_openDoorLog_Logic",openDoorLog);
	}
	
	/**
	 * 根据Id 批量删除(车牌表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOpenDoorLogLogicBatch(List<java.math.BigInteger> idList) {
		List<OpenDoorLog> delList = new java.util.ArrayList<OpenDoorLog>();
		for(java.math.BigInteger id:idList){
			OpenDoorLog openDoorLog = new OpenDoorLog();
			openDoorLog.setId(id);
			openDoorLog.setSys0DelState(RecordState_DELETED);
			delList.add(openDoorLog);
		}
		return sqlSession.update("openDoorLogBase.delete_openDoorLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(车牌表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOpenDoorLog(java.math.BigInteger id){
//		return sqlSession.delete("openDoorLogBase.delete_openDoorLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车牌表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOpenDoorLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("openDoorLogBase.delete_openDoorLog_Batch", idList);
//	}
	
	
}
