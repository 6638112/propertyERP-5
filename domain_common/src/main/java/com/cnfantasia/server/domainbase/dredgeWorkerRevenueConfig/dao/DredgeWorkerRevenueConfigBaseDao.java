package com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.entity.DredgeWorkerRevenueConfig;

/**
 * 描述:(疏通师傅收益配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeWorkerRevenueConfigBaseDao extends AbstractBaseDao implements IDredgeWorkerRevenueConfigBaseDao{
	/**
	 * 根据条件查询(疏通师傅收益配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeWorkerRevenueConfig> selectDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeWorkerRevenueConfigBase.select_dredgeWorkerRevenueConfig",paramMap);
	}
	/**
	 * 按条件分页查询(疏通师傅收益配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeWorkerRevenueConfig> selectDredgeWorkerRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeWorkerRevenueConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeWorkerRevenueConfig> resMap= sqlSession.selectList("dredgeWorkerRevenueConfigBase.select_dredgeWorkerRevenueConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(疏通师傅收益配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorkerRevenueConfig selectDredgeWorkerRevenueConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeWorkerRevenueConfigBase.select_dredgeWorkerRevenueConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅收益配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeWorkerRevenueConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeWorkerRevenueConfigBase.select_dredgeWorkerRevenueConfig_count", paramMap);
	}
	/**
	 * 往(疏通师傅收益配置)新增一条记录
	 * @param dredgeWorkerRevenueConfig
	 * @return
	 */
	@Override
	public int insertDredgeWorkerRevenueConfig(DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig){
		return sqlSession.insert("dredgeWorkerRevenueConfigBase.insert_dredgeWorkerRevenueConfig",dredgeWorkerRevenueConfig);
	}
	/**
	 * 批量新增(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfigList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerRevenueConfigBatch(List<DredgeWorkerRevenueConfig> dredgeWorkerRevenueConfigList) {
		return sqlSession.insert("dredgeWorkerRevenueConfigBase.insert_dredgeWorkerRevenueConfig_Batch",dredgeWorkerRevenueConfigList);
	}
	
	/**
	 * 更新(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfig
	 * @return
	 */
	@Override
	public int updateDredgeWorkerRevenueConfig(DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig){
		return sqlSession.update("dredgeWorkerRevenueConfigBase.update_dredgeWorkerRevenueConfig", dredgeWorkerRevenueConfig);
	}
	/**
	 * 批量更新(疏通师傅收益配置)信息
	 * @param dredgeWorkerRevenueConfigList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerRevenueConfigBatch(List<DredgeWorkerRevenueConfig> dredgeWorkerRevenueConfigList) {
		return sqlSession.update("dredgeWorkerRevenueConfigBase.update_dredgeWorkerRevenueConfig_Batch", dredgeWorkerRevenueConfigList);
	}
	
	/**
	 * 根据序列号删除(疏通师傅收益配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeWorkerRevenueConfigLogic(java.math.BigInteger id){
		DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig = new DredgeWorkerRevenueConfig();
		dredgeWorkerRevenueConfig.setId(id);
		dredgeWorkerRevenueConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeWorkerRevenueConfigBase.delete_dredgeWorkerRevenueConfig_Logic",dredgeWorkerRevenueConfig);
	}
	
	/**
	 * 根据Id 批量删除(疏通师傅收益配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeWorkerRevenueConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeWorkerRevenueConfig> delList = new java.util.ArrayList<DredgeWorkerRevenueConfig>();
		for(java.math.BigInteger id:idList){
			DredgeWorkerRevenueConfig dredgeWorkerRevenueConfig = new DredgeWorkerRevenueConfig();
			dredgeWorkerRevenueConfig.setId(id);
			dredgeWorkerRevenueConfig.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeWorkerRevenueConfig);
		}
		return sqlSession.update("dredgeWorkerRevenueConfigBase.delete_dredgeWorkerRevenueConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(疏通师傅收益配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerRevenueConfig(java.math.BigInteger id){
//		return sqlSession.delete("dredgeWorkerRevenueConfigBase.delete_dredgeWorkerRevenueConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅收益配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerRevenueConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeWorkerRevenueConfigBase.delete_dredgeWorkerRevenueConfig_Batch", idList);
//	}
	
	
}
