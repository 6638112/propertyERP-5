package com.cnfantasia.server.domainbase.payRedEnvelopeDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.entity.PayRedEnvelopeDetail;

/**
 * 描述:(粮票优惠详情) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayRedEnvelopeDetailBaseDao extends AbstractBaseDao implements IPayRedEnvelopeDetailBaseDao{
	/**
	 * 根据条件查询(粮票优惠详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayRedEnvelopeDetail> selectPayRedEnvelopeDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payRedEnvelopeDetailBase.select_payRedEnvelopeDetail",paramMap);
	}
	/**
	 * 按条件分页查询(粮票优惠详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayRedEnvelopeDetail> selectPayRedEnvelopeDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayRedEnvelopeDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayRedEnvelopeDetail> resMap= sqlSession.selectList("payRedEnvelopeDetailBase.select_payRedEnvelopeDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(粮票优惠详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayRedEnvelopeDetail selectPayRedEnvelopeDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payRedEnvelopeDetailBase.select_payRedEnvelopeDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(粮票优惠详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayRedEnvelopeDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payRedEnvelopeDetailBase.select_payRedEnvelopeDetail_count", paramMap);
	}
	/**
	 * 往(粮票优惠详情)新增一条记录
	 * @param payRedEnvelopeDetail
	 * @return
	 */
	@Override
	public int insertPayRedEnvelopeDetail(PayRedEnvelopeDetail payRedEnvelopeDetail){
		return sqlSession.insert("payRedEnvelopeDetailBase.insert_payRedEnvelopeDetail",payRedEnvelopeDetail);
	}
	/**
	 * 批量新增(粮票优惠详情)信息
	 * @param payRedEnvelopeDetailList
	 * @return
	 */
	@Override
	public int insertPayRedEnvelopeDetailBatch(List<PayRedEnvelopeDetail> payRedEnvelopeDetailList) {
		return sqlSession.insert("payRedEnvelopeDetailBase.insert_payRedEnvelopeDetail_Batch",payRedEnvelopeDetailList);
	}
	
	/**
	 * 更新(粮票优惠详情)信息
	 * @param payRedEnvelopeDetail
	 * @return
	 */
	@Override
	public int updatePayRedEnvelopeDetail(PayRedEnvelopeDetail payRedEnvelopeDetail){
		return sqlSession.update("payRedEnvelopeDetailBase.update_payRedEnvelopeDetail", payRedEnvelopeDetail);
	}
	/**
	 * 批量更新(粮票优惠详情)信息
	 * @param payRedEnvelopeDetailList
	 * @return
	 */
	@Override
	public int updatePayRedEnvelopeDetailBatch(List<PayRedEnvelopeDetail> payRedEnvelopeDetailList) {
		return sqlSession.update("payRedEnvelopeDetailBase.update_payRedEnvelopeDetail_Batch", payRedEnvelopeDetailList);
	}
	
	/**
	 * 根据序列号删除(粮票优惠详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeDetailLogic(java.math.BigInteger id){
		PayRedEnvelopeDetail payRedEnvelopeDetail = new PayRedEnvelopeDetail();
		payRedEnvelopeDetail.setId(id);
		payRedEnvelopeDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payRedEnvelopeDetailBase.delete_payRedEnvelopeDetail_Logic",payRedEnvelopeDetail);
	}
	
	/**
	 * 根据Id 批量删除(粮票优惠详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeDetailLogicBatch(List<java.math.BigInteger> idList) {
		List<PayRedEnvelopeDetail> delList = new java.util.ArrayList<PayRedEnvelopeDetail>();
		for(java.math.BigInteger id:idList){
			PayRedEnvelopeDetail payRedEnvelopeDetail = new PayRedEnvelopeDetail();
			payRedEnvelopeDetail.setId(id);
			payRedEnvelopeDetail.setSys0DelState(RecordState_DELETED);
			delList.add(payRedEnvelopeDetail);
		}
		return sqlSession.update("payRedEnvelopeDetailBase.delete_payRedEnvelopeDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(粮票优惠详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelopeDetail(java.math.BigInteger id){
//		return sqlSession.delete("payRedEnvelopeDetailBase.delete_payRedEnvelopeDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(粮票优惠详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelopeDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payRedEnvelopeDetailBase.delete_payRedEnvelopeDetail_Batch", idList);
//	}
	
	
}
