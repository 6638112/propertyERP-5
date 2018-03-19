package com.cnfantasia.server.api.coupon.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.ebuy.constant.EbuySupplyMerchantConstant;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * @ClassName: UserCouponEntity
 * @Date: 2016-07-13 14:00
 * @Auther: kangduo
 * @Description:()
 */
public class UserCouponEntity implements Serializable {
    private static final long serialVersionUID = -1953260114651682067L;

    private BigInteger id;
    private BigInteger userId;
    private Integer userCouponStatus;
    private String couponName;
    private String couponDesc;
    private Integer couponType;
    private Integer maxDiscountPercent;
    private Integer leastSpendUse;
    private Integer discountMoney;
    private Integer discountValue;
    private BigInteger exchangeProductId;
    private String useEndDate;
    private String updTime;
    private BigInteger supplyMerchantId;
    private Integer useType;
    private String supplyMerchantName;
    private Integer supplyMerchantType;

    //维修券属性
    private Integer dredgeSupportLevel;
    private BigInteger communitySupplyType;
    private BigInteger dredgeType;
    private Integer dredgeCouponFeeType;

    //当前小区是否在供应商服务范围内
    private Integer inMerchantService;

    //跳转类型
    private Integer jumpType;
    private String jumpData;
    private String linkUrl;
    
    private String code;

    private List<BigInteger> discountProductShelfIdList;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public Integer getUserCouponStatus() {
        return userCouponStatus;
    }

    public void setUserCouponStatus(Integer userCouponStatus) {
        this.userCouponStatus = userCouponStatus;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getMaxDiscountPercent() {
        return maxDiscountPercent;
    }

    public void setMaxDiscountPercent(Integer maxDiscountPercent) {
        this.maxDiscountPercent = maxDiscountPercent;
    }

    public Integer getLeastSpendUse() {
        return leastSpendUse;
    }

    public void setLeastSpendUse(Integer leastSpendUse) {
        this.leastSpendUse = leastSpendUse;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    public BigInteger getExchangeProductId() {
        return exchangeProductId;
    }

    public void setExchangeProductId(BigInteger exchangeProductId) {
        this.exchangeProductId = exchangeProductId;
    }

    public String getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(String useEndDate) {
        this.useEndDate = useEndDate;
    }

    public BigInteger getSupplyMerchantId() {
        return supplyMerchantId;
    }

    public void setSupplyMerchantId(BigInteger supplyMerchantId) {
        this.supplyMerchantId = supplyMerchantId;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getSupplyMerchantName() {
        return supplyMerchantName;
    }

    public void setSupplyMerchantName(String supplyMerchantName) {
        this.supplyMerchantName = supplyMerchantName;
    }

    public Integer getSupplyMerchantType() {
        return supplyMerchantType;
    }

    public void setSupplyMerchantType(Integer supplyMerchantType) {
        this.supplyMerchantType = supplyMerchantType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getCouponDesc() {
        StringBuilder sb = new StringBuilder();
        if (this.useType == 6 || useType == 7) {
            if (this.leastSpendUse == 0) {
                sb.append("可减").append(this.discountMoney).append("元");
            } else {
                sb.append("满").append(this.leastSpendUse).append("减").append(this.discountMoney);
            }
        } else if (this.useType.equals(5) && !StringUtils.isEmpty(supplyMerchantName)) {
            sb.append("购买").append(this.supplyMerchantName);
            if (this.supplyMerchantType.equals(2)) {
                sb.append("(楼下店)");
            }
            sb.append("商品");
        } else if (this.useType.equals(1)) {
            sb.append("超市购物");
        } else if (this.useType.equals(0)) {
            sb.append("超市购物");
        } else if (this.useType.equals(3)) {//维修类型
            if (this.dredgeCouponFeeType.equals(1)) {
                sb.append("抵扣维修家政订单费用");
            } else if (this.dredgeCouponFeeType.equals(2)) {
                sb.append("抵扣维修家政订单中人工费");
            } else if (this.dredgeCouponFeeType.equals(3)) {
                sb.append("抵扣维修家政订单中其它费");
            }
        }
        return sb.toString();
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public Integer getDredgeSupportLevel() {
        return dredgeSupportLevel;
    }

    public void setDredgeSupportLevel(Integer dredgeSupportLevel) {
        this.dredgeSupportLevel = dredgeSupportLevel;
    }

    public BigInteger getCommunitySupplyType() {
        return communitySupplyType;
    }

    public void setCommunitySupplyType(BigInteger communitySupplyType) {
        this.communitySupplyType = communitySupplyType;
    }

    public BigInteger getDredgeType() {
        return dredgeType;
    }

    public void setDredgeType(BigInteger dredgeType) {
        this.dredgeType = dredgeType;
    }

    public Integer getDredgeCouponFeeType() {
        return dredgeCouponFeeType;
    }

    public void setDredgeCouponFeeType(Integer dredgeCouponFeeType) {
        this.dredgeCouponFeeType = dredgeCouponFeeType;
    }

    public Integer getInMerchantService() {
        return inMerchantService;
    }

    public void setInMerchantService(Integer inMerchantService) {
        this.inMerchantService = inMerchantService;
    }

    public Integer getJumpType() {
        if (this.useType.intValue() == CouponUseTypeConstant.REPAIR) {
            return 1;
        } else if (this.useType.intValue() == CouponUseTypeConstant.SUPPLY_MERCHANT && "卖座网".equals(this.supplyMerchantName)) {
            return 2;
        } else if (this.useType.intValue() == CouponUseTypeConstant.SUPPLY_MERCHANT && "冠游科技".equals(this.supplyMerchantName)) {
            return 3;
        } else if (this.useType.intValue() == CouponUseTypeConstant.SUPPLY_MERCHANT && supplyMerchantType != null
                && this.supplyMerchantType == EbuySupplyMerchantConstant.MerchantType.Building_Service
                && inMerchantService != null) {
            return 4;
        } else if (this.useType.intValue() == CouponUseTypeConstant.COMMUNITY_PRODUCT
                || this.useType.intValue() == CouponUseTypeConstant.HOME_PRODUCT) {
            return 5;
        }
        return 0;
    }

    public void setJumpType(Integer jumpType) {
        this.jumpType = jumpType;
    }

    public String getJumpData() {
        if (getJumpType() == 1) {
            return "家政维修";
        }else if (getJumpType() == 2) {
            return "http://resource.jiefangqu.com/docs/itemListTickets/index.htm";
        } else if (getJumpType() == 3) {
            return "-1";
        } else if (getJumpType() == 4) {
            return this.supplyMerchantId.toString();
        } else if (getJumpType() == 5) {
            return this.linkUrl;
        }
        return jumpData;
    }

    public void setJumpData(String jumpData) {
        this.jumpData = jumpData;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public List<BigInteger> getDiscountProductShelfIdList() {
        return discountProductShelfIdList;
    }

    public void setDiscountProductShelfIdList(List<BigInteger> discountProductShelfIdList) {
        this.discountProductShelfIdList = discountProductShelfIdList;
    }

	public String getCode() {
		if(code != null && code.length() > 4) {
			StringBuilder str = new StringBuilder(code.replace(" ",""));  
		    int i = str.length() / 4;  
		    int j = str.length() % 4;  
		  
		    for (int x = (j == 0 ? i - 1 : i); x > 0; x--) {  
		        str = str.insert(x * 4, " ");  
		    }
			return str.toString();
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
