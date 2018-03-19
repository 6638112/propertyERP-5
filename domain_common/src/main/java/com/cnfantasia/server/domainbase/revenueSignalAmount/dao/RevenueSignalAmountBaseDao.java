package com.cnfantasia.server.domainbase.revenueSignalAmount.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;

/**
 * 描述:(单个提款项的收益参数信息信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RevenueSignalAmountBaseDao extends AbstractBaseDao implements IRevenueSignalAmountBaseDao{
	/**
	 * 根据条件查询(单个提款项的收益参数信息信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueSignalAmount> selectRevenueSignalAmountByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("revenueSignalAmountBase.select_revenueSignalAmount",paramMap);
	}
	/**
	 * 按条件分页查询(单个提款项的收益参数信息信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueSignalAmount> selectRevenueSignalAmountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRevenueSignalAmountCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RevenueSignalAmount> resMap= sqlSession.selectList("revenueSignalAmountBase.select_revenueSignalAmount_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(单个提款项的收益参数信息信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueSignalAmount selectRevenueSignalAmountBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("revenueSignalAmountBase.select_revenueSignalAmount_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(单个提款项的收益参数信息信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRevenueSignalAmountCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("revenueSignalAmountBase.select_revenueSignalAmount_count", paramMap);
	}
	/**
	 * 往(单个提款项的收益参数信息信息)新增一条记录
	 * @param revenueSignalAmount
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmount(RevenueSignalAmount revenueSignalAmount){
		return sqlSession.insert("revenueSignalAmountBase.insert_revenueSignalAmount",revenueSignalAmount);
	}
	/**
	 * 批量新增(单个提款项的收益参数信息信息)信息
	 * @param revenueSignalAmountList
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmountBatch(List<RevenueSignalAmount> revenueSignalAmountList) {
		return sqlSession.insert("revenueSignalAmountBase.insert_revenueSignalAmount_Batch",revenueSignalAmountList);
	}
	
	/**
	 * 更新(单个提款项的收益参数信息信息)信息
	 * @param revenueSignalAmount
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmount(RevenueSignalAmount revenueSignalAmount){
		return sqlSession.update("revenueSignalAmountBase.update_revenueSignalAmount", revenueSignalAmount);
	}
	/**
	 * 批量更新(单个提款项的收益参数信息信息)信息
	 * @param revenueSignalAmountList
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmountBatch(List<RevenueSignalAmount> revenueSignalAmountList) {
		return sqlSession.update("revenueSignalAmountBase.update_revenueSignalAmount_Batch", revenueSignalAmountList);
	}
	
	/**
	 * 根据序列号删除(单个提款项的收益参数信息信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountLogic(java.math.BigInteger id){
		RevenueSignalAmount revenueSignalAmount = new RevenueSignalAmount();
		revenueSignalAmount.setId(id);
		revenueSignalAmount.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("revenueSignalAmountBase.delete_revenueSignalAmount_Logic",revenueSignalAmount);
	}
	
	/**
	 * 根据Id 批量删除(单个提款项的收益参数信息信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountLogicBatch(List<java.math.BigInteger> idList) {
		List<RevenueSignalAmount> delList = new java.util.ArrayList<RevenueSignalAmount>();
		for(java.math.BigInteger id:idList){
			RevenueSignalAmount revenueSignalAmount = new RevenueSignalAmount();
			revenueSignalAmount.setId(id);
			revenueSignalAmount.setSys0DelState(RecordState_DELETED);
			delList.add(revenueSignalAmount);
		}
		return sqlSession.update("revenueSignalAmountBase.delete_revenueSignalAmount_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(单个提款项的收益参数信息信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmount(java.math.BigInteger id){
//		return sqlSession.delete("revenueSignalAmountBase.delete_revenueSignalAmount", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(单个提款项的收益参数信息信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmountBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("revenueSignalAmountBase.delete_revenueSignalAmount_Batch", idList);
//	}
	
	
}
