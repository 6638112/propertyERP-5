package com.cnfantasia.server.api.couponArea.service;

import com.cnfantasia.server.api.couponArea.dao.ICouponAreaDao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by shiyl on 2016/4/14.
 */
public class CouponAreaService implements ICouponAreaService {

    private ICouponAreaDao couponAreaDao;

    public List<Map> getCityListByCouponId(BigInteger couponId){
        return couponAreaDao.getCityListByCouponId(couponId);
    }

    public List<Map> getGroupBuildingListByCouponId(BigInteger couponId){
        return couponAreaDao.getGroupBuildingListByCouponId(couponId);
    }

    public Integer delCouponAreaByCouponId(BigInteger couponId) {
        return couponAreaDao.delCouponAreaByCouponId(couponId);
    }

    public ICouponAreaDao getCouponAreaDao() {
        return couponAreaDao;
    }

    public void setCouponAreaDao(ICouponAreaDao couponAreaDao) {
        this.couponAreaDao = couponAreaDao;
    }
}
