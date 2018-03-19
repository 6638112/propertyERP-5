package com.cnfantasia.server.domainbase.encyptLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encyptLog.entity.EncyptLog;

/**
 * 描述:(加密日志记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EncyptLogBaseDao extends AbstractBaseDao implements IEncyptLogBaseDao{
	/**
	 * 根据条件查询(加密日志记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EncyptLog> selectEncyptLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("encyptLogBase.select_encyptLog",paramMap);
	}
	/**
	 * 按条件分页查询(加密日志记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EncyptLog> selectEncyptLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEncyptLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EncyptLog> resMap= sqlSession.selectList("encyptLogBase.select_encyptLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(加密日志记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public EncyptLog selectEncyptLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("encyptLogBase.select_encyptLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(加密日志记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEncyptLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("encyptLogBase.select_encyptLog_count", paramMap);
	}
	/**
	 * 往(加密日志记录)新增一条记录
	 * @param encyptLog
	 * @return
	 */
	@Override
	public int insertEncyptLog(EncyptLog encyptLog){
		return sqlSession.insert("encyptLogBase.insert_encyptLog",encyptLog);
	}
	/**
	 * 批量新增(加密日志记录)信息
	 * @param encyptLogList
	 * @return
	 */
	@Override
	public int insertEncyptLogBatch(List<EncyptLog> encyptLogList) {
		return sqlSession.insert("encyptLogBase.insert_encyptLog_Batch",encyptLogList);
	}
	
	/**
	 * 更新(加密日志记录)信息
	 * @param encyptLog
	 * @return
	 */
	@Override
	public int updateEncyptLog(EncyptLog encyptLog){
		return sqlSession.update("encyptLogBase.update_encyptLog", encyptLog);
	}
	/**
	 * 批量更新(加密日志记录)信息
	 * @param encyptLogList
	 * @return
	 */
	@Override
	public int updateEncyptLogBatch(List<EncyptLog> encyptLogList) {
		return sqlSession.update("encyptLogBase.update_encyptLog_Batch", encyptLogList);
	}
	
	/**
	 * 根据序列号删除(加密日志记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEncyptLogLogic(java.math.BigInteger id){
		EncyptLog encyptLog = new EncyptLog();
		encyptLog.setId(id);
		encyptLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("encyptLogBase.delete_encyptLog_Logic",encyptLog);
	}
	
	/**
	 * 根据Id 批量删除(加密日志记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEncyptLogLogicBatch(List<java.math.BigInteger> idList) {
		List<EncyptLog> delList = new java.util.ArrayList<EncyptLog>();
		for(java.math.BigInteger id:idList){
			EncyptLog encyptLog = new EncyptLog();
			encyptLog.setId(id);
			encyptLog.setSys0DelState(RecordState_DELETED);
			delList.add(encyptLog);
		}
		return sqlSession.update("encyptLogBase.delete_encyptLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(加密日志记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEncyptLog(java.math.BigInteger id){
//		return sqlSession.delete("encyptLogBase.delete_encyptLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(加密日志记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEncyptLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("encyptLogBase.delete_encyptLog_Batch", idList);
//	}
	
	
}
