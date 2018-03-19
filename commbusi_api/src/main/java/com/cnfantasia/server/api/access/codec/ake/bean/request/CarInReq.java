package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;
import java.util.Date;

public class CarInReq implements ParkRequest {
    private static final long serialVersionUID = -1960273434451431562L;
    private String parkCode; // 停车场编号
    private String carNo; // 车牌
    private String cardCode; // 卡编号 与carNo二选一
    private Date inDate; // 入场时间
    private String enterAddress; // 入口位置。标记入场入口号
    private Float finalReliability; // 综合车牌最终识别可信度
    private Integer carClass; // 车型 1：小型车2：中型车3：大型车4:摩托车5：电动自行车
    private Float enterReliability; // 车牌识别可信度
    private String enterCarCode;
    private Integer enterType; // 进场类型 0未确认，1自动放行，2确认放行，3异常放行
    private Byte parkVipType; // 线下VIP类型 0:不是VIP；1：普通VIP

    public Byte getParkVipType() {
        return this.parkVipType;
    }

    public void setParkVipType(Byte parkVipType) {
        this.parkVipType = parkVipType;
    }

    public String getEnterAddress() {
        return this.enterAddress;
    }

    public void setEnterAddress(String enterAddress) {
        this.enterAddress = enterAddress;
    }

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getCarNo() {
        return this.carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCardCode() {
        return this.cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Date getInDate() {
        return this.inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Float getFinalReliability() {
        return this.finalReliability;
    }

    public void setFinalReliability(Float finalReliability) {
        this.finalReliability = finalReliability;
    }

    public Integer getCarClass() {
        return this.carClass;
    }

    public void setCarClass(Integer carClass) {
        this.carClass = carClass;
    }

    public Float getEnterReliability() {
        return this.enterReliability;
    }

    public void setEnterReliability(Float enterReliability) {
        this.enterReliability = enterReliability;
    }

    public String getEnterCarCode() {
        return this.enterCarCode;
    }

    public void setEnterCarCode(String enterCarCode) {
        this.enterCarCode = enterCarCode;
    }

    public Integer getEnterType() {
        return this.enterType;
    }

    public void setEnterType(Integer enterType) {
        this.enterType = enterType;
    }

    public String toString() {
        return "CarInReq [parkCode=" + this.parkCode + ", carNo=" + this.carNo + ", cardCode=" + this.cardCode + ", inDate=" + this.inDate
                + ", enterAddress=" + this.enterAddress + ", finalReliability=" + this.finalReliability + ", carClass=" + this.carClass
                + ", enterReliability=" + this.enterReliability + ", enterCarCode=" + this.enterCarCode + ", enterType=" + this.enterType
                + ", parkVipType=" + this.parkVipType + "]";
    }
}

