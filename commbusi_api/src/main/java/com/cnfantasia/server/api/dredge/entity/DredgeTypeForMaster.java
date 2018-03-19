package com.cnfantasia.server.api.dredge.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * 师傅查询疏通类型
 * 
 * @author wenfq 2015-08-13
 *
 */
public class DredgeTypeForMaster {
	long id;
	String name;
	String picUrl;
	String desc;
	int isSelected;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		if (picUrl != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, picUrl, null);
		}
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}

}
