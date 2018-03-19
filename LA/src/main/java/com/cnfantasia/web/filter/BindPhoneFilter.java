package com.cnfantasia.web.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.wl.wechat.web.LoginHelper;

/**
 * 微信绑定手机Filter <br>
 * 
 * 除了3个URL（1商品详情，2商城首页，3拼购详情）不拦截，其它的都要拦截
 * @author wenfq 2016-04-15
 * 
 */
public class BindPhoneFilter implements Filter {
	
	/**
	 * 被拦截前的请求Path
	 */
	public static final String LAST_REQUEST_SERVLET_PATH = "lastRequestServletPath";
	
	/** 需要排除（不拦截）的URL的正则表达式 */
    private Pattern excepUrlPattern;
    
    /** 绑定手机的URL */
    private String forwardBindPhoneUrl;
    
	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		 String excepUrlRegex = paramFilterConfig.getInitParameter("excepUrlRegex");
		 if(!StringUtils.isBlank(excepUrlRegex)){
			 excepUrlPattern = Pattern.compile(excepUrlRegex);
		 }
		 String forwardBindPhoneUrl = paramFilterConfig.getInitParameter("forwardBindPhoneUrl");
		 if(!StringUtils.isEmpty(forwardBindPhoneUrl)){
			 this.forwardBindPhoneUrl = forwardBindPhoneUrl;
		 }
	}

//  * 请求 http://127.0.0.1:8080/webApp/home.jsp?&a=1&b=2 时
//  * request.getRequestURL()： http://127.0.0.1:8080/webApp/home.jsp
// * request.getContextPath()： /webApp 
// * request.getServletPath()：/home.jsp
// * request.getRequestURI()： /webApp/home.jsp
// * request.getQueryString()：a=1&b=2
	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain) throws IOException,
			ServletException {
		
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;
		String servletPath = request.getServletPath();
		
		if(excepUrlPattern != null && excepUrlPattern.matcher(servletPath).matches()) {
			paramFilterChain.doFilter(request, response);
			return;
		}
		
		
		String queryString = request.getQueryString();
		
		//存储用户被拦截前的URL
		Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
		
		String contextPath = request.getContextPath();
		if(regist3rdResponse == null || regist3rdResponse.getUserId() == null){//未登录
			request.getSession().setAttribute(LAST_REQUEST_SERVLET_PATH, StringUtils.isEmpty(queryString) ? servletPath : servletPath + "?" + queryString);
			request.getRequestDispatcher("/login/regist3rd.do").forward(request, response); //在跳到绑定界面前进行登录操作
		} else if (StringUtils.isEmpty(regist3rdResponse.getMobile()) && ( regist3rdResponse.getPhoneBindState() == null
				|| regist3rdResponse.getPhoneBindState() == 0)) {//登录，未绑定手机
			request.getSession().setAttribute(LAST_REQUEST_SERVLET_PATH, StringUtils.isEmpty(queryString) ? servletPath : servletPath + "?" + queryString);
			//response.sendRedirect(contextPath + StringUtils.defaultIfEmpty(forwardBindPhoneUrl, "/"));
			request.getRequestDispatcher(forwardBindPhoneUrl).forward(request, response); //在跳到绑定界面前进行登录操作
		} else {//登录，已绑过手机
			/*String url = (String) request.getSession().getAttribute(LAST_REQUEST_SERVLET_PATH);
			if(url != null) {
				request.getSession().removeAttribute(LAST_REQUEST_SERVLET_PATH);
				response.sendRedirect(url);
				return;
			}*/
			paramFilterChain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
