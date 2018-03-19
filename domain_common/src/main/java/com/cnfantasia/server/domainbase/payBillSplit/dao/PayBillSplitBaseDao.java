package com.cnfantasia.server.domainbase.payBillSplit.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;

/**
 * 描述:(费用账单拆分表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayBillSplitBaseDao extends AbstractBaseDao implements IPayBillSplitBaseDao{
	/**
	 * 根据条件查询(费用账单拆分表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillSplit> selectPayBillSplitByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payBillSplitBase.select_payBillSplit",paramMap);
	}
	/**
	 * 按条件分页查询(费用账单拆分表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillSplit> selectPayBillSplitByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayBillSplitCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayBillSplit> resMap= sqlSession.selectList("payBillSplitBase.select_payBillSplit_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(费用账单拆分表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillSplit selectPayBillSplitBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payBillSplitBase.select_payBillSplit_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(费用账单拆分表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayBillSplitCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payBillSplitBase.select_payBillSplit_count", paramMap);
	}
	/**
	 * 往(费用账单拆分表)新增一条记录
	 * @param payBillSplit
	 * @return
	 */
	@Override
	public int insertPayBillSplit(PayBillSplit payBillSplit){
		return sqlSession.insert("payBillSplitBase.insert_payBillSplit",payBillSplit);
	}
	/**
	 * 批量新增(费用账单拆分表)信息
	 * @param payBillSplitList
	 * @return
	 */
	@Override
	public int insertPayBillSplitBatch(List<PayBillSplit> payBillSplitList) {
		return sqlSession.insert("payBillSplitBase.insert_payBillSplit_Batch",payBillSplitList);
	}
	
	/**
	 * 更新(费用账单拆分表)信息
	 * @param payBillSplit
	 * @return
	 */
	@Override
	public int updatePayBillSplit(PayBillSplit payBillSplit){
		return sqlSession.update("payBillSplitBase.update_payBillSplit", payBillSplit);
	}
	/**
	 * 批量更新(费用账单拆分表)信息
	 * @param payBillSplitList
	 * @return
	 */
	@Override
	public int updatePayBillSplitBatch(List<PayBillSplit> payBillSplitList) {
		return sqlSession.update("payBillSplitBase.update_payBillSplit_Batch", payBillSplitList);
	}
	
	/**
	 * 根据序列号删除(费用账单拆分表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillSplitLogic(java.math.BigInteger id){
		PayBillSplit payBillSplit = new PayBillSplit();
		payBillSplit.setId(id);
		payBillSplit.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payBillSplitBase.delete_payBillSplit_Logic",payBillSplit);
	}
	
	/**
	 * 根据Id 批量删除(费用账单拆分表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillSplitLogicBatch(List<java.math.BigInteger> idList) {
		List<PayBillSplit> delList = new java.util.ArrayList<PayBillSplit>();
		for(java.math.BigInteger id:idList){
			PayBillSplit payBillSplit = new PayBillSplit();
			payBillSplit.setId(id);
			payBillSplit.setSys0DelState(RecordState_DELETED);
			delList.add(payBillSplit);
		}
		return sqlSession.update("payBillSplitBase.delete_payBillSplit_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(费用账单拆分表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillSplit(java.math.BigInteger id){
//		return sqlSession.delete("payBillSplitBase.delete_payBillSplit", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(费用账单拆分表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillSplitBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payBillSplitBase.delete_payBillSplit_Batch", idList);
//	}
	
	
}
