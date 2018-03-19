package com.cnfantasia.server.ms.ebuy.entity;

import java.io.Serializable;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class OrderOpBean implements Serializable {
	
	private static final long serialVersionUID = 5676797555138767110L;
	
	private Long id;
	
	private String opName; //活动， 如新用户专享

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

}
