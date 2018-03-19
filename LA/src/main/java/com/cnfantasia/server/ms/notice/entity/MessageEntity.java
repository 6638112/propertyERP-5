package com.cnfantasia.server.ms.notice.entity;

import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

public class MessageEntity extends Message{

	private OmsUser createOmsUser;

	public OmsUser getCreateOmsUser() {
		return createOmsUser;
	}

	public void setCreateOmsUser(OmsUser createOmsUser) {
		this.createOmsUser = createOmsUser;
	}
	
	
}
