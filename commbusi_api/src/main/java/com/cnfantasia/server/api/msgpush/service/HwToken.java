package com.cnfantasia.server.api.msgpush.service;

import java.util.Date;

/**
 * @author wangzhe
 * @date 2017年3月9日
 * @description 华为请求access token的返回值
 *
 */
public class HwToken {

    private String access_token;
    private long expires_in; // Access Token 的有效期，以秒为单位
    private Date refreshtime;

    public String getAccess_token() {
        return access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public Date getRefreshtime() {
        return refreshtime;
    }

    public void setAccess_token(final String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(final long expires_in) {
        this.expires_in = expires_in;
    }

    public void setRefreshtime(final Date refreshtime) {
        this.refreshtime = refreshtime;
    }

}
