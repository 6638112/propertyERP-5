package com.cnfantasia.server.domainbase.operationMsgPushConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationMsgPushConfig.entity.OperationMsgPushConfig;

/**
 * 描述:(运营消息推送配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OperationMsgPushConfigBaseDao extends AbstractBaseDao implements IOperationMsgPushConfigBaseDao{
	/**
	 * 根据条件查询(运营消息推送配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OperationMsgPushConfig> selectOperationMsgPushConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("operationMsgPushConfigBase.select_operationMsgPushConfig",paramMap);
	}
	/**
	 * 按条件分页查询(运营消息推送配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OperationMsgPushConfig> selectOperationMsgPushConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOperationMsgPushConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OperationMsgPushConfig> resMap= sqlSession.selectList("operationMsgPushConfigBase.select_operationMsgPushConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(运营消息推送配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationMsgPushConfig selectOperationMsgPushConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("operationMsgPushConfigBase.select_operationMsgPushConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOperationMsgPushConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("operationMsgPushConfigBase.select_operationMsgPushConfig_count", paramMap);
	}
	/**
	 * 往(运营消息推送配置表)新增一条记录
	 * @param operationMsgPushConfig
	 * @return
	 */
	@Override
	public int insertOperationMsgPushConfig(OperationMsgPushConfig operationMsgPushConfig){
		return sqlSession.insert("operationMsgPushConfigBase.insert_operationMsgPushConfig",operationMsgPushConfig);
	}
	/**
	 * 批量新增(运营消息推送配置表)信息
	 * @param operationMsgPushConfigList
	 * @return
	 */
	@Override
	public int insertOperationMsgPushConfigBatch(List<OperationMsgPushConfig> operationMsgPushConfigList) {
		if (operationMsgPushConfigList == null || operationMsgPushConfigList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = operationMsgPushConfigList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<OperationMsgPushConfig> batchList = operationMsgPushConfigList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("operationMsgPushConfigBase.insert_operationMsgPushConfig_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(运营消息推送配置表)信息
	 * @param operationMsgPushConfig
	 * @return
	 */
	@Override
	public int updateOperationMsgPushConfig(OperationMsgPushConfig operationMsgPushConfig){
		return sqlSession.update("operationMsgPushConfigBase.update_operationMsgPushConfig", operationMsgPushConfig);
	}
	/**
	 * 批量更新(运营消息推送配置表)信息
	 * @param operationMsgPushConfigList
	 * @return
	 */
	@Override
	public int updateOperationMsgPushConfigBatch(List<OperationMsgPushConfig> operationMsgPushConfigList) {
		if (operationMsgPushConfigList == null || operationMsgPushConfigList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("operationMsgPushConfigBase.update_operationMsgPushConfig_Batch", operationMsgPushConfigList);
	}
	
	/**
	 * 根据序列号删除(运营消息推送配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationMsgPushConfigLogic(java.math.BigInteger id){
		OperationMsgPushConfig operationMsgPushConfig = new OperationMsgPushConfig();
		operationMsgPushConfig.setId(id);
		operationMsgPushConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("operationMsgPushConfigBase.delete_operationMsgPushConfig_Logic",operationMsgPushConfig);
	}
	
	/**
	 * 根据Id 批量删除(运营消息推送配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationMsgPushConfigLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<OperationMsgPushConfig> delList = new java.util.ArrayList<OperationMsgPushConfig>();
		for(java.math.BigInteger id:idList){
			OperationMsgPushConfig operationMsgPushConfig = new OperationMsgPushConfig();
			operationMsgPushConfig.setId(id);
			operationMsgPushConfig.setSys0DelState(RecordState_DELETED);
			delList.add(operationMsgPushConfig);
		}
		return sqlSession.update("operationMsgPushConfigBase.delete_operationMsgPushConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(运营消息推送配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationMsgPushConfig(java.math.BigInteger id){
//		return sqlSession.delete("operationMsgPushConfigBase.delete_operationMsgPushConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运营消息推送配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationMsgPushConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("operationMsgPushConfigBase.delete_operationMsgPushConfig_Batch", idList);
//	}
	
	
}
