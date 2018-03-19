package com.cnfantasia.server.domainbase.propertyRechargePreferRule.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRechargePreferRule.entity.PropertyRechargePreferRule;

/**
 * 描述:(物业预充值随机立减规则表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyRechargePreferRuleBaseDao extends AbstractBaseDao implements IPropertyRechargePreferRuleBaseDao{
	/**
	 * 根据条件查询(物业预充值随机立减规则表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferRule> selectPropertyRechargePreferRuleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyRechargePreferRuleBase.select_propertyRechargePreferRule",paramMap);
	}
	/**
	 * 按条件分页查询(物业预充值随机立减规则表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferRule> selectPropertyRechargePreferRuleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyRechargePreferRuleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyRechargePreferRule> resMap= sqlSession.selectList("propertyRechargePreferRuleBase.select_propertyRechargePreferRule_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业预充值随机立减规则表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRechargePreferRule selectPropertyRechargePreferRuleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyRechargePreferRuleBase.select_propertyRechargePreferRule_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减规则表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyRechargePreferRuleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyRechargePreferRuleBase.select_propertyRechargePreferRule_count", paramMap);
	}
	/**
	 * 往(物业预充值随机立减规则表)新增一条记录
	 * @param propertyRechargePreferRule
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferRule(PropertyRechargePreferRule propertyRechargePreferRule){
		return sqlSession.insert("propertyRechargePreferRuleBase.insert_propertyRechargePreferRule",propertyRechargePreferRule);
	}
	/**
	 * 批量新增(物业预充值随机立减规则表)信息
	 * @param propertyRechargePreferRuleList
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferRuleBatch(List<PropertyRechargePreferRule> propertyRechargePreferRuleList) {
		if (propertyRechargePreferRuleList == null || propertyRechargePreferRuleList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyRechargePreferRuleList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyRechargePreferRule> batchList = propertyRechargePreferRuleList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyRechargePreferRuleBase.insert_propertyRechargePreferRule_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业预充值随机立减规则表)信息
	 * @param propertyRechargePreferRule
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferRule(PropertyRechargePreferRule propertyRechargePreferRule){
		return sqlSession.update("propertyRechargePreferRuleBase.update_propertyRechargePreferRule", propertyRechargePreferRule);
	}
	/**
	 * 批量更新(物业预充值随机立减规则表)信息
	 * @param propertyRechargePreferRuleList
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferRuleBatch(List<PropertyRechargePreferRule> propertyRechargePreferRuleList) {
		if (propertyRechargePreferRuleList == null || propertyRechargePreferRuleList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyRechargePreferRuleBase.update_propertyRechargePreferRule_Batch", propertyRechargePreferRuleList);
	}
	
	/**
	 * 根据序列号删除(物业预充值随机立减规则表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferRuleLogic(java.math.BigInteger id){
		PropertyRechargePreferRule propertyRechargePreferRule = new PropertyRechargePreferRule();
		propertyRechargePreferRule.setId(id);
		propertyRechargePreferRule.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyRechargePreferRuleBase.delete_propertyRechargePreferRule_Logic",propertyRechargePreferRule);
	}
	
	/**
	 * 根据Id 批量删除(物业预充值随机立减规则表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferRuleLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyRechargePreferRule> delList = new java.util.ArrayList<PropertyRechargePreferRule>();
		for(java.math.BigInteger id:idList){
			PropertyRechargePreferRule propertyRechargePreferRule = new PropertyRechargePreferRule();
			propertyRechargePreferRule.setId(id);
			propertyRechargePreferRule.setSys0DelState(RecordState_DELETED);
			delList.add(propertyRechargePreferRule);
		}
		return sqlSession.update("propertyRechargePreferRuleBase.delete_propertyRechargePreferRule_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业预充值随机立减规则表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferRule(java.math.BigInteger id){
//		return sqlSession.delete("propertyRechargePreferRuleBase.delete_propertyRechargePreferRule", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业预充值随机立减规则表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferRuleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyRechargePreferRuleBase.delete_propertyRechargePreferRule_Batch", idList);
//	}
	
	
}
