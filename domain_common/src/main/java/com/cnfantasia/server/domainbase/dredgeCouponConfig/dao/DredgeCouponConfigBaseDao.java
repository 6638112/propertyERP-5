package com.cnfantasia.server.domainbase.dredgeCouponConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeCouponConfig.entity.DredgeCouponConfig;

/**
 * 描述:(维修券使用配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeCouponConfigBaseDao extends AbstractBaseDao implements IDredgeCouponConfigBaseDao{
	/**
	 * 根据条件查询(维修券使用配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeCouponConfig> selectDredgeCouponConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeCouponConfigBase.select_dredgeCouponConfig",paramMap);
	}
	/**
	 * 按条件分页查询(维修券使用配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeCouponConfig> selectDredgeCouponConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeCouponConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeCouponConfig> resMap= sqlSession.selectList("dredgeCouponConfigBase.select_dredgeCouponConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修券使用配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeCouponConfig selectDredgeCouponConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeCouponConfigBase.select_dredgeCouponConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修券使用配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeCouponConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeCouponConfigBase.select_dredgeCouponConfig_count", paramMap);
	}
	/**
	 * 往(维修券使用配置表)新增一条记录
	 * @param dredgeCouponConfig
	 * @return
	 */
	@Override
	public int insertDredgeCouponConfig(DredgeCouponConfig dredgeCouponConfig){
		return sqlSession.insert("dredgeCouponConfigBase.insert_dredgeCouponConfig",dredgeCouponConfig);
	}
	/**
	 * 批量新增(维修券使用配置表)信息
	 * @param dredgeCouponConfigList
	 * @return
	 */
	@Override
	public int insertDredgeCouponConfigBatch(List<DredgeCouponConfig> dredgeCouponConfigList) {
		return sqlSession.insert("dredgeCouponConfigBase.insert_dredgeCouponConfig_Batch",dredgeCouponConfigList);
	}
	
	/**
	 * 更新(维修券使用配置表)信息
	 * @param dredgeCouponConfig
	 * @return
	 */
	@Override
	public int updateDredgeCouponConfig(DredgeCouponConfig dredgeCouponConfig){
		return sqlSession.update("dredgeCouponConfigBase.update_dredgeCouponConfig", dredgeCouponConfig);
	}
	/**
	 * 批量更新(维修券使用配置表)信息
	 * @param dredgeCouponConfigList
	 * @return
	 */
	@Override
	public int updateDredgeCouponConfigBatch(List<DredgeCouponConfig> dredgeCouponConfigList) {
		return sqlSession.update("dredgeCouponConfigBase.update_dredgeCouponConfig_Batch", dredgeCouponConfigList);
	}
	
	/**
	 * 根据序列号删除(维修券使用配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeCouponConfigLogic(java.math.BigInteger id){
		DredgeCouponConfig dredgeCouponConfig = new DredgeCouponConfig();
		dredgeCouponConfig.setId(id);
		dredgeCouponConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeCouponConfigBase.delete_dredgeCouponConfig_Logic",dredgeCouponConfig);
	}
	
	/**
	 * 根据Id 批量删除(维修券使用配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeCouponConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeCouponConfig> delList = new java.util.ArrayList<DredgeCouponConfig>();
		for(java.math.BigInteger id:idList){
			DredgeCouponConfig dredgeCouponConfig = new DredgeCouponConfig();
			dredgeCouponConfig.setId(id);
			dredgeCouponConfig.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeCouponConfig);
		}
		return sqlSession.update("dredgeCouponConfigBase.delete_dredgeCouponConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修券使用配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeCouponConfig(java.math.BigInteger id){
//		return sqlSession.delete("dredgeCouponConfigBase.delete_dredgeCouponConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修券使用配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeCouponConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeCouponConfigBase.delete_dredgeCouponConfig_Batch", idList);
//	}
	
	
}
