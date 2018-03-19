package com.cnfantasia.server.domainbase.revenueConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 描述:(收益规则配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RevenueConfigBaseDao extends AbstractBaseDao implements IRevenueConfigBaseDao{
	/**
	 * 根据条件查询(收益规则配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueConfig> selectRevenueConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("revenueConfigBase.select_revenueConfig",paramMap);
	}
	/**
	 * 按条件分页查询(收益规则配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueConfig> selectRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRevenueConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RevenueConfig> resMap= sqlSession.selectList("revenueConfigBase.select_revenueConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(收益规则配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueConfig selectRevenueConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("revenueConfigBase.select_revenueConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(收益规则配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRevenueConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("revenueConfigBase.select_revenueConfig_count", paramMap);
	}
	/**
	 * 往(收益规则配置表)新增一条记录
	 * @param revenueConfig
	 * @return
	 */
	@Override
	public int insertRevenueConfig(RevenueConfig revenueConfig){
		return sqlSession.insert("revenueConfigBase.insert_revenueConfig",revenueConfig);
	}
	/**
	 * 批量新增(收益规则配置表)信息
	 * @param revenueConfigList
	 * @return
	 */
	@Override
	public int insertRevenueConfigBatch(List<RevenueConfig> revenueConfigList) {
		return sqlSession.insert("revenueConfigBase.insert_revenueConfig_Batch",revenueConfigList);
	}
	
	/**
	 * 更新(收益规则配置表)信息
	 * @param revenueConfig
	 * @return
	 */
	@Override
	public int updateRevenueConfig(RevenueConfig revenueConfig){
		return sqlSession.update("revenueConfigBase.update_revenueConfig", revenueConfig);
	}
	/**
	 * 批量更新(收益规则配置表)信息
	 * @param revenueConfigList
	 * @return
	 */
	@Override
	public int updateRevenueConfigBatch(List<RevenueConfig> revenueConfigList) {
		return sqlSession.update("revenueConfigBase.update_revenueConfig_Batch", revenueConfigList);
	}
	
	/**
	 * 根据序列号删除(收益规则配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueConfigLogic(java.math.BigInteger id){
		RevenueConfig revenueConfig = new RevenueConfig();
		revenueConfig.setId(id);
		revenueConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("revenueConfigBase.delete_revenueConfig_Logic",revenueConfig);
	}
	
	/**
	 * 根据Id 批量删除(收益规则配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<RevenueConfig> delList = new java.util.ArrayList<RevenueConfig>();
		for(java.math.BigInteger id:idList){
			RevenueConfig revenueConfig = new RevenueConfig();
			revenueConfig.setId(id);
			revenueConfig.setSys0DelState(RecordState_DELETED);
			delList.add(revenueConfig);
		}
		return sqlSession.update("revenueConfigBase.delete_revenueConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(收益规则配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueConfig(java.math.BigInteger id){
//		return sqlSession.delete("revenueConfigBase.delete_revenueConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收益规则配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("revenueConfigBase.delete_revenueConfig_Batch", idList);
//	}
	
	
}
