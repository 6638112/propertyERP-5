package com.cnfantasia.server.domainbase.revenueApply.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;

/**
 * 描述:(提款申请记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RevenueApplyBaseDao extends AbstractBaseDao implements IRevenueApplyBaseDao{
	/**
	 * 根据条件查询(提款申请记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueApply> selectRevenueApplyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("revenueApplyBase.select_revenueApply",paramMap);
	}
	/**
	 * 按条件分页查询(提款申请记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueApply> selectRevenueApplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRevenueApplyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RevenueApply> resMap= sqlSession.selectList("revenueApplyBase.select_revenueApply_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(提款申请记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueApply selectRevenueApplyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("revenueApplyBase.select_revenueApply_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(提款申请记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRevenueApplyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("revenueApplyBase.select_revenueApply_count", paramMap);
	}
	/**
	 * 往(提款申请记录)新增一条记录
	 * @param revenueApply
	 * @return
	 */
	@Override
	public int insertRevenueApply(RevenueApply revenueApply){
		return sqlSession.insert("revenueApplyBase.insert_revenueApply",revenueApply);
	}
	/**
	 * 批量新增(提款申请记录)信息
	 * @param revenueApplyList
	 * @return
	 */
	@Override
	public int insertRevenueApplyBatch(List<RevenueApply> revenueApplyList) {
		return sqlSession.insert("revenueApplyBase.insert_revenueApply_Batch",revenueApplyList);
	}
	
	/**
	 * 更新(提款申请记录)信息
	 * @param revenueApply
	 * @return
	 */
	@Override
	public int updateRevenueApply(RevenueApply revenueApply){
		return sqlSession.update("revenueApplyBase.update_revenueApply", revenueApply);
	}
	/**
	 * 批量更新(提款申请记录)信息
	 * @param revenueApplyList
	 * @return
	 */
	@Override
	public int updateRevenueApplyBatch(List<RevenueApply> revenueApplyList) {
		return sqlSession.update("revenueApplyBase.update_revenueApply_Batch", revenueApplyList);
	}
	
	/**
	 * 根据序列号删除(提款申请记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueApplyLogic(java.math.BigInteger id){
		RevenueApply revenueApply = new RevenueApply();
		revenueApply.setId(id);
		revenueApply.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("revenueApplyBase.delete_revenueApply_Logic",revenueApply);
	}
	
	/**
	 * 根据Id 批量删除(提款申请记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueApplyLogicBatch(List<java.math.BigInteger> idList) {
		List<RevenueApply> delList = new java.util.ArrayList<RevenueApply>();
		for(java.math.BigInteger id:idList){
			RevenueApply revenueApply = new RevenueApply();
			revenueApply.setId(id);
			revenueApply.setSys0DelState(RecordState_DELETED);
			delList.add(revenueApply);
		}
		return sqlSession.update("revenueApplyBase.delete_revenueApply_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(提款申请记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueApply(java.math.BigInteger id){
//		return sqlSession.delete("revenueApplyBase.delete_revenueApply", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(提款申请记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueApplyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("revenueApplyBase.delete_revenueApply_Batch", idList);
//	}
	
	
}
