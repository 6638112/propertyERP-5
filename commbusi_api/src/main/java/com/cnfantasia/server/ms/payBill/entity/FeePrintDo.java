package com.cnfantasia.server.ms.payBill.entity;

import java.io.Serializable;
import java.util.Date;

public class FeePrintDo implements Serializable {
	
	private static final long serialVersionUID = 3546274537954948007L;

	private Long id;
	
	private Long groupBuildId;
	
	private String groupbuild;
	
	private String building;
	
	private String unitnum;
	
	private String roomNum;
	
	private String accountName;
	
	private String accountMonth;
	
	private String account;
	
	private Date payTm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupBuildId() {
		return groupBuildId;
	}

	public void setGroupBuildId(Long groupBuildId) {
		this.groupBuildId = groupBuildId;
	}

	public String getGroupbuild() {
		return groupbuild;
	}

	public void setGroupbuild(String groupbuild) {
		this.groupbuild = groupbuild;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getUnitnum() {
		return unitnum;
	}

	public void setUnitnum(String unitnum) {
		this.unitnum = unitnum;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountMonth() {
		return accountMonth;
	}

	public void setAccountMonth(String accountMonth) {
		this.accountMonth = accountMonth;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getPayTm() {
		return payTm;
	}

	public void setPayTm(Date payTm) {
		this.payTm = payTm;
	}

}
