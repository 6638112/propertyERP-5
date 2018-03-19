/**   
* Filename:    TestDiscuzSupport.java   
* @version:    1.0  
* Create at:   2014年7月30日 下午12:50:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月30日    shiyl      1.0         1.0 Version   
*/
package test.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Filename:    TestDiscuzSupport.java
 * @version:    1.0.0
 * Create at:   2014年7月30日 下午12:50:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月30日       shiyl             1.0             1.0 Version
 */
public class TestDiscuzSupport {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException, HttpException {
		CookieStore cookieStore = new BasicCookieStore();
//		{
//			BasicClientCookie cookie = new BasicClientCookie("kEAn_2132_saltkey", "h5mkg4RB");
//			cookie.setPath("/");
//			cookie.setDomain("192.168.1.42");
//			cookie.setVersion(0);
//			cookieStore.addCookie(cookie);
//		}
//		{
//			BasicClientCookie cookie = new BasicClientCookie("kEAn_2132_lastvisit", "1406764345");
//			cookie.setPath("/");
//			cookie.setDomain("192.168.1.42");
//			cookie.setVersion(0);
//			cookieStore.addCookie(cookie);
//		}
//		{
//			BasicClientCookie cookie = new BasicClientCookie("kEAn_2132_sid", "ZADZ4v");
//			cookie.setPath("/");
//			cookie.setDomain("192.168.1.42");
//			cookie.setVersion(0);
//			cookieStore.addCookie(cookie);
//		}
//		{
//			BasicClientCookie cookie = new BasicClientCookie("kEAn_2132_lastact", "1406767945%09uc.php%09");
//			cookie.setPath("/");
//			cookie.setDomain("192.168.1.42");
//			cookie.setVersion(0);
//			cookieStore.addCookie(cookie);
//		}
		support(cookieStore);
	}
	public static CookieStore getCookieStore(HttpResponse httpResponse) throws HttpException, IOException{
		HttpContext context = null;
		final HttpClientContext localcontext = HttpClientContext.adapt(
        context != null ? context : new BasicHttpContext());
		ResponseProcessCookies responseProcessCookies=new ResponseProcessCookies();
		responseProcessCookies.process(httpResponse, localcontext);
		CookieStore cookieStore = localcontext.getCookieStore();
		
//		HeaderElementIterator it = new BasicHeaderElementIterator(httpResponse.headerIterator("Set-Cookie"));
//		while (it.hasNext()) {
//			HeaderElement elem = it.nextElement();
//			if(elem.getValue()!=null){
//				BasicClientCookie cookie = new BasicClientCookie(elem.getName(), elem.getValue());
//				cookie.setPath("/");
//				cookie.setVersion(0);
////				cookie.setDomain("192.168.1.42");
//				cookieStore.addCookie(cookie);
//				System.out.println("TestDiscuzSupport.getCookieStore is "+elem.getName()+"  "+elem.getValue());
//			}
////			NameValuePair[] params = elem.getParameters();
////			for (int i = 0; i < params.length; i++) {
////				System.out.println(" " + params[i]);
////			}
//		}
		
//		Header[] headers = httpResponse.getHeaders("Set-Cookie");
//		for(Header tmpHeader:headers){
////			BasicClientCookie cookie = new BasicClientCookie("CASTGC", "aaa");
////			cookie.setDomain("127.0.0.1");
////			cookie.setPath("/");
////			cookieStore.addCookie(cookie);
//			System.out.println(tmpHeader.getName()+"  "+tmpHeader.getValue());
//		}
//		System.out.println("123123123123");
		return cookieStore;
	}
	@SuppressWarnings("deprecation")
	public static void support(CookieStore cookieStore) throws UnsupportedEncodingException, IOException, HttpException{
		String postData = "http://192.168.1.42/forum.php?mod=misc&action=recommend&do=add&tid=16&hash=128299cc&ajaxmenu=1&inajax=1&ajaxtarget=recommend_add_menu_content";
		HttpGet httpPost = new HttpGet(postData);
//		httpPost.setHeader("Cookie","kEAn_2132_auth=40d7VNbbv3KaToCWTK7aiN1cAXi%2Ft4%2FWy%2FMmXfcKsH6JvUA7Sf7zmSLYgfhGRtMiJwoD3oRBxIDNLpvqsIqtsQ; kEAn_2132_saltkey=zp9ADOlU; kEAn_2132_lastvisit=1406717177; kEAn_2132_lastcheckfeed=1%7C1405474196; kEAn_2132_home_readfeed=1405044681; kEAn_2132_sid=C727QY; kEAn_2132_lastact=1406727551%09uc.php%09; kEAn_2132_nofavfid=1; kEAn_2132_visitedfid=75; kEAn_2132_smile=1D1; kEAn_2132_ulastactivity=85d3D%2BNbvFtRp9Sx5yty1N7yuS6xa2EmlDyH9106dy59Xt3npptt; kEAn_2132_editormode_e=1; kEAn_2132_forum_lastvisit=D_92_1406278673D_89_1406278692; sid=b3ecW1ROP84%252BQYugXg4P9HKXXl%252FY4oycrQL9R%252BeRNyknDuymjH6P6Q9vTA; JishiGou_DVF5Q8_sid=ZXqVhO; AJSTAT_ok_times=1");

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
		System.out.println("~~~~~~~~TestDiscuzSupport~~~~~~~~");
		{// response.getEntity()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
					System.out.println(res);
				}
			}
		}
		System.out.println("~~~~~~~~TestDiscuzSupport~~~~~~~~");
		{
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
			System.out.println("==TestDiscuzSupport=");
			System.out.println(response.getParams());
			System.out.println(response.getStatusLine());
			
		}
		
	}
	
	
}
