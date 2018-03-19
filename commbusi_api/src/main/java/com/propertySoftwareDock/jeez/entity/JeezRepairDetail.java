package com.propertySoftwareDock.jeez.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: JeezRepairDetail
 * @Date: 2016-12-21 16:35
 * @Auther: kangduo
 * @Description: (极致维修单详情)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Item")
public class JeezRepairDetail {

    @XmlElement(name="BillNo")
    private String billNo;

    @XmlElement(name="Status")
    private String status;

    @XmlElement(name="StartWorkTime")
    private String startWorkTime;

    @XmlElement(name="EndWorkTime")
    private String endWorkTime;

    @XmlElement(name="ReceiveTime")
    private String receiveTime;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartWorkTime() {
        if (startWorkTime == null || "".equals(startWorkTime))
            return null;

        if (startWorkTime.contains("-")) {
            return startWorkTime;
        }

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(startWorkTime));
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getEndWorkTime() {
        if (endWorkTime == null || "".equals(endWorkTime))
            return null;

        if (endWorkTime.contains("-")) {
            return endWorkTime;
        }

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(endWorkTime));
    }

    public void setEndWorkTime(String endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public String getReceiveTime() {
        if (receiveTime == null || "".equals(receiveTime))
            return null;

        if (receiveTime.contains("-")) {
            return receiveTime;
        }

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(receiveTime));
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

}
