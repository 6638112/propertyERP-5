package com.cnfantasia.server.domainbase.inviteRewardConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.inviteRewardConfig.dao.IInviteRewardConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardConfig.entity.InviteRewardConfig;

/**
 * 描述:(邀请奖励配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class InviteRewardConfigBaseService implements IInviteRewardConfigBaseService{
	
	private IInviteRewardConfigBaseDao inviteRewardConfigBaseDao;
	public void setInviteRewardConfigBaseDao(IInviteRewardConfigBaseDao inviteRewardConfigBaseDao) {
		this.inviteRewardConfigBaseDao = inviteRewardConfigBaseDao;
	}
	/**
	 * 根据条件查询(邀请奖励配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<InviteRewardConfig> getInviteRewardConfigByCondition(Map<String,Object> paramMap){
		return inviteRewardConfigBaseDao.selectInviteRewardConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(邀请奖励配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<InviteRewardConfig> getInviteRewardConfigByConditionDim(Map<String,Object> paramMap){
		return inviteRewardConfigBaseDao.selectInviteRewardConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(邀请奖励配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<InviteRewardConfig> getInviteRewardConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return inviteRewardConfigBaseDao.selectInviteRewardConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(邀请奖励配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<InviteRewardConfig> getInviteRewardConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return inviteRewardConfigBaseDao.selectInviteRewardConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(邀请奖励配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public InviteRewardConfig getInviteRewardConfigBySeqId(java.math.BigInteger id){
		return inviteRewardConfigBaseDao.selectInviteRewardConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getInviteRewardConfigCount(Map<String,Object> paramMap){
		return inviteRewardConfigBaseDao.selectInviteRewardConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getInviteRewardConfigCountDim(Map<String,Object> paramMap){
		return inviteRewardConfigBaseDao.selectInviteRewardConfigCount(paramMap,true);
	}
	/**
	 * 往(邀请奖励配置表)新增一条记录
	 * @param inviteRewardConfig
	 * @return
	 */
	@Override
	public int insertInviteRewardConfig(InviteRewardConfig inviteRewardConfig){
		return inviteRewardConfigBaseDao.insertInviteRewardConfig(inviteRewardConfig);
	}
	/**
	 * 批量新增(邀请奖励配置表)
	 * @param inviteRewardConfigList
	 * @return
	 */
	@Override
	public int insertInviteRewardConfigBatch(List<InviteRewardConfig> inviteRewardConfigList){
		return inviteRewardConfigBaseDao.insertInviteRewardConfigBatch(inviteRewardConfigList);
	}
	/**
	 * 更新(邀请奖励配置表)信息
	 * @param inviteRewardConfig
	 * @return
	 */
	@Override
	public int updateInviteRewardConfig(InviteRewardConfig inviteRewardConfig){
		return inviteRewardConfigBaseDao.updateInviteRewardConfig(inviteRewardConfig);
	}
	/**
	 * 批量更新(邀请奖励配置表)信息
	 * @param inviteRewardConfigList
	 * @return
	 */
	@Override
	public int updateInviteRewardConfigBatch(List<InviteRewardConfig> inviteRewardConfigList){
		return inviteRewardConfigBaseDao.updateInviteRewardConfigBatch(inviteRewardConfigList);
	}
	/**
	 * 根据序列号删除(邀请奖励配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardConfigLogic(java.math.BigInteger id){
		return inviteRewardConfigBaseDao.deleteInviteRewardConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(邀请奖励配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardConfigLogicBatch(List<java.math.BigInteger> idList){
		return inviteRewardConfigBaseDao.deleteInviteRewardConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(邀请奖励配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardConfig(java.math.BigInteger id){
//		return inviteRewardConfigBaseDao.deleteInviteRewardConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardConfigBatch(List<java.math.BigInteger> idList){
//		return inviteRewardConfigBaseDao.deleteInviteRewardConfigBatch(idList);
//	}
	
}
