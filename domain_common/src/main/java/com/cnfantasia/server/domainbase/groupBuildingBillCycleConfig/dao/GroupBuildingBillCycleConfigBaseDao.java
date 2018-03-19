package com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;

/**
 * 描述:(收费时间管理配置（自动生成账期和账单的配置）) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingBillCycleConfigBaseDao extends AbstractBaseDao implements IGroupBuildingBillCycleConfigBaseDao{
	/**
	 * 根据条件查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycleConfig> selectGroupBuildingBillCycleConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupBuildingBillCycleConfigBase.select_groupBuildingBillCycleConfig",paramMap);
	}
	/**
	 * 按条件分页查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycleConfig> selectGroupBuildingBillCycleConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupBuildingBillCycleConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupBuildingBillCycleConfig> resMap= sqlSession.selectList("groupBuildingBillCycleConfigBase.select_groupBuildingBillCycleConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingBillCycleConfig selectGroupBuildingBillCycleConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupBuildingBillCycleConfigBase.select_groupBuildingBillCycleConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(收费时间管理配置（自动生成账期和账单的配置）)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupBuildingBillCycleConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupBuildingBillCycleConfigBase.select_groupBuildingBillCycleConfig_count", paramMap);
	}
	/**
	 * 往(收费时间管理配置（自动生成账期和账单的配置）)新增一条记录
	 * @param groupBuildingBillCycleConfig
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig){
		return sqlSession.insert("groupBuildingBillCycleConfigBase.insert_groupBuildingBillCycleConfig",groupBuildingBillCycleConfig);
	}
	/**
	 * 批量新增(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param groupBuildingBillCycleConfigList
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycleConfigBatch(List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigList) {
		if (groupBuildingBillCycleConfigList == null || groupBuildingBillCycleConfigList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = groupBuildingBillCycleConfigList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<GroupBuildingBillCycleConfig> batchList = groupBuildingBillCycleConfigList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("groupBuildingBillCycleConfigBase.insert_groupBuildingBillCycleConfig_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param groupBuildingBillCycleConfig
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig){
		return sqlSession.update("groupBuildingBillCycleConfigBase.update_groupBuildingBillCycleConfig", groupBuildingBillCycleConfig);
	}
	/**
	 * 批量更新(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param groupBuildingBillCycleConfigList
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycleConfigBatch(List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigList) {
		if (groupBuildingBillCycleConfigList == null || groupBuildingBillCycleConfigList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("groupBuildingBillCycleConfigBase.update_groupBuildingBillCycleConfig_Batch", groupBuildingBillCycleConfigList);
	}
	
	/**
	 * 根据序列号删除(收费时间管理配置（自动生成账期和账单的配置）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleConfigLogic(java.math.BigInteger id){
		GroupBuildingBillCycleConfig groupBuildingBillCycleConfig = new GroupBuildingBillCycleConfig();
		groupBuildingBillCycleConfig.setId(id);
		groupBuildingBillCycleConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupBuildingBillCycleConfigBase.delete_groupBuildingBillCycleConfig_Logic",groupBuildingBillCycleConfig);
	}
	
	/**
	 * 根据Id 批量删除(收费时间管理配置（自动生成账期和账单的配置）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleConfigLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<GroupBuildingBillCycleConfig> delList = new java.util.ArrayList<GroupBuildingBillCycleConfig>();
		for(java.math.BigInteger id:idList){
			GroupBuildingBillCycleConfig groupBuildingBillCycleConfig = new GroupBuildingBillCycleConfig();
			groupBuildingBillCycleConfig.setId(id);
			groupBuildingBillCycleConfig.setSys0DelState(RecordState_DELETED);
			delList.add(groupBuildingBillCycleConfig);
		}
		return sqlSession.update("groupBuildingBillCycleConfigBase.delete_groupBuildingBillCycleConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(收费时间管理配置（自动生成账期和账单的配置）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycleConfig(java.math.BigInteger id){
//		return sqlSession.delete("groupBuildingBillCycleConfigBase.delete_groupBuildingBillCycleConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收费时间管理配置（自动生成账期和账单的配置）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycleConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupBuildingBillCycleConfigBase.delete_groupBuildingBillCycleConfig_Batch", idList);
//	}
	
	
}
