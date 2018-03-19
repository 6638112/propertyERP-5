package com.cnfantasia.server.ms.bankCollection.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class JspToHtmlUtil {
	
	/**
	 * 根据JSP所在的路径获取JSP的内容<br/>
	 * 在不跳转下访问目标jsp。就是利用RequestDispatcher.include(ServletRequest request
	 * 
	 * @param jspPath
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static final String getJspOutput(String jspPath, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WrapperResponse wrapperResponse = new WrapperResponse(response);
		request.getRequestDispatcher(jspPath).include(request, wrapperResponse);
		return wrapperResponse.getContent();
	}
}
