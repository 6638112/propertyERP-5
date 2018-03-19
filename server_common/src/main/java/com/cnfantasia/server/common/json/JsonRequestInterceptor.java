package com.cnfantasia.server.common.json;

import java.io.BufferedReader;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.exception.SystemException;

public class JsonRequestInterceptor extends HandlerInterceptorAdapter {

	// 日志记录器
	private Log logger = LogFactory.getLog(JsonRequestInterceptor.class);

	// 需要检查的类型
	private String contentType = "application/json";

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// System.out.println(request.getAttribute("num"));
		// System.out.println(request.getParameter("num"));
		// Enumeration<String> headNames=request.getHeaderNames();
		// while(headNames.hasMoreElements()){
		// String name=headNames.nextElement();
		// System.out.println(name+" "+request.getHeader(name));
		// }
		// Enumeration<String> reqNames=request.getAttributeNames();
		// while(reqNames.hasMoreElements()){
		// String name=reqNames.nextElement();
		// System.out.println(name+" "+request.getAttribute(name));
		// }

		String contentType = request.getHeader("content-type");
		if (logger.isDebugEnabled()) {
			logger.debug("content-type = " + contentType + ", " + request.getContentType());
		}

		if (null != contentType && contentType.toLowerCase().startsWith(this.contentType)) {
			// 解析上送过来的json数据
			Object json = JSON.parse(readJson(request));

			if (!(json instanceof Map)) {
				throw new SystemException("system.error.json.partten", new Object[] { json });
			}

			// 将数据解析到request
			applyRequest(request, (Map<String, Object>) json);
		}

		return super.preHandle(request, response, handler);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void applyRequest(HttpServletRequest request, Map<String, Object> datas) {
		// 头
		request.setAttribute("header", datas.get("header"));

		Map<String, Object> requestMap = (Map<String, Object>) datas.get("request");

		if (null != requestMap) {

			String transaction = (String) requestMap.get("transaction");
			if (null != transaction) {
				// 请求交易
				request.setAttribute("transaction", transaction);
			}

			// id
			String id = (String) requestMap.get("id");
			if (null != id) {
				request.setAttribute("id", id);
			}

			Map<String, Object> params = (Map<String, Object>) requestMap.get("params");
			if (null != params) {
				for (Entry<String, Object> entry : params.entrySet()) {
					request.setAttribute(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	/**
	 * 
	 */
	protected String readJson(HttpServletRequest request) throws Exception {
		StringBuilder sb;
		try {
			BufferedReader br = request.getReader();

			sb = new StringBuilder();

			String line = null;
			while (true) {
				line = br.readLine();

				if (null == line) {
					break;
				}

				sb.append(line);
			}
		} catch (Exception e) {
			throw new SystemException("system.error.read.json.error");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("request.json is: " + sb.toString());
		}

		return sb.toString();
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}