package com.cnfantasia.server.domainbase.buildingKeyConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 描述:(门禁认证选项显示配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BuildingKeyConfigBaseDao extends AbstractBaseDao implements IBuildingKeyConfigBaseDao{
	/**
	 * 根据条件查询(门禁认证选项显示配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("buildingKeyConfigBase.select_buildingKeyConfig",paramMap);
	}
	/**
	 * 按条件分页查询(门禁认证选项显示配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBuildingKeyConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BuildingKeyConfig> resMap= sqlSession.selectList("buildingKeyConfigBase.select_buildingKeyConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(门禁认证选项显示配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public BuildingKeyConfig selectBuildingKeyConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("buildingKeyConfigBase.select_buildingKeyConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(门禁认证选项显示配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBuildingKeyConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("buildingKeyConfigBase.select_buildingKeyConfig_count", paramMap);
	}
	/**
	 * 往(门禁认证选项显示配置表)新增一条记录
	 * @param buildingKeyConfig
	 * @return
	 */
	@Override
	public int insertBuildingKeyConfig(BuildingKeyConfig buildingKeyConfig){
		return sqlSession.insert("buildingKeyConfigBase.insert_buildingKeyConfig",buildingKeyConfig);
	}
	/**
	 * 批量新增(门禁认证选项显示配置表)信息
	 * @param buildingKeyConfigList
	 * @return
	 */
	@Override
	public int insertBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList) {
		return sqlSession.insert("buildingKeyConfigBase.insert_buildingKeyConfig_Batch",buildingKeyConfigList);
	}
	
	/**
	 * 更新(门禁认证选项显示配置表)信息
	 * @param buildingKeyConfig
	 * @return
	 */
	@Override
	public int updateBuildingKeyConfig(BuildingKeyConfig buildingKeyConfig){
		return sqlSession.update("buildingKeyConfigBase.update_buildingKeyConfig", buildingKeyConfig);
	}
	/**
	 * 批量更新(门禁认证选项显示配置表)信息
	 * @param buildingKeyConfigList
	 * @return
	 */
	@Override
	public int updateBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList) {
		return sqlSession.update("buildingKeyConfigBase.update_buildingKeyConfig_Batch", buildingKeyConfigList);
	}
	
	/**
	 * 根据序列号删除(门禁认证选项显示配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyConfigLogic(java.math.BigInteger id){
		BuildingKeyConfig buildingKeyConfig = new BuildingKeyConfig();
		buildingKeyConfig.setId(id);
		buildingKeyConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("buildingKeyConfigBase.delete_buildingKeyConfig_Logic",buildingKeyConfig);
	}
	
	/**
	 * 根据Id 批量删除(门禁认证选项显示配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<BuildingKeyConfig> delList = new java.util.ArrayList<BuildingKeyConfig>();
		for(java.math.BigInteger id:idList){
			BuildingKeyConfig buildingKeyConfig = new BuildingKeyConfig();
			buildingKeyConfig.setId(id);
			buildingKeyConfig.setSys0DelState(RecordState_DELETED);
			delList.add(buildingKeyConfig);
		}
		return sqlSession.update("buildingKeyConfigBase.delete_buildingKeyConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(门禁认证选项显示配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyConfig(java.math.BigInteger id){
//		return sqlSession.delete("buildingKeyConfigBase.delete_buildingKeyConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门禁认证选项显示配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("buildingKeyConfigBase.delete_buildingKeyConfig_Batch", idList);
//	}
	
	
}
