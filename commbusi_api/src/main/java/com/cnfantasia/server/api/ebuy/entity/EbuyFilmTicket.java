package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.lang.String;import java.util.Date;


public class EbuyFilmTicket implements Serializable {

	private static final long serialVersionUID = 6707741451728943589L;
	/**  */	private Long id;
	
	private String identifyCode;
	
	private Date expireTm; //f_expire_tm;
	
	private Long orderId;
	
	private Long productId;
	
	private Integer state;
	
	private Integer ticketNum;	
	private Date updTime;
	
	private Integer type; //1电影票 2虚拟商品券

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public Date getExpireTm() {
		return expireTm;
	}

	public void setExpireTm(Date expireTm) {
		this.expireTm = expireTm;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Integer getType() {
//		if(type == null) {
//			if(ticketNum > 100) {
//				type = 2;
//			} else {
//				type = 1;
//			}
//		}
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
