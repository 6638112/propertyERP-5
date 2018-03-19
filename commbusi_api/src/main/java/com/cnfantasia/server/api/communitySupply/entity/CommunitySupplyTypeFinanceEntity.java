package com.cnfantasia.server.api.communitySupply.entity;

import com.alibaba.fastjson.JSON;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CommunitySupplyTypeFinanceEntity
 * @Date: 2017-02-15 11:19
 * @Auther: kangduo
 * @Description: (理财类的图标，现放到个人中心)
 */
public class CommunitySupplyTypeFinanceEntity {

    private boolean hasUpShelf;
    private String picBasePath;
    private List<BigInteger> areaIdList;
    private List<FinanceEntity> entityList;

    public boolean isHasUpShelf() {
        return hasUpShelf;
    }

    public void setHasUpShelf(boolean hasUpShelf) {
        this.hasUpShelf = hasUpShelf;
    }

    public List<FinanceEntity> getEntityList() {
        if (entityList == null || entityList.size() == 0 || areaIdList == null) {
            return null;
        }
        List<FinanceEntity> newList = new ArrayList<FinanceEntity>(entityList.size());
        for (FinanceEntity financeEntity : entityList) {
            if (areaIdList.contains(financeEntity.getAreaId())) {
                newList.add(financeEntity);
            }
        }
        return newList;
    }

    public void setEntityList(List<FinanceEntity> entityList) {
        this.entityList = entityList;
    }

    public String getPicBasePath() {
        return picBasePath;
    }

    public void setPicBasePath(String picBasePath) {
        this.picBasePath = picBasePath;
    }

    public List<BigInteger> getAreaIdList() {
        return areaIdList;
    }

    public void setAreaIdList(List<BigInteger> areaIdList) {
        this.areaIdList = areaIdList;
    }

    private class FinanceEntity {
        private String iconPic;
        private String name;
        private String code;
        private String linkUrl;
        private BigInteger areaId;

        public String getIconPic() {
            return getPicBasePath() + iconPic;
        }

        public void setIconPic(String iconPic) {
            this.iconPic = iconPic;
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

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public BigInteger getAreaId() {
            return areaId;
        }

        public void setAreaId(BigInteger areaId) {
            this.areaId = areaId;
        }
    }
}
