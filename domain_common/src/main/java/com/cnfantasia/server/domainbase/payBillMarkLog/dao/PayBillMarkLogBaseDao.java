package com.cnfantasia.server.domainbase.payBillMarkLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillMarkLog.entity.PayBillMarkLog;

/**
 * 描述:(物业缴费账单标记日志) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayBillMarkLogBaseDao extends AbstractBaseDao implements IPayBillMarkLogBaseDao{
	/**
	 * 根据条件查询(物业缴费账单标记日志)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillMarkLog> selectPayBillMarkLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payBillMarkLogBase.select_payBillMarkLog",paramMap);
	}
	/**
	 * 按条件分页查询(物业缴费账单标记日志)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillMarkLog> selectPayBillMarkLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayBillMarkLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayBillMarkLog> resMap= sqlSession.selectList("payBillMarkLogBase.select_payBillMarkLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业缴费账单标记日志)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillMarkLog selectPayBillMarkLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payBillMarkLogBase.select_payBillMarkLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业缴费账单标记日志)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayBillMarkLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payBillMarkLogBase.select_payBillMarkLog_count", paramMap);
	}
	/**
	 * 往(物业缴费账单标记日志)新增一条记录
	 * @param payBillMarkLog
	 * @return
	 */
	@Override
	public int insertPayBillMarkLog(PayBillMarkLog payBillMarkLog){
		return sqlSession.insert("payBillMarkLogBase.insert_payBillMarkLog",payBillMarkLog);
	}
	/**
	 * 批量新增(物业缴费账单标记日志)信息
	 * @param payBillMarkLogList
	 * @return
	 */
	@Override
	public int insertPayBillMarkLogBatch(List<PayBillMarkLog> payBillMarkLogList) {
		if (payBillMarkLogList == null || payBillMarkLogList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = payBillMarkLogList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PayBillMarkLog> batchList = payBillMarkLogList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("payBillMarkLogBase.insert_payBillMarkLog_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业缴费账单标记日志)信息
	 * @param payBillMarkLog
	 * @return
	 */
	@Override
	public int updatePayBillMarkLog(PayBillMarkLog payBillMarkLog){
		return sqlSession.update("payBillMarkLogBase.update_payBillMarkLog", payBillMarkLog);
	}
	/**
	 * 批量更新(物业缴费账单标记日志)信息
	 * @param payBillMarkLogList
	 * @return
	 */
	@Override
	public int updatePayBillMarkLogBatch(List<PayBillMarkLog> payBillMarkLogList) {
		if (payBillMarkLogList == null || payBillMarkLogList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("payBillMarkLogBase.update_payBillMarkLog_Batch", payBillMarkLogList);
	}
	
	/**
	 * 根据序列号删除(物业缴费账单标记日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillMarkLogLogic(java.math.BigInteger id){
		PayBillMarkLog payBillMarkLog = new PayBillMarkLog();
		payBillMarkLog.setId(id);
		payBillMarkLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payBillMarkLogBase.delete_payBillMarkLog_Logic",payBillMarkLog);
	}
	
	/**
	 * 根据Id 批量删除(物业缴费账单标记日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillMarkLogLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PayBillMarkLog> delList = new java.util.ArrayList<PayBillMarkLog>();
		for(java.math.BigInteger id:idList){
			PayBillMarkLog payBillMarkLog = new PayBillMarkLog();
			payBillMarkLog.setId(id);
			payBillMarkLog.setSys0DelState(RecordState_DELETED);
			delList.add(payBillMarkLog);
		}
		return sqlSession.update("payBillMarkLogBase.delete_payBillMarkLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业缴费账单标记日志)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillMarkLog(java.math.BigInteger id){
//		return sqlSession.delete("payBillMarkLogBase.delete_payBillMarkLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业缴费账单标记日志)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillMarkLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payBillMarkLogBase.delete_payBillMarkLog_Batch", idList);
//	}
	
	
}
