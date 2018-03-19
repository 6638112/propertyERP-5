package com.cnfantasia.server.api.pub;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;

public class UpdateFilter implements Filter {
	
	/** 需要排除（不拦截）的URL的正则表达式 */
//    private final String URL = "getGlobalValue,checkUpd";
    
    private final String[] URLS= {"getGlobalValue","checkUpd","ebuyNew"};
    
     
    /** 检查不通过时，转发的URL */

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
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
		String requestURL = request.getRequestURL().toString();
		
		for(String url : URLS) {
			if(requestURL.contains(url)) {
				paramFilterChain.doFilter(request, response);
				return;
			}
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		String status = CommConstants.ResponseStatus.BUSINESS_FAILED;// status状态码
		String errCode = CommConstants.DEFAULT_SYS_ERRCODE;// errCode
		String errorMsg = "版本太旧已经停用，请升级版本后才能正常使用！";// errMessage

		{
			jsonResponse.setStatus(status);
			jsonResponse.setErrcode(errCode);
			jsonResponse.setMessage(errorMsg);
		}
		
		paramServletResponse.setContentType("text/json");
		paramServletResponse.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(jsonResponse));
		out.flush();
		out.close();
		return;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
}
