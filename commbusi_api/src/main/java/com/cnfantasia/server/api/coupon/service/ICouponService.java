package com.cnfantasia.server.api.coupon.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.coupon.entity.CarCoupon;
import com.cnfantasia.server.api.coupon.entity.CouponDto;
import com.cnfantasia.server.api.coupon.entity.CouponEntity;
import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * Created by shiyl on 2016/4/14.
 */
public interface ICouponService {
    public Integer addCoupon(CouponEntity coupon);

    public Integer updCoupon(CouponEntity coupon);

    public List<CouponDto> getCouponListByCondition(Map<String,Object> paramMap, PageModel pageModel);

    public List<UserCoupon> getUserCouponList(Map<String,Object> paramMap);

    public Integer updateCouponStatusClosedBatch(Map<String, Object> paramMap);

    public List<Coupon> getCouponListCanSendToUser(int couponType, BigInteger roomId);

    String couponExchange(String code, BigInteger userId);

    List<Map<String, Long>> qryCanCouponReceiveScene(BigInteger userId, BigInteger gbId);

    List<UserCouponEntity> receiveSceneCoupon(BigInteger userId, Integer sceneId, BigInteger gbId);
    
    public List<Map<String, Object>> getCouponProductList(BigInteger couponId);
    
    /**
     * 根据用户id查询停车券
     * @param userId
     * @return
     */
    public CarCoupon selectCarCouponByUserId(BigInteger userId);
    
    /**
     * 根据用户id查询可用的停车券
     * @param userId
     * @return
     */
    public CarCoupon selectAvailableCarCouponByUserId(BigInteger userId);

    List<Coupon> getShareOrderCouponCanSend(BigInteger orderId);

}
