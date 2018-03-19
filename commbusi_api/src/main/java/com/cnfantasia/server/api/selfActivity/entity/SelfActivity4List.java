package com.cnfantasia.server.api.selfActivity.entity;

import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;

public class SelfActivity4List extends SelfActivity {
	private static final long serialVersionUID = -2444376085772350372L;

	private String addUser;//添加人
	private String updUser;//更新人
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getUpdUser() {
		return updUser;
	}
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	
}
