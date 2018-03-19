package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.entity.DredgeProductEntity;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.StringUtils;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: ThemeAdvEntity.
 * @Date: 2017-07-13 15:01
 * @Auther: kangduo
 * @Description: ()
 */
public class ThemeAdvEntity {
    private BigInteger advId;
    private Integer advType;
    private String advPic;
    private String advTitle;
    private List<DredgeProductEntity> dredgeProductList;
    private List<EbuyProdForLst> ebuyProductList;

    public BigInteger getAdvId() {
        return advId;
    }

    public void setAdvId(BigInteger advId) {
        this.advId = advId;
    }

    public Integer getAdvType() {
        return advType;
    }

    public void setAdvType(Integer advType) {
        this.advType = advType;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvPic() {
        if (StringUtils.isEmpty(advPic)) {
            return null;
        }
        return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, advPic, null);
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public List<DredgeProductEntity> getDredgeProductList() {
        return dredgeProductList;
    }

    public void setDredgeProductList(List<DredgeProductEntity> dredgeProductList) {
        this.dredgeProductList = dredgeProductList;
    }

    public List<EbuyProdForLst> getEbuyProductList() {
        return ebuyProductList;
    }

    public void setEbuyProductList(List<EbuyProdForLst> ebuyProductList) {
        this.ebuyProductList = ebuyProductList;
    }
}
