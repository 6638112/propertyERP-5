package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 每天0点对超过用券时间的券作过期失效处理
 */
public class UserCouponTask implements ISynTask {

    private IUserCouponService userCouponService;

    @Override
    public Integer execTask() {
        return userCouponService.updateUserCouponInvalidBatch(new HashMap<String, Object>());
    }

    public void setUserCouponService(IUserCouponService userCouponService) {
        this.userCouponService = userCouponService;
    }
}
