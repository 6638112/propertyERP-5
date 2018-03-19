package com.cnfantasia.server.ms.inviteReward.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.inviteReward.entity.InviteRewardConfigEntity;
import com.cnfantasia.server.ms.inviteReward.entity.InviteUserEntity;

public interface IInviteRewardService {
	/**
	 * 神码行动新增邀请人和注册新人的注册粮票奖励
	 * @author huangzj
	 * @param registerUserId
	 * @param inviteNo
	 * */
	void excuteInviteRewardForRegister(BigInteger registerUserId, String inviteNo);
	
	/**
	 * 神码行动新增邀请人和注册新人的验证门牌粮票奖励
	 * @author huangzj
	 * @param registerUserId
	 * @param inviteNo
	 * */
	void excuteInviteRewardForVerifyRoom(BigInteger registerUserId, BigInteger roomId);
	
	/**
	 * 分页查询邀请人配置信息
	 * */
	List<InviteRewardConfigEntity> query_InviteRewardConfig_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	/**
	 * 查询邀请人配置信息数量
	 * */
	int query_InviteRewardConfig_forCount(Map<String, Object> paramMap);
	
	/**
	 * 根据ID查询邀请人配置信息
	 * */
	InviteRewardConfigEntity query_InviteRewardConfig_byId(BigInteger id);
	
	/**
	 * 保存邀请人配置信息
	 * @param id
	 * @param inviteUserId
	 * @param inviteCodel
	 * @param inviteType
	 * */
	int saveOrUpdate_InviteRewardConfig(String id, String inviteUserId, String inviteCode, String inviteType);
	
	/**
	 * 查询未邀请配置的用户信息
	 * */
	List<InviteUserEntity> query_InviteUserList(Map<String, Object> paramMap);
	
	/**
	 * 启用或禁用邀请奖励用户配置
	 * */
	int enableOrDisableConfig(String id, boolean isEnable);
	
	/**
	 * 查询邀请人配置信息数量
	 * */
	int query_InviteRewardConfig_isRepeat(Map<String, Object> paramMap);
}
