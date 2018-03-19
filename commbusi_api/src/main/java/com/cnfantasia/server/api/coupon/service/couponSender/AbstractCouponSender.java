package com.cnfantasia.server.api.coupon.service.couponSender;


import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.dao.ICouponDao;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.coupon.dao.ICouponBaseDao;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;
import com.cnfantasia.server.domainbase.redpointContent.service.IRedpointContentBaseService;
import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;
import com.cnfantasia.server.domainbase.redpointContentSource.service.IRedpointContentSourceBaseService;
import com.cnfantasia.server.domainbase.userCoupon.dao.IUserCouponBaseDao;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class AbstractCouponSender {
    private Log logger = LogFactory.getLog(getClass());

    protected Integer couponType;

    protected ICouponDao couponDao;

    protected IEbuyOrderBaseDao ebuyOrderBaseDao;

    protected IUserCouponBaseDao userCouponBaseDao;

    protected ICouponBaseDao couponBaseDao;

    protected IUuidManager uuidManager;


    protected IRedpointContentBaseService redpointContentBaseService;

    protected IRedpointContentSourceBaseService redpointContentSourceBaseService;

    public AbstractCouponSender() {
    }

    public AbstractCouponSender(Integer couponType) {
        this.couponType = couponType;
    }

    private List<Coupon> getCoupnsUsefulForUserByType(BigInteger orderId) {
        int couponType = this.couponType;
        List<Coupon> target = new ArrayList<Coupon>();
        EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("couponType", couponType);
        param.put("userId", ebuyOrder.getBuyerId());
        List<Coupon> coupons = couponDao.getCouponListCanSendToUser(param);


        for (Coupon c : coupons) {
            if (canCouponSend(c, ebuyOrder)) {
                target.add(c);
            }
        }
        return target;
    }

    protected abstract boolean canCouponSend(Coupon coupon, EbuyOrder ebuyOrder);

    public void sendCoupons(List<Coupon> couponList, BigInteger userId, BigInteger orderId) {
        List<UserCoupon> userCouponInsert = new ArrayList<UserCoupon>();
        List<Coupon> couponUpdate = new ArrayList<Coupon>();
        List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_coupon, couponList.size());
        for (int i = 0; i < couponList.size(); i++) {
            Coupon coupon = couponList.get(i);
            //用于已送数+1
            coupon.setSendCount(coupon.getSendCount() + 1);
            couponUpdate.add(coupon);

            //用于插入数据
            UserCoupon uc = new UserCoupon();
            uc.setId(ids.get(i));
            uc.setStatus(UserCouponStatus.VALID);
            uc.settUserFId(userId);
            uc.settEbuyOrderFId(orderId);
            uc.settCouponFId(coupon.getId());
            uc.setGetType(coupon.getGoalType() == 67 ? 2 : 1);
            uc.setUseEndDate(coupon.getUseEndDateType() == 1 ? coupon.getUseEndDate() :
                    DateUtil.formatDay.get().format(DateUtil.getNextDate(new Date(), Calendar.DAY_OF_YEAR, coupon.getUseDateNumber())));
            userCouponInsert.add(uc);
        }
        //送用户券并把消费券已送+1
        if (userCouponInsert.size() > 0) {
            userCouponBaseDao.insertUserCouponBatch(userCouponInsert);
            addRedPiont(userId, null);
        }
        if (couponUpdate.size() > 0) {
            couponBaseDao.updateCouponBatch(couponUpdate);
        }
    }

    public void sendCoupons(BigInteger orderId) {
        try {
            BigInteger userId = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId).getBuyerId();
            List<Coupon> coupons = getCoupnsUsefulForUserByType(orderId);
            List<UserCoupon> userCouponInsert = new ArrayList<UserCoupon>();
            List<Coupon> couponUpdate = new ArrayList<Coupon>();
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_coupon, coupons.size());
            for (int i = 0; i < coupons.size(); i++) {
                Coupon coupon = coupons.get(i);
                //用于已送数+1
                coupon.setSendCount(coupon.getSendCount() + 1);
                couponUpdate.add(coupon);

                //用于插入数据
                UserCoupon uc = new UserCoupon();
                uc.setId(ids.get(i));
                uc.setStatus(UserCouponStatus.VALID);
                uc.settUserFId(userId);
                uc.settEbuyOrderFId(orderId);
                uc.setGetType(coupon.getGoalType() == 67 ? 2 : 1);
                uc.settCouponFId(coupon.getId());
                uc.setUseEndDate(coupon.getUseEndDateType() == 1 ? coupon.getUseEndDate() :
                        DateUtil.formatDay.get().format(DateUtil.getNextDate(new Date(), Calendar.DAY_OF_YEAR, coupon.getUseDateNumber())));
                uc.setSys0AddTime(DateUtils.formatTime(new Date()));
                userCouponInsert.add(uc);
            }
            //送用户券并把消费券已送+1
            if (userCouponInsert.size() > 0) {
                userCouponBaseDao.insertUserCouponBatch(userCouponInsert);
                addRedPiont(userId, orderId);
            }
            if (couponUpdate.size() > 0) {
                couponBaseDao.updateCouponBatch(couponUpdate);
            }
        } catch (Exception e) {
            logger.info("消费券送券有问题：" + e.getMessage());
        }

    }

    private void addRedPiont(BigInteger userId, BigInteger sourceId) {

        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 1);
        String nextDay = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

        RedpointContent redpointContent = new RedpointContent();
        redpointContent.setUserId(userId);
        redpointContent.setUserType(1);
        redpointContent.setModelCode(RedpointConstant.RedpointContent_ModelCode.USER_HAS_NEW_COUPON);
        List<RedpointContent> contents = redpointContentBaseService.getRedpointContentByCondition(MapConverter.toMap(redpointContent));
        if (DataUtil.isEmpty(contents)) {
            redpointContent = new RedpointContent();
            redpointContent.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_redpoint_content));
            redpointContent.setUserId(userId);
            redpointContent.setUserType(1);
            redpointContent.setModelCode(RedpointConstant.RedpointContent_ModelCode.USER_HAS_NEW_COUPON);
            redpointContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);
            redpointContent.setExpireTime(nextDay);
            redpointContent.setLastModifyTime(now);
            redpointContentBaseService.insertRedpointContent(redpointContent);
        } else {
            redpointContent = contents.get(0);
            redpointContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);
            redpointContent.setExpireTime(nextDay);
            redpointContent.setLastModifyTime(now);
            redpointContentBaseService.updateRedpointContent(redpointContent);
        }
        if (sourceId == null) {
            return;
        }
        RedpointContentSource source = new RedpointContentSource();
        source.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_redpoint_content_source));
        source.setSourceType(RedpointDict.Redpoint_Content_SourceType.Coupon);
        source.setSourceId(sourceId);
        source.settRedpointContentFId(redpointContent.getId());
        source.setLastModifyTime(now);
        source.setOperationType("INSERT");
        redpointContentSourceBaseService.insertRedpointContentSource(source);

    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public ICouponDao getCouponDao() {
        return couponDao;
    }

    public void setCouponDao(ICouponDao couponDao) {
        this.couponDao = couponDao;
    }

    public IEbuyOrderBaseDao getEbuyOrderBaseDao() {
        return ebuyOrderBaseDao;
    }

    public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
        this.ebuyOrderBaseDao = ebuyOrderBaseDao;
    }

    public IUserCouponBaseDao getUserCouponBaseDao() {
        return userCouponBaseDao;
    }

    public void setUserCouponBaseDao(IUserCouponBaseDao userCouponBaseDao) {
        this.userCouponBaseDao = userCouponBaseDao;
    }

    public ICouponBaseDao getCouponBaseDao() {
        return couponBaseDao;
    }

    public void setCouponBaseDao(ICouponBaseDao couponBaseDao) {
        this.couponBaseDao = couponBaseDao;
    }

    public IUuidManager getUuidManager() {
        return uuidManager;
    }

    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    public void setRedpointContentSourceBaseService(IRedpointContentSourceBaseService redpointContentSourceBaseService) {
        this.redpointContentSourceBaseService = redpointContentSourceBaseService;
    }

    public void setRedpointContentBaseService(IRedpointContentBaseService redpointContentBaseService) {
        this.redpointContentBaseService = redpointContentBaseService;
    }

}
