package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.DataDetailObj;
import java.math.BigDecimal;

public class TraderCouponTemplate implements DataDetailObj {
    private static final long serialVersionUID = 3200952541545634098L;
    private String code;
    private String name;
    private Byte blanceType;
    private Byte couponType;
    private String faceValue;
    private BigDecimal originalPrice;
    private BigDecimal realPrice;
    private String useRule;
    private Boolean isCover;
    private Integer maxCoverNum;
    private String remark;
    private Long sellBillId;

    public Long getSellBillId() {
        return this.sellBillId;
    }

    public void setSellBillId(Long sellBillId) {
        this.sellBillId = sellBillId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getBlanceType() {
        return this.blanceType;
    }

    public void setBlanceType(Byte blanceType) {
        this.blanceType = blanceType;
    }

    public Byte getCouponType() {
        return this.couponType;
    }

    public void setCouponType(Byte couponType) {
        this.couponType = couponType;
    }

    public String getFaceValue() {
        return this.faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public BigDecimal getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getRealPrice() {
        return this.realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public String getUseRule() {
        return this.useRule;
    }

    public void setUseRule(String useRule) {
        this.useRule = useRule;
    }

    public Boolean getIsCover() {
        return this.isCover;
    }

    public void setIsCover(Boolean isCover) {
        this.isCover = isCover;
    }

    public Integer getMaxCoverNum() {
        return this.maxCoverNum;
    }

    public void setMaxCoverNum(Integer maxCoverNum) {
        this.maxCoverNum = maxCoverNum;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return "TraderCouponTemplate [code=" + this.code + ", name=" + this.name + ", blanceType=" + this.blanceType + ", couponType="
                + this.couponType + ", faceValue=" + this.faceValue + ", originalPrice=" + this.originalPrice + ", realPrice=" + this.realPrice
                + ", useRule=" + this.useRule + ", isCover=" + this.isCover + ", maxCoverNum=" + this.maxCoverNum + ", remark=" + this.remark + "]";
    }
}

