/**   
* Filename:    TestCasCookie.java   
* @version:    1.0  
* Create at:   2014年7月30日 上午1:52:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月30日    shiyl      1.0         1.0 Version   
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
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

/**
 * Filename:    TestCasCookie.java
 * @version:    1.0.0
 * Create at:   2014年7月30日 上午1:52:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月30日       shiyl             1.0             1.0 Version
 */
public class TestCasCookie {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
//		String postData = "http://127.0.0.1:8084/cas/services/j_acegi_cas_security_check";
//		String postData = "http://shiyl:8084/cas/serviceValidate?ticket=ST-3-32OUyei1sFN3VMwUwopt-shiyl&service=http://www.baidu.com/";
//		String postData = "http://192.168.9.211:8084/cas/serviceValidate?ticket=ST-3-32OUyei1sFN3VMwUwopt-shiyl&service=http://www.baidu.com/";
//		String postData = "http://192.168.9.211:8084/cas/login";
		String postData = "http://127.0.0.1:8084/cas/login";
//		String postData = "https://shiyl:8443/cas/login";
//		String postData = "http://192.168.1.31:8084/cas/login";
//		String postData = "http://192.168.1.31:8084/cas/logout";
		HttpUriRequest httpUriRequest = new HttpGet(postData);
//		httpUriRequest.setHeader("Cookie", "CASTGC=TGT-1-qbZpNRg1pDXTDg3Q2k4HpdeJLBnRw91hFcCOuknJJXfBj1c64p-cas01.example.org; JSESSIONID=47FD3F01181C09AFC835078D5934E9AB");
//		CASTGC=TGT-1-qbZpNRg1pDXTDg3Q2k4HpdeJLBnRw91hFcCOuknJJXfBj1c64p-cas01.example.org; JSESSIONID=4A0BD73DEA366BB693162C5BB655B498
		//?ticket=ST-1-embbKMvRbMYTHpx4cqfm-shiyl
		CookieStore cookieStore = new BasicCookieStore();
		{//cookie CASTGC=TGT-1-qbZpNRg1pDXTDg3Q2k4HpdeJLBnRw91hFcCOuknJJXfBj1c64p-cas01.example.org; JSESSIONID=47FD3F01181C09AFC835078D5934E9AB
//			BasicClientCookie cookie = new BasicClientCookie("CASTGC","TGT-1-qbZpNRg1pDXTDg3Q2k4HpdeJLBnRw91hFcCOuknJJXfBj1c64p-cas01.example.org");
			BasicClientCookie cookie = new BasicClientCookie("CASTGC","aaa");
//			cookie.setDomain("192.168.1.31");
//			cookie.setDomain("192.168.9.211");
			cookie.setDomain("127.0.0.1");
//			cookie.setDomain("shiyl");
			cookie.setPath("/");
			cookieStore.addCookie(cookie); 
		}
//		{//cookie
//			BasicClientCookie cookie = new BasicClientCookie("jsessionid","49C468CFBB22464394340FE55759BF7A");
//			//cookie.setDomain("your domain");
//			cookie.setPath("/");
//			cookieStore.addCookie(cookie); 
//		}
		
			
		CloseableHttpClient client = HttpClients.custom()
        .setDefaultCookieStore(cookieStore)
        .build();
		
		CloseableHttpResponse response=client.execute(httpUriRequest);
		{//response.getEntity()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String res =  new String(EntityUtils.toByteArray(entity),"UTF-8");
					System.out.println(res);
				}
			}
		}
		{
			Header[] headers = response.getAllHeaders();
			for(int i=0;i<headers.length;i++){
				System.out.println(headers[i]);
			}
			System.out.println("===");
//			System.out.println(response.getParams());
			System.out.println(response.getStatusLine());
		}
	}
	
	
}
