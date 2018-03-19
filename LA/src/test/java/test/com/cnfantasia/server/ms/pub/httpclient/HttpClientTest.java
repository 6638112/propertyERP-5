/**   
* Filename:    HttpClientTest.java   
* @version:    1.0  
* Create at:   2014年6月5日 上午7:21:06   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月5日    shiyl      1.0         1.0 Version   
*/
package test.com.cnfantasia.server.ms.pub.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import test.com.cnfantasia.server.ms.BaseTest;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.httpcllient.IHttpClient;

/**
 * Filename:    HttpClientTest.java
 * @version:    1.0.0
 * Create at:   2014年6月5日 上午7:21:06
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月5日       shiyl             1.0             1.0 Version
 */
public class HttpClientTest extends BaseTest{
	
//	@Test
//	public void testSubmit() throws Exception{
//		IHttpClient simpleHttpClient=ctx.getBean("simpleHttpClient", IHttpClient.class);
//		JsonRequest jsonRequest = new JsonRequest();
//		Request request = new Request();
//		request.addParameter("type", 3);
//		request.addParameter("page", 1);
//		request.addParameter("pageNum", 10);
//		request.setTransaction("/user/qryMsgList.json");
//		jsonRequest.setRequest(request);
//		Object response = simpleHttpClient.submit(jsonRequest);
//		System.out.println(JSON.toJSONString(response));
//	}
	@Test
	@Ignore
	public void testSubmitSimple() throws Exception{
		IHttpClient simpleHttpClient=ctx.getBean("simpleHttpClient", IHttpClient.class);
		String uri = "/user/qryMsgList.json";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("type", 3);
		params.put("page", 1);
		params.put("pageNum", 10);
		Object response = simpleHttpClient.submitSimple(uri, params);
		System.out.println(JSON.toJSONString(response));
	}
//	@Test
//	public void testWeiXin(){
//		SimpleHttpClient simpleHttpClient = ctx.getBean("simpleHttpClient", SimpleHttpClient.class);
//		String url = ConstantUtil.TOKENURL+"?grant_type="+ConstantUtil.GRANT_TYPE+"&appid="+ConstantUtil.APP_ID+"&secret="+ConstantUtil.APP_SECRET;
//		simpleHttpClient.setBaseURL(baseURL);
//	}
}
