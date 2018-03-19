package com.cnfantasia.server.ms.propertyRepair.entity;

/**
 * 报修配置
 * 
 * @author wenfq 2015-04-08
 * 
 */
public class PropertyRepairConfig {
	String pmName;//管理处名称

	String gbId; //小区ID
	String gbName;//小区名称
	int supportCount;//支付门牌数
	boolean openStatus;//开通状态

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public int getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(int supportCount) {
		this.supportCount = supportCount;
	}

	public boolean isOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(boolean openStatus) {
		this.openStatus = openStatus;
	}

	public String getGbId() {
		return gbId;
	}

	public void setGbId(String gbId) {
		this.gbId = gbId;
	}

}
