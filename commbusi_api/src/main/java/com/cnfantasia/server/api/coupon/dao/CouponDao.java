package com.cnfantasia.server.api.coupon.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.coupon.entity.CarCoupon;
import com.cnfantasia.server.api.coupon.entity.CouponDto;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * Created by shiyl on 2016/4/14.
 */
public class CouponDao extends AbstractBaseDao implements ICouponDao {

    @Override
    public List<CouponDto> getCouponListByCondition(Map<String,Object> paramMap, PageModel pageModel) {
        if(pageModel != null) {
            paramMap.put("begin", pageModel.begin);
            paramMap.put("length", pageModel.length);
        }
        return sqlSession.selectList("coupon.getCouponListByCondition", paramMap);
    }

    @Override
    public List<UserCoupon> getUserCouponList(Map<String,Object> paramMap){
        return sqlSession.selectList("coupon.getCoupons", paramMap);
    }

    @Override
    public Integer updateCouponStatusClosedBatch(Map<String, Object> paramMap) {
        return sqlSession.update("coupon.updateCouponStatusClosedBatch", paramMap);
    }

    @Override
    public List<Coupon> getCouponListCanSendToUser(Map<String, Object> paramMap) {
        return sqlSession.selectList("coupon.getCouponListCanSendToUser", paramMap);
    }

    @Override
    public List<Long> qryCanCouponReceiveScene(BigInteger userId, BigInteger cityId, BigInteger gbId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("cityId", cityId);
        params.put("gbId", gbId);
        return sqlSession.selectList("coupon.qryCanCouponReceiveScene", params);
    }

    @Override
    public List<Map<String, Long>> getCouponSceneReceiveThing(BigInteger userId) {
        return sqlSession.selectList("coupon.getCouponSceneReceiveThing", userId);
    }

    @Override
    public List<Coupon> getCanReceiveCouponBySceneId(BigInteger userId, Integer sceneId, BigInteger cityId, BigInteger gbId) {
        Map<String, Object> para = new HashMap<>();
        para.put("userId", userId);
        para.put("sceneId", sceneId);
        para.put("cityId", cityId);
        para.put("gbId", gbId);
        return sqlSession.selectList("coupon.getCanReceiveCouponBySceneId", para);
    }
    
    @Override
    public List<Map<String, Object>> getCouponProductList(BigInteger couponId) {
        return sqlSession.selectList("coupon.getCouponProductList", couponId);
    }

    public Integer delCouponProductsByCouponId(BigInteger couponId){
    	return sqlSession.update("coupon.delCouponProductsByCouponId", couponId);
    }
    
    /**
     * �����û�id��ѯͣ��ȯ
     * @param userId
     * @return
     */
    @Override
    public CarCoupon selectCarCouponByUserId(BigInteger userId){
    	return sqlSession.selectOne("coupon.selectCarCouponByUserId", userId);
    }
    
    /**
     * �����û�id��ѯӵ�еĳ���ȯ����
     * @param userId
     * @return
     */
    @Override
    public Integer selectCarCouponCountWithUserId(BigInteger userId){
    	return sqlSession.selectOne("coupon.selectCarCouponCountWithUserId", userId);
    }
    
    /**
     * ��ѯ���õ�ͣ��ȯ
     * @param userId
     * @return
     */
    @Override
    public Coupon selectAvailableCarCoupon(){
    	return sqlSession.selectOne("coupon.selectAvailableCarCoupon");
    }

    /**
     * 扫码送停车券
     * @return
     */
    @Override
    public Coupon selectAvailableCarCoupon(BigInteger storeId){
        return sqlSession.selectOne("coupon.selectAvailableCarCouponBystoreId", storeId);
    }
    
    /**
     * �����û�id��ѯ���õ�ͣ��ȯ
     * @param userId
     * @return
     */
    @Override
    public CarCoupon selectAvailableCarCouponByUserId(BigInteger userId){
    	return sqlSession.selectOne("coupon.selectAvailableCarCouponByUserId", userId);
    }

    @Override
    public BigInteger getCityIdByGb(BigInteger gbId) {
        return sqlSession.selectOne("coupon.getCityIdByGb", gbId);
    }

    @Override
    public int updateCouponNumsByStore(BigInteger cId, BigInteger storeId) {
        Map<String, Object> para = new HashMap<>();
        para.put("cId", cId);
        para.put("storeId", storeId);
        return sqlSession.update("coupon.updateCouponNumsByStore", para);
    }

    @Override
    public List<Coupon> getShareOrderCouponCanSend(Map<String, Object> param) {
        return sqlSession.selectList("coupon.getShareOrderCouponCanSend", param);
    }
}
