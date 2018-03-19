package com.cnfantasia.server.ms.inviteReward.dao;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.inviteRewardConfig.dao.InviteRewardConfigBaseDao;
import com.cnfantasia.server.ms.inviteReward.entity.InviteRewardConfigEntity;
import com.cnfantasia.server.ms.inviteReward.entity.InviteUserEntity;

public class InviteRewardDao extends InviteRewardConfigBaseDao implements IInviteRewardDao {

	@Override
	public List<InviteRewardConfigEntity> query_InviteRewardConfig_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("inviteReward.select_inviteRewardConfig_forPage", paramMap);
	}

	@Override
	public int query_InviteRewardConfig_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("inviteReward.select_inviteRewardConfig_forCount", paramMap);
	}

	@Override
	public InviteRewardConfigEntity query_InviteRewardConfig_byId(BigInteger id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return sqlSession.selectOne("inviteReward.select_inviteRewardConfig_byId", id);
	}

	@Override
	public List<InviteUserEntity> query_InviteUserList(Map<String, Object> paramMap) {
		return sqlSession.selectList("inviteReward.select_inviteUserList", paramMap);
	}

	@Override
	public int query_registerInviteRewardRecord_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("inviteReward.select_registerInviteRewardRecord_forCount", paramMap);
	}

	@Override
	public int query_InviteRewardConfig_isRepeat(Map<String, Object> paramMap) {
		return sqlSession.selectOne("inviteReward.select_inviteRewardConfig_isRepeat", paramMap);
	}

	@Override
	public int query_registerUserIsRG_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("inviteReward.select_user_isRoseGraden_forCount", paramMap);
	}
	
}
