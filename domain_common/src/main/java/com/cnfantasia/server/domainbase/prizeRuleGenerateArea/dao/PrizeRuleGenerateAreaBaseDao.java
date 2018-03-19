package com.cnfantasia.server.domainbase.prizeRuleGenerateArea.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleGenerateArea.entity.PrizeRuleGenerateArea;

/**
 * 描述:(折扣生成规则-折扣区间分配因素) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeRuleGenerateAreaBaseDao extends AbstractBaseDao implements IPrizeRuleGenerateAreaBaseDao{
	/**
	 * 根据条件查询(折扣生成规则-折扣区间分配因素)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateArea> selectPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeRuleGenerateAreaBase.select_prizeRuleGenerateArea",paramMap);
	}
	/**
	 * 按条件分页查询(折扣生成规则-折扣区间分配因素)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleGenerateArea> selectPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeRuleGenerateAreaCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeRuleGenerateArea> resMap= sqlSession.selectList("prizeRuleGenerateAreaBase.select_prizeRuleGenerateArea_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(折扣生成规则-折扣区间分配因素)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleGenerateArea selectPrizeRuleGenerateAreaBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeRuleGenerateAreaBase.select_prizeRuleGenerateArea_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(折扣生成规则-折扣区间分配因素)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeRuleGenerateAreaCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeRuleGenerateAreaBase.select_prizeRuleGenerateArea_count", paramMap);
	}
	/**
	 * 往(折扣生成规则-折扣区间分配因素)新增一条记录
	 * @param prizeRuleGenerateArea
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateArea(PrizeRuleGenerateArea prizeRuleGenerateArea){
		return sqlSession.insert("prizeRuleGenerateAreaBase.insert_prizeRuleGenerateArea",prizeRuleGenerateArea);
	}
	/**
	 * 批量新增(折扣生成规则-折扣区间分配因素)信息
	 * @param prizeRuleGenerateAreaList
	 * @return
	 */
	@Override
	public int insertPrizeRuleGenerateAreaBatch(List<PrizeRuleGenerateArea> prizeRuleGenerateAreaList) {
		return sqlSession.insert("prizeRuleGenerateAreaBase.insert_prizeRuleGenerateArea_Batch",prizeRuleGenerateAreaList);
	}
	
	/**
	 * 更新(折扣生成规则-折扣区间分配因素)信息
	 * @param prizeRuleGenerateArea
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateArea(PrizeRuleGenerateArea prizeRuleGenerateArea){
		return sqlSession.update("prizeRuleGenerateAreaBase.update_prizeRuleGenerateArea", prizeRuleGenerateArea);
	}
	/**
	 * 批量更新(折扣生成规则-折扣区间分配因素)信息
	 * @param prizeRuleGenerateAreaList
	 * @return
	 */
	@Override
	public int updatePrizeRuleGenerateAreaBatch(List<PrizeRuleGenerateArea> prizeRuleGenerateAreaList) {
		return sqlSession.update("prizeRuleGenerateAreaBase.update_prizeRuleGenerateArea_Batch", prizeRuleGenerateAreaList);
	}
	
	/**
	 * 根据序列号删除(折扣生成规则-折扣区间分配因素)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateAreaLogic(java.math.BigInteger id){
		PrizeRuleGenerateArea prizeRuleGenerateArea = new PrizeRuleGenerateArea();
		prizeRuleGenerateArea.setId(id);
		prizeRuleGenerateArea.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeRuleGenerateAreaBase.delete_prizeRuleGenerateArea_Logic",prizeRuleGenerateArea);
	}
	
	/**
	 * 根据Id 批量删除(折扣生成规则-折扣区间分配因素)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleGenerateAreaLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeRuleGenerateArea> delList = new java.util.ArrayList<PrizeRuleGenerateArea>();
		for(java.math.BigInteger id:idList){
			PrizeRuleGenerateArea prizeRuleGenerateArea = new PrizeRuleGenerateArea();
			prizeRuleGenerateArea.setId(id);
			prizeRuleGenerateArea.setSys0DelState(RecordState_DELETED);
			delList.add(prizeRuleGenerateArea);
		}
		return sqlSession.update("prizeRuleGenerateAreaBase.delete_prizeRuleGenerateArea_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(折扣生成规则-折扣区间分配因素)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateArea(java.math.BigInteger id){
//		return sqlSession.delete("prizeRuleGenerateAreaBase.delete_prizeRuleGenerateArea", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(折扣生成规则-折扣区间分配因素)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleGenerateAreaBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeRuleGenerateAreaBase.delete_prizeRuleGenerateArea_Batch", idList);
//	}
	
	
}
