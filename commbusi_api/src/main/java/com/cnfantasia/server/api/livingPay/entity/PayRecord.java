package com.cnfantasia.server.api.livingPay.entity;

import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生活缴费付款记录
 * @Author: wenfq
 * @Date: 2017-11-13 14:48
 */
public class PayRecord {
    private long id;
    private String changeObject;
    private String picUrl;
    private String typeName;
    private String address;
    private String payTime;
    private BigDecimal amount;
    private BigDecimal amtBalance;
    private int status;
    private String statusStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChangeObject() {
        return changeObject;
    }

    public void setChangeObject(String changeObject) {
        this.changeObject = changeObject;
    }

    public String getPicUrl() {
        if(!StringUtils.isEmpty(this.picUrl)){
            IFileServerService fileServerService = (IFileServerService) CnfantasiaCommbusiUtil.getBeanManager("fileServerService");
            return fileServerService.getAccessUrl("livingPay/" + this.picUrl, (Date) null);
        }

        return null;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmtBalance() {
        return amtBalance;
    }

    public void setAmtBalance(BigDecimal amtBalance) {
        this.amtBalance = amtBalance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
