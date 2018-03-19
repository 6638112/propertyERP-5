package com.cnfantasia.server.api.cache.dto;

import java.io.Serializable;

public class CacheMsg implements Serializable {

	private static final long serialVersionUID = 5943012413427295912L;

	private int msgType;

	public CacheMsg() {
		super();
	}

	public CacheMsg(int msgType) {
		super();
		this.msgType = msgType;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

}
