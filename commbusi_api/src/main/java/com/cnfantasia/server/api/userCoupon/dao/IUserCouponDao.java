package com.cnfantasia.server.api.userCoupon.dao;

import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by shiyl on 2016/4/14.
 */
public interface IUserCouponDao {

    public List<UserCouponEntity> getUserCouponList(Map<String, Object> paramMap);

    public Integer getUserCouponCount(Map<String, Object> paramMap);

    public Integer updateUserCouponValidBatchByIds(List<BigInteger> ids);

    public Integer updateUserCouponInvalidBatch(Map<String, Object> paramMap);

    public List<UserCouponEntity> getDredgeCouponListCanUse(Map<String, Object> paramMap);

    public Integer delUserCouponUseRecord(BigInteger orderId);

    List<UserCouponEntity> getUserCouponList4EbuyProduct(BigInteger userId);
}
