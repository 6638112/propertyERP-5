package com.cnfantasia.server.api.dredge.entity;

/**
 * 师傅可选的行政区
 * 
 * @author wenfq 2015-08-05
 */
public class BlockForMaster {
	long id; //block id
	String name; // block name
	int isSelected = 0; //已经选中

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

	public int getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}

}
