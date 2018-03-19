package com.cnfantasia.server.api.inviteReward.service;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.user.entity.User;

public interface IInviteRewardService {
	/**
	 * 神码行动新增邀请人和注册新人的注册粮票奖励
	 * @author huangzj
	 * @param registerUserId
	 * @param inviteNo
	 * */
	void excuteInviteRewardForRegister(BigInteger registerUserId, String inviteNo, String mobile);
	
	/**
	 * 神码行动新增邀请人和注册新人的验证门牌粮票奖励
	 * @author huangzj
	 * @param registerUserId
	 * @param inviteNo
	 * */
	void excuteInviteRewardForVerifyRoom(BigInteger registerUserId, BigInteger roomId);
	
	/**
	 * 神码行动--用户后续补充邀请码，给用户补充奖励
	 * @author huangzj
	 * @param User
	 * */
	void excuteInviteRewardForCompleteInviteNO(User user, String inviteNo);
}
