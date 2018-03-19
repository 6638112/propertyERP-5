package com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.entity.EbuyProductSettleApplyLog;

/**
 * 描述:(购销模式结算申请表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductSettleApplyLogBaseDao extends AbstractBaseDao implements IEbuyProductSettleApplyLogBaseDao{
	/**
	 * 根据条件查询(购销模式结算申请表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyLog> selectEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductSettleApplyLogBase.select_ebuyProductSettleApplyLog",paramMap);
	}
	/**
	 * 按条件分页查询(购销模式结算申请表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyLog> selectEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductSettleApplyLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductSettleApplyLog> resMap= sqlSession.selectList("ebuyProductSettleApplyLogBase.select_ebuyProductSettleApplyLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(购销模式结算申请表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductSettleApplyLog selectEbuyProductSettleApplyLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductSettleApplyLogBase.select_ebuyProductSettleApplyLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(购销模式结算申请表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductSettleApplyLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductSettleApplyLogBase.select_ebuyProductSettleApplyLog_count", paramMap);
	}
	/**
	 * 往(购销模式结算申请表)新增一条记录
	 * @param ebuyProductSettleApplyLog
	 * @return
	 */
	@Override
	public int insertEbuyProductSettleApplyLog(EbuyProductSettleApplyLog ebuyProductSettleApplyLog){
		return sqlSession.insert("ebuyProductSettleApplyLogBase.insert_ebuyProductSettleApplyLog",ebuyProductSettleApplyLog);
	}
	/**
	 * 批量新增(购销模式结算申请表)信息
	 * @param ebuyProductSettleApplyLogList
	 * @return
	 */
	@Override
	public int insertEbuyProductSettleApplyLogBatch(List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogList) {
		return sqlSession.insert("ebuyProductSettleApplyLogBase.insert_ebuyProductSettleApplyLog_Batch",ebuyProductSettleApplyLogList);
	}
	
	/**
	 * 更新(购销模式结算申请表)信息
	 * @param ebuyProductSettleApplyLog
	 * @return
	 */
	@Override
	public int updateEbuyProductSettleApplyLog(EbuyProductSettleApplyLog ebuyProductSettleApplyLog){
		return sqlSession.update("ebuyProductSettleApplyLogBase.update_ebuyProductSettleApplyLog", ebuyProductSettleApplyLog);
	}
	/**
	 * 批量更新(购销模式结算申请表)信息
	 * @param ebuyProductSettleApplyLogList
	 * @return
	 */
	@Override
	public int updateEbuyProductSettleApplyLogBatch(List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogList) {
		return sqlSession.update("ebuyProductSettleApplyLogBase.update_ebuyProductSettleApplyLog_Batch", ebuyProductSettleApplyLogList);
	}
	
	/**
	 * 根据序列号删除(购销模式结算申请表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyProductSettleApplyLogLogic(java.math.BigInteger id){
		EbuyProductSettleApplyLog ebuyProductSettleApplyLog = new EbuyProductSettleApplyLog();
		ebuyProductSettleApplyLog.setId(id);
		ebuyProductSettleApplyLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductSettleApplyLogBase.delete_ebuyProductSettleApplyLog_Logic",ebuyProductSettleApplyLog);
	}
	 */
	/**
	 * 根据Id 批量删除(购销模式结算申请表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyProductSettleApplyLogLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductSettleApplyLog> delList = new java.util.ArrayList<EbuyProductSettleApplyLog>();
		for(java.math.BigInteger id:idList){
			EbuyProductSettleApplyLog ebuyProductSettleApplyLog = new EbuyProductSettleApplyLog();
			ebuyProductSettleApplyLog.setId(id);
			ebuyProductSettleApplyLog.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductSettleApplyLog);
		}
		return sqlSession.update("ebuyProductSettleApplyLogBase.delete_ebuyProductSettleApplyLog_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(购销模式结算申请表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductSettleApplyLog(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductSettleApplyLogBase.delete_ebuyProductSettleApplyLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(购销模式结算申请表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductSettleApplyLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductSettleApplyLogBase.delete_ebuyProductSettleApplyLog_Batch", idList);
//	}
	
	
}
