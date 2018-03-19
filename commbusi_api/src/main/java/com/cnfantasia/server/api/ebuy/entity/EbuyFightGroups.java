package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName: EbuyFightGroups
 * @Date: 2016-06-28 10:51
 * @Auther: kangduo
 * @Description:(超市拼购)
 */
public class EbuyFightGroups implements Serializable {
    private static final long serialVersionUID = -287718194942577038L;

    private BigInteger productId;
    private Integer fightStatus;
    private Long fightPrice;
    private String fightPic;
    private String linkUrl;
    private String productName;
    private Integer leftCount;

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public Integer getFightStatus() {
        return fightStatus;
    }

    public void setFightStatus(Integer fightStatus) {
        this.fightStatus = fightStatus;
    }

    public BigDecimal getFightPrice() {
        return PriceUtil.div100(fightPrice);
    }

    public void setFightPrice(Long fightPrice) {
        this.fightPrice = fightPrice;
    }

    public String getFightPic() {
        if (fightPic != null) {
            return OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)
                    + PathConstants.Product_Image_Dir + fightPic;
        }
        return null;
    }

    public void setFightPic(String fightPic) {
        this.fightPic = fightPic;
    }

    public String getLinkUrl() {
        String server = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl);
        return server + "groupPurchase/productList.html?productId=" + productId;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }
}
