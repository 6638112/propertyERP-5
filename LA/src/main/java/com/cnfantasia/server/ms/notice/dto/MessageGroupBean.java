package com.cnfantasia.server.ms.notice.dto;

import java.io.Serializable;
import java.math.BigInteger;
/**
 * 
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月6日下午7:34:39
 */
public class MessageGroupBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2671127085356984366L;
	/**  */	private BigInteger id;	/** 小区 */	private BigInteger groupbuildingId;	/** 消息Id */	private BigInteger tMessageFId;
	public MessageGroupBean(){
	}

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @return the groupbuildingId
	 */
	public BigInteger getGroupbuildingId() {
		return groupbuildingId;
	}

	/**
	 * @param groupbuildingId the groupbuildingId to set
	 */
	public void setGroupbuildingId(BigInteger groupbuildingId) {
		this.groupbuildingId = groupbuildingId;
	}

	/**
	 * @return the tMessageFId
	 */
	public BigInteger gettMessageFId() {
		return tMessageFId;
	}

	/**
	 * @param tMessageFId the tMessageFId to set
	 */
	public void settMessageFId(BigInteger tMessageFId) {
		this.tMessageFId = tMessageFId;
	}
	
}
