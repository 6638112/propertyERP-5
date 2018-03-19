package com.cnfantasia.server.domainbase.taskLogger.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.taskLogger.entity.TaskLogger;

/**
 * 描述:(job记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class TaskLoggerBaseDao extends AbstractBaseDao implements ITaskLoggerBaseDao{
	/**
	 * 根据条件查询(job记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<TaskLogger> selectTaskLoggerByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("taskLoggerBase.select_taskLogger",paramMap);
	}
	/**
	 * 按条件分页查询(job记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<TaskLogger> selectTaskLoggerByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectTaskLoggerCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<TaskLogger> resMap= sqlSession.selectList("taskLoggerBase.select_taskLogger_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(job记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public TaskLogger selectTaskLoggerBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("taskLoggerBase.select_taskLogger_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(job记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectTaskLoggerCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("taskLoggerBase.select_taskLogger_count", paramMap);
	}
	/**
	 * 往(job记录表)新增一条记录
	 * @param taskLogger
	 * @return
	 */
	@Override
	public int insertTaskLogger(TaskLogger taskLogger){
		return sqlSession.insert("taskLoggerBase.insert_taskLogger",taskLogger);
	}
	/**
	 * 批量新增(job记录表)信息
	 * @param taskLoggerList
	 * @return
	 */
	@Override
	public int insertTaskLoggerBatch(List<TaskLogger> taskLoggerList) {
		if (taskLoggerList == null || taskLoggerList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = taskLoggerList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<TaskLogger> batchList = taskLoggerList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("taskLoggerBase.insert_taskLogger_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(job记录表)信息
	 * @param taskLogger
	 * @return
	 */
	@Override
	public int updateTaskLogger(TaskLogger taskLogger){
		return sqlSession.update("taskLoggerBase.update_taskLogger", taskLogger);
	}
	/**
	 * 批量更新(job记录表)信息
	 * @param taskLoggerList
	 * @return
	 */
	@Override
	public int updateTaskLoggerBatch(List<TaskLogger> taskLoggerList) {
		return sqlSession.update("taskLoggerBase.update_taskLogger_Batch", taskLoggerList);
	}
	
	/**
	 * 根据序列号删除(job记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteTaskLoggerLogic(java.math.BigInteger id){
		TaskLogger taskLogger = new TaskLogger();
		taskLogger.setId(id);
		taskLogger.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("taskLoggerBase.delete_taskLogger_Logic",taskLogger);
	}
	 */
	/**
	 * 根据Id 批量删除(job记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteTaskLoggerLogicBatch(List<java.math.BigInteger> idList) {
		List<TaskLogger> delList = new java.util.ArrayList<TaskLogger>();
		for(java.math.BigInteger id:idList){
			TaskLogger taskLogger = new TaskLogger();
			taskLogger.setId(id);
			taskLogger.setSys0DelState(RecordState_DELETED);
			delList.add(taskLogger);
		}
		return sqlSession.update("taskLoggerBase.delete_taskLogger_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(job记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteTaskLogger(java.math.BigInteger id){
//		return sqlSession.delete("taskLoggerBase.delete_taskLogger", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(job记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteTaskLoggerBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("taskLoggerBase.delete_taskLogger_Batch", idList);
//	}
	
	
}
