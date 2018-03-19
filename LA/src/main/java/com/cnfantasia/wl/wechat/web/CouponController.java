package com.cnfantasia.wl.wechat.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.*;

/**
 * 优惠券
 * 
 * @author wenfq 2015-04-24
 * 
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

	@Resource
	private IHttpClient simpleHttpClient;

	/**
	 * 列出所有可用优惠券
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listCoupon.do")
	public ModelAndView addAddressInfo(HttpServletRequest request) {
		String totalAmount = request.getParameter("totalAmount");
		String ext_couponCombiInfo = request.getParameter("ext_couponCombiInfo");
		
		request.setAttribute("totalAmount", JSON.parseObject(totalAmount));
		request.setAttribute("ext_couponCombiInfo", JSON.parseObject(ext_couponCombiInfo));
		
		return new ModelAndView("/ebuy/prizeList");
	}

	@RequestMapping(value = "/couponList.do")
	public String jumptoCouponList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
		String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + "coupon/couponList.do" + "?" + request.getQueryString();
		if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
			request.getSession().setAttribute("isLogin", true);
			String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
			return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
		}

		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, user);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/coupon/couponList.json", new HashMap<String, Object>(), LoginHelper.prepareReqHeader(request));
		List coupons = (List)((JSONObject)jsonResponse.getDataValue()).get("couponList");
		request.setAttribute("couponList", coupons);
		return "/coupon/couponList";
	}

}
