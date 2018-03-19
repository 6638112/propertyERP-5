package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;

public class OpenGateReq implements YdtRequest {
    private static final long serialVersionUID = 163212483216833276L;
    private String parkCode;
    private Integer channelSeq;
    private Integer controlType;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public Integer getChannelSeq() {
        return this.channelSeq;
    }

    public void setChannelSeq(Integer channelSeq) {
        this.channelSeq = channelSeq;
    }

    public Integer getControlType() {
        return this.controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public String toString() {
        return "{parkCode:" + this.parkCode + "," + "channelSeq:" + this.channelSeq + "," + "controlType:" + this.controlType + "}";
    }
}

