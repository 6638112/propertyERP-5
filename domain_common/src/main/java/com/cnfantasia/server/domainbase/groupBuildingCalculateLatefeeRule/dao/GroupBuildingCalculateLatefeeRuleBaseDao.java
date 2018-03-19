package com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;

/**
 * 描述:(小区滞纳金配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingCalculateLatefeeRuleBaseDao extends AbstractBaseDao implements IGroupBuildingCalculateLatefeeRuleBaseDao{
	/**
	 * 根据条件查询(小区滞纳金配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingCalculateLatefeeRule> selectGroupBuildingCalculateLatefeeRuleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupBuildingCalculateLatefeeRuleBase.select_groupBuildingCalculateLatefeeRule",paramMap);
	}
	/**
	 * 按条件分页查询(小区滞纳金配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingCalculateLatefeeRule> selectGroupBuildingCalculateLatefeeRuleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupBuildingCalculateLatefeeRuleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupBuildingCalculateLatefeeRule> resMap= sqlSession.selectList("groupBuildingCalculateLatefeeRuleBase.select_groupBuildingCalculateLatefeeRule_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区滞纳金配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingCalculateLatefeeRule selectGroupBuildingCalculateLatefeeRuleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupBuildingCalculateLatefeeRuleBase.select_groupBuildingCalculateLatefeeRule_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区滞纳金配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupBuildingCalculateLatefeeRuleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupBuildingCalculateLatefeeRuleBase.select_groupBuildingCalculateLatefeeRule_count", paramMap);
	}
	/**
	 * 往(小区滞纳金配置表)新增一条记录
	 * @param groupBuildingCalculateLatefeeRule
	 * @return
	 */
	@Override
	public int insertGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule){
		return sqlSession.insert("groupBuildingCalculateLatefeeRuleBase.insert_groupBuildingCalculateLatefeeRule",groupBuildingCalculateLatefeeRule);
	}
	/**
	 * 批量新增(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRuleList
	 * @return
	 */
	@Override
	public int insertGroupBuildingCalculateLatefeeRuleBatch(List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRuleList) {
		if (groupBuildingCalculateLatefeeRuleList == null || groupBuildingCalculateLatefeeRuleList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = groupBuildingCalculateLatefeeRuleList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<GroupBuildingCalculateLatefeeRule> batchList = groupBuildingCalculateLatefeeRuleList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("groupBuildingCalculateLatefeeRuleBase.insert_groupBuildingCalculateLatefeeRule_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRule
	 * @return
	 */
	@Override
	public int updateGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule){
		return sqlSession.update("groupBuildingCalculateLatefeeRuleBase.update_groupBuildingCalculateLatefeeRule", groupBuildingCalculateLatefeeRule);
	}
	/**
	 * 批量更新(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRuleList
	 * @return
	 */
	@Override
	public int updateGroupBuildingCalculateLatefeeRuleBatch(List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRuleList) {
		if (groupBuildingCalculateLatefeeRuleList == null || groupBuildingCalculateLatefeeRuleList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("groupBuildingCalculateLatefeeRuleBase.update_groupBuildingCalculateLatefeeRule_Batch", groupBuildingCalculateLatefeeRuleList);
	}
	
	/**
	 * 根据序列号删除(小区滞纳金配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingCalculateLatefeeRuleLogic(java.math.BigInteger id){
		GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule = new GroupBuildingCalculateLatefeeRule();
		groupBuildingCalculateLatefeeRule.setId(id);
		groupBuildingCalculateLatefeeRule.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupBuildingCalculateLatefeeRuleBase.delete_groupBuildingCalculateLatefeeRule_Logic",groupBuildingCalculateLatefeeRule);
	}
	
	/**
	 * 根据Id 批量删除(小区滞纳金配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingCalculateLatefeeRuleLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<GroupBuildingCalculateLatefeeRule> delList = new java.util.ArrayList<GroupBuildingCalculateLatefeeRule>();
		for(java.math.BigInteger id:idList){
			GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule = new GroupBuildingCalculateLatefeeRule();
			groupBuildingCalculateLatefeeRule.setId(id);
			groupBuildingCalculateLatefeeRule.setSys0DelState(RecordState_DELETED);
			delList.add(groupBuildingCalculateLatefeeRule);
		}
		return sqlSession.update("groupBuildingCalculateLatefeeRuleBase.delete_groupBuildingCalculateLatefeeRule_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区滞纳金配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingCalculateLatefeeRule(java.math.BigInteger id){
//		return sqlSession.delete("groupBuildingCalculateLatefeeRuleBase.delete_groupBuildingCalculateLatefeeRule", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区滞纳金配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingCalculateLatefeeRuleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupBuildingCalculateLatefeeRuleBase.delete_groupBuildingCalculateLatefeeRule_Batch", idList);
//	}
	
	
}
