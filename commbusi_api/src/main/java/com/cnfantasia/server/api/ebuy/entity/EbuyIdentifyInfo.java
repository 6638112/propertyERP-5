package com.cnfantasia.server.api.ebuy.entity;

/* import java.io.Serializable;*/
import java.io.Serializable;
import java.lang.String;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyAdvertise implements Serializable{
*/


public class EbuyIdentifyInfo implements Serializable {

	private static final long serialVersionUID = -4468119516236253314L;

	private Long id;
	
	private Long userId;
	
	private Long orderId;
		private String identify;
	
	private String idName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

}
