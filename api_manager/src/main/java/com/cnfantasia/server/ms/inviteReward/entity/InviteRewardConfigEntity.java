package com.cnfantasia.server.ms.inviteReward.entity;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.domainbase.inviteRewardConfig.entity.InviteRewardConfig;
import com.cnfantasia.server.domainbase.user.entity.User;

public class InviteRewardConfigEntity extends InviteRewardConfig {
	private static final long serialVersionUID = 1L;
	/**邀请人*/
	private User inviteUser = new User();
	public User getInviteUser() {
		return inviteUser;
	}
	public void setInviteUser(User inviteUser) {
		this.inviteUser = inviteUser;
	}
	/**我邀请的人注册信息*/
	private List<InviteRewardRelationEntity> relationList = new ArrayList<InviteRewardRelationEntity>();
	public List<InviteRewardRelationEntity> getRelationList() {
		return relationList;
	}
	public void setRelationList(List<InviteRewardRelationEntity> relationList) {
		this.relationList = relationList;
	}
	
}
