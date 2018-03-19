/**   
 * Filename:    TestDiscuzLoginContext.java   
 * @version:    1.0  
 * Create at:   2014年7月31日 上午1:43:17   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年7月31日    shiyl      1.0         1.0 Version   
 */
package test.httpclientContext;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import test.httpclient.TestDiscuzSupport;

/**
 * Filename: TestDiscuzLoginContext.java
 * 
 * @version: 1.0.0 Create at: 2014年7月31日 上午1:43:17 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年7月31日 shiyl 1.0 1.0 Version
 */
@SuppressWarnings("deprecation")
public class TestDiscuzLoginContext {

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws ClientProtocolException, IOException, HttpException {
		HttpClient httpclient = new DefaultHttpClient();
		// 创建cookie store的本地实例
		CookieStore cookieStore = new BasicCookieStore();
		// 创建本地的HTTP内容
		HttpContext localContext = new BasicHttpContext();
		// 绑定定制的cookie store到本地内容中
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
//	HttpGet httpget = new HttpGet("http://192.168.1.42/api/uc.php?time=1406775452&code=7a67MVUVPiE9ISQqQQf3bxtzD%2Fu5KWaD4jyznwEpq7jqh4O%2BibzrtMhUpPgXWWvCpKkQxwhUqUZPZCi%2BtSvIq1%2BRut%2BAIk7XrOumCLNDIra%2BCXoO7zp2XFWWTu5Amj7uss7gGJKnvhN1gkCWBoqETZJTqjPYVTZMWiwOsmQ");
		HttpGet httpget = new HttpGet("http://192.168.1.42/api/uc.php?time=1406775907&code=6bcb594%2BqOlnSfUHFJWkFfbdcolhe8xvQIHh9G6PQ77jYZf9exG5sZrg43Etoet%2FrdxMxosR2xHe4nX9y3kzidM0tGk1bvTJv08FicAkCsLK%2B1Cy5XT7DuRHzZPWhfs9G6QNcMnwt6TbM%2FLo4M%2F7Pj3ExGdSKBCFPZ7GyiTU");
		// httpget.addHeader("Host", "192.168.1.42");
		// httpget.addHeader("User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0");
		// httpget.addHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// httpget.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		// httpget.addHeader("Accept-Encoding", "gzip, deflate");
		// // httpget.addHeader("Cookie",
		// "sid=1854HwpLhpw%252BuR%252FRXmO2WxCMoWsGvrniDVywCRSwYHndWPLNwuFBVbne%252FA; PHPSESSID=jmd8f5q9se6mqkj1urbg5071e5; JishiGou_DVF5Q8_sid=ZXqVhO; AJSTAT_ok_times=1; kEAn_2132_sid=G8uMTU; kEAn_2132_saltkey=iSXp0U1p; kEAn_2132_lastvisit=1406719922; kEAn_2132_lastact=1406768228%09uc.php%09; kEAn_2132__refer=%252Fhome.php%253Fmod%253Dspacecp; kEAn_2132_seccode=12.effb0019e512b975ef; kEAn_2132_ulastactivity=ac53CAu1LnBovu6ZtH%2FETOmuDwOLtNywCPxyFY4OGw7TzNs4YHhB; kEAn_2132_auth=27dawLgP088Zgh1Ewmk1CVbaLkY83yiiS55c0riY%2FKtl3isufjfyJF5tBS8b%2Ff1zrxmw6Un1hTZvz61seQj7; kEAn_2132_lastcheckfeed=1%7C1406723537; kEAn_2132_lip=192.168.0.247%2C1406727265; kEAn_2132_nofavfid=1; kEAn_2132_st_p=1%7C1406724758%7C950ca549b4a22d38e14ebabf881224f0; kEAn_2132_visitedfid=75; kEAn_2132_viewid=tid_16; kEAn_2132_smile=1D1; kEAn_2132_creditnotice=0D0D2D0D0D0D0D0D0D1; kEAn_2132_creditbase=0D9990D10000120D10000D0D0D0D0D0; kEAn_2132_creditrule=%E6%AF%8F%E5%A4%A9%E7%99%BB%E5%BD%95");
		// httpget.addHeader("Connection", "keep-alive");
		// 作为参数传递本地内容
		HttpResponse response = httpclient.execute(httpget, localContext);

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
			System.out.println("show headers...");
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
		}
		{
			System.out.println("show cookie...");
			List<Cookie> cookeList = cookieStore.getCookies();
			for (Cookie aa : cookeList) {
				System.out.println(aa);
			}
		}
		TestDiscuzSupport.support(cookieStore);
//		doSuport(cookieStore);
	}

	@SuppressWarnings({"resource" })
	public static void doSuport(CookieStore cookieStore) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		// TestDiscuzSupport.support(cookieStore);
		System.out.println("=================================");
		{
			String postData = "http://192.168.1.42/forum.php?mod=misc&action=recommend&do=add&tid=16&hash=128299cc&ajaxmenu=1&inajax=1&ajaxtarget=recommend_add_menu_content";
			HttpGet httpPost = new HttpGet(postData);
			// httpPost.addHeader("Cookie",
			// "sid=1854HwpLhpw%252BuR%252FRXmO2WxCMoWsGvrniDVywCRSwYHndWPLNwuFBVbne%252FA; PHPSESSID=jmd8f5q9se6mqkj1urbg5071e5; JishiGou_DVF5Q8_sid=ZXqVhO; AJSTAT_ok_times=1; kEAn_2132_sid=G8uMTU; kEAn_2132_saltkey=iSXp0U1p; kEAn_2132_lastvisit=1406719922; kEAn_2132_lastact=1406768228%09uc.php%09; kEAn_2132__refer=%252Fhome.php%253Fmod%253Dspacecp; kEAn_2132_seccode=12.effb0019e512b975ef; kEAn_2132_ulastactivity=ac53CAu1LnBovu6ZtH%2FETOmuDwOLtNywCPxyFY4OGw7TzNs4YHhB; kEAn_2132_auth=27dawLgP088Zgh1Ewmk1CVbaLkY83yiiS55c0riY%2FKtl3isufjfyJF5tBS8b%2Ff1zrxmw6Un1hTZvz61seQj7; kEAn_2132_lastcheckfeed=1%7C1406723537; kEAn_2132_lip=192.168.0.247%2C1406727265; kEAn_2132_nofavfid=1; kEAn_2132_st_p=1%7C1406724758%7C950ca549b4a22d38e14ebabf881224f0; kEAn_2132_visitedfid=75; kEAn_2132_viewid=tid_16; kEAn_2132_smile=1D1; kEAn_2132_creditnotice=0D0D2D0D0D0D0D0D0D1; kEAn_2132_creditbase=0D9990D10000120D10000D0D0D0D0D0; kEAn_2132_creditrule=%E6%AF%8F%E5%A4%A9%E7%99%BB%E5%BD%95");
			HttpContext localContext2 = new BasicHttpContext();
			// {
			// BasicClientCookie cookie = new BasicClientCookie("PHPSESSID",
			// "jmd8f5q9se6mqkj1urbg5071e5");
			// // cookie.setPath("/");
			// // cookie.setDomain("192.168.1.42");
			// // cookie.setVersion(0);
			// cookieStore.addCookie(cookie);
			// }
			// {
			// BasicClientCookie cookie = new BasicClientCookie("CP",
			// "CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR");
			// cookie.setPath("/");
			// cookie.setDomain("192.168.1.42");
			// cookie.setVersion(0);
			// cookieStore.addCookie(cookie);
			// }
			// 绑定定制的cookie store到本地内容中
			localContext2.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			HttpResponse response2 = httpclient.execute(httpPost, localContext2);
			{
				HttpEntity entity = response2.getEntity();
				if (entity != null) {
					if (response2.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
						System.out.println(res);
					}
				}
			}
			{
				System.out.println("show cookie2...");
				List<Cookie> cookeList = cookieStore.getCookies();
				for (Cookie aa : cookeList) {
					System.out.println(aa);
				}
			}

		}
	}
}
