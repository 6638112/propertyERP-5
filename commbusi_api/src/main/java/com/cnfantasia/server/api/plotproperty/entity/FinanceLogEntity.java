package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class FinanceLogEntity implements Serializable {
	
	private static final long serialVersionUID = -6223429305463991200L;

	private Long id; //主键ID

	private Long realRoomId;
	
	private String carNum;
	
	private String orderNo;
	
	private String excepMsg;

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRealRoomId() {
		return realRoomId;
	}

	public void setRealRoomId(Long realRoomId) {
		this.realRoomId = realRoomId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getExcepMsg() {
		return excepMsg;
	}

	public void setExcepMsg(String excepMsg) {
		this.excepMsg = excepMsg;
	}

}
