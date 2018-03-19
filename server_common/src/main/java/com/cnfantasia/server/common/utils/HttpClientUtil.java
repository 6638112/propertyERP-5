/**   
 * Filename:    HttpClientUtil.java   
 * @version:    1.0  
 * Create at:   2014年9月24日 上午7:04:33   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年9月24日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Filename: HttpClientUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年9月24日 上午7:04:33 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月24日 shiyl 1.0 1.0 Version
 */
public class HttpClientUtil {
	public static final String Default_Encode = "UTF-8";
	public static byte[] doPost(String url, JSONObject jsonObject) throws HttpException, IOException{
		Map<String, Object> params = new HashMap<String, Object>();
		if(jsonObject!=null){
			for(String key:jsonObject.keySet()){
				params.put(key, jsonObject.get(key));
			}
		}
		return doPost(url, params);
	}
	public static byte[] doPost(String postUrl, Map<String,Object> parameters) throws ClientProtocolException, IOException{
		List<NameValuePair> formParams = null;
		if(parameters!=null){
			formParams = new ArrayList<NameValuePair>();
			for(String key:parameters.keySet()){
				formParams.add(new BasicNameValuePair(key, parameters.get(key).toString()));
			}
		}
		return doPostByte(postUrl, formParams, null, null, Default_Encode);
	}
	
	
	public static String doPostString(String postUrl, List<NameValuePair> formParams, List<Header> headerList,
			CookieStore cookieStore) throws ClientProtocolException, IOException {
		return doPostString(postUrl, formParams, headerList, cookieStore, Default_Encode);
	}
	public static String doPostString(String postUrl, List<NameValuePair> formParams, List<Header> headerList,
			CookieStore cookieStore,String encode) throws ClientProtocolException, IOException {
		byte[] responseData = doPostByte(postUrl, formParams, headerList, cookieStore, encode);
		String responseStr = null;
		if(responseData!=null&&responseData.length>0){
			responseStr = new String(responseData, encode);
		}
		return responseStr;
	}
	
	public static byte[] doPostByte(String postUrl, List<NameValuePair> formParams, List<Header> headerList,
			CookieStore cookieStore,String encode) throws ClientProtocolException, IOException {
		byte[] responseData = null;
		HttpEntity postentity = new UrlEncodedFormEntity(formParams, encode);
		responseData = doPostByte(postUrl, postentity, headerList, cookieStore);
		return responseData;
	}
	public static String doPostString(String postUrl,HttpEntity httpEntity, List<Header> headerList,
			CookieStore cookieStore,String encode) throws ClientProtocolException, IOException {
		byte[] responseData = doPostByte(postUrl, httpEntity, headerList, cookieStore);
		String responseStr = null;
		if(responseData!=null&&responseData.length>0){
			responseStr = new String(responseData, encode);
		}
		return responseStr;
	}
	public static byte[] doPostByte(String postUrl,HttpEntity httpEntity, List<Header> headerList,
			CookieStore cookieStore) throws ClientProtocolException, IOException {
		byte[] responseData = null;
		if (cookieStore == null) {
			cookieStore = new BasicCookieStore();
		}
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(httpEntity);
		if (headerList != null) {
			for (Header tmpHeader : headerList) {
				httpPost.addHeader(tmpHeader);
			}
		}
		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				responseData = EntityUtils.toByteArray(entity);
			} else if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY) || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
					|| (statusCode == HttpStatus.SC_SEE_OTHER) || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
				// 读取新的URL地址
				Header[] headerLocation = response.getHeaders("location");
				if (headerLocation != null) {
					String newuri = headerLocation[0].getValue();
					if ((newuri == null) || (newuri.equals(""))) {
						newuri = "/";
					}
					responseData = doPostByte(newuri, null, headerList, cookieStore);
				} else {
					throw new RuntimeException("Invalid redirect");
				}
			}
		}
		return responseData;
	}
	
	public static byte[] doGet(String postUrl, Map<String,Object> parameters) throws ClientProtocolException, IOException{
		return doPost(postUrl, parameters);
	}
	public static byte[] doGet(String url) throws HttpException, IOException{
		return doPost(url, null);
	}
	
}
