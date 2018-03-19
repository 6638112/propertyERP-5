package com.cnfantasia.server.domainbase.revenueGeneration.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueGeneration.entity.RevenueGeneration;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RevenueGenerationBaseDao extends AbstractBaseDao implements IRevenueGenerationBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueGeneration> selectRevenueGenerationByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("revenueGenerationBase.select_revenueGeneration",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueGeneration> selectRevenueGenerationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRevenueGenerationCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RevenueGeneration> resMap= sqlSession.selectList("revenueGenerationBase.select_revenueGeneration_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueGeneration selectRevenueGenerationBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("revenueGenerationBase.select_revenueGeneration_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRevenueGenerationCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("revenueGenerationBase.select_revenueGeneration_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param revenueGeneration
	 * @return
	 */
	@Override
	public int insertRevenueGeneration(RevenueGeneration revenueGeneration){
		return sqlSession.insert("revenueGenerationBase.insert_revenueGeneration",revenueGeneration);
	}
	/**
	 * 批量新增()信息
	 * @param revenueGenerationList
	 * @return
	 */
	@Override
	public int insertRevenueGenerationBatch(List<RevenueGeneration> revenueGenerationList) {
		return sqlSession.insert("revenueGenerationBase.insert_revenueGeneration_Batch",revenueGenerationList);
	}
	
	/**
	 * 更新()信息
	 * @param revenueGeneration
	 * @return
	 */
	@Override
	public int updateRevenueGeneration(RevenueGeneration revenueGeneration){
		return sqlSession.update("revenueGenerationBase.update_revenueGeneration", revenueGeneration);
	}
	/**
	 * 批量更新()信息
	 * @param revenueGenerationList
	 * @return
	 */
	@Override
	public int updateRevenueGenerationBatch(List<RevenueGeneration> revenueGenerationList) {
		return sqlSession.update("revenueGenerationBase.update_revenueGeneration_Batch", revenueGenerationList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteRevenueGenerationLogic(java.math.BigInteger id){
		RevenueGeneration revenueGeneration = new RevenueGeneration();
		revenueGeneration.setId(id);
		revenueGeneration.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("revenueGenerationBase.delete_revenueGeneration_Logic",revenueGeneration);
	}
	 */
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteRevenueGenerationLogicBatch(List<java.math.BigInteger> idList) {
		List<RevenueGeneration> delList = new java.util.ArrayList<RevenueGeneration>();
		for(java.math.BigInteger id:idList){
			RevenueGeneration revenueGeneration = new RevenueGeneration();
			revenueGeneration.setId(id);
			revenueGeneration.setSys0DelState(RecordState_DELETED);
			delList.add(revenueGeneration);
		}
		return sqlSession.update("revenueGenerationBase.delete_revenueGeneration_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueGeneration(java.math.BigInteger id){
//		return sqlSession.delete("revenueGenerationBase.delete_revenueGeneration", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueGenerationBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("revenueGenerationBase.delete_revenueGeneration_Batch", idList);
//	}
	
	
}
