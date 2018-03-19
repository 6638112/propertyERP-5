package com.cnfantasia.server.api.userCoupon.dao;

import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shiyl on 2016/4/14.
 */
public class UserCouponDao extends AbstractBaseDao implements IUserCouponDao {

    @Override
    public List<UserCouponEntity> getUserCouponList(Map<String, Object> paramMap) {
        return sqlSession.selectList("userCoupon.getUserCouponList", paramMap);
    }

    @Override
    public Integer getUserCouponCount(Map<String, Object> paramMap) {
        return sqlSession.selectOne("userCoupon.getUserCouponCount", paramMap);
    }

    @Override
    public Integer updateUserCouponValidBatchByIds(List<BigInteger> ids) {
        return sqlSession.update("userCoupon.updateUserCouponValidBatchByIds", ids);
    }

    @Override
    public Integer updateUserCouponInvalidBatch(Map<String, Object> paramMap) {
        return sqlSession.update("userCoupon.updateUserCouponInvalidBatch", paramMap);
    }

    @Override
    public List<UserCouponEntity> getDredgeCouponListCanUse(Map<String, Object> paramMap) {
        return sqlSession.selectList("userCoupon.getDredgeCouponListCanUse", paramMap);
    }

    @Override
    public Integer delUserCouponUseRecord(BigInteger orderId) {
        return sqlSession.delete("userCoupon.delUserCouponUseRecord", orderId);
    }

    @Override
    public List<UserCouponEntity> getUserCouponList4EbuyProduct(BigInteger userId) {
        Map<String, Object> para = new HashMap<>();
        para.put("userId", userId);
        return sqlSession.selectList("userCoupon.getUserCouponList4EbuyProduct", para);
    }

}
