package com.cnfantasia.server.domainbase.cashFlowSummary.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;

/**
 * 描述:(现金流量汇总表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CashFlowSummaryBaseDao extends AbstractBaseDao implements ICashFlowSummaryBaseDao{
	/**
	 * 根据条件查询(现金流量汇总表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectCashFlowSummaryByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cashFlowSummaryBase.select_cashFlowSummary",paramMap);
	}
	/**
	 * 按条件分页查询(现金流量汇总表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CashFlowSummary> selectCashFlowSummaryByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCashFlowSummaryCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CashFlowSummary> resMap= sqlSession.selectList("cashFlowSummaryBase.select_cashFlowSummary_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(现金流量汇总表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CashFlowSummary selectCashFlowSummaryBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("cashFlowSummaryBase.select_cashFlowSummary_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(现金流量汇总表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCashFlowSummaryCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("cashFlowSummaryBase.select_cashFlowSummary_count", paramMap);
	}
	/**
	 * 往(现金流量汇总表)新增一条记录
	 * @param cashFlowSummary
	 * @return
	 */
	@Override
	public int insertCashFlowSummary(CashFlowSummary cashFlowSummary){
		return sqlSession.insert("cashFlowSummaryBase.insert_cashFlowSummary",cashFlowSummary);
	}
	/**
	 * 批量新增(现金流量汇总表)信息
	 * @param cashFlowSummaryList
	 * @return
	 */
	@Override
	public int insertCashFlowSummaryBatch(List<CashFlowSummary> cashFlowSummaryList) {
		return sqlSession.insert("cashFlowSummaryBase.insert_cashFlowSummary_Batch",cashFlowSummaryList);
	}
	
	/**
	 * 更新(现金流量汇总表)信息
	 * @param cashFlowSummary
	 * @return
	 */
	@Override
	public int updateCashFlowSummary(CashFlowSummary cashFlowSummary){
		return sqlSession.update("cashFlowSummaryBase.update_cashFlowSummary", cashFlowSummary);
	}
	/**
	 * 批量更新(现金流量汇总表)信息
	 * @param cashFlowSummaryList
	 * @return
	 */
	@Override
	public int updateCashFlowSummaryBatch(List<CashFlowSummary> cashFlowSummaryList) {
		return sqlSession.update("cashFlowSummaryBase.update_cashFlowSummary_Batch", cashFlowSummaryList);
	}
	
	/**
	 * 根据序列号删除(现金流量汇总表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCashFlowSummaryLogic(java.math.BigInteger id){
		CashFlowSummary cashFlowSummary = new CashFlowSummary();
		cashFlowSummary.setId(id);
		cashFlowSummary.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("cashFlowSummaryBase.delete_cashFlowSummary_Logic",cashFlowSummary);
	}
	
	/**
	 * 根据Id 批量删除(现金流量汇总表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCashFlowSummaryLogicBatch(List<java.math.BigInteger> idList) {
		List<CashFlowSummary> delList = new java.util.ArrayList<CashFlowSummary>();
		for(java.math.BigInteger id:idList){
			CashFlowSummary cashFlowSummary = new CashFlowSummary();
			cashFlowSummary.setId(id);
			cashFlowSummary.setSys0DelState(RecordState_DELETED);
			delList.add(cashFlowSummary);
		}
		return sqlSession.update("cashFlowSummaryBase.delete_cashFlowSummary_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(现金流量汇总表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCashFlowSummary(java.math.BigInteger id){
//		return sqlSession.delete("cashFlowSummaryBase.delete_cashFlowSummary", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(现金流量汇总表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCashFlowSummaryBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("cashFlowSummaryBase.delete_cashFlowSummary_Batch", idList);
//	}
	
	
}
