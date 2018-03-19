package com.cnfantasia.wl.wechat.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.jfq.scan.service.ScanService;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.coupon.dao.ICouponDao;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.business.wx.WxCommUtil;
import com.cnfantasia.server.business.wx.message.WxArticle;
import com.cnfantasia.server.business.wx.message.util.WxMessageUtil;
import com.cnfantasia.server.business.wx.pay.util.XMLUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.coupon.service.ICouponBaseService;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProduct.service.IDredgeProductBaseService;
import com.cnfantasia.server.domainbase.loginNo.service.ILoginNoBaseService;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import com.cnfantasia.server.domainbase.userCoupon.service.IUserCouponBaseService;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import com.cnfantasia.wl.wechat.util.AccessTokenGetter;

/**
 * @ClassName: SubscribeController.
 * @Date: 2017-06-22 13:47
 * @Auther: kangduo
 * @Description: (公众号关注回调)
 */
@Controller
@RequestMapping("/subscribe")
public class SubscribeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SubscribeController.class);

    @Resource
    private IDredgeProductBaseService dredgeProductBaseService;
    @Resource
    private ISysParamManager sysParamManager;
    @Resource
    private ILoginNoBaseService loginNoBaseService;
    @Resource
    private ICouponBaseService couponBaseService;
    @Resource
    private ICouponDao couponDao;
    @Resource
    private IUserCouponBaseService userCouponBaseService;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private ScanService scanService;

    /**
     * 公众号被关注后回调.
     * @param request request
     * @return
     */
    @RequestMapping(value = "/callback.json", method = RequestMethod.POST)
    @ResponseBody
    public String subscribeCallBack(HttpServletRequest request) throws IOException, JDOMException {
        String resStr = WxCommUtil.parseWeixinCallback(request);
        logger.info("公众号事件回调返回结果为：" + resStr);
        SortedMap<String, String> resMap = XMLUtil.parseXML(resStr);
        if (resMap != null && "event".equals(resMap.get("MsgType")) && "subscribe".equals(resMap.get("Event"))) {
            String openId = resMap.get("FromUserName");
            String dredgeProductId = RedisCacheHandler.get(RedisCachePrefix.Dredge_Product_View_OpenId + openId);
            if (!StringUtils.isEmpty(dredgeProductId)) {
                DredgeProduct dredgeProduct = dredgeProductBaseService.getDredgeProductBySeqId(new BigInteger(dredgeProductId));
                WxArticle article = new WxArticle();
                article.setTitle(dredgeProduct.getShareFriendTitle());
                article.setDescription(dredgeProduct.getShareContent());
                String picUrl = sysParamManager.getImageServerUrl(SysParamKey.DredgePicBasePath) + sysParamManager.getSysParaValue(SysParamKey.DredgePicBasePath) + dredgeProduct.getSharePic();
                String url = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "dredge/productDetail.html?productId=" + dredgeProductId + "&jfqsource=weixin";
                article.setUrl(url);
                article.setPicurl(picUrl);
                List<WxArticle> articles = new ArrayList<WxArticle>(1);
                articles.add(article);
                WxMessageUtil.sendArticleMessage(articles, openId, AccessTokenGetter.getAccess_token());
            }

            String jfq_store_view_openId = RedisCacheHandler.get(RedisCachePrefix.JFQ_Store_View_OpenId + openId);
            if (!StringUtils.isBlank(jfq_store_view_openId)) {
                BigInteger userId = new BigInteger(jfq_store_view_openId);
                logger.info("jfq_store_view_openId: " + jfq_store_view_openId);
                WxArticle article = new WxArticle();
                article.setTitle(sysParamManager.getSysParaValue(SysParamKey.JFQ_Store_Share_Title));
                article.setDescription(sysParamManager.getSysParaValue(SysParamKey.JFQ_Store_Share_Content));
                article.setUrl(sysParamManager.getSysParaValue(SysParamKey.JFQ_Store_Visit_URL));
                article.setPicurl(sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "images/store_push.png");
                List<WxArticle> articles = new ArrayList<WxArticle>(1);
                articles.add(article);
                try {//延迟500ms再发，这样可以保证在关注回复消息之后用户再收到该图文消息
                    Thread.sleep(200);
                    WxMessageUtil.sendArticleMessage(articles, openId, AccessTokenGetter.getAccess_token());
                    Thread.sleep(200);
                    sendStoreCoupon(openId, userId);
                    Thread.sleep(200);
                    dealCarCoupon(openId, userId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String jfq_store_car_scan__openId = RedisCacheHandler.get(RedisCachePrefix.JFQ_Store_Car_Scan_OpenId + openId);
            if (!StringUtils.isBlank(jfq_store_car_scan__openId)) {
                BigInteger storeId = new BigInteger(jfq_store_car_scan__openId);
                BigInteger userId = new BigInteger(RedisCacheHandler.get(RedisCachePrefix.JFQ_Store_Car_Scan_UserId + openId));
                try {//延迟500ms再发，这样可以保证在关注回复消息之后用户再收到该图文消息
                    Thread.sleep(200);
                    scanService.dealCarCouponScan(openId, userId, storeId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

    @RequestMapping(value = "/callback.json", method = RequestMethod.GET)
    @ResponseBody
    public String responseWechat(HttpServletRequest request) {
        return request.getParameter("echostr");
    }

    /**
     * 送体验店的消费券.
     * @param openId 微信openId
     */
    private void sendStoreCoupon(String openId, BigInteger userId) {
        BigInteger couponId = new BigInteger("10134");
        Coupon coupon = couponBaseService.getCouponBySeqId(couponId);
        if (coupon == null || coupon.getSendEndDate().compareTo(DateUtils.getTodayStr()) < 0) {
            return;
        }
        if (userId == null) {
            return;
        }
        UserCoupon uc = new UserCoupon();
        uc.settCouponFId(couponId);
        uc.settUserFId(userId);
        int count = userCouponBaseService.getUserCouponCount(MapConverter.toMap(uc));
        if (count > 0) {
            return;
        }
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_coupon));
        userCoupon.settUserFId(userId);
        userCoupon.settCouponFId(coupon.getId());
        userCoupon.settEbuyOrderFId(new BigInteger("-1"));
        userCoupon.setUseEndDate(coupon.getUseEndDateType() == 1 ? coupon.getUseEndDate() :
                DateUtil.formatDay.get().format(DateUtil.getNextDate(new Date(), Calendar.DAY_OF_YEAR, coupon.getUseDateNumber())));
        userCoupon.setStatus(1);
        userCoupon.setSys0DelState(0);
        userCouponBaseService.insertUserCoupon(userCoupon);

        WxArticle article = new WxArticle();
        article.setTitle("体验店全场通用券");
        article.setDescription("购买解放区体验店商品，满99元减10");
        article.setUrl(sysParamManager.getSysParaValue(SysParamKey.JFQ_Store_Visit_URL));
        article.setPicurl(sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "images/jfq_store_coupon_send2.png");
        List<WxArticle> articles = new ArrayList<WxArticle>(1);
        articles.add(article);
        WxMessageUtil.sendArticleMessage(articles, openId, AccessTokenGetter.getAccess_token());
    }

    /**
     * 处理临停车券
     */
    private void dealCarCoupon(String openId, BigInteger userId){
        if(userId != null){// 判断是否来自小蜜蜂的二维码
            // 1、t_login_no：根据open_id查找userId
            int couponCount = couponDao.selectCarCouponCountWithUserId(userId);
            logger.debug("该用户couponCount=" + couponCount);
            if (couponCount == 0) {// 之前没有送过，才送券
                Coupon coupon = couponDao.selectAvailableCarCoupon();
                logger.debug("该用户coupon=" + coupon);
                if (null != coupon) {// 2、将券与userId绑定
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
                    article.setTitle(sysParamManager.getSysParaValue(SysParamKey.LA_TMP_CAR_PUSH_Title));
                    String desc = sysParamManager.getSysParaValue(SysParamKey.LA_TMP_CAR_PUSH_DESC);
                    article.setDescription(desc);
                    String url = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "temporaryParkingFee/goFeePage.html?from=" + AccessDict.Code.xiaomifeng;
                    article.setUrl(url);
                    article.setPicurl(sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "images/car_fee_coupon_send.png");
                    List<WxArticle> articles = new ArrayList<WxArticle>(1);
                    articles.add(article);
                    WxMessageUtil.sendArticleMessage(articles, openId, AccessTokenGetter.getAccess_token());
                    logger.debug("dealCarCoupon===>push over...");
                }
            }
        }
    }

}
