package com.cnfantasia.server.api.coupon.entity;

import com.cnfantasia.server.domainbase.coupon.entity.Coupon;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: CouponEntity
 * @Date: 2016-07-26 14:16
 * @Auther: kangduo
 * @Description:(消费券entity)
 */
public class CouponEntity extends Coupon implements Serializable {
    private static final long serialVersionUID = 38200912249579718L;
    //维修配置ID
    private BigInteger dredgeCouponConfigId;
    //维修券支持类型
    private Integer supportLevel;
    //维修券一支持大类
    private BigInteger communitySupplyTypeId;
    //维修券支持子类
    private BigInteger dredgeTypeId;
    //维修券可抵扣费用类型 1 所有类型费用 2 人工费 3 材料费
    private Integer couponFeeType;
    /**定向社区店商品、定向到家商品*/
    private String[] productIds;

    public Integer getSupportLevel() {
        return supportLevel;
    }

    public void setSupportLevel(Integer supportLevel) {
        this.supportLevel = supportLevel;
    }

    public BigInteger getCommunitySupplyTypeId() {
        return communitySupplyTypeId;
    }

    public void setCommunitySupplyTypeId(BigInteger communitySupplyTypeId) {
        this.communitySupplyTypeId = communitySupplyTypeId;
    }

    public BigInteger getDredgeTypeId() {
        return dredgeTypeId;
    }

    public void setDredgeTypeId(BigInteger dredgeTypeId) {
        this.dredgeTypeId = dredgeTypeId;
    }

    public Integer getCouponFeeType() {
        return couponFeeType;
    }

    public void setCouponFeeType(Integer couponFeeType) {
        this.couponFeeType = couponFeeType;
    }

    public BigInteger getDredgeCouponConfigId() {
        return dredgeCouponConfigId;
    }

    public void setDredgeCouponConfigId(BigInteger dredgeCouponConfigId) {
        this.dredgeCouponConfigId = dredgeCouponConfigId;
    }

	public String[] getProductIds() {
		return productIds;
	}

	public void setProductIds(String[] productIds) {
		this.productIds = productIds;
	}
    
}
