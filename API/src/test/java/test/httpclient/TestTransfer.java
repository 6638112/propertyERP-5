/**   
 * Filename:    TestTransfer.java   
 * @version:    1.0  
 * Create at:   2014年8月20日 上午6:27:47   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月20日    shiyl      1.0         1.0 Version   
 */
package test.httpclient;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Filename: TestTransfer.java
 * 
 * @version: 1.0.0 Create at: 2014年8月20日 上午6:27:47 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月20日 shiyl 1.0 1.0 Version
 */
public class TestTransfer {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String postData = "http://wuma.hyn.com:8080/pentaho/j_spring_security_check?j_username=admin&j_password=password3333&locale=en_US";
		HttpUriRequest httpUriRequest = new HttpGet(postData);
		CookieStore cookieStore = new BasicCookieStore();
		/*{// cookie
			BasicClientCookie cookie = new BasicClientCookie("CASTGC", "aaa");
			cookie.setDomain("127.0.0.1");
			cookie.setPath("/");
			cookieStore.addCookie(cookie);
		}*/

		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

		CloseableHttpResponse response = client.execute(httpUriRequest);
		{// response.getEntity()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
					System.out.println(res);
				}
			}
		}
		{
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
			System.out.println("===");
			// System.out.println(response.getParams());
			System.out.println(response.getStatusLine());
		}
	}

}
