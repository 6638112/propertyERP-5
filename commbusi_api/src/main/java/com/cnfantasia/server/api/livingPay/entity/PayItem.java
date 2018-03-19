package com.cnfantasia.server.api.livingPay.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.StringUtils;

import java.util.Date;

/**
 * 缴费项
 * @Author: wenfq
 * @Date: 2017-11-13 13:49
 */
public class PayItem {
    private long id;
    private String name;
    private String picUrl;
    private int sort;
    private int type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
