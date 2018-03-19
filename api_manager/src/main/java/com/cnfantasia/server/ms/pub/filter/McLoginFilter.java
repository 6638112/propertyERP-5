package com.cnfantasia.server.ms.pub.filter;

import java.io.IOException;
import java.net.URLEncoder;
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

import com.cnfantasia.server.ms.ebuyMerchant.entity.McLogonUser;
import com.cnfantasia.server.ms.pub.session.SessionManager;

public class McLoginFilter implements Filter {
	
	/** 需要排除（不拦截）的URL的正则表达式 */
    private Pattern excepUrlPattern;
     
    /** 检查不通过时，转发的URL */
    private String forwardUrl;

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		 String excepUrlRegex = paramFilterConfig.getInitParameter("excepUrlRegex");
		 forwardUrl = paramFilterConfig.getInitParameter("forwardUrl");
		 if(!StringUtils.isBlank(excepUrlRegex)){
			 excepUrlPattern = Pattern.compile(excepUrlRegex);
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
		
		if(servletPath.equals(forwardUrl) || (excepUrlPattern != null && excepUrlPattern.matcher(servletPath).matches())) {
			paramFilterChain.doFilter(request, response);
			return;
		}
		
		
		McLogonUser mcLogonUser = SessionManager.getMcLogonUser(request);
		if (mcLogonUser == null) {
//			StringBuffer parameters = new StringBuffer();
//			Set<String> keys = (Set<String>)request.getParameterMap().keySet();
//			if (keys.size() > 0) {
//				Iterator<String> it = keys.iterator();
//				while (it.hasNext()) {
//					String name = it.next();
//					String[] valueArray = (String[]) request.getParameterMap().get(name);
//					for (int i = 0; i < valueArray.length; i++) {
//						parameters.append(name + "=" + valueArray[i]);
//						if (i != valueArray.length - 1)
//							parameters.append("&");
//					}
//					if (it.hasNext())
//						parameters.append("&");
//				}
//			}
			
			String redirect = servletPath + "?" + StringUtils.defaultString(request.getQueryString());
			String contextPath = request.getContextPath();
			request.getSession().setAttribute("afterLoginUrl", contextPath + redirect);
//			response.sendRedirect(contextPath + StringUtils.defaultIfEmpty(forwardUrl, "/") + "?redirect=" + URLEncoder.encode(redirect, "UTF-8"));
			response.sendRedirect(contextPath + StringUtils.defaultIfEmpty(forwardUrl, "/"));
			return;
		} else {
//			if(mcLogonUser.getEbuyMerchantBean() == null) {
//				String contextPath = request.getContextPath();
//				response.sendRedirect(contextPath + StringUtils.defaultIfEmpty(forwardUrl, "/"));
//				return;
//			}
			String url = (String) request.getSession().getAttribute("afterLoginUrl");
			if(url != null) {
				request.getSession().removeAttribute("afterLoginUrl");
				response.sendRedirect(url);
				return;
			}
			paramFilterChain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	
//	protected void loginInternal(FilterChain chain, HttpServletRequest request, HttpServletResponse response)
//	throws IOException, ServletException {
//		
//		McLogonUser mcLogonUser = SessionManager.getMcLogonUser(request);
//		if (mcLogonUser == null) {
////			StringBuffer parameters = new StringBuffer();
////			Set<String> keys = (Set<String>)request.getParameterMap().keySet();
////			if (keys.size() > 0) {
////				Iterator<String> it = keys.iterator();
////				while (it.hasNext()) {
////					String name = it.next();
////					String[] valueArray = (String[]) request.getParameterMap().get(name);
////					for (int i = 0; i < valueArray.length; i++) {
////						parameters.append(name + "=" + valueArray[i]);
////						if (i != valueArray.length - 1)
////							parameters.append("&");
////					}
////					if (it.hasNext())
////						parameters.append("&");
////				}
////			}
//			
//			String servletPath = request.getServletPath();
//			String redirect = servletPath + "?" + StringUtils.defaultString(request.getQueryString());
//			String contextPath = request.getContextPath();
//			response.sendRedirect(contextPath + StringUtils.defaultIfEmpty(forwardUrl, "/") + "?redirect=" + URLEncoder.encode(redirect, "UTF-8"));
//			return;
//		} else {
//			chain.doFilter(request, response);
//		}
//	}

}
