package com.cnfantasia.wl.wechat.web;

import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: ThemeAdvController.
 * @Date: 2017-07-13 14:28
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/themeAdv")
public class ThemeAdvController extends BaseController {
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IHttpClient simpleHttpClient;

    /**
     * 进入到家/超市的运营活动商品列表页面
     * @return
     */
    @RequestMapping("/productList.html")
    public String themeAdvPage() {
        return "/themeAdv/productList";
    }

    /**
     * 周年庆活动2017, 是一系列的主题商品推广.
     * @return
     */
    @RequestMapping(value = "/anniversary.html", method = RequestMethod.GET)
    public String anniversaryActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String subChannelId = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		String version = HeaderManager.getVersion();
		String sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		logger.debug("ThemeAdvController.do-sessionKey:" + sessionKey + ", version:" + version + ",subChannelId:" + subChannelId + ", sessionId:" +  request.getSession().getId());
		if(subChannelId == null || version == null) {
			subChannelId = (String) request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
			version = (String) request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
		} else {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, version);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, subChannelId);
		}
		
		if (!"1".equals(subChannelId) && !"2".equals(subChannelId)) {
			String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + "themeAdv/anniversary.html" + "?" + request.getQueryString();
			currentUrl = currentUrl.replace("code", "codeTest"); //由于二次转发，code参数会造成不登录，后台报异常。快速这样先解决。
			Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

			if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
				request.getSession().setAttribute("isLogin", true);
				String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
				return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
			}
			
			logger.debug("ThemeAdvController.do-weixin");
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		}
		
		if(sessionKey != null) {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, version);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, sessionKey);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, subChannelId);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID, HeaderManager.getGbId());
		} else {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID, HeaderManager.getGbId());
			request.getSession().removeAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		}
    	
        return "/anniversary/anniversary2017";
    }
    
    /**
     * 周年庆活动2017, 是一系列的主题商品推广.
     * 由于到家广告栏跳转不支持链接后面带参数，会造成拿不到参数。所以增加一个链接，给前端H5判断是到家分会场
     * @return
     */
    @RequestMapping(value = "/anniversary2.html", method = RequestMethod.GET)
    public String anniversaryActivity2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String subChannelId = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		String version = HeaderManager.getVersion();
		String sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		logger.debug("ThemeAdvController.do-sessionKey:" + sessionKey + ", version:" + version + ",subChannelId:" + subChannelId + ", sessionId:" +  request.getSession().getId());
		if(subChannelId == null || version == null) {
			subChannelId = (String) request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
			version = (String) request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
		} else {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, version);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, subChannelId);
		}
		
		if (!"1".equals(subChannelId) && !"2".equals(subChannelId)) {
			String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + "themeAdv/anniversary2.html" + "?" + request.getQueryString();
			currentUrl = currentUrl.replace("code", "codeTest"); //由于二次转发，code参数会造成不登录，后台报异常。快速这样先解决。
			Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

			if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
				request.getSession().setAttribute("isLogin", true);
				String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
				return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
			}
			
			logger.debug("ThemeAdvController.do-weixin");
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		}
		
		if(sessionKey != null) {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, version);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, sessionKey);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, subChannelId);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID, HeaderManager.getGbId());
		} else {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID, HeaderManager.getGbId());
			request.getSession().removeAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		}
		request.setAttribute("servertpart", 1);
        return "/anniversary/anniversary2017";
    }
}
