package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;

/**
 * 类说明：
 * 
 * @author hunter
 * @since 2014年6月7日下午2:30:24
 */
public class EbuyMerchantBean implements Serializable {
	
	private static final long serialVersionUID = 3804603870669797586L;

	private Long id;
	
	private Long merchantId;
	
	private Long userId;
	
	private Boolean isFirstLogin;
	
	private Integer storeAuditStatus; //f_store_audit_status;

	private String ownerPhone;
	
	public static int STORE_AUDIT_STATUS_PASS = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public Integer getStoreAuditStatus() {
		if(storeAuditStatus == null) {
			storeAuditStatus = 0;
		}
		return storeAuditStatus;
	}

	public void setStoreAuditStatus(Integer storeAuditStatus) {
		this.storeAuditStatus = storeAuditStatus;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
}
