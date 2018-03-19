package com.cnfantasia.server.ms.advertise.entity;

import java.io.Serializable;

/**
 * 广告列表查询参数封装类
 */
public class AdvQryParam implements Serializable {
    private static final long serialVersionUID = -7030800462629096948L;

    private String advName;
    private String advStatus;
    private String advCode;
    private String advStartTime;
    private String advEndTime;
    /**广告类型*/
    private String advType;
    /**页面跳转类型*/
    private String jumpType;

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String  getAdvStatus() {
        return advStatus;
    }

    public void setAdvStatus(String  advStatus) {
        this.advStatus = advStatus;
    }

    public String getAdvStartTime() {
        return advStartTime;
    }

    public void setAdvStartTime(String advStartTime) {
        this.advStartTime = advStartTime;
    }

    public String getAdvEndTime() {
        return advEndTime;
    }

    public void setAdvEndTime(String advEndTime) {
        this.advEndTime = advEndTime;
    }

    public String getAdvCode() {
        return advCode;
    }

    public void setAdvCode(String advCode) {
        this.advCode = advCode;
    }

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

	public String getJumpType() {
		return jumpType;
	}

	public void setJumpType(String jumpType) {
		this.jumpType = jumpType;
	}
    
}
