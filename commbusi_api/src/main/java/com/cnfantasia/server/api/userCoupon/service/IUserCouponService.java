package com.cnfantasia.server.api.userCoupon.service;

import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by shiyl on 2016/4/14.
 */
public interface IUserCouponService {

    public List<UserCouponEntity> getUserCouponList(Map<String, Object> paramMap);

    public Integer getUserCouponCount(Map<String, Object> paramMap);

    public Integer updateUserCouponValidBatchByIds(List<BigInteger> ids);

    public Integer updateUserCouponInvalidBatch(Map<String, Object> paramMap);

    /**
     * 查询维修可用券
     * @param amount 维修单价格
     * @param userId 用户ID
     * @param communitySupplyType 维修大类
     * @param dredgeType 维修子类
     * @return
     */
    public List<UserCouponEntity> getDredgeCouponListCanUse(BigDecimal amount,
                                                            BigInteger userId,
                                                            BigInteger communitySupplyType,
                                                            BigInteger dredgeType,
                                                            BigInteger dredgeProductId);

    public Integer delUserCouponUseRecord(BigInteger orderId);

    List<UserCouponEntity> getUserCouponList4EbuyProduct(List<ProductIdQtyEntity> productQtyList, BigInteger userId);

    List<UserCouponEntity> sendShareOrderCoupon(BigInteger userId, BigInteger orderId);
}
