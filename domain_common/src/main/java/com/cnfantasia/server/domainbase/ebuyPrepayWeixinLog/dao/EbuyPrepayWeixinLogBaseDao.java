package com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.entity.EbuyPrepayWeixinLog;

/**
 * 描述:(微信预支付日志表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyPrepayWeixinLogBaseDao extends AbstractBaseDao implements IEbuyPrepayWeixinLogBaseDao{
	/**
	 * 根据条件查询(微信预支付日志表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyPrepayWeixinLog> selectEbuyPrepayWeixinLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyPrepayWeixinLogBase.select_ebuyPrepayWeixinLog",paramMap);
	}
	/**
	 * 按条件分页查询(微信预支付日志表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyPrepayWeixinLog> selectEbuyPrepayWeixinLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyPrepayWeixinLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyPrepayWeixinLog> resMap= sqlSession.selectList("ebuyPrepayWeixinLogBase.select_ebuyPrepayWeixinLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(微信预支付日志表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyPrepayWeixinLog selectEbuyPrepayWeixinLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyPrepayWeixinLogBase.select_ebuyPrepayWeixinLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(微信预支付日志表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyPrepayWeixinLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyPrepayWeixinLogBase.select_ebuyPrepayWeixinLog_count", paramMap);
	}
	/**
	 * 往(微信预支付日志表)新增一条记录
	 * @param ebuyPrepayWeixinLog
	 * @return
	 */
	@Override
	public int insertEbuyPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog){
		return sqlSession.insert("ebuyPrepayWeixinLogBase.insert_ebuyPrepayWeixinLog",ebuyPrepayWeixinLog);
	}
	/**
	 * 批量新增(微信预支付日志表)信息
	 * @param ebuyPrepayWeixinLogList
	 * @return
	 */
	@Override
	public int insertEbuyPrepayWeixinLogBatch(List<EbuyPrepayWeixinLog> ebuyPrepayWeixinLogList) {
		return sqlSession.insert("ebuyPrepayWeixinLogBase.insert_ebuyPrepayWeixinLog_Batch",ebuyPrepayWeixinLogList);
	}
	
	/**
	 * 更新(微信预支付日志表)信息
	 * @param ebuyPrepayWeixinLog
	 * @return
	 */
	@Override
	public int updateEbuyPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog){
		return sqlSession.update("ebuyPrepayWeixinLogBase.update_ebuyPrepayWeixinLog", ebuyPrepayWeixinLog);
	}
	/**
	 * 批量更新(微信预支付日志表)信息
	 * @param ebuyPrepayWeixinLogList
	 * @return
	 */
	@Override
	public int updateEbuyPrepayWeixinLogBatch(List<EbuyPrepayWeixinLog> ebuyPrepayWeixinLogList) {
		return sqlSession.update("ebuyPrepayWeixinLogBase.update_ebuyPrepayWeixinLog_Batch", ebuyPrepayWeixinLogList);
	}
	
	/**
	 * 根据序列号删除(微信预支付日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayWeixinLogLogic(java.math.BigInteger id){
		EbuyPrepayWeixinLog ebuyPrepayWeixinLog = new EbuyPrepayWeixinLog();
		ebuyPrepayWeixinLog.setId(id);
		ebuyPrepayWeixinLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyPrepayWeixinLogBase.delete_ebuyPrepayWeixinLog_Logic",ebuyPrepayWeixinLog);
	}
	
	/**
	 * 根据Id 批量删除(微信预支付日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayWeixinLogLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyPrepayWeixinLog> delList = new java.util.ArrayList<EbuyPrepayWeixinLog>();
		for(java.math.BigInteger id:idList){
			EbuyPrepayWeixinLog ebuyPrepayWeixinLog = new EbuyPrepayWeixinLog();
			ebuyPrepayWeixinLog.setId(id);
			ebuyPrepayWeixinLog.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyPrepayWeixinLog);
		}
		return sqlSession.update("ebuyPrepayWeixinLogBase.delete_ebuyPrepayWeixinLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(微信预支付日志表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayWeixinLog(java.math.BigInteger id){
//		return sqlSession.delete("ebuyPrepayWeixinLogBase.delete_ebuyPrepayWeixinLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微信预支付日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayWeixinLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyPrepayWeixinLogBase.delete_ebuyPrepayWeixinLog_Batch", idList);
//	}
	
	
}
