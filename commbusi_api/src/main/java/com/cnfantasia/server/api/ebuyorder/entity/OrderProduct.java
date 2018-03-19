package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class OrderProduct implements Serializable {
	
	private static final long serialVersionUID = -5620632724462357331L;

	private Long id;
	
	private String productName;
	
	private BigDecimal price;
	
	private Integer qty;
	
	private String picBase;
	
	private Date updTime;

	private boolean hasProductRefund;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		if(price != null) {
			return BigDecimalUtil.div100(price);
		}
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	public double getProdPrice() {
		return BigDecimalUtil.mul(getPrice().doubleValue(), qty.doubleValue());
	}

	public String getPicBase() {
		if(picBase != null) {
			String picTemp = CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.PRODUCT_PIC_BASE_PATH, picBase, updTime);
			return ImageUtil.getImageListURL(picTemp, "72");
		}
		return picBase;
	}

	public void setPicBase(String picBase) {
		this.picBase = picBase;
	}

	public Date getUpdTime() {
		return null;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public boolean isHasProductRefund() {
		return hasProductRefund;
	}

	public void setHasProductRefund(boolean hasProductRefund) {
		this.hasProductRefund = hasProductRefund;
	}
}
