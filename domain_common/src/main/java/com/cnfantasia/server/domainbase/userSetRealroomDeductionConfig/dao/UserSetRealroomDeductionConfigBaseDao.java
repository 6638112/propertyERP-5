package com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.entity.UserSetRealroomDeductionConfig;

/**
 * 描述:(用户房间划扣配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserSetRealroomDeductionConfigBaseDao extends AbstractBaseDao implements IUserSetRealroomDeductionConfigBaseDao{
	/**
	 * 根据条件查询(用户房间划扣配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserSetRealroomDeductionConfig> selectUserSetRealroomDeductionConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userSetRealroomDeductionConfigBase.select_userSetRealroomDeductionConfig",paramMap);
	}
	/**
	 * 按条件分页查询(用户房间划扣配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserSetRealroomDeductionConfig> selectUserSetRealroomDeductionConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserSetRealroomDeductionConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserSetRealroomDeductionConfig> resMap= sqlSession.selectList("userSetRealroomDeductionConfigBase.select_userSetRealroomDeductionConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户房间划扣配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserSetRealroomDeductionConfig selectUserSetRealroomDeductionConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userSetRealroomDeductionConfigBase.select_userSetRealroomDeductionConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户房间划扣配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserSetRealroomDeductionConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userSetRealroomDeductionConfigBase.select_userSetRealroomDeductionConfig_count", paramMap);
	}
	/**
	 * 往(用户房间划扣配置)新增一条记录
	 * @param userSetRealroomDeductionConfig
	 * @return
	 */
	@Override
	public int insertUserSetRealroomDeductionConfig(UserSetRealroomDeductionConfig userSetRealroomDeductionConfig){
		return sqlSession.insert("userSetRealroomDeductionConfigBase.insert_userSetRealroomDeductionConfig",userSetRealroomDeductionConfig);
	}
	/**
	 * 批量新增(用户房间划扣配置)信息
	 * @param userSetRealroomDeductionConfigList
	 * @return
	 */
	@Override
	public int insertUserSetRealroomDeductionConfigBatch(List<UserSetRealroomDeductionConfig> userSetRealroomDeductionConfigList) {
		return sqlSession.insert("userSetRealroomDeductionConfigBase.insert_userSetRealroomDeductionConfig_Batch",userSetRealroomDeductionConfigList);
	}
	
	/**
	 * 更新(用户房间划扣配置)信息
	 * @param userSetRealroomDeductionConfig
	 * @return
	 */
	@Override
	public int updateUserSetRealroomDeductionConfig(UserSetRealroomDeductionConfig userSetRealroomDeductionConfig){
		return sqlSession.update("userSetRealroomDeductionConfigBase.update_userSetRealroomDeductionConfig", userSetRealroomDeductionConfig);
	}
	/**
	 * 批量更新(用户房间划扣配置)信息
	 * @param userSetRealroomDeductionConfigList
	 * @return
	 */
	@Override
	public int updateUserSetRealroomDeductionConfigBatch(List<UserSetRealroomDeductionConfig> userSetRealroomDeductionConfigList) {
		return sqlSession.update("userSetRealroomDeductionConfigBase.update_userSetRealroomDeductionConfig_Batch", userSetRealroomDeductionConfigList);
	}
	
	/**
	 * 根据序列号删除(用户房间划扣配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserSetRealroomDeductionConfigLogic(java.math.BigInteger id){
		UserSetRealroomDeductionConfig userSetRealroomDeductionConfig = new UserSetRealroomDeductionConfig();
		userSetRealroomDeductionConfig.setId(id);
		userSetRealroomDeductionConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userSetRealroomDeductionConfigBase.delete_userSetRealroomDeductionConfig_Logic",userSetRealroomDeductionConfig);
	}
	
	/**
	 * 根据Id 批量删除(用户房间划扣配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserSetRealroomDeductionConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<UserSetRealroomDeductionConfig> delList = new java.util.ArrayList<UserSetRealroomDeductionConfig>();
		for(java.math.BigInteger id:idList){
			UserSetRealroomDeductionConfig userSetRealroomDeductionConfig = new UserSetRealroomDeductionConfig();
			userSetRealroomDeductionConfig.setId(id);
			userSetRealroomDeductionConfig.setSys0DelState(RecordState_DELETED);
			delList.add(userSetRealroomDeductionConfig);
		}
		return sqlSession.update("userSetRealroomDeductionConfigBase.delete_userSetRealroomDeductionConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户房间划扣配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserSetRealroomDeductionConfig(java.math.BigInteger id){
//		return sqlSession.delete("userSetRealroomDeductionConfigBase.delete_userSetRealroomDeductionConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户房间划扣配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserSetRealroomDeductionConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userSetRealroomDeductionConfigBase.delete_userSetRealroomDeductionConfig_Batch", idList);
//	}
	
	
}
