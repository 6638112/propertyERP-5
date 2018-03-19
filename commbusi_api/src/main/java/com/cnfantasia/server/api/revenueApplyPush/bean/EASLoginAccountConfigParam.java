package com.cnfantasia.server.api.revenueApplyPush.bean;
/**
 * EAS登录账号相关的参数配置
 * @author wenfq
 *
 */
public class EASLoginAccountConfigParam {
	String userName;//用户名
	String password;//用户密码
	String dataCenter;//数据中
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDataCenter() {
		return dataCenter;
	}
	public void setDataCenter(String dataCenter) {
		this.dataCenter = dataCenter;
	}
	public EASLoginAccountConfigParam(String userName, String password, String dataCenter) {
		super();
		this.userName = userName;
		this.password = password;
		this.dataCenter = dataCenter;
	}
}
