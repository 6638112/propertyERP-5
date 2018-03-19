package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;
import java.util.Arrays;
import java.util.Date;

public class UpdateTraderCouponStatusReq implements YdtRequest {
    private static final long serialVersionUID = 1149006940654223842L;
    private String traderCouponGrantSn;
    private Byte optType;
    private Date updateTime;
    private String[] newCarCodeArray;
    private String parkCode;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String[] getNewCarCodeArray() {
        return this.newCarCodeArray;
    }

    public void setNewCarCodeArray(String[] newCarCodeArray) {
        this.newCarCodeArray = newCarCodeArray;
    }

    public String getTraderCouponGrantSn() {
        return this.traderCouponGrantSn;
    }

    public void setTraderCouponGrantSn(String traderCouponGrantSn) {
        this.traderCouponGrantSn = traderCouponGrantSn;
    }

    public Byte getOptType() {
        return this.optType;
    }

    public void setOptType(Byte optType) {
        this.optType = optType;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String toString() {
        return "UpdateTraderCouponStatusReq [traderCouponGrantSn=" + this.traderCouponGrantSn + ", optType=" + this.optType + ", updateTime="
                + this.updateTime + ", newCarCodeArray=" + Arrays.toString(this.newCarCodeArray) + "]";
    }
}

