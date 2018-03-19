package com.cnfantasia.server.ms.logger.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;
import com.cnfantasia.server.ms.logger.dao.ICommLoggerBaseDao;
import com.cnfantasia.server.ms.logger.entity.ManagerLogger;


public class CommLoggerBaseDao extends AbstractBaseDao implements ICommLoggerBaseDao{

	/**
	 * 根据条件查询(后台日志记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ManagerLogger> selectCommLoggerByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("managerLoggerBase.select_managerLogger",paramMap);
	}
	/**
	 * 按条件分页查询(后台日志记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ManagerLogger> selectCommLoggerByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommLoggerCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ManagerLogger> resMap= sqlSession.selectList("managerLoggerBase.select_managerLogger_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(后台日志记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ManagerLogger selectCommLoggerBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("managerLoggerBase.select_managerLogger_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(后台日志记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommLoggerCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("managerLoggerBase.select_managerLogger_count", paramMap);
	}
	/**
	 * 往(后台日志记录表)新增一条记录
	 * @param commLogger
	 * @return
	 */
	@Override
	public int insertCommLogger(ManagerLogger commLogger){
		return sqlSession.insert("managerLoggerBase.insert_managerLogger",commLogger);
	}
	/**
	 * 批量新增(后台日志记录表)信息
	 * @param commLoggerList
	 * @return
	 */
	@Override
	public int insertCommLoggerBatch(List<ManagerLogger> commLoggerList) {
		return sqlSession.insert("managerLoggerBase.insert_managerLogger_Batch",commLoggerList);
	}
	
	/**
	 * 更新(后台日志记录表)信息
	 * @param commLogger
	 * @return
	 */
	@Override
	public int updateCommLogger(ManagerLogger commLogger){
		return sqlSession.update("managerLoggerBase.update_managerLogger", commLogger);
	}
	/**
	 * 批量更新(后台日志记录表)信息
	 * @param commLoggerList
	 * @return
	 */
	@Override
	public int updateCommLoggerBatch(List<ManagerLogger> commLoggerList) {
		return sqlSession.update("managerLoggerBase.update_managerLogger_Batch", commLoggerList);
	}
	
	/**
	 * 根据序列号删除(后台日志记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerLogic(java.math.BigInteger id){
		CommLogger commLogger = new CommLogger();
		commLogger.setId(id);
		commLogger.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("managerLoggerBase.delete_managerLogger_Logic",commLogger);
	}
	
	/**
	 * 根据Id 批量删除(后台日志记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerLogicBatch(List<java.math.BigInteger> idList) {
		List<ManagerLogger> delList = new java.util.ArrayList<ManagerLogger>();
		for(java.math.BigInteger id:idList){
			ManagerLogger managerLogger = new ManagerLogger();
			managerLogger.setId(id);
			managerLogger.setSys0DelState(RecordState_DELETED);
			delList.add(managerLogger);
		}
		return sqlSession.update("managerLoggerBase.delete_managerLogger_Logic_Batch",delList);
	}

}
