package com.cnfantasia.server.domainbase.gbSoftwareConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;

/**
 * 描述:(小区物业软件配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GbSoftwareConfigBaseDao extends AbstractBaseDao implements IGbSoftwareConfigBaseDao{
	/**
	 * 根据条件查询(小区物业软件配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GbSoftwareConfig> selectGbSoftwareConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("gbSoftwareConfigBase.select_gbSoftwareConfig",paramMap);
	}
	/**
	 * 按条件分页查询(小区物业软件配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GbSoftwareConfig> selectGbSoftwareConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGbSoftwareConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GbSoftwareConfig> resMap= sqlSession.selectList("gbSoftwareConfigBase.select_gbSoftwareConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区物业软件配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public GbSoftwareConfig selectGbSoftwareConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("gbSoftwareConfigBase.select_gbSoftwareConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区物业软件配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGbSoftwareConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("gbSoftwareConfigBase.select_gbSoftwareConfig_count", paramMap);
	}
	/**
	 * 往(小区物业软件配置)新增一条记录
	 * @param gbSoftwareConfig
	 * @return
	 */
	@Override
	public int insertGbSoftwareConfig(GbSoftwareConfig gbSoftwareConfig){
		return sqlSession.insert("gbSoftwareConfigBase.insert_gbSoftwareConfig",gbSoftwareConfig);
	}
	/**
	 * 批量新增(小区物业软件配置)信息
	 * @param gbSoftwareConfigList
	 * @return
	 */
	@Override
	public int insertGbSoftwareConfigBatch(List<GbSoftwareConfig> gbSoftwareConfigList) {
		return sqlSession.insert("gbSoftwareConfigBase.insert_gbSoftwareConfig_Batch",gbSoftwareConfigList);
	}
	
	/**
	 * 更新(小区物业软件配置)信息
	 * @param gbSoftwareConfig
	 * @return
	 */
	@Override
	public int updateGbSoftwareConfig(GbSoftwareConfig gbSoftwareConfig){
		return sqlSession.update("gbSoftwareConfigBase.update_gbSoftwareConfig", gbSoftwareConfig);
	}
	/**
	 * 批量更新(小区物业软件配置)信息
	 * @param gbSoftwareConfigList
	 * @return
	 */
	@Override
	public int updateGbSoftwareConfigBatch(List<GbSoftwareConfig> gbSoftwareConfigList) {
		return sqlSession.update("gbSoftwareConfigBase.update_gbSoftwareConfig_Batch", gbSoftwareConfigList);
	}
	
	/**
	 * 根据序列号删除(小区物业软件配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGbSoftwareConfigLogic(java.math.BigInteger id){
		GbSoftwareConfig gbSoftwareConfig = new GbSoftwareConfig();
		gbSoftwareConfig.setId(id);
		gbSoftwareConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("gbSoftwareConfigBase.delete_gbSoftwareConfig_Logic",gbSoftwareConfig);
	}
	
	/**
	 * 根据Id 批量删除(小区物业软件配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGbSoftwareConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<GbSoftwareConfig> delList = new java.util.ArrayList<GbSoftwareConfig>();
		for(java.math.BigInteger id:idList){
			GbSoftwareConfig gbSoftwareConfig = new GbSoftwareConfig();
			gbSoftwareConfig.setId(id);
			gbSoftwareConfig.setSys0DelState(RecordState_DELETED);
			delList.add(gbSoftwareConfig);
		}
		return sqlSession.update("gbSoftwareConfigBase.delete_gbSoftwareConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区物业软件配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGbSoftwareConfig(java.math.BigInteger id){
//		return sqlSession.delete("gbSoftwareConfigBase.delete_gbSoftwareConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区物业软件配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGbSoftwareConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("gbSoftwareConfigBase.delete_gbSoftwareConfig_Batch", idList);
//	}
	
	
}
