package com.cnfantasia.server.api.lotteryDraw.entity;

import java.math.BigInteger;

/**
 * @ClassName: LotteryDrawEntity
 * @Date: 2016-11-25 14:46
 * @Auther: yanghua
 * @Description:(幸运抽奖实体)
 */
public class LotteryDrawEntity {
    /**商品ID*/
    private BigInteger id;
    /**商品名称*/
    private String name;
    /**商品描述*/
    private String desc;
    /**密文用户电话*/
    private String phone;
    /**明文用户电话*/
    private String mobile;
    /**中状态*/
    private Integer prizeStatus;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
    	if(this.phone != null && this.phone.length() > 7){
    		phone = phone.substring(0,3) + "****" + phone.substring(7,this.phone.length());
    	}
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPrizeStatus() {
        return prizeStatus;
    }

    public void setPrizeStatus(Integer prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
