package com.cnfantasia.server.domainbase.prizeRuleConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleConfig.entity.PrizeRuleConfig;

/**
 * 描述:(抽奖规则配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeRuleConfigBaseDao extends AbstractBaseDao implements IPrizeRuleConfigBaseDao{
	/**
	 * 根据条件查询(抽奖规则配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleConfig> selectPrizeRuleConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeRuleConfigBase.select_prizeRuleConfig",paramMap);
	}
	/**
	 * 按条件分页查询(抽奖规则配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleConfig> selectPrizeRuleConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeRuleConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeRuleConfig> resMap= sqlSession.selectList("prizeRuleConfigBase.select_prizeRuleConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抽奖规则配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleConfig selectPrizeRuleConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeRuleConfigBase.select_prizeRuleConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖规则配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeRuleConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeRuleConfigBase.select_prizeRuleConfig_count", paramMap);
	}
	/**
	 * 往(抽奖规则配置)新增一条记录
	 * @param prizeRuleConfig
	 * @return
	 */
	@Override
	public int insertPrizeRuleConfig(PrizeRuleConfig prizeRuleConfig){
		return sqlSession.insert("prizeRuleConfigBase.insert_prizeRuleConfig",prizeRuleConfig);
	}
	/**
	 * 批量新增(抽奖规则配置)信息
	 * @param prizeRuleConfigList
	 * @return
	 */
	@Override
	public int insertPrizeRuleConfigBatch(List<PrizeRuleConfig> prizeRuleConfigList) {
		return sqlSession.insert("prizeRuleConfigBase.insert_prizeRuleConfig_Batch",prizeRuleConfigList);
	}
	
	/**
	 * 更新(抽奖规则配置)信息
	 * @param prizeRuleConfig
	 * @return
	 */
	@Override
	public int updatePrizeRuleConfig(PrizeRuleConfig prizeRuleConfig){
		return sqlSession.update("prizeRuleConfigBase.update_prizeRuleConfig", prizeRuleConfig);
	}
	/**
	 * 批量更新(抽奖规则配置)信息
	 * @param prizeRuleConfigList
	 * @return
	 */
	@Override
	public int updatePrizeRuleConfigBatch(List<PrizeRuleConfig> prizeRuleConfigList) {
		return sqlSession.update("prizeRuleConfigBase.update_prizeRuleConfig_Batch", prizeRuleConfigList);
	}
	
	/**
	 * 根据序列号删除(抽奖规则配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleConfigLogic(java.math.BigInteger id){
		PrizeRuleConfig prizeRuleConfig = new PrizeRuleConfig();
		prizeRuleConfig.setId(id);
		prizeRuleConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeRuleConfigBase.delete_prizeRuleConfig_Logic",prizeRuleConfig);
	}
	
	/**
	 * 根据Id 批量删除(抽奖规则配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeRuleConfig> delList = new java.util.ArrayList<PrizeRuleConfig>();
		for(java.math.BigInteger id:idList){
			PrizeRuleConfig prizeRuleConfig = new PrizeRuleConfig();
			prizeRuleConfig.setId(id);
			prizeRuleConfig.setSys0DelState(RecordState_DELETED);
			delList.add(prizeRuleConfig);
		}
		return sqlSession.update("prizeRuleConfigBase.delete_prizeRuleConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖规则配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleConfig(java.math.BigInteger id){
//		return sqlSession.delete("prizeRuleConfigBase.delete_prizeRuleConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖规则配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeRuleConfigBase.delete_prizeRuleConfig_Batch", idList);
//	}
	
	
}
