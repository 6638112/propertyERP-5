package com.cnfantasia.server.api.dredge.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 自选耗材实体
 * @author wenfq 2016-08-25
 *
 */
public class SelfBuyProduct {
	private BigInteger id;
	private String pic;
	private String name;
	private String desc;
	//买价
	private Integer price;
	//进货价
	private Integer productPursePrice;
	private Integer quantity;//数量
	private BigInteger supplyMerchantId;
	private BigInteger productShelfId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getPic() {
		ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		return sysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH) + sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH) + pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BigDecimal getPrice() {
		return PriceUtil.div100(price.longValue()).setScale(2, RoundingMode.HALF_UP);
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getProductPursePrice() {
		return productPursePrice;
	}

	public void setProductPursePrice(Integer productPursePrice) {
		this.productPursePrice = productPursePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigInteger getSupplyMerchantId() {
		return supplyMerchantId;
	}

	public void setSupplyMerchantId(BigInteger supplyMerchantId) {
		this.supplyMerchantId = supplyMerchantId;
	}

	public BigInteger getProductShelfId() {
		return productShelfId;
	}

	public void setProductShelfId(BigInteger productShelfId) {
		this.productShelfId = productShelfId;
	}
}
