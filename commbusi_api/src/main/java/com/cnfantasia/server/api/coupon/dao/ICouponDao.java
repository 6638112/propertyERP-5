package com.cnfantasia.server.api.coupon.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.coupon.entity.CarCoupon;
import com.cnfantasia.server.api.coupon.entity.CouponDto;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * Created by shiyl on 2016/4/14.
 */
public interface ICouponDao {
    List<CouponDto> getCouponListByCondition(Map<String, Object> paramMap, PageModel pageModel);

    List<UserCoupon> getUserCouponList(Map<String, Object> paramMap);

    Integer updateCouponStatusClosedBatch(Map<String, Object> paramMap);

    List<Coupon> getCouponListCanSendToUser(Map<String, Object> paramMap);

    List<Long> qryCanCouponReceiveScene(BigInteger userId, BigInteger cityId, BigInteger gbId);

    List<Map<String, Long>> getCouponSceneReceiveThing(BigInteger userId);

    List<Coupon> getCanReceiveCouponBySceneId(BigInteger userId, Integer sceneId, BigInteger cityId, BigInteger gbId);
    
    List<Map<String, Object>> getCouponProductList(BigInteger couponId);
    
    Integer delCouponProductsByCouponId(BigInteger couponId);
    
    /**
     * 根据用户id查询停车券
     * @param userId
     * @return
     */
    CarCoupon selectCarCouponByUserId(BigInteger userId);
    
    /**
     * 根据用户id查询拥有的车禁券数量
     * @param userId
     * @return
     */
    Integer selectCarCouponCountWithUserId(BigInteger userId);
    
    /**
     * 查询可用的停车券
     * @param userId
     * @return
     */
    Coupon selectAvailableCarCoupon();

    /**
     * 扫码送停车券
     * @return
     */
    Coupon selectAvailableCarCoupon(BigInteger storeId);

    /**
     * 根据用户id查询可用的停车券
     * @param userId
     * @return
     */
    CarCoupon selectAvailableCarCouponByUserId(BigInteger userId);

    BigInteger getCityIdByGb(BigInteger gbId);

    /**
     * 更新临停车扫码券数量
     * @param id
     * @param storeId
     */
    int updateCouponNumsByStore(BigInteger id, BigInteger storeId);

    List<Coupon> getShareOrderCouponCanSend(Map<String, Object> param);
}
