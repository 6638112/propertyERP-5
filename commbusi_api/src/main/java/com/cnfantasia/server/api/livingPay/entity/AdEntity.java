package com.cnfantasia.server.api.livingPay.entity;

/**
 * @className: AdEntity
 * @date: 2018-01-04 14:16
 * @author: yanghua
 * @description:(生活缴费首页广告)
 */
public class AdEntity {
    private String imgUrl;
    private String imgLinkUrl;

    public String getImgLinkUrl() {
        return imgLinkUrl;
    }

    public void setImgLinkUrl(String imgLinkUrl) {
        this.imgLinkUrl = imgLinkUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
