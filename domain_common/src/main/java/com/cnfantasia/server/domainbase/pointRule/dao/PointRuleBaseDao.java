package com.cnfantasia.server.domainbase.pointRule.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointRule.entity.PointRule;

/**
 * 描述:(积分规则) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PointRuleBaseDao extends AbstractBaseDao implements IPointRuleBaseDao{
	/**
	 * 根据条件查询(积分规则)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PointRule> selectPointRuleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("pointRuleBase.select_pointRule",paramMap);
	}
	/**
	 * 按条件分页查询(积分规则)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PointRule> selectPointRuleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPointRuleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PointRule> resMap= sqlSession.selectList("pointRuleBase.select_pointRule_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(积分规则)信息
	 * @param id
	 * @return
	 */
	@Override
	public PointRule selectPointRuleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("pointRuleBase.select_pointRule_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(积分规则)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPointRuleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("pointRuleBase.select_pointRule_count", paramMap);
	}
	/**
	 * 往(积分规则)新增一条记录
	 * @param pointRule
	 * @return
	 */
	@Override
	public int insertPointRule(PointRule pointRule){
		return sqlSession.insert("pointRuleBase.insert_pointRule",pointRule);
	}
	/**
	 * 批量新增(积分规则)信息
	 * @param pointRuleList
	 * @return
	 */
	@Override
	public int insertPointRuleBatch(List<PointRule> pointRuleList) {
		return sqlSession.insert("pointRuleBase.insert_pointRule_Batch",pointRuleList);
	}
	
	/**
	 * 更新(积分规则)信息
	 * @param pointRule
	 * @return
	 */
	@Override
	public int updatePointRule(PointRule pointRule){
		return sqlSession.update("pointRuleBase.update_pointRule", pointRule);
	}
	/**
	 * 批量更新(积分规则)信息
	 * @param pointRuleList
	 * @return
	 */
	@Override
	public int updatePointRuleBatch(List<PointRule> pointRuleList) {
		return sqlSession.update("pointRuleBase.update_pointRule_Batch", pointRuleList);
	}
	
	/**
	 * 根据序列号删除(积分规则)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePointRuleLogic(java.math.BigInteger id){
		PointRule pointRule = new PointRule();
		pointRule.setId(id);
		pointRule.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("pointRuleBase.delete_pointRule_Logic",pointRule);
	}
	
	/**
	 * 根据Id 批量删除(积分规则)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePointRuleLogicBatch(List<java.math.BigInteger> idList) {
		List<PointRule> delList = new java.util.ArrayList<PointRule>();
		for(java.math.BigInteger id:idList){
			PointRule pointRule = new PointRule();
			pointRule.setId(id);
			pointRule.setSys0DelState(RecordState_DELETED);
			delList.add(pointRule);
		}
		return sqlSession.update("pointRuleBase.delete_pointRule_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(积分规则)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePointRule(java.math.BigInteger id){
//		return sqlSession.delete("pointRuleBase.delete_pointRule", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(积分规则)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePointRuleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("pointRuleBase.delete_pointRule_Batch", idList);
//	}
	
	
}
