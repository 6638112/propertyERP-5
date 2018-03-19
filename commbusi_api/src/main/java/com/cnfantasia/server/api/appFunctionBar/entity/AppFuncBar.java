package com.cnfantasia.server.api.appFunctionBar.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.common.util.LicaiDo;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;

/**
 * @ClassName: AppFuncBar
 * @Date: 2017-02-23 14:04
 * @Auther: kangduo
 * @Description: (功能栏)
 */
public class AppFuncBar implements LicaiDo {
    private BigInteger id;
    private String iconUrl;
    private String name;
    private String code;
    private String androidParam;
    private String iosParam;
    //跳转类型 1:url, 2:app
    private Integer jumpType;
    private String linkUrl;

    private Integer propfeeCanpay;
    private Integer verifyStatus;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getIconUrl() {
        return DataUtil.isEmpty(iconUrl) ? null : CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.App_Function_Bar_PicBase, iconUrl, null);
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAndroidParam() {
        return androidParam;
    }

    public void setAndroidParam(String androidParam) {
        this.androidParam = androidParam;
    }

    public String getIosParam() {
        return iosParam;
    }

    public void setIosParam(String iosParam) {
        this.iosParam = iosParam;
    }

    public Integer getJumpType() {
        return jumpType;
    }

    public void setJumpType(Integer jumpType) {
        this.jumpType = jumpType;
    }

    public String getLinkUrl() {
        ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
        if (linkUrl != null && !"".equals(linkUrl) && !linkUrl.contains("http") && linkUrl.contains("/") && !linkUrl.contains("../")) {
            return sysParamManager.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + linkUrl;
        }
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getPropfeeCanpay() {
        return propfeeCanpay;
    }

    public void setPropfeeCanpay(Integer propfeeCanpay) {
        this.propfeeCanpay = propfeeCanpay;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

	@Override
	public boolean isLicai() {
		if(name.contains("理") || name.contains("借") || name.contains("保") || name.contains("宝")) {
			return true;
		}
		return false;
	}
}
