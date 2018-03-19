/**   
* Filename:    IHttpClient.java   
* @version:    1.0  
* Create at:   2014年6月5日 上午9:03:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.httpcllient;

import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;

import com.cnfantasia.server.common.json.JsonResponse;

/**
 * Filename:    IHttpClient.java
 * @version:    1.0.0
 * Create at:   2014年6月5日 上午9:03:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月5日       shiyl             1.0             1.0 Version
 */
public interface IHttpClient{
	public JsonResponse submitSimple(String uri,Map<String,Object> params);
	/**
	 * 简单封装的请求
	 * @param uri
	 * @param params
	 * @param appendHeaderList 附加的header,会先加载配置的header
	 * @return
	 */
	public JsonResponse submitSimple(String uri,Map<String,Object> params,List<Header> appendHeaderList);
	public JsonResponse doPost(String postUrl, List<NameValuePair> formParams, List<Header> headerList,
			CookieStore cookieStore);
	public JsonResponse doPost(String postUrl,HttpEntity entity, List<Header> headerList,
			CookieStore cookieStore);

	/**
	 * Get请求走这里
	 * @author wenfq 2017-08-04
	 * @param uri
	 * @param params
	 * @param appendHeaderList
	 * @return
	 */
	public JsonResponse doGet(String uri,Map<String,Object> params,List<Header> appendHeaderList);
}
