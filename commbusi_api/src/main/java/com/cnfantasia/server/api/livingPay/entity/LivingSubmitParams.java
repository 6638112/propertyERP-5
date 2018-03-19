package com.cnfantasia.server.api.livingPay.entity;


import java.math.BigInteger;

/**
 * 提交订单参数
 * @author wenfq 2017-11-15
 */
public class LivingSubmitParams {
    String chargeObject;
    String gbName;
    String roomNum;
    String linkTel;
    double amount;
    int feeTypeId;
    BigInteger userId;
    Integer feeType;
    String name;
    String cardId;
    BigInteger spId;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getChargeObject() {
        return chargeObject;
    }

    public void setChargeObject(String chargeObject) {
        this.chargeObject = chargeObject;
    }

    public String getGbName() {
        return gbName;
    }

    public void setGbName(String gbName) {
        this.gbName = gbName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(int feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigInteger getSpId() {
        return spId;
    }

    public void setSpId(BigInteger spId) {
        this.spId = spId;
    }
}
