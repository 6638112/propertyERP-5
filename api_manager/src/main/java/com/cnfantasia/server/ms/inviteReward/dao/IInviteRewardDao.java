package com.cnfantasia.server.ms.inviteReward.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.inviteRewardConfig.dao.IInviteRewardConfigBaseDao;
import com.cnfantasia.server.ms.inviteReward.entity.InviteRewardConfigEntity;
import com.cnfantasia.server.ms.inviteReward.entity.InviteUserEntity;

public interface IInviteRewardDao extends IInviteRewardConfigBaseDao {
	/**
	 * 分页查询邀请人配置信息
	 * */
	public List<InviteRewardConfigEntity> query_InviteRewardConfig_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	/**
	 * 查询邀请人配置信息数量
	 * */
	public int query_InviteRewardConfig_forCount(Map<String, Object> paramMap);
	
	/**
	 * 根据ID查询邀请人配置信息
	 * */
	public InviteRewardConfigEntity query_InviteRewardConfig_byId(BigInteger id);
	
	/**
	 * 查询未邀请配置的用户信息
	 * */
	public List<InviteUserEntity> query_InviteUserList(Map<String, Object> paramMap);
	
	/**
	 * 查询注册新人是否已经领过验证门牌的奖励
	 * */
	public int query_registerInviteRewardRecord_forCount(Map<String, Object> paramMap);
	
	/**
	 * 查询邀请人配置信息数量
	 * */
	public int query_InviteRewardConfig_isRepeat(Map<String, Object> paramMap);
	
	/**
	 * 查询当前验证门牌的用户是否是玫瑰园业主
	 * */
	public int query_registerUserIsRG_forCount(Map<String, Object> paramMap);
}
