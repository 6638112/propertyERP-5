package com.cnfantasia.server.api.msgpush.web;

/**
 * @author wangzhe
 * @date 2017年3月9日
 * @description 华为请求access token的返回值
 *
 */
public class HwToken {

    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

}
