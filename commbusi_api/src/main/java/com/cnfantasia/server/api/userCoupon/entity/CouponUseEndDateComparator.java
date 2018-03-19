package com.cnfantasia.server.api.userCoupon.entity;

import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @ClassName: CouponUseEndDateComparator.
 * @Date: 2017-07-17 17:14
 * @Auther: kangduo
 * @Description: ()
 */
public class CouponUseEndDateComparator implements Comparator<UserCouponEntity>, Serializable {

    private static final long serialVersionUID = -75882710911117553L;

    @Override
    public int compare(UserCouponEntity o1, UserCouponEntity o2) {
        String coupon1 = o1.getUseEndDate();
        String coupon2 = o2.getUseEndDate();
        return coupon2.compareTo(coupon1);
    }
}
