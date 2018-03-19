package com.cnfantasia.server.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.alibaba.fastjson.JSON;

/**
 * 
 * <p>
 * 后台Http操作工具类
 * <p>
 * <p>
 * </p>
 * 
 * @author yewj
 * @version $Id: HttpUtil.java
 */
public class HttpUtil {

	private static Logger LOG = Logger.getLogger(HttpUtil.class);

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static final String[] IGNORE_HEADERS = { "content-length", "accept-encoding" };

	private HttpClient httpClient = null;

	private Map<String, Object> parameters = new HashMap<String, Object>();

	private Map<String, String> headers = new HashMap<String, String>();

	private  int TIMEOUT_TIME = 10000;

	public void setTIMEOUT_TIME(int timeout_time) {
		TIMEOUT_TIME = timeout_time;
	}

	public HttpUtil() {}

	public HttpUtil(HttpServletRequest request) {
		addHeaders(request);
	}

	public HttpUtil(int DEFAULT_TIME_OUT) {
		TIMEOUT_TIME = DEFAULT_TIME_OUT;
	}

	 public HttpUtil(HttpServletRequest request,int DEFAULT_TIME_OUT) {
	 addHeaders(request);
	 TIMEOUT_TIME = DEFAULT_TIME_OUT;
	 }

	public void addParameter(Map<String, Object> paramMap){
		parameters.putAll(paramMap);
	}
	
	public void addParameter(String name, String value) {
		parameters.put(name, value);
	}

	public void removeParameter(String name) {
		parameters.remove(name);
	}
	
	public void clearParameter() {
		parameters.clear();
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	@SuppressWarnings("unchecked")
	public void addHeaders(HttpServletRequest request) {
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			addHeader(name, request.getHeader(name));
		}
	}

	public void addHeader(String name, String value) {
		headers.put(name, value);
	}

	public String post(String url) throws HttpException, IOException {

		if (httpClient == null) createClient();

		PostMethod method = new PostMethod(url);
		method.getParams().setSoTimeout(TIMEOUT_TIME);
		addParameters(method);
		addHeaders(method);

		return getRequestString(method, url);
	}

	public String post(String url, int timeOut) throws HttpException, IOException {

		if (httpClient == null) createClient();

		PostMethod method = new PostMethod(url);
		method.getParams().setSoTimeout(timeOut);
		addParameters(method);
		addHeaders(method);

		return getRequestString(method, url);
	}
	
	public String post(String url, File file, int timeOut) throws HttpException, IOException {

		if (httpClient == null) createClient();

		PostMethod method = new PostMethod(url) {
			@Override
			public String getRequestCharSet() {
				return "utf-8";
			}
			
		};
		method.getParams().setSoTimeout(timeOut);
		addParameters(method, file);
		addHeaders(method);

		return getRequestString(method, url);
	}
	
	public String post(String url, int timeOut, String returnCharset) throws HttpException, IOException {

		if (httpClient == null) createClient();

		PostMethod method = new PostMethod(url);
		method.getParams().setSoTimeout(timeOut);
		addParameters(method);
		addHeaders(method);

		return getRequestString(method, url, returnCharset);
	}

	public String get(String url, int timeOut, String returnCharset, HttpServletRequest request) throws HttpException, IOException {
		if (httpClient == null) createClient();

		//add parameters to url
		StringBuffer stringBufferUrl = new StringBuffer(url.contains("?") ? url : url + "?");
		for (String key : parameters.keySet()) {
			if (parameters.get(key) == null) {
				continue;
			}
			String value = parameters.get(key).toString();
			value = URLEncoder.encode(value, "UTF-8");//对value进行encode操作
			stringBufferUrl.append("&").append(key).append("=").append(value);
		}

		GetMethod method = new GetMethod(stringBufferUrl.toString());
		method.getParams().setSoTimeout(timeOut);

		addHeaders(method);

		StringBuilder buffer = new StringBuilder();
		try {
			Cookie[] cookies = (Cookie[]) request.getSession().getAttribute("cookies");
			if(cookies != null) {
				httpClient.getState().addCookies(cookies);
			}
			int statusCode = httpClient.executeMethod(method);
			request.getSession().setAttribute("cookies", httpClient.getState().getCookies());

			if (statusCode != HttpStatus.SC_OK) throw new IOException(url + ":" + statusCode);
			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), returnCharset));
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line).append(LINE_SEPARATOR);
		} finally {
			if (method != null) method.releaseConnection();
		}

		return buffer.toString();
	}
	
	public String post(String url, int timeOut, String returnCharset, HttpServletRequest request) throws HttpException, IOException {

		if (httpClient == null) createClient();

		PostMethod method = new PostMethod(url);
		method.getParams().setSoTimeout(timeOut);
		addParameters(method);
		addHeaders(method);

		return getRequestString(method, url, returnCharset, request);
	}

	public String post(String url, String charset) throws HttpException, IOException {

		if (httpClient == null) createClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setSoTimeout(TIMEOUT_TIME);
		if (charset.equalsIgnoreCase("UTF-8")) addUTF8Parameters(method);
		else
			addParameters(method);
		addHeaders(method);

		return getRequestString(method, url);
	}

	public String post(String url, String charset, int timeOut) throws HttpException, IOException {

		if (httpClient == null) createClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setSoTimeout(timeOut);
		if (charset.equalsIgnoreCase("UTF-8")) addUTF8Parameters(method);
		else
			addParameters(method);
		addHeaders(method);

		return getRequestString(method, url);
	}

	public String get(String url) throws HttpException, IOException {

		if (httpClient == null) createClient();
		
		url = appendParametersToGetMethodURL(url);
		GetMethod method = new GetMethod(url);
		method.getParams().setSoTimeout(TIMEOUT_TIME);
		addHeaders(method);
		
		return getRequestString(method, url);	
	}

	public String get(String url, int timeOut) throws HttpException, IOException {

		if (httpClient == null) createClient();

		url = appendParametersToGetMethodURL(url);
		
		GetMethod method = new GetMethod(url);
		method.getParams().setSoTimeout(timeOut);
		addHeaders(method);

		return getRequestString(method, url);
	}

	private String appendParametersToGetMethodURL(String url) {
		if(!parameters.isEmpty()){
			url = url+"?";
			int i = 0;
			for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next();
				i++;
				if(i < parameters.size())
					url = url + key +"="+parameters.get(key).toString() + "&";
				else
					url = url + key +"="+parameters.get(key).toString();
			}
		}
		LOG.info("url is:" + url);
		return url;
	}

	private String getRequestString(HttpMethod method, String url) throws UnsupportedEncodingException, IOException {
		StringBuilder buffer = new StringBuilder();
		try {
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) throw new IOException(url + ":" + statusCode);

			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line).append(LINE_SEPARATOR);
		} finally {
			if (method != null) method.releaseConnection();
		}
		return buffer.toString();
	}
	
	private String getRequestString(HttpMethod method, String url, String returnCharset) throws UnsupportedEncodingException, IOException {
		StringBuilder buffer = new StringBuilder();
		try {
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK && statusCode != 302) throw new IOException(url + ":" + statusCode);
			if(statusCode == 302) {
				String location = method.getResponseHeader("Location").getValue();
				HttpUtil httpUtil = new HttpUtil();
				httpUtil.addHeaders(method);
				return httpUtil.post(location, TIMEOUT_TIME);
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), returnCharset));
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line).append(LINE_SEPARATOR);
		} finally {
			if (method != null) method.releaseConnection();
		}
		return buffer.toString();
	}

	private String getRequestString(HttpMethod method, String url, String returnCharset, HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		StringBuilder buffer = new StringBuilder();
		try {
			Cookie[] cookies = (Cookie[]) request.getSession().getAttribute("cookies");
			if(cookies != null) {
				httpClient.getState().addCookies(cookies);
			}
			int statusCode = httpClient.executeMethod(method);
			request.getSession().setAttribute("cookies", httpClient.getState().getCookies());
			if (statusCode != HttpStatus.SC_OK && statusCode != 302) throw new IOException(url + ":" + statusCode);
			if(statusCode == 302) {
				String location = method.getResponseHeader("Location").getValue();
				HttpUtil httpUtil = new HttpUtil();
				httpUtil.addHeader("subChannelId", "20");
				return httpUtil.post(location, TIMEOUT_TIME, "UTF-8", request);
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), returnCharset));
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line).append(LINE_SEPARATOR);
		} finally {
			if (method != null) method.releaseConnection();
		}
		return buffer.toString();
	}

	private void addHeaders(HttpMethod method) {
		out: for (String key : headers.keySet()) {
			for (String ignore : IGNORE_HEADERS)
				if (ignore.equals(key)) continue out;
			String value = headers.get(key);
			method.addRequestHeader(key, value);
		}
	}

	private void addParameters(PostMethod method) throws UnsupportedEncodingException {
		for (String key : parameters.keySet()) {
			if(parameters.get(key)==null){
				continue;
			}
			String value = parameters.get(key).toString();
			value = new String(value.getBytes("utf-8"), "ISO8859-1");
			method.addParameter(key, value);
		}
	}

	private void addParameters(PostMethod method, File file) throws UnsupportedEncodingException {
		List<Part> partList = new ArrayList<Part>();
		for (String key : parameters.keySet()) {
			String value = parameters.get(key).toString();
//			value = new String(value.getBytes("utf-8"), "ISO8859-1");
			partList.add(new StringPart(key, value, "utf-8"));
		}
		try {
			partList.add(new FilePart("Filedata", file, null, "utf-8"));
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
		Part[] parts = partList.toArray(new Part[0]);
		MultipartRequestEntity mre = new MultipartRequestEntity(parts, method.getParams());
		method.setRequestEntity(mre);
	}

	private void addUTF8Parameters(PostMethod method) {
		for (String key : parameters.keySet()) {
			String value = parameters.get(key).toString();
			method.addParameter(key, value);
		}
	}

	private void createClient() {
		httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT_TIME);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT_TIME);

		HttpClientParams params = httpClient.getParams();
		params.setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

		addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		addHeader("Accept-Language", "zh-cn,zh;q=0.5");
	}
	
	public static JSONObject post(String url,JSONObject json){
		org.apache.http.client.HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			 
			HttpResponse res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == org.springframework.http.HttpStatus.OK.value()){
				HttpEntity entity = res.getEntity();
				String charset = EntityUtils.getContentCharSet(entity);
				response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	
	private static final int HA_TIME_OUT = 8000;
	public static String postRaw(String url, Map<String, Object> rawParams){
		String result = StringUtils.EMPTY;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HA_TIME_OUT)
					.setConnectTimeout(HA_TIME_OUT).setConnectionRequestTimeout(HA_TIME_OUT)
					.setStaleConnectionCheckEnabled(true).build();
			httppost.setConfig(requestConfig);
			
			if (rawParams != null) {
				StringEntity s = new StringEntity(JSON.toJSONString(rawParams), CharEncoding.UTF_8);
				s.setContentEncoding(CharEncoding.UTF_8);
				s.setContentType("application/json");
				httppost.setEntity(s);
			}
			response = httpclient.execute(httppost);
			if(response.getStatusLine().getStatusCode() == org.springframework.http.HttpStatus.OK.value()){
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, CharEncoding.UTF_8);
				}
				EntityUtils.consume(resEntity);
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = null;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * post请求服务器
	 * 
	 * @param url 发送请求的URL
	 * @param rawParams 请求参数
	 * @return
	 */
	public static String post(String url, Map<String, Object> rawParams) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("accept", "*/*");
			httppost.setHeader("connection", "Keep-Alive");
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
			if (rawParams != null) {
				Iterator<String> keys = rawParams.keySet().iterator();
				String key = null;
				List<NameValuePair> nvps = new ArrayList<NameValuePair>(rawParams.size());
				while (keys.hasNext()) {
					key = keys.next();
					NameValuePair nvp = new BasicNameValuePair(key, String.valueOf(rawParams.get(key)));
					nvps.add(nvp);
				}

				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
				httppost.setEntity(formEntity);
			}
			response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				result = EntityUtils.toString(resEntity, "utf-8");
			}
			EntityUtils.consume(resEntity);
		} catch (IOException e) {
			e.printStackTrace();
			result = null;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public static final String getAllParams(String logTag, HttpServletRequest request){
		StringBuffer paramAll = new StringBuffer();
	    {
	    	@SuppressWarnings("unchecked")
		    Enumeration<String> attNames = request.getParameterNames();
	        while(attNames.hasMoreElements()){
		       	String obj = attNames.nextElement();
		       	paramAll.append(obj);
		       	paramAll.append(":");
		       	paramAll.append(request.getParameter(obj));
		      	paramAll.append(";");
	        }
	        paramAll.append(";RemoteAddr:"+request.getRemoteAddr()+";");
	        
	        return logTag +" paramAll:["+paramAll+"]";
	    }
	}
	
}
