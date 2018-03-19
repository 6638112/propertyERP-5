package com.cnfantasia.server.api.communitySupply.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * @ClassName: CommunityPhoneTypeEntity
 * @Date: 2017-04-10 13:54
 * @Auther: kangduo
 * @Description: (帮助电话类型)
 */
public class CommunityPhoneTypeEntity {
    private String name;
    private String iconUrl;
    private String code;
    private Integer deepType;//1直接带号码，2需要跳下一层
    private String phone;//deepType == 1时把号码带过去
    private Integer supplyTypeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.CommunityPhoneTypeIcon, iconUrl, null);
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDeepType() {
        return deepType;
    }

    public void setDeepType(Integer deepType) {
        this.deepType = deepType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSupplyTypeId() {
        return supplyTypeId;
    }

    public void setSupplyTypeId(Integer supplyTypeId) {
        this.supplyTypeId = supplyTypeId;
    }
}
