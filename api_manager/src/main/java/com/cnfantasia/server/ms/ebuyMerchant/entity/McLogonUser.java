package com.cnfantasia.server.ms.ebuyMerchant.entity;

import java.io.Serializable;

import com.cnfantasia.server.api.ebuyorder.entity.EbuyMerchantBean;

/**
 * 移动电商商户端登录用户
 * @author yewj
 *
 */
public class McLogonUser implements Serializable {
	
	private static final long serialVersionUID = 4211180439203361858L;

	private Long userId;
	
	private Long huaId;
	
	private String mobile;
	
	private Integer loginType;
	
	private String sessionKey;
	
//	private Long merchantId;
//	
//	private Boolean isFirstLogin;
	
	private EbuyMerchantBean ebuyMerchantBean;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getHuaId() {
		return huaId;
	}

	public void setHuaId(Long huaId) {
		this.huaId = huaId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

//	public Long getMerchantId() {
//		return merchantId;
//	}
//
//	public void setMerchantId(Long merchantId) {
//		this.merchantId = merchantId;
//	}
//
//	public Boolean getIsFirstLogin() {
//		return isFirstLogin;
//	}
//
//	public void setIsFirstLogin(Boolean isFirstLogin) {
//		this.isFirstLogin = isFirstLogin;
//	}

	public EbuyMerchantBean getEbuyMerchantBean() {
		return ebuyMerchantBean;
	}

	public void setEbuyMerchantBean(EbuyMerchantBean ebuyMerchantBean) {
		this.ebuyMerchantBean = ebuyMerchantBean;
	}
	
	/*
	 * {"dataValue":{"inviteNo":"50279","defaultRoomId":50687,"sex":"0","imgProfile":"","nickName":"123","userId":50290,
	 * "ext_room_isAdmin":false,"huaId":"50290","mobile":"13723460250","isAdmin":false,"loginType":0,"discount":9.2,"savedHbMoney":5.00,
	 * "isDiscountUsed":false,"leftPrizeCount":3,"defaultRoomInfo":{"block":"南山区","building":"aa","city":"深圳市","ext_groupBuilding_isSign":false,
	 * "ext_room_isAdmin":false,"groupBuilding":"桃园小区","id":"50687","isDefault":true,"province":"广东省","realRoomId":823495,"roomHuaId":"TYXQ50687",
	 * "roomNum":"gg","totalAddress":"广东省深圳市南山区桃园小区aagg","verifyStatus":4},"hbBalance":0.00,"isFirstRegist":false,
	 * "sessionKey":"3e097cec249da728b135c7d8a1d56055","leftPoint":5},"status":"0000"}
	 */
}
