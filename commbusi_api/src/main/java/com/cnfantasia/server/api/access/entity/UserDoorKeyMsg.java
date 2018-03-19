package com.cnfantasia.server.api.access.entity;


public class UserDoorKeyMsg {
	/** 开通状态 */
	private Integer openStatus;
	/** 付款状态 */
	private Integer payStatus;
	/** 审核原因 */
	private String auditReason;
	/** 门禁密钥 */
	private String doorKey;
	/** 供应商类型=={"1":"立方","2";"芝麻开门"} */
	private Integer type;
	/** 门禁位置描述 */
	private String doorName;
	/**远程开门开关*/
	private Integer isOpenRemote;
	/**走进开门开关*/
	private Integer isOpenClose;
	/**芝麻开门第三方小区编号*/
	private String gbId;

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	public String getDoorKey() {
		return doorKey;
	}

	public void setDoorKey(String doorKey) {
		this.doorKey = doorKey;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDoorName() {
		return doorName;
	}

	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}

	public Integer getIsOpenRemote() {
		return isOpenRemote;
	}

	public void setIsOpenRemote(Integer isOpenRemote) {
		this.isOpenRemote = isOpenRemote;
	}

	public Integer getIsOpenClose() {
		return isOpenClose;
	}

	public void setIsOpenClose(Integer isOpenClose) {
		this.isOpenClose = isOpenClose;
	}

	public String getGbId() {
		return gbId;
	}

	public void setGbId(String gbId) {
		this.gbId = gbId;
	}


}
