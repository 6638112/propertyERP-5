package com.cnfantasia.server.domainbase.inviteRewardRelation.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.inviteRewardRelation.entity.InviteRewardRelation;

/**
 * 描述:(邀请奖励关系纪录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IInviteRewardRelationBaseService {
	
	/**
	 * 根据条件查询(邀请奖励关系纪录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<InviteRewardRelation> getInviteRewardRelationByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(邀请奖励关系纪录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<InviteRewardRelation> getInviteRewardRelationByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(邀请奖励关系纪录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<InviteRewardRelation> getInviteRewardRelationByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(邀请奖励关系纪录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<InviteRewardRelation> getInviteRewardRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(邀请奖励关系纪录表)信息
	 * @param id
	 * @return
	 */
	public InviteRewardRelation getInviteRewardRelationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(邀请奖励关系纪录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getInviteRewardRelationCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(邀请奖励关系纪录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getInviteRewardRelationCountDim(Map<String,Object> paramMap);
	/**
	 * 往(邀请奖励关系纪录表)新增一条记录
	 * @param inviteRewardRelation
	 * @return
	 */
	public int insertInviteRewardRelation(InviteRewardRelation inviteRewardRelation);
	/**
	 * 批量新增(邀请奖励关系纪录表)
	 * @param inviteRewardRelationList
	 * @return
	 */
	public int insertInviteRewardRelationBatch(List<InviteRewardRelation> inviteRewardRelationList);
	/**
	 * 更新(邀请奖励关系纪录表)信息
	 * @param inviteRewardRelation
	 * @return
	 */
	public int updateInviteRewardRelation(InviteRewardRelation inviteRewardRelation);
	/**
	 * 批量更新(邀请奖励关系纪录表)信息
	 * @param inviteRewardRelationList
	 * @return
	 */
	public int updateInviteRewardRelationBatch(List<InviteRewardRelation> inviteRewardRelationList);
	/**
	 * 根据序列号删除(邀请奖励关系纪录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteInviteRewardRelationLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(邀请奖励关系纪录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteInviteRewardRelationLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(邀请奖励关系纪录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteInviteRewardRelation(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励关系纪录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteInviteRewardRelationBatch(List<java.math.BigInteger> idList);
	
}
