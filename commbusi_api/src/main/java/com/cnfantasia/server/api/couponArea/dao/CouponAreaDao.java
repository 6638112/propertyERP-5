package com.cnfantasia.server.api.couponArea.dao;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by shiyl on 2016/4/14.
 */
public class CouponAreaDao extends AbstractBaseDao implements ICouponAreaDao {

    public List<Map> getCityListByCouponId(BigInteger couponId) {
        return sqlSession.selectList("couponArea.getCityListByCouponId", couponId);
    }

    public List<Map> getGroupBuildingListByCouponId(BigInteger couponId) {
        return sqlSession.selectList("couponArea.getGroupBuildingListByCouponId", couponId);
    }

    public Integer delCouponAreaByCouponId(BigInteger couponId) {
        return sqlSession.delete("couponArea.delCouponAreaByCouponId", couponId);
    }
}
