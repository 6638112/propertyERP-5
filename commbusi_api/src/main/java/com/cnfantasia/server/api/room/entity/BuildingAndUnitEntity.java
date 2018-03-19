package com.cnfantasia.server.api.room.entity;

import java.util.List;

/**
 * 楼栋与单元实体
 * @author wenfq
 *
 */
public class BuildingAndUnitEntity {
	long id;
	String name;
	List<String> unitList;
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
	public List<String> getUnitList() {
		return unitList;
	}
	public void setUnitList(List<String> unitList) {
		this.unitList = unitList;
	}	
}
