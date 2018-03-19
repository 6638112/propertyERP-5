package com.cnfantasia.server.api.inviteReward.dao;

import java.math.BigInteger;
import java.util.Map;

import com.cnfantasia.server.domainbase.inviteRewardConfig.dao.IInviteRewardConfigBaseDao;

public interface IInviteRewardDao extends IInviteRewardConfigBaseDao {
	/**
	 * 查询注册新人是否已经领过验证门牌的奖励
	 * */
	public int query_registerInviteRewardRecord_forCount(Map<String, Object> paramMap);
	
	/**
	 * 查询邀请手机号是否注册,返回注册的主账号
	 * */
	public BigInteger query_userId_forInvitePhone(Map<String, Object> paramMap);
	
	/**
	 * 查询当前验证门牌的用户是否是玫瑰园业主
	 * */
	public int query_registerUserIsRG_forCount(Map<String, Object> paramMap);
}
