package com.cnfantasia.server.api.payment.entity;

import java.io.Serializable;

/**
 * 双乾补贴预支付返回数据
 * 
 * @author liyulin
 * @version 1.0 2016年9月22日 上午10:21:11
 */
public class SqPayBtPrePayDto extends SqPayBtRequest implements Serializable{

	private static final long serialVersionUID = -8943193580723567666L;
	
	private String encryptInfo;

	public String getEncryptInfo() {
		return encryptInfo;
	}

	public void setEncryptInfo(String encryptInfo) {
		this.encryptInfo = encryptInfo;
	}
	
}
