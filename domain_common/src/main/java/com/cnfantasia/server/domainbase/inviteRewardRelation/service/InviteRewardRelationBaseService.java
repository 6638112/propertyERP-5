package com.cnfantasia.server.domainbase.inviteRewardRelation.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.inviteRewardRelation.dao.IInviteRewardRelationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardRelation.entity.InviteRewardRelation;

/**
 * 描述:(邀请奖励关系纪录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class InviteRewardRelationBaseService implements IInviteRewardRelationBaseService{
	
	private IInviteRewardRelationBaseDao inviteRewardRelationBaseDao;
	public void setInviteRewardRelationBaseDao(IInviteRewardRelationBaseDao inviteRewardRelationBaseDao) {
		this.inviteRewardRelationBaseDao = inviteRewardRelationBaseDao;
	}
	/**
	 * 根据条件查询(邀请奖励关系纪录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<InviteRewardRelation> getInviteRewardRelationByCondition(Map<String,Object> paramMap){
		return inviteRewardRelationBaseDao.selectInviteRewardRelationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(邀请奖励关系纪录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<InviteRewardRelation> getInviteRewardRelationByConditionDim(Map<String,Object> paramMap){
		return inviteRewardRelationBaseDao.selectInviteRewardRelationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(邀请奖励关系纪录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<InviteRewardRelation> getInviteRewardRelationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return inviteRewardRelationBaseDao.selectInviteRewardRelationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(邀请奖励关系纪录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<InviteRewardRelation> getInviteRewardRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return inviteRewardRelationBaseDao.selectInviteRewardRelationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(邀请奖励关系纪录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public InviteRewardRelation getInviteRewardRelationBySeqId(java.math.BigInteger id){
		return inviteRewardRelationBaseDao.selectInviteRewardRelationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励关系纪录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getInviteRewardRelationCount(Map<String,Object> paramMap){
		return inviteRewardRelationBaseDao.selectInviteRewardRelationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励关系纪录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getInviteRewardRelationCountDim(Map<String,Object> paramMap){
		return inviteRewardRelationBaseDao.selectInviteRewardRelationCount(paramMap,true);
	}
	/**
	 * 往(邀请奖励关系纪录表)新增一条记录
	 * @param inviteRewardRelation
	 * @return
	 */
	@Override
	public int insertInviteRewardRelation(InviteRewardRelation inviteRewardRelation){
		return inviteRewardRelationBaseDao.insertInviteRewardRelation(inviteRewardRelation);
	}
	/**
	 * 批量新增(邀请奖励关系纪录表)
	 * @param inviteRewardRelationList
	 * @return
	 */
	@Override
	public int insertInviteRewardRelationBatch(List<InviteRewardRelation> inviteRewardRelationList){
		return inviteRewardRelationBaseDao.insertInviteRewardRelationBatch(inviteRewardRelationList);
	}
	/**
	 * 更新(邀请奖励关系纪录表)信息
	 * @param inviteRewardRelation
	 * @return
	 */
	@Override
	public int updateInviteRewardRelation(InviteRewardRelation inviteRewardRelation){
		return inviteRewardRelationBaseDao.updateInviteRewardRelation(inviteRewardRelation);
	}
	/**
	 * 批量更新(邀请奖励关系纪录表)信息
	 * @param inviteRewardRelationList
	 * @return
	 */
	@Override
	public int updateInviteRewardRelationBatch(List<InviteRewardRelation> inviteRewardRelationList){
		return inviteRewardRelationBaseDao.updateInviteRewardRelationBatch(inviteRewardRelationList);
	}
	/**
	 * 根据序列号删除(邀请奖励关系纪录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRelationLogic(java.math.BigInteger id){
		return inviteRewardRelationBaseDao.deleteInviteRewardRelationLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(邀请奖励关系纪录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRelationLogicBatch(List<java.math.BigInteger> idList){
		return inviteRewardRelationBaseDao.deleteInviteRewardRelationLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(邀请奖励关系纪录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRelation(java.math.BigInteger id){
//		return inviteRewardRelationBaseDao.deleteInviteRewardRelation(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励关系纪录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRelationBatch(List<java.math.BigInteger> idList){
//		return inviteRewardRelationBaseDao.deleteInviteRewardRelationBatch(idList);
//	}
	
}
