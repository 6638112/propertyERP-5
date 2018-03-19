package com.cnfantasia.server.domainbase.redpointModelcodeConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointModelcodeConfig.entity.RedpointModelcodeConfig;

/**
 * 描述:(红点模块编码配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RedpointModelcodeConfigBaseDao extends AbstractBaseDao implements IRedpointModelcodeConfigBaseDao{
	/**
	 * 根据条件查询(红点模块编码配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RedpointModelcodeConfig> selectRedpointModelcodeConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("redpointModelcodeConfigBase.select_redpointModelcodeConfig",paramMap);
	}
	/**
	 * 按条件分页查询(红点模块编码配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RedpointModelcodeConfig> selectRedpointModelcodeConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRedpointModelcodeConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RedpointModelcodeConfig> resMap= sqlSession.selectList("redpointModelcodeConfigBase.select_redpointModelcodeConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(红点模块编码配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RedpointModelcodeConfig selectRedpointModelcodeConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("redpointModelcodeConfigBase.select_redpointModelcodeConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(红点模块编码配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRedpointModelcodeConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("redpointModelcodeConfigBase.select_redpointModelcodeConfig_count", paramMap);
	}
	/**
	 * 往(红点模块编码配置表)新增一条记录
	 * @param redpointModelcodeConfig
	 * @return
	 */
	@Override
	public int insertRedpointModelcodeConfig(RedpointModelcodeConfig redpointModelcodeConfig){
		return sqlSession.insert("redpointModelcodeConfigBase.insert_redpointModelcodeConfig",redpointModelcodeConfig);
	}
	/**
	 * 批量新增(红点模块编码配置表)信息
	 * @param redpointModelcodeConfigList
	 * @return
	 */
	@Override
	public int insertRedpointModelcodeConfigBatch(List<RedpointModelcodeConfig> redpointModelcodeConfigList) {
		return sqlSession.insert("redpointModelcodeConfigBase.insert_redpointModelcodeConfig_Batch",redpointModelcodeConfigList);
	}
	
	/**
	 * 更新(红点模块编码配置表)信息
	 * @param redpointModelcodeConfig
	 * @return
	 */
	@Override
	public int updateRedpointModelcodeConfig(RedpointModelcodeConfig redpointModelcodeConfig){
		return sqlSession.update("redpointModelcodeConfigBase.update_redpointModelcodeConfig", redpointModelcodeConfig);
	}
	/**
	 * 批量更新(红点模块编码配置表)信息
	 * @param redpointModelcodeConfigList
	 * @return
	 */
	@Override
	public int updateRedpointModelcodeConfigBatch(List<RedpointModelcodeConfig> redpointModelcodeConfigList) {
		return sqlSession.update("redpointModelcodeConfigBase.update_redpointModelcodeConfig_Batch", redpointModelcodeConfigList);
	}
	
	/**
	 * 根据序列号删除(红点模块编码配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRedpointModelcodeConfigLogic(java.math.BigInteger id){
		RedpointModelcodeConfig redpointModelcodeConfig = new RedpointModelcodeConfig();
		redpointModelcodeConfig.setId(id);
		redpointModelcodeConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("redpointModelcodeConfigBase.delete_redpointModelcodeConfig_Logic",redpointModelcodeConfig);
	}
	
	/**
	 * 根据Id 批量删除(红点模块编码配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRedpointModelcodeConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<RedpointModelcodeConfig> delList = new java.util.ArrayList<RedpointModelcodeConfig>();
		for(java.math.BigInteger id:idList){
			RedpointModelcodeConfig redpointModelcodeConfig = new RedpointModelcodeConfig();
			redpointModelcodeConfig.setId(id);
			redpointModelcodeConfig.setSys0DelState(RecordState_DELETED);
			delList.add(redpointModelcodeConfig);
		}
		return sqlSession.update("redpointModelcodeConfigBase.delete_redpointModelcodeConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(红点模块编码配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointModelcodeConfig(java.math.BigInteger id){
//		return sqlSession.delete("redpointModelcodeConfigBase.delete_redpointModelcodeConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(红点模块编码配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointModelcodeConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("redpointModelcodeConfigBase.delete_redpointModelcodeConfig_Batch", idList);
//	}
	
	
}
