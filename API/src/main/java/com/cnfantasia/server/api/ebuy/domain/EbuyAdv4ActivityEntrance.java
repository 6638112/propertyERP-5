package com.cnfantasia.server.api.ebuy.domain;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;


import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: EbuyAdv4ActivityEntrance
 * @Date: 2016-12-26 14:28
 * @Auther: kangduo
 * @Description: ()
 */
public class EbuyAdv4ActivityEntrance implements Serializable {
    private static final long serialVersionUID = -6441199811780693977L;

    //1 幸运购 2 拼购 3 限时购
    private Integer type;
    //状态，0，预告，1，进行中，为了图片显示及跳转
    private Integer status;
    private String title;
    private String linkUrl;
    private String showPic;
    private String prePic;

    public EbuyAdv4ActivityEntrance(EbuyAdvertise advertise) {
        ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
        this.type = advertise.getType();
        this.title = advertise.getTittle();
        this.linkUrl = sysParamManager.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + advertise.getLinkUrl();
        String pic = advertise.getPicName();
        if (!DataUtil.isEmpty(pic)) {
            String[] split = pic.split(";");
            if (split.length == 1) {
                this.showPic = CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, split[0], null);
            } else {
                this.showPic = CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, split[0], null);
                this.prePic = CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, split[1], null);
            }
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
    }

    public String getPrePic() {
        return prePic;
    }

    public void setPrePic(String prePic) {
        this.prePic = prePic;
    }
}
