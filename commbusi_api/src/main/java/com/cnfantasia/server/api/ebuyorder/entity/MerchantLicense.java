package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;
import java.util.Date;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class MerchantLicense implements Serializable {
	
	private static final long serialVersionUID = -4392560886549344010L;

	private Long id;
	
	private String url;
	
	private Date updTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		if(url != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.EBUY_STORE_PIC, url, updTime);
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	
}
