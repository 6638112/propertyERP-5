/**   
 * Filename:    SimpleHttpClient.java   
 * @version:    1.0  
 * Create at:   2014年6月5日 上午8:50:18   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月5日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.common.httpcllient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HttpClientUtil;
import com.cnfantasia.server.common.utils.HttpUtil;


/**
 * Filename: SimpleHttpClient.java
 * 
 * @version: 1.0.0 Create at: 2014年6月5日 上午8:50:18 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月5日 shiyl 1.0 1.0 Version
 */
public abstract class SimpleHttpClient implements IHttpClient{
	private Log logger = LogFactory.getLog(getClass());
	/**通讯异常*/ 
//	private static final String COMMUNICATE_ERR="9***";
	private Integer sendBufferSize;
	private String baseURL;
	private String encoding;
	private String method;
	private Map<String,String> headers;
	
	public void init() {
//		httpClient = new HttpClient();
//		HttpConnectionManagerParams params = httpClient.getHttpConnectionManager().getParams();
//		if (connectionTimeOut > 0) {
//			params.setConnectionTimeout(connectionTimeOut);
//		}
//		if (sendBufferSize > 0) {
//			params.setSendBufferSize(sendBufferSize);
//		}
//		if (null == method) {
//			method = MethodName.POST;
//		}
//		// 设置编码格式
//		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
	}

	@Override
	public JsonResponse doGet(String uri, Map<String, Object> params,List<Header> appendHeaderList){
		// 改调用HttpUtil工具类来post，这个类可以保证session一致性
		HttpUtil httpUtil = new HttpUtil();
		if (params != null) {
			for (String tmpStr : params.keySet()) {
				httpUtil.addParameter(tmpStr, params.get(tmpStr) == null ? null
						: params.get(tmpStr).toString());
			}
		}
		if (appendHeaderList != null) {
			for (int i = 0; i < appendHeaderList.size(); i++) {
				httpUtil.addHeader(appendHeaderList.get(i).getName(),
						appendHeaderList.get(i).getValue());
			}
		}

		JsonResponse message = new JsonResponse();
		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			if(servletRequestAttributes==null){return null;}
			HttpServletRequest request =servletRequestAttributes.getRequest();
			String retStr = httpUtil.get(baseURL+uri, 10000, "UTF-8", request);
			message = JSON.parseObject(retStr, JsonResponse.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("系统错误，请联系管理员！");
		}

		return message;
	}

	@Override
	public JsonResponse submitSimple(String uri, Map<String, Object> params,List<Header> appendHeaderList) {
		/*List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if(params!=null){
			for(String tmpStr:params.keySet()){
				formParams.add(new BasicNameValuePair(tmpStr, params.get(tmpStr)==null?null:params.get(tmpStr).toString()));
			}
		}
		List<Header> headerList = new ArrayList<Header>();
		if(headers!=null){
			for(String tmpStr:headers.keySet()){
				headerList.add(new BasicHeader(tmpStr, headers.get(tmpStr)));
			}
		}
		if(appendHeaderList!=null){
			headerList.addAll(appendHeaderList);
		}
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(servletRequestAttributes==null){return null;}
		HttpServletRequest request =servletRequestAttributes.getRequest(); 
		CookieStore cookieStore = (CookieStore) request.getSession().getAttribute("cookies");
		request.getCookies();

		JsonResponse jsonResponse=doPost(uri, formParams, headerList, cookieStore);
		
		 return jsonResponse;*/
		
		// 改调用HttpUtil工具类来post，这个类可以保证session一致性
		HttpUtil httpUtil = new HttpUtil();
		if (params != null) {
			for (String tmpStr : params.keySet()) {
				httpUtil.addParameter(tmpStr, params.get(tmpStr) == null ? null
						: params.get(tmpStr).toString());
			}
		}
		if (appendHeaderList != null) {
			for (int i = 0; i < appendHeaderList.size(); i++) {
				httpUtil.addHeader(appendHeaderList.get(i).getName(),
						appendHeaderList.get(i).getValue());
			}
		}
		
		JsonResponse message = new JsonResponse();
		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			if(servletRequestAttributes==null){return null;}
			HttpServletRequest request =servletRequestAttributes.getRequest(); 
			String retStr = httpUtil.post(baseURL+uri, 20000, "UTF-8", request);
			message = JSON.parseObject(retStr, JsonResponse.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("网络不给力，请稍后再试！");
		}
		
		return message;
	
	}
	@Override
	public JsonResponse submitSimple(String uri, Map<String, Object> params) {
		return submitSimple(uri, params, null);
	}
	@Override
	public JsonResponse doPost(String uri,HttpEntity entity, List<Header> headerList,
			CookieStore cookieStore){
		String postUrl = baseURL+uri;
		if(headerList==null){
			headerList = new ArrayList<Header>();
		}
		if (cookieStore == null) {
			cookieStore = new BasicCookieStore();
		}
		String res=null;
		try {
			res = HttpClientUtil.doPostString(postUrl, entity, headerList, cookieStore,encoding);
		} catch (Exception e) {
			logger.info("The client request:"+uri+",entity "+entity+",cause exception,msg is :"+e.getMessage(),e);
//			try {
//				logger.info("The client request:"+uri+",entity "+JSON.toJSONString(entity)+",cause exception,msg is :"+e.getMessage(),e);
//			} catch (Exception e2) {
//			}
//			jsonResponse = new JsonResponse();
//			jsonResponse.setStatus(COMMUNICATE_ERR);
		}
		logger.debug("Response date is :"+res);
		JsonResponse jsonResponse = checkAndConvert2JsonResponse(res);
		return jsonResponse;
	}
	@Override
	public JsonResponse doPost(String uri, List<NameValuePair> formParams, List<Header> headerList,
			CookieStore cookieStore) {
		JsonResponse jsonResponse =null;
		try {
			if(formParams==null){
				formParams = new ArrayList<NameValuePair>();
			}
			HttpEntity entity = new UrlEncodedFormEntity(formParams,encoding);
			jsonResponse = doPost(uri, entity, headerList, cookieStore);
		} catch (Exception e) {
			logger.info("The client request:"+uri+",params "+JSON.toJSONString(formParams)+",cause exception,msg is :"+e.getMessage(),e);
		}
		return jsonResponse;
	}
	
	public abstract JsonResponse checkAndConvert2JsonResponse(String res);

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Integer getSendBufferSize() {
		return sendBufferSize;
	}

	public void setSendBufferSize(Integer sendBufferSize) {
		this.sendBufferSize = sendBufferSize;
	}
	

}
