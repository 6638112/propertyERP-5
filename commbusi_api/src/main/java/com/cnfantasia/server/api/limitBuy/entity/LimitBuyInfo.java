package com.cnfantasia.server.api.limitBuy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * @ClassName: LimitBuyInfo
 * @Date: 2016-12-28 14:03
 * @Auther: kangduo
 * @Description: (限时购基本信息)
 */
public class LimitBuyInfo implements Serializable{
    private static final long serialVersionUID = -3964457296908555174L;

    private BigInteger limitBuyId;
    private String limitBuyTitle;
    private Integer limitBuyPrice;
    private String limitBuyPic;
    private BigInteger productId;
    private String productName;
    private Integer leftCount;
    private int maxCanBuy;
    private BigInteger shelfId;
    private Integer shelfPrice;
    private Integer shelfPriceDiscount;
    private BigInteger merchantId;
    private String merchantName;
    private String linkUrl;
    // 0 未开始 1 进行中 2 已结束
    private Integer status;
    private String activityStartTime;
    private String activityEndTime;
    private String productDesc;// 商品描述
    private Boolean inAnniversary = false;

    public BigInteger getLimitBuyId() {
        return limitBuyId;
    }

    public void setLimitBuyId(BigInteger limitBuyId) {
        this.limitBuyId = limitBuyId;
    }

    public String getLimitBuyTitle() {
        return limitBuyTitle;
    }

    public void setLimitBuyTitle(String limitBuyTitle) {
        this.limitBuyTitle = limitBuyTitle;
    }

    public BigDecimal getLimitBuyPrice() {
        if (limitBuyPrice == null) {
            return null;
        }
        return PriceUtil.div100(limitBuyPrice.longValue());
    }

    public Integer getLimitBuyPriceInteger() {
        return limitBuyPrice;
    }
    public void setLimitBuyPrice(Integer limitBuyPrice) {
        this.limitBuyPrice = limitBuyPrice;
    }

    public String getLimitBuyPic() {
        if (limitBuyPic != null) {
            /*return OmsSysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH)
                    + SysParamKey.PRODUCT_PIC_BASE_PATH + limitBuyPic;*/
            return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.PRODUCT_PIC_BASE_PATH, limitBuyPic, null);
        }
        return null;
    }

    public void setLimitBuyPic(String limitBuyPic) {
        this.limitBuyPic = limitBuyPic;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public BigInteger getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(BigInteger merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLinkUrl() {
        String server = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl);
        return server + "limitBuy/limitBuyDetail.html?id=" + limitBuyId;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigInteger getShelfId() {
        return shelfId;
    }

    public void setShelfId(BigInteger shelfId) {
        this.shelfId = shelfId;
    }

    public Integer getShelfPrice() {
        return shelfPrice;
    }

    public void setShelfPrice(Integer shelfPrice) {
        this.shelfPrice = shelfPrice;
    }

    public Integer getShelfPriceDiscount() {
        return shelfPriceDiscount;
    }

    public void setShelfPriceDiscount(Integer shelfPriceDiscount) {
        this.shelfPriceDiscount = shelfPriceDiscount;
    }

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getMaxCanBuy() {
		return maxCanBuy;
	}

	public void setMaxCanBuy(int maxCanBuy) {
		this.maxCanBuy = maxCanBuy;
	}

    public Boolean getInAnniversary() {
        return inAnniversary;
    }

    public void setInAnniversary(Boolean inAnniversary) {
        this.inAnniversary = inAnniversary;
    }
}
