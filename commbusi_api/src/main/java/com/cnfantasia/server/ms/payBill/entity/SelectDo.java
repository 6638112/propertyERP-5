package com.cnfantasia.server.ms.payBill.entity;

import java.io.Serializable;

public class SelectDo implements Serializable {
	
	private static final long serialVersionUID = 3546274537954948007L;

	private Long id;
	
	private String name;
	
	private String unitName;
	
	private String roomNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

}
