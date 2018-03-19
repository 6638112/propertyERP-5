package com.cnfantasia.server.api.flashDealActivity.entity;

import java.math.BigInteger;

/**
 * @ClassName: FlashDealRemindEntity
 * @Date: 2016-12-09 17:38
 * @Auther: yanghua
 * @Description:(提醒类)
 */
public class FlashDealRemindEntity {
    /**活动id*/
    private BigInteger activityId;
    /**用户id*/
    private BigInteger userId;
    /**提醒id*/
    private BigInteger remindId;
    /**活动标题*/
    private String title;
    /**活动开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;

    public BigInteger getActivityId() {
        return activityId;
    }

    public void setActivityId(BigInteger activityId) {
        this.activityId = activityId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getRemindId() {
        return remindId;
    }

    public void setRemindId(BigInteger remindId) {
        this.remindId = remindId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
