/**   
* Filename:    TestHeaderPostParams.java   
* @version:    1.0  
* Create at:   2014年9月19日 上午7:35:16   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月19日    shiyl      1.0         1.0 Version   
*/
package test.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Filename:    TestHeaderPostParams.java
 * @version:    1.0.0
 * Create at:   2014年9月19日 上午7:35:16
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月19日       shiyl             1.0             1.0 Version
 */
public class TestHeaderPostParams {
	public static void main(String[] args) throws Exception {
//		String postData = "http://127.0.0.1:8080/API/kitchen/qryCookbookTypes.json";
		String postData = "http://127.0.0.1:8080/API/prize/doPrize.json";
		HttpPost httpPost = new HttpPost(postData);
		{//post param
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			formParams.add(new BasicNameValuePair("cityName", "logging"));
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(entity);
		}
//		{//ok
//			httpPost.addHeader("deviceId", "aaaa");
//		}
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = client.execute(httpPost);
		{// response.getEntity()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
					System.out.println(res);
				}
			}
		}
	
		
	}
	
}
