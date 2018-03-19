package com.cnfantasia.server.api.homeMessage.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.common.util.LicaiDo;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DataUtil;

/**
 * @ClassName: HomeMessageEntity
 * @Date: 2017-02-23 14:16
 * @Auther: kangduo
 * @Description: ()
 */
public class HomeMessageEntity implements Comparable<HomeMessageEntity>, LicaiDo{
    private String iconUrl;
    private String code;
    private String name;
    private String updTime;
    private String content;
    private String extInfo;
    private Integer order;
    private String linkUrl;

    public String getIconUrl() {
        return DataUtil.isEmpty(iconUrl) ? null : CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.Home_Message_Icon_PicBase, iconUrl, null);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public int compareTo(HomeMessageEntity entity) {
        //先order,后updTime排序
        if (this.order == null && entity.getOrder() == null) {
            if (this.updTime == null && entity.getUpdTime() == null) {
                return 0;
            } else if (this.updTime == null) {
                return 1;
            } else if (entity.getUpdTime() == null) {
                return -1;
            }
            return entity.getUpdTime().compareTo(this.getUpdTime());
        } else if (this.order == null) {
            return 1;
        } else if (entity.getOrder() == null) {
            return -1;
        }

        return entity.getOrder() - this.order;
    }

	@Override
	public boolean isLicai() {
		if(name != null && (name.contains("钱") || name.contains("借"))) {
			return true;
		}
		return false;
	}
}
