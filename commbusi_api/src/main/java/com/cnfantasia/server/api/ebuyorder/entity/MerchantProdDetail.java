package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;


/**
 * 类说明：
 * 
 * @author yewj
 */
public class MerchantProdDetail implements Serializable {
	
	private static final long serialVersionUID = 2325549812231681972L;

	private Long id;
	
	private String prodName;
	
	private Long productTypeId;
	
	private Double price;
	
	private Double priceDiscount;
	
	private Integer leftCount;
	
	private Integer status;
	
	private Integer statusAudit;
	
	private String reason;
	
	private String picBase;
	
	private Date updTime;
	
	private List<MerchantProdPic> prodPicList;
	
	private List<MerchantProdParam> prodParamList;
	
	public static int STATUS_AUDIT_REPERTORY = 2;//草稿，保存在仓库中
	
	public static int STATUS_AUDIT_WAIT = 3;//提交状态, 即待审核
	
	public static int STATUS_AUDIT_PASS_NOT = 4;//审核不通过
	
	public static int STATUS_AUDIT_PASS = 5;///审核通过
	
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

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
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

	public Double getPriceDiscount() {
		if(priceDiscount != null) {
			return BigDecimalUtil.div(priceDiscount, 100, 2);
		}
		return priceDiscount;
	}

	public void setPriceDiscount(Double priceDiscount) {
		this.priceDiscount = priceDiscount;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setStatusAudit(Integer statusAudit) {
		this.statusAudit = statusAudit;
	}

	public String getPicBase() {
		if(picBase != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.PRODUCT_PIC_BASE_PATH, picBase, updTime);
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

	public List<MerchantProdPic> getProdPicList() {
		return prodPicList;
	}

	public void setProdPicList(List<MerchantProdPic> prodPicList) {
		this.prodPicList = prodPicList;
	}

	public List<MerchantProdParam> getProdParamList() {
		return prodParamList;
	}

	public void setProdParamList(List<MerchantProdParam> prodParamList) {
		this.prodParamList = prodParamList;
	}
	
	public int getParamNum() {
		if(prodParamList != null && prodParamList.size() > 0) {
			return prodParamList.size();
		} else {
			return 1;
		}
	}

}
