package com.cnfantasia.server.api.dredge.entity;

import java.util.List;

/**
 * 疏通服务的一级分类, 师傅端用
 * @author wenfq 2015-08-17
 *
 */
public class DredgeParentType {
	String title; //所属父类名称
	List<DredgeTypeForMaster> dtList; //所包含的子类集

	public List<DredgeTypeForMaster> getDtList() {
		return dtList;
	}

	public void setDtList(List<DredgeTypeForMaster> dtList) {
		this.dtList = dtList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
