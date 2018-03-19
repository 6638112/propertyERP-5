package com.cnfantasia.server.ms.pub.validation;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cnfantasia.server.common.validation.express.ExpressionExecutor;

/**
 *	校验拦截器
 */
public class ValidationInterceptor extends HandlerInterceptorAdapter {
	
	// 日志记录器
	private static final Log logger = LogFactory.getLog(ValidationInterceptor.class);
	
	// 表达式执行器
	private ExpressionExecutor executor;
	public static String getMylUrl(String srcUri,String appName){
    if(appName!=null&&appName.startsWith("/")){
      appName=appName.substring(1);
    }
    if (srcUri.indexOf(appName) != -1) {
      srcUri = srcUri.substring(appName.length() + 1);//=2去掉‘/’线
    }
    return srcUri;
  }
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = getMylUrl(request.getRequestURI(), request.getContextPath());
		if (ValidationContext.checkValidate(url)) {
			// 具有可校验特性
			Map<String, Object> regulations = ValidationContext.getRegulations(url);
			if (null != regulations && ! regulations.isEmpty()) {
				// 定义了校验规则
				
				for (Entry<String, Object> entry : regulations.entrySet()) {
					// 规章表达式
					Object expression = entry.getValue();
					
					// 取得字段
					String field = entry.getKey();
					
					Object value = getFieldValue(request, field);
					
					if (logger.isDebugEnabled()) {
						logger.debug("before validate, the parameter is : " + field + "=" + value);
					}
					
					// 交给执行器进行处理
					value = executor.execute(field, value, expression);
					
					if (logger.isDebugEnabled()) {
						logger.debug("after validate, the parameter is : " + field + "=" + value);
					}
					
					// 将解析后的值放入request
					request.setAttribute(field, value);
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
	protected Object getFieldValue(HttpServletRequest request, String field) {
		Object value = request.getAttribute(field);
		if (null == value) {
			String[] values = request.getParameterValues(field);
			
			value = values;
			
			if (null != values && values.length == 1) {
				value = values[0];
			}
		}
		
		return value;
	}

	/**
	 * 
	 */
	public void setExecutor(ExpressionExecutor executor) {
		this.executor = executor;
	}
}