package com.cnfantasia.server.ms.pub.filter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;

public class SecurityServletFilter extends HttpServlet implements Filter {

	private Log log = LogFactory.getLog(getClass());

	/** 需要排除（不拦截）的URL的正则表达式 */
	private Pattern excepUrlRegexPattern;

	/** 检查不通过时，转发的URL */
	private String forwardUrl;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excepUrlRegexPattern = Pattern.compile(filterConfig.getInitParameter("excepUrlRegex"));
		forwardUrl = filterConfig.getInitParameter("forwardUrl");
	}

	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;
		String servletPath = request.getServletPath();

		if (servletPath.equals(forwardUrl) || excepUrlRegexPattern.matcher(servletPath).matches()) {
			paramFilterChain.doFilter(request, response);
			return;
		}

		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session == null || session.getAttribute("currUser") == null) {
			((HttpServletResponse) response).sendRedirect(request.getContextPath() + "/security/loginPage.html");
		} else {

			List<OmsPermiFunction> permiFunctionList = (List<OmsPermiFunction>) session.getAttribute("permiFunctionList");
			boolean hasPermi = false;
			for (OmsPermiFunction omsPermiFunction : permiFunctionList) {
				if (omsPermiFunction.getAction().equals(servletPath)
						|| (omsPermiFunction.getParam() != null && omsPermiFunction.getParam().contains(servletPath))) {
					hasPermi = true;
					break;
				}
			}

//			hasPermi = true;//实在受不了这个权限，直接注释掉。
			if (hasPermi) {
				paramFilterChain.doFilter(request, response);
			} else {
				log.info("no permission ------ " + servletPath);
				((HttpServletResponse) response).sendRedirect(request.getContextPath() + "/security/accessNoPermission.html");
				//throw new RuntimeException("has not permisstion: ------ " + servletPath);
			}
		}

	}

	public static void main(String[] args) {
		String excpUrlPattern = "/security/[\\w]*.html|/login/[\\w]*.html";
		String forewardUrl = "/login/doLogin.html";

		Pattern p = Pattern.compile(excpUrlPattern);
		System.out.println(p.matcher(forewardUrl).matches());

	}

}
