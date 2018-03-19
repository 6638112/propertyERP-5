package com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.entity.EbuyPrepayAlipayLog;

/**
 * 描述:(淘宝预支付日志表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyPrepayAlipayLogBaseDao extends AbstractBaseDao implements IEbuyPrepayAlipayLogBaseDao{
	/**
	 * 根据条件查询(淘宝预支付日志表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyPrepayAlipayLog> selectEbuyPrepayAlipayLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyPrepayAlipayLogBase.select_ebuyPrepayAlipayLog",paramMap);
	}
	/**
	 * 按条件分页查询(淘宝预支付日志表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyPrepayAlipayLog> selectEbuyPrepayAlipayLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyPrepayAlipayLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyPrepayAlipayLog> resMap= sqlSession.selectList("ebuyPrepayAlipayLogBase.select_ebuyPrepayAlipayLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(淘宝预支付日志表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyPrepayAlipayLog selectEbuyPrepayAlipayLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyPrepayAlipayLogBase.select_ebuyPrepayAlipayLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(淘宝预支付日志表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyPrepayAlipayLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyPrepayAlipayLogBase.select_ebuyPrepayAlipayLog_count", paramMap);
	}
	/**
	 * 往(淘宝预支付日志表)新增一条记录
	 * @param ebuyPrepayAlipayLog
	 * @return
	 */
	@Override
	public int insertEbuyPrepayAlipayLog(EbuyPrepayAlipayLog ebuyPrepayAlipayLog){
		return sqlSession.insert("ebuyPrepayAlipayLogBase.insert_ebuyPrepayAlipayLog",ebuyPrepayAlipayLog);
	}
	/**
	 * 批量新增(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLogList
	 * @return
	 */
	@Override
	public int insertEbuyPrepayAlipayLogBatch(List<EbuyPrepayAlipayLog> ebuyPrepayAlipayLogList) {
		return sqlSession.insert("ebuyPrepayAlipayLogBase.insert_ebuyPrepayAlipayLog_Batch",ebuyPrepayAlipayLogList);
	}
	
	/**
	 * 更新(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLog
	 * @return
	 */
	@Override
	public int updateEbuyPrepayAlipayLog(EbuyPrepayAlipayLog ebuyPrepayAlipayLog){
		return sqlSession.update("ebuyPrepayAlipayLogBase.update_ebuyPrepayAlipayLog", ebuyPrepayAlipayLog);
	}
	/**
	 * 批量更新(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLogList
	 * @return
	 */
	@Override
	public int updateEbuyPrepayAlipayLogBatch(List<EbuyPrepayAlipayLog> ebuyPrepayAlipayLogList) {
		return sqlSession.update("ebuyPrepayAlipayLogBase.update_ebuyPrepayAlipayLog_Batch", ebuyPrepayAlipayLogList);
	}
	
	/**
	 * 根据序列号删除(淘宝预支付日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayAlipayLogLogic(java.math.BigInteger id){
		EbuyPrepayAlipayLog ebuyPrepayAlipayLog = new EbuyPrepayAlipayLog();
		ebuyPrepayAlipayLog.setId(id);
		ebuyPrepayAlipayLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyPrepayAlipayLogBase.delete_ebuyPrepayAlipayLog_Logic",ebuyPrepayAlipayLog);
	}
	
	/**
	 * 根据Id 批量删除(淘宝预支付日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayAlipayLogLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyPrepayAlipayLog> delList = new java.util.ArrayList<EbuyPrepayAlipayLog>();
		for(java.math.BigInteger id:idList){
			EbuyPrepayAlipayLog ebuyPrepayAlipayLog = new EbuyPrepayAlipayLog();
			ebuyPrepayAlipayLog.setId(id);
			ebuyPrepayAlipayLog.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyPrepayAlipayLog);
		}
		return sqlSession.update("ebuyPrepayAlipayLogBase.delete_ebuyPrepayAlipayLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(淘宝预支付日志表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayAlipayLog(java.math.BigInteger id){
//		return sqlSession.delete("ebuyPrepayAlipayLogBase.delete_ebuyPrepayAlipayLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(淘宝预支付日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayAlipayLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyPrepayAlipayLogBase.delete_ebuyPrepayAlipayLog_Batch", idList);
//	}
	
	
}
