package com.cnfantasia.server.common.json;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cnfantasia.server.common.CommConstants;

/**
 * 类名：JsonResponse
 * 
 */
public class JsonResponse {
//	private Log logger = LogFactory.getLog(getClass());
	// 状态信息
	private String status;
	// 提示内容
	private String message;
	// 业务结果数据
	private Object dataValue;
	private String errcode;
	
	private String transNo;
	
	private String jsonCallback;
	
	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	/**
	 * 构造方法
	 */
	public JsonResponse() {
		status = CommConstants.ResponseStatus.SUCCESS;
//		message = "success";
		dataValue = new LinkedHashMap<String, Object>();
		
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(servletRequestAttributes != null) {
			HttpServletRequest request =servletRequestAttributes.getRequest();
			if(request != null) {
				String callBack = request.getParameter("callBack");
				if(callBack == null) {
					callBack = request.getParameter("jsonpCallback");
				}
				setJsonCallback(callBack);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void putData(String key, Object value) {
		((Map<String, Object>)dataValue).put(key, value);
	}
	@SuppressWarnings("unchecked")
	public void putDataAll(Map<String,Object> map) {
		((Map<String, Object>)dataValue).putAll(map);
	}
	// @Override
	// public String toString() {
	// return new StringBuilder()
	// .append("{")
	// .append("status:").append(status).append(",")
	// .append("message:").append(message).append(",")
	// .append("dataValue: ").append(dataValue)
	// .append("}")
	// .toString();
	// }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
////	/**
////	 * 通过错误码把录入错误内容
////	 * @param messageKey
////	 */
//	public void setMessageKey(String messageKey) {
//		setMessageKey(messageKey, null);
//	}
//	/**
//	 * 通过错误码把录入错误内容
//	 * @param messageKey
//	 */
//	private void setMessageKey(String messageKey,Object[] paramArrayOfObject) {
//		if(messageKey==null){
//			return;
//		}
////		if(this.message==null||"null".equals(this.message)){
//			String message =MessageSourceUtil.getMessage(messageKey,paramArrayOfObject);
//			if(StringUtils.isEmpty(message)){
//				throw new RuntimeException("Undefined message for messageKey:"+messageKey);
//			}
//			this.message = message;
////		}
//	}
	
	
	public Object getDataValue() {
		return dataValue;
	}
	
	public void setDataValue(Object dataValue) { 
		 this.dataValue = dataValue;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getJsonCallback() {
		return jsonCallback;
	}

	public void setJsonCallback(String jsonCallback) {
		this.jsonCallback = jsonCallback;
	}

	
}
