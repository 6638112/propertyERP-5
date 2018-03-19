package com.cnfantasia.server.domainbase.inviteRewardConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardConfig.entity.InviteRewardConfig;

/**
 * 描述:(邀请奖励配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IInviteRewardConfigBaseDao {
	/**
	 * 根据条件查询(邀请奖励配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<InviteRewardConfig> selectInviteRewardConfigByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(邀请奖励配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<InviteRewardConfig> selectInviteRewardConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(邀请奖励配置表)信息
	 * @param id
	 * @return
	 */
	public InviteRewardConfig selectInviteRewardConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(邀请奖励配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectInviteRewardConfigCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(邀请奖励配置表)新增一条记录
	 * @param inviteRewardConfig
	 * @return
	 */
	public int insertInviteRewardConfig(InviteRewardConfig inviteRewardConfig);
	
	/**
	 * 批量新增(邀请奖励配置表)信息
	 * @param inviteRewardConfigList
	 * @return
	 */
	public int insertInviteRewardConfigBatch(List<InviteRewardConfig> inviteRewardConfigList);
	
	/**
	 * 更新(邀请奖励配置表)信息
	 * @param inviteRewardConfig
	 * @return
	 */
	public int updateInviteRewardConfig(InviteRewardConfig inviteRewardConfig);
	
	/**
	 * 批量更新(邀请奖励配置表)信息
	 * @param inviteRewardConfigList
	 * @return
	 */
	public int updateInviteRewardConfigBatch(List<InviteRewardConfig> inviteRewardConfigList);
	
	/**
	 * 根据序列号删除(邀请奖励配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteInviteRewardConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(邀请奖励配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteInviteRewardConfigLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(邀请奖励配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteInviteRewardConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteInviteRewardConfigBatch(List<java.math.BigInteger> idList);
	
	
}
