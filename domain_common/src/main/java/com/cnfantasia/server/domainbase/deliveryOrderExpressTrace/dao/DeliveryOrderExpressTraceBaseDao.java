package com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.entity.DeliveryOrderExpressTrace;

/**
 * 描述:(供应商快递记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DeliveryOrderExpressTraceBaseDao extends AbstractBaseDao implements IDeliveryOrderExpressTraceBaseDao{
	/**
	 * 根据条件查询(供应商快递记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DeliveryOrderExpressTrace> selectDeliveryOrderExpressTraceByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("deliveryOrderExpressTraceBase.select_deliveryOrderExpressTrace",paramMap);
	}
	/**
	 * 按条件分页查询(供应商快递记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DeliveryOrderExpressTrace> selectDeliveryOrderExpressTraceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDeliveryOrderExpressTraceCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DeliveryOrderExpressTrace> resMap= sqlSession.selectList("deliveryOrderExpressTraceBase.select_deliveryOrderExpressTrace_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(供应商快递记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DeliveryOrderExpressTrace selectDeliveryOrderExpressTraceBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("deliveryOrderExpressTraceBase.select_deliveryOrderExpressTrace_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(供应商快递记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDeliveryOrderExpressTraceCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("deliveryOrderExpressTraceBase.select_deliveryOrderExpressTrace_count", paramMap);
	}
	/**
	 * 往(供应商快递记录表)新增一条记录
	 * @param deliveryOrderExpressTrace
	 * @return
	 */
	@Override
	public int insertDeliveryOrderExpressTrace(DeliveryOrderExpressTrace deliveryOrderExpressTrace){
		return sqlSession.insert("deliveryOrderExpressTraceBase.insert_deliveryOrderExpressTrace",deliveryOrderExpressTrace);
	}
	/**
	 * 批量新增(供应商快递记录表)信息
	 * @param deliveryOrderExpressTraceList
	 * @return
	 */
	@Override
	public int insertDeliveryOrderExpressTraceBatch(List<DeliveryOrderExpressTrace> deliveryOrderExpressTraceList) {
		return sqlSession.insert("deliveryOrderExpressTraceBase.insert_deliveryOrderExpressTrace_Batch",deliveryOrderExpressTraceList);
	}
	
	/**
	 * 更新(供应商快递记录表)信息
	 * @param deliveryOrderExpressTrace
	 * @return
	 */
	@Override
	public int updateDeliveryOrderExpressTrace(DeliveryOrderExpressTrace deliveryOrderExpressTrace){
		return sqlSession.update("deliveryOrderExpressTraceBase.update_deliveryOrderExpressTrace", deliveryOrderExpressTrace);
	}
	/**
	 * 批量更新(供应商快递记录表)信息
	 * @param deliveryOrderExpressTraceList
	 * @return
	 */
	@Override
	public int updateDeliveryOrderExpressTraceBatch(List<DeliveryOrderExpressTrace> deliveryOrderExpressTraceList) {
		return sqlSession.update("deliveryOrderExpressTraceBase.update_deliveryOrderExpressTrace_Batch", deliveryOrderExpressTraceList);
	}
	
	/**
	 * 根据序列号删除(供应商快递记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDeliveryOrderExpressTraceLogic(java.math.BigInteger id){
		DeliveryOrderExpressTrace deliveryOrderExpressTrace = new DeliveryOrderExpressTrace();
		deliveryOrderExpressTrace.setId(id);
		deliveryOrderExpressTrace.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("deliveryOrderExpressTraceBase.delete_deliveryOrderExpressTrace_Logic",deliveryOrderExpressTrace);
	}
	
	/**
	 * 根据Id 批量删除(供应商快递记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDeliveryOrderExpressTraceLogicBatch(List<java.math.BigInteger> idList) {
		List<DeliveryOrderExpressTrace> delList = new java.util.ArrayList<DeliveryOrderExpressTrace>();
		for(java.math.BigInteger id:idList){
			DeliveryOrderExpressTrace deliveryOrderExpressTrace = new DeliveryOrderExpressTrace();
			deliveryOrderExpressTrace.setId(id);
			deliveryOrderExpressTrace.setSys0DelState(RecordState_DELETED);
			delList.add(deliveryOrderExpressTrace);
		}
		return sqlSession.update("deliveryOrderExpressTraceBase.delete_deliveryOrderExpressTrace_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(供应商快递记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDeliveryOrderExpressTrace(java.math.BigInteger id){
//		return sqlSession.delete("deliveryOrderExpressTraceBase.delete_deliveryOrderExpressTrace", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商快递记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDeliveryOrderExpressTraceBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("deliveryOrderExpressTraceBase.delete_deliveryOrderExpressTrace_Batch", idList);
//	}
	
	
}
