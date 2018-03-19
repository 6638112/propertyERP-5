package com.cnfantasia.server.domainbase.appCrashLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appCrashLog.entity.AppCrashLog;

/**
 * 描述:(app崩溃日志) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AppCrashLogBaseDao extends AbstractBaseDao implements IAppCrashLogBaseDao{
	/**
	 * 根据条件查询(app崩溃日志)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppCrashLog> selectAppCrashLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("appCrashLogBase.select_appCrashLog",paramMap);
	}
	/**
	 * 按条件分页查询(app崩溃日志)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppCrashLog> selectAppCrashLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAppCrashLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AppCrashLog> resMap= sqlSession.selectList("appCrashLogBase.select_appCrashLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(app崩溃日志)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppCrashLog selectAppCrashLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("appCrashLogBase.select_appCrashLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(app崩溃日志)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAppCrashLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("appCrashLogBase.select_appCrashLog_count", paramMap);
	}
	/**
	 * 往(app崩溃日志)新增一条记录
	 * @param appCrashLog
	 * @return
	 */
	@Override
	public int insertAppCrashLog(AppCrashLog appCrashLog){
		return sqlSession.insert("appCrashLogBase.insert_appCrashLog",appCrashLog);
	}
	/**
	 * 批量新增(app崩溃日志)信息
	 * @param appCrashLogList
	 * @return
	 */
	@Override
	public int insertAppCrashLogBatch(List<AppCrashLog> appCrashLogList) {
		return sqlSession.insert("appCrashLogBase.insert_appCrashLog_Batch",appCrashLogList);
	}
	
	/**
	 * 更新(app崩溃日志)信息
	 * @param appCrashLog
	 * @return
	 */
	@Override
	public int updateAppCrashLog(AppCrashLog appCrashLog){
		return sqlSession.update("appCrashLogBase.update_appCrashLog", appCrashLog);
	}
	/**
	 * 批量更新(app崩溃日志)信息
	 * @param appCrashLogList
	 * @return
	 */
	@Override
	public int updateAppCrashLogBatch(List<AppCrashLog> appCrashLogList) {
		return sqlSession.update("appCrashLogBase.update_appCrashLog_Batch", appCrashLogList);
	}
	
	/**
	 * 根据序列号删除(app崩溃日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppCrashLogLogic(java.math.BigInteger id){
		AppCrashLog appCrashLog = new AppCrashLog();
		appCrashLog.setId(id);
		appCrashLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("appCrashLogBase.delete_appCrashLog_Logic",appCrashLog);
	}
	
	/**
	 * 根据Id 批量删除(app崩溃日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppCrashLogLogicBatch(List<java.math.BigInteger> idList) {
		List<AppCrashLog> delList = new java.util.ArrayList<AppCrashLog>();
		for(java.math.BigInteger id:idList){
			AppCrashLog appCrashLog = new AppCrashLog();
			appCrashLog.setId(id);
			appCrashLog.setSys0DelState(RecordState_DELETED);
			delList.add(appCrashLog);
		}
		return sqlSession.update("appCrashLogBase.delete_appCrashLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(app崩溃日志)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppCrashLog(java.math.BigInteger id){
//		return sqlSession.delete("appCrashLogBase.delete_appCrashLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(app崩溃日志)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppCrashLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("appCrashLogBase.delete_appCrashLog_Batch", idList);
//	}
	
	
}
