package com.cnfantasia.server.ms.payBill.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;

public class FeePrint implements Serializable {
	
	private static final long serialVersionUID = 3546274537954948007L;

	private Long id;
	
	private Long realRoomId;
	
	private String addr;
	
	private String accountName; //账单名称
	
	private String accountMonth; //账单月份
	
	private String accountType; //账单类型
	
	private String account; //账单金额
	
	private Date payTm; //缴费时间
	
	private String feeDetail;//账单详情
	
	private RealRoomEntity realRoomEntity;

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

	public String getFeeDetail() {
		return feeDetail;
	}
	
	public List<FeeDetail> getFeeDetailList() {
		return JSON.parseArray(this.feeDetail, FeeDetail.class);
	}

	public void setFeeDetail(String feeDetail) {
		this.feeDetail = feeDetail;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountMonth() {
		return accountMonth;
	}

	public void setAccountMonth(String accountMonth) {
		this.accountMonth = accountMonth;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccount() {
		if (account != null) {
			return account;
		} else {
			BigDecimal totalAmount = BigDecimal.ZERO;
			if (getFeeDetailList() != null) {
				for (FeeDetail feeDetail : getFeeDetailList()) {
					totalAmount = feeDetail.getAmount().add(totalAmount);
				}
			}
			return totalAmount.toString();
		}
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getPayTm() {
		return payTm;
	}

	public void setPayTm(Date payTm) {
		this.payTm = payTm;
	}

	public RealRoomEntity getRealRoomEntity() {
		return realRoomEntity;
	}

	public void setRealRoomEntity(RealRoomEntity realRoomEntity) {
		this.realRoomEntity = realRoomEntity;
	}

}
