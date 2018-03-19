package com.cnfantasia.server.ms.accesslink.dto;

import com.cnfantasia.server.domainbase.blackList.entity.BlackList;

public class BlackListDto extends BlackList {
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

	public BlackListDto() {
		super();
	}

	public BlackListDto(String addMan, String updateMan) {
		super();
		this.addMan = addMan;
		this.updateMan = updateMan;
	}

	public String getAddMan() {
		return addMan;
	}

	public void setAddMan(String addMan) {
		this.addMan = addMan;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

}
