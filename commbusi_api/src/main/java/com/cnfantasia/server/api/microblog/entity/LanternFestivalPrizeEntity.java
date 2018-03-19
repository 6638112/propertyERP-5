package com.cnfantasia.server.api.microblog.entity;

/**
 * 元宵节活动中奖实体信息
 * 
 * @author wenfq  2017年2月8日
 *
 */
public class LanternFestivalPrizeEntity {
	Long userId;//中奖解放号
	String riddleNo;//谜语编号
	String userPhoneNum;//中奖手机号
	int status;//领奖状态
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRiddleNo() {
		return riddleNo;
	}
	public void setRiddleNo(String riddleNo) {
		this.riddleNo = riddleNo;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
