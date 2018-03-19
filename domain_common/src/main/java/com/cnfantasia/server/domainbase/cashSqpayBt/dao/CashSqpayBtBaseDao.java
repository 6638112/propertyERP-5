package com.cnfantasia.server.domainbase.cashSqpayBt.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cashSqpayBt.entity.CashSqpayBt;

/**
 * 描述:(双乾支付优惠补贴明细表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CashSqpayBtBaseDao extends AbstractBaseDao implements ICashSqpayBtBaseDao{
	/**
	 * 根据条件查询(双乾支付优惠补贴明细表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CashSqpayBt> selectCashSqpayBtByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cashSqpayBtBase.select_cashSqpayBt",paramMap);
	}
	/**
	 * 按条件分页查询(双乾支付优惠补贴明细表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CashSqpayBt> selectCashSqpayBtByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCashSqpayBtCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CashSqpayBt> resMap= sqlSession.selectList("cashSqpayBtBase.select_cashSqpayBt_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(双乾支付优惠补贴明细表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CashSqpayBt selectCashSqpayBtBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("cashSqpayBtBase.select_cashSqpayBt_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(双乾支付优惠补贴明细表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCashSqpayBtCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("cashSqpayBtBase.select_cashSqpayBt_count", paramMap);
	}
	/**
	 * 往(双乾支付优惠补贴明细表)新增一条记录
	 * @param cashSqpayBt
	 * @return
	 */
	@Override
	public int insertCashSqpayBt(CashSqpayBt cashSqpayBt){
		return sqlSession.insert("cashSqpayBtBase.insert_cashSqpayBt",cashSqpayBt);
	}
	/**
	 * 批量新增(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBtList
	 * @return
	 */
	@Override
	public int insertCashSqpayBtBatch(List<CashSqpayBt> cashSqpayBtList) {
		return sqlSession.insert("cashSqpayBtBase.insert_cashSqpayBt_Batch",cashSqpayBtList);
	}
	
	/**
	 * 更新(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBt
	 * @return
	 */
	@Override
	public int updateCashSqpayBt(CashSqpayBt cashSqpayBt){
		return sqlSession.update("cashSqpayBtBase.update_cashSqpayBt", cashSqpayBt);
	}
	/**
	 * 批量更新(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBtList
	 * @return
	 */
	@Override
	public int updateCashSqpayBtBatch(List<CashSqpayBt> cashSqpayBtList) {
		return sqlSession.update("cashSqpayBtBase.update_cashSqpayBt_Batch", cashSqpayBtList);
	}
	
	/**
	 * 根据序列号删除(双乾支付优惠补贴明细表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCashSqpayBtLogic(java.math.BigInteger id){
		CashSqpayBt cashSqpayBt = new CashSqpayBt();
		cashSqpayBt.setId(id);
		cashSqpayBt.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("cashSqpayBtBase.delete_cashSqpayBt_Logic",cashSqpayBt);
	}
	 */
	/**
	 * 根据Id 批量删除(双乾支付优惠补贴明细表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCashSqpayBtLogicBatch(List<java.math.BigInteger> idList) {
		List<CashSqpayBt> delList = new java.util.ArrayList<CashSqpayBt>();
		for(java.math.BigInteger id:idList){
			CashSqpayBt cashSqpayBt = new CashSqpayBt();
			cashSqpayBt.setId(id);
			cashSqpayBt.setSys0DelState(RecordState_DELETED);
			delList.add(cashSqpayBt);
		}
		return sqlSession.update("cashSqpayBtBase.delete_cashSqpayBt_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(双乾支付优惠补贴明细表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCashSqpayBt(java.math.BigInteger id){
//		return sqlSession.delete("cashSqpayBtBase.delete_cashSqpayBt", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(双乾支付优惠补贴明细表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCashSqpayBtBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("cashSqpayBtBase.delete_cashSqpayBt_Batch", idList);
//	}
	
	
}
