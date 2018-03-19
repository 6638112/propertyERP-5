package com.cnfantasia.server.api.couponArea.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by shiyl on 2016/4/14.
 */
public interface ICouponAreaService {

    public List<Map> getCityListByCouponId(BigInteger couponId);

    public List<Map> getGroupBuildingListByCouponId(BigInteger couponId);

    public Integer delCouponAreaByCouponId(BigInteger couponId);
}
