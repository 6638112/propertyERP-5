package com.cnfantasia.server.api.coupon.service.couponSender;

import com.cnfantasia.server.api.coupon.constant.CouponTypeConstant;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;


public class CashCouponSender extends AbstractCouponSender {

    public CashCouponSender() {
        super(CouponTypeConstant.CASHCOUPON);
    }

    @Override
    protected boolean canCouponSend(Coupon coupon, EbuyOrder ebuyOrder) {
        boolean flag = false;

        //满足最低消费额
        if (coupon.getLeastSpendSend() * 100 <= ebuyOrder.getAmount() && ebuyOrder.getType().compareTo(coupon.getGoalType())==0) {
            //是否使用了优惠
            if (ebuyOrder.getTotalCouponAmount() != null && ebuyOrder.getTotalCouponAmount() > 0) {
                flag = coupon.getUseDiscountSend().equals("Y");
            } else {
                flag = true;
            }
        }
        return flag;
    }
}
