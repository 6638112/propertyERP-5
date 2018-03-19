package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;
import java.util.Arrays;
import java.util.Date;

public class SendTraderCouponReq implements YdtRequest {
    private static final long serialVersionUID = -7436039166984685024L;
    private String traderCouponGrantSn;
    private TraderCouponTemplate traderCouponTemplate;
    private Date validFrom;
    private Date validTo;
    private String[] carCodeArray;
    private String parkCode;
    private String traderCode;
    private String traderName;
    private Date grantTime;

    public Date getGrantTime() {
        return this.grantTime;
    }

    public void setGrantTime(Date grantTime) {
        this.grantTime = grantTime;
    }

    public String getTraderCode() {
        return this.traderCode;
    }

    public void setTraderCode(String traderCode) {
        this.traderCode = traderCode;
    }

    public String getTraderName() {
        return this.traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getTraderCouponGrantSn() {
        return this.traderCouponGrantSn;
    }

    public void setTraderCouponGrantSn(String traderCouponGrantSn) {
        this.traderCouponGrantSn = traderCouponGrantSn;
    }

    public TraderCouponTemplate getTraderCouponTemplate() {
        return this.traderCouponTemplate;
    }

    public void setTraderCouponTemplate(TraderCouponTemplate traderCouponTemplate) {
        this.traderCouponTemplate = traderCouponTemplate;
    }

    public Date getValidFrom() {
        return this.validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return this.validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String[] getCarCodeArray() {
        return this.carCodeArray;
    }

    public void setCarCodeArray(String[] carCodeArray) {
        this.carCodeArray = carCodeArray;
    }

    public String toString() {
        return "SendTraderCouponReq [traderCouponGrantSn=" + this.traderCouponGrantSn + ", traderCouponTemplate=" + this.traderCouponTemplate
                + ", validFrom=" + this.validFrom + ", validTo=" + this.validTo + ", carCodeArray=" + Arrays.toString(this.carCodeArray) + "]";
    }
}

