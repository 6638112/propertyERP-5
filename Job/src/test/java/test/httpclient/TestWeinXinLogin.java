/**   
* Filename:    TestWeinXinLogin.java   
* @version:    1.0  
* Create at:   2014年8月12日 上午1:10:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月12日    shiyl      1.0         1.0 Version   
*/
package test.httpclient;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    TestWeinXinLogin.java
 * @version:    1.0.0
 * Create at:   2014年8月12日 上午1:10:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月12日       shiyl             1.0             1.0 Version
 */
public class TestWeinXinLogin {
	private static Log logger = LogFactory.getLog(TestWeinXinLogin.class);
	public static void main(String[] args) {
		String accessToken = "OezXcEiiBSKSxW0eoylIeMRHXma4K03lH1uADyOQGzpoH7GgOsNkwCpTESxc3OkoalEdFHJ_SZikXEetvB9OamjOXroxdiy0sOzAd7KLPUcpzWef7bH-gOUqFSvBw4Uk8smEheZrYaiD-WVyO0gMPQ";
		String openId = "onDQXtyrPgm8f5-CnUZoYJpnw-cA";
		String postData = "https://api.weixin.qq.com/sns/auth?access_token="+accessToken+"&openid="+openId;
		HttpUriRequest httpUriRequest = new HttpGet(postData);
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom()
        .setDefaultCookieStore(cookieStore)
        .build();
		
		try {
			CloseableHttpResponse response=client.execute(httpUriRequest);
			{//response.getEntity()
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String res =  new String(EntityUtils.toByteArray(entity),"UTF-8");
						Map<String,Object> resMap = JSON.parseObject(res);
						System.out.println(resMap.get("errcode"));
						System.out.println(res);
//						String errcode = resMap.get("errcode").toString();
//						String errmsg = resMap.get("errmsg").toString();
//						if ("0".equals(errcode)) {
//							System.out.println(true);
//						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("ValidateAccessTokenWeiXin cause Exception,errMsg is "+e.getMessage(),e);
		}
	}
}
