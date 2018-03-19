package com.cnfantasia.server.domainbase.payRedEnvelope.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * 描述:(粮票信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayRedEnvelopeBaseDao extends AbstractBaseDao implements IPayRedEnvelopeBaseDao{
	/**
	 * 根据条件查询(粮票信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayRedEnvelope> selectPayRedEnvelopeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payRedEnvelopeBase.select_payRedEnvelope",paramMap);
	}
	/**
	 * 按条件分页查询(粮票信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayRedEnvelope> selectPayRedEnvelopeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayRedEnvelopeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayRedEnvelope> resMap= sqlSession.selectList("payRedEnvelopeBase.select_payRedEnvelope_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(粮票信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayRedEnvelope selectPayRedEnvelopeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payRedEnvelopeBase.select_payRedEnvelope_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(粮票信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayRedEnvelopeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payRedEnvelopeBase.select_payRedEnvelope_count", paramMap);
	}
	/**
	 * 往(粮票信息表)新增一条记录
	 * @param payRedEnvelope
	 * @return
	 */
	@Override
	public int insertPayRedEnvelope(PayRedEnvelope payRedEnvelope){
		return sqlSession.insert("payRedEnvelopeBase.insert_payRedEnvelope",payRedEnvelope);
	}
	/**
	 * 批量新增(粮票信息表)信息
	 * @param payRedEnvelopeList
	 * @return
	 */
	@Override
	public int insertPayRedEnvelopeBatch(List<PayRedEnvelope> payRedEnvelopeList) {
		return sqlSession.insert("payRedEnvelopeBase.insert_payRedEnvelope_Batch",payRedEnvelopeList);
	}
	
	/**
	 * 更新(粮票信息表)信息
	 * @param payRedEnvelope
	 * @return
	 */
	@Override
	public int updatePayRedEnvelope(PayRedEnvelope payRedEnvelope){
		return sqlSession.update("payRedEnvelopeBase.update_payRedEnvelope", payRedEnvelope);
	}
	/**
	 * 批量更新(粮票信息表)信息
	 * @param payRedEnvelopeList
	 * @return
	 */
	@Override
	public int updatePayRedEnvelopeBatch(List<PayRedEnvelope> payRedEnvelopeList) {
		return sqlSession.update("payRedEnvelopeBase.update_payRedEnvelope_Batch", payRedEnvelopeList);
	}
	
	/**
	 * 根据序列号删除(粮票信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeLogic(java.math.BigInteger id){
		PayRedEnvelope payRedEnvelope = new PayRedEnvelope();
		payRedEnvelope.setId(id);
		payRedEnvelope.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payRedEnvelopeBase.delete_payRedEnvelope_Logic",payRedEnvelope);
	}
	
	/**
	 * 根据Id 批量删除(粮票信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeLogicBatch(List<java.math.BigInteger> idList) {
		List<PayRedEnvelope> delList = new java.util.ArrayList<PayRedEnvelope>();
		for(java.math.BigInteger id:idList){
			PayRedEnvelope payRedEnvelope = new PayRedEnvelope();
			payRedEnvelope.setId(id);
			payRedEnvelope.setSys0DelState(RecordState_DELETED);
			delList.add(payRedEnvelope);
		}
		return sqlSession.update("payRedEnvelopeBase.delete_payRedEnvelope_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(粮票信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelope(java.math.BigInteger id){
//		return sqlSession.delete("payRedEnvelopeBase.delete_payRedEnvelope", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(粮票信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelopeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payRedEnvelopeBase.delete_payRedEnvelope_Batch", idList);
//	}
	
	
}
