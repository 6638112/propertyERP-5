package com.cnfantasia.server.ms.pub.interceptor;

import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.ms.pub.annotation.CheckLogin;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.web.LoginHelper;

import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName: CheckLoginInterceptor.
 * @Date: 2017-09-01 13:43
 * @Auther: kangduo
 * @Description: ()
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

    private Log logger = LogFactory.getLog(getClass());
    private IHttpClient simpleHttpClient;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            Method method = handlerMethod.getMethod();
            CheckLogin checkLogin = method.getAnnotation(CheckLogin.class);
            if (checkLogin != null) {
                String subChannelId = httpServletRequest.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
                logger.debug("checkLoginInterceptor:preHandle:subChannelId=" + subChannelId);
                if (!"1".equals(subChannelId) && !"2".equals(subChannelId)) {
                    String servletPath = httpServletRequest.getServletPath().replaceFirst("/", "");
                    String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + servletPath + "?" + httpServletRequest.getQueryString();
                    currentUrl = currentUrl.replace("code", "codeTest"); //由于二次转发，code参数会造成不登录，后台报异常。快速这样先解决。
                    Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("isLogin");
                    logger.debug("checkLoginInterceptor:preHandle:LoginHelper.isLoginUser=" + LoginHelper.isLoginUser(httpServletRequest));
                    logger.debug("checkLoginInterceptor:preHandle:code=" + httpServletRequest.getParameter("code"));
                    logger.debug("checkLoginInterceptor:preHandle:isLogin=" + isLogin);
                    if ((!LoginHelper.isLoginUser(httpServletRequest) && httpServletRequest.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
                        httpServletRequest.getSession().setAttribute("isLogin", true);
                        String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
                        httpServletResponse.sendRedirect(wechatRedirectUrl);
                        return false;
                    }

                    SnsUserinfo user = LoginHelper.getSnsUserInfo(httpServletRequest, httpServletResponse);
                    LoginHelper.loginAPI(simpleHttpClient, httpServletRequest, user);
                }
            }
        }
        return super.preHandle(httpServletRequest, httpServletResponse, o);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        super.postHandle(httpServletRequest, httpServletResponse, o, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        super.afterCompletion(httpServletRequest, httpServletResponse, o, e);
    }

    public void setSimpleHttpClient(IHttpClient simpleHttpClient) {
        this.simpleHttpClient = simpleHttpClient;
    }
}
