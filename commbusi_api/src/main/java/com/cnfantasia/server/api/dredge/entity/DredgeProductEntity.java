package com.cnfantasia.server.api.dredge.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: DredgeProductEntity
 * @Date: 2017-05-08 14:28
 * @Auther: kangduo
 * @Description: (维修服务商品)
 */
public class DredgeProductEntity extends DredgeProduct implements Serializable {
    private static final long serialVersionUID = 5636536348002986760L;
    private String supplierName;
    //商品规格中价格最低价
    private Integer sellPrice;
    //商品规格最低价对应的市场价
    private Integer marketPrice;
    private String priceUnit;
    //商品规格
    private List<DredgeProductSpecification> productSpecList;

    private List<String> productPicList;
    private List<String> introducePicList;

    private Boolean inAnniversary = false;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public BigDecimal getSellPriceDecimal() {
        if (sellPrice == null) {
            return null;
        }
        return PriceUtil.div100(Long.valueOf(sellPrice));
    }
    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public BigDecimal getMarketPriceDecimal() {
        if (marketPrice == null) {
            return null;
        }
        return PriceUtil.div100(Long.valueOf(marketPrice));
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public List<DredgeProductSpecification> getProductSpecList() {
        return productSpecList;
    }

    public void setProductSpecList(List<DredgeProductSpecification> productSpecList) {
        this.productSpecList = productSpecList;
    }

    public List<String> getProductPicList() {
        String pics = getProductPic();
        if (DataUtil.isEmpty(pics)) {
            return null;
        }
        List<String> picList = Arrays.asList(pics.split(";"));
        List<String> resList = new ArrayList<>(picList.size());
        for (String pic : picList) {
            resList.add(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, pic, null));
        }
        return resList;
    }

    public void setProductPicList(List<String> productPicList) {
        this.productPicList = productPicList;
    }

    public List<String> getIntroducePicList() {
        String pics = getIntroducePic();
        if (DataUtil.isEmpty(pics)) {
            return null;
        }
        List<String> picList = Arrays.asList(pics.split(";"));
        List<String> resList = new ArrayList<>(picList.size());
        for (String pic : picList) {
            resList.add(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, pic, null));
        }
        return resList;
    }

    public void setIntroducePicList(List<String> introducePicList) {
        this.introducePicList = introducePicList;
    }

    public Boolean getInAnniversary() {
        return inAnniversary;
    }

    public void setInAnniversary(Boolean inAnniversary) {
        this.inAnniversary = inAnniversary;
    }
}
