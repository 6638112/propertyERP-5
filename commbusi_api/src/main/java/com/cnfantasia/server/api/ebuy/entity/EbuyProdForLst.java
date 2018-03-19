package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.comments.constant.CommentsConstant;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.PriceUtil;

public class EbuyProdForLst implements Serializable {

	private static final long serialVersionUID = -2425258607288158588L;
	
	private Long id;
	
	private String name;
	
	private String nameMini;
	
	private String picBase;
	
	private BigDecimal priceOnShelf; //货架出售价格，即货架上的折扣价
	
	private BigDecimal price; //价格
	
	private BigDecimal priceDiscount; //折扣价
	
	private String priceUnit;
	
	private Integer filmTickets;
	
	private Integer supplyType; //商家类型，附近和自营
	
	private String deliveMoney; //29元起送，起送条件

	private Boolean inAnniversary = false;
	
//	private Integer opType; //运营类型，1为新用户专享
//	
//	private String opName; //运营活动名称,如新用户专享
//	
//	private Integer canBuyNum; //此类活动最多可以买多少个商品
	
	private Date updTime;

	private String sosName; //货源名称
	private String sosIntroduceUrl; //货源介绍URL

	/**
	 * 库存
	 */
	int leftCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameMini() {
		if(nameMini == null) {
			return name;
		}
		return nameMini;
	}

	public void setNameMini(String nameMini) {
		this.nameMini = nameMini;
	}

	public String getPicBase() {
		if(picBase != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.PRODUCT_PIC_BASE_PATH, picBase, updTime);
		}
		return picBase;
	}
	
	public String getPicBase640() {
		if(picBase != null) {
			String tempPicBase = CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.PRODUCT_PIC_BASE_PATH, picBase, updTime);
			tempPicBase = tempPicBase.substring(0, tempPicBase.lastIndexOf(".")) + "/640" + tempPicBase.substring(tempPicBase.lastIndexOf("."), tempPicBase.length());
			return tempPicBase;
		}
		return picBase;
	}

	public void setPicBase(String picBase) {
		this.picBase = picBase;
	}

	public BigDecimal getPriceOnShelf() {
		if(priceOnShelf == null) {
			priceOnShelf = priceDiscount;
		}
		return priceOnShelf;
	}

	public void setPriceOnShelf(BigDecimal priceOnShelf) {
		this.priceOnShelf = PriceUtil.div100(priceOnShelf); //priceOnShelf.divide(BigDecimal.valueOf(100));
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = PriceUtil.div100(price); // price.divide(BigDecimal.valueOf(100));
	}
	
	public String getSavePrice() {
		return CommentsConstant.Default_Comments_Level_Format.format(getPrice().subtract(getPriceOnShelf()));
	}

//	public BigDecimal getPriceDiscount() {
//		return priceDiscount;
//	}

	public void setPriceDiscount(BigDecimal priceDiscount) {
		this.priceDiscount = priceDiscount.divide(BigDecimal.valueOf(100));;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public Integer getFilmTickets() {
		return filmTickets;
	}

	public void setFilmTickets(Integer filmTickets) {
		this.filmTickets = filmTickets;
	}

	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	public String getDeliveMoney() {
		return deliveMoney;
	}

	public void setDeliveMoney(String deliveMoney) {
		this.deliveMoney = deliveMoney;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

//	public Date getUpdTime() {
//		return updTime;
//	}

//	@Override
//	public Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}

	public int getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(int leftCount) {
		this.leftCount = leftCount;
	}

	public Boolean getInAnniversary() {
		return inAnniversary;
	}

	public void setInAnniversary(Boolean inAnniversary) {
		this.inAnniversary = inAnniversary;
	}

	public String getSosName() {
		return sosName;
	}

	public void setSosName(String sosName) {
		this.sosName = sosName;
	}

	public String getSosIntroduceUrl() {
		return sosIntroduceUrl;
	}

	public void setSosIntroduceUrl(String sosIntroduceUrl) {
		this.sosIntroduceUrl = sosIntroduceUrl;
	}
}
