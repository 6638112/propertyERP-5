package com.cnfantasia.server.domainbase.payCarCardList.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payCarCardList.entity.PayCarCardList;

/**
 * 描述:(车禁月卡付款列表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayCarCardListBaseDao extends AbstractBaseDao implements IPayCarCardListBaseDao{
	/**
	 * 根据条件查询(车禁月卡付款列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayCarCardList> selectPayCarCardListByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payCarCardListBase.select_payCarCardList",paramMap);
	}
	/**
	 * 按条件分页查询(车禁月卡付款列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayCarCardList> selectPayCarCardListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayCarCardListCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayCarCardList> resMap= sqlSession.selectList("payCarCardListBase.select_payCarCardList_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(车禁月卡付款列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayCarCardList selectPayCarCardListBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payCarCardListBase.select_payCarCardList_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(车禁月卡付款列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayCarCardListCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payCarCardListBase.select_payCarCardList_count", paramMap);
	}
	/**
	 * 往(车禁月卡付款列表)新增一条记录
	 * @param payCarCardList
	 * @return
	 */
	@Override
	public int insertPayCarCardList(PayCarCardList payCarCardList){
		return sqlSession.insert("payCarCardListBase.insert_payCarCardList",payCarCardList);
	}
	/**
	 * 批量新增(车禁月卡付款列表)信息
	 * @param payCarCardListList
	 * @return
	 */
	@Override
	public int insertPayCarCardListBatch(List<PayCarCardList> payCarCardListList) {
		return sqlSession.insert("payCarCardListBase.insert_payCarCardList_Batch",payCarCardListList);
	}
	
	/**
	 * 更新(车禁月卡付款列表)信息
	 * @param payCarCardList
	 * @return
	 */
	@Override
	public int updatePayCarCardList(PayCarCardList payCarCardList){
		return sqlSession.update("payCarCardListBase.update_payCarCardList", payCarCardList);
	}
	/**
	 * 批量更新(车禁月卡付款列表)信息
	 * @param payCarCardListList
	 * @return
	 */
	@Override
	public int updatePayCarCardListBatch(List<PayCarCardList> payCarCardListList) {
		return sqlSession.update("payCarCardListBase.update_payCarCardList_Batch", payCarCardListList);
	}
	
	/**
	 * 根据序列号删除(车禁月卡付款列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayCarCardListLogic(java.math.BigInteger id){
		PayCarCardList payCarCardList = new PayCarCardList();
		payCarCardList.setId(id);
		payCarCardList.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payCarCardListBase.delete_payCarCardList_Logic",payCarCardList);
	}
	
	/**
	 * 根据Id 批量删除(车禁月卡付款列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayCarCardListLogicBatch(List<java.math.BigInteger> idList) {
		List<PayCarCardList> delList = new java.util.ArrayList<PayCarCardList>();
		for(java.math.BigInteger id:idList){
			PayCarCardList payCarCardList = new PayCarCardList();
			payCarCardList.setId(id);
			payCarCardList.setSys0DelState(RecordState_DELETED);
			delList.add(payCarCardList);
		}
		return sqlSession.update("payCarCardListBase.delete_payCarCardList_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(车禁月卡付款列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayCarCardList(java.math.BigInteger id){
//		return sqlSession.delete("payCarCardListBase.delete_payCarCardList", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车禁月卡付款列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayCarCardListBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payCarCardListBase.delete_payCarCardList_Batch", idList);
//	}
	
	
}
