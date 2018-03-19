package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.ImageUtil;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class MerchantProdPic implements Serializable {
	
	private static final long serialVersionUID = 7818866388795342989L;

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
			String urlTemp = CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.PRODUCT_PIC_BASE_PATH, url, updTime);
			return ImageUtil.getImageListURL(urlTemp, "640x467");
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
