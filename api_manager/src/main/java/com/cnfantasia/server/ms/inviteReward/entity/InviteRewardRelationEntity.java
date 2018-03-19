package com.cnfantasia.server.ms.inviteReward.entity;

import com.cnfantasia.server.domainbase.inviteRewardRelation.entity.InviteRewardRelation;

public class InviteRewardRelationEntity extends InviteRewardRelation {
	private static final long serialVersionUID = 1L;
	/**注册人信息*/
	/** 手机号 */
	private String registerMobile;
	/** 用户花Id */
	private String registerHuaId;
	/** 昵称 */
	private String registerNickName;
	/** 真实姓名 */
	private String registerRealName;
	/** 性别 */
	private String registerSex;
	/** 是否登录过标识 */
	private Integer registerIslogin;
	/** 注册时间 */
	private String registerTime;
	public String getRegisterMobile() {
		return registerMobile;
	}
	public void setRegisterMobile(String registerMobile) {
		this.registerMobile = registerMobile;
	}
	public String getRegisterHuaId() {
		return registerHuaId;
	}
	public void setRegisterHuaId(String registerHuaId) {
		this.registerHuaId = registerHuaId;
	}
	public String getRegisterNickName() {
		return registerNickName;
	}
	public void setRegisterNickName(String registerNickName) {
		this.registerNickName = registerNickName;
	}
	public String getRegisterRealName() {
		return registerRealName;
	}
	public void setRegisterRealName(String registerRealName) {
		this.registerRealName = registerRealName;
	}
	public String getRegisterSex() {
		return registerSex;
	}
	public void setRegisterSex(String registerSex) {
		this.registerSex = registerSex;
	}
	public Integer getRegisterIslogin() {
		return registerIslogin;
	}
	public void setRegisterIslogin(Integer registerIslogin) {
		this.registerIslogin = registerIslogin;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}
