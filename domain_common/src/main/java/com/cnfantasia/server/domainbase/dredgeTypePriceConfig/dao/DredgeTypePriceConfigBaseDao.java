package com.cnfantasia.server.domainbase.dredgeTypePriceConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.entity.DredgeTypePriceConfig;

/**
 * 描述:(维修价格配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeTypePriceConfigBaseDao extends AbstractBaseDao implements IDredgeTypePriceConfigBaseDao{
	/**
	 * 根据条件查询(维修价格配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeTypePriceConfig> selectDredgeTypePriceConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeTypePriceConfigBase.select_dredgeTypePriceConfig",paramMap);
	}
	/**
	 * 按条件分页查询(维修价格配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeTypePriceConfig> selectDredgeTypePriceConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeTypePriceConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeTypePriceConfig> resMap= sqlSession.selectList("dredgeTypePriceConfigBase.select_dredgeTypePriceConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修价格配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeTypePriceConfig selectDredgeTypePriceConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeTypePriceConfigBase.select_dredgeTypePriceConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修价格配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeTypePriceConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeTypePriceConfigBase.select_dredgeTypePriceConfig_count", paramMap);
	}
	/**
	 * 往(维修价格配置表)新增一条记录
	 * @param dredgeTypePriceConfig
	 * @return
	 */
	@Override
	public int insertDredgeTypePriceConfig(DredgeTypePriceConfig dredgeTypePriceConfig){
		return sqlSession.insert("dredgeTypePriceConfigBase.insert_dredgeTypePriceConfig",dredgeTypePriceConfig);
	}
	/**
	 * 批量新增(维修价格配置表)信息
	 * @param dredgeTypePriceConfigList
	 * @return
	 */
	@Override
	public int insertDredgeTypePriceConfigBatch(List<DredgeTypePriceConfig> dredgeTypePriceConfigList) {
		if (dredgeTypePriceConfigList == null || dredgeTypePriceConfigList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = dredgeTypePriceConfigList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<DredgeTypePriceConfig> batchList = dredgeTypePriceConfigList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("dredgeTypePriceConfigBase.insert_dredgeTypePriceConfig_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(维修价格配置表)信息
	 * @param dredgeTypePriceConfig
	 * @return
	 */
	@Override
	public int updateDredgeTypePriceConfig(DredgeTypePriceConfig dredgeTypePriceConfig){
		return sqlSession.update("dredgeTypePriceConfigBase.update_dredgeTypePriceConfig", dredgeTypePriceConfig);
	}
	/**
	 * 批量更新(维修价格配置表)信息
	 * @param dredgeTypePriceConfigList
	 * @return
	 */
	@Override
	public int updateDredgeTypePriceConfigBatch(List<DredgeTypePriceConfig> dredgeTypePriceConfigList) {
		if (dredgeTypePriceConfigList == null || dredgeTypePriceConfigList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("dredgeTypePriceConfigBase.update_dredgeTypePriceConfig_Batch", dredgeTypePriceConfigList);
	}
	
	/**
	 * 根据序列号删除(维修价格配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypePriceConfigLogic(java.math.BigInteger id){
		DredgeTypePriceConfig dredgeTypePriceConfig = new DredgeTypePriceConfig();
		dredgeTypePriceConfig.setId(id);
		dredgeTypePriceConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeTypePriceConfigBase.delete_dredgeTypePriceConfig_Logic",dredgeTypePriceConfig);
	}
	
	/**
	 * 根据Id 批量删除(维修价格配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypePriceConfigLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<DredgeTypePriceConfig> delList = new java.util.ArrayList<DredgeTypePriceConfig>();
		for(java.math.BigInteger id:idList){
			DredgeTypePriceConfig dredgeTypePriceConfig = new DredgeTypePriceConfig();
			dredgeTypePriceConfig.setId(id);
			dredgeTypePriceConfig.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeTypePriceConfig);
		}
		return sqlSession.update("dredgeTypePriceConfigBase.delete_dredgeTypePriceConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修价格配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeTypePriceConfig(java.math.BigInteger id){
//		return sqlSession.delete("dredgeTypePriceConfigBase.delete_dredgeTypePriceConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修价格配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeTypePriceConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeTypePriceConfigBase.delete_dredgeTypePriceConfig_Batch", idList);
//	}
	
	
}
