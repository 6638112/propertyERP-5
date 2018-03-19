package com.cnfantasia.server.api.inviteReward.dao;
import java.math.BigInteger;
import java.util.Map;

import com.cnfantasia.server.domainbase.inviteRewardConfig.dao.InviteRewardConfigBaseDao;

public class InviteRewardDao extends InviteRewardConfigBaseDao implements IInviteRewardDao {

	@Override
	public int query_registerInviteRewardRecord_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("inviteReward.select_registerInviteRewardRecord_forCount", paramMap);
	}
	
	@Override
	public BigInteger query_userId_forInvitePhone(Map<String, Object> paramMap) {
		return sqlSession.selectOne("inviteReward.select_user_id_for_invite_phone", paramMap);
	}

	@Override
	public int query_registerUserIsRG_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("inviteReward.select_user_isRoseGraden_forCount", paramMap);
	}
}
