package com.cnfantasia.wl.wechat.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.jfq.scan.service.ScanService;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.util.UserInfoGetter;
import com.cnfantasia.wl.wechat.util.WeixinUtils;
import com.cnfantasia.wl.wechat.web.LoginHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @ClassName: ScanController
 * @Date: 2017-08-10 14:06
 * @Auther: yanghua
 * @Description:(二维码扫描)
 */
@Controller
@RequestMapping("/scan")
public class ScanController extends BaseController {
    private final Log logger = LogFactory.getLog(getClass());
    private final String CAR_USER_SESSION_KEY = "carUserKey";

    @Resource
    private ScanService scanService;
    @Resource
    private IHttpClient simpleHttpClient;

    /**
     * 临停车商户扫码
     */
    @RequestMapping("/carScan.json")
    public String carScan(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
            String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + "scan/carScan.json" + "?" + request.getQueryString();
            String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
            request.getSession().setAttribute("isLogin", true);
            
            return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
        }
        
        String storeId = request.getParameter("storeId");//店铺ID
        String ua = request.getHeader("user-agent").toLowerCase();
        logger.info("ScanController-->carScan:"+ua);
        if (ua.indexOf("micromessenger") > 0) {// 通过微信浏览器进入
            int subscribe = 0;
            {
                SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
                if (user.getOpenid() != null) {
                    subscribe = JSON.parseObject(UserInfoGetter.getUserInfo(user.getOpenid())).getInteger("subscribe");
                    request.getSession().setAttribute("subscribe", subscribe);
                } else {
                    request.getSession().setAttribute("subscribe", 0);
                }
            }
            SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
            LoginHelper.loginAPI(simpleHttpClient, request, user);
            request.getSession().setAttribute(CAR_USER_SESSION_KEY, user);
            Regist3rdResponse regist3rdResponse = (Regist3rdResponse) request.getSession().getAttribute("regist3rdResponse");
            BigInteger userId = new BigInteger(regist3rdResponse.getUserId());
            if (user.getOpenid() != null && !DataUtil.isEmpty(storeId)) {
                RedisCacheHandler.set(RedisCachePrefix.JFQ_Store_Car_Scan_OpenId + user.getOpenid(), storeId);
                RedisCacheHandler.set(RedisCachePrefix.JFQ_Store_Car_Scan_UserId + user.getOpenid(), regist3rdResponse.getUserId());
            }
            if(subscribe == 0) {
                return "redirect:https://resource.jiefangqu.com/docs/followjfq/index.htm";
            } else {
                Coupon coupon = scanService.dealCarCouponScan(user.getOpenid(), userId, new BigInteger(storeId));
                BigInteger couponId = coupon != null ? coupon.getId() : null;
                return "redirect:/temporaryParkingFee/goFeePage.html?from=xiaomifeng&couponId="+couponId;
            }
        } else {
            return "redirect:/temporaryParkingFee/parkingTip.html";
        }
    }

    @RequestMapping("/getCouponSuccess.html")
    public ModelAndView parkingTip(){
        return new ModelAndView("/temporaryParkingFee/getCouponSuccess");
    }
}
