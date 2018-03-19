package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.coupon.service.ICouponService;

import java.util.HashMap;

/**
 * 每天0点对超过发券时间的券作关闭处理
 */
public class CouponTask implements ISynTask{

    private ICouponService couponService;

    @Override
    public Integer execTask() {
        return couponService.updateCouponStatusClosedBatch(new HashMap<String, Object>());
    }

    public void setCouponService(ICouponService couponService) {
        this.couponService = couponService;
    }
}
