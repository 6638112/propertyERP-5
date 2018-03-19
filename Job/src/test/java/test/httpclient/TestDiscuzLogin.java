/**   
 * Filename:    TestDiscuzLogin.java   
 * @version:    1.0  
 * Create at:   2014年7月30日 上午10:48:01   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年7月30日    shiyl      1.0         1.0 Version   
 */
package test.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Filename: TestDiscuzLogin.java
 * 
 * @version: 1.0.0 Create at: 2014年7月30日 上午10:48:01 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年7月30日 shiyl 1.0 1.0 Version
 */
public class TestDiscuzLogin {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException, HttpException {
		String postData = "http://192.168.1.42/api/uc.php?time=1406767158&code=c380SsQy5y%2Fk8aascZ40EZgeuGgbSpF2G%2B9FJ60oAs0SwlhhSFJL6vTuS66oqPhoXCo8X5IXPQtFylsbdjf3Zq7WRU%2FkNQrazh239fIemxnimMrkOC4D5hna%2Fk00ZFjfHup%2FfHnJ706SZaNPCAV9f1o0LzUD1Q%2FXy8IyT60";
		HttpPost httpPost = new HttpPost(postData);
//		httpPost.setHeader("Cookie","kEAn_2132_saltkey=C79Uaj74; kEAn_2132_lastvisit=1405472724; kEAn_2132_ulastactivity=d1a99kbAF%2BdG6GMEaq6trLinJDLkJCzZWS4tP8ZDyx%2BjOUQReS2t; kEAn_2132_nofavfid=1; kEAn_2132_visitedfid=73D89D92D90; kEAn_2132_forum_lastvisit=D_92_1406278673D_89_1406278692; kEAn_2132_sid=va2r23; kEAn_2132_lastact=1406718649%09misc.php%09seccode; kEAn_2132_seccode=3.7694c708856ef6f475");
//		httpPost.setHeader("Cookie","kEAn_2132_saltkey=C79Uaj74; kEAn_2132_lastvisit=1405472724; kEAn_2132_ulastactivity=d1a99kbAF%2BdG6GMEaq6trLinJDLkJCzZWS4tP8ZDyx%2BjOUQReS2t; kEAn_2132_nofavfid=1; kEAn_2132_visitedfid=73D89D92D90; kEAn_2132_forum_lastvisit=D_92_1406278673D_89_1406278692; kEAn_2132_sid=va2r23; kEAn_2132_lastact=1406718649%09misc.php%09seccode; kEAn_2132_seccode=3.7694c708856ef6f475");
		CookieStore cookieStore = new BasicCookieStore();
		/*{// cookie
			// BasicClientCookie cookie = new
			// BasicClientCookie("CASTGC","TGT-1-qbZpNRg1pDXTDg3Q2k4HpdeJLBnRw91hFcCOuknJJXfBj1c64p-cas01.example.org");
			BasicClientCookie cookie = new BasicClientCookie("CASTGC", "aaa");
			cookie.setDomain("127.0.0.1");
			cookie.setPath("/");
			cookieStore.addCookie(cookie);
		}*/
//		{//http://192.168.1.42/member.php?mod=logging&action=login&loginsubmit=yes&handlekey=login&loginhash=LS23m&inajax=1
//			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//			formParams.add(new BasicNameValuePair("mod", "logging"));
//			formParams.add(new BasicNameValuePair("action", "login"));
//			formParams.add(new BasicNameValuePair("loginsubmit", "yes"));
//			formParams.add(new BasicNameValuePair("handlekey", "login"));
//			formParams.add(new BasicNameValuePair("loginhash", "LS23m"));
//			formParams.add(new BasicNameValuePair("inajax", "1"));
//			
//			formParams.add(new BasicNameValuePair("username", "admin"));
//			formParams.add(new BasicNameValuePair("password", "admin"));
//			formParams.add(new BasicNameValuePair("seccodeverify", "c7vt"));
//			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
//			httpPost.setEntity(entity);
//			//formhash=4a2ccd42&referer=http%3A%2F%2F192.168.1.42%2Fforum.php&loginfield=username&username=admin&password=admin&questionid=0&answer=&seccodehash=cSAva2r23&seccodemodid=member%3A%3Alogging&seccodeverify=ehf7
//		}
		// {
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("param1", "中国"));
		// params.add(new BasicNameValuePair("param2", "value2"));
		// String param = URLEncodedUtils.format(params, "UTF-8");
		// URI uri = URIUtils.createURI("http", "localhost", 8084,
		// "/sshsky/index.html", param, null);
		// System.out.println(uri);
		// }
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
		System.out.println("~~~~TestDiscuzLogin~~~~~~~~~~~~");
		{
//			TestDiscuzSupport.support(response);
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
			System.out.println("=======TestDiscuzLogin=======");
			System.out.println(response.getParams());
			System.out.println(response.getStatusLine());
			
			
//			//cookie
//			System.out.println("---------------");
//			HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
//			while (it.hasNext()) {
//				HeaderElement elem = it.nextElement();
//				System.out.println(elem.getName() + " = " + elem.getValue());
////				NameValuePair[] params = elem.getParameters();
////				for (int i = 0; i < params.length; i++) {
////					System.out.println(" " + params[i]);
////				}
//			}
		}
		
	}
	
	
}
