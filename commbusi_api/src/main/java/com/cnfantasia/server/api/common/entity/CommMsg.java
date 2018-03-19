package com.cnfantasia.server.api.common.entity;

/* */ import java.io.Serializable;/* */

/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class CommMsg implements Serializable{
	
	private static final long serialVersionUID = -6026495000081502408L;

	private String mobile;
	
	private String msg;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
