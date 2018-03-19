package com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.entity.PrizeRuleGenerateUsercount;

/**
 * 描述:(折扣生成规则-用户数量因素) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeRuleGenerateUsercountBaseDao extends AbstractBaseDao implements IPrizeRuleGenerateUsercountBaseDao{
	/**
	 * 根据条件查询(折扣生成规则-用户数量因素)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateUsercount> selectPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeRuleGenerateUsercountBase.select_prizeRuleGenerateUsercount",paramMap);
	}
	/**
	 * 按条件分页查询(折扣生成规则-用户数量因素)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateUsercount> selectPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeRuleGenerateUsercountCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeRuleGenerateUsercount> resMap= sqlSession.selectList("prizeRuleGenerateUsercountBase.select_prizeRuleGenerateUsercount_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(折扣生成规则-用户数量因素)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleGenerateUsercount selectPrizeRuleGenerateUsercountBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeRuleGenerateUsercountBase.select_prizeRuleGenerateUsercount_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(折扣生成规则-用户数量因素)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeRuleGenerateUsercountCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeRuleGenerateUsercountBase.select_prizeRuleGenerateUsercount_count", paramMap);
	}
	/**
	 * 往(折扣生成规则-用户数量因素)新增一条记录
	 * @param prizeRuleGenerateUsercount
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateUsercount(PrizeRuleGenerateUsercount prizeRuleGenerateUsercount){
		return sqlSession.insert("prizeRuleGenerateUsercountBase.insert_prizeRuleGenerateUsercount",prizeRuleGenerateUsercount);
	}
	/**
	 * 批量新增(折扣生成规则-用户数量因素)信息
	 * @param prizeRuleGenerateUsercountList
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateUsercountBatch(List<PrizeRuleGenerateUsercount> prizeRuleGenerateUsercountList) {
		return sqlSession.insert("prizeRuleGenerateUsercountBase.insert_prizeRuleGenerateUsercount_Batch",prizeRuleGenerateUsercountList);
	}
	
	/**
	 * 更新(折扣生成规则-用户数量因素)信息
	 * @param prizeRuleGenerateUsercount
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateUsercount(PrizeRuleGenerateUsercount prizeRuleGenerateUsercount){
		return sqlSession.update("prizeRuleGenerateUsercountBase.update_prizeRuleGenerateUsercount", prizeRuleGenerateUsercount);
	}
	/**
	 * 批量更新(折扣生成规则-用户数量因素)信息
	 * @param prizeRuleGenerateUsercountList
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateUsercountBatch(List<PrizeRuleGenerateUsercount> prizeRuleGenerateUsercountList) {
		return sqlSession.update("prizeRuleGenerateUsercountBase.update_prizeRuleGenerateUsercount_Batch", prizeRuleGenerateUsercountList);
	}
	
	/**
	 * 根据序列号删除(折扣生成规则-用户数量因素)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateUsercountLogic(java.math.BigInteger id){
		PrizeRuleGenerateUsercount prizeRuleGenerateUsercount = new PrizeRuleGenerateUsercount();
		prizeRuleGenerateUsercount.setId(id);
		prizeRuleGenerateUsercount.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeRuleGenerateUsercountBase.delete_prizeRuleGenerateUsercount_Logic",prizeRuleGenerateUsercount);
	}
	
	/**
	 * 根据Id 批量删除(折扣生成规则-用户数量因素)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateUsercountLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeRuleGenerateUsercount> delList = new java.util.ArrayList<PrizeRuleGenerateUsercount>();
		for(java.math.BigInteger id:idList){
			PrizeRuleGenerateUsercount prizeRuleGenerateUsercount = new PrizeRuleGenerateUsercount();
			prizeRuleGenerateUsercount.setId(id);
			prizeRuleGenerateUsercount.setSys0DelState(RecordState_DELETED);
			delList.add(prizeRuleGenerateUsercount);
		}
		return sqlSession.update("prizeRuleGenerateUsercountBase.delete_prizeRuleGenerateUsercount_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(折扣生成规则-用户数量因素)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateUsercount(java.math.BigInteger id){
//		return sqlSession.delete("prizeRuleGenerateUsercountBase.delete_prizeRuleGenerateUsercount", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(折扣生成规则-用户数量因素)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateUsercountBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeRuleGenerateUsercountBase.delete_prizeRuleGenerateUsercount_Batch", idList);
//	}
	
	
}
