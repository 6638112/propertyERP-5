package com.cnfantasia.server.ms.pub.entity;

import java.io.Serializable;

/**
 * @author zhangzehao
 *
 */
public class JsonMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8317513895477516201L;
	
	private String message;
	
	private String result;
	
	private int total;
	
	private Object rows;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

}
