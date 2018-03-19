package com.cnfantasia.server.domainbase.coupon.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/**
 * 消费券实体类
 */
public class Coupon implements Serializable{

    private static final long serialVersionUID = 5056960176379397364L;

    /**
     * id
     */
    private BigInteger id;

    /**
     * 消费券名称
     */
    private String couponName;

    /**
     * 消费券类型 现金，折扣，实物兑换
     */
    private Integer couponType;

    /**
     * 适合类型 通用，超市，物业，维修，车禁，楼下店
     */
    private Integer useType;

    /**
     * 发放区域 全国，城市，小区
     */
    private Integer sendAreaType;
    
    /** 
     * 发券渠道
     */
	private Integer goalType;

    /**
     * 状态：开启，关闭
     */
    private Integer couponStatus;

    /**
     * 最少消费多少送
     */
    private Integer leastSpendSend;

    /**
     * 使用其它优惠是否送 Y，N
     */
    private String useDiscountSend;

    /**
     * 发放开始日期
     */
    private String sendStartDate;

    /**
     * 发放结束日期
     */
    private String sendEndDate;

    /**
     * 发放总量
     */
    private Integer sendTotal;

    /**
     * 已发放量
     */
    private Integer sendCount;

    /**
     * 最大可抵百分比
     */
    private Integer maxDiscountPercent;

    /**
     * 最少消费多少可用
     */
    private Integer leastSpendUse;

    /**
     * 现金券抵扣金额
     */
    private Integer discountMoney;

    /**
     * 折扣券可以打几折
     */
    private Integer discountValue;

    /**
     * 实物券可兑换商品ID
     */
    private Integer exchangeProductId;

    /**
     * 指定店铺券的店铺
     */
    private BigInteger supplyMerchantId;

    /**
     * 可用开始时间
     */
    private String useStartDate;
    
    /**
     * 可用结束时间
     */
    private String useEndDate;

    /**
     * 使用有效期类型，1固定日期，2获取后的N天
     */
    private Integer useEndDateType;
    /**
     * 自获取后可用天数
     */
    private Integer useDateNumber;

    private String linkUrl;
    private BigInteger receiveScene;

    private Date sys0AddTime;
    private Date sys0UpdTime;
    private BigInteger sys0AddUser;
    private BigInteger sys0UpdUser;

    private Set<BigInteger> cityIds;
    private Set<BigInteger> buildingIds;

    /**
     * 查询排序方式
     */
    private Integer queryOrderByType;

    private boolean canEdit;
    private boolean canOpen;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getSendAreaType() {
        return sendAreaType;
    }

    public void setSendAreaType(Integer sendAreaType) {
        this.sendAreaType = sendAreaType;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Integer getLeastSpendSend() {
        return leastSpendSend;
    }

    public void setLeastSpendSend(Integer leastSpendSend) {
        this.leastSpendSend = leastSpendSend;
    }

    public String getUseDiscountSend() {
        return useDiscountSend;
    }

    public void setUseDiscountSend(String  useDiscountSend) {
        this.useDiscountSend = useDiscountSend;
    }

    public String getSendStartDate() {
        return sendStartDate;
    }

    public void setSendStartDate(String sendStartDate) {
        this.sendStartDate = sendStartDate;
    }

    public String getSendEndDate() {
        return sendEndDate;
    }

    public void setSendEndDate(String sendEndDate) {
        this.sendEndDate = sendEndDate;
    }

    public String getUseStartDate() {
        return useStartDate;
    }

    public void setUseStartDate(String useStartDate) {
        this.useStartDate = useStartDate;
    }

    public String getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(String useEndDate) {
        this.useEndDate = useEndDate;
    }

    public Integer getSendTotal() {
        return sendTotal;
    }

    public void setSendTotal(Integer sendTotal) {
        this.sendTotal = sendTotal;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getMaxDiscountPercent() {
        return maxDiscountPercent;
    }

    public void setMaxDiscountPercent(Integer maxDiscountPercent) {
        this.maxDiscountPercent = maxDiscountPercent;
    }

    public Integer getLeastSpendUse() {
        return leastSpendUse;
    }

    public void setLeastSpendUse(Integer leastSpendUse) {
        this.leastSpendUse = leastSpendUse;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getExchangeProductId() {
        return exchangeProductId;
    }

    public void setExchangeProductId(Integer exchangeProductId) {
        this.exchangeProductId = exchangeProductId;
    }

    public Date getSys0AddTime() {
        return sys0AddTime;
    }

    public void setSys0AddTime(Date sys0AddTime) {
        this.sys0AddTime = sys0AddTime;
    }

    public Date getSys0UpdTime() {
        return sys0UpdTime;
    }

    public void setSys0UpdTime(Date sys0UpdTime) {
        this.sys0UpdTime = sys0UpdTime;
    }

    public BigInteger getSys0AddUser() {
        return sys0AddUser;
    }

    public void setSys0AddUser(BigInteger sys0AddUser) {
        this.sys0AddUser = sys0AddUser;
    }

    public BigInteger getSys0UpdUser() {
        return sys0UpdUser;
    }

    public void setSys0UpdUser(BigInteger sys0UpdUser) {
        this.sys0UpdUser = sys0UpdUser;
    }

    public Set getCityIds() {
        return cityIds;
    }

    public void setCityIds(Set<BigInteger> cityIds) {
        this.cityIds = cityIds;
    }

    public Set getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(Set<BigInteger> buildingIds) {
        this.buildingIds = buildingIds;
    }

    public Integer getQueryOrderByType() {
        return queryOrderByType;
    }

    public void setQueryOrderByType(Integer queryOrderByType) {
        this.queryOrderByType = queryOrderByType;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isCanOpen() {
        return canOpen;
    }

    public void setCanOpen(boolean canOpen) {
        this.canOpen = canOpen;
    }

    public BigInteger getSupplyMerchantId() {
        return supplyMerchantId;
    }

    public void setSupplyMerchantId(BigInteger supplyMerchantId) {
        this.supplyMerchantId = supplyMerchantId;
    }

	public Integer getGoalType() {
		return goalType;
	}

	public void setGoalType(Integer goalType) {
		this.goalType = goalType;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public BigInteger getReceiveScene() {
		return receiveScene;
	}

	public void setReceiveScene(BigInteger receiveScene) {
		this.receiveScene = receiveScene;
	}

    public Integer getUseEndDateType() {
        return useEndDateType;
    }

    public void setUseEndDateType(Integer useEndDateType) {
        this.useEndDateType = useEndDateType;
    }

    public Integer getUseDateNumber() {
        return useDateNumber;
    }

    public void setUseDateNumber(Integer useDateNumber) {
        this.useDateNumber = useDateNumber;
    }
}
