package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;
import java.util.Date;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.ImageUtil;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class MerchantProdLst implements Serializable {
	
	private static final long serialVersionUID = 1959045811826015327L;

	private Long id;
	
	private String prodName;
	
	private Double price;
	
	private Integer leftCount;

	/**
	 * 销量
	 */
	private Integer selNum = 0;

	/**
	 * 是否爆款
	 */
	private int isHotSale = 0;
	
	private Integer statusAudit;
	
	private Integer status;
	
	private String picBase;
	
	private Date updTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Double getPrice() {
		if(price != null) {
			return BigDecimalUtil.div(price, 100, 2);
		}
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatusAudit() {
		return statusAudit;
	}

	public void setStatusAudit(Integer statusAudit) {
		this.statusAudit = statusAudit;
	}

	public String getPicBase() {
		if(picBase != null) {
			String picTemp =  CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.PRODUCT_PIC_BASE_PATH, picBase, updTime);
			return ImageUtil.getImageListURL(picTemp, "226x166");
		}
		return picBase;
	}

	public void setPicBase(String picBase) {
		this.picBase = picBase;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Integer getSelNum() {
		return selNum;
	}

	public void setSelNum(Integer selNum) {
		this.selNum = selNum;
	}

	public int getIsHotSale() {
		return isHotSale;
	}

	public void setIsHotSale(int isHotSale) {
		this.isHotSale = isHotSale;
	}
}
