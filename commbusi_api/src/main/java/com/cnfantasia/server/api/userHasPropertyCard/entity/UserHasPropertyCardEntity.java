package com.cnfantasia.server.api.userHasPropertyCard.entity;

import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

public class UserHasPropertyCardEntity extends UserHasPropertyCard {
	
	private String oldUpdateTime;

	public UserHasPropertyCardEntity() {
		super();
	}

	public UserHasPropertyCardEntity(UserHasPropertyCard arg) {
		super(arg);
	}

	public String getOldUpdateTime() {
		return oldUpdateTime;
	}

	public void setOldUpdateTime(String oldUpdateTime) {
		this.oldUpdateTime = oldUpdateTime;
	}

}
