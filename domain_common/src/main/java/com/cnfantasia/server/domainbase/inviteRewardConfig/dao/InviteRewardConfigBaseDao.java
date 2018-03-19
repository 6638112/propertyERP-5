package com.cnfantasia.server.domainbase.inviteRewardConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardConfig.entity.InviteRewardConfig;

/**
 * 描述:(邀请奖励配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class InviteRewardConfigBaseDao extends AbstractBaseDao implements IInviteRewardConfigBaseDao{
	/**
	 * 根据条件查询(邀请奖励配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<InviteRewardConfig> selectInviteRewardConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("inviteRewardConfigBase.select_inviteRewardConfig",paramMap);
	}
	/**
	 * 按条件分页查询(邀请奖励配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<InviteRewardConfig> selectInviteRewardConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectInviteRewardConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<InviteRewardConfig> resMap= sqlSession.selectList("inviteRewardConfigBase.select_inviteRewardConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(邀请奖励配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public InviteRewardConfig selectInviteRewardConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("inviteRewardConfigBase.select_inviteRewardConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectInviteRewardConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("inviteRewardConfigBase.select_inviteRewardConfig_count", paramMap);
	}
	/**
	 * 往(邀请奖励配置表)新增一条记录
	 * @param inviteRewardConfig
	 * @return
	 */
	@Override
	public int insertInviteRewardConfig(InviteRewardConfig inviteRewardConfig){
		return sqlSession.insert("inviteRewardConfigBase.insert_inviteRewardConfig",inviteRewardConfig);
	}
	/**
	 * 批量新增(邀请奖励配置表)信息
	 * @param inviteRewardConfigList
	 * @return
	 */
	@Override
	public int insertInviteRewardConfigBatch(List<InviteRewardConfig> inviteRewardConfigList) {
		return sqlSession.insert("inviteRewardConfigBase.insert_inviteRewardConfig_Batch",inviteRewardConfigList);
	}
	
	/**
	 * 更新(邀请奖励配置表)信息
	 * @param inviteRewardConfig
	 * @return
	 */
	@Override
	public int updateInviteRewardConfig(InviteRewardConfig inviteRewardConfig){
		return sqlSession.update("inviteRewardConfigBase.update_inviteRewardConfig", inviteRewardConfig);
	}
	/**
	 * 批量更新(邀请奖励配置表)信息
	 * @param inviteRewardConfigList
	 * @return
	 */
	@Override
	public int updateInviteRewardConfigBatch(List<InviteRewardConfig> inviteRewardConfigList) {
		return sqlSession.update("inviteRewardConfigBase.update_inviteRewardConfig_Batch", inviteRewardConfigList);
	}
	
	/**
	 * 根据序列号删除(邀请奖励配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardConfigLogic(java.math.BigInteger id){
		InviteRewardConfig inviteRewardConfig = new InviteRewardConfig();
		inviteRewardConfig.setId(id);
		inviteRewardConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("inviteRewardConfigBase.delete_inviteRewardConfig_Logic",inviteRewardConfig);
	}
	
	/**
	 * 根据Id 批量删除(邀请奖励配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardConfigLogicBatch(List<java.math.BigInteger> idList) {
		List<InviteRewardConfig> delList = new java.util.ArrayList<InviteRewardConfig>();
		for(java.math.BigInteger id:idList){
			InviteRewardConfig inviteRewardConfig = new InviteRewardConfig();
			inviteRewardConfig.setId(id);
			inviteRewardConfig.setSys0DelState(RecordState_DELETED);
			delList.add(inviteRewardConfig);
		}
		return sqlSession.update("inviteRewardConfigBase.delete_inviteRewardConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(邀请奖励配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardConfig(java.math.BigInteger id){
//		return sqlSession.delete("inviteRewardConfigBase.delete_inviteRewardConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("inviteRewardConfigBase.delete_inviteRewardConfig_Batch", idList);
//	}
	
	
}
