package com.cnfantasia.server.api.userCoupon.service;

import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.coupon.service.ICouponService;
import com.cnfantasia.server.api.coupon.service.couponSender.AbstractCouponSender;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.userCoupon.dao.IUserCouponDao;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductShelf.service.IEbuyProductShelfBaseService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shiyl on 2016/4/14.
 */
public class UserCouponService implements IUserCouponService {

    private IUserCouponDao userCouponDao;
    private IEbuyProductShelfBaseService ebuyProductShelfBaseService;
    private IEbuyOrderBaseService ebuyOrderBaseService;
    private ICouponService couponService;
    private AbstractCouponSender cashCouponSender;

    @Override
    public List<UserCouponEntity> getUserCouponList(Map<String, Object> paramMap) {
        return userCouponDao.getUserCouponList(paramMap);
    }

    @Override
    public Integer getUserCouponCount(Map<String, Object> paramMap) {
        return userCouponDao.getUserCouponCount(paramMap);
    }

    @Override
    public Integer updateUserCouponValidBatchByIds(List<BigInteger> ids) {
        return userCouponDao.updateUserCouponValidBatchByIds(ids);
    }

    @Override
    public Integer updateUserCouponInvalidBatch(Map<String, Object> paramMap) {
        return userCouponDao.updateUserCouponInvalidBatch(paramMap);
    }

    @Override
    public List<UserCouponEntity> getDredgeCouponListCanUse(BigDecimal amount,
                                                            BigInteger userId,
                                                            BigInteger communitySupplyType,
                                                            BigInteger dredgeTypeId,
                                                            BigInteger dredgeProductId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("communitySupplyType", communitySupplyType);
        param.put("dredgeType", dredgeTypeId);
        param.put("amount", amount);
        param.put("dredgeProductId", dredgeProductId);
        return userCouponDao.getDredgeCouponListCanUse(param);
    }

    @Override
    public Integer delUserCouponUseRecord(BigInteger orderId) {
        return userCouponDao.delUserCouponUseRecord(orderId);
    }

    @Override
    public List<UserCouponEntity> getUserCouponList4EbuyProduct(List<ProductIdQtyEntity> productQtyList, BigInteger userId) {
        List<UserCouponEntity> userCouponEntityList = userCouponDao.getUserCouponList4EbuyProduct(userId);
        List<UserCouponEntity> resList = new ArrayList<>();
        if (!DataUtil.isEmpty(userCouponEntityList)) {
            List<EbuyProductShelf> shelfList = new ArrayList<>(productQtyList.size());
            for (ProductIdQtyEntity productIdQtyEntity : productQtyList) {
                shelfList.add(ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(productIdQtyEntity.getProductId()));
            }
            for (UserCouponEntity entity : userCouponEntityList) {
                int total = 0;
                for (int i = 0; i < productQtyList.size(); i++) {
                    if (entity.getDiscountProductShelfIdList().contains(shelfList.get(i).getId())) {
                        total += shelfList.get(i).getPriceDiscount() * productQtyList.get(i).getProductQty();
                    }
                }
                if (total > 0 && total >= entity.getLeastSpendUse() * 100) {
                    resList.add(entity);
                }
            }
        }
        return resList;
    }

    @Override
    public List<UserCouponEntity> sendShareOrderCoupon(BigInteger userId, BigInteger orderId) {
        EbuyOrder order = ebuyOrderBaseService.getEbuyOrderBySeqId(orderId);
        if (order.getBuyerId().compareTo(userId) != 0) {
            throw new BusinessRuntimeException("userCouponService.sendShareOrderCoupon.wrongUserId");
        }
        List<Coupon> couponList = couponService.getShareOrderCouponCanSend(orderId);
        cashCouponSender.sendCoupons(couponList, userId, orderId);
        List<UserCouponEntity> userCouponEntityList = new ArrayList<>(couponList.size());
        UserCouponEntity entity = null;
        for (Coupon coupon : couponList) {
            entity = new UserCouponEntity();
            entity.setDiscountMoney(coupon.getDiscountMoney());
            entity.setLeastSpendUse(coupon.getLeastSpendUse());
            entity.setUseEndDate(coupon.getUseEndDateType() == 1 ? coupon.getUseEndDate() :
                    DateUtil.formatDay.get().format(DateUtil.getNextDate(new Date(), Calendar.DAY_OF_YEAR, coupon.getUseDateNumber())));
            entity.setCouponName(coupon.getCouponName());
            entity.setUseType(coupon.getUseType());
            entity.setLinkUrl(coupon.getLinkUrl());
            userCouponEntityList.add(entity);
        }
        return userCouponEntityList;
    }


    public IUserCouponDao getUserCouponDao() {
        return userCouponDao;
    }

    public void setUserCouponDao(IUserCouponDao userCouponDao) {
        this.userCouponDao = userCouponDao;
    }
    public void setEbuyProductShelfBaseService(IEbuyProductShelfBaseService ebuyProductShelfBaseService) {
        this.ebuyProductShelfBaseService = ebuyProductShelfBaseService;
    }
    public void setEbuyOrderBaseService(IEbuyOrderBaseService ebuyOrderBaseService) {
        this.ebuyOrderBaseService = ebuyOrderBaseService;
    }
    public void setCouponService(ICouponService couponService) {
        this.couponService = couponService;
    }
    public void setCashCouponSender(AbstractCouponSender cashCouponSender) {
        this.cashCouponSender = cashCouponSender;
    }
}
