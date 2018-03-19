package com.cnfantasia.server.api.flashDealActivity.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: FlashDealActivityDetailEntity
 * @Date: 2016-10-31 10:06
 * @Auther: kangduo
 * @Description:()
 */
public class FlashDealActivityDetailEntity implements Serializable{

    private static final long serialVersionUID = -2257495609557688760L;

    private BigInteger activityId;
    private BigInteger merchantId;
    private String activityTitle;
    private String activityPic;
    //状态，1，进行中，2，未开始，3，已结束
    private Integer activityStatus;
    //activityPic拆为列表
    private List<String> activityPicList;
    private String activityStartTime;
    private String activityEndTime;
    private Integer activityPrice;
    private String introduceContent;
    private String introduceRule;
    private Integer prizeCount;
    private Integer buyCount;
    private Integer isSettle;
    /**开始时间9:00*/
    private String startTime;
    /**结束时间9:00*/
    private String endTime;
    //我的购买记录
    private FlashDealBuyRecord buyRecord;
    /**粮票支付百分比*/
    private Double JFBPercent;
    //中奖列表
    private List<FlashDealBuyRecord> winRecords;
    /**商品图片*/
    private String productPic;
    /**商品名称*/
    private String productName;
    /**商品价格*/
    private Long productPrice;
    /**商品ID*/
    private BigInteger productId;
    /**货架ID*/
    private BigInteger productShelfId;
    /**用户提醒状态1提醒，0未提醒*/
    private Integer remindStatus;
    /**自提点*/
    private String merchantName;

    public Integer getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(Integer prizeCount) {
        this.prizeCount = prizeCount;
    }

    public BigInteger getActivityId() {
        return activityId;
    }

    public void setActivityId(BigInteger activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityPic() {
        return activityPic;
    }

    public void setActivityPic(String activityPic) {
        this.activityPic = activityPic;
    }

    public List<String> getActivityPicList() {
        ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
        if (this.productPic == null || this.productPic.length() <= 0) {
            return null;
        }
        List<String> picList = Arrays.asList(productPic.split(";"));
        List<String> resList = new ArrayList<String>(picList.size());
        for (String s : picList) {
            String imageServerUrl = sysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH);
            s = imageServerUrl + sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH) + s;
            resList.add(s);
        }
        return resList;
    }

    public void setActivityPicList(List<String> activityPicList) {
        this.activityPicList = activityPicList;
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

    public String getIntroduceContent() {
        return introduceContent;
    }

    public void setIntroduceContent(String introduceContent) {
        this.introduceContent = introduceContent;
    }

    public String getIntroduceRule() {
        return introduceRule;
    }

    public void setIntroduceRule(String introduceRule) {
        this.introduceRule = introduceRule;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public FlashDealBuyRecord getBuyRecord() {
        return buyRecord;
    }

    public void setBuyRecord(FlashDealBuyRecord buyRecord) {
        this.buyRecord = buyRecord;
    }

    public List<FlashDealBuyRecord> getWinRecords() {
        return winRecords;
    }

    public void setWinRecords(List<FlashDealBuyRecord> winRecords) {
        this.winRecords = winRecords;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public BigDecimal getActivityPriceDecimal() {
        return this.activityPrice == null ? BigDecimal.ZERO : PriceUtil.removeTailZero(PriceUtil.div100(this.activityPrice.longValue()));
    }

    public Integer getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Integer activityPrice) {
        this.activityPrice = activityPrice;
    }

    public Integer getIsSettle() {
        return isSettle;
    }

    public void setIsSettle(Integer isSettle) {
        this.isSettle = isSettle;
    }

    public Double getJFBPercent() {
        return JFBPercent;
    }

    public void setJFBPercent(Double JFBPercent) {
        this.JFBPercent = JFBPercent;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public Integer getRemindStatus() {
        return remindStatus;
    }

    public void setRemindStatus(Integer remindStatus) {
        this.remindStatus = remindStatus;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigInteger getProductShelfId() {
        return productShelfId;
    }

    public void setProductShelfId(BigInteger productShelfId) {
        this.productShelfId = productShelfId;
    }

    public BigInteger getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(BigInteger merchantId) {
        this.merchantId = merchantId;
    }
}
