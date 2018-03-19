package com.cnfantasia.jfq.scan.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.coupon.dao.ICouponDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.business.wx.message.WxArticle;
import com.cnfantasia.server.business.wx.message.util.WxMessageUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import com.cnfantasia.server.domainbase.userCoupon.service.IUserCouponBaseService;
import com.cnfantasia.wl.wechat.util.AccessTokenGetter;

/**
 * @ClassName: ScanService
 * @Date: 2017-08-10 17:03
 * @Auther: yanghua
 * @Description:(临停车扫码送券)
 */
public class ScanService {
    private static Logger logger = LoggerFactory.getLogger(ScanService.class);
    @Resource
    private ISysParamManager sysParamManager;
    @Resource
    private ICouponDao couponDao;
    @Resource
    private IUserCouponBaseService userCouponBaseService;
    @Resource
    private IUuidManager uuidManager;
    /**
     * 扫码送临停车券
     */
    public Coupon dealCarCouponScan(String openId, BigInteger userId, BigInteger storeId) {
        Coupon coupon = couponDao.selectAvailableCarCoupon(storeId);
        logger.debug("该用户coupon=" + coupon);
        if (null != coupon) {
            //更新券的数量
            int i = couponDao.updateCouponNumsByStore(coupon.getId(), storeId);
            if(i == 1) {//券的数量更新成功在进行发送
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_coupon));
                userCoupon.settUserFId(userId);
                userCoupon.settCouponFId(coupon.getId());
                userCoupon.settEbuyOrderFId(new BigInteger("-1"));
                userCoupon.setUseEndDate(DateUtils.getTodayStr());
                userCoupon.setStatus(1);
                userCoupon.setSys0DelState(0);
                userCouponBaseService.insertUserCoupon(userCoupon);

                // 3、推送
                WxArticle article = new WxArticle();
                article.setTitle(sysParamManager.getSysParaValue(SysParamKey.LA_TMP_CAR_PUSH_Title_Scan));
                String desc = sysParamManager.getSysParaValue(SysParamKey.LA_TMP_CAR_PUSH_DESC_Scan);
                article.setDescription(desc);
                String url = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "temporaryParkingFee/goFeePage.html?from=" + AccessDict.Code.xiaomifeng;
                article.setUrl(url);
                article.setPicurl(sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "images/car_fee_coupon_send.png");
                List<WxArticle> articles = new ArrayList<WxArticle>(1);
                articles.add(article);
                WxMessageUtil.sendArticleMessage(articles, openId, AccessTokenGetter.getAccess_token());
                logger.debug("dealCarCoupon===>push over...");
            }
            return coupon;
        }
        return null;
    }
}
